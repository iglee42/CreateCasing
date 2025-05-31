package fr.iglee42.createcasing.blockEntities.renderers;


import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import fr.iglee42.createcasing.blockEntities.GlassShaftBlockEntity;
import fr.iglee42.createcasing.blocks.shafts.GlassShaftBlock;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class GlassShaftRenderer extends KineticBlockEntityRenderer<GlassShaftBlockEntity> {


    public GlassShaftRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected BlockState getRenderedBlockState(GlassShaftBlockEntity be) {
        return EncasedBlocks.GLASS_SHAFT.getDefaultState().setValue(GlassShaftBlock.AXIS,getRotationAxisOf(be));
    }
}
