package fr.iglee42.createcasing.mixins.create;

import com.cyvack.create_crystal_clear.blocks.glass_encased_shaft.GlassEncasedShaftBlock;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.ShaftBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedShaftBlock;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.compatibility.createcrystalclear.CreateCrystalClearCompatibility;
import fr.iglee42.createcasing.compatibility.createcrystalclear.GlassEncasedShaftBlockCompat;
import net.minecraft.core.BlockPos;
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

@Mixin(value = ShaftBlock.class,priority = 2000)
public class ShaftBlockMixin {

    /**
     * @author iglee42
     * @reason Allow to use all encased shaft
     */
    @Inject(method = "use",at = @At("HEAD") ,cancellable = true)
    public void use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray, CallbackInfoReturnable<InteractionResult> cir) {
        if (!player.isShiftKeyDown() && player.mayBuild()) {
            ItemStack heldItem = player.getItemInHand(hand);
            List<EncasedShaftBlock> shafts = new ArrayList<>();
            ForgeRegistries.BLOCKS.getKeys().stream().filter(r -> ForgeRegistries.BLOCKS.getValue(r) instanceof EncasedShaftBlock).forEach(r -> shafts.add((EncasedShaftBlock) ForgeRegistries.BLOCKS.getValue(r)));
            shafts.stream().filter(s->s.getCasing().isIn(heldItem))
                    .findFirst().ifPresent(s->{
                        if (world.isClientSide) {
                            cir.setReturnValue(InteractionResult.SUCCESS);
                        }
                        KineticTileEntity.switchToBlockState(world, pos, s.defaultBlockState().setValue(AXIS, state.getValue(AXIS)));
                        cir.setReturnValue(InteractionResult.SUCCESS);
                    });
            if (CreateCasing.isCrystalClearLoaded()) {
                if (CreateCrystalClearCompatibility.checkShaft(world,heldItem,pos,state)) cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }

}
