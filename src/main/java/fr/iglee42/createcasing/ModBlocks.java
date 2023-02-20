package fr.iglee42.createcasing;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.contraptions.fluids.PipeAttachmentModel;
import com.simibubi.create.content.contraptions.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCTBehaviour;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.changeAcces.PublicEncasedCogWheelBlock;
import fr.iglee42.createcasing.changeAcces.PublicEncasedPipeBlock;
import fr.iglee42.createcasing.changeAcces.PublicEncasedShaftBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,CreateCasing.MODID);



    //public static final RegistryObject<Block> RAILWAY_ENCASED_SHAFT = BLOCKS.register("railway_encased_shaft", ()->new PublicEncasedShaftBlock(BlockBehaviour.Properties.copy(AllBlocks.ANDESITE_ENCASED_SHAFT.get()),AllBlocks.RAILWAY_CASING));
    //public static final RegistryObject<Block> COPPER_ENCASED_SHAFT = BLOCKS.register("copper_encased_shaft", ()->new PublicEncasedShaftBlock(BlockBehaviour.Properties.copy(AllBlocks.ANDESITE_ENCASED_SHAFT.get()),AllBlocks.COPPER_CASING));

    public static final BlockEntry<PublicEncasedShaftBlock> RAILWAY_ENCASED_SHAFT =
            REGISTRATE.block("railway_encased_shaft", (p)->new PublicEncasedShaftBlock(p,AllBlocks.RAILWAY_CASING))
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
                    .transform(BuilderTransformers.encasedShaft("railway", () -> AllSpriteShifts.RAILWAY_CASING))
                    .transform(axeOrPickaxe())
                    .register();
    public static final BlockEntry<PublicEncasedShaftBlock> COPPER_ENCASED_SHAFT =
            REGISTRATE.block("copper_encased_shaft", (p)->new PublicEncasedShaftBlock(p,AllBlocks.COPPER_CASING))
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
                    .transform(BuilderTransformers.encasedShaft("copper", () -> AllSpriteShifts.COPPER_CASING))
                    .transform(axeOrPickaxe())
                    .register();
    public static final BlockEntry<PublicEncasedCogWheelBlock> RAILWAY_ENCASED_COGWHEEL = REGISTRATE
            .block("railway_encased_cogwheel", p -> new PublicEncasedCogWheelBlock(false, p,AllBlocks.RAILWAY_CASING))
            .properties(p -> p.color(MaterialColor.PODZOL))
            .transform(BuilderTransformers.encasedCogwheel("railway", () -> AllSpriteShifts.RAILWAY_CASING))
            .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(AllSpriteShifts.RAILWAY_CASING,
                    Couple.create(ModSprites.RAILWAY_ENCASED_COGWHEEL_SIDE,
                            ModSprites.RAILWAY_ENCASED_COGWHEEL_OTHERSIDE))))
            .transform(axeOrPickaxe())
            .register();
    public static final BlockEntry<PublicEncasedCogWheelBlock> COPPER_ENCASED_COGWHEEL = REGISTRATE
            .block("copper_encased_cogwheel", p -> new PublicEncasedCogWheelBlock(false, p,AllBlocks.COPPER_CASING))
            .properties(p -> p.color(MaterialColor.PODZOL))
            .transform(BuilderTransformers.encasedCogwheel("copper", () -> AllSpriteShifts.COPPER_CASING))
            .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(AllSpriteShifts.COPPER_CASING,
                    Couple.create(ModSprites.COPPER_ENCASED_COGWHEEL_SIDE,
                            ModSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE))))
            .transform(axeOrPickaxe())
            .register();

    public static final BlockEntry<PublicEncasedCogWheelBlock> RAILWAY_ENCASED_COGWHEEL_LARGE = REGISTRATE
            .block("railway_encased_large_cogwheel", p -> new PublicEncasedCogWheelBlock(true, p,AllBlocks.RAILWAY_CASING))
            .properties(p -> p.color(MaterialColor.PODZOL))
            .transform(BuilderTransformers.encasedLargeCogwheel("railway", () -> AllSpriteShifts.RAILWAY_CASING))
            .transform(axeOrPickaxe())
            .register();
    public static final BlockEntry<PublicEncasedCogWheelBlock> COPPER_ENCASED_COGWHEEL_LARGE = REGISTRATE
            .block("copper_encased_large_cogwheel", p -> new PublicEncasedCogWheelBlock(true, p,AllBlocks.COPPER_CASING))
            .properties(p -> p.color(MaterialColor.PODZOL))
            .transform(BuilderTransformers.encasedLargeCogwheel("copper", () -> AllSpriteShifts.COPPER_CASING))
            .transform(axeOrPickaxe())
            .register();

    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_ANDESITE_FLUID_PIPE =
            REGISTRATE.block("andesite_encased_fluid_pipe", PublicEncasedPipeBlock::new)
                    .initialProperties(SharedProperties::copperMetal)
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(axeOrPickaxe())
                    .blockstate(BlockStateGen.encasedPipe())
                    .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShifts.ANDESITE_CASING)))
                    .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, AllSpriteShifts.ANDESITE_CASING,
                            (s, f) -> !s.getValue(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(f)))))
                    .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::new))
                    .loot((p, b) -> p.dropOther(b, AllBlocks.FLUID_PIPE.get()))
                    .register();
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_BRASS_FLUID_PIPE =
            REGISTRATE.block("brass_encased_fluid_pipe", PublicEncasedPipeBlock::new)
                    .initialProperties(SharedProperties::copperMetal)
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(axeOrPickaxe())
                    .blockstate(BlockStateGen.encasedPipe())
                    .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShifts.BRASS_CASING)))
                    .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, AllSpriteShifts.BRASS_CASING,
                            (s, f) -> !s.getValue(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(f)))))
                    .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::new))
                    .loot((p, b) -> p.dropOther(b, AllBlocks.FLUID_PIPE.get()))
                    .register();
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_RAILWAY_FLUID_PIPE =
            REGISTRATE.block("railway_encased_fluid_pipe", PublicEncasedPipeBlock::new)
                    .initialProperties(SharedProperties::copperMetal)
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(axeOrPickaxe())
                    .blockstate(BlockStateGen.encasedPipe())
                    .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShifts.RAILWAY_CASING)))
                    .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, AllSpriteShifts.RAILWAY_CASING,
                            (s, f) -> !s.getValue(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(f)))))
                    .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::new))
                    .loot((p, b) -> p.dropOther(b, AllBlocks.FLUID_PIPE.get()))
                    .register();


    public static void register() {}

}
