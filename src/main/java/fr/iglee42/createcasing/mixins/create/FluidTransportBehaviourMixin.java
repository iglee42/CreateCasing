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
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = FluidTransportBehaviour.class, remap = false)
public abstract class FluidTransportBehaviourMixin {


    @Shadow
    public abstract boolean canHaveFlowToward(BlockState state,Direction direction);


    /**
     * @author Iglee42
     * @reason Adapt for all Encased Fluid Pipe
     */
    @Overwrite
    public FluidTransportBehaviour.AttachmentTypes getRenderedRimAttachment(BlockAndTintGetter world, BlockPos pos, BlockState state, Direction direction) {
        if (!this.canHaveFlowToward(state, direction)) {
            return FluidTransportBehaviour.AttachmentTypes.NONE;
        } else {
            BlockPos offsetPos = pos.relative(direction);
            BlockState facingState = world.getBlockState(offsetPos);
            if (facingState.getBlock() instanceof PumpBlock && facingState.getValue(PumpBlock.FACING) == direction.getOpposite()) {
                return FluidTransportBehaviour.AttachmentTypes.NONE;
            } else if (facingState.getBlock() instanceof EncasedPipeBlock &&
                    (Boolean)facingState.getValue((Property) EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(direction.getOpposite()))) {
                return FluidTransportBehaviour.AttachmentTypes.RIM;
            } else {
                return FluidPropagator.hasFluidCapability(world, offsetPos, direction.getOpposite()) && !AllBlocks.HOSE_PULLEY.has(facingState) ? FluidTransportBehaviour.AttachmentTypes.DRAIN : FluidTransportBehaviour.AttachmentTypes.RIM;
            }
        }
    }


}
