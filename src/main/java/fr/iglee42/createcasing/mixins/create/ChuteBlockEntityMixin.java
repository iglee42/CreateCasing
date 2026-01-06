package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.kinetics.fan.EncasedFanBlock;
import com.simibubi.create.content.logistics.chute.ChuteBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ChuteBlockEntity.class,remap = false)
public class ChuteBlockEntityMixin {

    @Redirect(method = "calculatePush",at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean encased$pushForCustomFans(BlockEntry<?> instance, BlockState state){
        return state.getBlock() instanceof EncasedFanBlock;
    }

    @Redirect(method = "calculatePull",at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean encased$pullForCustomFans(BlockEntry<?> instance, BlockState state){
        return state.getBlock() instanceof EncasedFanBlock;
    }
}
