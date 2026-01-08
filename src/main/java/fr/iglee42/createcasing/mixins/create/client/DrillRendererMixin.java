package fr.iglee42.createcasing.mixins.create.client;

import com.simibubi.create.content.kinetics.drill.DrillBlockEntity;
import com.simibubi.create.content.kinetics.drill.DrillRenderer;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DrillRenderer.class,remap = false)
public class DrillRendererMixin {

    @Inject(method = "getRotatedModel(Lcom/simibubi/create/content/kinetics/drill/DrillBlockEntity;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;",at=@At("HEAD"),cancellable = true)
    private void encased$modifyHeadModel(DrillBlockEntity be, BlockState state, CallbackInfoReturnable<SuperByteBuffer> cir){
        cir.setReturnValue(CachedBuffers.partialFacing(EncasedPartialModels.getDrillHead(state),state));
    }

    @Redirect(method = "renderInContraption",at= @At(value = "INVOKE", target = "Lnet/createmod/catnip/render/CachedBuffers;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;"))
    private static SuperByteBuffer encased$modifyHeadModelInContraption(PartialModel partial, BlockState referenceState){
        return CachedBuffers.partialFacing(EncasedPartialModels.getDrillHead(referenceState),referenceState);
    }

}
