package fr.iglee42.createcasing.mixins.create.fluids;

import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import com.simibubi.create.content.fluids.tank.BoilerData;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = BoilerData.class, remap = false)
public class BoilerDataMixin {

    @Redirect(method = "evaluate",at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z",ordinal = 0))
    private boolean encased$allowAllEngine(BlockEntry<?> instance, BlockState state){
        return state.getBlock() instanceof SteamEngineBlock;
    }

    @Redirect(method = "evaluate",at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z",ordinal = 1))
    private boolean encased$allowAllWhistle(BlockEntry<?> instance, BlockState state){
        return state.getBlock() instanceof WhistleBlock;
    }

    @Redirect(method = "checkPipeOrganAdvancement",at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z",ordinal = 0))
    private boolean encased$allowAllWhistleForChecks(BlockEntry<?> instance, BlockState state){
        return state.getBlock() instanceof WhistleBlock;
    }
}
