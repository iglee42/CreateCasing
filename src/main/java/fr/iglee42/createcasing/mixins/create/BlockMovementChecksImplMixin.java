package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceBlock;
import com.simibubi.create.content.contraptions.actors.roller.RollerBlock;
import com.simibubi.create.content.kinetics.drill.DrillBlock;
import com.simibubi.create.content.kinetics.saw.SawBlock;
import com.simibubi.create.impl.contraption.BlockMovementChecksImpl;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockMovementChecksImpl.class,remap = false)
public class BlockMovementChecksImplMixin {

    @Inject(method = "isNotSupportiveFallback",at=@At("HEAD"),cancellable = true)
    private static void encased$updateFallbackForCustomEncasedBlocks(BlockState state, Direction facing, CallbackInfoReturnable<Boolean> cir){
        if(state.getBlock() instanceof PortableStorageInterfaceBlock){
            cir.setReturnValue(true);
            return;
        }

        if(state.getBlock() instanceof SawBlock){
            cir.setReturnValue(true);
            return;
        }

        if(state.getBlock() instanceof RollerBlock){
            cir.setReturnValue(true);
            return;
        }

        if(state.getBlock() instanceof DrillBlock){
            cir.setReturnValue(true);
            return;
        }
    }
}
