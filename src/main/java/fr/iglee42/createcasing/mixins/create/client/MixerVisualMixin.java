package fr.iglee42.createcasing.mixins.create.client;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.kinetics.mixer.MixerVisual;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MixerVisual.class,remap = false)
public class MixerVisualMixin extends SingleAxisRotatingVisual<MechanicalMixerBlockEntity> {

    @Mutable
    @Shadow
    @Final
    private RotatingInstance mixerHead;

    public MixerVisualMixin(VisualizationContext context, MechanicalMixerBlockEntity blockEntity, float partialTick, Model model) {
        super(context, blockEntity, partialTick, model);
    }


    @Inject(method = "<init>",at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/kinetics/mixer/MixerVisual;animate(F)V"))
    private void encased$modifyHead(VisualizationContext context, MechanicalMixerBlockEntity blockEntity, float partialTick, CallbackInfo ci){
        mixerHead = instancerProvider().instancer(AllInstanceTypes.ROTATING, Models.partial(EncasedPartialModels.getMixerHead(blockEntity.getBlockState())))
                .createInstance();

        mixerHead.setRotationAxis(Direction.Axis.Y);
    }

}
