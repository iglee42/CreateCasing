package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.chainDrive.ChainDriveBlock;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CustomChainDriveBlock extends ChainDriveBlock {

    private final String type;

    public CustomChainDriveBlock(Properties properties,String type) {
        super(properties);
        this.type = type;
    }

    public String getType() {
        return type;
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

    public static boolean areCustomBlocksConnected(BlockState state, BlockState other, Direction facing) {
        if (!(state.getBlock() instanceof CustomChainDriveBlock b && other.getBlock() instanceof CustomChainDriveBlock b1 && b.getType().equals(b1.getType()))) return false;
        return areBlocksConnected(state, other, facing);
    }


    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.CHAIN_DRIVE.get();
    }
}

