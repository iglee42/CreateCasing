package fr.iglee42.createcasing.blocks.customs;

import com.google.common.base.Predicates;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.AbstractSimpleShaftBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import com.simibubi.create.content.kinetics.steamEngine.PoweredShaftBlock;
import com.simibubi.create.foundation.placement.IPlacementHelper;
import com.simibubi.create.foundation.placement.PlacementHelpers;
import com.simibubi.create.foundation.placement.PlacementOffset;
import com.simibubi.create.foundation.placement.PoleHelper;
import fr.iglee42.createcasing.blockEntities.WoodenShaftBlockEntity;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import fr.iglee42.createcasing.registries.ModBlocks;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;

import java.util.function.Predicate;

public class WoodenShaftBlock extends ShaftBlock {

    public static final int placementHelperId = PlacementHelpers.register(new WoodenShaftBlock.PlacementHelper());

    public WoodenShaftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return ModBlockEntities.WOODEN_SHAFT.get();
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray) {
        InteractionResult result = super.use(state, world, pos, player, hand, ray);
        if (result != InteractionResult.PASS) return result;
        Item item = player.getItemInHand(hand).getItem();
        if (item.equals(Items.OAK_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.OAK_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.SPRUCE_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.SPRUCE_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.BIRCH_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.BIRCH_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.JUNGLE_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.JUNGLE_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.ACACIA_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.ACACIA_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.DARK_OAK_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.DARK_OAK_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.CRIMSON_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.CRIMSON_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.WARPED_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.WARPED_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.BLACKSTONE)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.MLDEG_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        }
        IPlacementHelper helper = PlacementHelpers.get(placementHelperId);
        ItemStack heldItem = player.getItemInHand(hand);
        if (helper.matchesItem(heldItem))
            return helper.getOffset(player, world, state, pos, ray)
                    .placeInWorld(world, (BlockItem) heldItem.getItem(), player, hand, ray);
        return InteractionResult.PASS;
    }

    @MethodsReturnNonnullByDefault
    private static class PlacementHelper extends PoleHelper<Direction.Axis> {
        // used for extending a shaft in its axis, like the piston poles. works with
        // shafts and cogs

        private PlacementHelper() {
            super(state -> state.getBlock() instanceof AbstractSimpleShaftBlock
                    || state.getBlock() instanceof PoweredShaftBlock, state -> state.getValue(AXIS), AXIS);
        }

        @Override
        public Predicate<ItemStack> getItemPredicate() {
            return i -> i.getItem() instanceof BlockItem
                    && ((BlockItem) i.getItem()).getBlock() instanceof AbstractSimpleShaftBlock;
        }

        @Override
        public Predicate<BlockState> getStatePredicate() {
            return s->s.getBlock() instanceof AbstractSimpleShaftBlock;
        }

        @Override
        public PlacementOffset getOffset(Player player, Level world, BlockState state, BlockPos pos,
                                         BlockHitResult ray) {
            PlacementOffset offset = super.getOffset(player, world, state, pos, ray);
            if (offset.isSuccessful())
                offset.withTransform(offset.getTransform()
                        .andThen(s -> ShaftBlock.pickCorrectShaftType(s, world, offset.getBlockPos())));
            return offset;
        }

    }
}
