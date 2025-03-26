package fr.iglee42.createcasing.blockEntities.renderers;


import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;

import fr.iglee42.createcasing.blockEntities.GlassShaftBlockEntity;
import fr.iglee42.createcasing.blocks.shafts.GlassShaftBlock;
import fr.iglee42.createcasing.registries.ModBlocks;
import fr.iglee42.createcasing.registries.ModPartialModels;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class GlassShaftRenderer extends KineticBlockEntityRenderer<GlassShaftBlockEntity> {


    public GlassShaftRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected BlockState getRenderedBlockState(GlassShaftBlockEntity be) {
        return ModBlocks.GLASS_SHAFT.getDefaultState().setValue(GlassShaftBlock.AXIS,getRotationAxisOf(be));
    }
}
