package fr.iglee42.createcasing.registries;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.base.ShaftVisual;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorBlockEntity;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorRenderer;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorVisual;
import com.simibubi.create.content.kinetics.chainDrive.ChainGearshiftBlockEntity;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;
import com.simibubi.create.content.kinetics.gearbox.GearboxRenderer;
import com.simibubi.create.content.kinetics.gearbox.GearboxVisual;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerRenderer;
import com.simibubi.create.content.kinetics.mixer.MixerVisual;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import com.simibubi.create.content.kinetics.press.MechanicalPressRenderer;
import com.simibubi.create.content.kinetics.press.PressVisual;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogVisual;
import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import com.simibubi.create.content.logistics.depot.DepotRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import dev.engine_room.flywheel.lib.model.Models;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.blockEntities.*;
import fr.iglee42.createcasing.blockEntities.renderers.*;
import fr.iglee42.createcasing.blockEntities.visuals.*;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.transmissions.TransmissionSet;
import fr.iglee42.createcasing.transmissions.TransmissionSets;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
            .visual(() -> (ctx,be,pt)->new SingleAxisRotatingVisual<>(ctx,be,pt,Models.partial(EncasedPartialModels.MLDEG_SHAFT)), false)
            //.validBlocks(EncasedBlocks.MLDEG_SHAFT)
            .renderer(() -> BracketedKineticBlockEntityRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassShaftBlockEntity> BRASS_SHAFT = REGISTRATE
            .blockEntity("brass_shaft", BrassShaftBlockEntity::new)
            .visual(() -> (visualizationContext, be, pt) ->new SingleAxisRotatingVisual<>(visualizationContext,be,pt, Models.partial(EncasedPartialModels.BRASS_SHAFT)), false)
            //.validBlocks(EncasedBlocks.BRASS_SHAFT)
            .renderer(() -> BracketedKineticBlockEntityRenderer::new)
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

        registerTransmission(event, CUSTOM_SHAFT.get(), TransmissionSet::getShaft, TransmissionSet::doesGenerateShaft, TransmissionSet::getShaftBlockEntityType);
        registerTransmission(event, CUSTOM_COGWHEELS.get(), TransmissionSet::getCogwheel, TransmissionSet::doesGenerateCogwheel, TransmissionSet::getCogwheelBlockEntityType);
        registerTransmission(event, CUSTOM_COGWHEELS.get(), TransmissionSet::getLargeCogwheel, TransmissionSet::doesGenerateLargeCogwheel, TransmissionSet::getLargeCogwheelBlockEntityType);
    }

    private static void register(BlockEntityTypeAddBlocksEvent event, BlockEntityType<?> type, Function<CasingSet, Block> blockFunction, Predicate<CasingSet> validateFunction){
        CasingSets.getSets().stream().filter(set-> Objects.nonNull(blockFunction.apply(set))).filter(validateFunction).map(blockFunction).forEach(b->event.modify(type,b));
    }

    private static void registerTransmission(BlockEntityTypeAddBlocksEvent event, BlockEntityType<?> type, Function<TransmissionSet, Block> blockFunction, Predicate<TransmissionSet> validateFunction,Function<TransmissionSet,BlockEntityType<?>> typeFunction){
        TransmissionSets.getSets().stream().filter(set-> Objects.nonNull(blockFunction.apply(set))).filter(validateFunction).forEach(b->event.modify(typeFunction.apply(b) == null ? type : typeFunction.apply(b),blockFunction.apply(b)));
    }
}
