package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static fr.iglee42.createcasing.CreateCasing.MODID;

@Mixin(CogWheelBlock.class)
public class CogWheelBlockMixin {

    @Shadow
    boolean isLarge;

    @Inject(method = "useItemOn",remap = false,at = @At(value = "RETURN",shift = At.Shift.BEFORE),cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void inject(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<ItemInteractionResult> cir){
        ItemStack item = player.getItemInHand(hand);
        if (!item.is(Items.SPRUCE_PLANKS) &&item.is(ItemTags.PLANKS) && BuiltInRegistries.ITEM.getKey(item.getItem()).getNamespace().equals("minecraft")) {
            Direction.Axis axis = state.getValue(CogWheelBlock.AXIS);
            level.setBlockAndUpdate(pos, BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID,BuiltInRegistries.ITEM.getKey(item.getItem()).getPath().replace("_planks","")+(isLarge?"_large_cogwheel":"_cogwheel"))).defaultBlockState().setValue(CogWheelBlock.AXIS, axis));
            cir.setReturnValue(ItemInteractionResult.SUCCESS);
        }
    }

}
