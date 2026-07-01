package fr.iglee42.createcasing.utils;

import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.saw.SawBlock;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.blockEntities.AutoClutchBlockEntity;
import fr.iglee42.createcasing.blocks.ConfigurableGearboxBlock;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.config.EncasedConfigs;
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

import java.util.Optional;
import java.util.function.Function;

import static com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock.AXIS;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

@EventBusSubscriber(modid = CreateCasing.MODID)
public class ItemChangeBlockManager {

    @SubscribeEvent
    public static <T extends Comparable<T>> void onRightClick(PlayerInteractEvent.RightClickBlock event){
        Level level = event.getEntity().level();
        if (event.getItemStack().isEmpty()) return;
        if (level.getBlockState(event.getPos()).isAir()) return;
        BlockState state = level.getBlockState(event.getPos());
        CasingSet casingSet;
        if ((casingSet = getSetForCasing(event.getItemStack().getItem())) != null && EncasedConfigs.common().kinetics.casingBlockSwappable.get()) {
            if (casingSet.isInSet(state.getBlock())) return;
            if (isElementInSet(state,CasingSet::getGearbox) && casingSet.getGearbox() != null)
                changeAxisBlock(event, state, level, casingSet.getGearbox().defaultBlockState());
            if (isElementInSet(state,CasingSet::getMixer) && casingSet.getMixer() != null)
                changeBlock(event, state, level, casingSet.getMixer().defaultBlockState());
            if (isElementInSet(state,CasingSet::getPress)&& casingSet.getPress() != null)
                changeHorizontalDirectionBlock(event, state, level, casingSet.getPress().defaultBlockState());
            if (isElementInSet(state,CasingSet::getDepot) && event.getFace() != Direction.UP && casingSet.getDepot() != null)
                changeBlock(event, state, level, casingSet.getDepot().defaultBlockState());
            if (isElementInSet(state,CasingSet::getChainDrive) && casingSet.getChainDrive() != null)
                changeAxisBlock(event, state, level, casingSet.getChainDrive().defaultBlockState());
            if (isElementInSet(state,CasingSet::getChainGearshift) && casingSet.getChainGearshift() != null)
                changeAxisBlock(event, state, level, casingSet.getChainGearshift().defaultBlockState());
            if (isElementInSet(state,CasingSet::getConfigurableGearbox) && casingSet.getConfigurableGearbox() != null)
            {
                BlockState newState = casingSet.getConfigurableGearbox().defaultBlockState();
                for (Direction dir : Iterate.directions) {
                    Property<Boolean> property = ConfigurableGearboxBlock.getPropertyByDirection(dir);
                    newState = newState.setValue(property,state.getValue(property));
                }
                changeBlock(event, state, level, newState);
            }
            if (isElementInSet(state,CasingSet::getChainConveyor) && casingSet.getChainConveyor() != null) {
                changeBlock(event, state, level, casingSet.getChainConveyor().defaultBlockState());
            }
            if (isElementInSet(state,CasingSet::getGearshift) && casingSet.getGearshift() != null){
                changeAxisBlock(event,state,level, casingSet.getGearshift().defaultBlockState());
            }
            if (isElementInSet(state,CasingSet::getClutch) && casingSet.getClutch() != null){
                changeAxisBlock(event,state,level, casingSet.getClutch().defaultBlockState());
            }
            if (isElementInSet(state,CasingSet::getAutoClutch) && casingSet.getAutoClutch() != null){
                Optional<Integer> configuredValue = Optional.empty();
                Optional<AutoClutchBlockEntity.Mode> mode = Optional.empty();
                Optional<AutoClutchBlockEntity.Operation> operation = Optional.empty();
                if (level.getBlockEntity(event.getPos()) instanceof AutoClutchBlockEntity be){
                    configuredValue = Optional.of(be.getConfiguredValue());
                    mode = Optional.of(be.getMode());
                    operation = Optional.of(be.getOperation());
                }
                changeAxisBlock(event,state,level, casingSet.getAutoClutch().defaultBlockState());
                if (level.getBlockEntity(event.getPos()) instanceof AutoClutchBlockEntity be){
                    configuredValue.ifPresent(be::setConfiguredValue);
                    mode.ifPresent(be::setMode);
                    operation.ifPresent(be::setOperation);
                }
            }
            if (isElementInSet(state,CasingSet::getDeployer) && casingSet.getDeployer() != null){
                changeFacingBlock(event,state,level, casingSet.getDeployer().defaultBlockState());
            }
            if (isElementInSet(state,CasingSet::getStorageInterface) && casingSet.getStorageInterface() != null){
                changeFacingBlock(event,state,level, casingSet.getStorageInterface().defaultBlockState());
            }
            if (isElementInSet(state,CasingSet::getEncasedFan) && casingSet.getEncasedFan() != null){
                changeFacingBlock(event,state,level,casingSet.getEncasedFan().defaultBlockState());
            }
            if (isElementInSet(state,CasingSet::getHarvester) && casingSet.getHarvester() != null){
                changeHorizontalDirectionBlock(event,state,level,casingSet.getHarvester().defaultBlockState());
            }
            if (isElementInSet(state,CasingSet::getSaw) && casingSet.getSaw() != null){
                BlockState bs = casingSet.getSaw().defaultBlockState();
                bs = bs.setValue(SawBlock.AXIS_ALONG_FIRST_COORDINATE,state.getValue(SawBlock.AXIS_ALONG_FIRST_COORDINATE)).setValue(SawBlock.FLIPPED,state.getValue(SawBlock.FLIPPED));
                changeFacingBlock(event,state,level,bs);
            }
            if (isElementInSet(state,CasingSet::getDrill) && casingSet.getDrill() != null){
                changeFacingBlock(event,state,level,casingSet.getDrill().defaultBlockState());
            }
            if (isElementInSet(state,CasingSet::getPlough) && casingSet.getPlough() != null){
                changeHorizontalDirectionBlock(event,state,level,casingSet.getPlough().defaultBlockState());
            }
            if (isElementInSet(state,CasingSet::getRoller) && casingSet.getRoller() != null){
                changeHorizontalDirectionBlock(event,state,level,casingSet.getRoller().defaultBlockState());
            }
            if (isElementInSet(state,CasingSet::getSlicer) && casingSet.getSlicer() != null)
                changeBlock(event, state, level, casingSet.getSlicer().defaultBlockState());
        }
        TransmissionSet transmissionSet;

        if ((transmissionSet = getSetForItem(event.getItemStack().getItem())) != null && EncasedConfigs.common().kinetics.shaftCogwheelsSwappable.get()){
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
        if (!state.hasProperty(HORIZONTAL_FACING)) return;
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

    public static boolean isElementInSet(BlockState state, Function<CasingSet,Block> function){
        return CasingSets.getSets().stream().filter(set->function.apply(set) != null).anyMatch(set->state.getBlock().equals(function.apply(set)));
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
