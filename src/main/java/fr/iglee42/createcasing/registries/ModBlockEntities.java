package fr.iglee42.createcasing.registries;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.base.ShaftVisual;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
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

public class ModBlockEntities {


    public static final BlockEntityEntry<KineticBlockEntity> ENCASED_SHAFT = REGISTRATE
            .blockEntity("casing_encased_shaft", KineticBlockEntity::new)
            .visual(() -> ShaftVisual::new, false)
            .validBlocks(ModBlocks.RAILWAY_ENCASED_SHAFT,ModBlocks.COPPER_ENCASED_SHAFT,ModBlocks.SHADOW_ENCASED_SHAFT,ModBlocks.REFINED_RADIANCE_ENCASED_SHAFT,ModBlocks.INDUSTRIAL_IRON_ENCASED_SHAFT,ModBlocks.CREATIVE_ENCASED_SHAFT,ModBlocks.BRASS_CHAIN_DRIVE,ModBlocks.COPPER_CHAIN_DRIVE,ModBlocks.RAILWAY_CHAIN_DRIVE,ModBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE,ModBlocks.CREATIVE_CHAIN_DRIVE)
            .renderer(() -> ShaftRenderer::new)
            .register();
    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_COGWHEEL = REGISTRATE
            .blockEntity("casing_encased_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCogVisual::small, false)
            .validBlocks(ModBlocks.RAILWAY_ENCASED_COGWHEEL,ModBlocks.COPPER_ENCASED_COGWHEEL,ModBlocks.SHADOW_ENCASED_COGWHEEL,ModBlocks.RADIANCE_ENCASED_COGWHEEL,ModBlocks.INDUSTRIAL_IRON_ENCASED_COGWHEEL,ModBlocks.CREATIVE_ENCASED_COGWHEEL)
            .renderer(() -> EncasedCogRenderer::small)
            .register();
    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_COGWHEEL_LARGE = REGISTRATE
            .blockEntity("casing_encased_cogwheel_large", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCogVisual::large, false)
            .validBlocks(ModBlocks.RAILWAY_ENCASED_COGWHEEL_LARGE,ModBlocks.COPPER_ENCASED_COGWHEEL_LARGE,ModBlocks.SHADOW_ENCASED_COGWHEEL_LARGE,ModBlocks.RADIANCE_ENCASED_COGWHEEL_LARGE,ModBlocks.INDUSTRIAL_IRON_ENCASED_COGWHEEL_LARGE,ModBlocks.CREATIVE_ENCASED_COGWHEEL_LARGE)
            .renderer(() -> EncasedCogRenderer::large)
            .register();

    public static final BlockEntityEntry<FluidPipeBlockEntity> ENCASED_FLUID_PIPE = REGISTRATE
            .blockEntity("encased_fluid_pipe", FluidPipeBlockEntity::new)
            .validBlocks(ModBlocks.ENCASED_ANDESITE_FLUID_PIPE,ModBlocks.ENCASED_BRASS_FLUID_PIPE,ModBlocks.ENCASED_RAILWAY_FLUID_PIPE,ModBlocks.ENCASED_RADIANCE_FLUID_PIPE,ModBlocks.ENCASED_SHADOW_FLUID_PIPE,ModBlocks.ENCASED_INDUSTRIAL_IRON_FLUID_PIPE,ModBlocks.ENCASED_CREATIVE_FLUID_PIPE)
            .register();

    public static final BlockEntityEntry<GearboxBlockEntity> GEARBOX = REGISTRATE
            .blockEntity("custom_gearbox", GearboxBlockEntity::new)
            .visual(() -> GearboxVisual::new, false)
            .validBlocks(ModBlocks.BRASS_GEARBOX,ModBlocks.COPPER_GEARBOX,ModBlocks.RAILWAY_GEARBOX,ModBlocks.INDUSTRIAL_IRON_GEARBOX,ModBlocks.CREATIVE_GEARBOX)
            .renderer(() -> GearboxRenderer::new)
            .register();

    public static final BlockEntityEntry<CustomMixerBlockEntity> MIXER = REGISTRATE
            .blockEntity("custom_mixer", CustomMixerBlockEntity::new)
            .visual(() -> CustomMixerVisual::new)
            .validBlocks(ModBlocks.BRASS_MIXER,ModBlocks.COPPER_MIXER,ModBlocks.RAILWAY_MIXER,ModBlocks.INDUSTRIAL_IRON_MIXER,ModBlocks.CREATIVE_MIXER)
            .renderer(() -> CustomMixerRenderer::new)
            .register();

    public static final BlockEntityEntry<MechanicalPressBlockEntity> PRESS = REGISTRATE
            .blockEntity("custom_press", MechanicalPressBlockEntity::new)
            .visual(() -> PressVisual::new)
            .validBlocks(ModBlocks.BRASS_PRESS,ModBlocks.COPPER_PRESS,ModBlocks.RAILWAY_PRESS,ModBlocks.INDUSTRIAL_IRON_PRESS,ModBlocks.CREATIVE_PRESS)
            .renderer(() -> CustomPressRenderer::new)
            .register();

    public static final BlockEntityEntry<DepotBlockEntity> DEPOT = REGISTRATE
            .blockEntity("custom_depot", DepotBlockEntity::new)
            .validBlocks(ModBlocks.BRASS_DEPOT,ModBlocks.COPPER_DEPOT,ModBlocks.RAILWAY_DEPOT,ModBlocks.INDUSTRIAL_IRON_DEPOT,ModBlocks.CREATIVE_DEPOT)
            .renderer(() -> DepotRenderer::new)
            .register();

    public static final BlockEntityEntry<WoodenShaftBlockEntity> WOODEN_SHAFT = REGISTRATE
            .blockEntity("wooden_shaft", WoodenShaftBlockEntity::new)
            .visual(() -> WoodenShaftVisual::create, false)
            .validBlocks(ModBlocks.OAK_SHAFT,ModBlocks.SPRUCE_SHAFT,ModBlocks.BIRCH_SHAFT,ModBlocks.JUNGLE_SHAFT,ModBlocks.ACACIA_SHAFT,ModBlocks.DARK_OAK_SHAFT,ModBlocks.CRIMSON_SHAFT,ModBlocks.WARPED_SHAFT,ModBlocks.MANGROVE_SHAFT,ModBlocks.BAMBOO_SHAFT,ModBlocks.CHERRY_SHAFT)
            .renderer(() -> BracketedKineticBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<GlassShaftBlockEntity> GLASS_SHAFT = REGISTRATE
            .blockEntity("glass_shaft", GlassShaftBlockEntity::new)
            //.instance(() -> GlassShaftInstance::new, false)
            .validBlocks(ModBlocks.GLASS_SHAFT)
            .renderer(() -> GlassShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<MetalShaftBlockEntity> METAL_SHAFT = REGISTRATE
            .blockEntity("metal_shaft", MetalShaftBlockEntity::new)
            .visual(() -> (ctx,be,pt)->new SingleAxisRotatingVisual<>(ctx,be,pt,Models.partial(ModPartialModels.MLDEG_SHAFT)), false)
            .validBlocks(ModBlocks.MLDEG_SHAFT)
            .renderer(() -> BracketedKineticBlockEntityRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassShaftBlockEntity> BRASS_SHAFT = REGISTRATE
            .blockEntity("brass_shaft", BrassShaftBlockEntity::new)
            .visual(() -> (visualizationContext, be, pt) ->new SingleAxisRotatingVisual<>(visualizationContext,be,pt, Models.partial(ModPartialModels.BRASS_SHAFT)), false)
            .validBlocks(ModBlocks.BRASS_SHAFT)
            .renderer(() -> BracketedKineticBlockEntityRenderer::new)
            .register();


    public static final BlockEntityEntry<CreativeCogwheelBlockEntity> CREATIVE_COGWHEEL = REGISTRATE
            .blockEntity("creative_cogwheel", CreativeCogwheelBlockEntity::new)
            .visual(() -> CreativeCogwheelVisual::new, false)
            .validBlocks(ModBlocks.CREATIVE_COGWHEEL)
            .renderer(() -> CreativeCogwheelRenderer::new)
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
            .validBlocks(ModBlocks.BRASS_CHAIN_GEARSHIFT,ModBlocks.COPPER_CHAIN_GEARSHIFT,ModBlocks.RAILWAY_CHAIN_GEARSHIFT,ModBlocks.INDUSTRIAL_IRON_CHAIN_GEARSHIFT,ModBlocks.CREATIVE_CHAIN_GEARSHIFT)
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
