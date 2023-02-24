package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.ShaftBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedShaftBlock;
import com.simibubi.create.content.curiosities.girder.GirderEncasedShaftBlock;
import com.simibubi.create.foundation.utility.placement.IPlacementHelper;
import com.simibubi.create.foundation.utility.placement.PlacementHelpers;
import fr.iglee42.createcasing.compatibility.CreateCrystalClearCompatibility;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.List;

import static com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock.AXIS;
import static com.simibubi.create.content.contraptions.relays.elementary.ShaftBlock.placementHelperId;
import static com.simibubi.create.foundation.block.ProperWaterloggedBlock.WATERLOGGED;

@Mixin(ShaftBlock.class)
public class ShaftBlockMixin {

    /**
     * @author iglee42
     * @reason Allow to use all encased shaft
     * @null
     */
    @Overwrite
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray) {
        if (!player.isShiftKeyDown() && player.mayBuild()) {
            ItemStack heldItem = player.getItemInHand(hand);
            if (CreateCrystalClearCompatibility.checkShaft(heldItem,world,pos,state)) return InteractionResult.SUCCESS;
            List<EncasedShaftBlock> shafts = new ArrayList<>();
            ForgeRegistries.BLOCKS.getKeys().stream().filter(r-> ForgeRegistries.BLOCKS.getValue(r) instanceof EncasedShaftBlock).forEach(r->shafts.add((EncasedShaftBlock) ForgeRegistries.BLOCKS.getValue(r)));
            EncasedShaftBlock[] var8 = shafts.toArray(new EncasedShaftBlock[]{});
            int var9 = var8.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                EncasedShaftBlock encasedShaft = var8[var10];
                if (encasedShaft.getCasing().isIn(heldItem)) {
                    if (world.isClientSide) {
                        return InteractionResult.SUCCESS;
                    }

                    KineticTileEntity.switchToBlockState(world, pos, (BlockState)encasedShaft.defaultBlockState().setValue(AXIS, (Direction.Axis)state.getValue(AXIS)));
                    return InteractionResult.SUCCESS;
                }
            }

            if (AllBlocks.METAL_GIRDER.isIn(heldItem) && state.getValue(AXIS) != Direction.Axis.Y) {
                KineticTileEntity.switchToBlockState(world, pos, (BlockState)((BlockState)AllBlocks.METAL_GIRDER_ENCASED_SHAFT.getDefaultState().setValue(WATERLOGGED, (Boolean)state.getValue(WATERLOGGED))).setValue(GirderEncasedShaftBlock.HORIZONTAL_AXIS, state.getValue(AXIS) == Direction.Axis.Z ? Direction.Axis.Z : Direction.Axis.X));
                if (!world.isClientSide && !player.isCreative()) {
                    heldItem.shrink(1);
                    if (heldItem.isEmpty()) {
                        player.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }

                return InteractionResult.SUCCESS;
            } else {
                IPlacementHelper helper = PlacementHelpers.get(placementHelperId);
                if (helper.matchesItem(heldItem)) {
                    return helper.getOffset(player, world, state, pos, ray).placeInWorld(world, (BlockItem)heldItem.getItem(), player, hand, ray);
                } else {
                    return InteractionResult.PASS;
                }
            }
        } else {
            return InteractionResult.PASS;
        }
    }

}
