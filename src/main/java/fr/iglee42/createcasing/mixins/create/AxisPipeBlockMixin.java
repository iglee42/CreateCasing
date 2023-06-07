package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.contraptions.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.contraptions.fluids.pipes.AxisPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.foundation.utility.Iterate;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.changeAcces.PublicEncasedPipeBlock;
import fr.iglee42.createcasing.compatibility.createcrystalclear.CreateCrystalClearCompatibility;
import fr.iglee42.createcasing.compatibility.createcrystalclear.GlassEncasedPipeBlockCompat;
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
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(value = AxisPipeBlock.class)
public abstract class AxisPipeBlockMixin {
    
    @Shadow
    public abstract Direction.Axis getAxis(BlockState state);


    /**
     * @author iglee42
     * @reason Allow to use all encased pipes
     */
    @Inject(method = "use",at = @At("HEAD"),cancellable = true)
    public void use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack heldItem = player.getItemInHand(hand);
        List<PublicEncasedPipeBlock> pipes = new ArrayList<>();
        ForgeRegistries.BLOCKS.getKeys().stream().filter(r -> ForgeRegistries.BLOCKS.getValue(r) instanceof PublicEncasedPipeBlock).forEach(r -> pipes.add((PublicEncasedPipeBlock) ForgeRegistries.BLOCKS.getValue(r)));
        pipes.stream().filter(p -> p.getCasing().isIn(heldItem))
                .findFirst().ifPresent(s -> {
                    if (world.isClientSide) {
                        cir.setReturnValue(InteractionResult.SUCCESS);
                    }
                    BlockState newState = s.defaultBlockState();
                    Direction[] var8 = Iterate.directionsInAxis(this.getAxis(state));

                    for (Direction d : var8) {
                        newState = newState.setValue(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(d), true);
                    }

                    FluidTransportBehaviour.cacheFlows(world, pos);
                    world.setBlockAndUpdate(pos, newState);
                    FluidTransportBehaviour.loadFlows(world, pos);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                });
        if (CreateCasing.isCrystalClearLoaded()) {
            if (CreateCrystalClearCompatibility.checkAxisPipes(world, pos, heldItem, this.getAxis(state)))
                cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }



}
