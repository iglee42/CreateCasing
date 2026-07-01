package fr.iglee42.createcasing.mixins.create.fluids;

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

    @Redirect(method = "useItemOn",at= @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;isIn(Lnet/minecraft/world/item/ItemStack;)Z"))
    private boolean encased$allowUseOfAllWhistles(BlockEntry<?> instance, ItemStack stack){
        return FluidSets.getSets().stream().map(FluidSet::getWhistleSupplier).filter(Objects::nonNull).anyMatch(entry->entry.isIn(stack));
    }

    @Redirect(method = "canSurvive",at= @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean encased$allowSurviveAllWhistles(BlockEntry<?> instance, BlockState state){
        return state.getBlock() instanceof WhistleBlock;
    }

    @Redirect(method = "hidesNeighborFace",at= @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean encased$hidesForAllWhistles(BlockEntry<?> instance, BlockState state){
        return state.getBlock() instanceof WhistleBlock;
    }
}
