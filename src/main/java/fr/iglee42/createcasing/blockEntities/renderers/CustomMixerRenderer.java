package fr.iglee42.createcasing.blockEntities.renderers;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import fr.iglee42.createcasing.registries.ModPartialModels;
import fr.iglee42.createcasing.blockEntities.CustomMixerBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CustomMixerRenderer extends KineticBlockEntityRenderer {

	public CustomMixerRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public boolean shouldRenderOffScreen(BlockEntity be) {
		return true;
	}

	@Override
	protected void renderSafe(KineticBlockEntity te, float partialTicks, PoseStack ms, MultiBufferSource buffer,
		int light, int overlay) {

		if (light == 0) return;
		if (Backend.canUseInstancing(te.getLevel())) return;

		BlockState blockState = te.getBlockState();
		CustomMixerBlockEntity mixer = (CustomMixerBlockEntity) te;

		VertexConsumer vb = buffer.getBuffer(RenderType.solid());

		SuperByteBuffer superBuffer = CachedBufferer.partial(AllPartialModels.SHAFTLESS_COGWHEEL, blockState);
		standardKineticRotationTransform(superBuffer, te, light).renderInto(ms, vb);

		float renderedHeadOffset = mixer.getRenderedHeadOffset(partialTicks);
		float speed = mixer.getRenderedHeadRotationSpeed(partialTicks);
		float time = AnimationTickHolder.getRenderTime(te.getLevel());
		float angle = ((time * speed * 6 / 10f) % 360) / 180 * (float) Math.PI;

		SuperByteBuffer poleRender = CachedBufferer.partial(AllPartialModels.MECHANICAL_MIXER_POLE, blockState);
		SuperByteBuffer oldPoleRender = CachedBufferer.partial(AllPartialModels.MECHANICAL_MIXER_POLE, blockState);
		SuperByteBuffer headRender = CachedBufferer.partial(AllPartialModels.MECHANICAL_MIXER_HEAD, blockState);
		SuperByteBuffer oldHeadRender = CachedBufferer.partial(AllPartialModels.MECHANICAL_MIXER_HEAD, blockState);
		switch (te.getBlockState().getBlock().getRegistryName().getPath().replace("_mixer","").toLowerCase()) {
			case "brass" -> {
				poleRender = CachedBufferer.partial(ModPartialModels.BRASS_MIXER_POLE, blockState);
				headRender = CachedBufferer.partial(ModPartialModels.BRASS_MIXER_HEAD, blockState);
			}
			case "copper" -> {
				poleRender = CachedBufferer.partial(ModPartialModels.COPPER_MIXER_POLE, blockState);
				headRender = CachedBufferer.partial(ModPartialModels.COPPER_MIXER_HEAD, blockState);
			}
			case "railway" -> {
				poleRender = CachedBufferer.partial(ModPartialModels.RAILWAY_MIXER_POLE, blockState);
				headRender = CachedBufferer.partial(ModPartialModels.RAILWAY_MIXER_HEAD, blockState);
			}
		}

		if (poleRender == oldPoleRender ||  headRender == oldHeadRender) return;

		poleRender.translate(0, -renderedHeadOffset, 0)
				.light(light)
				.renderInto(ms, vb);
		VertexConsumer vbCutout = buffer.getBuffer(RenderType.cutoutMipped());

		headRender.rotateCentered(Direction.UP, angle)
				.translate(0, -renderedHeadOffset, 0)
				.light(light)
				.renderInto(ms, vbCutout);
	}

}
