package fr.iglee42.createcasing.compatibility.createcrystalclear;

import com.cyvack.create_crystal_clear.index.CCBlocks;
import com.cyvack.create_crystal_clear.index.CCSpriteShifts;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.content.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.changeAcces.PublicEncasedCogwheelBlock;
import fr.iglee42.createcasing.changeAcces.PublicEncasedPipeBlock;
import fr.iglee42.createcasing.changeAcces.PublicEncasedShaftBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;


public class CreateCrystalClearCompatibility {

    public static final BlockEntry<PublicEncasedShaftBlock> COPPER_GLASS_ENCASED_SHAFT = glassEncasedShaft("copper", false, () -> CCBlocks.GLASS_CASINGS.getCasing("copper"));
    public static final BlockEntry<PublicEncasedShaftBlock> COPPER_CLEAR_GLASS_ENCASED_SHAFT = glassEncasedShaft("copper", true, () -> CCBlocks.CLEAR_GLASS.getCasing("copper"));

    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_GLASS_ENCASED_COGWHEEL = glassEncasedCogwheel("copper", false, false, () -> CCBlocks.GLASS_CASINGS.getCasing("copper"));
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_CLEAR_GLASS_ENCASED_COGWHEEL = glassEncasedCogwheel("copper", false, true, () -> CCBlocks.CLEAR_GLASS.getCasing("copper"));
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_GLASS_ENCASED_LARGE_COGWHEEL = glassEncasedCogwheel("copper", true, false, () -> CCBlocks.GLASS_CASINGS.getCasing("copper"));
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_CLEAR_GLASS_ENCASED_LARGE_COGWHEEL = glassEncasedCogwheel("copper", true, true, () -> CCBlocks.CLEAR_GLASS.getCasing("copper"));

    //public static final BlockEntry<PublicEncasedPipeBlock> ANDESITE_GLASS_ENCASED_PIPE = createPipe("andesite", false, () -> CCBlocks.GLASS_CASINGS.getCasing("andesite"));
    //public static final BlockEntry<PublicEncasedPipeBlock> ANDESITE_CLEAR_GLASS_ENCASED_PIPE = createPipe("andesite", true, () -> CCBlocks.CLEAR_GLASS.getCasing("andesite"));
    //public static final BlockEntry<PublicEncasedPipeBlock> BRASS_GLASS_ENCASED_PIPE = createPipe("brass", false, () -> CCBlocks.GLASS_CASINGS.getCasing("brass"));
    //public static final BlockEntry<PublicEncasedPipeBlock> BRASS_CLEAR_GLASS_ENCASED_PIPE = createPipe("brass", true, () -> CCBlocks.CLEAR_GLASS.getCasing("brass"));
    //public static final BlockEntry<PublicEncasedPipeBlock> TRAIN_GLASS_ENCASED_PIPE = createPipe("railway", false, () -> CCBlocks.GLASS_CASINGS.getCasing("train"));
    //public static final BlockEntry<PublicEncasedPipeBlock> TRAIN_CLEAR_GLASS_ENCASED_PIPE = createPipe("railway", true, () -> CCBlocks.CLEAR_GLASS.getCasing("train"));
    //public static final BlockEntry<PublicEncasedPipeBlock> COPPER_GLASS_ENCASED_PIPE = createPipe("copper", false, () -> CCBlocks.GLASS_CASINGS.getCasing("copper"));
    //public static final BlockEntry<PublicEncasedPipeBlock> COPPER_CLEAR_GLASS_ENCASED_PIPE = createPipe("copper", true, () -> CCBlocks.CLEAR_GLASS.getCasing("copper"));


    public static void register() {
    }

    public static BlockEntry<PublicEncasedShaftBlock> glassEncasedShaft(String casingType, Boolean clear, Supplier<Block> casing) {
        String name = clear ? casingType + "_clear" : casingType;
        return REGISTRATE.block(name + "_glass_encased_shaft", p -> new PublicEncasedShaftBlock(p, casing))
                .properties(p -> BlockBehaviour.Properties.copy(Blocks.GLASS))
                .transform(BuilderTransformers.encasedShaft(name + "_glass_casing", () -> CCSpriteShifts.omni(name + "_glass_casing")))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
                .transform(axeOrPickaxe())
                .addLayer(() -> RenderType::cutout)
                .register();
    }

    public static BlockEntry<PublicEncasedCogwheelBlock> glassEncasedCogwheel(String casingType, Boolean large, Boolean clear, Supplier<Block> casing) {
        String name = clear ? casingType + "_clear" : casingType;
        return !large ?
                //small cog
                REGISTRATE.block(name + "_glass_encased_cogwheel", p -> new PublicEncasedCogwheelBlock(p, false, casing))
                        .properties(p -> BlockBehaviour.Properties.copy(Blocks.GLASS))
                        .transform(BuilderTransformers.encasedCogwheel(name + "_glass_casing", () -> CCSpriteShifts.omni(name + "_glass_casing")))
                        .transform(EncasingRegistry.addVariantTo(AllBlocks.COGWHEEL))
                        .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(CCSpriteShifts.omni(name + "_glass_casing"),
                                Couple.create(CCSpriteShifts.vertical(name + "_glass_casing"),
                                        CCSpriteShifts.horizontal(name + "_glass_casing")))))
                        .transform(axeOrPickaxe())
                        .addLayer(() -> RenderType::cutout)
                        .register() :
                //Large Cog
                REGISTRATE.block(name + "_glass_encased_large_cogwheel", p -> new PublicEncasedCogwheelBlock(p, true, casing))
                        .properties(p -> BlockBehaviour.Properties.copy(Blocks.GLASS))
                        .transform(BuilderTransformers.encasedLargeCogwheel(name + "_glass_casing", () -> CCSpriteShifts.omni(name + "_glass_casing")))
                        .transform(EncasingRegistry.addVariantTo(AllBlocks.LARGE_COGWHEEL))
                        .transform(axeOrPickaxe())
                        .addLayer(() -> RenderType::cutout)
                        .register();
    }

    public static BlockEntry<PublicEncasedPipeBlock> createPipe(String type, boolean clear, Supplier<Block> casing) {
        String name = clear ? type + "_clear" : type;
        return REGISTRATE.block(name + "_glass_encased_fluid_pipe", p -> new PublicEncasedPipeBlock(p, casing))
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(axeOrPickaxe())
                .blockstate(BlockStateGen.encasedPipe())
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(CCSpriteShifts.omni(name + "_glass_casing"))))
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, CCSpriteShifts.omni(name + "_glass_casing"),
                        (s, f) -> !s.getValue(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(f)))))
                .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::new))
                .loot((p, b) -> p.dropOther(b, AllBlocks.FLUID_PIPE.get()))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.FLUID_PIPE))
                .addLayer(() -> RenderType::cutout)
                .register();
    }

}
