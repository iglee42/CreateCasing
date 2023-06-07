package fr.iglee42.createcasing.mixins.minecraft;

import fr.iglee42.createcasing.ModBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockEntityType.class,remap = true)
public class BlockEntityTypeMixin {


    @Inject(method = "isValid",at = @At("HEAD"),cancellable = true)
    private void isValid(BlockState state, CallbackInfoReturnable<Boolean> cir){
        if (this.equals(ModBlockEntities.ENCASED_SHAFT.get()) || this.equals(ModBlockEntities.ENCASED_COGWHEEL.get()) || this.equals(ModBlockEntities.ENCASED_COGWHEEL_LARGE.get()) ||this.equals(ModBlockEntities.ENCASED_FLUID_PIPE.get())) cir.setReturnValue(true);
    }

}
