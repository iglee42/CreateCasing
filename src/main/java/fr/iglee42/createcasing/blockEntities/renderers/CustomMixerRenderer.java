package fr.iglee42.createcasing.blockEntities.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import fr.iglee42.createcasing.blockEntities.CustomMixerBlockEntity;
import fr.iglee42.createcasing.registries.ModPartialModels;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.state.BlockState;

public class CustomMixerRenderer extends KineticBlockEntityRenderer<CustomMixerBlockEntity> {

	public CustomMixerRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public boolean shouldRenderOffScreen(CustomMixerBlockEntity be) {
		return true;
	}

	@Override
	protected void renderSafe(CustomMixerBlockEntity mixer, float partialTicks, PoseStack ms, MultiBufferSource buffer,
		int light, int overlay) {

		//if (light == 0) return;
		if (VisualizationManager.supportsVisualization(mixer.getLevel())) return;

		BlockState blockState = mixer.getBlockState();

		VertexConsumer vb = buffer.getBuffer(RenderType.solid());

		SuperByteBuffer superBuffer = CachedBuffers.partial(AllPartialModels.SHAFTLESS_COGWHEEL, blockState);
		standardKineticRotationTransform(superBuffer, mixer, light).renderInto(ms, vb);

		float renderedHeadOffset = mixer.getRenderedHeadOffset(partialTicks);
		float speed = mixer.getRenderedHeadRotationSpeed(partialTicks);
		float time = AnimationTickHolder.getRenderTime(mixer.getLevel());
		float angle = ((time * speed * 6 / 10f) % 360) / 180 * (float) Math.PI;

		SuperByteBuffer poleRender = CachedBuffers.partial(AllPartialModels.MECHANICAL_MIXER_POLE, blockState);
		SuperByteBuffer headRender = CachedBuffers.partial(AllPartialModels.MECHANICAL_MIXER_HEAD, blockState);
		SuperByteBuffer oldHeadRender = CachedBuffers.partial(AllPartialModels.MECHANICAL_MIXER_HEAD, blockState);
		switch (BuiltInRegistries.BLOCK.getKey(mixer.getBlockState().getBlock()).getPath().replace("_mixer","").toLowerCase()) {
			case "brass" -> {
				headRender = CachedBuffers.partial(ModPartialModels.BRASS_MIXER_HEAD, blockState);
			}
			case "copper" -> {
				headRender = CachedBuffers.partial(ModPartialModels.COPPER_MIXER_HEAD, blockState);
			}
			case "railway" -> {
				headRender = CachedBuffers.partial(ModPartialModels.RAILWAY_MIXER_HEAD, blockState);
			}
			case "industrial_iron" -> {
				headRender = CachedBuffers.partial(ModPartialModels.INDUSTRIAL_IRON_MIXER_HEAD, blockState);
			}
			default -> {
				headRender = CachedBuffers.partial(AllPartialModels.MECHANICAL_MIXER_HEAD, blockState);
			}
		}

		if (headRender == oldHeadRender) return;

		poleRender.translate(0, -renderedHeadOffset, 0)
				.light(light)
				.renderInto(ms, vb);
		VertexConsumer vbCutout = buffer.getBuffer(RenderType.cutoutMipped());

		headRender.rotateCentered(angle,Direction.UP)
				.translate(0, -renderedHeadOffset, 0)
				.light(light)
				.renderInto(ms, vbCutout);
	}

}
