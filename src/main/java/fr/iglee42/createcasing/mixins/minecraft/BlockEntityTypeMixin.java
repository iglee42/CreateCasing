package fr.iglee42.createcasing.mixins.minecraft;

import com.simibubi.create.AllBlockEntityTypes;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(value = BlockEntityType.class)
public class BlockEntityTypeMixin {


    @Shadow
    @Final
    private Set<Block> validBlocks;

    @Inject(method = "isValid",at = @At("HEAD"),cancellable = true,remap = false)
    private void isValid(BlockState state, CallbackInfoReturnable<Boolean> cir){
        if (!this.validBlocks.contains(state.getBlock()) && EncasedBlockEntities.ENCASED_SHAFT.isBound() &&( this.equals(EncasedBlockEntities.ENCASED_SHAFT.get()) ||
                this.equals(EncasedBlockEntities.ENCASED_COGWHEEL.get()) ||
                this.equals(EncasedBlockEntities.ENCASED_COGWHEEL_LARGE.get()) ||
                this.equals(EncasedBlockEntities.ENCASED_FLUID_PIPE.get()) ||
                this.equals(EncasedBlockEntities.CUSTOM_ENCASED_SHAFT.get()) ||
                this.equals(EncasedBlockEntities.ENCASED_CUSTOM_COGWHEEL.get()) ||
                this.equals(EncasedBlockEntities.ENCASED_CUSTOM_LARGE_COGWHEEL.get()) ||
                this.equals(EncasedBlockEntities.WOODEN_COGWHEELS.get()) ||
                this.equals(AllBlockEntityTypes.BRACKETED_KINETIC.get()) ||
                this.equals(EncasedBlockEntities.API_GEARBOX.get()) ||
                this.equals(EncasedBlockEntities.API_DEPOT.get()) ||
                this.equals(EncasedBlockEntities.API_MIXER.get()) ||
                this.equals(EncasedBlockEntities.API_PRESS.get()) ||
                this.equals(EncasedBlockEntities.API_COGWHEEL.get())))// ||
                //this.equals(AllBlockEntityTypes.FLUID_PIPE.get()) ||
                //this.equals(AllBlockEntityTypes.FLUID_TANK.get())))
            cir.setReturnValue(true);

    }

}
