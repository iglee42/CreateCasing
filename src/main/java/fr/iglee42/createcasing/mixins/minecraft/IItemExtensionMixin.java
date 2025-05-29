package fr.iglee42.createcasing.mixins.minecraft;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = IItemExtension.class,remap = false)
public interface IItemExtensionMixin {


    @Shadow
    Item self();

    /**
     * @author iglee42
     * @reason Allow shaft to used on the configurable gearbox
     */
    @Overwrite
    default boolean doesSneakBypassUse(ItemStack stack, LevelReader level, BlockPos pos, Player player){
        if (self() != null && (AllBlocks.SHAFT.is(self()) || AllItems.WRENCH.is(self()))) return true;
        return false;
    }

}
