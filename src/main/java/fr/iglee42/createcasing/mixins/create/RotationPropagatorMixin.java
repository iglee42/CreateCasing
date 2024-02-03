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

@Mixin(value = RotationPropagator.class,remap = false)
public class RotationPropagatorMixin {


    @Inject(method = "getRotationSpeedModifier",at = @At("HEAD"),cancellable = true)
    private static void inject(KineticBlockEntity from, KineticBlockEntity _to, CallbackInfoReturnable<Float> cir){
        createCasing$rotationSpeedModifier(from,_to,cir);
    }

    @Unique
    private static void createCasing$rotationSpeedModifier(KineticBlockEntity from, KineticBlockEntity to, CallbackInfoReturnable<Float> cir) {
        final BlockState stateFrom = from.getBlockState();
        final BlockState stateTo = to.getBlockState();

        Block fromBlock = stateFrom.getBlock();
        Block toBlock = stateTo.getBlock();

        final BlockPos diff = to.getBlockPos()
                .subtract(from.getBlockPos());
        final Direction direction = Direction.getNearest(diff.getX(), diff.getY(), diff.getZ());
        if (fromBlock instanceof CustomChainDriveBlock && toBlock instanceof CustomChainDriveBlock) {
            boolean connected = CustomChainDriveBlock.areBlocksConnected(stateFrom, stateTo, direction);
            cir.setReturnValue(connected ? CustomChainDriveBlock.getRotationSpeedModifier(from, to) : 0);
        }

    }


}
