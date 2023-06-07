package fr.iglee42.createcasing;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.fluids.pipes.FluidPipeTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.SimpleKineticTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.*;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;

public class ModBlockEntities {


    public static final BlockEntityEntry<KineticBlockEntity> ENCASED_SHAFT = REGISTRATE
            .blockEntity("casing_encased_shaft", KineticBlockEntity::new)
            .instance(() -> ShaftInstance::new, false)
            .validBlocks(ModBlocks.RAILWAY_ENCASED_SHAFT,ModBlocks.COPPER_ENCASED_SHAFT,ModBlocks.SHADOW_ENCASED_SHAFT,ModBlocks.REFINED_RADIANCE_ENCASED_SHAFT)
            .renderer(() -> ShaftRenderer::new)
            .register();
    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_COGWHEEL = REGISTRATE
            .blockEntity("casing_encased_cogwheel", SimpleKineticBlockEntity::new)
            .instance(() -> EncasedCogInstance::small, false)
            .validBlocks(ModBlocks.RAILWAY_ENCASED_COGWHEEL,ModBlocks.COPPER_ENCASED_COGWHEEL,ModBlocks.SHADOW_ENCASED_COGWHEEL,ModBlocks.RADIANCE_ENCASED_COGWHEEL)
            .renderer(() -> EncasedCogRenderer::small)
            .register();
    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_COGWHEEL_LARGE = REGISTRATE
            .blockEntity("casing_encased_cogwheel_large", SimpleKineticBlockEntity::new)
            .instance(() -> EncasedCogInstance::large, false)
            .validBlocks(ModBlocks.RAILWAY_ENCASED_COGWHEEL_LARGE,ModBlocks.COPPER_ENCASED_COGWHEEL_LARGE,ModBlocks.SHADOW_ENCASED_COGWHEEL_LARGE,ModBlocks.RADIANCE_ENCASED_COGWHEEL_LARGE)
            .renderer(() -> EncasedCogRenderer::large)
            .register();

    public static final BlockEntityEntry<FluidPipeBlockEntity> ENCASED_FLUID_PIPE = REGISTRATE
            .blockEntity("encased_fluid_pipe", FluidPipeBlockEntity::new)
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
