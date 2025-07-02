package fr.iglee42.createcasing.registries;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.base.ShaftVisual;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorBlockEntity;
import com.simibubi.create.content.kinetics.chainDrive.ChainGearshiftBlockEntity;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;
import com.simibubi.create.content.kinetics.gearbox.GearboxRenderer;
import com.simibubi.create.content.kinetics.gearbox.GearboxVisual;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerRenderer;
import com.simibubi.create.content.kinetics.mixer.MixerVisual;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
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
import fr.iglee42.createcasing.api.instances.ApiCogwheelBlockEntityVisual;
import fr.iglee42.createcasing.api.renderers.ApiCogwheelBlockEntityRenderer;
import fr.iglee42.createcasing.blockEntities.*;
import fr.iglee42.createcasing.blockEntities.renderers.*;
import fr.iglee42.createcasing.blockEntities.visuals.*;

import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;

public class EncasedBlockEntities {


    public static final BlockEntityEntry<KineticBlockEntity> ENCASED_SHAFT = REGISTRATE
            .blockEntity("casing_encased_shaft", KineticBlockEntity::new)
            .visual(() -> ShaftVisual::new, false)
            .validBlocks(EncasedBlocks.RAILWAY_ENCASED_SHAFT, EncasedBlocks.COPPER_ENCASED_SHAFT, EncasedBlocks.SHADOW_STEEL_ENCASED_SHAFT, EncasedBlocks.REFINED_RADIANCE_ENCASED_SHAFT, EncasedBlocks.INDUSTRIAL_IRON_ENCASED_SHAFT, EncasedBlocks.CREATIVE_ENCASED_SHAFT, EncasedBlocks.BRASS_CHAIN_DRIVE, EncasedBlocks.COPPER_CHAIN_DRIVE, EncasedBlocks.RAILWAY_CHAIN_DRIVE, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE, EncasedBlocks.CREATIVE_CHAIN_DRIVE, EncasedBlocks.WEATHERED_IRON_ENCASED_SHAFT, EncasedBlocks.WEATHERED_IRON_CHAIN_DRIVE, EncasedBlocks.REFINED_RADIANCE_CHAIN_DRIVE, EncasedBlocks.SHADOW_STEEL_CHAIN_DRIVE)
            .renderer(() -> ShaftRenderer::new)
            .register();
    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_COGWHEEL = REGISTRATE
            .blockEntity("casing_encased_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCogVisual::small, false)
            .validBlocks(EncasedBlocks.RAILWAY_ENCASED_COGWHEEL, EncasedBlocks.COPPER_ENCASED_COGWHEEL, EncasedBlocks.SHADOW_STEEL_ENCASED_COGWHEEL, EncasedBlocks.REFINED_RADIANCE_ENCASED_COGWHEEL, EncasedBlocks.INDUSTRIAL_IRON_ENCASED_COGWHEEL, EncasedBlocks.CREATIVE_ENCASED_COGWHEEL, EncasedBlocks.WEATHERED_IRON_ENCASED_COGWHEEL)
            .renderer(() -> EncasedCogRenderer::small)
            .register();
    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_COGWHEEL_LARGE = REGISTRATE
            .blockEntity("casing_encased_cogwheel_large", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCogVisual::large, false)
            .validBlocks(EncasedBlocks.RAILWAY_ENCASED_COGWHEEL_LARGE, EncasedBlocks.COPPER_ENCASED_COGWHEEL_LARGE, EncasedBlocks.SHADOW_STEEL_ENCASED_COGWHEEL_LARGE, EncasedBlocks.REFINED_RADIANCE_ENCASED_COGWHEEL_LARGE, EncasedBlocks.INDUSTRIAL_IRON_ENCASED_COGWHEEL_LARGE, EncasedBlocks.CREATIVE_ENCASED_COGWHEEL_LARGE, EncasedBlocks.WEATHERED_IRON_ENCASED_COGWHEEL_LARGE)
            .renderer(() -> EncasedCogRenderer::large)
            .register();

    public static final BlockEntityEntry<FluidPipeBlockEntity> ENCASED_FLUID_PIPE = REGISTRATE
            .blockEntity("encased_fluid_pipe", FluidPipeBlockEntity::new)
            .validBlocks(EncasedBlocks.ENCASED_ANDESITE_FLUID_PIPE, EncasedBlocks.ENCASED_BRASS_FLUID_PIPE, EncasedBlocks.ENCASED_RAILWAY_FLUID_PIPE, EncasedBlocks.ENCASED_REFINED_RADIANCE_FLUID_PIPE, EncasedBlocks.ENCASED_SHADOW_STEEL_FLUID_PIPE, EncasedBlocks.ENCASED_INDUSTRIAL_IRON_FLUID_PIPE, EncasedBlocks.ENCASED_CREATIVE_FLUID_PIPE, EncasedBlocks.ENCASED_WEATHERED_IRON_FLUID_PIPE)
            .register();

    public static final BlockEntityEntry<GearboxBlockEntity> GEARBOX = REGISTRATE
            .blockEntity("custom_gearbox", GearboxBlockEntity::new)
            .visual(() -> GearboxVisual::new, false)
            .validBlocks(EncasedBlocks.BRASS_GEARBOX, EncasedBlocks.COPPER_GEARBOX, EncasedBlocks.RAILWAY_GEARBOX, EncasedBlocks.INDUSTRIAL_IRON_GEARBOX, EncasedBlocks.CREATIVE_GEARBOX, EncasedBlocks.WEATHERED_IRON_GEARBOX, EncasedBlocks.REFINED_RADIANCE_GEARBOX, EncasedBlocks.SHADOW_STEEL_GEARBOX)
            .renderer(() -> GearboxRenderer::new)
            .register();

    public static final BlockEntityEntry<MechanicalMixerBlockEntity> MIXER = REGISTRATE
            .blockEntity("custom_mixer", MechanicalMixerBlockEntity::new)
            .visual(() -> CustomMixerVisual::new)
            .validBlocks(EncasedBlocks.BRASS_MIXER, EncasedBlocks.COPPER_MIXER, EncasedBlocks.RAILWAY_MIXER, EncasedBlocks.INDUSTRIAL_IRON_MIXER, EncasedBlocks.CREATIVE_MIXER, EncasedBlocks.WEATHERED_IRON_MIXER, EncasedBlocks.REFINED_RADIANCE_MIXER, EncasedBlocks.SHADOW_STEEL_MIXER)
            .renderer(() -> CustomMixerRenderer::new)
            .register();

    public static final BlockEntityEntry<MechanicalPressBlockEntity> PRESS = REGISTRATE
            .blockEntity("custom_press", MechanicalPressBlockEntity::new)
            .visual(() -> PressVisual::new)
            .validBlocks(EncasedBlocks.BRASS_PRESS, EncasedBlocks.COPPER_PRESS, EncasedBlocks.RAILWAY_PRESS, EncasedBlocks.INDUSTRIAL_IRON_PRESS, EncasedBlocks.CREATIVE_PRESS, EncasedBlocks.WEATHERED_IRON_PRESS, EncasedBlocks.REFINED_RADIANCE_PRESS, EncasedBlocks.SHADOW_STEEL_PRESS)
            .renderer(() -> CustomPressRenderer::new)
            .register();

    public static final BlockEntityEntry<DepotBlockEntity> DEPOT = REGISTRATE
            .blockEntity("custom_depot", DepotBlockEntity::new)
            .validBlocks(EncasedBlocks.BRASS_DEPOT, EncasedBlocks.COPPER_DEPOT, EncasedBlocks.RAILWAY_DEPOT, EncasedBlocks.INDUSTRIAL_IRON_DEPOT, EncasedBlocks.CREATIVE_DEPOT, EncasedBlocks.WEATHERED_IRON_DEPOT, EncasedBlocks.REFINED_RADIANCE_DEPOT, EncasedBlocks.SHADOW_STEEL_DEPOT)
            .renderer(() -> DepotRenderer::new)
            .register();

    public static final BlockEntityEntry<WoodenShaftBlockEntity> WOODEN_SHAFT = REGISTRATE
            .blockEntity("wooden_shaft", WoodenShaftBlockEntity::new)
            .visual(() -> WoodenShaftVisual::create, false)
            .validBlocks(EncasedBlocks.OAK_SHAFT, EncasedBlocks.SPRUCE_SHAFT, EncasedBlocks.BIRCH_SHAFT, EncasedBlocks.JUNGLE_SHAFT, EncasedBlocks.ACACIA_SHAFT, EncasedBlocks.DARK_OAK_SHAFT, EncasedBlocks.CRIMSON_SHAFT, EncasedBlocks.WARPED_SHAFT, EncasedBlocks.MANGROVE_SHAFT, EncasedBlocks.BAMBOO_SHAFT, EncasedBlocks.CHERRY_SHAFT)
            .renderer(() -> BracketedKineticBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<GlassShaftBlockEntity> GLASS_SHAFT = REGISTRATE
            .blockEntity("glass_shaft", GlassShaftBlockEntity::new)
            .visual(() -> (ctx,be,pt)->new SingleAxisRotatingVisual<>(ctx,be,pt,Models.partial(EncasedPartialModels.GLASS_SHAFT)), false)
            .validBlocks(EncasedBlocks.GLASS_SHAFT)
            .renderer(() -> BracketedKineticBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<MetalShaftBlockEntity> METAL_SHAFT = REGISTRATE
            .blockEntity("metal_shaft", MetalShaftBlockEntity::new)
            .visual(() -> (ctx,be,pt)->new SingleAxisRotatingVisual<>(ctx,be,pt,Models.partial(EncasedPartialModels.MLDEG_SHAFT)), false)
            .validBlocks(EncasedBlocks.MLDEG_SHAFT)
            .renderer(() -> BracketedKineticBlockEntityRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassShaftBlockEntity> BRASS_SHAFT = REGISTRATE
            .blockEntity("brass_shaft", BrassShaftBlockEntity::new)
            .visual(() -> (visualizationContext, be, pt) ->new SingleAxisRotatingVisual<>(visualizationContext,be,pt, Models.partial(EncasedPartialModels.BRASS_SHAFT)), false)
            .validBlocks(EncasedBlocks.BRASS_SHAFT)
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
            .validBlocks(EncasedBlocks.ANDESITE_CONFIGURABLE_GEARBOX, EncasedBlocks.BRASS_CONFIGURABLE_GEARBOX, EncasedBlocks.COPPER_CONFIGURABLE_GEARBOX, EncasedBlocks.RAILWAY_CONFIGURABLE_GEARBOX, EncasedBlocks.CREATIVE_CONFIGURABLE_GEARBOX, EncasedBlocks.INDUSTRIAL_IRON_CONFIGURABLE_GEARBOX, EncasedBlocks.WEATHERED_IRON_CONFIGURABLE_GEARBOX, EncasedBlocks.REFINED_RADIANCE_CONFIGURABLE_GEARBOX, EncasedBlocks.SHADOW_STEEL_CONFIGURABLE_GEARBOX)
            .renderer(() -> ConfigurableGearboxRenderer::new)
            .register();

    public static final BlockEntityEntry<CustomEncasedShaftBlockEntity> CUSTOM_ENCASED_SHAFT = REGISTRATE
            .blockEntity("custom_encased_shaft", CustomEncasedShaftBlockEntity::new)
            .visual(() -> CustomEncasedShaftVisual::new, false)
            .validBlocks()
            .renderer(() -> CustomEncasedShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<ChainGearshiftBlockEntity> CUSTOM_CHAIN_GEARSHIFT = REGISTRATE
            .blockEntity("custom_chain_gearshift", ChainGearshiftBlockEntity::new)
            .visual(() -> ShaftVisual::new, false)
            .validBlocks(EncasedBlocks.BRASS_CHAIN_GEARSHIFT, EncasedBlocks.COPPER_CHAIN_GEARSHIFT, EncasedBlocks.RAILWAY_CHAIN_GEARSHIFT, EncasedBlocks.WEATHERED_IRON_CHAIN_GEARSHIFT, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_GEARSHIFT, EncasedBlocks.CREATIVE_CHAIN_GEARSHIFT, EncasedBlocks.REFINED_RADIANCE_CHAIN_GEARSHIFT, EncasedBlocks.SHADOW_STEEL_CHAIN_GEARSHIFT)
            .renderer(() -> ShaftRenderer::new)
            .register();
    public static final BlockEntityEntry<BracketedKineticBlockEntity> WOODEN_COGWHEELS = REGISTRATE
            .blockEntity("wooden_cogwheels", BracketedKineticBlockEntity::new)
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

    public static final BlockEntityEntry<ChainConveyorBlockEntity> CHAIN_CONVEYOR = REGISTRATE
            .blockEntity("custom_chain_conveyor", ChainConveyorBlockEntity::new)
            .visual(() -> CustomChainConveyorVisual::new)
            .validBlocks(EncasedBlocks.BRASS_CHAIN_CONVEYOR, EncasedBlocks.COPPER_CHAIN_CONVEYOR, EncasedBlocks.RAILWAY_CHAIN_CONVEYOR, EncasedBlocks.CREATIVE_CHAIN_CONVEYOR, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_CONVEYOR, EncasedBlocks.WEATHERED_IRON_CHAIN_CONVEYOR, EncasedBlocks.REFINED_RADIANCE_CHAIN_CONVEYOR, EncasedBlocks.SHADOW_STEEL_CHAIN_CONVEYOR)
            .renderer(() -> CustomChainConveyorRenderer::new)
            .register();




    //API


    public static final BlockEntityEntry<GearboxBlockEntity> API_GEARBOX = REGISTRATE
            .blockEntity("api_gearbox", GearboxBlockEntity::new)
            .visual(() -> GearboxVisual::new, false)
            .validBlocks()
            .renderer(() -> GearboxRenderer::new)
            .register();

    public static final BlockEntityEntry<DepotBlockEntity> API_DEPOT = REGISTRATE
            .blockEntity("api_depot", DepotBlockEntity::new)
            .validBlocks()
            .renderer(() -> DepotRenderer::new)
            .register();

    public static final BlockEntityEntry<MechanicalMixerBlockEntity> API_MIXER = REGISTRATE
            .blockEntity("api_mixer", MechanicalMixerBlockEntity::new)
            .visual(() -> MixerVisual::new)
            .validBlocks()
            .renderer(() -> MechanicalMixerRenderer::new)
            .register();

    public static final BlockEntityEntry<MechanicalPressBlockEntity> API_PRESS = REGISTRATE
            .blockEntity("api_press", MechanicalPressBlockEntity::new)
            .visual(() -> PressVisual::new)
            .validBlocks()
            .renderer(() -> CustomPressRenderer::new)
            .register();


    public static final BlockEntityEntry<BracketedKineticBlockEntity> API_COGWHEEL = REGISTRATE
            .blockEntity("api_cogwheel", BracketedKineticBlockEntity::new)
            .visual(() -> ApiCogwheelBlockEntityVisual::create, false)
            .validBlocks()
            .renderer(() -> ApiCogwheelBlockEntityRenderer::new)
            .register();

    public static void register() {}
}
