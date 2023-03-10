package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.contraptions.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.foundation.tileEntity.SmartTileEntity;
import com.simibubi.create.foundation.tileEntity.TileEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com.simibubi.create.content.contraptions.fluids.pipes.FluidPipeTileEntity$StandardPipeFluidTransportBehaviour", remap = false)
public class StandardPipeFluidTransportBehaviourMixin extends FluidTransportBehaviour{

    public StandardPipeFluidTransportBehaviourMixin(SmartTileEntity te) {
        super(te);
    }

    @Override
    public boolean canHaveFlowToward(BlockState state, Direction direction) {
        return (FluidPipeBlock.isPipe(state) || state.getBlock() instanceof EncasedPipeBlock) && (Boolean)state.getValue((Property)FluidPipeBlock.PROPERTY_BY_DIRECTION.get(direction));
    }

    /**
     * @author Iglee42
     * @reason Allow to use with all encased pipes
     */
    @Inject(method = "getRenderedRimAttachment",at = @At("HEAD"),cancellable = true)
    public void getRenderedRimAttachment(BlockAndTintGetter world, BlockPos pos, BlockState state, Direction direction, CallbackInfoReturnable<AttachmentTypes> cir) {
        FluidTransportBehaviour.AttachmentTypes attachment = super.getRenderedRimAttachment(world, pos, state, direction);
        BlockPos offsetPos = pos.relative(direction,1);
        BlockState otherState = world.getBlockState(offsetPos);
        if (attachment == FluidTransportBehaviour.AttachmentTypes.RIM && !FluidPipeBlock.isPipe(otherState) && !AllBlocks.MECHANICAL_PUMP.has(otherState) && !(otherState.getBlock() instanceof EncasedPipeBlock)) {
            FluidTransportBehaviour pipeBehaviour = TileEntityBehaviour.get(world, offsetPos, FluidTransportBehaviour.TYPE);
            if (pipeBehaviour != null && pipeBehaviour.canHaveFlowToward(otherState, direction.getOpposite())) {
                cir.setReturnValue(FluidTransportBehaviour.AttachmentTypes.CONNECTION);
            }
        }

        if (attachment == FluidTransportBehaviour.AttachmentTypes.RIM && !FluidPipeBlock.shouldDrawRim(world, pos, state, direction)) {
            cir.setReturnValue(FluidTransportBehaviour.AttachmentTypes.CONNECTION);
        } else {
            cir.setReturnValue(attachment == FluidTransportBehaviour.AttachmentTypes.NONE && (Boolean)state.getValue((Property)FluidPipeBlock.PROPERTY_BY_DIRECTION.get(direction)) ? FluidTransportBehaviour.AttachmentTypes.CONNECTION : attachment);
        }
    }
}
