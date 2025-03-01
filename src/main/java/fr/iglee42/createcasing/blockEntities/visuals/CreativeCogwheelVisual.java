package fr.iglee42.createcasing.blockEntities.visuals;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityVisual;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import fr.iglee42.createcasing.blockEntities.CreativeCogwheelBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class CreativeCogwheelVisual extends KineticBlockEntityVisual<CreativeCogwheelBlockEntity> {


	protected final RotatingInstance rotatingModel;

	public CreativeCogwheelVisual(VisualizationContext modelManager, CreativeCogwheelBlockEntity blockEntity, float partialTick) {
		super(modelManager, blockEntity, partialTick);

		rotatingModel = instancerProvider().instancer(AllInstanceTypes.ROTATING, Models.partial(AllPartialModels.SHAFTLESS_COGWHEEL))
				.createInstance();

		rotatingModel.setup(blockEntity)
				.setPosition(getVisualPosition())
				.rotateToFace(rotationAxis())
				.setChanged();
	}


	@Override
	public void update(float pt) {
		rotatingModel.setup(blockEntity)
				.setChanged();
	}

	@Override
	public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
		consumer.accept(rotatingModel);
	}

	@Override
	public void updateLight(float v) {
		relight(rotatingModel);
	}

	@Override
	protected void _delete() {
		rotatingModel.delete();
	}

}
