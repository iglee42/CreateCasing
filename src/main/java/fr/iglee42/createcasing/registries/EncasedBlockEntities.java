package fr.iglee42.createcasing.registries;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.contraptions.actors.harvester.HarvesterBlockEntity;
import com.simibubi.create.content.contraptions.actors.harvester.HarvesterRenderer;
import com.simibubi.create.content.contraptions.actors.roller.RollerBlockEntity;
import com.simibubi.create.content.contraptions.actors.roller.RollerRenderer;
import com.simibubi.create.content.contraptions.pulley.HosePulleyVisual;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlockEntity;
import com.simibubi.create.content.decoration.steamWhistle.WhistleRenderer;
import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import com.simibubi.create.content.fluids.drain.ItemDrainRenderer;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyRenderer;
import com.simibubi.create.content.fluids.pipes.*;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveRenderer;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveVisual;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import com.simibubi.create.content.fluids.pump.PumpRenderer;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.content.fluids.spout.SpoutRenderer;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.content.fluids.tank.FluidTankRenderer;
import com.simibubi.create.content.kinetics.base.*;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorBlockEntity;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorRenderer;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorVisual;
import com.simibubi.create.content.kinetics.chainDrive.ChainGearshiftBlockEntity;
import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import com.simibubi.create.content.kinetics.deployer.DeployerRenderer;
import com.simibubi.create.content.kinetics.deployer.DeployerVisual;
import com.simibubi.create.content.kinetics.drill.DrillBlockEntity;
import com.simibubi.create.content.kinetics.drill.DrillRenderer;
import com.simibubi.create.content.kinetics.fan.EncasedFanBlockEntity;
import com.simibubi.create.content.kinetics.fan.EncasedFanRenderer;
import com.simibubi.create.content.kinetics.fan.FanVisual;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;
import com.simibubi.create.content.kinetics.gearbox.GearboxRenderer;
import com.simibubi.create.content.kinetics.gearbox.GearboxVisual;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerRenderer;
import com.simibubi.create.content.kinetics.mixer.MixerVisual;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import com.simibubi.create.content.kinetics.press.MechanicalPressRenderer;
import com.simibubi.create.content.kinetics.press.PressVisual;
import com.simibubi.create.content.kinetics.saw.SawBlockEntity;
import com.simibubi.create.content.kinetics.saw.SawRenderer;
import com.simibubi.create.content.kinetics.saw.SawVisual;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogVisual;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineRenderer;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineVisual;
import com.simibubi.create.content.kinetics.transmission.ClutchBlockEntity;
import com.simibubi.create.content.kinetics.transmission.GearshiftBlockEntity;
import com.simibubi.create.content.kinetics.transmission.SplitShaftRenderer;
import com.simibubi.create.content.kinetics.transmission.SplitShaftVisual;
import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import com.simibubi.create.content.logistics.depot.DepotRenderer;
import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import dev.engine_room.flywheel.lib.model.Models;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.blockEntities.*;
import fr.iglee42.createcasing.blockEntities.renderers.*;
import fr.iglee42.createcasing.blockEntities.visuals.*;
import fr.iglee42.createcasing.blocks.customs.EncasedCustomCogwheelBlock;
import fr.iglee42.createcasing.blocks.fluids.EncasedCustomPipeBlock;
import fr.iglee42.createcasing.blocks.shafts.EncasedCustomShaftBlock;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.fluids.FluidSet;
import fr.iglee42.createcasing.fluids.FluidSets;
import fr.iglee42.createcasing.transmissions.TransmissionSet;
import fr.iglee42.createcasing.transmissions.TransmissionSets;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;

@EventBusSubscriber(modid = CreateCasing.MODID)
public class EncasedBlockEntities {


    public static final BlockEntityEntry<KineticBlockEntity> ENCASED_SHAFT = REGISTRATE
            .blockEntity("encased_shaft", KineticBlockEntity::new)
            .visual(() -> ShaftVisual::new, false)
            .renderer(() -> ShaftRenderer::new)
            .register();
    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_COGWHEEL = REGISTRATE
            .blockEntity("encased_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCogVisual::small, false)
            .renderer(() -> EncasedCogRenderer::small)
            .register();
    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_COGWHEEL_LARGE = REGISTRATE
            .blockEntity("encased_cogwheel_large", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCogVisual::large, false)
            .renderer(() -> EncasedCogRenderer::large)
            .register();

    public static final BlockEntityEntry<FluidPipeBlockEntity> ENCASED_FLUID_PIPE = REGISTRATE
            .blockEntity("encased_fluid_pipe", FluidPipeBlockEntity::new)
            .register();

    public static final BlockEntityEntry<GearboxBlockEntity> GEARBOX = REGISTRATE
            .blockEntity("gearbox", GearboxBlockEntity::new)
            .visual(() -> GearboxVisual::new, false)
            .renderer(() -> GearboxRenderer::new)
            .register();

    public static final BlockEntityEntry<MechanicalMixerBlockEntity> MIXER = REGISTRATE
            .blockEntity("mixer", MechanicalMixerBlockEntity::new)
            .visual(() -> MixerVisual::new)
            .renderer(() -> MechanicalMixerRenderer::new)
            .register();

    public static final BlockEntityEntry<MechanicalPressBlockEntity> PRESS = REGISTRATE
            .blockEntity("press", MechanicalPressBlockEntity::new)
            .visual(() -> PressVisual::new)
            .renderer(() -> MechanicalPressRenderer::new)
            .register();

    public static final BlockEntityEntry<DepotBlockEntity> DEPOT = REGISTRATE
            .blockEntity("depot", DepotBlockEntity::new)
            .renderer(() -> DepotRenderer::new)
            .register();

    public static final BlockEntityEntry<WoodenShaftBlockEntity> WOODEN_SHAFT = REGISTRATE
            .blockEntity("wooden_shaft", WoodenShaftBlockEntity::new)
            .visual(() -> WoodenShaftVisual::create, false)
            //.validBlocks(EncasedBlocks.OAK_SHAFT, EncasedBlocks.SPRUCE_SHAFT, EncasedBlocks.BIRCH_SHAFT, EncasedBlocks.JUNGLE_SHAFT, EncasedBlocks.ACACIA_SHAFT, EncasedBlocks.DARK_OAK_SHAFT, EncasedBlocks.CRIMSON_SHAFT, EncasedBlocks.WARPED_SHAFT, EncasedBlocks.MANGROVE_SHAFT, EncasedBlocks.BAMBOO_SHAFT, EncasedBlocks.CHERRY_SHAFT)
            .renderer(() -> BracketedKineticBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<GlassShaftBlockEntity> GLASS_SHAFT = REGISTRATE
            .blockEntity("glass_shaft", GlassShaftBlockEntity::new)
            .visual(() -> (ctx,be,pt)->new SingleAxisRotatingVisual<>(ctx,be,pt,Models.partial(EncasedPartialModels.GLASS_SHAFT)), false)
            //.validBlocks(EncasedBlocks.GLASS_SHAFT)
            .renderer(() -> BracketedKineticBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<CustomShaftBlockEntity> CUSTOM_SHAFT = REGISTRATE
            .blockEntity("custom_shaft", CustomShaftBlockEntity::new)
            .visual(() -> CustomShaftVisual::new, false)
            //.validBlocks(EncasedBlocks.MLDEG_SHAFT)
            .renderer(() -> BracketedKineticBlockEntityRenderer::new)
            .register();
    public static final BlockEntityEntry<AutoClutchBlockEntity> AUTOMATIC_CLUTCH = REGISTRATE
            .blockEntity("automatic_clutch", AutoClutchBlockEntity::new)
            .visual(() ->SplitShaftVisual::new, false)
            //.validBlocks(EncasedBlocks.BRASS_SHAFT)
            .renderer(() -> SplitShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<CreativeCogwheelBlockEntity> CREATIVE_COGWHEEL = REGISTRATE
            .blockEntity("creative_cogwheel", CreativeCogwheelBlockEntity::new)
            .visual(() -> CreativeCogwheelVisual::new, false)
            .validBlocks(EncasedBlocks.CREATIVE_COGWHEEL)
            .renderer(() -> CreativeCogwheelRenderer::new)
            .register();

    public static final BlockEntityEntry<GearboxBlockEntity> CONFIGURABLE_GEARBOX = REGISTRATE
            .blockEntity("configurable_gearbox", GearboxBlockEntity::new)
            .visual(() -> ConfigurableGearboxVisual::new, false)
            .renderer(() -> ConfigurableGearboxRenderer::new)
            .register();

    public static final BlockEntityEntry<CustomEncasedShaftBlockEntity> ENCASED_CUSTOM_SHAFT = REGISTRATE
            .blockEntity("encased_custom_shaft", CustomEncasedShaftBlockEntity::new)
            .visual(() -> CustomEncasedShaftVisual::new, false)
            .validBlocks()
            .renderer(() -> CustomEncasedShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<KineticBlockEntity> CHAIN_DRIVE = REGISTRATE
            .blockEntity("chain_drive", KineticBlockEntity::new)
            .visual(() -> ShaftVisual::new, false)
            .renderer(() -> ShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<ChainGearshiftBlockEntity> CHAIN_GEARSHIFT = REGISTRATE
            .blockEntity("chain_gearshift", ChainGearshiftBlockEntity::new)
            .visual(() -> ShaftVisual::new, false)
            .renderer(() -> ShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<ChainConveyorBlockEntity> CHAIN_CONVEYOR = REGISTRATE
            .blockEntity("chain_conveyor", ChainConveyorBlockEntity::new)
            .visual(() -> ChainConveyorVisual::new)
            .renderer(() -> ChainConveyorRenderer::new)
            .register();

    public static final BlockEntityEntry<BracketedKineticBlockEntity> WOODEN_COGWHEELS = REGISTRATE
            .blockEntity("wooden_cogwheels", BracketedKineticBlockEntity::new)
            .visual(() -> WoodenCogwheelBlockEntityVisual::create, false)
            .validBlocks()
            .renderer(() -> WoodenCogwheelBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<BracketedKineticBlockEntity> CUSTOM_COGWHEELS = REGISTRATE
            .blockEntity("custom_cogwheels", BracketedKineticBlockEntity::new)
            .visual(() -> WoodenCogwheelBlockEntityVisual::create, false)
            .validBlocks()
            .renderer(() -> WoodenCogwheelBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_CUSTOM_COGWHEEL = REGISTRATE
            .blockEntity("encased_custom_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCustomCogVisual::small, false)
            .validBlocks(AllBlocks.ANDESITE_ENCASED_COGWHEEL, AllBlocks.BRASS_ENCASED_COGWHEEL)
            .renderer(() -> EncasedCustomCogRenderer::small)
            .register();

    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_CUSTOM_LARGE_COGWHEEL = REGISTRATE
            .blockEntity("encased_custom_large_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCustomCogVisual::large, false)
            .validBlocks(AllBlocks.ANDESITE_ENCASED_LARGE_COGWHEEL, AllBlocks.BRASS_ENCASED_LARGE_COGWHEEL)
            .renderer(() -> EncasedCustomCogRenderer::large)
            .register();

    public static final BlockEntityEntry<ClutchBlockEntity> CLUTCH = REGISTRATE
            .blockEntity("clutch", ClutchBlockEntity::new)
            .visual(() -> SplitShaftVisual::new, false)
            .renderer(() -> SplitShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<GearshiftBlockEntity> GEARSHIFT = REGISTRATE
            .blockEntity("gearshift", GearshiftBlockEntity::new)
            .visual(() -> SplitShaftVisual::new, false)
            .renderer(() -> SplitShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<DeployerBlockEntity> DEPLOYER = REGISTRATE
            .blockEntity("deployer", DeployerBlockEntity::new)
            .visual(() -> DeployerVisual::new)
            .renderer(() -> DeployerRenderer::new)
            .register();


    public static final BlockEntityEntry<EncasedFanBlockEntity> ENCASED_FAN = REGISTRATE
            .blockEntity("encased_fan", EncasedFanBlockEntity::new)
            .visual(() -> FanVisual::new, false)
            .renderer(() -> EncasedFanRenderer::new)
            .register();

    public static final BlockEntityEntry<HarvesterBlockEntity> HARVESTER = REGISTRATE
            .blockEntity("harvester", HarvesterBlockEntity::new)
            .renderer(() -> HarvesterRenderer::new)
            .register();

    public static final BlockEntityEntry<RollerBlockEntity> MECHANICAL_ROLLER = REGISTRATE
            .blockEntity("mechanical_roller", RollerBlockEntity::new)
            .renderer(() -> RollerRenderer::new)
            .register();

    public static final BlockEntityEntry<SawBlockEntity> SAW = REGISTRATE
            .blockEntity("saw", SawBlockEntity::new)
            .visual(() -> SawVisual::new)
            .renderer(() -> SawRenderer::new)
            .register();

    public static final BlockEntityEntry<DrillBlockEntity> DRILL = REGISTRATE
            .blockEntity("drill", DrillBlockEntity::new)
            .visual(() -> (context, blockEntity, partialTick) -> {
                Direction facing = blockEntity.getBlockState()
                        .getValue(BlockStateProperties.FACING);
                return new OrientedRotatingVisual<>(context, blockEntity, partialTick, Direction.SOUTH, facing, Models.partial(EncasedPartialModels.getDrillHead(blockEntity.getBlockState())));
            }, false)
            .renderer(() -> DrillRenderer::new)
            .register();

    public static final BlockEntityEntry<SmartFluidPipeBlockEntity> SMART_FLUID_PIPE = REGISTRATE
            .blockEntity("smart_fluid_pipe", SmartFluidPipeBlockEntity::new)
            .renderer(() -> SmartBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<FluidPipeBlockEntity> FLUID_PIPE = REGISTRATE
            .blockEntity("fluid_pipe", FluidPipeBlockEntity::new)
            .register();

    public static final BlockEntityEntry<StraightPipeBlockEntity> GLASS_FLUID_PIPE = REGISTRATE
            .blockEntity("glass_fluid_pipe", StraightPipeBlockEntity::new)
            .visual(() -> GlassPipeVisual::new, false)
            .renderer(() -> TransparentStraightPipeRenderer::new)
            .register();
    public static final BlockEntityEntry<PumpBlockEntity> MECHANICAL_PUMP = REGISTRATE
            .blockEntity("mechanical_pump", PumpBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG))
            .renderer(() -> PumpRenderer::new)
            .register();

    public static final BlockEntityEntry<FluidTankBlockEntity> FLUID_TANK = REGISTRATE
            .blockEntity("fluid_tank", FluidTankBlockEntity::new)
            .renderer(() -> FluidTankRenderer::new)
            .register();

    public static final BlockEntityEntry<SteamEngineBlockEntity> STEAM_ENGINE = REGISTRATE
            .blockEntity("steam_engine", SteamEngineBlockEntity::new)
            .visual(() -> SteamEngineVisual::new, false)
            .renderer(() -> SteamEngineRenderer::new)
            .register();

    public static final BlockEntityEntry<ItemDrainBlockEntity> ITEM_DRAIN = REGISTRATE
            .blockEntity("item_drain", ItemDrainBlockEntity::new)
            .renderer(() -> ItemDrainRenderer::new)
            .register();

    public static final BlockEntityEntry<FluidValveBlockEntity> FLUID_VALVE = REGISTRATE
            .blockEntity("fluid_valve", FluidValveBlockEntity::new)
            .visual(() -> FluidValveVisual::new)
            .renderer(() -> FluidValveRenderer::new)
            .register();

    public static final BlockEntityEntry<HosePulleyBlockEntity> HOSE_PULLEY = REGISTRATE
            .blockEntity("hose_pulley", HosePulleyBlockEntity::new)
            .visual(() -> CustomHosePulleyVisual::new)
            .renderer(() -> CustomHosePulleyRenderer::new)
            .register();

    public static final BlockEntityEntry<WhistleBlockEntity> STEAM_WHISTLE = REGISTRATE
            .blockEntity("steam_whistle", WhistleBlockEntity::new)
            .renderer(() -> WhistleRenderer::new)
            .register();

    public static final BlockEntityEntry<SpoutBlockEntity> SPOUT = REGISTRATE
            .blockEntity("spout", SpoutBlockEntity::new)
            .renderer(() -> SpoutRenderer::new)
            .register();

    public static final BlockEntityEntry<FluidPipeBlockEntity> ENCASED_CUSTOM_FLUID_PIPE = REGISTRATE
            .blockEntity("encased_custom_fluid_pipe", FluidPipeBlockEntity::new)
            .register();


    public static void register() {}

    @SubscribeEvent
    public static void modifyBlockEntity(BlockEntityTypeAddBlocksEvent event){
        register(event,ENCASED_SHAFT.get(), CasingSet::getShaft,CasingSet::doesGenerateShaft);
        register(event,ENCASED_COGWHEEL.get(), CasingSet::getCogwheel, CasingSet::doesGenerateCogwheel);
        register(event,ENCASED_COGWHEEL_LARGE.get(), CasingSet::getLargeCogwheel,CasingSet::doesGenerateLargeCogwheel);
        register(event,ENCASED_FLUID_PIPE.get(), CasingSet::getFluidPipe,CasingSet::doesGenerateFluidPipe);
        register(event,GEARBOX.get(), CasingSet::getGearbox,CasingSet::doesGenerateGearbox);
        register(event,PRESS.get(), CasingSet::getPress,CasingSet::doesGeneratePress);
        register(event,MIXER.get(), CasingSet::getMixer,CasingSet::doesGenerateMixer);
        register(event,DEPOT.get(), CasingSet::getDepot,CasingSet::doesGenerateDepot);
        register(event, CHAIN_DRIVE.get(), CasingSet::getChainDrive,CasingSet::doesGenerateChainDrive);
        register(event, CHAIN_GEARSHIFT.get(),CasingSet::getChainGearshift,CasingSet::doesGenerateChainGearshift);
        register(event,CONFIGURABLE_GEARBOX.get(),CasingSet::getConfigurableGearbox,CasingSet::doesGenerateConfigurableGearbox);
        register(event,CHAIN_CONVEYOR.get(),CasingSet::getChainConveyor,CasingSet::doesGenerateChainConveyor);
        register(event, GEARSHIFT.get(),CasingSet::getGearshift,CasingSet::doesGenerateGearshift);
        register(event,CLUTCH.get(),CasingSet::getClutch,CasingSet::doesGenerateClutch);
        register(event,AUTOMATIC_CLUTCH.get(),CasingSet::getAutoClutch,CasingSet::doesGenerateAutoClutch);
        register(event,DEPLOYER.get(),CasingSet::getDeployer,CasingSet::doesGenerateDeployer);
        register(event,AllBlockEntityTypes.PORTABLE_STORAGE_INTERFACE.get(),CasingSet::getStorageInterface,CasingSet::doesGenerateStorageInterface);
        register(event,ENCASED_FAN.get(),CasingSet::getEncasedFan,CasingSet::doesGenerateEncasedFan);
        register(event,HARVESTER.get(),CasingSet::getHarvester,CasingSet::doesGenerateHarvester);
        register(event,DRILL.get(),CasingSet::getDrill,CasingSet::doesGenerateDrill);
        register(event,SAW.get(),CasingSet::getSaw,CasingSet::doesGenerateSaw);
        register(event,MECHANICAL_ROLLER.get(),CasingSet::getRoller,CasingSet::doesGenerateRoller);

        registerTransmission(event, CUSTOM_SHAFT.get(), TransmissionSet::getShaft, TransmissionSet::doesGenerateShaft, TransmissionSet::getShaftBlockEntityType);
        registerTransmission(event, CUSTOM_COGWHEELS.get(), TransmissionSet::getCogwheel, TransmissionSet::doesGenerateCogwheel, TransmissionSet::getCogwheelBlockEntityType);
        registerTransmission(event, CUSTOM_COGWHEELS.get(), TransmissionSet::getLargeCogwheel, TransmissionSet::doesGenerateLargeCogwheel, TransmissionSet::getLargeCogwheelBlockEntityType);

        registerFluidSet(event, FLUID_PIPE.get(), FluidSet::getFluidPipe, FluidSet::doesGenerateFluidPipe);
        registerFluidSet(event, GLASS_FLUID_PIPE.get(), FluidSet::getGlassFluidPipe, FluidSet::doesGenerateFluidPipe);
        registerFluidSet(event, MECHANICAL_PUMP.get(), FluidSet::getPump, FluidSet::doesGeneratePump);
        registerFluidSet(event, SMART_FLUID_PIPE.get(), FluidSet::getSmartFluidPipe, FluidSet::doesGenerateSmartFluidPipe);
        registerFluidSet(event, FLUID_TANK.get(), FluidSet::getFluidTank, FluidSet::doesGenerateFluidTank);
        registerFluidSet(event, STEAM_ENGINE.get(), FluidSet::getSteamEngine, FluidSet::doesGenerateSteamEngine);
        registerFluidSet(event, ITEM_DRAIN.get(), FluidSet::getItemDrain, FluidSet::doesGenerateItemDrain);
        registerFluidSet(event, FLUID_VALVE.get(), FluidSet::getFluidValve, FluidSet::doesGenerateFluidValve);
        registerFluidSet(event, AllBlockEntityTypes.VALVE_HANDLE.get(), FluidSet::getValveHandle, FluidSet::doesGenerateValveHandle);
        registerFluidSet(event, HOSE_PULLEY.get(), FluidSet::getHosePulley, FluidSet::doesGenerateHosePulley);
        registerFluidSet(event, AllBlockEntityTypes.PORTABLE_FLUID_INTERFACE.get(), FluidSet::getPortableFluidInterface, FluidSet::doesGeneratePortableFluidInterface);
        registerFluidSet(event, STEAM_WHISTLE.get(), FluidSet::getWhistle, FluidSet::doesGenerateWhistle);
        registerFluidSet(event, SPOUT.get(), FluidSet::getSpout, FluidSet::doesGenerateSpout);

        REGISTRATE.getAll(Registries.BLOCK).forEach(e->{
            if (e.get() instanceof EncasedCustomShaftBlock block)
                event.modify(ENCASED_CUSTOM_SHAFT.get(),block);

            if (e.get() instanceof EncasedCustomCogwheelBlock block)
                event.modify(block.isLargeCog() ? ENCASED_CUSTOM_LARGE_COGWHEEL.get() : ENCASED_CUSTOM_COGWHEEL.get(),block);

            if (e.get() instanceof EncasedCustomPipeBlock block)
                event.modify(ENCASED_CUSTOM_FLUID_PIPE.get(),block);
        });
    }

    public static void register(BlockEntityTypeAddBlocksEvent event, BlockEntityType<?> type, Function<CasingSet, Block> blockFunction, Predicate<CasingSet> validateFunction){
        CasingSets.getSets().stream().filter(set-> Objects.nonNull(blockFunction.apply(set))).filter(validateFunction).map(blockFunction).forEach(b->event.modify(type,b));
    }

    public static void registerFluidSet(BlockEntityTypeAddBlocksEvent event, BlockEntityType<?> type, Function<FluidSet, Block> blockFunction, Predicate<FluidSet> validateFunction){
        FluidSets.getSets().stream().filter(set-> Objects.nonNull(blockFunction.apply(set))).filter(validateFunction).map(blockFunction).forEach(b->event.modify(type,b));
    }

    private static void registerTransmission(BlockEntityTypeAddBlocksEvent event, BlockEntityType<?> type, Function<TransmissionSet, Block> blockFunction, Predicate<TransmissionSet> validateFunction,Function<TransmissionSet,BlockEntityType<?>> typeFunction){
        TransmissionSets.getSets().stream().filter(set-> Objects.nonNull(blockFunction.apply(set))).filter(validateFunction).forEach(b->event.modify(typeFunction.apply(b) == null ? type : typeFunction.apply(b),blockFunction.apply(b)));
    }
}
