package fr.iglee42.createcasing.utils;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.config.CCKinetics;
import fr.iglee42.createcasing.config.ModConfigs;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.simibubi.create.content.kinetics.base.HorizontalKineticBlock.HORIZONTAL_FACING;
import static com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock.AXIS;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = CreateCasing.MODID)
public class ItemChangeBlockManager {

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event){
        Level level = event.getEntity().level();
        if (event.getItemStack().isEmpty()) return;
        if (level.getBlockState(event.getPos()).isAir()) return;
        BlockState state = level.getBlockState(event.getPos());
        if (EncasableBlocks.hasBlocksForCasing(event.getItemStack().getItem()) && ModConfigs.common().kinetics.casingBlockSwappable.get()) {
            EncasableBlocks casingSet = EncasableBlocks.getBlockByCasing(event.getItemStack().getItem());
            if (casingSet.isInSet(state)) return;
            if (EncasableBlocks.isGearbox(state))
                changeAxisBlock(event, state, level, casingSet.getGearbox().getDefaultState());
            if (EncasableBlocks.isMixer(state))
                changeBlock(event, state, level, casingSet.getMixer().getDefaultState());
            if (EncasableBlocks.isPress(state))
                changeHorizontalDirectionBlock(event, state, level, casingSet.getPress().getDefaultState());
            if (EncasableBlocks.isDepot(state) && event.getFace() != Direction.UP)
                changeBlock(event, state, level, casingSet.getDepot().getDefaultState());
            if (EncasableBlocks.isChainDrive(state))
                changeAxisBlock(event, state, level, casingSet.getChainDrive().getDefaultState());
            if (EncasableBlocks.isAdjustableChainDrive(state))
                changeAxisBlock(event, state, level, casingSet.getAdjustableChainDrive().getDefaultState());
        }
        if (WoodBlocks.hasBlocksForItem(event.getItemStack().getItem()) && ModConfigs.common().kinetics.shaftCogwheelsSwappable.get()){
            WoodBlocks woodSet = WoodBlocks.getBlockByItem(event.getItemStack().getItem());
            if (woodSet.isInSet(state)) return;
            if (WoodBlocks.isShaft(state) && woodSet.hasShaft())
                changeAxisBlock(event,state,level, woodSet.getShaft().getDefaultState());
            if (WoodBlocks.isCogwheel(state) && woodSet.hasCogwheel())
                changeAxisBlock(event,state,level, woodSet.getCogwheel().getDefaultState());
            if (WoodBlocks.isLargeCogwheel(state) && woodSet.hasLargeCogwheel())
                changeAxisBlock(event,state,level, woodSet.getLargeCogwheel().getDefaultState());
        }
    }

    private static void changeBlock(PlayerInteractEvent.RightClickBlock event,BlockState state,Level level,BlockState newBlock){
        level.setBlockAndUpdate(event.getPos(), newBlock);
        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }

    private static void changeHorizontalDirectionBlock(PlayerInteractEvent.RightClickBlock event,BlockState state,Level level,BlockState newBlock){
        if (!(state.getBlock() instanceof HorizontalKineticBlock))return;
        Direction facing = state.getValue(HORIZONTAL_FACING);
        changeBlock(event,state,level,newBlock.setValue(HORIZONTAL_FACING,facing));
    }

    private static void changeAxisBlock(PlayerInteractEvent.RightClickBlock event,BlockState state,Level level,BlockState newBlock){
        if (!(state.getBlock() instanceof RotatedPillarKineticBlock))return;
        Direction.Axis axis = state.getValue(AXIS);
        changeBlock(event,state,level,newBlock.setValue(AXIS,axis));
    }


}
