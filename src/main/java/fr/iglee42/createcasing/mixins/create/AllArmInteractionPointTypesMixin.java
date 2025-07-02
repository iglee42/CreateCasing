package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.kinetics.mechanicalArm.AllArmInteractionPointTypes;
import fr.iglee42.createcasing.api.blocks.ApiDepotBlock;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AllArmInteractionPointTypes.DepotType.class,remap = false)
public class AllArmInteractionPointTypesMixin {

    @Inject(method = "canCreatePoint", at = @At("HEAD"), cancellable = true)
    private void inject(Level level, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir){
        if (EncasedBlocks.BRASS_DEPOT.has(state) || EncasedBlocks.COPPER_DEPOT.has(state) || EncasedBlocks.RAILWAY_DEPOT.has(state) || EncasedBlocks.INDUSTRIAL_IRON_DEPOT.has(state) || EncasedBlocks.CREATIVE_DEPOT.has(state) || EncasedBlocks.WEATHERED_IRON_DEPOT.has(state)|| EncasedBlocks.REFINED_RADIANCE_DEPOT.has(state) || EncasedBlocks.SHADOW_STEEL_DEPOT.has(state) || state.getBlock() instanceof ApiDepotBlock) cir.setReturnValue(true);
    }

}
