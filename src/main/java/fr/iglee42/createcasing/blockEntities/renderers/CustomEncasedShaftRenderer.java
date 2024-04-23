package fr.iglee42.createcasing.blockEntities.renderers;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import fr.iglee42.createcasing.blocks.customs.EncasedCustomShaftBlock;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class CustomEncasedShaftRenderer extends ShaftRenderer<KineticBlockEntity> {

    public CustomEncasedShaftRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected BlockState getRenderedBlockState(KineticBlockEntity be) {
        return ((EncasedCustomShaftBlock)be.getBlockState().getBlock()).getShaft().get().defaultBlockState().setValue(ShaftBlock.AXIS,getRotationAxisOf(be));
    }
}
