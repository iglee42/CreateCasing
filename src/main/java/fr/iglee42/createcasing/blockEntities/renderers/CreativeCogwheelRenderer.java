package fr.iglee42.createcasing.blockEntities.renderers;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;

import fr.iglee42.createcasing.blockEntities.CreativeCogwheelBlockEntity;
import fr.iglee42.createcasing.blocks.CreativeCogwheelBlock;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CreativeCogwheelRenderer extends KineticBlockEntityRenderer<CreativeCogwheelBlockEntity> {



	public CreativeCogwheelRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected void renderSafe(CreativeCogwheelBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
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
		float angle = getAngleForTe(be, pos, axis);

	}

	@Override
	protected SuperByteBuffer getRotatedModel(CreativeCogwheelBlockEntity be, BlockState state) {
		return CachedBufferer.partialFacingVertical(AllPartialModels.SHAFTLESS_COGWHEEL, state,
			Direction.fromAxisAndDirection(state.getValue(CreativeCogwheelBlock.AXIS), AxisDirection.POSITIVE));
	}

}
