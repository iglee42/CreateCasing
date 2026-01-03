package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.kinetics.deployer.DeployerBlock;
import com.simibubi.create.content.schematics.client.SchematicHandler;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = SchematicHandler.class,remap = false)
public class SchematicHandlerMixin {
    @Redirect(method = "onMouseInput",at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z",ordinal = 1))
    private boolean encased$disableForCustomDeployers(BlockEntry<?> instance, BlockState state){
        return state.getBlock() instanceof DeployerBlock;
    }
}
