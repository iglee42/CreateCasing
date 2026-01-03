package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceBlock;
import com.simibubi.create.content.logistics.packager.PackagerBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = PackagerBlock.class,remap = false)
public class PackagerBlockMixin {

    @Redirect(method = "getStateForPlacement",at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean encased$customPortableStorageInterface(BlockEntry<?> instance, BlockState state){
        return state.getBlock() instanceof PortableStorageInterfaceBlock;
    }
}
