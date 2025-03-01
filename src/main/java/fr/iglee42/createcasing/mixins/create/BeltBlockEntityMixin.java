package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import fr.iglee42.createcasing.registries.ModBlocks;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = BeltBlockEntity.class)
public abstract class BeltBlockEntityMixin {


    @Shadow public BeltBlockEntity.CasingType casing;
    @Unique public BeltBlockEntity.CasingType createCasing$newCasing;

    @Shadow public abstract BeltBlockEntity getControllerBE();

    @Inject(method = "setCasingType",at=@At("HEAD"),remap=false)
    private void encased$saveNewCasing(BeltBlockEntity.CasingType type, CallbackInfo ci){
        createCasing$newCasing = type;
    }

    @ModifyArg(method = "setCasingType",remap = false,at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;levelEvent(ILnet/minecraft/core/BlockPos;I)V"),index = 2)
    private int encased$changeParticle(int old) {
        if (createCasing$newCasing.equals(ModBlocks.COPPER_BELT_CASING)) return Block.getId(AllBlocks.COPPER_CASING.getDefaultState());
        if (createCasing$newCasing.equals(ModBlocks.RAILWAY_BELT_CASING)) return Block.getId(AllBlocks.RAILWAY_CASING.getDefaultState());
        if (createCasing$newCasing.equals(ModBlocks.INDUSTRIAL_IRON_BELT_CASING)) return Block.getId(AllBlocks.INDUSTRIAL_IRON_BLOCK.getDefaultState());
        if (createCasing$newCasing.equals(ModBlocks.CREATIVE_BELT_CASING)) return Block.getId(ModBlocks.CREATIVE_CASING.getDefaultState());
        return old;
    }


}
