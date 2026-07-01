package fr.iglee42.createcasing.blockEntities.visuals;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.contraptions.pulley.HosePulleyVisual;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import dev.engine_room.flywheel.api.instance.Instancer;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.fluids.FluidSet;
import fr.iglee42.createcasing.fluids.FluidSets;
import net.createmod.catnip.render.CachedBuffers;

import java.util.Optional;

public class CustomHosePulleyVisual extends HosePulleyVisual {
    public CustomHosePulleyVisual(VisualizationContext dispatcher, HosePulleyBlockEntity blockEntity, float partialTick) {
        super(dispatcher, blockEntity, partialTick);
    }

    @Override
    protected Instancer<TransformedInstance> getMagnetModel() {
        Optional<FluidSet> set = FluidSets.getSets().stream().filter(FluidSet::doesGenerateHosePulley).filter(s->s.isInSet(blockState.getBlock())).findFirst();
        PartialModel model = set.map(FluidSet::getHosePulleyMagnetModel).orElse(AllPartialModels.HOSE_MAGNET);
        return instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(model));
    }

    @Override
    protected Instancer<TransformedInstance> getHalfMagnetModel() {
        Optional<FluidSet> set = FluidSets.getSets().stream().filter(FluidSet::doesGenerateHosePulley).filter(s->s.isInSet(blockState.getBlock())).findFirst();
        PartialModel model = set.map(FluidSet::getHosePulleyHalfMagnetModel).orElse(AllPartialModels.HOSE_HALF_MAGNET);
        return instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(model));
    }
}
