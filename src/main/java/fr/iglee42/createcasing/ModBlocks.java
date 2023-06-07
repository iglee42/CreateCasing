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
    public static final BlockEntry<PublicEncasedShaftBlock> RAILWAY_ENCASED_SHAFT = createShaft("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> COPPER_ENCASED_SHAFT = createShaft("copper",AllBlocks.COPPER_CASING::get, AllSpriteShifts.COPPER_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> SHADOW_ENCASED_SHAFT = createShaft("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> REFINED_RADIANCE_ENCASED_SHAFT = createShaft("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING);

    //COGWHEELS
    public static final BlockEntry<PublicEncasedCogwheelBlock> RAILWAY_ENCASED_COGWHEEL = createCogwheel("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING,ModSprites.RAILWAY_ENCASED_COGWHEEL_SIDE,ModSprites.RAILWAY_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_ENCASED_COGWHEEL = createCogwheel("copper",AllBlocks.COPPER_CASING::get,AllSpriteShifts.COPPER_CASING,ModSprites.COPPER_ENCASED_COGWHEEL_SIDE,ModSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> SHADOW_ENCASED_COGWHEEL = createCogwheel("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING,ModSprites.SHADOW_ENCASED_COGWHEEL_SIDE,ModSprites.SHADOW_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> RADIANCE_ENCASED_COGWHEEL = createCogwheel("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING,ModSprites.RADIANCE_ENCASED_COGWHEEL_SIDE,ModSprites.RADIANCE_ENCASED_COGWHEEL_OTHERSIDE);

    //LARGE COGWHEELS

    public static final BlockEntry<PublicEncasedCogwheelBlock> RAILWAY_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("copper",AllBlocks.COPPER_CASING::get,AllSpriteShifts.COPPER_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> SHADOW_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> RADIANCE_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING);

    //PIPES

    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_ANDESITE_FLUID_PIPE = createPipe("andesite",AllBlocks.ANDESITE_CASING::get,AllSpriteShifts.ANDESITE_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_BRASS_FLUID_PIPE = createPipe("brass",AllBlocks.BRASS_CASING::get,AllSpriteShifts.BRASS_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_RAILWAY_FLUID_PIPE = createPipe("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_SHADOW_FLUID_PIPE = createPipe("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_RADIANCE_FLUID_PIPE = createPipe("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING);

    public static final BlockEntry<CustomGearboxBlock> BRASS_GEARBOX = createGearbox("brass",AllSpriteShifts.BRASS_CASING,ModItems.VERTICAL_BRASS_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> COPPER_GEARBOX = createGearbox("copper",AllSpriteShifts.COPPER_CASING,ModItems.VERTICAL_COPPER_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> RAILWAY_GEARBOX = createGearbox("railway",AllSpriteShifts.RAILWAY_CASING,ModItems.VERTICAL_RAILWAY_GEARBOX);

    public static final BlockEntry<CustomMixerBlock> BRASS_MIXER = createMixer("brass");
    public static final BlockEntry<CustomMixerBlock> COPPER_MIXER = createMixer("copper");
    public static final BlockEntry<CustomMixerBlock> RAILWAY_MIXER = createMixer("railway");


    //METHODS
    public static BlockEntry<PublicEncasedShaftBlock> createShaft(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite){
        return REGISTRATE.block(name+"_encased_shaft", p -> new PublicEncasedShaftBlock(p, casing))
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BuilderTransformers.encasedShaft(name, () -> sprite))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
                .transform(axeOrPickaxe())
                .register();
    }

    public static BlockEntry<PublicEncasedCogwheelBlock> createCogwheel(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite, CTSpriteShiftEntry sideSprite, CTSpriteShiftEntry otherSideSprite){
        return REGISTRATE.block(name+"_encased_cogwheel", p -> new PublicEncasedCogwheelBlock(p, false, casing))
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BuilderTransformers.encasedCogwheel(name, () -> sprite))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.COGWHEEL))
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(sprite,
                        Couple.create(sideSprite,
                                otherSideSprite))))
                .transform(axeOrPickaxe())
                .register();
    }

    public static BlockEntry<PublicEncasedCogwheelBlock> createLargeCogwheel(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite){
        return REGISTRATE.block(name+"_encased_large_cogwheel", p -> new PublicEncasedCogwheelBlock(p, true, casing))
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BuilderTransformers.encasedLargeCogwheel(name, () -> sprite))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.LARGE_COGWHEEL))
                .transform(axeOrPickaxe())
                .register();
    }

    public static BlockEntry<PublicEncasedPipeBlock> createPipe(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite){
        return REGISTRATE.block(name+"_encased_fluid_pipe", p -> new PublicEncasedPipeBlock(p, casing))
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
                .register();
    }

    public static BlockEntry<CustomGearboxBlock> createGearbox(String name, CTSpriteShiftEntry sprite, ItemEntry<CustomVerticalGearboxItem> item){
        return REGISTRATE.block(name+"_gearbox", (p)->new CustomGearboxBlock(p,item))
                .initialProperties(SharedProperties::stone)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BlockStressDefaults.setNoImpact())
                .transform(axeOrPickaxe())
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(sprite)))
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, sprite,
                        (s, f) -> f.getAxis() == s.getValue(GearboxBlock.AXIS))))
                .blockstate((c, p) -> axisBlock(c, p, $ -> AssetLookup.partialBaseModel(c, p), true))
                .item()
                .transform(customItemModel())
                .register();
    }

    public static BlockEntry<CustomMixerBlock> createMixer(String name){
        return REGISTRATE.block(name+"_mixer", CustomMixerBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.color(MaterialColor.STONE))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(axeOrPickaxe())
                .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
                .addLayer(() -> RenderType::cutoutMipped)
                .transform(BlockStressDefaults.setImpact(4.0))
                .item(AssemblyOperatorBlockItem::new)
                .transform(customItemModel())
                .register();
    }


    public static void register() {}

}
