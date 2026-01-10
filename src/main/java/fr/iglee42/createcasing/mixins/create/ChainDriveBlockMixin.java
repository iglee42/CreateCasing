package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.kinetics.chainDrive.ChainDriveBlock;
import fr.iglee42.createcasing.blocks.customs.CustomChainDriveBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ChainDriveBlock.class,remap = true)
public class ChainDriveBlockMixin {

    @Inject(method = "updateShape",at=@At("HEAD"),cancellable = true)
    private void encased$dontConnectToCustom(BlockState stateIn, Direction face, BlockState neighbour, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos, CallbackInfoReturnable<BlockState> cir){
        if (neighbour.getBlock() instanceof CustomChainDriveBlock) {
            cir.setReturnValue(stateIn);
            return;
        }
    }
}
