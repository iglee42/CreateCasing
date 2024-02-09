package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.contraptions.ITransformableBlock;
import com.simibubi.create.content.contraptions.StructureTransform;
import com.simibubi.create.content.kinetics.base.DirectionalAxisKineticBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.chainDrive.ChainDriveBlock;
import com.simibubi.create.content.kinetics.chainDrive.ChainGearshiftBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.utility.Iterate;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.SignalGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.PushReaction;

public class CustomChainDriveBlock extends RotatedPillarKineticBlock
        implements IBE<KineticBlockEntity>, ITransformableBlock {

    public static final Property<ChainDriveBlock.Part> PART = EnumProperty.create("part", ChainDriveBlock.Part.class);
    public static final BooleanProperty CONNECTED_ALONG_FIRST_COORDINATE =
            DirectionalAxisKineticBlock.AXIS_ALONG_FIRST_COORDINATE;

    private final String type;

    public CustomChainDriveBlock(Properties properties,String type) {
        super(properties);
        this.type = type;
        registerDefaultState(defaultBlockState().setValue(PART, ChainDriveBlock.Part.NONE));
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean shouldCheckWeakPower(BlockState state, SignalGetter level, BlockPos pos, Direction side) {
        return false;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.NORMAL;
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(PART, CONNECTED_ALONG_FIRST_COORDINATE));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Axis placedAxis = context.getNearestLookingDirection()
                .getAxis();
        Axis axis = context.getPlayer() != null && context.getPlayer()
                .isShiftKeyDown() ? placedAxis : getPreferredAxis(context);
        if (axis == null)
            axis = placedAxis;

        BlockState state = defaultBlockState().setValue(AXIS, axis);
        for (Direction facing : Iterate.directions) {
            if (facing.getAxis() == axis)
                continue;
            BlockPos pos = context.getClickedPos();
            BlockPos offset = pos.relative(facing);
            state = updateShape(state, facing, context.getLevel()
                    .getBlockState(offset), context.getLevel(), pos, offset);
        }
        return state;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction face, BlockState neighbour, LevelAccessor worldIn,
                                  BlockPos currentPos, BlockPos facingPos) {
        if ((neighbour.getBlock() instanceof CustomChainDriveBlock b && type.equals(b.getType())) || neighbour.isAir()) {

            ChainDriveBlock.Part part = stateIn.getValue(PART);
            Axis axis = stateIn.getValue(AXIS);
            boolean connectionAlongFirst = stateIn.getValue(CONNECTED_ALONG_FIRST_COORDINATE);
            Axis connectionAxis =
                    connectionAlongFirst ? (axis == Axis.X ? Axis.Y : Axis.X) : (axis == Axis.Z ? Axis.Y : Axis.Z);

            Axis faceAxis = face.getAxis();
            boolean facingAlongFirst = axis == Axis.X ? faceAxis.isVertical() : faceAxis == Axis.X;
            boolean positive = face.getAxisDirection() == AxisDirection.POSITIVE;

            if (axis == faceAxis)
                return stateIn;

            if (!(neighbour.getBlock() instanceof CustomChainDriveBlock)) {
                if (facingAlongFirst != connectionAlongFirst || part == ChainDriveBlock.Part.NONE)
                    return stateIn;
                if (part == ChainDriveBlock.Part.MIDDLE)
                    return stateIn.setValue(PART, positive ? ChainDriveBlock.Part.END : ChainDriveBlock.Part.START);
                if ((part == ChainDriveBlock.Part.START) == positive)
                    return stateIn.setValue(PART, ChainDriveBlock.Part.NONE);
                return stateIn;
            }

            ChainDriveBlock.Part otherPart = neighbour.getValue(PART);
            Axis otherAxis = neighbour.getValue(AXIS);
            boolean otherConnection = neighbour.getValue(CONNECTED_ALONG_FIRST_COORDINATE);
            Axis otherConnectionAxis =
                    otherConnection ? (otherAxis == Axis.X ? Axis.Y : Axis.X) : (otherAxis == Axis.Z ? Axis.Y : Axis.Z);

            if (neighbour.getValue(AXIS) == faceAxis)
                return stateIn;
            if (otherPart != ChainDriveBlock.Part.NONE && otherConnectionAxis != faceAxis)
                return stateIn;

            if (part == ChainDriveBlock.Part.NONE) {
                part = positive ? ChainDriveBlock.Part.START : ChainDriveBlock.Part.END;
                connectionAlongFirst = axis == Axis.X ? faceAxis.isVertical() : faceAxis == Axis.X;
            } else if (connectionAxis != faceAxis) {
                return stateIn;
            }

            if ((part == ChainDriveBlock.Part.START) != positive)
                part = ChainDriveBlock.Part.MIDDLE;

            return stateIn.setValue(PART, part)
                    .setValue(CONNECTED_ALONG_FIRST_COORDINATE, connectionAlongFirst);
        }
        return stateIn;
    }

    @Override
    public BlockState getRotatedBlockState(BlockState originalState, Direction targetedFace) {
        if (originalState.getValue(PART) == ChainDriveBlock.Part.NONE)
            return super.getRotatedBlockState(originalState, targetedFace);
        return super.getRotatedBlockState(originalState,
                Direction.get(AxisDirection.POSITIVE, getConnectionAxis(originalState)));
    }

    @Override
    public BlockState updateAfterWrenched(BlockState newState, UseOnContext context) {
//		Blocks.AIR.getDefaultState()
//			.updateNeighbors(context.getWorld(), context.getPos(), 1);
        Axis axis = newState.getValue(AXIS);
        newState = defaultBlockState().setValue(AXIS, axis);
        if (newState.hasProperty(BlockStateProperties.POWERED))
            newState = newState.setValue(BlockStateProperties.POWERED, context.getLevel()
                    .hasNeighborSignal(context.getClickedPos()));
        for (Direction facing : Iterate.directions) {
            if (facing.getAxis() == axis)
                continue;
            BlockPos pos = context.getClickedPos();
            BlockPos offset = pos.relative(facing);
            newState = updateShape(newState, facing, context.getLevel()
                    .getBlockState(offset), context.getLevel(), pos, offset);
        }
//		newState.updateNeighbors(context.getWorld(), context.getPos(), 1 | 2);
        return newState;
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == state.getValue(AXIS);
    }

    @Override
    public Axis getRotationAxis(BlockState state) {
        return state.getValue(AXIS);
    }

    public static boolean areBlocksConnected(BlockState state, BlockState other, Direction facing) {
        if (!(state.getBlock() instanceof CustomChainDriveBlock b && other.getBlock() instanceof CustomChainDriveBlock b1 && b.getType().equals(b1.getType()))) return false;
        ChainDriveBlock.Part part = state.getValue(PART);
        Axis connectionAxis = getConnectionAxis(state);
        Axis otherConnectionAxis = getConnectionAxis(other);

        if (otherConnectionAxis != connectionAxis)
            return false;
        if (facing.getAxis() != connectionAxis)
            return false;
        if (facing.getAxisDirection() == AxisDirection.POSITIVE && (part == ChainDriveBlock.Part.MIDDLE || part == ChainDriveBlock.Part.START))
            return true;
        if (facing.getAxisDirection() == AxisDirection.NEGATIVE && (part == ChainDriveBlock.Part.MIDDLE || part == ChainDriveBlock.Part.END))
            return true;

        return false;
    }

    protected static Axis getConnectionAxis(BlockState state) {
        Axis axis = state.getValue(AXIS);
        boolean connectionAlongFirst = state.getValue(CONNECTED_ALONG_FIRST_COORDINATE);
        Axis connectionAxis =
                connectionAlongFirst ? (axis == Axis.X ? Axis.Y : Axis.X) : (axis == Axis.Z ? Axis.Y : Axis.Z);
        return connectionAxis;
    }

    public static float getRotationSpeedModifier(KineticBlockEntity from, KineticBlockEntity to) {
        float fromMod = 1;
        float toMod = 1;
        if (from instanceof ChainGearshiftBlockEntity)
            fromMod = ((ChainGearshiftBlockEntity) from).getModifier();
        if (to instanceof ChainGearshiftBlockEntity)
            toMod = ((ChainGearshiftBlockEntity) to).getModifier();
        return fromMod / toMod;
    }
    
    @Override
    public Class<KineticBlockEntity> getBlockEntityClass() {
        return KineticBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return ModBlockEntities.ENCASED_SHAFT.get();
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return rotate(state, rot, Axis.Y);
    }

    protected BlockState rotate(BlockState pState, Rotation rot, Axis rotAxis) {
        Axis connectionAxis = getConnectionAxis(pState);
        Direction direction = Direction.fromAxisAndDirection(connectionAxis, AxisDirection.POSITIVE);
        Direction normal = Direction.fromAxisAndDirection(pState.getValue(AXIS), AxisDirection.POSITIVE);
        for (int i = 0; i < rot.ordinal(); i++) {
            direction = direction.getClockWise(rotAxis);
            normal = normal.getClockWise(rotAxis);
        }

        if (direction.getAxisDirection() == AxisDirection.NEGATIVE)
            pState = reversePart(pState);

        Axis newAxis = normal.getAxis();
        Axis newConnectingDirection = direction.getAxis();
        boolean alongFirst = newAxis == Axis.X && newConnectingDirection == Axis.Y
                || newAxis != Axis.X && newConnectingDirection == Axis.X;

        return pState.setValue(AXIS, newAxis)
                .setValue(CONNECTED_ALONG_FIRST_COORDINATE, alongFirst);
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        Axis connectionAxis = getConnectionAxis(pState);
        if (pMirror.mirror(Direction.fromAxisAndDirection(connectionAxis, AxisDirection.POSITIVE))
                .getAxisDirection() == AxisDirection.POSITIVE)
            return pState;
        return reversePart(pState);
    }

    protected BlockState reversePart(BlockState pState) {
        ChainDriveBlock.Part part = pState.getValue(PART);
        if (part == ChainDriveBlock.Part.START)
            return pState.setValue(PART, ChainDriveBlock.Part.END);
        if (part == ChainDriveBlock.Part.END)
            return pState.setValue(PART, ChainDriveBlock.Part.START);
        return pState;
    }

    @Override
    public BlockState transform(BlockState state, StructureTransform transform) {
        return rotate(mirror(state, transform.mirror), transform.rotation, transform.rotationAxis);
    }

}

