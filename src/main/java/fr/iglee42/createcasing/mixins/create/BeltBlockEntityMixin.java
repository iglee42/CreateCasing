package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


@Mixin(value = BeltBlockEntity.class)
public abstract class BeltBlockEntityMixin {


    @Shadow(remap = false) public BeltBlockEntity.CasingType casing;
    @Unique public BeltBlockEntity.CasingType createCasing$newCasing;

    @Shadow(remap = false) public abstract BeltBlockEntity getControllerBE();

    @Inject(method = "setCasingType",at=@At("HEAD"),remap=false)
    private void encased$saveNewCasing(BeltBlockEntity.CasingType type, CallbackInfo ci){
        createCasing$newCasing = type;
    }

    @ModifyArg(method = "setCasingType",remap = false,at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;levelEvent(ILnet/minecraft/core/BlockPos;I)V"),index = 2)
    private int encased$changeParticle(int old) {
        AtomicInteger returnValue = new AtomicInteger(old);
        CasingSets.getSets().stream().filter(CasingSet::doesGenerateBelt)
                .filter(set->set.getCasing() != null)
                .filter(set-> Objects.equals(set.getBeltCasingType(), createCasing$newCasing))
                .findFirst()
                .ifPresent(set-> returnValue.set(Block.getId(set.getCasing().defaultBlockState())));
        return returnValue.get();
    }


}
