package fr.iglee42.createcasing.blockEntities.renderers;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.Iterate;

import fr.iglee42.createcasing.blocks.customs.EncasedCustomCogwheelBlock;
import fr.iglee42.createcasing.registries.ModPartialModels;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class EncasedCustomCogRenderer extends KineticBlockEntityRenderer<SimpleKineticBlockEntity> {

	private boolean large;

	public static EncasedCustomCogRenderer small(BlockEntityRendererProvider.Context context) {
		return new EncasedCustomCogRenderer(context, false);
	}

	public static EncasedCustomCogRenderer large(BlockEntityRendererProvider.Context context) {
		return new EncasedCustomCogRenderer(context, true);
	}

	public EncasedCustomCogRenderer(BlockEntityRendererProvider.Context context, boolean large) {
		super(context);
		this.large = large;
	}

	@Override
	protected void renderSafe(SimpleKineticBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
		int light, int overlay) {
		super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
		if (Backend.canUseInstancing(be.getLevel()))
			return;

		BlockState blockState = be.getBlockState();
		Block block = blockState.getBlock();
		if (!(block instanceof IRotate))
			return;
		IRotate def = (IRotate) block;

		Axis axis = getRotationAxisOf(be);
		BlockPos pos = be.getBlockPos();
		float angle = large ? BracketedKineticBlockEntityRenderer.getAngleForLargeCogShaft(be, axis)
			: getAngleForTe(be, pos, axis);

		for (Direction d : Iterate.directionsInAxis(getRotationAxisOf(be))) {
			if (!def.hasShaftTowards(be.getLevel(), be.getBlockPos(), blockState, d))
				continue;
			SuperByteBuffer shaft = CachedBufferer.partialFacing(AllPartialModels.SHAFT_HALF, be.getBlockState(), d);
			kineticRotationTransform(shaft, be, axis, angle, light);
			shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
		}
	}

	@Override
	protected SuperByteBuffer getRotatedModel(SimpleKineticBlockEntity be, BlockState state) {
		return CachedBufferer.partialFacingVertical(
			large ? ModPartialModels.LARGE_COGS_MODELS.get(ForgeRegistries.BLOCKS.getKey(((EncasedCustomCogwheelBlock)state.getBlock()).getCogwheel().get()).getPath().replaceAll("_large_cogwheel","")) : ModPartialModels.COGS_MODELS.get(ForgeRegistries.BLOCKS.getKey(((EncasedCustomCogwheelBlock)state.getBlock()).getCogwheel().get()).getPath().replaceAll("_cogwheel","")), state,
			Direction.fromAxisAndDirection(state.getValue(EncasedCustomCogwheelBlock.AXIS), AxisDirection.POSITIVE));
	}

}