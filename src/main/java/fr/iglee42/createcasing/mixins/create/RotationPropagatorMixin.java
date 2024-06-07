package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.chainDrive.ChainDriveBlock;
import fr.iglee42.createcasing.blocks.customs.CustomChainDriveBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = RotationPropagator.class,remap = false)
public class RotationPropagatorMixin {


    @Inject(method = "getRotationSpeedModifier",at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/kinetics/RotationPropagator;isLargeToLargeGear(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)Z",shift = At.Shift.BEFORE),cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void inject(KineticBlockEntity from, KineticBlockEntity to, CallbackInfoReturnable<Float> cir, BlockState stateFrom, BlockState stateTo, Block fromBlock, Block toBlock, IRotate definitionFrom, IRotate definitionTo, BlockPos diff){
        //Injection from Create Encased
        final Direction direction = Direction.getNearest(diff.getX(), diff.getY(), diff.getZ());
        createCasing$rotationSpeedModifier(from,to,stateFrom,stateTo,fromBlock,toBlock,direction,cir);
    }

    @Unique
    private static void createCasing$rotationSpeedModifier(KineticBlockEntity from,KineticBlockEntity to,BlockState stateFrom,BlockState stateTo,Block fromBlock,Block toBlock,Direction direction, CallbackInfoReturnable<Float> cir) {
        if (fromBlock instanceof CustomChainDriveBlock && toBlock instanceof CustomChainDriveBlock) {
            boolean connected = CustomChainDriveBlock.areBlocksConnected(stateFrom, stateTo, direction);
            cir.setReturnValue(connected ? CustomChainDriveBlock.getRotationSpeedModifier(from, to) : 0);
        }

    }


}
