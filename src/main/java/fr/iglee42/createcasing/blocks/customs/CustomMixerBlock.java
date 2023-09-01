package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.block.IBE;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import fr.iglee42.createcasing.blockEntities.CustomMixerBlockEntity;
import fr.iglee42.createcasing.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class    CustomMixerBlock extends KineticBlock implements IBE<CustomMixerBlockEntity>, ICogWheel {

    public CustomMixerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return !AllBlocks.BASIN.has(worldIn.getBlockState(pos.below()));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult p_60508_) {


        if (player.getItemInHand(hand).is(AllBlocks.ANDESITE_CASING.get().asItem())) {
            world.setBlockAndUpdate(blockPos, AllBlocks.MECHANICAL_MIXER.getDefaultState());
            return InteractionResult.SUCCESS;
        } else if (player.getItemInHand(hand).is(AllBlocks.BRASS_CASING.get().asItem())) {
            if (ModBlocks.COPPER_MIXER.has(blockState) || ModBlocks.RAILWAY_MIXER.has(blockState)  || ModBlocks.INDUSTRIAL_IRON_MIXER.has(blockState))
                world.setBlockAndUpdate(blockPos, ModBlocks.BRASS_MIXER.getDefaultState());
            return InteractionResult.SUCCESS;
        } else if (player.getItemInHand(hand).is(AllBlocks.COPPER_CASING.get().asItem())) {
            if (ModBlocks.BRASS_MIXER.has(blockState)||ModBlocks.RAILWAY_MIXER.has(blockState)  || ModBlocks.INDUSTRIAL_IRON_MIXER.has(blockState) )
                world.setBlockAndUpdate(blockPos, ModBlocks.COPPER_MIXER.getDefaultState());
            return InteractionResult.SUCCESS;
        } else if (player.getItemInHand(hand).is(AllBlocks.RAILWAY_CASING.get().asItem())) {
            if (ModBlocks.BRASS_MIXER.has(blockState)||ModBlocks.COPPER_MIXER.has(blockState) || ModBlocks.INDUSTRIAL_IRON_MIXER.has(blockState))
                world.setBlockAndUpdate(blockPos, ModBlocks.RAILWAY_MIXER.getDefaultState());
            return InteractionResult.SUCCESS;
        }else if (player.getItemInHand(hand).is(AllBlocks.INDUSTRIAL_IRON_BLOCK.get().asItem())) {
            if (ModBlocks.BRASS_MIXER.has(blockState)||ModBlocks.COPPER_MIXER.has(blockState) || ModBlocks.RAILWAY_MIXER.has(blockState))
                world.setBlockAndUpdate(blockPos, ModBlocks.INDUSTRIAL_IRON_MIXER.getDefaultState());
            return InteractionResult.SUCCESS;
        }
        return super.use(blockState, world, blockPos, player, hand, p_60508_);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext
                && ((EntityCollisionContext) context).getEntity() instanceof Player)
            return AllShapes.CASING_14PX.get(Direction.DOWN);

        return AllShapes.MECHANICAL_PROCESSOR_SHAPE;
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.Y;
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return false;
    }

    @Override
    public float getParticleTargetRadius() {
        return .85f;
    }

    @Override
    public float getParticleInitialRadius() {
        return .75f;
    }

    @Override
    public IRotate.SpeedLevel getMinimumRequiredSpeedLevel() {
        return IRotate.SpeedLevel.MEDIUM;
    }

    @Override
    public Class<CustomMixerBlockEntity> getBlockEntityClass() {
        return CustomMixerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends CustomMixerBlockEntity> getBlockEntityType() {
        return ModBlockEntities.MIXER.get();
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type) {
        return false;
    }

}
