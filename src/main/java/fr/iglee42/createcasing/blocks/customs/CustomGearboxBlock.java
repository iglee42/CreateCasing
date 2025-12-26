package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;
import fr.iglee42.createcasing.items.CustomVerticalGearboxItem;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.HitResult;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class CustomGearboxBlock extends GearboxBlock {

	private final Supplier<BlockItem> verticalItem;

	public CustomGearboxBlock(Properties properties , Supplier<BlockItem> verticalItem) {
		super(properties);
		this.verticalItem = verticalItem;
	}

	public BlockEntityType<? extends GearboxBlockEntity> getBlockEntityType() {
		return EncasedBlockEntities.GEARBOX.get();
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
		if (state.getValue(AXIS).isVertical())
			return super.getDrops(state, builder);
		return Arrays.asList(new ItemStack(verticalItem.get()));
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader world, BlockPos pos,
									   Player player) {
		if (state.getValue(AXIS).isVertical())
			return super.getCloneItemStack(state, target, world, pos, player);
		return new ItemStack(verticalItem.get());
	}

}