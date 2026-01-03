package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import fr.iglee42.createcasing.blocks.customs.CustomDeployerBlock;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.simibubi.create.content.kinetics.base.DirectionalKineticBlock.FACING;

@Mixin(value = DeployerBlockEntity.class,remap = false)
public class DeployerBlockEntityMixin {
    @Inject(method = "getMovementVector",at = @At("HEAD"), cancellable = true)
    private void encased$getMovementVectorForCustomDeployers(CallbackInfoReturnable<Vec3> cir){
        DeployerBlockEntity entity = (DeployerBlockEntity)(Object)this;
        if (entity.getBlockState().getBlock() instanceof CustomDeployerBlock){
            cir.setReturnValue(Vec3.atLowerCornerOf(entity.getBlockState().getValue(FACING)
                    .getNormal()));
            return;
        }
    }
}
