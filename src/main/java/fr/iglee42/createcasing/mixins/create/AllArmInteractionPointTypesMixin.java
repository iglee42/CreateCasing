package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.kinetics.mechanicalArm.AllArmInteractionPointTypes;
import fr.iglee42.createcasing.blocks.api.ApiDepotBlock;
import fr.iglee42.createcasing.registries.ModBlocks;
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
        if (ModBlocks.BRASS_DEPOT.has(state) || ModBlocks.COPPER_DEPOT.has(state) || ModBlocks.RAILWAY_DEPOT.has(state) || ModBlocks.INDUSTRIAL_IRON_DEPOT.has(state) || ModBlocks.CREATIVE_DEPOT.has(state) || state.getBlock() instanceof ApiDepotBlock) cir.setReturnValue(true);
    }

}
