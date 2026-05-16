package fr.iglee42.createcasing.blocks;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.AbstractEncasedShaftBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import com.tterrag.registrate.util.RegistrateDistExecutor;
import fr.iglee42.createcasing.blockEntities.AutoClutchBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import fr.iglee42.createcasing.screen.AutoClutchScreen;
import net.createmod.catnip.gui.ScreenOpener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class AutoClutchBlock extends AbstractEncasedShaftBlock implements IBE<AutoClutchBlockEntity> {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public AutoClutchBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ACTIVE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public Class<AutoClutchBlockEntity> getBlockEntityClass() {
        return AutoClutchBlockEntity.class;
    }

    @Override
    public BlockEntityType<AutoClutchBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.AUTOMATIC_CLUTCH.get();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState p_60503_, Level world, BlockPos pos, Player player, BlockHitResult p_60508_) {
        RegistrateDistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> withBlockEntityDo(world, pos, be -> openScreen(be,player)));
        return InteractionResult.SUCCESS;
    }

    @OnlyIn(Dist.CLIENT)
    protected void openScreen(AutoClutchBlockEntity be, Player player){
        if (!(player instanceof LocalPlayer))
            return;
        ScreenOpener.open(new AutoClutchScreen(be));
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        BlockEntity be = worldIn.getBlockEntity(pos);
        if (be == null || !(be instanceof KineticBlockEntity kte))
            return;
        RotationPropagator.handleAdded(worldIn, pos, kte);
    }
}
