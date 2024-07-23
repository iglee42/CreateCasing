package fr.iglee42.createcasing.items;

import static com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock.AXIS;

import java.util.List;
import java.util.function.Predicate;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.placement.IPlacementHelper;
import com.simibubi.create.foundation.placement.PlacementHelpers;
import com.simibubi.create.foundation.placement.PlacementOffset;
import com.simibubi.create.foundation.utility.Iterate;

import fr.iglee42.createcasing.api.items.ApiCogwheelBlockItem;
import fr.iglee42.createcasing.blocks.customs.WoodenCogwheelBlock;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class WoodenCogwheelBlockItem extends BlockItem {

	boolean large;

	private final int placementHelperId;
	private final int integratedCogHelperId;

	public WoodenCogwheelBlockItem(WoodenCogwheelBlock block, Properties builder) {
		super(block, builder);
		large = block.isLarge;

		placementHelperId = PlacementHelpers.register(large ? new LargeCogHelper() : new SmallCogHelper());
		integratedCogHelperId =
			PlacementHelpers.register(large ? new CogwheelBlockItem.IntegratedLargeCogHelper() : new CogwheelBlockItem.IntegratedSmallCogHelper());
	}

	@Override
	public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
		Level world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = world.getBlockState(pos);

		IPlacementHelper helper = PlacementHelpers.get(placementHelperId);
		Player player = context.getPlayer();
		BlockHitResult ray = new BlockHitResult(context.getClickLocation(), context.getClickedFace(), pos, true);
		if (helper.matchesState(state) && player != null && !player.isShiftKeyDown()) {
			return helper.getOffset(player, world, state, pos, ray)
				.placeInWorld(world, this, player, context.getHand(), ray);
		}

		if (integratedCogHelperId != -1) {
			helper = PlacementHelpers.get(integratedCogHelperId);

			if (helper.matchesState(state) && player != null && !player.isShiftKeyDown()) {
				return helper.getOffset(player, world, state, pos, ray)
					.placeInWorld(world, this, player, context.getHand(), ray);
			}
		}

		return super.onItemUseFirst(stack, context);
	}


	@MethodsReturnNonnullByDefault
	private static class SmallCogHelper extends CogwheelBlockItem.DiagonalCogHelper {

		@Override
		public Predicate<ItemStack> getItemPredicate() {
			return ((Predicate<ItemStack>) ICogWheel::isSmallCogItem).and(ICogWheel::isDedicatedCogItem);
		}

		@Override
		public PlacementOffset getOffset(Player player, Level world, BlockState state, BlockPos pos,
										 BlockHitResult ray) {
			if (hitOnShaft(state, ray))
				return PlacementOffset.fail();

			if (!ICogWheel.isLargeCog(state)) {
				Axis axis = ((IRotate) state.getBlock()).getRotationAxis(state);
				List<Direction> directions = IPlacementHelper.orderedByDistanceExceptAxis(pos, ray.getLocation(), axis);

				for (Direction dir : directions) {
					BlockPos newPos = pos.relative(dir);

					if (!CogWheelBlock.isValidCogwheelPosition(false, world, newPos, axis))
						continue;

					if (!world.getBlockState(newPos)
							.canBeReplaced())
						continue;

					return PlacementOffset.success(newPos, s -> s.setValue(AXIS, axis));

				}

				return PlacementOffset.fail();
			}

			return super.getOffset(player, world, state, pos, ray);
		}
	}

	@MethodsReturnNonnullByDefault
	private static class LargeCogHelper extends CogwheelBlockItem.DiagonalCogHelper {

		@Override
		public Predicate<ItemStack> getItemPredicate() {
			return ((Predicate<ItemStack>) ICogWheel::isLargeCogItem).and(ICogWheel::isDedicatedCogItem);
		}

		@Override
		public PlacementOffset getOffset(Player player, Level world, BlockState state, BlockPos pos,
										 BlockHitResult ray) {
			if (hitOnShaft(state, ray))
				return PlacementOffset.fail();

			if (ICogWheel.isLargeCog(state)) {
				Axis axis = ((IRotate) state.getBlock()).getRotationAxis(state);
				Direction side = IPlacementHelper.orderedByDistanceOnlyAxis(pos, ray.getLocation(), axis)
						.get(0);
				List<Direction> directions = IPlacementHelper.orderedByDistanceExceptAxis(pos, ray.getLocation(), axis);
				for (Direction dir : directions) {
					BlockPos newPos = pos.relative(dir)
							.relative(side);

					if (!CogWheelBlock.isValidCogwheelPosition(true, world, newPos, dir.getAxis()))
						continue;

					if (!world.getBlockState(newPos)
							.canBeReplaced())
						continue;

					return PlacementOffset.success(newPos, s -> s.setValue(AXIS, dir.getAxis()));
				}

				return PlacementOffset.fail();
			}

			return super.getOffset(player, world, state, pos, ray);
		}
	}

}
