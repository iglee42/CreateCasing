package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import fr.iglee42.createcasing.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
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

public class CustomPressBlock extends HorizontalKineticBlock implements IBE<MechanicalPressBlockEntity> {

	public CustomPressBlock(Properties properties) {
		super(properties);
	}


	@Override
	public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult p_60508_) {

			Direction facing = blockState.getValue(HORIZONTAL_FACING);

			if (player.getItemInHand(hand).is(AllBlocks.ANDESITE_CASING.get().asItem())) {
				world.setBlockAndUpdate(blockPos, AllBlocks.MECHANICAL_PRESS.getDefaultState().setValue(HORIZONTAL_FACING, facing));
				return InteractionResult.SUCCESS;
			} else if (player.getItemInHand(hand).is(AllBlocks.BRASS_CASING.get().asItem())) {
				if (ModBlocks.COPPER_PRESS.has(blockState) || ModBlocks.RAILWAY_PRESS.has(blockState) || ModBlocks.INDUSTRIAL_IRON_PRESS.has(blockState) || ModBlocks.CREATIVE_PRESS.has(blockState))
					world.setBlockAndUpdate(blockPos, ModBlocks.BRASS_PRESS.getDefaultState().setValue(HORIZONTAL_FACING, facing));
				return InteractionResult.SUCCESS;
			} else if (player.getItemInHand(hand).is(AllBlocks.COPPER_CASING.get().asItem())) {
				if (ModBlocks.BRASS_PRESS.has(blockState)||ModBlocks.RAILWAY_PRESS.has(blockState) || ModBlocks.INDUSTRIAL_IRON_PRESS.has(blockState) || ModBlocks.CREATIVE_PRESS.has(blockState))
					world.setBlockAndUpdate(blockPos, ModBlocks.COPPER_PRESS.getDefaultState().setValue(HORIZONTAL_FACING, facing));
				return InteractionResult.SUCCESS;
			} else if (player.getItemInHand(hand).is(AllBlocks.RAILWAY_CASING.get().asItem())) {
				if (ModBlocks.BRASS_PRESS.has(blockState)||ModBlocks.COPPER_PRESS.has(blockState) || ModBlocks.INDUSTRIAL_IRON_PRESS.has(blockState) || ModBlocks.CREATIVE_PRESS.has(blockState))
					world.setBlockAndUpdate(blockPos, ModBlocks.RAILWAY_PRESS.getDefaultState().setValue(HORIZONTAL_FACING, facing));
				return InteractionResult.SUCCESS;
			} else if (player.getItemInHand(hand).is(AllBlocks.INDUSTRIAL_IRON_BLOCK.get().asItem())) {
				if (ModBlocks.BRASS_PRESS.has(blockState)||ModBlocks.COPPER_PRESS.has(blockState) || ModBlocks.RAILWAY_PRESS.has(blockState) || ModBlocks.CREATIVE_PRESS.has(blockState))
					world.setBlockAndUpdate(blockPos, ModBlocks.INDUSTRIAL_IRON_PRESS.getDefaultState().setValue(HORIZONTAL_FACING, facing));
				return InteractionResult.SUCCESS;
			} else if (player.getItemInHand(hand).is(ModBlocks.CREATIVE_CASING.get().asItem())) {
				if (ModBlocks.BRASS_PRESS.has(blockState)||ModBlocks.COPPER_PRESS.has(blockState) || ModBlocks.RAILWAY_PRESS.has(blockState) || ModBlocks.INDUSTRIAL_IRON_PRESS.has(blockState))
					world.setBlockAndUpdate(blockPos, ModBlocks.CREATIVE_PRESS.getDefaultState().setValue(HORIZONTAL_FACING, facing));
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
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		return !AllBlocks.BASIN.has(worldIn.getBlockState(pos.below()));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction prefferedSide = getPreferredHorizontalFacing(context);
		if (prefferedSide != null)
			return defaultBlockState().setValue(HORIZONTAL_FACING, prefferedSide);
		return super.getStateForPlacement(context);
	}

	@Override
	public Axis getRotationAxis(BlockState state) {
		return state.getValue(HORIZONTAL_FACING)
			.getAxis();
	}

	@Override
	public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
		return face.getAxis() == state.getValue(HORIZONTAL_FACING)
			.getAxis();
	}

	@Override
	public Class<MechanicalPressBlockEntity> getBlockEntityClass() {
		return MechanicalPressBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends MechanicalPressBlockEntity> getBlockEntityType() {
		return ModBlockEntities.PRESS.get();
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type) {
		return false;
	}

}
