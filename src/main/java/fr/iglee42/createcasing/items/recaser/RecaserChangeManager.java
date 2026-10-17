package fr.iglee42.createcasing.items.recaser;

import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import com.simibubi.create.content.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlockEntity;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlock;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.kinetics.saw.SawBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueHandler;
import fr.iglee42.createcasing.blockEntities.AutoClutchBlockEntity;
import fr.iglee42.createcasing.blocks.ConfigurableGearboxBlock;
import fr.iglee42.createcasing.mixins.create.fluids.FluidValveBlockEntityAccessor;
import fr.iglee42.createcasing.sets.SetBase;
import fr.iglee42.createcasing.sets.casings.CasingSet;
import fr.iglee42.createcasing.sets.casings.CasingSets;
import fr.iglee42.createcasing.sets.fluids.FluidSet;
import fr.iglee42.createcasing.sets.fluids.FluidSets;
import fr.iglee42.createcasing.sets.transmissions.TransmissionSet;
import fr.iglee42.createcasing.sets.transmissions.TransmissionSets;
import fr.iglee42.createcasing.items.recaser.RecaserBlockChanger.*;
import fr.iglee42.createcasing.items.recaser.RecaserBlockChanger.ActionStore.*;
import net.createmod.catnip.animation.LerpedFloat;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static fr.iglee42.createcasing.items.recaser.RecaserBlockChanger.changeBlock;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.*;

public class RecaserChangeManager {

    public static final HashMap<Item, List<SetBase<?, ?>>> ITEM_SETS_CACHE = new HashMap<>();

    private static final Key<Integer> autoClutchValue = new Key<>();
    private static final Key<AutoClutchBlockEntity.Mode> autoClutchMode = new Key<>();
    private static final Key<AutoClutchBlockEntity.Operation> autoClutchOperation = new Key<>();
    private static final Key<ItemStack> filter = new Key<>();
    private static final Key<ItemStack> depotItem = new Key<>();
    private static final Key<Float> fluidValvePointer = new Key<>();

    public static InteractionResult tryChangeBlock(Level level, BlockPos pos, BlockState state, Direction clickedFace, Player player, RecaserItem.Ammo ammo) {
        SetBase<?, ?> blockSet = getSetForBlock(state.getBlock());

        if (blockSet == null)
            return InteractionResult.PASS;

        if (ammo.sets().contains(blockSet))
            return InteractionResult.PASS;

        SetBase<?, ?> newSet = ammo.sets()
                .stream()
                .filter(set -> set.getClass().equals(blockSet.getClass()))
                .findFirst()
                .orElse(null);

        if (newSet == null)
            return InteractionResult.PASS;

        RecaserBlockChanger changer = new RecaserBlockChanger();

        if (newSet instanceof CasingSet newCSet && blockSet instanceof CasingSet oldCSet) {
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getShaft, AXIS);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getCogwheel, AXIS, EncasedCogwheelBlock.BOTTOM_SHAFT, EncasedCogwheelBlock.TOP_SHAFT);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getLargeCogwheel, AXIS, EncasedCogwheelBlock.BOTTOM_SHAFT, EncasedCogwheelBlock.TOP_SHAFT);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getFluidPipe, EncasedPipeBlock.FACING_TO_PROPERTY_MAP.values().toArray(new BooleanProperty[0]));
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getGearbox, AXIS);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getMixer);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getPress, HORIZONTAL_FACING);
            if (clickedFace != Direction.UP)
                changer.<CasingSet, DepotBlockEntity>replace(level,pos, oldCSet, newCSet, CasingSet::getDepot,(lvl,blockPos,be,store)->{
                    store.set(depotItem, be.getHeldItem().copy());
                    be.setHeldItem(ItemStack.EMPTY);
                },(lvl,blockPos,be,store)->{
                    be.setHeldItem(store.get(depotItem));
                });

            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getChainDrive, AXIS);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getChainGearshift, AXIS);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getChainConveyor);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getGearshift, AXIS);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getClutch, AXIS);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getStorageInterface, FACING);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getEncasedFan, FACING);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getHarvester, HORIZONTAL_FACING);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getDrill, FACING);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getPlough, HORIZONTAL_FACING);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getRoller, HORIZONTAL_FACING);
            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getSlicer);

            changer.<CasingSet, AutoClutchBlockEntity>replace(level,pos, oldCSet, newCSet, CasingSet::getAutoClutch, (lvl, blockPos, be, store)->{
                store.set(autoClutchValue, be.getConfiguredValue());
                store.set(autoClutchMode, be.getMode());
                store.set(autoClutchOperation, be.getOperation());
            }, (lvl, blockPos, be, store)->{
                be.setConfiguredValue(store.get(autoClutchValue));
                be.setMode(store.get(autoClutchMode));
                be.setOperation(store.get(autoClutchOperation));
            }, AXIS);

            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getConfigurableGearbox, ConfigurableGearboxBlock.PROPERTY_BY_DIRECTION.values().toArray(new BooleanProperty[0]));

            changer.replace(level,pos, oldCSet, newCSet, CasingSet::getSaw,SawBlock.AXIS_ALONG_FIRST_COORDINATE,SawBlock.FLIPPED, FACING);
        }

        if (newSet instanceof TransmissionSet newTSet && blockSet instanceof TransmissionSet oldTSet) {
            changer.replace(level,pos, oldTSet, newTSet, TransmissionSet::getShaft, AXIS);
            changer.replace(level,pos, oldTSet, newTSet, TransmissionSet::getCogwheel, AXIS);
            changer.replace(level,pos, oldTSet, newTSet, TransmissionSet::getLargeCogwheel, AXIS);
        }

        if (newSet instanceof FluidSet newFSet && blockSet instanceof FluidSet oldFSet) {
            changer.replace(level,pos, oldFSet, newFSet, FluidSet::getFluidPipe, FluidPipeBlock.PROPERTY_BY_DIRECTION.values().toArray(new BooleanProperty[0]));
            changer.replace(level,pos, oldFSet, newFSet, FluidSet::getGlassFluidPipe, AXIS);
            changer.replace(level,pos, oldFSet, newFSet, FluidSet::getPump, FACING);
            changer.<FluidSet, SmartFluidPipeBlockEntity>replace(level,pos, oldFSet, newFSet, FluidSet::getSmartFluidPipe,(lvl, blockPos, be, store)->{
                FilteringBehaviour filteringBehaviour = be.getBehaviour(FilteringBehaviour.TYPE);
                if (filteringBehaviour != null) {
                    store.set(filter, filteringBehaviour.getFilter().copy());
                    be.clearContent();
                }
            } ,(lvl,blockPos, be,store)->{
                FilteringBehaviour filteringBehaviour = be.getBehaviour(FilteringBehaviour.TYPE);
                if (filteringBehaviour != null) {
                    be.clearContent();
                    filteringBehaviour.setFilter(store.get(filter));
                }
            }, HORIZONTAL_FACING, SmartFluidPipeBlock.FACE);
            changer.replace(level,pos, oldFSet, newFSet, FluidSet::getFluidTank);
            changer.replace(level,pos, oldFSet, newFSet, FluidSet::getSteamEngine, HORIZONTAL_FACING,SteamEngineBlock.FACE);
            changer.replace(level,pos, oldFSet, newFSet, FluidSet::getItemDrain);
            changer.<FluidSet, FluidValveBlockEntity>replace(level,pos, oldFSet, newFSet, FluidSet::getFluidValve, (lvl, blockPos, be, store)->{
                store.set(fluidValvePointer,((FluidValveBlockEntityAccessor)be).encased$getPointer().getValue());
            } ,(lvl,blockPos, be,store)->{
                ((FluidValveBlockEntityAccessor)be).encased$getPointer().setValue(store.get(fluidValvePointer));
            },  FluidValveBlock.AXIS_ALONG_FIRST_COORDINATE, FluidValveBlock.ENABLED, FluidValveBlock.FACING);
            changer.replace(level,pos, oldFSet, newFSet, FluidSet::getPortableFluidInterface, FACING);
            changer.replace(level,pos, oldFSet, newFSet, FluidSet::getHosePulley, HORIZONTAL_FACING);
            changer.replace(level,pos, oldFSet, newFSet, FluidSet::getSpout);
            changer.replace(level,pos, oldFSet, newFSet, FluidSet::getWhistle,HORIZONTAL_FACING, WhistleBlock.POWERED, WhistleBlock.SIZE, WhistleBlock.WALL);
        }

        InteractionResult placementResult = changer.getPlacementResult();
        if (placementResult.consumesAction()){
            if (!player.isCreative()) {
                ammo.stack().shrink(1);
                if (ammo.stack().isEmpty())
                    player.getInventory().removeItem(ammo.stack());
            }
            if (level.isClientSide)
                ScrollValueHandler.wrenchCog.bump(10,30f);
            player.swing(InteractionHand.MAIN_HAND);
        }
        return placementResult;
    }



    public static @Nullable SetBase<?, ?> getSetForBlock(Block block) {
        CasingSet cSet = getCasingSetForBlock(block);
        if (cSet != null) return cSet;
        TransmissionSet tSet = getTransmissionSetForBlock(block);
        if (tSet != null) return tSet;
        return getFluidSetForBlock(block);
    }

    private static CasingSet getCasingSetForBlock(Block block) {
        return CasingSets.getSets().stream()
                .filter(set -> set.isInSet(block))
                .findFirst()
                .orElse(null);
    }

    private static TransmissionSet getTransmissionSetForBlock(Block block) {
        return TransmissionSets.getSets().stream()
                .filter(set -> set.isInSet(block))
                .findFirst()
                .orElse(null);
    }

    private static FluidSet getFluidSetForBlock(Block block) {
        return FluidSets.getSets().stream()
                .filter(set -> set.isInSet(block))
                .findFirst()
                .orElse(null);
    }

    private static CasingSet getCasingSetForItem(RegistryAccess registryAccess, Item item) {
        return CasingSets.getSets().stream()
                .filter(set -> set.isItemForSet(registryAccess, item))
                .findFirst()
                .orElse(null);
    }

    private static TransmissionSet getTransmissionSetForItem(RegistryAccess registryAccess, Item item) {
        return TransmissionSets.getSets().stream()
                .filter(set -> set.isItemForSet(registryAccess, item))
                .findFirst()
                .orElse(null);
    }

    private static FluidSet getFluidSetForItem(RegistryAccess registryAccess, Item item) {
        return FluidSets.getSets().stream()
                .filter(set -> set.isItemForSet(registryAccess, item))
                .findFirst()
                .orElse(null);
    }

    public static boolean isShaft(BlockState state) {
        return TransmissionSets.getSets().stream().filter(set -> set.getShaft() != null).anyMatch(set -> state.getBlock().equals(set.getShaft()));
    }

    public static boolean isCogwheel(BlockState state) {
        return TransmissionSets.getSets().stream().filter(set -> set.getCogwheel() != null).anyMatch(set -> state.getBlock().equals(set.getCogwheel()));
    }

    public static boolean isLargeCogwheel(BlockState state) {
        return TransmissionSets.getSets().stream().filter(set -> set.getLargeCogwheel() != null).anyMatch(set -> state.getBlock().equals(set.getLargeCogwheel()));
    }

    public static List<SetBase<?, ?>> getSetsForItem(RegistryAccess registryAccess, Item item) {
        return ITEM_SETS_CACHE.computeIfAbsent(item, key -> findSetsForItem(registryAccess, key));
    }

    private static List<SetBase<?, ?>> findSetsForItem(RegistryAccess registryAccess, Item item) {
        List<SetBase<?, ?>> sets = new ArrayList<>();
        CasingSet cSet = getCasingSetForItem(registryAccess, item);
        if (cSet != null) sets.add(cSet);
        TransmissionSet tSet = getTransmissionSetForItem(registryAccess, item);
        if (tSet != null) sets.add(tSet);
        FluidSet fSet = getFluidSetForItem(registryAccess, item);
        if (fSet != null) sets.add(fSet);
        return sets;
    }

}
