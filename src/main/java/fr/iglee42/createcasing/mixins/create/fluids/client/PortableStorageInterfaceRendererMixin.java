package fr.iglee42.createcasing.mixins.create.fluids.client;

import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceRenderer;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.fluids.FluidSet;
import fr.iglee42.createcasing.fluids.FluidSets;
import net.createmod.catnip.render.CachedBuffers;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(value = PortableStorageInterfaceRenderer.class,remap = false)
public class PortableStorageInterfaceRendererMixin {


    @Inject(method = "getTopForState",at= @At(value = "HEAD"), cancellable = true)
    private static void encased$replaceTopModel(BlockState state, CallbackInfoReturnable<PartialModel> cir){
        Optional<FluidSet> set = FluidSets.getSets().stream().filter(FluidSet::doesGeneratePortableFluidInterface).filter(s->s.isInSet(state.getBlock())).findFirst();
        if (set.isEmpty()) return;
        cir.setReturnValue(set.get().getPortableFluidInterfaceTopModel());
    }


}
