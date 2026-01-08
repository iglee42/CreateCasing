package fr.iglee42.createcasing.mixins.create.arm_interactions;

import com.simibubi.create.content.kinetics.mechanicalArm.AllArmInteractionPointTypes;
import fr.iglee42.createcasing.casings.CasingSets;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(value = AllArmInteractionPointTypes.DepotType.class,remap = false)
public class DepotMixin {

    @Inject(method = "canCreatePoint", at = @At("HEAD"), cancellable = true)
    private void encased$addCustomDepot(Level level, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir){
        if (CasingSets.getSets().stream().filter(set-> Objects.nonNull(set.getDepot())).anyMatch(set->state.is(set.getDepot()))) cir.setReturnValue(true);
    }

}
