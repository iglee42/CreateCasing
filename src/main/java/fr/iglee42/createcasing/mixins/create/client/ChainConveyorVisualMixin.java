package fr.iglee42.createcasing.mixins.create.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorBlockEntity;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorVisual;
import com.simibubi.create.foundation.render.SpecialModels;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ChainConveyorVisual.class,remap = false)
public class ChainConveyorVisualMixin extends SingleAxisRotatingVisual<ChainConveyorBlockEntity> {

    public ChainConveyorVisualMixin(VisualizationContext context, ChainConveyorBlockEntity blockEntity, float partialTick, Model model) {
        super(context, blockEntity, partialTick, model);
    }

    @Redirect(method = "<init>",at= @At(value = "INVOKE", target = "Ldev/engine_room/flywheel/lib/model/Models;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;)Ldev/engine_room/flywheel/api/model/Model;",ordinal = 0))
    private static Model encased$replaceShaftModel(PartialModel partial, @Local(argsOnly = true) ChainConveyorBlockEntity blockEntity){
        return Models.partial(EncasedPartialModels.getChainConveyorShaft(blockEntity.getBlockState()));
    }

    @Redirect(method = "setupGuards",at= @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/render/SpecialModels;chunkDiffuse(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;)Ldev/engine_room/flywheel/api/model/Model;",ordinal = 0))
    private Model encased$replaceWheelModel(PartialModel partial){
        return SpecialModels.chunkDiffuse(EncasedPartialModels.getChainConveyorWheel(blockEntity.getBlockState()));
    }

    @Redirect(method = "setupGuards",at= @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/render/SpecialModels;chunkDiffuse(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;)Ldev/engine_room/flywheel/api/model/Model;",ordinal = 1))
    private Model encased$replaceGuardModel(PartialModel partial){
        return SpecialModels.chunkDiffuse(EncasedPartialModels.getChainConveyorGuard(blockEntity.getBlockState()));
    }

}
