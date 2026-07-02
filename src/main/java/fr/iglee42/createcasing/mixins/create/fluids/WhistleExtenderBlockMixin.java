package fr.iglee42.createcasing.mixins.create.fluids;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import com.simibubi.create.content.decoration.steamWhistle.WhistleExtenderBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.fluids.FluidSet;
import fr.iglee42.createcasing.fluids.FluidSets;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(value = WhistleExtenderBlock.class,remap = false)
public class WhistleExtenderBlockMixin {

    @WrapOperation(method = "useItemOn",at= @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;isIn(Lnet/minecraft/world/item/ItemStack;)Z"))
    private boolean encased$allowUseOfAllWhistles(BlockEntry<?> instance, ItemStack stack, Operation<Boolean> original){
        if (FluidSets.getSets().stream().map(FluidSet::getWhistleSupplier).filter(Objects::nonNull).anyMatch(entry->entry.isIn(stack))) return true;
        return original.call(instance,stack);
    }

    @WrapOperation(method = "canSurvive",at= @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean encased$allowSurviveAllWhistles(BlockEntry<?> instance, BlockState state, Operation<Boolean> original){
        if (state.getBlock() instanceof WhistleBlock) return true;
        return original.call(instance,state);
    }

    @WrapOperation(method = "hidesNeighborFace",at= @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean encased$hidesForAllWhistles(BlockEntry<?> instance, BlockState state, Operation<Boolean> original){
        if (state.getBlock() instanceof WhistleBlock) return true;
        return original.call(instance,state);
    }
}
