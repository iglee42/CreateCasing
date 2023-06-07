package fr.iglee42.createcasing.mixins.extendedgears;

import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.utility.Iterate;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.compatibility.createextendedcogs.CreateExtendedCogwheelsCompat;
import fr.iglee42.createcasing.compatibility.createextendedcogs.CustomCogwheelCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

import static com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock.AXIS;

@Mixin(CogWheelBlock.class)
public class CogWheelBlockMixin {

    @Inject(method = "use",at = @At("HEAD"),cancellable = true)
    private void use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray, CallbackInfoReturnable<InteractionResult> cir) {
        if (!player.isShiftKeyDown() && player.mayBuild() && CreateCasing.isExtendedCogsLoaded()) {
            ItemStack heldItem = player.getItemInHand(hand);
            if (CreateExtendedCogwheelsCompat.checkCogs(world,state,pos,heldItem)) cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
