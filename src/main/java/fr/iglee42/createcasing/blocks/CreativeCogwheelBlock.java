package fr.iglee42.createcasing.blocks;

import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.block.IBE;
import fr.iglee42.createcasing.blockEntities.CreativeCogwheelBlockEntity;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class CreativeCogwheelBlock extends RotatedPillarKineticBlock implements IBE<CreativeCogwheelBlockEntity>,ICogWheel {

    public CreativeCogwheelBlock(Properties properties) {
        super(properties);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState placedOn = context.getLevel()
                .getBlockState(context.getClickedPos()
                        .relative(context.getClickedFace()
                                .getOpposite()));
        BlockState stateForPlacement = super.getStateForPlacement(context);
        if (ICogWheel.isSmallCog(placedOn))
            stateForPlacement =
                    stateForPlacement.setValue(AXIS, ((IRotate) placedOn.getBlock()).getRotationAxis(placedOn));
        return stateForPlacement;
    }

    // IRotate:

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return false;
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(AXIS);
    }

    @Override
    public boolean hideStressImpact() {
        return true;
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public Class<CreativeCogwheelBlockEntity> getBlockEntityClass() {
        return CreativeCogwheelBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends CreativeCogwheelBlockEntity> getBlockEntityType() {
        return ModBlockEntities.CREATIVE_COGWHEEL.get();
    }

    @Override
    public boolean isDedicatedCogWheel() {
        return true;
    }

    @Override
    public boolean isSmallCog() {
        return true;
    }
}
