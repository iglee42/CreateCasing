package fr.iglee42.createcasing.mixins.extendedgears;

import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.utility.Iterate;
import fr.iglee42.createcasing.CreateCasing;
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
            List<CustomCogwheelCompat> cogs = new ArrayList<>();
            ForgeRegistries.BLOCKS.getKeys().stream().filter(r -> ForgeRegistries.BLOCKS.getValue(r) instanceof CustomCogwheelCompat).forEach(r -> cogs.add((CustomCogwheelCompat) ForgeRegistries.BLOCKS.getValue(r)));
            cogs.stream().filter(c->(c.getCogwheel() == state.getBlock() || c.getHalfCog() == state.getBlock() || c.getShaftlessCog() == state.getBlock()) && ((CogWheelBlock)state.getBlock()).isLargeCog() == c.isLargeCog() && c.getCasing().isIn(heldItem)).findFirst().ifPresent(encasedCog->{
                if (world.isClientSide) {
                    cir.setReturnValue(InteractionResult.SUCCESS);
                }

                BlockState encasedState = encasedCog.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                Direction[] var14 = Iterate.directionsInAxis(state.getValue(AXIS));

                for (Direction d : var14) {
                    BlockState adjacentState = world.getBlockState(pos.relative(d));
                    if (adjacentState.getBlock() instanceof IRotate) {
                        IRotate def = (IRotate) adjacentState.getBlock();
                        if (def.hasShaftTowards(world, pos.relative(d), adjacentState, d.getOpposite())) {
                            encasedState = encasedState.cycle(d.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT : EncasedCogwheelBlock.BOTTOM_SHAFT);
                        }
                    }
                }

                KineticTileEntity.switchToBlockState(world, pos, encasedState);
                cir.setReturnValue(InteractionResult.SUCCESS);
            });
            if (CreateCasing.isCrystalClearLoaded()){
                //if (CreateCrystalClearCompatibility.checkCustomCogs()) cir.setReturnValue(InteractionResult.SUCCESS);
                /*List<CustomGlassCogwheelCompat> glassCogs = new ArrayList<>();
                ForgeRegistries.BLOCKS.getKeys().stream().filter(r -> ForgeRegistries.BLOCKS.getValue(r) instanceof CustomGlassCogwheelCompat).forEach(r -> glassCogs.add((CustomGlassCogwheelCompat) ForgeRegistries.BLOCKS.getValue(r)));
                glassCogs.stream().filter(c->c.getCogwheel() == state.getBlock() && ((MetalCogWheel)state.getBlock()).isLargeCog() == c.isLargeCog() && c.getCasing().isIn(heldItem)).findFirst().ifPresent(encasedCog->{
                    if (world.isClientSide) {
                        cir.setReturnValue(InteractionResult.SUCCESS);
                    }

                    BlockState encasedState = encasedCog.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                    Direction[] var14 = Iterate.directionsInAxis(state.getValue(AXIS));

                    for (Direction d : var14) {
                        BlockState adjacentState = world.getBlockState(pos.relative(d));
                        if (adjacentState.getBlock() instanceof IRotate) {
                            IRotate def = (IRotate) adjacentState.getBlock();
                            if (def.hasShaftTowards(world, pos.relative(d), adjacentState, d.getOpposite())) {
                                encasedState = encasedState.cycle(d.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT : EncasedCogwheelBlock.BOTTOM_SHAFT);
                            }
                        }
                    }

                    KineticTileEntity.switchToBlockState(world, pos, encasedState);
                    cir.setReturnValue(InteractionResult.SUCCESS);*/
            }
        }
    }
}
