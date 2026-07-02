package fr.iglee42.createcasing.registries;

import com.simibubi.create.*;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.contraptions.actors.harvester.HarvesterMovementBehaviour;
import com.simibubi.create.content.contraptions.actors.plough.PloughBlock;
import com.simibubi.create.content.contraptions.actors.plough.PloughMovementBehaviour;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceBlock;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceMovement;
import com.simibubi.create.content.contraptions.actors.roller.RollerBlockItem;
import com.simibubi.create.content.contraptions.actors.roller.RollerMovementBehaviour;
import com.simibubi.create.content.decoration.encasing.*;
import com.simibubi.create.content.fluids.pipes.*;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlock;
import com.simibubi.create.content.fluids.tank.*;
import com.simibubi.create.content.kinetics.crank.ValveHandleBlock;
import com.simibubi.create.content.kinetics.deployer.DeployerMovementBehaviour;
import com.simibubi.create.content.kinetics.deployer.DeployerMovingInteraction;
import com.simibubi.create.content.kinetics.drill.DrillMovementBehaviour;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;
import com.simibubi.create.content.kinetics.saw.SawMovementBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.simibubi.create.content.logistics.depot.MountedDepotInteractionBehaviour;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.block.ItemUseOverrides;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.*;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullBiFunction;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.blocks.AutoClutchBlock;
import fr.iglee42.createcasing.blocks.ConfigurableGearboxBlock;
import fr.iglee42.createcasing.blocks.CreativeCogwheelBlock;
import fr.iglee42.createcasing.blocks.cogwheels.CustomCogwheelBlock;
import fr.iglee42.createcasing.blocks.customs.*;
import fr.iglee42.createcasing.blocks.fluids.*;
import fr.iglee42.createcasing.blocks.publics.PublicChainConveyorBlock;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedCogwheelBlock;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedPipeBlock;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedShaftBlock;
import fr.iglee42.createcasing.blocks.shafts.*;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.config.CCStress;
import fr.iglee42.createcasing.fluids.FluidSet;
import fr.iglee42.createcasing.fluids.FluidSets;
import fr.iglee42.createcasing.items.WoodenCogwheelBlockItem;
import fr.iglee42.createcasing.registries.generators.CustomFluidTankGenerator;
import fr.iglee42.createcasing.registries.generators.CustomSawGenerator;
import fr.iglee42.createcasing.registries.generators.CustomSmartFluidPipeGenerator;
import fr.iglee42.createcasing.registries.generators.CustomWhistleGenerator;
import fr.iglee42.createcasing.transmissions.TransmissionSet;
import fr.iglee42.createcasing.transmissions.TransmissionSets;
import fr.iglee42.createcasing.utils.CasingBuilderTransformers;
import net.createmod.catnip.data.Couple;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;

import java.util.Objects;
import java.util.function.*;

import static com.simibubi.create.api.behaviour.display.DisplaySource.displaySource;
import static com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour.interactionBehaviour;
import static com.simibubi.create.api.behaviour.movement.MovementBehaviour.movementBehaviour;
import static com.simibubi.create.api.contraption.storage.fluid.MountedFluidStorageType.mountedFluidStorage;
import static com.simibubi.create.api.contraption.storage.item.MountedItemStorageType.mountedItemStorage;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;
import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;
import static fr.iglee42.createcasing.registries.EncasedBlockStateGens.*;

public class EncasedBlocks {

    static {
        REGISTRATE.setCreativeTab(EncasedCreativeModeTabs.MAIN_TAB);
    }


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
                    .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                    .item()
                    .properties(p -> p.rarity(Rarity.EPIC))
                    .transform(customItemModel())
                    .register();


    //METHODS

    public static BlockEntry<CasingBlock> createCasing(String name, CTSpriteShiftEntry connectedTexturesSprite){
        return REGISTRATE.block(name+"_casing", CasingBlock::new)
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(CasingBuilderTransformers.casing(() -> connectedTexturesSprite))
                .blockstate((c,p)->p.simpleBlock(c.get(),p.models().cubeAll(c.getName(),name.equalsIgnoreCase("creative") ? Create.asResource("block/"+c.getId().getPath()) : CreateCasing.asResource("block/casing/"+name))))
                .simpleItem()
                .register();
    }

    private static BlockEntry<PublicEncasedShaftBlock> createEncasedShaft(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite){
        return createEncasedShaft(AllBlocks.SHAFT,name,casing,sprite,PublicEncasedShaftBlock::new);
    }

    private static <E extends ShaftBlock, T extends EncasedShaftBlock> BlockEntry<T> createEncasedShaft(BlockEntry<E> shaft, String name, Supplier<Block> casing, CTSpriteShiftEntry sprite, NonNullBiFunction<BlockBehaviour.Properties,Supplier<Block>, T> factory){
        String s = shaft.getId().getPath().replace("_shaft","");
        return REGISTRATE.block(name+"_encased"+(shaft.equals(AllBlocks.SHAFT) ? "" : "_"+ s)+"_shaft", p -> factory.apply(p,casing))
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(CasingBuilderTransformers.encasedShaft(shaft,name, () -> sprite))
                .transform(EncasingRegistry.addVariantTo(shaft))
                .transform(axeOrPickaxe())
                .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem)
                .register();
    }

    private static BlockEntry<PublicEncasedCogwheelBlock> createEncasedCogwheel(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite, CTSpriteShiftEntry sideSprite, CTSpriteShiftEntry otherSideSprite){
        return createEncasedCogwheel(AllBlocks.COGWHEEL,name,casing,sprite,sideSprite,otherSideSprite,(p, c)->new PublicEncasedCogwheelBlock(p,false,c));
    }

    private static <E extends Block & EncasableBlock, T extends EncasedCogwheelBlock>  BlockEntry<T> createEncasedCogwheel(BlockEntry<E> cogwheel, String name, Supplier<Block> casing, CTSpriteShiftEntry sprite, CTSpriteShiftEntry sideSprite, CTSpriteShiftEntry otherSideSprite, NonNullBiFunction<BlockBehaviour.Properties,Supplier<Block>, T> factory){
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

    private static BlockEntry<PublicEncasedCogwheelBlock> createEncasedLargeCogwheel(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite){
        return createEncasedLargeCogwheel(AllBlocks.LARGE_COGWHEEL,name,casing,sprite,(p, c)->new PublicEncasedCogwheelBlock(p,true,c));
    }

    private static  <E extends CogWheelBlock, T extends EncasedCogwheelBlock>  BlockEntry<T> createEncasedLargeCogwheel(BlockEntry<E> cogwheel, String name, Supplier<Block> casing, CTSpriteShiftEntry sprite, NonNullBiFunction<BlockBehaviour.Properties,Supplier<Block>, T> factory){
        String s = cogwheel.getId().getPath().replace("_large_cogwheel","");
        return REGISTRATE.block(name+"_encased"+(cogwheel.equals(AllBlocks.LARGE_COGWHEEL) ? "" : "_"+ s)+"_large_cogwheel", p ->factory.apply(p,casing))
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(CasingBuilderTransformers.encasedLargeCogwheel(cogwheel,name, () -> sprite))
                .transform(EncasingRegistry.addVariantTo(cogwheel))
                .transform(axeOrPickaxe())
                .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem)
                .register();
    }

    private static BlockEntry<PublicEncasedPipeBlock> createEncasedPipe(String name, Supplier<Block> casing, CTSpriteShiftEntry sprite){;
        BlockBuilder<PublicEncasedPipeBlock,CreateRegistrate> builder = REGISTRATE.block(name+"_encased_fluid_pipe", p -> new PublicEncasedPipeBlock(p, casing))
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(axeOrPickaxe())
                .blockstate(EncasedBlockStateGens.encasedPipe(name))
                .onRegister(EncasedBlockRegistrationHelpers.pipeAttachmentModel())
                .loot((p, b) -> p.dropOther(b, AllBlocks.FLUID_PIPE.get()))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.FLUID_PIPE))
                .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem);
        builder = connectedTexture(builder,sprite,(block,cc)->cc.make(block, sprite,
                (s, f) -> !s.getValue(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(f))));
        return builder.register();
    }

    private static BlockEntry<CustomGearboxBlock> createGearbox(String name, CTSpriteShiftEntry sprite, Supplier<BlockItem> item){
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
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
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
                .item(AssemblyOperatorBlockItem::new)
                .model((c,p)->p.getBuilder(c.getName()).parent(pressModel(p,name,true)))
                .build()
                .register();
    }

    private static BlockEntry<? extends ShaftBlock> createShaft(String name, Function<BlockBehaviour.Properties, ? extends ShaftBlock> factory){
        return REGISTRATE.block(name+"_shaft", (p)->factory != null ? factory.apply(p) : new CustomShaftBlock(p))
                .initialProperties(SharedProperties::wooden)
                .properties(p -> p.mapColor(MapColor.METAL))
                .transform(CCStress.setNoImpact())
                .transform(axeOrPickaxe())
                .blockstate(EncasedBlockStateGens.shaft(name))
                .onRegister(EncasedBlockRegistrationHelpers.bracketedKineticBlockModel())
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(shaftModel(p, name))))
                .build()
                .register();
    }


    private static BlockEntry<? extends CogWheelBlock> createCogwheel(String name, BiFunction<BlockBehaviour.Properties,Boolean, ? extends CogWheelBlock> factory){
        return REGISTRATE.block(name+"_cogwheel", p->factory != null ? factory.apply(p,false) : CustomCogwheelBlock.small(p))
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.sound(SoundType.WOOD).mapColor(MapColor.DIRT))
                .transform(CCStress.setNoImpact())
                .transform(axeOrPickaxe())
                .blockstate(EncasedBlockStateGens.cogwheel(name))
                .onRegister(EncasedBlockRegistrationHelpers.bracketedKineticBlockModel())
                .item(WoodenCogwheelBlockItem::new)
                .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(cogwheelModel(p, name,true))))
                .build()
                .register();
    }

    private static BlockEntry<? extends CogWheelBlock> createLargeCogwheel(String name, BiFunction<BlockBehaviour.Properties,Boolean, ? extends CogWheelBlock> factory) {
        return REGISTRATE.block(name+"_large_cogwheel", p->factory != null ? factory.apply(p,true) : CustomCogwheelBlock.large(p))
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.sound(SoundType.WOOD).mapColor(MapColor.DIRT))
                .transform(axeOrPickaxe())
                .transform(CCStress.setNoImpact())
                .blockstate(EncasedBlockStateGens.largeCogwheel(name))
                .onRegister(EncasedBlockRegistrationHelpers.bracketedKineticBlockModel())
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

    public static BlockEntry<CustomChainDriveBlock> createChainDrive(String name){
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

    public static BlockEntry<CustomGearshiftBlock> createGearshift(String name) {
        return REGISTRATE.block(name+"_gearshift", CustomGearshiftBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.noOcclusion()
                        .mapColor(MapColor.PODZOL))
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .transform(CCStress.setNoImpact())
                .transform(axeOrPickaxe())
                .blockstate((c, p) -> EncasedBlockStateGens.axisBlock(c,p,gearshiftModel(p,name),false))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(gearshiftItemModel(p,name)))
                .build()
                .register();
    }

    public static BlockEntry<CustomClutchBlock> createClutch(String name) {
        return REGISTRATE.block(name+"_clutch", CustomClutchBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.noOcclusion()
                        .mapColor(MapColor.PODZOL))
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .transform(CCStress.setNoImpact())
                .transform(axeOrPickaxe())
                .blockstate((c, p) ->EncasedBlockStateGens.axisBlock(c,p,clutchModel(p,name),false))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(clutchItemModel(p,name)))
                .build()
                .register();
    }

    public static BlockEntry<CustomDeployerBlock> createDeployer(String name){
        return REGISTRATE.block(name + "_deployer", CustomDeployerBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(axeOrPickaxe())
                .blockstate((c,p)->EncasedBlockStateGens.directionalAxisBlock(c,p,deployerModel(p,name)))
                .transform(CCStress.setImpact(4.0))
                .onRegister(movementBehaviour(new DeployerMovementBehaviour()))
                .onRegister(interactionBehaviour(new DeployerMovingInteraction()))
                .item(AssemblyOperatorBlockItem::new)
                .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
                .model((c,p)->p.getBuilder(c.getName()).parent(deployerItemModel(p,name)))
                .build()
                .register();
    }

    public static BlockEntry<PortableStorageInterfaceBlock> createPortableStorageInterface(String name) {
        return REGISTRATE.block(name + "_portable_storage_interface", PortableStorageInterfaceBlock::forItems)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(axeOrPickaxe())
                .blockstate((c, p) -> p.directionalBlock(c.get(), storageInterfaceModel(p,name,false)))
                .onRegister(movementBehaviour(new PortableStorageInterfaceMovement()))
                .item()
                .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
                .model((c,p)->p.getBuilder(c.getName()).parent(storageInterfaceModel(p,name,true)))
                .build()
                .register();
    }

    public static BlockEntry<CustomEncasedFanBlock> createEncasedFan(String name) {
        return REGISTRATE.block(name + "_encased_fan", CustomEncasedFanBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .blockstate((c,p)->p.directionalBlock(c.get(),fanModel(p,name)))
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .transform(axeOrPickaxe())
                .transform(CCStress.setImpact(2.0))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(fanItemModel(p,name)))
                .build()
                .register();
    }

    public static BlockEntry<CustomHarvesterBlock> createHarvester(String name){
        return REGISTRATE.block(name + "_mechanical_harvester", CustomHarvesterBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.mapColor(MapColor.METAL)
                        .forceSolidOn())
                .transform(axeOrPickaxe())
                .onRegister(movementBehaviour(new HarvesterMovementBehaviour()))
                .blockstate((c,p)->p.horizontalBlock(c.get(),harvesterModel(p,name,false)))
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .item()
                .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
                .model((c,p)->p.getBuilder(c.getName()).parent(harvesterModel(p,name,true)))
                .build()
                .register();
    }

    public static BlockEntry<PloughBlock> createPlough(String name){
        return REGISTRATE.block(name+"_mechanical_plough", PloughBlock::new)
			.initialProperties(SharedProperties::stone)
			.properties(p -> p.mapColor(MapColor.COLOR_GRAY)
				.forceSolidOn())
			.transform(axeOrPickaxe())
			.onRegister(movementBehaviour(new PloughMovementBehaviour()))
            .blockstate((c,p)->p.horizontalBlock(c.get(),ploughModel(p,name)))
			.item()
			.tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .model((c,p)->p.getBuilder(c.getName()).parent(new ModelFile.ExistingModelFile(CreateCasing.asResource("block/mechanical_plough/"+name),p.existingFileHelper)))
			.build()
			.register();
    }
    public static BlockEntry<CustomRollerBlock> createRoller(String name){
        return REGISTRATE.block(name+"_mechanical_roller", CustomRollerBlock::new)
			.initialProperties(SharedProperties::stone)
			.properties(p -> p.mapColor(MapColor.COLOR_GRAY)
				.noOcclusion())
			.transform(axeOrPickaxe())
			.onRegister(movementBehaviour(new RollerMovementBehaviour()))
			.blockstate((c,p)->p.horizontalBlock(c.get(),rollerModel(p,name,false)))
			.transform(EncasedBlockRegistrationHelpers::cutoutMipped)
			.item(RollerBlockItem::new)
			.tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .model((c,p)->p.getBuilder(c.getName()).parent(rollerModel(p,name,true)))
            .build()
			.register();
    }

    public static BlockEntry<CustomSawBlock> createSaw(String name){
        return REGISTRATE.block(name+"_mechanical_saw", CustomSawBlock::new)
                .initialProperties(SharedProperties::stone)
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(axeOrPickaxe())
                .blockstate(new CustomSawGenerator(name)::generate)
                .transform(CCStress.setImpact(4.0))
                .onRegister(movementBehaviour(new SawMovementBehaviour()))
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .item()
                .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
                .model((c,p)->p.getBuilder(c.getName()).parent(sawItemModel(p,name)))
                .build()
                .register();
    }

    public static BlockEntry<CustomDrillBlock> createDrill(String name){
        return REGISTRATE.block(name+"_mechanical_drill", CustomDrillBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.mapColor(MapColor.PODZOL))
                .transform(axeOrPickaxe())
                .blockstate((c,p)->p.directionalBlock(c.get(),drillModel(p,name,false)))
                .transform(CCStress.setImpact(4.0))
                .onRegister(movementBehaviour(new DrillMovementBehaviour()))
                .item()
                .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
                .model((c,p)->p.getBuilder(c.getName()).parent(drillModel(p,name,true)))
                .build()
                .register();
    }

    public static BlockEntry<AutoClutchBlock> createAutoClutch(String name) {
        return REGISTRATE.block(name+"_automatic_clutch", AutoClutchBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.noOcclusion()
                        .mapColor(MapColor.PODZOL))
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .transform(CCStress.setNoImpact())
                .transform(axeOrPickaxe())
                .blockstate((c, p) ->EncasedBlockStateGens.axisBlock(c,p,autoClutchModel(p,name),false))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(autoClutchItemModel(p,name)))
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

    public static void register() {

        CreateCasing.KJS_HANDLER.dispatchRegisterEvent();

        CasingSets.getSets().forEach(set->{
            if (set.doesGenerateCasing()){
                set.setCasing(createCasing(set.getName(),set.getConnectedTextureSprite()));
            }

            Supplier<? extends Block> casing = set.getCasingSupplier();

            if (set.doesGenerateShaft())
                set.setShaft(createEncasedShaft(set.getName(), (Supplier<Block>) casing,set.getConnectedTextureSprite()));

            if (set.doesGenerateCogwheel())
                set.setCogwheel(createEncasedCogwheel(set.getName(), (Supplier<Block>) casing,set.getConnectedTextureSprite(),set.getCogSideSprite(),set.getCogOtherSideSprite()));

            if (set.doesGenerateLargeCogwheel())
                set.setLargeCogwheel(createEncasedLargeCogwheel(set.getName(), (Supplier<Block>) casing,set.getConnectedTextureSprite()));

            if (set.doesGenerateFluidPipe())
                set.setFluidPipe(createEncasedPipe(set.getName(), (Supplier<Block>) casing,set.getConnectedTextureSprite()));

            if (set.doesGenerateGearbox())
                set.setGearbox(createGearbox(set.getName(),set.getConnectedTextureSprite(),()->set.getVerticalGearboxItem()));

            if (set.doesGeneratePress())
                set.setPress(createPress(set.getName()));

            if (set.doesGenerateMixer())
                set.setMixer(createMixer(set.getName()));

            if (set.doesGenerateDepot())
                set.setDepot(createDepot(set.getName()));

            if (set.doesGenerateChainDrive())
                set.setChainDrive(createChainDrive(set.getName()));

            if (set.doesGenerateChainGearshift())
                set.setChainGearshift(createChainGearshift(set.getName()));

            if (set.doesGenerateConfigurableGearbox())
                set.setConfigurableGearbox(createConfigurableGearbox(set.getName(),set.getConnectedTextureSprite()));

            if (set.doesGenerateChainConveyor())
                set.setChainConveyor(createChainConveyor(set.getName()));

            if (set.doesGenerateGearshift())
                set.setGearshift(createGearshift(set.getName()));

            if (set.doesGenerateClutch())
                set.setClutch(createClutch(set.getName()));

            if (set.doesGenerateAutoClutch())
                set.setAutoClutch(createAutoClutch(set.getName()));

            if (set.doesGenerateDeployer())
                set.setDeployer(createDeployer(set.getName()));

            if (set.doesGenerateStorageInterface())
                set.setStorageInterface(createPortableStorageInterface(set.getName()));

            if (set.doesGenerateEncasedFan())
                set.setEncasedFan(createEncasedFan(set.getName()));

            if (set.doesGenerateHarvester())
                set.setHarvester(createHarvester(set.getName()));

            if (set.doesGenerateSaw())
                set.setSaw(createSaw(set.getName()));

            if (set.doesGenerateDrill())
                set.setDrill(createDrill(set.getName()));

            if (set.doesGeneratePlough())
                set.setPlough(createPlough(set.getName()));

            if (set.doesGenerateRoller())
                set.setRoller(createRoller(set.getName()));
        });

        TransmissionSets.getSets().forEach(set->{
            if (set.doesGenerateShaft())
                set.setShaft(createShaft(set.getName(), set.getShaftConstructor()));

            if (set.doesGenerateCogwheel())
                set.setCogwheel(createCogwheel(set.getName(), set.getCogwheelConstructor()));

            if (set.doesGenerateLargeCogwheel())
                set.setLargeCogwheel(createLargeCogwheel(set.getName(), set.getCogwheelConstructor()));
        });
        registerFluidSets();
        CasingSets.getSets().stream().filter(CasingSet::doesGenerateEncasedCustomShaft).forEach(set->{
            TransmissionSets.getSets().stream().filter(Predicate.not(TransmissionSet::isNotEncasable)).filter(TransmissionSet::doesGenerateShaft).forEach(tset->{
                if (tset.getShaftSupplier() != null){
                    createEncasedShaft(tset.getShaftSupplier(),set.getName(), ()->set.getCasing(),set.getConnectedTextureSprite(),(p, s)->new EncasedCustomShaftBlock(p,s,tset.getShaftSupplier()));
                }
            });
        });

        CasingSets.getSets().stream().filter(CasingSet::doesGenerateEncasedCustomCogwheel).forEach(set->{
            TransmissionSets.getSets().stream().filter(Predicate.not(TransmissionSet::isNotEncasable)).filter(TransmissionSet::doesGenerateCogwheel).forEach(tset->{
                if (tset.getCogwheelSupplier() != null){
                    createEncasedCogwheel(tset.getCogwheelSupplier(),set.getName(), ()->set.getCasing(),set.getConnectedTextureSprite(),set.getCogSideSprite(),set.getCogOtherSideSprite(),(p,s)->new EncasedCustomCogwheelBlock(p,false,s,tset.getCogwheelSupplier()));
                }
            });
        });

        CasingSets.getSets().stream().filter(CasingSet::doesGenerateEncasedCustomLargeCogwheel).forEach(set->{
            TransmissionSets.getSets().stream().filter(Predicate.not(TransmissionSet::isNotEncasable)).filter(TransmissionSet::doesGenerateLargeCogwheel).forEach(tset->{
                if (tset.getLargeCogwheelSupplier() != null){
                    createEncasedLargeCogwheel(tset.getLargeCogwheelSupplier(),set.getName(), ()->set.getCasing(),set.getConnectedTextureSprite(),(p,s)->new EncasedCustomCogwheelBlock(p,true,s,tset.getLargeCogwheelSupplier()));
                }
            });
        });
    }

    public static boolean isWoodenShaftHasState(BlockState state) {

        return state.getBlock() instanceof WoodenShaftBlock;

    }

    public static void forEachShaft(Consumer<BlockEntry<? extends ShaftBlock>> action){
        TransmissionSets.getSets().stream().filter(TransmissionSet::doesGenerateShaft).forEach(set->action.accept(set.getShaftSupplier()));
    }

    public static void forEachCogwheel(Consumer<BlockEntry<? extends CogWheelBlock>> action){
        TransmissionSets.getSets().stream().filter(TransmissionSet::doesGenerateCogwheel).forEach(set->action.accept(set.getCogwheelSupplier()));
    }

    public static void forEachLargeCogwheel(Consumer<BlockEntry<? extends CogWheelBlock>> action){
        TransmissionSets.getSets().stream().filter(TransmissionSet::doesGenerateLargeCogwheel).forEach(set->action.accept(set.getLargeCogwheelSupplier()));
    }

    private static void registerFluidSets(){
        FluidSets.getSets().forEach(set->{
            if (set.doesGenerateFluidPipe()) {
                BlockEntry<CustomFluidPipeBlock> pipe = createFluidPipe(set.getName());
                set.setFluidPipe(pipe,createGlassFluidPipe(set.getName(),pipe));
            }
            if (set.doesGeneratePump())
                set.setPump(createFluidPump(set.getName()));

            if (set.doesGenerateSmartFluidPipe())
                set.setSmartFluidPipe(createSmartFluidPipe(set.getName()));

            if (set.doesGenerateFluidTank())
                set.setFluidTank(createFluidTank(set.getName(),set.getTankSideSprite(),set.getTankTopSprite(),set.getTankInnerSprite()));

            if (set.doesGenerateSteamEngine())
                set.setSteamEngine(createSteamEngine(set.getName()));

            if (set.doesGenerateItemDrain())
                set.setItemDrain(createItemDrain(set.getName()));

            if (set.doesGenerateFluidValve())
                set.setFluidValve(createFluidValve(set.getName()));

            if (set.doesGenerateValveHandle())
                set.setValveHandle(createValveHandle(set.getName()));

            if (set.doesGenerateHosePulley())
                set.setHosePulley(createHosePulley(set.getName()));

            if (set.doesGeneratePortableFluidInterface())
                set.setPortableFluidInterface(createPortableFluidInterface(set.getName()));

            if (set.doesGenerateWhistle())
                set.setWhistle(createSteamWhistle(set.getName()));

            if (set.doesGenerateSpout())
                set.setSpout(createSpout(set.getName()));
        });

        CasingSets.getSets().stream().filter(CasingSet::doesGenerateEncasedCustomPipe).forEach(set->{
            FluidSets.getSets().stream().filter(Predicate.not(FluidSet::isNotEncasable)).filter(FluidSet::doesGenerateFluidPipe).forEach(fset->{
                if (fset.getFluidPipeSupplier() != null){
                    createEncasedCustomPipe(fset.getName(),set.getName(), ()->set.getCasing(),fset.getFluidPipeSupplier(),set.getConnectedTextureSprite());
                }
            });
        });
    }


    public static BlockEntry<CustomFluidPipeBlock> createFluidPipe(String name) {
        return REGISTRATE.block(name+"_fluid_pipe", CustomFluidPipeBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.forceSolidOff())
                .transform(pickaxeOnly())
                .blockstate(EncasedBlockStateGens.pipe(name))
                .onRegister(EncasedBlockRegistrationHelpers.encasedPipeAttachmentModel(name))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(new ModelFile.UncheckedModelFile(CreateCasing.asResource("block/fluid_pipe/"+name+"/item"))))
                .build()
                .register();
    }


    public static BlockEntry<CustomGlassFluidPipeBlock> createGlassFluidPipe(String name, BlockEntry<? extends FluidPipeBlock> pipe){
            return REGISTRATE.block(name+"_glass_fluid_pipe", CustomGlassFluidPipeBlock::new)
                    .initialProperties(SharedProperties::copperMetal)
                    .properties(p -> p.noOcclusion())
                    .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                    .transform(pickaxeOnly())
                    .blockstate((c, p) -> {
                        p.getVariantBuilder(c.getEntry())
                                .forAllStatesExcept(state -> {
                                    Direction.Axis axis = state.getValue(BlockStateProperties.AXIS);
                                    return ConfiguredModel.builder()
                                            .modelFile(p.models()
                                                    .getExistingFile(p.modLoc("block/fluid_pipe/"+name+"/window")))
                                            .uvLock(false)
                                            .rotationX(axis == Direction.Axis.Y ? 0 : 90)
                                            .rotationY(axis == Direction.Axis.X ? 90 : 0)
                                            .build();
                                }, BlockStateProperties.WATERLOGGED);
                    })
                    .onRegister(EncasedBlockRegistrationHelpers.encasedPipeAttachmentModel(name))
                    .loot((p, b) -> p.dropOther(b, pipe.get()))
                    .register();
    }

    public static BlockEntry<CustomPumpBlock> createFluidPump(String name) {
        return REGISTRATE.block(name+"_mechanical_pump", CustomPumpBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.mapColor(MapColor.STONE))
                .transform(pickaxeOnly())
                .blockstate(pump(name))
                .onRegister(EncasedBlockRegistrationHelpers.encasedPipeAttachmentModel(name))
                .transform(CCStress.setImpact(4.0))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(pumpModel(p,name,true)))
                .build()
                .register();
    }

    public static BlockEntry<CustomSmartFluidPipeBlock> createSmartFluidPipe(String name) {
        return REGISTRATE.block(name+"_smart_fluid_pipe", CustomSmartFluidPipeBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.mapColor(MapColor.TERRACOTTA_YELLOW))
                .transform(pickaxeOnly())
                .blockstate(new CustomSmartFluidPipeGenerator(name)::generate)
                .onRegister(EncasedBlockRegistrationHelpers.encasedPipeAttachmentModel(name))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(smartPipeModel(p,name,true)))
                .build()
                .register();
    }

    public static BlockEntry<CustomFluidTankBlock> createFluidTank(String name,CTSpriteShiftEntry sideSprite, CTSpriteShiftEntry topSprite, CTSpriteShiftEntry innerSprite) {
        return REGISTRATE.block(name+"_fluid_tank", CustomFluidTankBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.noOcclusion()
                        .isRedstoneConductor((p1, p2, p3) -> true))
                .transform(pickaxeOnly())
                .blockstate(new CustomFluidTankGenerator(name)::generate)
                .onRegister(EncasedBlockRegistrationHelpers.encasedFluidTankModel(sideSprite, topSprite, innerSprite))
                .transform(displaySource(AllDisplaySources.BOILER))
                .transform(mountedFluidStorage(AllMountedStorageTypes.FLUID_TANK))
                .onRegister(movementBehaviour(new FluidTankMovementBehavior()))
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .item(FluidTankItem::new)
                .model(AssetLookup.customBlockItemModel("fluid_tank",name, "block_single_window"))
                .build()
                .register();
    }

    public static BlockEntry<CustomSteamEngineBlock> createSteamEngine(String name) {
        return REGISTRATE.block(name+"_steam_engine", CustomSteamEngineBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .transform(pickaxeOnly())
                .blockstate((c, p) -> p.horizontalFaceBlock(c.get(), steamEngineModel(p,name,false)))
                .transform(CCStress.setCapacity(1024.0))
                .onRegister(BlockStressValues.setGeneratorSpeed(64, true))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(steamEngineModel(p,name,true)))
                .build()
                .register();
    }

    public static BlockEntry<CustomItemDrainBlock> createItemDrain(String name) {
        return REGISTRATE.block(name+"_item_drain", CustomItemDrainBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .transform(pickaxeOnly())
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .blockstate((c, p) -> p.simpleBlock(c.get(), itemDrainModel(p,name)))
                .item()
                .model(AssetLookup.customBlockItemModel("item_drain",name))
                .build()
                .register();
    }

    public static BlockEntry<CustomFluidValveBlock> createFluidValve(String name) {
       return REGISTRATE.block(name+"_fluid_valve", CustomFluidValveBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .transform(pickaxeOnly())
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .blockstate((c, p) -> BlockStateGen.directionalAxisBlock(c, p,
                        (state, vertical) -> fluidValveModel(p, name, false,vertical,state.getValue(FluidValveBlock.ENABLED))))
                .onRegister(EncasedBlockRegistrationHelpers.encasedPipeAttachmentModel(name))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(fluidValveModel(p,name,true,false,true)))
                .build()
                .register();
    }

    public static BlockEntry<ValveHandleBlock> createValveHandle(String name) {
        return REGISTRATE.block(name+"_valve_handle", ValveHandleBlock::copper)
                .transform(pickaxeOnly())
                .transform(valveHandle(name))
                .transform(CCStress.setCapacity(8.0))
                .register();
    }

    public static BlockEntry<CustomHosePulleyBlock> createHosePulley(String name) {
        return REGISTRATE.block(name+"_hose_pulley", CustomHosePulleyBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .transform(pickaxeOnly())
                .blockstate(hosePulley(name))
                .transform(CCStress.setImpact(4.0))
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(hosePulleyModel(p,name,true)))
                .build()
                .register();
    }

    public static BlockEntry<PortableStorageInterfaceBlock> createPortableFluidInterface(String name) {
        return REGISTRATE.block(name+"_portable_fluid_interface", PortableStorageInterfaceBlock::forFluids)
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY))
                .transform(axeOrPickaxe())
                .blockstate((c, p) -> p.directionalBlock(c.get(), fluidInterfaceModel(p,name,false)))
                .onRegister(movementBehaviour(new PortableStorageInterfaceMovement()))
                .item()
                .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
                .model((c,p)->p.getBuilder(c.getName()).parent(fluidInterfaceModel(p,name,true)))
                .build()
                .register();
    }


    private static <B extends ValveHandleBlock> NonNullUnaryOperator<BlockBuilder<B, CreateRegistrate>> valveHandle(
           String name) {
        return b -> b.initialProperties(SharedProperties::copperMetal)
                .blockstate((c, p) -> {
                    p.directionalBlock(c.get(), p.models()
                            .withExistingParent(name + "_valve_handle", Create.asResource("block/valve_handle"))
                            .texture("3", p.modLoc("block/valve_handle/" + name)));
                })
                .tag(AllTags.AllBlockTags.BRITTLE.tag, AllTags.AllBlockTags.VALVE_HANDLES.tag)
                .onRegister(BlockStressValues.setGeneratorSpeed(32))
                .onRegister(ItemUseOverrides::addBlock)
                .item()
                .tag(AllTags.AllItemTags.VALVE_HANDLES.tag)
                .build();
    }


    public static BlockEntry<CustomWhistleBlock> createSteamWhistle(String name){
        return REGISTRATE.block(name+"_steam_whistle", CustomWhistleBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.mapColor(MapColor.GOLD))
                .transform(pickaxeOnly())
                .blockstate(new CustomWhistleGenerator(name)::generate)
                .item()
                .model((c,p)->p.getBuilder(c.getName()).parent(whistleItemModel(p,name)))
                .build()
                .register();
    }

    public static BlockEntry<CustomSpoutBlock> createSpout(String name) {
        return REGISTRATE.block(name +"_spout", CustomSpoutBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .transform(pickaxeOnly())
                .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), spoutModel(prov,name,false)))
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .item(AssemblyOperatorBlockItem::new)
                .model((c,p)->p.getBuilder(c.getName()).parent(spoutModel(p,name,true)))
                .build()
                .register();
    }

    private static BlockEntry<EncasedCustomPipeBlock> createEncasedCustomPipe(String name, String casingName, Supplier<Block> casing,Supplier<? extends FluidPipeBlock> pipe, CTSpriteShiftEntry sprite){;
        BlockBuilder<EncasedCustomPipeBlock,CreateRegistrate> builder = REGISTRATE.block(casingName+"_encased_"+name+"_fluid_pipe", p -> new EncasedCustomPipeBlock(p, casing,pipe))
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(axeOrPickaxe())
                .blockstate(EncasedBlockStateGens.encasedCustomPipe(name,casingName))
                .onRegister(EncasedBlockRegistrationHelpers.encasedPipeAttachmentModel(name))
                .loot((p, b) -> p.dropOther(b, pipe.get()))
                .transform(EncasingRegistry.addVariantTo(pipe))
                .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem);
        builder = connectedTexture(builder,sprite,(block,cc)->cc.make(block, sprite,
                (s, f) -> !s.getValue(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(f))));
        return builder.register();
    }





}
