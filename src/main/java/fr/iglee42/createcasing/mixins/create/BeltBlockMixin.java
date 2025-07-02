package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = BeltBlock.class)
public abstract class BeltBlockMixin {

    @Shadow public abstract void updateCoverProperty(LevelAccessor world, BlockPos pos, BlockState state);

    @Inject(method = "useItemOn",remap = false,at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;isIn(Lnet/minecraft/world/item/ItemStack;)Z",ordinal = 1,shift = At.Shift.BEFORE),locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void encased$otherCasingUses(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<ItemInteractionResult> cir, boolean isWrench, boolean isConnector, boolean isShaft){
        if (cmr$customCasingUse(AllBlocks.COPPER_CASING, EncasedBlocks.COPPER_BELT_CASING,stack,level,pos,player)) {
            cir.setReturnValue(ItemInteractionResult.SUCCESS);
            return;
        }
        if (cmr$customCasingUse(AllBlocks.RAILWAY_CASING, EncasedBlocks.RAILWAY_BELT_CASING,stack,level,pos,player)) {
            cir.setReturnValue(ItemInteractionResult.SUCCESS);
            return;
        }
        if (cmr$customCasingUse(AllBlocks.INDUSTRIAL_IRON_BLOCK, EncasedBlocks.INDUSTRIAL_IRON_BELT_CASING,stack,level,pos,player)) {
            cir.setReturnValue(ItemInteractionResult.SUCCESS);
            return;
        }
        if (cmr$customCasingUse(AllBlocks.WEATHERED_IRON_BLOCK, EncasedBlocks.WEATHERED_IRON_BELT_CASING,stack,level,pos,player)) {
            cir.setReturnValue(ItemInteractionResult.SUCCESS);
            return;
        }
        if (cmr$customCasingUse(EncasedBlocks.CREATIVE_CASING, EncasedBlocks.CREATIVE_BELT_CASING,stack,level,pos,player)) {
            cir.setReturnValue(ItemInteractionResult.SUCCESS);
            return;
        }
        if (cmr$customCasingUse(AllBlocks.SHADOW_STEEL_CASING, EncasedBlocks.SHADOW_STEEL_BELT_CASING,stack,level,pos,player)) {
            cir.setReturnValue(ItemInteractionResult.SUCCESS);
            return;
        }
        if (cmr$customCasingUse(AllBlocks.REFINED_RADIANCE_CASING, EncasedBlocks.REFINED_RADIANCE_BELT_CASING,stack,level,pos,player)) {
            cir.setReturnValue(ItemInteractionResult.SUCCESS);
            return;
        }
    }


    @Unique
    private boolean cmr$customCasingUse(BlockEntry<? extends Block> entry, BeltBlockEntity.CasingType type,ItemStack heldItem,Level world,BlockPos pos,Player player){
        if (entry.isIn(heldItem) && type != null) {
            if (world.getBlockEntity(pos) instanceof BeltBlockEntity be)
                be.setCasingType(type);
            updateCoverProperty(world, pos, world.getBlockState(pos));

            SoundType soundType = entry.getDefaultState()
                    .getSoundType(world, pos, player);
            world.playSound(null, pos, soundType.getPlaceSound(), SoundSource.BLOCKS,
                    (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);

            return true;
        }
        return false;
    }

}
