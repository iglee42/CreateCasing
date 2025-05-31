package fr.iglee42.createcasing.blockEntities.visuals;

import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visual.BlockEntityVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import fr.iglee42.createcasing.blockEntities.WoodenShaftBlockEntity;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import net.minecraft.core.registries.BuiltInRegistries;

public class WoodenShaftVisual {

    public static BlockEntityVisual<WoodenShaftBlockEntity> create(VisualizationContext context, WoodenShaftBlockEntity blockEntity, float partialTick) {
        Model model = Models.partial(EncasedPartialModels.SHAFT_MODELS.get(BuiltInRegistries.BLOCK.getKey(blockEntity.getBlockState().getBlock()).getPath().replaceAll("_shaft","")));
        return new SingleAxisRotatingVisual<>(context, blockEntity, partialTick, model);
    }
}
