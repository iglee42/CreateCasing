package fr.iglee42.createcasing.blockEntities.visuals;

import java.util.function.Consumer;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityVisual;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;

import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.blocks.customs.EncasedCustomCogwheelBlock;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

public class EncasedCustomCogVisual extends KineticBlockEntityVisual<KineticBlockEntity> {

	private final boolean large;

	protected RotatingInstance rotatingModel;
	protected RotatingInstance rotatingTopShaft;
	protected RotatingInstance rotatingBottomShaft;

	public static EncasedCustomCogVisual small(VisualizationContext modelManager, KineticBlockEntity blockEntity, float partialTick) {
		return new EncasedCustomCogVisual(modelManager, blockEntity, false, partialTick, Models.partial(getCogModel(blockEntity,false)));
	}

	public static EncasedCustomCogVisual large(VisualizationContext modelManager, KineticBlockEntity blockEntity, float partialTick) {
		return new EncasedCustomCogVisual(modelManager, blockEntity, true, partialTick, Models.partial(getCogModel(blockEntity,true)));
	}

	public EncasedCustomCogVisual(VisualizationContext modelManager, KineticBlockEntity blockEntity, boolean large, float partialTick, Model model) {
		super(modelManager, blockEntity, partialTick);
		this.large = large;

		rotatingModel = instancerProvider().instancer(AllInstanceTypes.ROTATING, model)
				.createInstance();

		rotatingModel.setup(blockEntity)
				.setPosition(getVisualPosition())
				.rotateToFace(rotationAxis())
				.setChanged();

		RotatingInstance rotatingTopShaft = null;
		RotatingInstance rotatingBottomShaft = null;

		Block block = blockState.getBlock();
		if (block instanceof IRotate def) {
			for (Direction d : Iterate.directionsInAxis(rotationAxis())) {
				if (!def.hasShaftTowards(blockEntity.getLevel(), blockEntity.getBlockPos(), blockState, d))
					continue;
				RotatingInstance instance = instancerProvider().instancer(AllInstanceTypes.ROTATING, Models.partial(AllPartialModels.SHAFT_HALF))
						.createInstance();
				instance.setup(blockEntity)
						.setPosition(getVisualPosition())
						.rotateToFace(Direction.SOUTH, d)
						.setChanged();

				if (large) {
					instance.setRotationOffset(BracketedKineticBlockEntityRenderer.getShaftAngleOffset(rotationAxis(), pos));
				}

				if (d.getAxisDirection() == AxisDirection.POSITIVE) {
					rotatingTopShaft = instance;
				} else {
					rotatingBottomShaft = instance;
				}
			}
		}

		this.rotatingTopShaft = rotatingTopShaft;
		this.rotatingBottomShaft = rotatingBottomShaft;
	}


	@Override
	public void update(float pt) {
		rotatingModel.setup(blockEntity)
				.setChanged();
		if (rotatingTopShaft != null) rotatingTopShaft.setup(blockEntity)
				.setChanged();
		if (rotatingBottomShaft != null) rotatingBottomShaft.setup(blockEntity)
				.setChanged();
	}

	@Override
	public void updateLight(float partialTick) {
		relight(rotatingModel, rotatingTopShaft, rotatingBottomShaft);
	}

	@Override
	protected void _delete() {
		rotatingModel.delete();
		if (rotatingTopShaft != null) rotatingTopShaft.delete();
		if (rotatingBottomShaft != null) rotatingBottomShaft.delete();
	}

	@Override
	public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
		consumer.accept(rotatingModel);
		consumer.accept(rotatingTopShaft);
		consumer.accept(rotatingBottomShaft);
	}

	protected static PartialModel getCogModel(BlockEntity blockEntity, boolean large) {
		BlockState referenceState = blockEntity.getBlockState();
		Direction facing =
			Direction.fromAxisAndDirection(referenceState.getValue(BlockStateProperties.AXIS), AxisDirection.POSITIVE);
		PartialModel partial = large ? EncasedPartialModels.SHAFTLESS_LARGE_COGS_MODELS.get(BuiltInRegistries.BLOCK.getKey(((EncasedCustomCogwheelBlock)referenceState.getBlock()).getCogwheel().get()).getPath().replaceAll("_large_cogwheel","")) : EncasedPartialModels.SHAFTLESS_COGS_MODELS.get(BuiltInRegistries.BLOCK.getKey(((EncasedCustomCogwheelBlock)referenceState.getBlock()).getCogwheel().get()).getPath().replaceAll("_cogwheel",""));
		return partial;
	}

}