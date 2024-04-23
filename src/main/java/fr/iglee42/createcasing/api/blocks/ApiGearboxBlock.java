package fr.iglee42.createcasing.api.blocks;

import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import com.tterrag.registrate.util.entry.ItemEntry;
import fr.iglee42.createcasing.api.items.ApiVerticalGearboxItem;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import fr.iglee42.createcasing.utils.Deferred;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.HitResult;

import java.util.Arrays;
import java.util.List;


/*
This class is a copy from the original class GearboxBlock
 */
public class ApiGearboxBlock extends RotatedPillarKineticBlock implements IBE<GearboxBlockEntity> {

	private Deferred<ItemEntry<ApiVerticalGearboxItem>> verticalItem;

	public ApiGearboxBlock(Properties properties, Deferred<ItemEntry<ApiVerticalGearboxItem>> verticalItem) {
		super(properties);
		this.verticalItem = verticalItem;
	}


	public BlockEntityType<? extends GearboxBlockEntity> getBlockEntityType() {
		return ModBlockEntities.API_GEARBOX.get();
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        if (verticalItem == null) return super.getDrops(state, builder);
        if (state.getValue(AXIS).isVertical())
			return super.getDrops(state, builder);
		return Arrays.asList(new ItemStack(verticalItem.data.get()));
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos,
									   Player player) {
		if (verticalItem == null) return super.getCloneItemStack(state, target, world, pos, player);
		if (state.getValue(AXIS).isVertical())
			return super.getCloneItemStack(state, target, world, pos, player);
		return new ItemStack(verticalItem.data.get());
	}


	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(AXIS, Direction.Axis.Y);
	}

	// IRotate:

	@Override
	public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
		return face.getAxis() != state.getValue(AXIS);
	}

	@Override
	public Direction.Axis getRotationAxis(BlockState state) {
		return state.getValue(AXIS);
	}

	@Override
	public Class<GearboxBlockEntity> getBlockEntityClass() {
		return GearboxBlockEntity.class;
	}

}