package fr.iglee42.createcasing.blockEntities.visuals;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import fr.iglee42.createcasing.blocks.shafts.EncasedCustomShaftBlock;
import fr.iglee42.createcasing.registries.ModPartialModels;
import net.minecraft.core.registries.BuiltInRegistries;

public class CustomEncasedShaftVisual extends SingleAxisRotatingVisual<KineticBlockEntity> {


    public CustomEncasedShaftVisual(VisualizationContext context, KineticBlockEntity blockEntity, float partialTick) {
        super(context, blockEntity, partialTick,
                Models.partial(ModPartialModels.SHAFT_MODELS.get(BuiltInRegistries.BLOCK.getKey(((EncasedCustomShaftBlock)blockEntity.getBlockState().getBlock()).getShaft().get()).getPath().replace("_shaft",""))));
    }
}
