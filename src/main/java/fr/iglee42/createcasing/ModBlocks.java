package fr.iglee42.createcasing;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.fluids.PipeAttachmentModel;
import com.simibubi.create.content.contraptions.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCTBehaviour;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
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

    //SHAFTS
    public static final BlockEntry<PublicEncasedShaftBlock> RAILWAY_ENCASED_SHAFT = createShaft("railway",AllBlocks.RAILWAY_CASING,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> COPPER_ENCASED_SHAFT = createShaft("copper",AllBlocks.COPPER_CASING, AllSpriteShifts.COPPER_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> SHADOW_ENCASED_SHAFT = createShaft("shadow_steel",AllBlocks.SHADOW_STEEL_CASING,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> REFINED_RADIANCE_ENCASED_SHAFT = createShaft("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING,AllSpriteShifts.REFINED_RADIANCE_CASING);

    //COGWHEELS
    public static final BlockEntry<PublicEncasedCogWheelBlock> RAILWAY_ENCASED_COGWHEEL = createCogwheel("railway",AllBlocks.RAILWAY_CASING,AllSpriteShifts.RAILWAY_CASING,ModSprites.RAILWAY_ENCASED_COGWHEEL_SIDE,ModSprites.RAILWAY_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogWheelBlock> COPPER_ENCASED_COGWHEEL = createCogwheel("copper",AllBlocks.COPPER_CASING,AllSpriteShifts.COPPER_CASING,ModSprites.COPPER_ENCASED_COGWHEEL_SIDE,ModSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogWheelBlock> SHADOW_ENCASED_COGWHEEL = createCogwheel("shadow_steel",AllBlocks.SHADOW_STEEL_CASING,AllSpriteShifts.SHADOW_STEEL_CASING,ModSprites.SHADOW_ENCASED_COGWHEEL_SIDE,ModSprites.SHADOW_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogWheelBlock> RADIANCE_ENCASED_COGWHEEL = createCogwheel("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING,AllSpriteShifts.REFINED_RADIANCE_CASING,ModSprites.RADIANCE_ENCASED_COGWHEEL_SIDE,ModSprites.RADIANCE_ENCASED_COGWHEEL_OTHERSIDE);

    //LARGE COGWHEELS

    public static final BlockEntry<PublicEncasedCogWheelBlock> RAILWAY_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("railway",AllBlocks.RAILWAY_CASING,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedCogWheelBlock> COPPER_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("copper",AllBlocks.COPPER_CASING,AllSpriteShifts.COPPER_CASING);
    public static final BlockEntry<PublicEncasedCogWheelBlock> SHADOW_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("shadow_steel",AllBlocks.SHADOW_STEEL_CASING,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedCogWheelBlock> RADIANCE_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING,AllSpriteShifts.REFINED_RADIANCE_CASING);

    //PIPES

    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_ANDESITE_FLUID_PIPE = createPipe("andesite",AllBlocks.ANDESITE_CASING,AllSpriteShifts.ANDESITE_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_BRASS_FLUID_PIPE = createPipe("brass",AllBlocks.BRASS_CASING,AllSpriteShifts.BRASS_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_RAILWAY_FLUID_PIPE = createPipe("railway",AllBlocks.RAILWAY_CASING,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_SHADOW_FLUID_PIPE = createPipe("shadow_steel",AllBlocks.SHADOW_STEEL_CASING,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_RADIANCE_FLUID_PIPE = createPipe("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING,AllSpriteShifts.REFINED_RADIANCE_CASING);


    //METHODS
    public static BlockEntry<PublicEncasedShaftBlock> createShaft(String name, BlockEntry<CasingBlock> casing, CTSpriteShiftEntry sprite){
        return REGISTRATE.block(name + "_encased_shaft", (p)->new PublicEncasedShaftBlock(p,casing))
                .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
                .transform(BuilderTransformers.encasedShaft(name, () -> sprite))
                .transform(axeOrPickaxe())
                .register();
    }

    public static BlockEntry<PublicEncasedCogWheelBlock> createCogwheel(String name, BlockEntry<CasingBlock> casing, CTSpriteShiftEntry sprite, CTSpriteShiftEntry sideSprite,CTSpriteShiftEntry otherSideSprite){
        return REGISTRATE.block(name+"_encased_cogwheel", p -> new PublicEncasedCogWheelBlock(false, p,casing))
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BuilderTransformers.encasedCogwheel(name, () -> sprite))
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(sprite,
                        Couple.create(sideSprite,
                                otherSideSprite))))
                .transform(axeOrPickaxe())
                .register();
    }

    public static BlockEntry<PublicEncasedCogWheelBlock> createLargeCogwheel(String name,BlockEntry<CasingBlock> casing, CTSpriteShiftEntry sprite){
        return REGISTRATE.block(name+"_encased_large_cogwheel", p -> new PublicEncasedCogWheelBlock(true, p,casing))
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BuilderTransformers.encasedLargeCogwheel(name, () -> sprite))
                .transform(axeOrPickaxe())
                .register();
    }

    public static BlockEntry<PublicEncasedPipeBlock> createPipe(String name,BlockEntry<CasingBlock> casing,CTSpriteShiftEntry sprite){
        return REGISTRATE.block(name +"_encased_fluid_pipe", (p)->new PublicEncasedPipeBlock(p,casing))
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
                .register();
    }


    public static void register() {}

}
