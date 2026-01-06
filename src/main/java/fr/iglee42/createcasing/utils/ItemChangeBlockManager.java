package fr.iglee42.createcasing.utils;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.blocks.ConfigurableGearboxBlock;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.config.CCKinetics;
import fr.iglee42.createcasing.config.ModConfigs;
import fr.iglee42.createcasing.transmissions.TransmissionSet;
import fr.iglee42.createcasing.transmissions.TransmissionSets;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.simibubi.create.content.kinetics.base.DirectionalKineticBlock.FACING;
import static com.simibubi.create.content.kinetics.base.HorizontalKineticBlock.HORIZONTAL_FACING;
import static com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock.AXIS;

@EventBusSubscriber(modid = CreateCasing.MODID)
public class ItemChangeBlockManager {

    @SubscribeEvent
    public static <T extends Comparable<T>> void onRightClick(PlayerInteractEvent.RightClickBlock event){
        Level level = event.getEntity().level();
        if (event.getItemStack().isEmpty()) return;
        if (level.getBlockState(event.getPos()).isAir()) return;
        BlockState state = level.getBlockState(event.getPos());
        CasingSet casingSet;
        if ((casingSet = getSetForCasing(event.getItemStack().getItem())) != null && ModConfigs.common().kinetics.casingBlockSwappable.get()) {
            if (casingSet.isInSet(state.getBlock())) return;
            if (isGearbox(state) && casingSet.getGearbox() != null)
                changeAxisBlock(event, state, level, casingSet.getGearbox().defaultBlockState());
            if (isMixer(state) && casingSet.getMixer() != null)
                changeBlock(event, state, level, casingSet.getMixer().defaultBlockState());
            if (isPress(state)&& casingSet.getPress() != null)
                changeHorizontalDirectionBlock(event, state, level, casingSet.getPress().defaultBlockState());
            if (isDepot(state) && event.getFace() != Direction.UP && casingSet.getDepot() != null)
                changeBlock(event, state, level, casingSet.getDepot().defaultBlockState());
            if (isChainDrive(state) && casingSet.getChainDrive() != null)
                changeAxisBlock(event, state, level, casingSet.getChainDrive().defaultBlockState());
            if (isChainGearshift(state) && casingSet.getChainGearshift() != null)
                changeAxisBlock(event, state, level, casingSet.getChainGearshift().defaultBlockState());
            if (isConfigurableGearbox(state) && casingSet.getConfigurableGearbox() != null)
            {
                BlockState newState = casingSet.getConfigurableGearbox().defaultBlockState();
                for (Direction dir : Iterate.directions) {
                    Property<Boolean> property = ConfigurableGearboxBlock.getPropertyByDirection(dir);
                    newState = newState.setValue(property,state.getValue(property));
                }
                changeBlock(event, state, level, newState);
            }
            if (isChainConveyor(state) && casingSet.getChainConveyor() != null) {
                changeBlock(event, state, level, casingSet.getChainConveyor().defaultBlockState());
            }
            if (isGearshift(state) && casingSet.getGearshift() != null){
                changeAxisBlock(event,state,level, casingSet.getGearshift().defaultBlockState());
            }
            if (isClutch(state) && casingSet.getClutch() != null){
                changeAxisBlock(event,state,level, casingSet.getClutch().defaultBlockState());
            }
            if (isDeployer(state) && casingSet.getDeployer() != null){
                changeFacingBlock(event,state,level, casingSet.getDeployer().defaultBlockState());
            }
            if (isStorageInterface(state) && casingSet.getStorageInterface() != null){
                changeFacingBlock(event,state,level, casingSet.getStorageInterface().defaultBlockState());
            }
            if (isEncasedFan(state) && casingSet.getEncasedFan() != null){
                changeFacingBlock(event,state,level,casingSet.getEncasedFan().defaultBlockState());
            }
        }
        TransmissionSet transmissionSet;

        if ((transmissionSet = getSetForItem(event.getItemStack().getItem())) != null && ModConfigs.common().kinetics.shaftCogwheelsSwappable.get()){
            if (transmissionSet.isInSet(state.getBlock())) return;
            if (isShaft(state) && transmissionSet.getShaft() != null)
                changeAxisBlock(event,state,level, transmissionSet.getShaft().defaultBlockState());
            if (isCogwheel(state) && transmissionSet.getCogwheel() != null)
                changeAxisBlock(event,state,level, transmissionSet.getCogwheel().defaultBlockState());
            if (isLargeCogwheel(state) && transmissionSet.getLargeCogwheel() != null)
                changeAxisBlock(event,state,level, transmissionSet.getLargeCogwheel().defaultBlockState());
        }
    }

    private static void changeBlock(PlayerInteractEvent.RightClickBlock event,BlockState state,Level level,BlockState newBlock){
        level.setBlockAndUpdate(event.getPos(), newBlock);
        level.levelEvent(2001, event.getPos(), Block.getId(newBlock));
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

    private static void changeFacingBlock(PlayerInteractEvent.RightClickBlock event,BlockState state,Level level,BlockState newBlock){
        if (!state.hasProperty(FACING)) return;
        Direction direction = state.getValue(FACING);
        changeBlock(event,state,level,newBlock.setValue(FACING,direction));
    }


    private static CasingSet getSetForCasing(Item casing){
        return CasingSets.getSets().stream()
                .filter(set->set.getCasing() != null)
                .filter(set->set.getCasing().asItem().equals(casing))
                .findFirst()
                .orElse(null);
    }

    private static TransmissionSet getSetForItem(Item item){
        return TransmissionSets.getSets().stream()
                .filter(set->set.getItem() != null)
                .filter(set->set.getItem().equals(item))
                .findFirst()
                .orElse(null);
    }

    public static boolean isGearbox(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getGearbox() != null).anyMatch(set->state.getBlock().equals(set.getGearbox()));
    }

    public static boolean isPress(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getPress() != null).anyMatch(set->state.getBlock().equals(set.getPress()));
    }

    public static boolean isMixer(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getMixer() != null).anyMatch(set->state.getBlock().equals(set.getMixer()));
    }

    public static boolean isDepot(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getDepot() != null).anyMatch(set->state.getBlock().equals(set.getDepot()));
    }

    public static boolean isChainDrive(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getChainDrive() != null).anyMatch(set->state.getBlock().equals(set.getChainDrive()));
    }

    public static boolean isChainGearshift(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getChainGearshift() != null).anyMatch(set->state.getBlock().equals(set.getChainGearshift()));
    }

    public static boolean isConfigurableGearbox(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getConfigurableGearbox() != null).anyMatch(set->state.getBlock().equals(set.getConfigurableGearbox()));
    }

    public static boolean isChainConveyor(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getChainConveyor() != null).anyMatch(set->state.getBlock().equals(set.getChainConveyor()));
    }

    public static boolean isGearshift(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getGearshift() != null).anyMatch(set->state.getBlock().equals(set.getGearshift()));
    }

    public static boolean isClutch(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getClutch() != null).anyMatch(set->state.getBlock().equals(set.getClutch()));
    }

    public static boolean isDeployer(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getDeployer() != null).anyMatch(set->state.getBlock().equals(set.getDeployer()));
    }
    public static boolean isStorageInterface(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getStorageInterface() != null).anyMatch(set->state.getBlock().equals(set.getStorageInterface()));
    }
    public static boolean isEncasedFan(BlockState state){
        return CasingSets.getSets().stream().filter(set->set.getEncasedFan() != null).anyMatch(set->state.getBlock().equals(set.getEncasedFan()));
    }

    public static boolean isShaft(BlockState state){
        return TransmissionSets.getSets().stream().filter(set->set.getShaft() != null).anyMatch(set->state.getBlock().equals(set.getShaft()));
    }

    public static boolean isCogwheel(BlockState state){
        return TransmissionSets.getSets().stream().filter(set->set.getCogwheel() != null).anyMatch(set->state.getBlock().equals(set.getCogwheel()));
    }

    public static boolean isLargeCogwheel(BlockState state){
        return TransmissionSets.getSets().stream().filter(set->set.getLargeCogwheel() != null).anyMatch(set->state.getBlock().equals(set.getLargeCogwheel()));
    }



}
