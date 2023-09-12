package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import fr.iglee42.createcasing.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MetalShaftBlock extends ShaftBlock {
    public MetalShaftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return ModBlockEntities.METAL_SHAFT.get();
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
        } else if (item.equals(Items.MANGROVE_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.MANGROVE_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.BAMBOO_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.BAMBOO_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.CHERRY_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.CHERRY_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.CRIMSON_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.CRIMSON_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        } else if (item.equals(Items.WARPED_PLANKS)) {
            Direction.Axis axis = state.getValue(AXIS);
            world.setBlockAndUpdate(pos, ModBlocks.WARPED_SHAFT.getDefaultState().setValue(AXIS, axis));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
