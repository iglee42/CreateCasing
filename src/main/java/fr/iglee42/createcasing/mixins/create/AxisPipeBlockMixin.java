package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.contraptions.fluids.pipes.AxisPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.foundation.utility.Iterate;
import fr.iglee42.createcasing.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AxisPipeBlock.class)
public abstract class AxisPipeBlockMixin {
    
    @Shadow
    public abstract Direction.Axis getAxis(BlockState state);


    /**
     * @author iglee42
     * @reason Allow to use all encased pipes
     */
    @Overwrite
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!(player.getMainHandItem().getItem() instanceof BlockItem) || !(((BlockItem)player.getMainHandItem().getItem()).getBlock() instanceof CasingBlock)) {
            return InteractionResult.PASS;
        } else if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockState newState = AllBlocks.ENCASED_FLUID_PIPE.getDefaultState();
            Block block = ((BlockItem) player.getMainHandItem().getItem()).getBlock();
            if (AllBlocks.COPPER_CASING.get().equals(block)) {
                newState = AllBlocks.ENCASED_FLUID_PIPE.getDefaultState();
            } else if (AllBlocks.ANDESITE_CASING.get().equals(block)) {
                newState = ModBlocks.ENCASED_ANDESITE_FLUID_PIPE.getDefaultState();
            } else if (AllBlocks.BRASS_CASING.get().equals(block)) {
                newState = ModBlocks.ENCASED_BRASS_FLUID_PIPE.getDefaultState();
            } else if (AllBlocks.RAILWAY_CASING.get().equals(block)) {
                newState = ModBlocks.ENCASED_RAILWAY_FLUID_PIPE.getDefaultState();
            }
            Direction[] var8 = Iterate.directionsInAxis(this.getAxis(state));
            int var9 = var8.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                Direction d = var8[var10];
                newState = (BlockState)newState.setValue((Property)EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(d), true);
            }

            FluidTransportBehaviour.cacheFlows(world, pos);
            world.setBlockAndUpdate(pos, newState);
            FluidTransportBehaviour.loadFlows(world, pos);
            return InteractionResult.SUCCESS;
        }
    }

}
