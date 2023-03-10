package fr.iglee42.createcasing.mixins.minecraft;

import fr.iglee42.createcasing.compatibility.createextendedcogs.CreateExtendedCogwheelsCompat;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {


    @Inject(method = "isValid",at = @At("HEAD"),cancellable = true)
    private void isValid(BlockState state, CallbackInfoReturnable<Boolean> cir){
        if (this.equals(CreateExtendedCogwheelsCompat.COGWHEEL.get()) && state.getBlock().getRegistryName().getNamespace().equals(CreateExtendedCogwheelsCompat.REGISTER_MODID)){
            cir.setReturnValue(true);
        }
    }

}
