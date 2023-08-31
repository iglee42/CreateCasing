package fr.iglee42.createcasing.blockEntities.instances;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.api.instance.DynamicInstance;
import com.jozufozu.flywheel.core.materials.oriented.OrientedData;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogInstance;
import com.simibubi.create.foundation.render.AllMaterialSpecs;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import fr.iglee42.createcasing.registries.ModPartialModels;
import fr.iglee42.createcasing.blockEntities.CustomMixerBlockEntity;
import net.minecraft.core.Direction;

public class CustomMixerInstance extends EncasedCogInstance implements DynamicInstance {

	private RotatingData mixerHead;
	private OrientedData mixerPole;

	private final CustomMixerBlockEntity mixer;

	public CustomMixerInstance(MaterialManager dispatcher, CustomMixerBlockEntity tile) {
		super(dispatcher, tile, false);
		this.mixer = tile;

		RotatingData mixerHead = null;


		OrientedData mixerPole = null;


		switch (tile.getBlockState().getBlock().getRegistryName().getPath().replace("_mixer","").toLowerCase()) {
			case "brass" -> {
				mixerHead = materialManager.defaultCutout()
						.material(AllMaterialSpecs.ROTATING).getModel(ModPartialModels.BRASS_MIXER_HEAD, blockState)
						.createInstance();
				mixerPole = getOrientedMaterial().getModel(ModPartialModels.BRASS_MIXER_POLE, blockState)
						.createInstance();
			}
            case "copper" -> {
				mixerHead = materialManager.defaultCutout()
						.material(AllMaterialSpecs.ROTATING).getModel(ModPartialModels.COPPER_MIXER_HEAD, blockState)
						.createInstance();
				mixerPole = getOrientedMaterial().getModel(ModPartialModels.COPPER_MIXER_POLE, blockState)
						.createInstance();
			}
            case "railway" -> {
                mixerHead = materialManager.defaultCutout()
                        .material(AllMaterialSpecs.ROTATING).getModel(ModPartialModels.RAILWAY_MIXER_HEAD, blockState)
                        .createInstance();
                mixerPole = getOrientedMaterial().getModel(ModPartialModels.RAILWAY_MIXER_POLE, blockState)
                        .createInstance();
            }
			case "industrial_iron" ->{
				mixerHead = materialManager.defaultCutout()
						.material(AllMaterialSpecs.ROTATING).getModel(ModPartialModels.INDUSTRIAL_IRON_MIXER_HEAD, blockState)
						.createInstance();
				mixerPole = getOrientedMaterial().getModel(ModPartialModels.INDUSTRIAL_IRON_MIXER_POLE, blockState)
						.createInstance();
			}
			default -> {
				mixerHead = materialManager.defaultCutout()
						.material(AllMaterialSpecs.ROTATING).getModel(AllPartialModels.MECHANICAL_MIXER_HEAD, blockState)
						.createInstance();
				mixerPole = getOrientedMaterial().getModel(AllPartialModels.MECHANICAL_MIXER_POLE, blockState)
						.createInstance();
			}
		}


		this.mixerHead = mixerHead;
		this.mixerPole = mixerPole;


		this.mixerHead.setRotationAxis(Direction.Axis.Y);


		float renderedHeadOffset = getRenderedHeadOffset();

		transformPole(renderedHeadOffset);
		transformHead(renderedHeadOffset);
	}

	@Override
	protected Instancer<RotatingData> getCogModel() {
		return materialManager.defaultSolid()
			.material(AllMaterialSpecs.ROTATING)
			.getModel(AllPartialModels.SHAFTLESS_COGWHEEL, blockEntity.getBlockState());
	}

	@Override
	public void beginFrame() {

		float renderedHeadOffset = getRenderedHeadOffset();

		/*if (mixerHead.skyLight == 0 || mixerPole.skyLight == 0) {
			remove();
			return;
		}*/

		transformPole(renderedHeadOffset);
		transformHead(renderedHeadOffset);
	}

	private void transformHead(float renderedHeadOffset) {
		float speed = mixer.getRenderedHeadRotationSpeed(AnimationTickHolder.getPartialTicks());

		mixerHead.setPosition(getInstancePosition())
				.nudge(0, -renderedHeadOffset, 0)
				.setRotationalSpeed(speed * 2);
	}

	private void transformPole(float renderedHeadOffset) {
		mixerPole.setPosition(getInstancePosition())
				.nudge(0, -renderedHeadOffset, 0);
	}

	private float getRenderedHeadOffset() {
		return mixer.getRenderedHeadOffset(AnimationTickHolder.getPartialTicks());
	}

	@Override
	public void updateLight() {
		super.updateLight();

		relight(pos.below(), mixerHead);
		relight(pos, mixerPole);
	}

	@Override
	public void remove() {
		super.remove();
		mixerHead.delete();
		mixerPole.delete();
	}
}
