package fr.iglee42.createcasing.mixins.create.client;

import com.simibubi.create.content.contraptions.actors.roller.RollerRenderer;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorBlockEntity;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorRenderer;
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

@Mixin(value = RollerRenderer.class,remap = false)
public class RollerRendererMixin {

    @Redirect(method = "renderSafe(Lcom/simibubi/create/content/contraptions/actors/roller/RollerBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",at= @At(value = "INVOKE", target = "Lnet/createmod/catnip/render/CachedBuffers;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;",ordinal = 1))
    private SuperByteBuffer encased$replaceWheelModel(PartialModel partial, BlockState referenceState){
        return CachedBuffers.partial(EncasedPartialModels.getRollerFrame(referenceState),referenceState);
    }

    @Redirect(method = "renderInContraption",at= @At(value = "INVOKE", target = "Lnet/createmod/catnip/render/CachedBuffers;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;",ordinal = 1))
    private static SuperByteBuffer encased$replaceGuardModel(PartialModel partial, BlockState referenceState){
        return CachedBuffers.partial(EncasedPartialModels.getRollerFrame(referenceState),referenceState);
    }

}
