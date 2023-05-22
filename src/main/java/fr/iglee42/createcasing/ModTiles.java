package fr.iglee42.createcasing;

import com.simibubi.create.Create;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.components.mixer.MechanicalMixerRenderer;
import com.simibubi.create.content.contraptions.fluids.pipes.FluidPipeTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.*;
import com.simibubi.create.content.contraptions.relays.gearbox.GearboxInstance;
import com.simibubi.create.content.contraptions.relays.gearbox.GearboxRenderer;
import com.simibubi.create.content.contraptions.relays.gearbox.GearboxTileEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import fr.iglee42.createcasing.changeAcces.PublicSimpleKinecticTileEntity;
import fr.iglee42.createcasing.tiles.CustomMixerTileEntity;
import fr.iglee42.createcasing.tiles.instances.CustomMixerInstance;
import fr.iglee42.createcasing.tiles.renderers.CustomMixerRenderer;

import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;

public class ModTiles {


    public static final BlockEntityEntry<KineticTileEntity> ENCASED_SHAFT = REGISTRATE
            .tileEntity("casing_encased_shaft", KineticTileEntity::new)
            .instance(() -> ShaftInstance::new, false)
            .validBlocks(ModBlocks.RAILWAY_ENCASED_SHAFT,ModBlocks.COPPER_ENCASED_SHAFT,ModBlocks.SHADOW_ENCASED_SHAFT,ModBlocks.REFINED_RADIANCE_ENCASED_SHAFT)
            .renderer(() -> ShaftRenderer::new)
            .register();
    public static final BlockEntityEntry<PublicSimpleKinecticTileEntity> ENCASED_COGWHEEL = REGISTRATE
            .tileEntity("casing_encased_cogwheel", PublicSimpleKinecticTileEntity::new)
            .instance(() -> EncasedCogInstance::small, false)
            .validBlocks(ModBlocks.RAILWAY_ENCASED_COGWHEEL,ModBlocks.COPPER_ENCASED_COGWHEEL,ModBlocks.SHADOW_ENCASED_COGWHEEL,ModBlocks.RADIANCE_ENCASED_COGWHEEL)
            .renderer(() -> EncasedCogRenderer::small)
            .register();
    public static final BlockEntityEntry<PublicSimpleKinecticTileEntity> ENCASED_COGWHEEL_LARGE = REGISTRATE
            .tileEntity("casing_encased_cogwheel_large", PublicSimpleKinecticTileEntity::new)
            .instance(() -> EncasedCogInstance::large, false)
            .validBlocks(ModBlocks.RAILWAY_ENCASED_COGWHEEL_LARGE,ModBlocks.COPPER_ENCASED_COGWHEEL_LARGE,ModBlocks.SHADOW_ENCASED_COGWHEEL_LARGE,ModBlocks.RADIANCE_ENCASED_COGWHEEL_LARGE)
            .renderer(() -> EncasedCogRenderer::large)
            .register();

    public static final BlockEntityEntry<FluidPipeTileEntity> ENCASED_FLUID_PIPE = REGISTRATE
            .tileEntity("encased_fluid_pipe", FluidPipeTileEntity::new)
            .validBlocks(ModBlocks.ENCASED_ANDESITE_FLUID_PIPE,ModBlocks.ENCASED_BRASS_FLUID_PIPE,ModBlocks.ENCASED_RAILWAY_FLUID_PIPE,ModBlocks.ENCASED_RADIANCE_FLUID_PIPE,ModBlocks.ENCASED_SHADOW_FLUID_PIPE)
            .register();

    public static final BlockEntityEntry<GearboxTileEntity> GEARBOX = Create.REGISTRATE
            .tileEntity("custom_gearbox", GearboxTileEntity::new)
            .instance(() -> GearboxInstance::new, false)
            .validBlocks(ModBlocks.BRASS_GEARBOX,ModBlocks.COPPER_GEARBOX,ModBlocks.RAILWAY_GEARBOX)
            .renderer(() -> GearboxRenderer::new)
            .register();

    public static final BlockEntityEntry<CustomMixerTileEntity> MIXER = Create.REGISTRATE
            .tileEntity("custom_mixer", CustomMixerTileEntity::new)
            .instance(() -> CustomMixerInstance::new)
            .validBlocks(ModBlocks.BRASS_MIXER,ModBlocks.COPPER_MIXER,ModBlocks.RAILWAY_MIXER)
            .renderer(() -> CustomMixerRenderer::new)
            .register();

    public static void register() {}
}
