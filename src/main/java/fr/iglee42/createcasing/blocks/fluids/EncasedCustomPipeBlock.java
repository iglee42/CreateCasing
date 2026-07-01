package fr.iglee42.createcasing.blocks.fluids;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

import java.util.function.Supplier;

public class EncasedCustomPipeBlock extends EncasedPipeBlock {

    private Supplier<? extends FluidPipeBlock> pipe;

    public EncasedCustomPipeBlock(Properties properties, Supplier<Block> casing, Supplier<? extends FluidPipeBlock> pipe) {
        super(properties, casing);
        this.pipe = pipe::get;
    }

    @Override
    public BlockEntityType<? extends FluidPipeBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.ENCASED_CUSTOM_FLUID_PIPE.get();
    }

    public Supplier<? extends FluidPipeBlock> getPipe() {
        return pipe;
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (world.isClientSide)
            return InteractionResult.SUCCESS;

        context.getLevel()
                .levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, context.getClickedPos(), Block.getId(state));
        BlockState equivalentPipe = transferSixWayProperties(state, pipe.get().defaultBlockState());

        Direction firstFound = Direction.UP;
        for (Direction d : Iterate.directions)
            if (state.getValue(FACING_TO_PROPERTY_MAP.get(d))) {
                firstFound = d;
                break;
            }

        FluidTransportBehaviour.cacheFlows(world, pos);
        world.setBlockAndUpdate(pos, pipe.get()
                .updateBlockState(equivalentPipe, firstFound, null, world, pos));
        FluidTransportBehaviour.loadFlows(world, pos);
        return InteractionResult.SUCCESS;
    }

    @Override
    public ItemRequirement getRequiredItems(BlockState state, BlockEntity be) {
        return ItemRequirement.of(pipe.get().defaultBlockState(), be);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return new ItemStack(pipe.get());
    }
}
