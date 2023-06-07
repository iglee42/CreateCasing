package fr.iglee42.createcasing.compatibility.createextendedcogs;

import com.jozufozu.flywheel.backend.Backend;
import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class EncasedCogWheelTileRendererCompat extends KineticTileEntityRenderer {
    public EncasedCogWheelTileRendererCompat(BlockEntityRendererProvider.Context context) {
        super(context);
    }



    protected void renderSafe(KineticTileEntity te, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        if (!Backend.canUseInstancing(te.getLevel())) {

            BlockState blockState = te.getBlockState();
            Block block = blockState.getBlock();
            if (block instanceof IRotate) {
                IRotate def = (IRotate)block;
                Direction[] var10 = Iterate.directionsInAxis(getRotationAxisOf(te));
                int var11 = var10.length;

                for (Direction d : var10) {
                    if (def.hasShaftTowards(te.getLevel(), te.getBlockPos(), blockState, d)) {
                        renderRotatingBuffer(te, CachedBufferer.partialFacing(AllBlockPartials.SHAFT_HALF, te.getBlockState(), d), ms, buffer.getBuffer(RenderType.solid()), light);
                    }
                }

            }
        }
    }
    /*protected SuperByteBuffer getRotatedModel(KineticTileEntity te, BlockState state) {
        PartialModel model = ICustomCogWheel.getPartialModelType(te.getBlockState());
        return CachedBufferer.partialFacingVertical(model == null ? AllBlockPartials.SHAFTLESS_COGWHEEL : model, state, Direction.fromAxisAndDirection((Direction.Axis)state.getValue(EncasedCogwheelBlock.AXIS), AxisDirection.POSITIVE));
    }*/

    @Override
    protected SuperByteBuffer getRotatedModel(KineticTileEntity te, BlockState state) {
        PartialModel model = ((CustomCogwheelCompat)state.getBlock()).getPartialModelType();
        return CachedBufferer.partialFacingVertical(model == null ? AllBlockPartials.SHAFTLESS_COGWHEEL : model, state, Direction.fromAxisAndDirection((Direction.Axis)state.getValue(EncasedCogwheelBlock.AXIS), AxisDirection.POSITIVE));
    }
}