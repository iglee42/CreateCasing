package fr.iglee42.createcasing.blockEntities.visuals;

import com.simibubi.create.AllPartialModels;

import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.OrientedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.visual.SimpleDynamicVisual;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Consumer;

public class CustomMixerVisual extends SingleAxisRotatingVisual<MechanicalMixerBlockEntity> implements SimpleDynamicVisual {

	private RotatingInstance mixerHead;
	private OrientedInstance mixerPole;

	private final MechanicalMixerBlockEntity mixer;

	public CustomMixerVisual(VisualizationContext context, MechanicalMixerBlockEntity blockEntity, float partialTick) {
		super(context, blockEntity, partialTick, Models.partial(AllPartialModels.SHAFTLESS_COGWHEEL));
		this.mixer = blockEntity;

		RotatingInstance mixerHead;


		mixerPole = instancerProvider().instancer(InstanceTypes.ORIENTED, Models.partial(AllPartialModels.MECHANICAL_MIXER_POLE))
				.createInstance();


		switch (BuiltInRegistries.BLOCK.getKey(mixer.getBlockState().getBlock()).getPath().replace("_mixer","").toLowerCase()) {
			case "brass" -> {
				mixerHead = instancerProvider()
						.instancer(AllInstanceTypes.ROTATING,Models.partial(EncasedPartialModels.BRASS_MIXER_HEAD))
						.createInstance();
			}
            case "copper" -> {
				mixerHead = instancerProvider()
						.instancer(AllInstanceTypes.ROTATING,Models.partial(EncasedPartialModels.COPPER_MIXER_HEAD))
						.createInstance();
			}
            case "railway" -> {
				mixerHead = instancerProvider()
						.instancer(AllInstanceTypes.ROTATING,Models.partial(EncasedPartialModels.RAILWAY_MIXER_HEAD))
						.createInstance();
            }
			case "industrial_iron" ->{
				mixerHead = instancerProvider()
						.instancer(AllInstanceTypes.ROTATING,Models.partial(EncasedPartialModels.INDUSTRIAL_IRON_MIXER_HEAD))
						.createInstance();
			}
			case "weathered_iron" ->{
				mixerHead = instancerProvider()
						.instancer(AllInstanceTypes.ROTATING,Models.partial(EncasedPartialModels.WEATHERED_IRON_MIXER_HEAD))
						.createInstance();
			}
			case "creative" ->{
				mixerHead = instancerProvider()
						.instancer(AllInstanceTypes.ROTATING,Models.partial(EncasedPartialModels.CREATIVE_MIXER_HEAD))
						.createInstance();
			}
			case "refined_radiance" ->{
				mixerHead = instancerProvider()
						.instancer(AllInstanceTypes.ROTATING,Models.partial(EncasedPartialModels.REFINED_RADIANCE_MIXER_HEAD))
						.createInstance();
			}
			case "shadow_steel" ->{
				mixerHead = instancerProvider()
						.instancer(AllInstanceTypes.ROTATING,Models.partial(EncasedPartialModels.SHADOW_STEEL_MIXER_HEAD))
						.createInstance();
			}
			default -> {
				mixerHead = instancerProvider()
						.instancer(AllInstanceTypes.ROTATING,Models.partial(AllPartialModels.MECHANICAL_MIXER_HEAD))
						.createInstance();
			}
		}


		this.mixerHead = mixerHead;
		this.mixerHead.setRotationAxis(Direction.Axis.Y);

		animate(partialTick);
	}


	private void animate(float pt) {
		float renderedHeadOffset = mixer.getRenderedHeadOffset(pt);

		transformPole(renderedHeadOffset);
		transformHead(renderedHeadOffset, pt);
	}

	private void transformHead(float renderedHeadOffset, float pt) {
		float speed = mixer.getRenderedHeadRotationSpeed(pt);

		mixerHead.setPosition(getVisualPosition())
				.nudge(0, -renderedHeadOffset, 0)
				.setRotationalSpeed(speed * 2 * RotatingInstance.SPEED_MULTIPLIER)
				.setChanged();
	}

	private void transformPole(float renderedHeadOffset) {
		mixerPole.position(getVisualPosition())
				.translatePosition(0, -renderedHeadOffset, 0)
				.setChanged();
	}

	@Override
	public void updateLight(float partialTick) {
		super.updateLight(partialTick);

		relight(pos.below(), mixerHead);
		relight(mixerPole);
	}

	@Override
	protected void _delete() {
		super._delete();
		mixerHead.delete();
		mixerPole.delete();
	}

	@Override
	public void collectCrumblingInstances(Consumer<Instance> consumer) {
		super.collectCrumblingInstances(consumer);
		consumer.accept(mixerHead);
		consumer.accept(mixerPole);
	}

	@Override
	public void beginFrame(Context context) {
		animate(context.partialTick());
	}
}
