package fr.iglee42.createcasing.mixins.create.fluids.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.fluids.tank.FluidTankRenderer;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.sets.fluids.FluidSet;
import fr.iglee42.createcasing.sets.fluids.FluidSets;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(value = FluidTankRenderer.class,remap = false)
public class FluidTankRendererMixin {


    @WrapOperation(method = "renderAsBoiler",at= @At(value = "INVOKE", target = "Lnet/createmod/catnip/render/CachedBuffers;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;",ordinal = 0))
    private SuperByteBuffer encased$replaceGauge(PartialModel partial, BlockState referenceState, Operation<SuperByteBuffer> original){
        Optional<FluidSet> set = FluidSets.getSets().stream().filter(FluidSet::doesGenerateSteamEngine).filter(s->s.isInSet(referenceState.getBlock())).findFirst();
        if (set.isPresent()) return CachedBuffers.partial(set.get().getEngineGaugeModel(),referenceState);
        return original.call(partial,referenceState);
    }

    @WrapOperation(method = "renderAsBoiler",at= @At(value = "INVOKE", target = "Lnet/createmod/catnip/render/CachedBuffers;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;",ordinal = 1))
    private SuperByteBuffer encased$replaceGaugeDial(PartialModel partial, BlockState referenceState, Operation<SuperByteBuffer> original){
        Optional<FluidSet> set = FluidSets.getSets().stream().filter(FluidSet::doesGenerateSteamEngine).filter(s->s.isInSet(referenceState.getBlock())).findFirst();
        if (set.isPresent()) return CachedBuffers.partial(set.get().getEngineGaugeDialModel(),referenceState);
        return original.call(partial,referenceState);
    }

}
