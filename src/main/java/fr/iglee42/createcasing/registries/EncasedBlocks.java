package fr.iglee42.createcasing.registries;

import com.simibubi.create.*;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.decoration.encasing.*;
import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.content.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.simibubi.create.content.logistics.depot.MountedDepotInteractionBehaviour;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.*;
import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiFunction;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.blocks.ConfigurableGearboxBlock;
import fr.iglee42.createcasing.blocks.CreativeCogwheelBlock;
import fr.iglee42.createcasing.blocks.customs.*;
import fr.iglee42.createcasing.blocks.publics.PublicChainConveyorBlock;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedCogwheelBlock;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedPipeBlock;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedShaftBlock;
import fr.iglee42.createcasing.blocks.shafts.*;
import fr.iglee42.createcasing.config.CCStress;
import fr.iglee42.createcasing.items.CustomVerticalGearboxItem;
import fr.iglee42.createcasing.items.WoodenCogwheelBlockItem;
import fr.iglee42.createcasing.utils.CasingBuilderTransformers;
import net.createmod.catnip.data.Couple;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.simibubi.create.api.behaviour.display.DisplaySource.displaySource;
import static com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour.interactionBehaviour;
import static com.simibubi.create.api.contraption.storage.item.MountedItemStorageType.mountedItemStorage;
import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;
import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;
import static fr.iglee42.createcasing.registries.EncasedBlockStateGens.*;
import static net.minecraft.world.level.block.Blocks.GLASS;

public class EncasedBlocks {

    static {
        REGISTRATE.setCreativeTab(EncasedCreativeModeTabs.MAIN_TAB);
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, CreateCasing.MODID);

    public static final BlockEntry<CasingBlock> CREATIVE_CASING = createCasing("creative",AllSpriteShifts.CREATIVE_CASING);


    //BELT CASINGS
    public static BeltBlockEntity.CasingType COPPER_BELT_CASING;
    public static BeltBlockEntity.CasingType RAILWAY_BELT_CASING;
    public static BeltBlockEntity.CasingType INDUSTRIAL_IRON_BELT_CASING;
    public static BeltBlockEntity.CasingType CREATIVE_BELT_CASING;
    public static BeltBlockEntity.CasingType WEATHERED_IRON_BELT_CASING;
    public static BeltBlockEntity.CasingType REFINED_RADIANCE_BELT_CASING;
    public static BeltBlockEntity.CasingType SHADOW_STEEL_BELT_CASING;

    //SHAFTS
    public static final BlockEntry<PublicEncasedShaftBlock> RAILWAY_ENCASED_SHAFT = createShaft("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> COPPER_ENCASED_SHAFT = createShaft("copper",AllBlocks.COPPER_CASING::get, AllSpriteShifts.COPPER_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> SHADOW_STEEL_ENCASED_SHAFT = createShaft("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> REFINED_RADIANCE_ENCASED_SHAFT = createShaft("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> CREATIVE_ENCASED_SHAFT = createShaft("creative", EncasedBlocks.CREATIVE_CASING::get,AllSpriteShifts.CREATIVE_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> INDUSTRIAL_IRON_ENCASED_SHAFT = createShaft("industrial_iron",AllBlocks.INDUSTRIAL_IRON_BLOCK::get,null);
    public static final BlockEntry<PublicEncasedShaftBlock> WEATHERED_IRON_ENCASED_SHAFT = createShaft("weathered_iron",AllBlocks.WEATHERED_IRON_BLOCK::get,null);

    //COGWHEELS
    public static final BlockEntry<PublicEncasedCogwheelBlock> RAILWAY_ENCASED_COGWHEEL = createCogwheel("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING, EncasedSprites.RAILWAY_ENCASED_COGWHEEL_SIDE, EncasedSprites.RAILWAY_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_ENCASED_COGWHEEL = createCogwheel("copper",AllBlocks.COPPER_CASING::get,AllSpriteShifts.COPPER_CASING, EncasedSprites.COPPER_ENCASED_COGWHEEL_SIDE, EncasedSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> SHADOW_STEEL_ENCASED_COGWHEEL = createCogwheel("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING, EncasedSprites.SHADOW_STEEL_ENCASED_COGWHEEL_SIDE, EncasedSprites.SHADOW_STEEL_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> REFINED_RADIANCE_ENCASED_COGWHEEL = createCogwheel("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING, EncasedSprites.REFINED_RADIANCE_ENCASED_COGWHEEL_SIDE, EncasedSprites.REFINED_RADIANCE_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> CREATIVE_ENCASED_COGWHEEL = createCogwheel("creative", EncasedBlocks.CREATIVE_CASING::get,AllSpriteShifts.CREATIVE_CASING, EncasedSprites.CREATIVE_ENCASED_COGWHEEL_SIDE, EncasedSprites.CREATIVE_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> INDUSTRIAL_IRON_ENCASED_COGWHEEL = createCogwheel("industrial_iron",AllBlocks.INDUSTRIAL_IRON_BLOCK::get,null,null,null);
    public static final BlockEntry<PublicEncasedCogwheelBlock> WEATHERED_IRON_ENCASED_COGWHEEL = createCogwheel("weathered_iron",AllBlocks.WEATHERED_IRON_BLOCK::get,null,null,null);

    //LARGE COGWHEELS

    public static final BlockEntry<PublicEncasedCogwheelBlock> RAILWAY_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("copper",AllBlocks.COPPER_CASING::get,AllSpriteShifts.COPPER_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> SHADOW_STEEL_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> REFINED_RADIANCE_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> CREATIVE_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("creative", EncasedBlocks.CREATIVE_CASING::get,AllSpriteShifts.CREATIVE_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> INDUSTRIAL_IRON_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("industrial_iron",AllBlocks.INDUSTRIAL_IRON_BLOCK::get,null);
    public static final BlockEntry<PublicEncasedCogwheelBlock> WEATHERED_IRON_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("weathered_iron",AllBlocks.WEATHERED_IRON_BLOCK::get,null);


    //PIPES

    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_ANDESITE_FLUID_PIPE = createPipe("andesite",AllBlocks.ANDESITE_CASING::get,AllSpriteShifts.ANDESITE_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_BRASS_FLUID_PIPE = createPipe("brass",AllBlocks.BRASS_CASING::get,AllSpriteShifts.BRASS_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_RAILWAY_FLUID_PIPE = createPipe("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_SHADOW_STEEL_FLUID_PIPE = createPipe("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_REFINED_RADIANCE_FLUID_PIPE = createPipe("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_CREATIVE_FLUID_PIPE = createPipe("creative", EncasedBlocks.CREATIVE_CASING::get,AllSpriteShifts.CREATIVE_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_INDUSTRIAL_IRON_FLUID_PIPE = createPipe("industrial_iron",AllBlocks.INDUSTRIAL_IRON_BLOCK::get,null);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_WEATHERED_IRON_FLUID_PIPE = createPipe("weathered_iron",AllBlocks.WEATHERED_IRON_BLOCK::get,null);

    public static final BlockEntry<CustomGearboxBlock> BRASS_GEARBOX = createGearbox("brass",AllSpriteShifts.BRASS_CASING, EncasedItems.VERTICAL_BRASS_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> COPPER_GEARBOX = createGearbox("copper",AllSpriteShifts.COPPER_CASING, EncasedItems.VERTICAL_COPPER_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> RAILWAY_GEARBOX = createGearbox("railway",AllSpriteShifts.RAILWAY_CASING, EncasedItems.VERTICAL_RAILWAY_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> CREATIVE_GEARBOX = createGearbox("creative",AllSpriteShifts.CREATIVE_CASING, EncasedItems.VERTICAL_CREATIVE_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> INDUSTRIAL_IRON_GEARBOX = createGearbox("industrial_iron",null, EncasedItems.VERTICAL_INDUSTRIAL_IRON_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> WEATHERED_IRON_GEARBOX = createGearbox("weathered_iron",null, EncasedItems.VERTICAL_WEATHERED_IRON_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> REFINED_RADIANCE_GEARBOX = createGearbox("refined_radiance",AllSpriteShifts.REFINED_RADIANCE_CASING, EncasedItems.VERTICAL_REFINED_RADIANCE_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> SHADOW_STEEL_GEARBOX = createGearbox("shadow_steel",AllSpriteShifts.SHADOW_STEEL_CASING, EncasedItems.VERTICAL_SHADOW_STEEL_GEARBOX);

    public static final BlockEntry<CustomMixerBlock> BRASS_MIXER = createMixer("brass");
    public static final BlockEntry<CustomMixerBlock> COPPER_MIXER = createMixer("copper");
    public static final BlockEntry<CustomMixerBlock> RAILWAY_MIXER = createMixer("railway");
    public static final BlockEntry<CustomMixerBlock> CREATIVE_MIXER = createMixer("creative");
    public static final BlockEntry<CustomMixerBlock> INDUSTRIAL_IRON_MIXER = createMixer("industrial_iron");
    public static final BlockEntry<CustomMixerBlock> WEATHERED_IRON_MIXER = createMixer("weathered_iron");
    public static final BlockEntry<CustomMixerBlock> REFINED_RADIANCE_MIXER = createMixer("refined_radiance");
    public static final BlockEntry<CustomMixerBlock> SHADOW_STEEL_MIXER = createMixer("shadow_steel");

    public static final BlockEntry<CustomPressBlock> BRASS_PRESS = createPress("brass");
    public static final BlockEntry<CustomPressBlock> COPPER_PRESS = createPress("copper");
    public static final BlockEntry<CustomPressBlock> RAILWAY_PRESS = createPress("railway");
    public static final BlockEntry<CustomPressBlock> CREATIVE_PRESS = createPress("creative");
    public static final BlockEntry<CustomPressBlock> INDUSTRIAL_IRON_PRESS = createPress("industrial_iron");
    public static final BlockEntry<CustomPressBlock> WEATHERED_IRON_PRESS = createPress("weathered_iron");
    public static final BlockEntry<CustomPressBlock> REFINED_RADIANCE_PRESS = createPress("refined_radiance");
    public static final BlockEntry<CustomPressBlock> SHADOW_STEEL_PRESS = createPress("shadow_steel");

    public static final BlockEntry<CustomDepotBlock> BRASS_DEPOT = createDepot("brass");
    public static final BlockEntry<CustomDepotBlock> COPPER_DEPOT = createDepot("copper");
    public static final BlockEntry<CustomDepotBlock> RAILWAY_DEPOT = createDepot("railway");
    public static final BlockEntry<CustomDepotBlock> CREATIVE_DEPOT = createDepot("creative");
    public static final BlockEntry<CustomDepotBlock> INDUSTRIAL_IRON_DEPOT = createDepot("industrial_iron");
    public static final BlockEntry<CustomDepotBlock> WEATHERED_IRON_DEPOT = createDepot("weathered_iron");
    public static final BlockEntry<CustomDepotBlock> REFINED_RADIANCE_DEPOT = createDepot("refined_radiance");
    public static final BlockEntry<CustomDepotBlock> SHADOW_STEEL_DEPOT = createDepot("shadow_steel");

    public static final BlockEntry<CustomChainDriveBlock> BRASS_CHAIN_DRIVE = createDrive("brass");
    public static final BlockEntry<CustomChainDriveBlock> COPPER_CHAIN_DRIVE = createDrive("copper");
    public static final BlockEntry<CustomChainDriveBlock> RAILWAY_CHAIN_DRIVE = createDrive("railway");
    public static final BlockEntry<CustomChainDriveBlock> CREATIVE_CHAIN_DRIVE = createDrive("creative");
    public static final BlockEntry<CustomChainDriveBlock> INDUSTRIAL_IRON_CHAIN_DRIVE = createDrive("industrial_iron");
    public static final BlockEntry<CustomChainDriveBlock> WEATHERED_IRON_CHAIN_DRIVE = createDrive("weathered_iron");
    public static final BlockEntry<CustomChainDriveBlock> REFINED_RADIANCE_CHAIN_DRIVE = createDrive("refined_radiance");
    public static final BlockEntry<CustomChainDriveBlock> SHADOW_STEEL_CHAIN_DRIVE = createDrive("shadow_steel");

    public static final BlockEntry<CustomChainGearshiftBlock> BRASS_CHAIN_GEARSHIFT = createChainGearshift("brass");
    public static final BlockEntry<CustomChainGearshiftBlock> COPPER_CHAIN_GEARSHIFT = createChainGearshift("copper");
    public static final BlockEntry<CustomChainGearshiftBlock> RAILWAY_CHAIN_GEARSHIFT = createChainGearshift("railway");
    public static final BlockEntry<CustomChainGearshiftBlock> CREATIVE_CHAIN_GEARSHIFT = createChainGearshift("creative");
    public static final BlockEntry<CustomChainGearshiftBlock> INDUSTRIAL_IRON_CHAIN_GEARSHIFT = createChainGearshift("industrial_iron");
    public static final BlockEntry<CustomChainGearshiftBlock> WEATHERED_IRON_CHAIN_GEARSHIFT = createChainGearshift("weathered_iron");
    public static final BlockEntry<CustomChainGearshiftBlock> REFINED_RADIANCE_CHAIN_GEARSHIFT = createChainGearshift("refined_radiance");
    public static final BlockEntry<CustomChainGearshiftBlock> SHADOW_STEEL_CHAIN_GEARSHIFT = createChainGearshift("shadow_steel");

    public static final BlockEntry<ConfigurableGearboxBlock> ANDESITE_CONFIGURABLE_GEARBOX = createConfigurableGearbox("andesite",AllSpriteShifts.ANDESITE_CASING);
    public static final BlockEntry<ConfigurableGearboxBlock> BRASS_CONFIGURABLE_GEARBOX = createConfigurableGearbox("brass",AllSpriteShifts.BRASS_CASING);
    public static final BlockEntry<ConfigurableGearboxBlock> COPPER_CONFIGURABLE_GEARBOX = createConfigurableGearbox("copper",AllSpriteShifts.COPPER_CASING);
    public static final BlockEntry<ConfigurableGearboxBlock> RAILWAY_CONFIGURABLE_GEARBOX = createConfigurableGearbox("railway",AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<ConfigurableGearboxBlock> CREATIVE_CONFIGURABLE_GEARBOX = createConfigurableGearbox("creative",AllSpriteShifts.CREATIVE_CASING);
    public static final BlockEntry<ConfigurableGearboxBlock> INDUSTRIAL_IRON_CONFIGURABLE_GEARBOX = createConfigurableGearbox("industrial_iron",null);
    public static final BlockEntry<ConfigurableGearboxBlock> WEATHERED_IRON_CONFIGURABLE_GEARBOX = createConfigurableGearbox("weathered_iron",null);
    public static final BlockEntry<ConfigurableGearboxBlock> REFINED_RADIANCE_CONFIGURABLE_GEARBOX = createConfigurableGearbox("refined_radiance",AllSpriteShifts.REFINED_RADIANCE_CASING);
    public static final BlockEntry<ConfigurableGearboxBlock> SHADOW_STEEL_CONFIGURABLE_GEARBOX = createConfigurableGearbox("shadow_steel",AllSpriteShifts.SHADOW_STEEL_CASING);

    public static final BlockEntry<PublicChainConveyorBlock> BRASS_CHAIN_CONVEYOR = createChainConveyor("brass");
    public static final BlockEntry<PublicChainConveyorBlock> COPPER_CHAIN_CONVEYOR = createChainConveyor("copper");
    public static final BlockEntry<PublicChainConveyorBlock> RAILWAY_CHAIN_CONVEYOR = createChainConveyor("railway");
    public static final BlockEntry<PublicChainConveyorBlock> CREATIVE_CHAIN_CONVEYOR = createChainConveyor("creative");
    public static final BlockEntry<PublicChainConveyorBlock> INDUSTRIAL_IRON_CHAIN_CONVEYOR = createChainConveyor("industrial_iron");
    public static final BlockEntry<PublicChainConveyorBlock> WEATHERED_IRON_CHAIN_CONVEYOR = createChainConveyor("weathered_iron");
    public static final BlockEntry<PublicChainConveyorBlock> REFINED_RADIANCE_CHAIN_CONVEYOR = createChainConveyor("refined_radiance");
    public static final BlockEntry<PublicChainConveyorBlock> SHADOW_STEEL_CHAIN_CONVEYOR = createChainConveyor("shadow_steel");

    public static final BlockEntry<WoodenShaftBlock> OAK_SHAFT = createWoodenShaft("oak");
    public static final BlockEntry<WoodenShaftBlock> SPRUCE_SHAFT = createWoodenShaft("spruce");
    public static final BlockEntry<WoodenShaftBlock> BIRCH_SHAFT = createWoodenShaft("birch");
    public static final BlockEntry<WoodenShaftBlock> JUNGLE_SHAFT = createWoodenShaft("jungle");
    public static final BlockEntry<WoodenShaftBlock> ACACIA_SHAFT = createWoodenShaft("acacia");
    public static final BlockEntry<WoodenShaftBlock> DARK_OAK_SHAFT = createWoodenShaft("dark_oak");
    public static final BlockEntry<WoodenShaftBlock> MANGROVE_SHAFT = createWoodenShaft("mangrove");
    public static final BlockEntry<WoodenShaftBlock> CHERRY_SHAFT = createWoodenShaft("cherry");
    public static final BlockEntry<WoodenShaftBlock> BAMBOO_SHAFT = createWoodenShaft("bamboo");
    public static final BlockEntry<WoodenShaftBlock> CRIMSON_SHAFT = createWoodenShaft("crimson");
    public static final BlockEntry<WoodenShaftBlock> WARPED_SHAFT = createWoodenShaft("warped");


    public static final BlockEntry<WoodenCogwheelBlock> OAK_COGWHEEL = createWoodenCogwheel("oak");
    public static final BlockEntry<WoodenCogwheelBlock> BIRCH_COGWHEEL = createWoodenCogwheel("birch");
    public static final BlockEntry<WoodenCogwheelBlock> JUNGLE_COGWHEEL = createWoodenCogwheel("jungle");
    public static final BlockEntry<WoodenCogwheelBlock> ACACIA_COGWHEEL = createWoodenCogwheel("acacia");
    public static final BlockEntry<WoodenCogwheelBlock> DARK_OAK_COGWHEEL = createWoodenCogwheel("dark_oak");
    public static final BlockEntry<WoodenCogwheelBlock> MANGROVE_COGWHEEL = createWoodenCogwheel("mangrove");
    public static final BlockEntry<WoodenCogwheelBlock> CHERRY_COGWHEEL = createWoodenCogwheel("cherry");
    public static final BlockEntry<WoodenCogwheelBlock> BAMBOO_COGWHEEL = createWoodenCogwheel("bamboo");
    public static final BlockEntry<WoodenCogwheelBlock> CRIMSON_COGWHEEL = createWoodenCogwheel("crimson");
    public static final BlockEntry<WoodenCogwheelBlock> WARPED_COGWHEEL = createWoodenCogwheel("warped");

    public static final BlockEntry<WoodenCogwheelBlock> OAK_LARGE_COGWHEEL = createLargeWoodenCogwheel("oak");
    public static final BlockEntry<WoodenCogwheelBlock> BIRCH_LARGE_COGWHEEL = createLargeWoodenCogwheel("birch");
    public static final BlockEntry<WoodenCogwheelBlock> JUNGLE_LARGE_COGWHEEL = createLargeWoodenCogwheel("jungle");
    public static final BlockEntry<WoodenCogwheelBlock> ACACIA_LARGE_COGWHEEL = createLargeWoodenCogwheel("acacia");
    public static final BlockEntry<WoodenCogwheelBlock> DARK_OAK_LARGE_COGWHEEL = createLargeWoodenCogwheel("dark_oak");
    public static final BlockEntry<WoodenCogwheelBlock> MANGROVE_LARGE_COGWHEEL = createLargeWoodenCogwheel("mangrove");
    public static final BlockEntry<WoodenCogwheelBlock> CHERRY_LARGE_COGWHEEL = createLargeWoodenCogwheel("cherry");
    public static final BlockEntry<WoodenCogwheelBlock> BAMBOO_LARGE_COGWHEEL = createLargeWoodenCogwheel("bamboo");
    public static final BlockEntry<WoodenCogwheelBlock> CRIMSON_LARGE_COGWHEEL = createLargeWoodenCogwheel("crimson");
    public static final BlockEntry<WoodenCogwheelBlock> WARPED_LARGE_COGWHEEL = createLargeWoodenCogwheel("warped");

    public static final BlockEntry<GlassShaftBlock> GLASS_SHAFT = REGISTRATE.block("glass_shaft", GlassShaftBlock::new)
            .initialProperties(()-> GLASS)
            .properties(p -> p.mapColor(MapColor.NONE)
                    .sound(SoundType.GLASS)
                    .noOcclusion())
            .transform(CCStress.setNoImpact())
            .blockstate(EncasedBlockStateGens.shaft("glass"))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item()
            .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(shaftModel(p, "glass"))))
            .build()
            .register();

    public static final BlockEntry<BrassShaftBlock> BRASS_SHAFT = REGISTRATE.block("brass_shaft", BrassShaftBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.METAL))
            .transform(CCStress.setNoImpact())
            .transform(pickaxeOnly())
            .blockstate(EncasedBlockStateGens.shaft("brass"))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item()
            .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(shaftModel(p, "brass"))))
            .build()
            .register();

    public static final BlockEntry<MetalShaftBlock> MLDEG_SHAFT = REGISTRATE.block("mldeg_shaft", MetalShaftBlock::new)
            .initialProperties(()-> Blocks.BLACKSTONE)
            .properties(p -> p.mapColor(MapColor.NONE)
                    .sound(SoundType.STONE)
                    .noOcclusion())
            .transform(CCStress.setNoImpact())
            .blockstate(EncasedBlockStateGens.shaft("mldeg"))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem)
            .item()
            .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(shaftModel(p, "mldeg"))))
            .build()
            .register();

    public static final BlockEntry<CreativeCogwheelBlock> CREATIVE_COGWHEEL =
            REGISTRATE.block("creative_cogwheel", CreativeCogwheelBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.mapColor(MapColor.COLOR_PURPLE))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
                    .transform(pickaxeOnly())
                    .blockstate((c,p)->EncasedBlockStateGens.axisBlock(c,p,b->new ModelFile.UncheckedModelFile(CreateCasing.asResource("block/creative_cogwheel/block")),false))
                    .transform(CCStress.setCapacity(16384.0))
                    .onRegister(BlockStressValues.setGeneratorSpeed(256,true))
                    .addLayer(()-> RenderType::cutoutMipped)
                    .item()
                    .properties(p -> p.rarity(Rarity.EPIC))
                    .transform(customItemModel())
                    .register();


    //METHODS

    public static BlockEntry<CasingBlock> createCasing(String name, CTSpriteShiftEntry connectedTexturesSprite){
        return REGISTRATE.block(name+"_casing", CasingBlock::new)
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(CasingBuilderTransformers.casing(() -> connectedTexturesSprite))
                .blockstate((c,p)->p.simpleBlock(c.get(),p.models().cubeAll(c.getName(),Create.asResource("block/"+c.getId().getPath()))))
                .simpleItem()
                .register();
    }

    private static BlockEntry<PublicEncasedShaftBlock> createShaft(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite){
        return createShaft(AllBlocks.SHAFT,name,casing,sprite,PublicEncasedShaftBlock::new);
    }

    private static <E extends Block & EncasableBlock, T extends EncasedShaftBlock> BlockEntry<T> createShaft(BlockEntry<E> shaft, String name, Supplier<Block> casing, CTSpriteShiftEntry sprite, NonNullBiFunction<BlockBehaviour.Properties,Supplier<Block>, T> factory){
        String s = shaft.getId().getPath().replace("_shaft","");
        return REGISTRATE.block(name+"_encased"+(shaft.equals(AllBlocks.SHAFT) ? "" : "_"+ s)+"_shaft", p -> factory.apply(p,casing))
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(CasingBuilderTransformers.encasedShaft(shaft,name, () -> sprite))
                .transform(EncasingRegistry.addVariantTo(shaft))
                .transform(axeOrPickaxe())
                .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem)
                .register();
    }

    private static BlockEntry<PublicEncasedCogwheelBlock> createCogwheel(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite, CTSpriteShiftEntry sideSprite, CTSpriteShiftEntry otherSideSprite){
        return createCogwheel(AllBlocks.COGWHEEL,name,casing,sprite,sideSprite,otherSideSprite,(p,c)->new PublicEncasedCogwheelBlock(p,false,c));
    }

    private static <E extends Block & EncasableBlock, T extends EncasedCogwheelBlock>  BlockEntry<T> createCogwheel(BlockEntry<E> cogwheel,String name, Supplier<Block> casing, CTSpriteShiftEntry sprite, CTSpriteShiftEntry sideSprite, CTSpriteShiftEntry otherSideSprite, NonNullBiFunction<BlockBehaviour.Properties,Supplier<Block>, T> factory){
        String s = cogwheel.getId().getPath().replace("_cogwheel","");
        BlockBuilder<T,CreateRegistrate> builder =  REGISTRATE.block(name+"_encased"+(cogwheel.equals(AllBlocks.COGWHEEL) ? "" : "_"+ s)+"_cogwheel",  p -> factory.apply(p,casing))
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(CasingBuilderTransformers.encasedCogwheel(cogwheel,name, () -> sprite))
                .transform(EncasingRegistry.addVariantTo(cogwheel))
                .transform(axeOrPickaxe())
                .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem);
        if (sprite != null){
            builder = builder.onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(sprite,
                    Couple.create(sideSprite,
                            otherSideSprite))));
        }
        return builder.register();
    }

    private static BlockEntry<PublicEncasedCogwheelBlock> createLargeCogwheel(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite){
        return createLargeCogwheel(AllBlocks.LARGE_COGWHEEL,name,casing,sprite,(p,c)->new PublicEncasedCogwheelBlock(p,true,c));
    }

    private static  <E extends Block & EncasableBlock, T extends EncasedCogwheelBlock>  BlockEntry<T> createLargeCogwheel(BlockEntry<E> cogwheel,String name, Supplier<Block> casing, CTSpriteShiftEntry sprite, NonNullBiFunction<BlockBehaviour.Properties,Supplier<Block>, T> factory){
        String s = cogwheel.getId().getPath().replace("_large_cogwheel","");
        return REGISTRATE.block(name+"_encased"+(cogwheel.equals(AllBlocks.LARGE_COGWHEEL) ? "" : "_"+ s)+"_large_cogwheel", p ->factory.apply(p,casing))
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(CasingBuilderTransformers.encasedLargeCogwheel(cogwheel,name, () -> sprite))
                .transform(EncasingRegistry.addVariantTo(cogwheel))
                .transform(axeOrPickaxe())
                .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem)
                .register();
    }

    private static BlockEntry<PublicEncasedPipeBlock> createPipe(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite){;
        BlockBuilder<PublicEncasedPipeBlock,CreateRegistrate> builder = REGISTRATE.block(name+"_encased_fluid_pipe", p -> new PublicEncasedPipeBlock(p, casing))
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(axeOrPickaxe())
                .blockstate(EncasedBlockStateGens.encasedPipe(name))
                .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
                .loot((p, b) -> p.dropOther(b, AllBlocks.FLUID_PIPE.get()))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.FLUID_PIPE))
                .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem);
        builder = connectedTexture(builder,sprite,(block,cc)->cc.make(block, sprite,
                (s, f) -> !s.getValue(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(f))));
        return builder.register();
    }

    private static BlockEntry<CustomGearboxBlock> createGearbox(String name, CTSpriteShiftEntry sprite, ItemEntry<CustomVerticalGearboxItem> item){
        BlockBuilder<CustomGearboxBlock,CreateRegistrate> entry = REGISTRATE.block(name+"_gearbox", (p)->new CustomGearboxBlock(p,item))
                .initialProperties(SharedProperties::stone)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(CCStress.setNoImpact())
                .transform(axeOrPickaxe())
                .blockstate( gearbox(name));
        entry = connectedTexture(entry,sprite,(block, cc) -> cc.make(block, sprite,
                (s, f) -> f.getAxis() == s.getValue(GearboxBlock.AXIS)));
        return entry.item()
                .model((ctx,prov)->prov.getBuilder(ctx.getName()).parent(Objects.requireNonNull(gearboxModel(prov, name, "item"))))
                .build()
                .register();
    }

    private static BlockEntry<CustomMixerBlock> createMixer(String name){
        return REGISTRATE.block(name+"_mixer", CustomMixerBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.mapColor(MapColor.STONE))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(axeOrPickaxe())
                .blockstate(EncasedBlockStateGens.mixer(name))
                .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createcasing.custom_mixer"))
                .addLayer(() -> RenderType::cutoutMipped)
                .transform(CCStress.setImpact(4.0))
                .item(AssemblyOperatorBlockItem::new)
                .model((c,p)->p.getBuilder(c.getName()).parent(mixerModel(p,name,true)))
                .build()
                .register();
    }

    private static BlockEntry<CustomPressBlock> createPress(String name){
        return REGISTRATE.block(name+"_press", CustomPressBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(axeOrPickaxe())
                .blockstate(EncasedBlockStateGens.press(name))
                .transform(CCStress.setImpact(8.0))
                .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createcasing.custom_press"))
                .item(AssemblyOperatorBlockItem::new)
                .model((c,p)->p.getBuilder(c.getName()).parent(pressModel(p,name,true)))
                .build()
                .register();
    }

    private static BlockEntry<WoodenShaftBlock> createWoodenShaft(String name){
        return REGISTRATE.block(name+"_shaft", WoodenShaftBlock::new)
                .initialProperties(SharedProperties::wooden)
                .properties(p -> p.mapColor(MapColor.METAL))
                .transform(CCStress.setNoImpact())
                .transform(axeOnly())
                .blockstate(EncasedBlockStateGens.shaft(name))
                .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(shaftModel(p, name))))
                .build()
                .register();
    }

    private static BlockEntry<WoodenCogwheelBlock> createWoodenCogwheel(String name) {
        return REGISTRATE.block(name+"_cogwheel", WoodenCogwheelBlock::small)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.sound(SoundType.WOOD).mapColor(MapColor.DIRT))
                .transform(CCStress.setNoImpact())
                .transform(axeOnly())
                .blockstate(EncasedBlockStateGens.cogwheel(name))
                .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                .item(WoodenCogwheelBlockItem::new)
                .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(cogwheelModel(p, name,true))))
                .build()
                .register();
    }

    private static BlockEntry<WoodenCogwheelBlock> createLargeWoodenCogwheel(String name) {
        return REGISTRATE.block(name+"_large_cogwheel", WoodenCogwheelBlock::large)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.sound(SoundType.WOOD).mapColor(MapColor.DIRT))
                .transform(axeOnly())
                .transform(CCStress.setNoImpact())
                .blockstate(EncasedBlockStateGens.largeCogwheel(name))
                .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                .item(WoodenCogwheelBlockItem::new)
                .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(largeCogwheelModel(p, name,true))))
                .build()
                .register();

    }


    public static BlockEntry<CustomDepotBlock> createDepot(String name){
        return REGISTRATE.block(name+"_depot", CustomDepotBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
                .transform(axeOrPickaxe())
                .blockstate(EncasedBlockStateGens.depot(name))
                .transform(displaySource(AllDisplaySources.ITEM_NAMES))
                .onRegister(interactionBehaviour(new MountedDepotInteractionBehaviour()))
                .transform(mountedItemStorage(AllMountedStorageTypes.DEPOT))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(depotModel(p, name))))
                .build()
                .register();
    }

    public static BlockEntry<CustomChainDriveBlock> createDrive(String name){
        return REGISTRATE.block(name+"_encased_chain_drive", p-> new CustomChainDriveBlock(p,name))
                        .initialProperties(SharedProperties::stone)
                        .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
                        .transform(CCStress.setNoImpact())
                        .transform(axeOrPickaxe())
                        .blockstate( EncasedBlockStateGens.encasedChainDrive(name))
                        .item()
                        .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(createChainDriveModel(p, name,true,"item"))))
                        .build()
                        .register();

    }

    public static BlockEntry<CustomChainGearshiftBlock> createChainGearshift(String name){
        return REGISTRATE.block(name+ "_adjustable_chain_gearshift", p->new CustomChainGearshiftBlock(p,name))
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.noOcclusion().mapColor(MapColor.NETHER))
                .transform(CCStress.setNoImpact())
                .transform(axeOrPickaxe())
                .blockstate( EncasedBlockStateGens.adjustableChainGearshift(name))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(createAdjustableChainGearshiftModel(p, name,true,"item",false))))
                .build()
                .register();
    }

    public static BlockEntry<ConfigurableGearboxBlock> createConfigurableGearbox(String name, CTSpriteShiftEntry ct){
        BlockBuilder<ConfigurableGearboxBlock,CreateRegistrate> entry = REGISTRATE.block(name+"_configurable_gearbox", ConfigurableGearboxBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(CCStress.setNoImpact())
                .transform(axeOrPickaxe())
                .blockstate(EncasedBlockStateGens.configurableGearbox(name))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(createConfigurableGearboxItemModel(p,name)))
                .build();
        if (ct != null)entry = entry
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(ct)))
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, ct,
                        (s, f) -> !s.getValue(ConfigurableGearboxBlock.getPropertyByDirection(f)))));
        return entry.register();
    }

    public static BlockEntry<PublicChainConveyorBlock> createChainConveyor(String name){
        return REGISTRATE.block(name+"_chain_conveyor", PublicChainConveyorBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.noOcclusion()
                .mapColor(MapColor.PODZOL))
                .transform(axeOrPickaxe())
                .transform(CCStress.setImpact(1))
                .blockstate(chainConveyor(name))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(createConveyorModel(p,name,true)))
                .build()
                .register();
    }

    private static <T extends Block> BlockBuilder<T,CreateRegistrate> connectedTexture( BlockBuilder<T, CreateRegistrate> entry,CTSpriteShiftEntry sprite,BiConsumer<T, CasingConnectivity> consumer){
        if (sprite != null){
            return entry.onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(sprite)))
                    .onRegister(CreateRegistrate.casingConnectivity(consumer));
        }
        return entry;
    }

    public static void registerEncasedShafts() {
        List<BlockEntry<?>> casings = Arrays.asList(AllBlocks.WEATHERED_IRON_BLOCK,AllBlocks.INDUSTRIAL_IRON_BLOCK,CREATIVE_CASING,AllBlocks.ANDESITE_CASING,AllBlocks.BRASS_CASING,AllBlocks.COPPER_CASING,AllBlocks.RAILWAY_CASING,AllBlocks.REFINED_RADIANCE_CASING,AllBlocks.SHADOW_STEEL_CASING);
        forEachShaft(shaft-> {
            casings.forEach(c-> {
                String casing = c.getId().getPath().replace("_casing", "").replace("_block","");
                CTSpriteShiftEntry sprite = EncasedSprites.getEntryForCasing(casing);
                createShaft(shaft,casing, c::get,sprite,(p,s)->new EncasedCustomShaftBlock(p,s,shaft));
            });

        });
        forEachCogwheel(cogwheel-> {
            casings.forEach(c-> {
                String casing = c.getId().getPath().replace("_casing", "").replace("_block","");
                CTSpriteShiftEntry sprite = EncasedSprites.getEntryForCasing(casing);
                CTSpriteShiftEntry sideSprite = EncasedSprites.getEntryForSide(casing);
                CTSpriteShiftEntry otherSideSprite = EncasedSprites.getEntryForOtherSide(casing);
                createCogwheel(cogwheel,casing, c::get,sprite,sideSprite,otherSideSprite,(p,s)->new EncasedCustomCogwheelBlock(p,false,s,cogwheel));
            });


        });
        forEachLargeCogwheel(cogwheel-> {
            casings.forEach(c-> {
                String casing = c.getId().getPath().replace("_casing", "").replace("_block","");
                CTSpriteShiftEntry sprite = EncasedSprites.getEntryForCasing(casing);
                createLargeCogwheel(cogwheel,casing, c::get,sprite,(p,s)->new EncasedCustomCogwheelBlock(p,true,s,cogwheel));
            });
        });
    }


    public static void register() {

    }

    public static boolean isWoodenShaftHasState(BlockState state) {

        return OAK_SHAFT.has(state) ||
                SPRUCE_SHAFT.has(state) ||
                BIRCH_SHAFT.has(state) ||
                JUNGLE_SHAFT.has(state) ||
                ACACIA_SHAFT.has(state) ||
                DARK_OAK_SHAFT.has(state) ||
                MANGROVE_SHAFT.has(state) ||
                BAMBOO_SHAFT.has(state) ||
                CHERRY_SHAFT.has(state) ||
                CRIMSON_SHAFT.has(state) ||
                WARPED_SHAFT.has(state);

    }

    public static void forEachShaft(Consumer<BlockEntry<? extends ShaftBlock>> action){
        action.accept(OAK_SHAFT);
        action.accept(BIRCH_SHAFT);
        action.accept(SPRUCE_SHAFT);
        action.accept(JUNGLE_SHAFT);
        action.accept(ACACIA_SHAFT);
        action.accept(DARK_OAK_SHAFT);
        action.accept(MANGROVE_SHAFT);
        action.accept(BAMBOO_SHAFT);
        action.accept(CHERRY_SHAFT);
        action.accept(WARPED_SHAFT);
        action.accept(CRIMSON_SHAFT);
        action.accept(GLASS_SHAFT);
        action.accept(MLDEG_SHAFT);
    }

    public static void forEachCogwheel(Consumer<BlockEntry<? extends WoodenCogwheelBlock>> action){
        action.accept(OAK_COGWHEEL);
        action.accept(BIRCH_COGWHEEL);
        action.accept(JUNGLE_COGWHEEL);
        action.accept(ACACIA_COGWHEEL);
        action.accept(DARK_OAK_COGWHEEL);
        action.accept(MANGROVE_COGWHEEL);
        action.accept(BAMBOO_COGWHEEL);
        action.accept(CHERRY_COGWHEEL);
        action.accept(WARPED_COGWHEEL);
        action.accept(CRIMSON_COGWHEEL);
    }

    public static void forEachLargeCogwheel(Consumer<BlockEntry<? extends WoodenCogwheelBlock>> action){
        action.accept(OAK_LARGE_COGWHEEL);
        action.accept(BIRCH_LARGE_COGWHEEL);
        action.accept(JUNGLE_LARGE_COGWHEEL);
        action.accept(ACACIA_LARGE_COGWHEEL);
        action.accept(DARK_OAK_LARGE_COGWHEEL);
        action.accept(MANGROVE_LARGE_COGWHEEL);
        action.accept(BAMBOO_LARGE_COGWHEEL);
        action.accept(CHERRY_LARGE_COGWHEEL);
        action.accept(WARPED_LARGE_COGWHEEL);
        action.accept(CRIMSON_LARGE_COGWHEEL);
    }

    public static boolean isChainConveyor(BlockState state){
        return AllBlocks.CHAIN_CONVEYOR.has(state)
                || BRASS_CHAIN_CONVEYOR.has(state)
                || COPPER_CHAIN_CONVEYOR.has(state)
                || RAILWAY_CHAIN_CONVEYOR.has(state)
                || CREATIVE_CHAIN_CONVEYOR.has(state)
                || INDUSTRIAL_IRON_CHAIN_CONVEYOR.has(state)
                || WEATHERED_IRON_CHAIN_CONVEYOR.has(state)
                || REFINED_RADIANCE_CHAIN_CONVEYOR.has(state)
                || SHADOW_STEEL_CHAIN_CONVEYOR.has(state);
    }
}
