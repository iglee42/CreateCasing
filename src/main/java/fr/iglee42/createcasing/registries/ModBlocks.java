package fr.iglee42.createcasing.registries;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.content.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;
import com.simibubi.create.content.kinetics.motor.CreativeMotorBlock;
import com.simibubi.create.content.kinetics.motor.CreativeMotorGenerator;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.*;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.blocks.customs.*;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedCogwheelBlock;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedPipeBlock;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedShaftBlock;
import fr.iglee42.createcasing.items.CustomVerticalGearboxItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;
import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CreateCasing.MODID);



    //public static final RegistryObject<Block> RAILWAY_ENCASED_SHAFT = BLOCKS.register("railway_encased_shaft", ()->new PublicEncasedShaftBlock(BlockBehaviour.Properties.copy(AllBlocks.ANDESITE_ENCASED_SHAFT.get()),AllBlocks.RAILWAY_CASING));
    //public static final RegistryObject<Block> COPPER_ENCASED_SHAFT = BLOCKS.register("copper_encased_shaft", ()->new PublicEncasedShaftBlock(BlockBehaviour.Properties.copy(AllBlocks.ANDESITE_ENCASED_SHAFT.get()),AllBlocks.COPPER_CASING));

    //SHAFTS
    public static final BlockEntry<PublicEncasedShaftBlock> RAILWAY_ENCASED_SHAFT = createShaft("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> COPPER_ENCASED_SHAFT = createShaft("copper",AllBlocks.COPPER_CASING::get, AllSpriteShifts.COPPER_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> SHADOW_ENCASED_SHAFT = createShaft("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> REFINED_RADIANCE_ENCASED_SHAFT = createShaft("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING);
    public static final BlockEntry<PublicEncasedShaftBlock> INDUSTRIAL_IRON_ENCASED_SHAFT = REGISTRATE.block("industrial_iron_encased_shaft", p -> new PublicEncasedShaftBlock(p, AllBlocks.INDUSTRIAL_IRON_BLOCK))
            .properties(p -> p.color(MaterialColor.PODZOL))
            .transform(encasedNoSpriteShaft("industrial_iron"))
            .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
            .transform(axeOrPickaxe())
            .register();

    //COGWHEELS
    public static final BlockEntry<PublicEncasedCogwheelBlock> RAILWAY_ENCASED_COGWHEEL = createCogwheel("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING,ModSprites.RAILWAY_ENCASED_COGWHEEL_SIDE,ModSprites.RAILWAY_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_ENCASED_COGWHEEL = createCogwheel("copper",AllBlocks.COPPER_CASING::get,AllSpriteShifts.COPPER_CASING,ModSprites.COPPER_ENCASED_COGWHEEL_SIDE,ModSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> SHADOW_ENCASED_COGWHEEL = createCogwheel("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING,ModSprites.SHADOW_ENCASED_COGWHEEL_SIDE,ModSprites.SHADOW_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> RADIANCE_ENCASED_COGWHEEL = createCogwheel("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING,ModSprites.RADIANCE_ENCASED_COGWHEEL_SIDE,ModSprites.RADIANCE_ENCASED_COGWHEEL_OTHERSIDE);
    public static final BlockEntry<PublicEncasedCogwheelBlock> INDUSTRIAL_IRON_ENCASED_COGWHEEL =REGISTRATE.block("industrial_iron_encased_cogwheel", p -> new PublicEncasedCogwheelBlock(p, false, AllBlocks.INDUSTRIAL_IRON_BLOCK))
            .properties(p -> p.color(MaterialColor.PODZOL).noOcclusion())
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(EncasingRegistry.addVariantTo(AllBlocks.COGWHEEL))
            .transform(axeOrPickaxe())
            .item()
            .transform(customItemModel())
            .register();;

    //LARGE COGWHEELS

    public static final BlockEntry<PublicEncasedCogwheelBlock> RAILWAY_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> COPPER_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("copper",AllBlocks.COPPER_CASING::get,AllSpriteShifts.COPPER_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> SHADOW_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> RADIANCE_ENCASED_COGWHEEL_LARGE = createLargeCogwheel("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING);
    public static final BlockEntry<PublicEncasedCogwheelBlock> INDUSTRIAL_IRON_ENCASED_COGWHEEL_LARGE = REGISTRATE.block("industrial_iron_encased_large_cogwheel", p -> new PublicEncasedCogwheelBlock(p, true, AllBlocks.INDUSTRIAL_IRON_BLOCK))
            .properties(p -> p.color(MaterialColor.PODZOL).noOcclusion())
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(EncasingRegistry.addVariantTo(AllBlocks.LARGE_COGWHEEL))
            .transform(axeOrPickaxe())
            .item()
            .transform(customItemModel())
            .register();

    //PIPES

    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_ANDESITE_FLUID_PIPE = createPipe("andesite",AllBlocks.ANDESITE_CASING::get,AllSpriteShifts.ANDESITE_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_BRASS_FLUID_PIPE = createPipe("brass",AllBlocks.BRASS_CASING::get,AllSpriteShifts.BRASS_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_RAILWAY_FLUID_PIPE = createPipe("railway",AllBlocks.RAILWAY_CASING::get,AllSpriteShifts.RAILWAY_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_SHADOW_FLUID_PIPE = createPipe("shadow_steel",AllBlocks.SHADOW_STEEL_CASING::get,AllSpriteShifts.SHADOW_STEEL_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_RADIANCE_FLUID_PIPE = createPipe("refined_radiance",AllBlocks.REFINED_RADIANCE_CASING::get,AllSpriteShifts.REFINED_RADIANCE_CASING);
    public static final BlockEntry<PublicEncasedPipeBlock> ENCASED_INDUSTRIAL_IRON_FLUID_PIPE = REGISTRATE.block("industrial_iron_encased_fluid_pipe", p -> new PublicEncasedPipeBlock(p, AllBlocks.INDUSTRIAL_IRON_BLOCK))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .transform(pickaxeOnly())
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::new))
            .loot((p, b) -> p.dropOther(b, AllBlocks.FLUID_PIPE.get()))
            .transform(EncasingRegistry.addVariantTo(AllBlocks.FLUID_PIPE))
            .register();

    public static final BlockEntry<CustomGearboxBlock> BRASS_GEARBOX = createGearbox("brass",AllSpriteShifts.BRASS_CASING,ModItems.VERTICAL_BRASS_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> COPPER_GEARBOX = createGearbox("copper",AllSpriteShifts.COPPER_CASING,ModItems.VERTICAL_COPPER_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> RAILWAY_GEARBOX = createGearbox("railway",AllSpriteShifts.RAILWAY_CASING,ModItems.VERTICAL_RAILWAY_GEARBOX);
    public static final BlockEntry<CustomGearboxBlock> INDUSTRIAL_IRON_GEARBOX = REGISTRATE.block("industrial_iron_gearbox", (p)->new CustomGearboxBlock(p,ModItems.VERTICAL_INDUSTRIAL_IRON_GEARBOX))
            .initialProperties(SharedProperties::stone)
            .properties(BlockBehaviour.Properties::noOcclusion)
            .properties(p -> p.color(MaterialColor.PODZOL))
            .transform(BlockStressDefaults.setNoImpact())
            .transform(pickaxeOnly())
            .blockstate((c, p) -> axisBlock(c, p, $ -> AssetLookup.partialBaseModel(c, p), true))
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<CustomMixerBlock> BRASS_MIXER = createMixer("brass");
    public static final BlockEntry<CustomMixerBlock> COPPER_MIXER = createMixer("copper");
    public static final BlockEntry<CustomMixerBlock> RAILWAY_MIXER = createMixer("railway");
    public static final BlockEntry<CustomMixerBlock> INDUSTRIAL_IRON_MIXER = createMixer("industrial_iron");

    public static final BlockEntry<CustomPressBlock> BRASS_PRESS = createPress("brass");
    public static final BlockEntry<CustomPressBlock> COPPER_PRESS = createPress("copper");
    public static final BlockEntry<CustomPressBlock> RAILWAY_PRESS = createPress("railway");
    public static final BlockEntry<CustomPressBlock> INDUSTRIAL_IRON_PRESS = createPress("industrial_iron");

    public static final BlockEntry<WoodenShaftBlock> OAK_SHAFT = createWoodenShaft("oak");
    public static final BlockEntry<WoodenShaftBlock> SPRUCE_SHAFT = createWoodenShaft("spruce");
    public static final BlockEntry<WoodenShaftBlock> BIRCH_SHAFT = createWoodenShaft("birch");
    public static final BlockEntry<WoodenShaftBlock> JUNGLE_SHAFT = createWoodenShaft("jungle");
    public static final BlockEntry<WoodenShaftBlock> ACACIA_SHAFT = createWoodenShaft("acacia");
    public static final BlockEntry<WoodenShaftBlock> DARK_OAK_SHAFT = createWoodenShaft("dark_oak");
    public static final BlockEntry<WoodenShaftBlock> CRIMSON_SHAFT = createWoodenShaft("crimson");
    public static final BlockEntry<WoodenShaftBlock> WARPED_SHAFT = createWoodenShaft("warped");

    public static final BlockEntry<GlassShaftBlock> GLASS_SHAFT = REGISTRATE.block("glass_shaft", GlassShaftBlock::new)
            .initialProperties(Material.GLASS)
            .properties(p -> p.color(MaterialColor.NONE)
                    .sound(SoundType.GLASS)
                    .noOcclusion())
            .transform(BlockStressDefaults.setNoImpact())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .simpleItem()
            .register();

    public static final BlockEntry<MLDEGShaftBlock> MLDEG_SHAFT = REGISTRATE.block("mldeg_shaft", MLDEGShaftBlock::new)
            .initialProperties(()-> Blocks.BLACKSTONE)
            .properties(p -> p.color(MaterialColor.NONE)
                    .sound(SoundType.STONE)
                    .noOcclusion())
            .transform(BlockStressDefaults.setNoImpact())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .onRegisterAfter(Registry.ITEM_REGISTRY, CreateCasing::hideItem)
            .simpleItem()
            .register();

    public static final BlockEntry<CreativeCogwheelBlock> CREATIVE_COGWHEEL =
            REGISTRATE.block("creative_cogwheel", CreativeCogwheelBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.color(MaterialColor.COLOR_PURPLE))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
                    .transform(pickaxeOnly())
                    .blockstate(new CreativeMotorGenerator()::generate)
                    .transform(BlockStressDefaults.setCapacity(16384.0))
                    .transform(BlockStressDefaults.setGeneratorSpeed(() -> Couple.create(0, 256)))
                    .addLayer(()-> RenderType::cutoutMipped)
                    .item()
                    .properties(p -> p.rarity(Rarity.EPIC))
                    .transform(customItemModel())
                    .register();


    public static <B extends EncasedShaftBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedNoSpriteShaft(String casing) {
        return builder -> encasedBase(builder, AllBlocks.SHAFT::get)
                .blockstate((c, p) -> axisBlock(c, p, blockState -> p.models()
                        .getExistingFile(p.modLoc("block/encased_shaft/block_" + casing)), true))
                .item()
                .model(AssetLookup.customBlockItemModel("encased_shaft", "item_" + casing))
                .build();
    }

    private static <B extends RotatedPillarKineticBlock, P> BlockBuilder<B, P> encasedBase(BlockBuilder<B, P> b,
                                                                                           Supplier<ItemLike> drop) {
        return b.initialProperties(SharedProperties::stone)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(BlockStressDefaults.setNoImpact())
                .loot((p, lb) -> p.dropOther(lb, drop.get()));
    }


    //METHODS
    public static BlockEntry<PublicEncasedShaftBlock> createShaft(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite){
        return REGISTRATE.block(name+"_encased_shaft", p -> new PublicEncasedShaftBlock(p, casing))
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BuilderTransformers.encasedShaft(name, () -> sprite))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
                .transform(axeOrPickaxe())
                .onRegisterAfter(Registry.ITEM_REGISTRY, CreateCasing::hideItem)
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
                .onRegisterAfter(Registry.ITEM_REGISTRY, CreateCasing::hideItem)
                .register();
    }

    public static BlockEntry<PublicEncasedCogwheelBlock> createLargeCogwheel(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite){
        return REGISTRATE.block(name+"_encased_large_cogwheel", p -> new PublicEncasedCogwheelBlock(p, true, casing))
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BuilderTransformers.encasedLargeCogwheel(name, () -> sprite))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.LARGE_COGWHEEL))
                .transform(axeOrPickaxe())
                .onRegisterAfter(Registry.ITEM_REGISTRY, CreateCasing::hideItem)
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
                .onRegisterAfter(Registry.ITEM_REGISTRY, CreateCasing::hideItem)
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
        return Objects.equals(name, "brass") || Objects.equals(name, "copper") || Objects.equals(name, "railway") ? REGISTRATE.block(name+"_mixer", CustomMixerBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.color(MaterialColor.STONE))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(axeOrPickaxe())
                .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
                .onRegisterAfter(Registry.ITEM_REGISTRY, v -> ItemDescription.useKey(v, "block.createcasing."+name+"_mixer"))
                .addLayer(() -> RenderType::cutoutMipped)
                .transform(BlockStressDefaults.setImpact(6.0))
                .item(AssemblyOperatorBlockItem::new)
                .transform(customItemModel())
                .register()
        :
                REGISTRATE.block(name+"_mixer", CustomMixerBlock::new)
                        .initialProperties(SharedProperties::stone)
                        .properties(p -> p.color(MaterialColor.STONE))
                        .properties(BlockBehaviour.Properties::noOcclusion)
                        .transform(axeOrPickaxe())
                        .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
                        .onRegisterAfter(Registry.ITEM_REGISTRY, v -> ItemDescription.useKey(v, "block.createcasing.custom_mixer"))
                        .addLayer(() -> RenderType::cutoutMipped)
                        .transform(BlockStressDefaults.setImpact(4.0))
                        .item(AssemblyOperatorBlockItem::new)
                        .transform(customItemModel())
                        .register();
    }

    public static BlockEntry<CustomPressBlock> createPress(String name){
        return REGISTRATE.block(name+"_press", CustomPressBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.color(MaterialColor.PODZOL))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(axeOrPickaxe())
                .blockstate(BlockStateGen.horizontalBlockProvider(true))
                .transform(BlockStressDefaults.setImpact(8.0))
                .onRegisterAfter(Registry.ITEM_REGISTRY, v -> ItemDescription.useKey(v, "block.createcasing.custom_press"))
                .item(AssemblyOperatorBlockItem::new)
                .transform(customItemModel())
                .register();
    }

    public static BlockEntry<WoodenShaftBlock> createWoodenShaft(String name){
        return REGISTRATE.block(name+"_shaft", WoodenShaftBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.color(MaterialColor.METAL))
                .transform(BlockStressDefaults.setNoImpact())
                .transform(axeOnly())
                .blockstate(BlockStateGen.axisBlockProvider(false))
                .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                .simpleItem()
                .register();
    }

    public static void registerEncasedShafts() {
        forEachShaft(shaft-> {
            Create.REGISTRATE.getAll(Registry.BLOCK_REGISTRY).stream().filter(r -> r.getId().getPath().endsWith("_casing")).forEach(c -> {
                String casing = c.getId().getPath().replace("_casing", "");
                try {
                    CTSpriteShiftEntry sprite = (CTSpriteShiftEntry) AllSpriteShifts.class.getField(c.getId().getPath().toUpperCase()).get(new CTSpriteShiftEntry(AllCTTypes.OMNIDIRECTIONAL));
                    REGISTRATE.block(casing + "_encased_" + shaft.getId().getPath(), p -> new CustomEncasedShaft(p, c, shaft))
                            .properties(p -> p.color(MaterialColor.PODZOL))
                            .transform(BuilderTransformers.encasedShaft(casing, () -> sprite))
                            .transform(EncasingRegistry.addVariantTo(shaft))
                            .transform(axeOrPickaxe())
                            .loot((l,s)->l.dropOther(s,s.getShaft().get().asItem()))
                            .onRegisterAfter(Registry.ITEM_REGISTRY, CreateCasing::hideItem)
                            .register();
                } catch (NoSuchFieldException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            });
            REGISTRATE.block("industrial_iron_encased_"+shaft.getId().getPath(), p -> new CustomEncasedShaft(p, AllBlocks.INDUSTRIAL_IRON_BLOCK,shaft))
                    .properties(p -> p.color(MaterialColor.PODZOL))
                    .transform(encasedNoSpriteShaft("industrial_iron"))
                    .transform(EncasingRegistry.addVariantTo(shaft))
                    .transform(axeOrPickaxe())
                    .loot((l,s)->l.dropOther(s,s.getShaft().get().asItem()))
                    .onRegisterAfter(Registry.ITEM_REGISTRY, CreateCasing::hideItem)
                    .register();
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
        action.accept(WARPED_SHAFT);
        action.accept(CRIMSON_SHAFT);
        action.accept(GLASS_SHAFT);
        action.accept(MLDEG_SHAFT);
    }
}
