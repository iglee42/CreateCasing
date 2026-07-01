package fr.iglee42.createcasing.mixins.create.fluids.client;

import com.simibubi.create.content.contraptions.pulley.AbstractPulleyRenderer;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.fluids.FluidSet;
import fr.iglee42.createcasing.fluids.FluidSets;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(value = AbstractPulleyRenderer.class,remap = false)
public class AbstractPulleyRendererMixin {

    @Redirect(method = "renderSafe(Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",at= @At(value = "INVOKE", target = "Lnet/createmod/catnip/render/CachedBuffers;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;",ordinal = 0))
    private SuperByteBuffer encased$replaceMagnetModel(PartialModel partial, BlockState referenceState){
        Optional<FluidSet> set = FluidSets.getSets().stream().filter(FluidSet::doesGenerateHosePulley).filter(s->s.isInSet(referenceState.getBlock())).findFirst();
        return set.map(fluidSet -> CachedBuffers.partial(fluidSet.getHosePulleyHalfMagnetModel(), referenceState)).orElseGet(() -> CachedBuffers.partial(partial, referenceState));
    }

}
