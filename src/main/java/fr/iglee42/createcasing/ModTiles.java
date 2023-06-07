package fr.iglee42.createcasing;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.fluids.pipes.FluidPipeTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.SimpleKineticTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.*;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import fr.iglee42.createcasing.changeAcces.PublicSimpleKinecticTileEntity;

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

    public static void register() {}
}
