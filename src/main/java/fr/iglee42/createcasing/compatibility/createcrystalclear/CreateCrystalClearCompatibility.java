package fr.iglee42.createcasing.compatibility.createcrystalclear;

import com.cyvack.create_crystal_clear.blocks.ModBlocks;
import com.cyvack.create_crystal_clear.blocks.ModSpriteShifts;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.content.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.ModSprites;
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

    public static final BlockEntry<PublicEncasedShaftBlock> COPPER_GLASS_ENCASED_SHAFT = glassEncasedShaft("copper",false, ModBlocks.COPPER_GLASS_CASING::get ,ModSpriteShifts.COPPER_GLASS_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> COPPER_CLEAR_GLASS_ENCASED_SHAFT = glassEncasedShaft("copper",true, ModBlocks.COPPER_CLEAR_GLASS_CASING::get ,ModSpriteShifts.COPPER_CLEAR_GLASS_CASING);

    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_GLASS_ENCASED_COGWHEEL = glassEncasedCogwheel("copper",false,false, ModBlocks.COPPER_GLASS_CASING::get,ModSpriteShifts.COPPER_GLASS_CASING, ModSprites.COPPER_ENCASED_COGWHEEL_SIDE, ModSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_CLEAR_GLASS_ENCASED_COGWHEEL = glassEncasedCogwheel("copper",false,true, ModBlocks.COPPER_CLEAR_GLASS_CASING::get, ModSpriteShifts.COPPER_CLEAR_GLASS_CASING, ModSprites.COPPER_ENCASED_COGWHEEL_SIDE, ModSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_GLASS_ENCASED_LARGE_COGWHEEL = glassEncasedCogwheel("copper",true,false, ModBlocks.COPPER_GLASS_CASING::get,ModSpriteShifts.COPPER_GLASS_CASING, ModSprites.COPPER_ENCASED_COGWHEEL_SIDE, ModSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_CLEAR_GLASS_ENCASED_LARGE_COGWHEEL = glassEncasedCogwheel("copper",true,true, ModBlocks.COPPER_CLEAR_GLASS_CASING::get, ModSpriteShifts.COPPER_CLEAR_GLASS_CASING, ModSprites.COPPER_ENCASED_COGWHEEL_SIDE, ModSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE);

    public static final BlockEntry<PublicEncasedPipeBlock> ANDESITE_GLASS_ENCASED_PIPE = createPipe("andesite",false,ModBlocks.ANDESITE_GLASS_CASING::get,ModSpriteShifts.ANDESITE_GLASS_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ANDESITE_CLEAR_GLASS_ENCASED_PIPE = createPipe("andesite",true,ModBlocks.ANDESITE_CLEAR_GLASS_CASING::get,ModSpriteShifts.ANDESITE_CLEAR_GLASS_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> BRASS_GLASS_ENCASED_PIPE = createPipe("brass",false,ModBlocks.BRASS_GLASS_CASING::get,ModSpriteShifts.BRASS_GLASS_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> BRASS_CLEAR_GLASS_ENCASED_PIPE = createPipe("brass",true,ModBlocks.BRASS_CLEAR_GLASS_CASING::get,ModSpriteShifts.BRASS_CLEAR_GLASS_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> TRAIN_GLASS_ENCASED_PIPE = createPipe("railway",false,ModBlocks.TRAIN_GLASS_CASING::get,ModSpriteShifts.TRAIN_GLASS_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> TRAIN_CLEAR_GLASS_ENCASED_PIPE = createPipe("railway",true,ModBlocks.TRAIN_CLEAR_GLASS_CASING::get,ModSpriteShifts.TRAIN_CLEAR_GLASS_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> COPPER_GLASS_ENCASED_PIPE = createPipe("copper",false,ModBlocks.COPPER_GLASS_CASING::get,ModSpriteShifts.COPPER_GLASS_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> COPPER_CLEAR_GLASS_ENCASED_PIPE = createPipe("copper",true,ModBlocks.COPPER_CLEAR_GLASS_CASING::get,ModSpriteShifts.COPPER_CLEAR_GLASS_CASING);




    

    public static void register() {}

    public static BlockEntry<PublicEncasedShaftBlock> glassEncasedShaft(String casingType, Boolean clear, Supplier<Block> casing,CTSpriteShiftEntry sprite) {
        String name = clear ? casingType + "_clear" : casingType;
        return REGISTRATE.block(name+"_glass_encased_shaft", p -> new PublicEncasedShaftBlock(p, casing))
                .properties(p -> BlockBehaviour.Properties.copy(Blocks.GLASS))
                .transform(BuilderTransformers.encasedShaft(name+"_glass_casing", () -> sprite))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
                .transform(axeOrPickaxe())
                .addLayer(() -> RenderType::cutout)
                .register();
    }

    public static BlockEntry<PublicEncasedCogwheelBlock> glassEncasedCogwheel(String casingType, Boolean large, Boolean clear,Supplier<Block> casing,CTSpriteShiftEntry sprite,CTSpriteShiftEntry sideSprite, CTSpriteShiftEntry otherSideSprite){
        String name = clear? casingType+"_clear" : casingType;
        return !large?
                //small cog
                REGISTRATE.block(name+"_glass_encased_cogwheel", p -> new PublicEncasedCogwheelBlock(p, false, casing))
                        .properties(p -> BlockBehaviour.Properties.copy(Blocks.GLASS))
                        .transform(BuilderTransformers.encasedCogwheel(name+"_glass_casing", () -> sprite))
                        .transform(EncasingRegistry.addVariantTo(AllBlocks.COGWHEEL))
                        .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(sprite,
                                Couple.create(sideSprite,
                                        otherSideSprite))))
                        .transform(axeOrPickaxe())
                        .addLayer(()->RenderType::cutout)
                        .register() :
                //Large Cog
                REGISTRATE.block(name+"_glass_encased_large_cogwheel", p -> new PublicEncasedCogwheelBlock(p, true, casing))
                        .properties(p -> BlockBehaviour.Properties.copy(Blocks.GLASS))
                        .transform(BuilderTransformers.encasedLargeCogwheel(name+"_glass_casing", () -> sprite))
                        .transform(EncasingRegistry.addVariantTo(AllBlocks.LARGE_COGWHEEL))
                        .transform(axeOrPickaxe())
                        .addLayer(()->RenderType::cutout)
                        .register();
    }

    public static BlockEntry<PublicEncasedPipeBlock> createPipe(String type,boolean clear, Supplier<Block> casing, CTSpriteShiftEntry sprite){
        String name = clear? type+"_clear" : type;
        return REGISTRATE.block(name+"_glass_encased_fluid_pipe", p -> new PublicEncasedPipeBlock(p, casing))
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(axeOrPickaxe())
                .blockstate(BlockStateGen.encasedPipe())
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(sprite)))
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, sprite,
                        (s, f) -> !s.getValue(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(f)))))
                .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::new))
                .loot((p, b) -> p.dropOther(b, AllBlocks.FLUID_PIPE.get()))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.FLUID_PIPE))
                .addLayer(()->RenderType::cutout)
                .register();
    }

}
