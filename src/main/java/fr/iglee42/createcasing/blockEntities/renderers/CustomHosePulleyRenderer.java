package fr.iglee42.createcasing.blockEntities.renderers;


import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.contraptions.pulley.AbstractPulleyRenderer;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.fluids.FluidSet;
import fr.iglee42.createcasing.fluids.FluidSets;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction.Axis;

import java.util.Optional;

public class CustomHosePulleyRenderer extends AbstractPulleyRenderer<HosePulleyBlockEntity> {

    public CustomHosePulleyRenderer(BlockEntityRendererProvider.Context context) {
        super(context, AllPartialModels.HOSE_HALF, AllPartialModels.HOSE_HALF_MAGNET);
    }

    @Override
    protected Axis getShaftAxis(HosePulleyBlockEntity be) {
        return be.getBlockState()
                .getValue(HosePulleyBlock.HORIZONTAL_FACING)
                .getClockWise()
                .getAxis();
    }

    @Override
    protected PartialModel getCoil() {
        return AllPartialModels.HOSE_COIL;
    }

    @Override
    protected SuperByteBuffer renderRope(HosePulleyBlockEntity be) {
        return CachedBuffers.partial(AllPartialModels.HOSE, be.getBlockState());
    }

    @Override
    protected SuperByteBuffer renderMagnet(HosePulleyBlockEntity be) {
        Optional<FluidSet> set = FluidSets.getSets().stream().filter(FluidSet::doesGenerateHosePulley).filter(s->s.isInSet(be.getBlockState().getBlock())).findFirst();
        PartialModel model = set.map(FluidSet::getHosePulleyMagnetModel).orElse(AllPartialModels.HOSE_MAGNET);
        return CachedBuffers.partial(model, be.getBlockState());
    }

    @Override
    protected float getOffset(HosePulleyBlockEntity be, float partialTicks) {
        return be.getInterpolatedOffset(partialTicks);
    }

    @Override
    protected SpriteShiftEntry getCoilShift() {
        return AllSpriteShifts.HOSE_PULLEY_COIL;
    }

    @Override
    protected boolean isRunning(HosePulleyBlockEntity be) {
        return true;
    }

}