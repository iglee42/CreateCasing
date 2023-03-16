package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.fluids.FluidPropagator;
import com.simibubi.create.content.contraptions.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.contraptions.fluids.PumpBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.EncasedPipeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = FluidTransportBehaviour.class,remap = false)
public abstract class FluidTransportBehaviourMixin {


    @Shadow
    public abstract boolean canHaveFlowToward(BlockState state,Direction direction);


    /**
     * @author Iglee42
     * @reason Adapt for all Encased Fluid Pipe
     */
    @Inject(method = "getRenderedRimAttachment",at = @At("HEAD"),cancellable = true)
    public void getRenderedRimAttachment(BlockAndTintGetter world, BlockPos pos, BlockState state, Direction direction, CallbackInfoReturnable<FluidTransportBehaviour.AttachmentTypes> cir) {
        if (!this.canHaveFlowToward(state, direction)) {
            cir.setReturnValue(FluidTransportBehaviour.AttachmentTypes.NONE);
        } else {
            BlockPos offsetPos = pos.relative(direction,1);
            BlockState facingState = world.getBlockState(offsetPos);
            if (facingState.getBlock() instanceof PumpBlock && facingState.getValue(PumpBlock.FACING) == direction.getOpposite()) {
                cir.setReturnValue(FluidTransportBehaviour.AttachmentTypes.NONE);
            } else if (facingState.getBlock() instanceof EncasedPipeBlock &&
                    (Boolean)facingState.getValue((Property) EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(direction.getOpposite()))) {
                cir.setReturnValue(FluidTransportBehaviour.AttachmentTypes.RIM);
            } else {
                cir.setReturnValue(FluidPropagator.hasFluidCapability(world, offsetPos, direction.getOpposite()) && !AllBlocks.HOSE_PULLEY.has(facingState) ? FluidTransportBehaviour.AttachmentTypes.DRAIN : FluidTransportBehaviour.AttachmentTypes.RIM);
            }
        }
    }


}