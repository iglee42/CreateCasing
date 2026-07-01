package fr.iglee42.createcasing.mixins.create.fluids;

import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FluidTransportBehaviour.class,remap = false)
public class FluidTransportBehaviourMixin {

    @Redirect(method = "getRenderedRimAttachment",at= @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z",ordinal = 1))
    private boolean encased$replaceRimOnAllPulley(BlockEntry instance, BlockState state){
        return state.getBlock() instanceof HosePulleyBlock;
    }

}
