package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.function.Supplier;

public class EncasedCustomCogwheelBlock extends EncasedCogwheelBlock {

    private Supplier<Block> cogwheel;

    public EncasedCustomCogwheelBlock(Properties properties, boolean large, Supplier<Block> casing, Supplier<? extends Block> cogwheel) {
        super(properties, large, casing);
        this.cogwheel = cogwheel::get;
    }

    public Supplier<Block> getCogwheel() {
        return cogwheel;
    }

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        if (context.getLevel().isClientSide)
            return InteractionResult.SUCCESS;
        context.getLevel()
                .levelEvent(2001, context.getClickedPos(), Block.getId(state));
        KineticBlockEntity.switchToBlockState(context.getLevel(), context.getClickedPos(),
                cogwheel.get().defaultBlockState()
                        .setValue(AXIS, state.getValue(AXIS)));
        return InteractionResult.SUCCESS;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        if (target instanceof BlockHitResult)
            return ((BlockHitResult) target).getDirection()
                    .getAxis() == getRotationAxis(state) ? new ItemStack(cogwheel.get()) : getCasing().asItem().getDefaultInstance();
        return super.getCloneItemStack(state, target, world, pos, player);
    }

    @Override
    public BlockEntityType<? extends SimpleKineticBlockEntity> getBlockEntityType() {
        return isLarge ? ModBlockEntities.ENCASED_CUSTOM_LARGE_COGWHEEL.get() : ModBlockEntities.ENCASED_CUSTOM_COGWHEEL.get();
    }

}
