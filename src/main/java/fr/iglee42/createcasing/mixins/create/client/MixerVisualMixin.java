package fr.iglee42.createcasing.mixins.create.client;

import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.kinetics.mixer.MixerVisual;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = MixerVisual.class,remap = false)
public abstract class MixerVisualMixin extends SingleAxisRotatingVisual<MechanicalMixerBlockEntity> {

    public MixerVisualMixin(VisualizationContext context, MechanicalMixerBlockEntity blockEntity, float partialTick, Model model) {
        super(context, blockEntity, partialTick, model);
    }

    @Redirect(method = "<init>",at= @At(value = "INVOKE", target = "Ldev/engine_room/flywheel/lib/model/Models;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;)Ldev/engine_room/flywheel/api/model/Model;",ordinal = 1))
    private Model encased$replaceHeadModel(PartialModel partial){
        return Models.partial(EncasedPartialModels.getMixerHead(blockEntity.getBlockState()));
    }

}
