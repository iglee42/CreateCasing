package fr.iglee42.createcasing.blocks.shafts;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.tterrag.registrate.util.RegistrateDistExecutor;
import fr.iglee42.createcasing.blockEntities.BrassShaftBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import fr.iglee42.createcasing.screen.BrassShaftScreen;
import net.createmod.catnip.gui.ScreenOpener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BrassShaftBlock extends MetalShaftBlock{
    public BrassShaftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.BRASS_SHAFT.get();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState p_60503_, Level world, BlockPos pos, Player player, BlockHitResult p_60508_) {
        RegistrateDistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> withBlockEntityDo(world, pos, be -> openScreen((BrassShaftBlockEntity) be,player)));
        return InteractionResult.SUCCESS;
    }

    @OnlyIn(Dist.CLIENT)
    protected void openScreen(BrassShaftBlockEntity be,Player player){
        if (!(player instanceof LocalPlayer))
            return;
        ScreenOpener.open(new BrassShaftScreen(be));
    }

    @Override
    public void neighborChanged(BlockState p_60509_, Level level, BlockPos pos, Block p_60512_, BlockPos p_60513_, boolean p_60514_) {
        if (level.hasNeighborSignal(pos)){
            RotationPropagator.handleAdded(level,pos, (KineticBlockEntity) level.getBlockEntity(pos));
        }
        super.neighborChanged(p_60509_, level, pos, p_60512_, p_60513_, p_60514_);
    }
}
