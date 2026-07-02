package fr.iglee42.createcasing.mixins.create.fluids.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.fluids.spout.SpoutRenderer;
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

@Mixin(value = SpoutRenderer.class,remap = false)
public class SpoutRendererMixin {

    @WrapOperation(method = "renderSafe(Lcom/simibubi/create/content/fluids/spout/SpoutBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",at= @At(value = "INVOKE", target = "Lnet/createmod/catnip/render/CachedBuffers;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;"))
    private SuperByteBuffer encased$customizeSpoutBottom(PartialModel partial, BlockState referenceState, Operation<SuperByteBuffer> original){
        if (!partial.equals(AllPartialModels.SPOUT_BOTTOM)) return CachedBuffers.partial(partial,referenceState);
        Optional<FluidSet> set = FluidSets.getSets().stream().filter(FluidSet::doesGenerateSpout).filter(s->s.isInSet(referenceState.getBlock())).findFirst();
        if (set.isPresent()) return CachedBuffers.partial(set.get().getSpoutBottomModel(), referenceState);
        return original.call(partial, referenceState);
    }

}
