package fr.iglee42.createcasing.registries;

import com.google.common.base.Function;
import com.simibubi.create.Create;
import com.simibubi.create.content.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.kinetics.base.DirectionalAxisKineticBlock;
import com.simibubi.create.content.kinetics.chainDrive.ChainDriveBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.RegistrateProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.blocks.ConfigurableGearboxBlock;
import fr.iglee42.createcasing.blocks.customs.CustomChainDriveBlock;
import fr.iglee42.createcasing.blocks.customs.CustomChainGearshiftBlock;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.client.model.generators.*;

import java.util.Objects;
import java.util.function.BiFunction;

public class EncasedBlockStateGens {

    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> encasedShaft(String shaft,String casing){
        return (ctx,prov)->axisBlock(ctx,prov,encasedShaftModel(prov,shaft,casing,false));
    }

    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> gearbox(String casing) {
        return (ctx,prov)->axisBlock(ctx,prov,gearboxModel(prov,casing,"block"));
    }


    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> shaft(String shaft) {
        return (ctx,prov)->axisBlock(ctx,prov,shaftModel(prov,shaft),false);
    }

    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> cogwheel(String cogwheel) {
        return (ctx,prov)-> {
            axisBlock(ctx, prov, cogwheelModel(prov, cogwheel, true));
            cogwheelModel(prov, cogwheel, false);
        };
    }

    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> largeCogwheel(String cogwheel) {
        return (ctx,prov)-> {
            axisBlock(ctx, prov, largeCogwheelModel(prov, cogwheel, true));
            largeCogwheelModel(prov, cogwheel, false);
        };
    }

    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> encasedCogwheel(String cogwheel,String casing) {
        return (ctx,prov)->axisBlock(ctx, prov, blockState -> encasedCogwheelModel(prov,casing,cogwheel,blockState,false),false);
    }

    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> encasedLargeCogwheel(String cogwheel,String casing) {
        return (ctx,prov)->axisBlock(ctx, prov, blockState -> encasedCogwheelModel(prov,casing,cogwheel,blockState,true),false);
    }

    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> encasedChainDrive(String casing) {
        return (ctx,prov)->{
            prov.getVariantBuilder(ctx.getEntry())
                    .forAllStatesExcept(state -> {
                        int rotationX = getChainDriveXRot(state);
                        int rotationY = getChainDriveYRot(state);
                        String suffix = getChainDriveModelSuffix(state);
                        return ConfiguredModel.builder().modelFile(createChainDriveModel(prov,casing,false,suffix)).rotationX(rotationX).rotationY(rotationY).build();
                    });
        };
    }

    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> adjustableChainGearshift(String casing) {
        return (ctx,prov)->{
            prov.getVariantBuilder(ctx.getEntry())
                    .forAllStatesExcept(state -> {
                        int rotationX = getChainDriveXRot(state);
                        int rotationY = getChainDriveYRot(state);
                        String suffix = getChainDriveModelSuffix(state);
                        return ConfiguredModel.builder().modelFile(createAdjustableChainGearshiftModel(prov,casing,false,suffix,state.getValue(CustomChainGearshiftBlock.POWERED))).rotationX(rotationX).rotationY(rotationY).build();
                    });
        };
    }

    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> chainConveyor(String casing) {
        return (ctx,prov)->{
            prov.simpleBlock(ctx.get(),createConveyorModel(prov,casing,false));
            texturesChainConveyor(Objects.requireNonNull(createModelInBlock(prov, "chain_conveyor/" + casing + "/wheel")).parent(new ModelFile.UncheckedModelFile("create:block/chain_conveyor/wheel")),casing);
            texturesChainConveyor(Objects.requireNonNull(createModelInBlock(prov, "chain_conveyor/" + casing + "/guard")).parent(new ModelFile.UncheckedModelFile("create:block/chain_conveyor/guard")),casing);
            texturesChainConveyor(Objects.requireNonNull(createModelInBlock(prov, "chain_conveyor/" + casing + "/shaft")).parent(new ModelFile.UncheckedModelFile("create:block/chain_conveyor/shaft")),casing);
        };
    }

    public static ModelFile createConveyorModel(RegistrateProvider prov, String casing, boolean item) {
        if (!item){
            return Objects.requireNonNull(createModelInBlock(prov, "chain_conveyor/" + casing + "/block"))
                    .parent(new ModelFile.UncheckedModelFile("create:block/chain_conveyor/block"))
                    .texture("0",getConveyorCasingTexture(casing))
                    .texture("particle",getCasingTexture(casing));
        } else {
            return texturesChainConveyor(Objects.requireNonNull(createModelInBlock(prov, "chain_conveyor/" + casing + "/item"))
                    .parent(new ModelFile.UncheckedModelFile("create:block/chain_conveyor/item")),casing);
        }
    }

    private static ModelBuilder<? extends ModelBuilder<?>> texturesChainConveyor(ModelBuilder<? extends ModelBuilder<?>> builder,String casing){
        return builder
                .texture("conveyor_casing",getConveyorCasingTexture(casing))
                .texture("conveyor_port", getConveyorPortTexture(casing))
                .texture("bullwheel", getBullWheelTexture(casing))
                .texture("particle",getCasingTexture(casing));
    }

    public static ModelFile createAdjustableChainGearshiftModel(RegistrateProvider provider, String casing, boolean item, String suffix, boolean powered){
        ModelFile file = createChainDriveModel(provider,casing,item,suffix);
        if (!isValidProvider(provider)) return file;
        return Objects.requireNonNull(createModelInBlock(provider, "adjustable_chain_gearshift/" + casing + "/" + suffix + (powered ? "_powered" : "")))
                .parent(file)
                .texture("side",getAdjustableChainGearshiftTexture(casing,powered))
                .texture("particle",getCasingTexture(casing));
    }


    public static ModelFile createChainDriveModel(RegistrateProvider provider,String casing,boolean item,String suffix){
        if (!item){
            String partKey = suffix.startsWith("end") ? "2" : "1";
            return Objects.requireNonNull(createModelInBlock(provider, "encased_chain_drive/" + casing + "/" + suffix))
                    .parent(new ModelFile.UncheckedModelFile("create:block/encased_chain_drive/"+suffix))
                    .texture(partKey,suffix.equals("single") ? getGearboxTexture(casing) : getChainDrivePart(casing,partKey))
                    .texture("side",getChainDriveSideTexture(casing))
                    .texture("particle",getCasingTexture(casing));

        } else {
            return Objects.requireNonNull(createModelInBlock(provider, "encased_chain_drive/" + casing + "/item"))
                    .parent(new ModelFile.UncheckedModelFile("create:block/encased_chain_drive/item"))
                    .texture("1",getGearboxTexture(casing))
                    .texture("side",getChainDriveSideTexture(casing))
                    .texture("particle",getCasingTexture(casing));
        }
    }

    private static int getChainDriveXRot(BlockState state){
        ChainDriveBlock.Part part = state.getValue(CustomChainDriveBlock.PART);
        boolean connectedAlongFirst = state.getValue(CustomChainDriveBlock.CONNECTED_ALONG_FIRST_COORDINATE);
        Direction.Axis axis = state.getValue(CustomChainDriveBlock.AXIS);

        if (part == ChainDriveBlock.Part.NONE)
            return axis == Direction.Axis.Y ? 90 : 0;
        if (axis == Direction.Axis.X)
            return (connectedAlongFirst ? 90 : 0) + (part == ChainDriveBlock.Part.START ? 180 : 0);
        if (axis == Direction.Axis.Z)
            return (connectedAlongFirst ? 0 : (part == ChainDriveBlock.Part.START ? 270 : 90));
        return 0;
    }
    private static int getChainDriveYRot(BlockState state){
        ChainDriveBlock.Part part = state.getValue(CustomChainDriveBlock.PART);
        boolean connectedAlongFirst = state.getValue(CustomChainDriveBlock.CONNECTED_ALONG_FIRST_COORDINATE);
        Direction.Axis axis = state.getValue(CustomChainDriveBlock.AXIS);

        if (part == ChainDriveBlock.Part.NONE)
            return axis == Direction.Axis.X ? 90 : 0;
        if (axis == Direction.Axis.Z)
            return (connectedAlongFirst && part == ChainDriveBlock.Part.END ? 270 : 90);
        boolean flip = part == ChainDriveBlock.Part.END && !connectedAlongFirst || part == ChainDriveBlock.Part.START && connectedAlongFirst;
        if (axis == Direction.Axis.Y)
            return (connectedAlongFirst ? 90 : 0) + (flip ? 180 : 0);
        return 0;
    }

    private static String getChainDriveModelSuffix(BlockState state) {
        ChainDriveBlock.Part part = state.getValue(CustomChainDriveBlock.PART);
        Direction.Axis axis = state.getValue(CustomChainDriveBlock.AXIS);

        if (part == ChainDriveBlock.Part.NONE)
            return "single";

        String orientation = axis == Direction.Axis.Y ? "vertical" : "horizontal";
        String section = part == ChainDriveBlock.Part.MIDDLE ? "middle" : "end";
        return section + "_" + orientation;
    }


    public static <P extends EncasedPipeBlock> NonNullBiConsumer<DataGenContext<Block, P>, RegistrateBlockstateProvider> encasedPipe(String casing) {
        return (c, p) -> {
            ModelFile open = Objects.requireNonNull(createModelInBlock(p, "encased_pipe/" + casing + "/block_open")).parent(new ModelFile.UncheckedModelFile(CreateCasing.asResource("block/templates/pipe_block_open"))).texture("1",getCasingTexture(casing)).texture("particle",getCasingTexture(casing));
            ModelFile flat = Objects.requireNonNull(createModelInBlock(p, "encased_pipe/" + casing + "/block_flat")).parent(new ModelFile.UncheckedModelFile(Create.ID + ":block/encased_fluid_pipe/block_flat")).texture("0",getCasingTexture(casing)).texture("particle",getCasingTexture(casing));
            MultiPartBlockStateBuilder builder = p.getMultipartBuilder(c.get());
            for (boolean flatPass : Iterate.trueAndFalse)
                for (Direction d : Iterate.directions) {
                    int verticalAngle = d == Direction.UP ? 90 : d == Direction.DOWN ? -90 : 0;
                    builder.part()
                            .modelFile(flatPass ? flat : open)
                            .rotationX(verticalAngle)
                            .rotationY((int) (d.toYRot() + (d.getAxis()
                                    .isVertical() ? 90 : 0)) % 360)
                            .addModel()
                            .condition(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(d), !flatPass)
                            .end();
                }
        };
    }

    public static <P extends ConfigurableGearboxBlock> NonNullBiConsumer<DataGenContext<Block, P>, RegistrateBlockstateProvider> configurableGearbox(String casing) {
        return (c, p) -> {
            ModelFile block = Objects.requireNonNull(createModelInBlock(p, "configurable_gearbox/" + casing + "/block"))
                    .parent(new ModelFile.UncheckedModelFile(CreateCasing.asResource("block/templates/configurable_gearbox/block")))
                    .texture("0",getCasingTexture(casing))
                    .texture("1",getGearboxTexture(casing))
                    .texture("particle",getCasingTexture(casing));
            ModelFile face = Objects.requireNonNull(createModelInBlock(p, "configurable_gearbox/" + casing + "/face"))
                    .parent(new ModelFile.UncheckedModelFile(CreateCasing.asResource("block/templates/configurable_gearbox/face")))
                    .texture("0",getCasingTexture(casing))
                    .texture("particle",getCasingTexture(casing));
            MultiPartBlockStateBuilder builder = p.getMultipartBuilder(c.get());
            builder.part().modelFile(block).addModel().end();
                for (Direction d : Iterate.directions) {
                    int verticalAngle = d == Direction.UP ? -90 : d == Direction.DOWN ? 90 : 0;
                    builder.part()
                            .modelFile(face)
                            .rotationX(verticalAngle)
                            .rotationY((int) (d.getOpposite().toYRot() + (d.getAxis()
                                    .isVertical() ? 90 : 0)) % 360)
                            .addModel()
                            .condition(ConfigurableGearboxBlock.getPropertyByDirection(d), false)
                            .end();
                }
        };
    }

    public static ModelFile createConfigurableGearboxItemModel(RegistrateItemModelProvider p,String casing){
        return Objects.requireNonNull(createModelInBlock(p, "configurable_gearbox/" + casing + "/item"))
                .parent(new ModelFile.UncheckedModelFile(CreateCasing.asResource("block/templates/configurable_gearbox/item")))
                .texture("0",getCasingTexture(casing))
                .texture("1",getGearboxTexture(casing))
                .texture("particle",getCasingTexture(casing));
    }

    public static <P extends Block> NonNullBiConsumer<DataGenContext<Block, P>, RegistrateBlockstateProvider> press(String casing) {
        return (c, p) -> {
            p.horizontalBlock(c.get(),pressModel(p,casing,false));
        };
    }
    public static <P extends Block> NonNullBiConsumer<DataGenContext<Block, P>, RegistrateBlockstateProvider> mixer(String casing) {
        return (c, p) -> {
            p.simpleBlock(c.get(),mixerModel(p,casing,false));
            Objects.requireNonNull(createModelInBlock(p, "mixer/" + casing + "/head")).parent(new ModelFile.UncheckedModelFile("create:block/mechanical_mixer/head")).texture("6",getMixerPart(casing,"head"));
        };
    }

    public static <P extends Block> NonNullBiConsumer<DataGenContext<Block, P>, RegistrateBlockstateProvider> depot(String casing) {
        return (c, p) -> {
            p.simpleBlock(c.get(), Objects.requireNonNull(depotModel(p, casing)));
        };
    }
    public static <T> ModelFile encasedCogwheelModel(RegistrateProvider p, String casing, String cogwheel, BlockState state,boolean large){
        boolean top = state != null ? state.getValue(EncasedCogwheelBlock.TOP_SHAFT) : false;
        boolean bottom = state != null ? state.getValue(EncasedCogwheelBlock.BOTTOM_SHAFT) : false;
        String suffix = (top ? "_top" : "" ) + (bottom ? "_bottom":"");
        if (isValidProvider(p)) {
            ModelBuilder<? extends ModelBuilder<?>> file = Objects.requireNonNull(createModelInBlock(p, "encased"+(large? "_large": "")+"_cogwheel/" + casing + "/" + (state == null ? "item" : "block"+ suffix)))
                    .parent(new ModelFile.UncheckedModelFile("create:block/encased"+(large? "_large": "")+"_cogwheel/block" + suffix))
                    .texture("1", getCasingTexture(casing))
                    .texture("casing", getCasingTexture(casing))
                    .texture("particle",getCasingTexture(casing))
                    .texture("4", getGearboxTexture(casing))
                    .texture("side", large ? getLargeCogwheelSideTexture(casing) : getCogwheelSideTexture(casing));
            if (state == null) file = file.parent(new ModelFile.UncheckedModelFile("create:block/encased"+(large? "_large": "")+"_cogwheel/item"));
            return file;
        }

        return null;
    }
    public static <T> ModelFile pressModel(RegistrateProvider p, String casing,boolean item){
        if (!item)
            return Objects.requireNonNull(createModelInBlock(p, "press/" + casing+"/block"))
                    .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_press/block"))
                    .texture("gearbox_top",getCasingTexture(casing))
                    .texture("gearbox",getGearboxTexture(casing))
                    .texture("mechanical_press_top",getPressPart(casing,"top"))
                    .texture("4",getPressPart(casing,"side"))
                    .texture("mechanical_press_bottom",getPressPart(casing,"bottom"))
                    .texture("particle",getCasingTexture(casing));
        else
            return Objects.requireNonNull(createModelInBlock(p, "press/" + casing+"/item"))
                    .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_press/item"))
                    .texture("gearbox_top",getCasingTexture(casing))
                    .texture("gearbox",getGearboxTexture(casing))
                    .texture("mechanical_press_top",getPressPart(casing,"top"))
                    .texture("8",getPressPart(casing,"side"))
                    .texture("mechanical_press_bottom",getPressPart(casing,"bottom"))
                    .texture("particle",getCasingTexture(casing));
    }

    public static <T> ModelFile depotModel(RegistrateProvider p, String casing){
        if (isValidProvider(p))
            return Objects.requireNonNull(createModelInBlock(p, "depot/" + casing+"/block"))
                    .parent(new ModelFile.UncheckedModelFile("create:block/depot/block"))
                    .texture("3",getCasingTexture(casing))
                    .texture("2",getDepotPart(casing,"top"))
                    .texture("1",getDepotPart(casing,"side"))
                    .texture("particle",getCasingTexture(casing));
        return null;
    }

    public static <T> ModelFile mixerModel(RegistrateProvider p, String casing,boolean item){
        if (!item)
            return Objects.requireNonNull(createModelInBlock(p, "mixer/" + casing+"/block"))
                    .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_mixer/block"))
                    .texture("2",getCasingTexture(casing))
                    .texture("11",getPressPart(casing,"top"))
                    .texture("4",getMixerPart(casing,"side"))
                    .texture("particle",getCasingTexture(casing));
        else
            return Objects.requireNonNull(createModelInBlock(p, "mixer/" + casing+"/item"))
                    .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_mixer/item"))
                    .texture("2",getCasingTexture(casing))
                    .texture("11",getPressPart(casing,"top"))
                    .texture("4",getMixerPart(casing,"side"))
                    .texture("6",getMixerPart(casing,"head"))
                    .texture("particle",getCasingTexture(casing));
    }

    public static <T> Function<BlockState,ModelFile> gearshiftModel(RegistrateProvider p, String casing){
        if (isValidProvider(p))
           return state ->{
            boolean powered = state.getValue(BlockStateProperties.POWERED);
            return Objects.requireNonNull(createModelInBlock(p,"gearshift/"+casing+"/block" + (powered?"_powered":"")))
                    .parent(new ModelFile.UncheckedModelFile("create:block/gearshift/block" + (powered?"_powered":"")))
                    .texture("0",getGearshiftTexture(casing,powered))
                    .texture("1",getGearboxTexture(casing))
                    .texture("2",getFunnelFrameTexture(casing))
                    .texture("particle",getGearshiftTexture(casing,powered));
           };
        return null;
    }

    public static ModelFile gearshiftItemModel(RegistrateProvider p, String casing){
        return Objects.requireNonNull(createModelInBlock(p,"gearshift/"+casing+"/item"))
                .parent(new ModelFile.UncheckedModelFile("create:block/gearshift/item"))
                .texture("0",getGearshiftTexture(casing,false))
                .texture("1",getGearboxTexture(casing))
                .texture("particle",getGearshiftTexture(casing,false));
    }

    public static <T> Function<BlockState,ModelFile> clutchModel(RegistrateProvider p, String casing){
        if (isValidProvider(p))
            return state ->{
                boolean powered = state.getValue(BlockStateProperties.POWERED);
                return Objects.requireNonNull(createModelInBlock(p,"clutch/"+casing+"/block" + (powered?"_powered":"")))
                        .parent(new ModelFile.UncheckedModelFile("create:block/clutch/block" + (powered?"_powered":"")))
                        .texture("0",getClutchTexture(casing,powered))
                        .texture("1",getGearboxTexture(casing))
                        .texture("2",getFunnelFrameTexture(casing))
                        .texture("particle",getClutchTexture(casing,powered));
            };
        return null;
    }

    public static ModelFile clutchItemModel(RegistrateProvider p, String casing){
        return Objects.requireNonNull(createModelInBlock(p,"clutch/"+casing+"/item"))
                .parent(new ModelFile.UncheckedModelFile("create:block/clutch/item"))
                .texture("0",getClutchTexture(casing,false))
                .texture("1",getGearboxTexture(casing))
                .texture("4",getFunnelFrameTexture(casing))
                .texture("particle",getClutchTexture(casing,false));
    }

    public static BiFunction<BlockState,Boolean,ModelFile> deployerModel(RegistrateProvider p, String casing){
        if (isValidProvider(p))
            return ($,vertical) ->{
                return Objects.requireNonNull(createModelInBlock(p,"deployer/"+casing+"/" + (vertical?"vertical":"horizontal")))
                        .parent(new ModelFile.UncheckedModelFile("create:block/deployer/" + (vertical?"vertical":"horizontal")))
                        .texture("5",getPistonCasingTexture(casing))
                        .texture("6",getPistonBottomTexture(casing))
                        .texture("7",getPistonInnerTexture(casing))
                        .texture("particle",getGearboxTopTexture(casing))
                        .texture("gearbox",getGearboxTexture(casing))
                        .texture("gearbox_top",getGearboxTopTexture(casing))
                        .texture("andesite_casing_short",getShortCasingTexture(casing));
            };
        return null;
    }

    public static ModelFile deployerItemModel(RegistrateProvider p, String casing){
        return Objects.requireNonNull(createModelInBlock(p,"deployer/"+casing+"/item"))
                .parent(new ModelFile.UncheckedModelFile("create:block/deployer/item"))
                .texture("10",getPistonCasingTexture(casing))
                .texture("6",getPistonBottomTexture(casing))
                .texture("7",getPistonInnerTexture(casing))
                .texture("particle",getGearboxTopTexture(casing))
                .texture("gearbox",getGearboxTexture(casing))
                .texture("gearbox_top",getGearboxTopTexture(casing))
                .texture("andesite_casing_short",getShortCasingTexture(casing));
    }

    public static ModelFile storageInterfaceModel(RegistrateProvider p, String casing,boolean item){
        if (isValidProvider(p))
            return Objects.requireNonNull(createModelInBlock(p,"portable_storage_interface/"+casing+"/"+(item ? "item" :"block")))
                    .parent(new ModelFile.UncheckedModelFile("create:block/portable_storage_interface/"+(item ? "item" :"block")))
                    .texture("0",getPortableStorageInterfaceTexture(casing))
                    .texture("1",getCasingTexture(casing))
                    .texture("particle",getCasingTexture(casing));
        return null;
    }

    public static ModelFile fanModel(RegistrateProvider p, String casing){
        if (isValidProvider(p))
            return Objects.requireNonNull(createModelInBlock(p,"encased_fan/"+casing+"/block"))
                    .parent(new ModelFile.UncheckedModelFile("create:block/encased_fan/block"))
                    .texture("3",getFunnelFrameTexture(casing))
                    .texture("back",getGearboxTexture(casing))
                    .texture("fan_casing",getFanPart(casing,"casing"))
                    .texture("particle",getFanPart(casing,"side"))
                    .texture("fan_side",getFanPart(casing,"side"));
        return null;
    }

    public static ModelFile fanItemModel(RegistrateProvider p, String casing){
        return Objects.requireNonNull(createModelInBlock(p,"encased_fan/"+casing+"/item"))
                .parent(new ModelFile.UncheckedModelFile("create:block/encased_fan/item"))
                .texture("2",getGearboxTexture(casing))
                .texture("fan_casing",getFanPart(casing,"casing"))
                .texture("particle",getFanPart(casing,"side"))
                .texture("fan_side",getFanPart(casing,"side"));
    }


    public static ModelFile harvesterModel(RegistrateProvider p, String casing,boolean item){
        if (isValidProvider(p))
            return Objects.requireNonNull(createModelInBlock(p,"mechanical_harvester/"+casing+"/"+(item ? "item" :"block")))
                    .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_harvester/"+(item ? "item" :"block")))
                    .texture("andesite_casing_short",getShortCasingTexture(casing))
                    .texture("particle",getShortCasingTexture(casing));
        return null;
    }

    public static ModelFile rollerModel(RegistrateProvider p, String casing,boolean item){
        if (isValidProvider(p)) {
            if (!item)Objects.requireNonNull(createModelInBlock(p, "mechanical_roller/" + casing + "/frame"))
                    .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_roller/frame"))
                    .texture("1", getRollerMetalTexture(casing))
                    .texture("particle", getRollerCasingTexture(casing));
            return Objects.requireNonNull(createModelInBlock(p, "mechanical_roller/" + casing + "/" + (item ? "item" : "block")))
                    .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_roller/" + (item ? "item" : "block")))
                    .texture(item ? "casing" : "0", getRollerCasingTexture(casing))
                    .texture(item ? "metal" : "unused", getRollerMetalTexture(casing))
                    .texture("particle", getCasingTexture(casing));
        }
        return null;
    }

    public static ModelFile drillModel(RegistrateProvider p, String casing,boolean item){
        if (isValidProvider(p)){
            Objects.requireNonNull(createModelInBlock(p,"mechanical_drill/"+casing+"/head"))
                    .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_drill/head"))
                    .texture("10",getVeryShortCasingTexture(casing));
            return Objects.requireNonNull(createModelInBlock(p,"mechanical_drill/"+casing+"/"+(item ? "item" :"block")))
                    .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_drill/"+(item ? "item" :"block")))
                    .texture("10",getVeryShortCasingTexture(casing))
                    .texture("gearbox_top",getGearboxTopTexture(casing))
                    .texture("particle",getGearboxTexture(casing))
                    .texture("gearbox",getGearboxTexture(casing));
        }

        return null;
    }

    public static ModelFile ploughModel(RegistrateProvider p, String casing){
        if (isValidProvider(p))
            return Objects.requireNonNull(createModelInBlock(p,"mechanical_plough/"+casing))
                .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_plough"))
                .texture("andesite_casing_short",getShortCasingTexture(casing))
                .texture("particle",getShortCasingTexture(casing));
        return null;
    }

    public static ModelFile sawItemModel(RegistrateProvider p, String casing){
        return Objects.requireNonNull(createModelInBlock(p,"mechanical_saw/"+casing+"/item"))
                .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_saw/item"))
                .texture("slit",getSawTexture(casing,"_top_no_slot"))
                .texture("gearbox",getGearboxTexture(casing))
                .texture("particle",getGearboxTopTexture(casing))
                .texture("gearbox_top",getGearboxTopTexture(casing))
                .texture("encased_belt",getChainDriveSideTexture(casing))
                .texture("andesite_casing_short",getShortCasingTexture(casing));
    }




    public static <T extends Block> void axisBlock(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov,ModelFile model){
        axisBlock(ctx,prov,model,true);
    }

    public static <T extends Block> void axisBlock(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov,ModelFile model, boolean uvLock){
        axisBlock(ctx,prov,bs->model,uvLock);
    }
    public static <T extends Block> void axisBlock(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov, Function<BlockState,ModelFile> model, boolean uvLock){
        if (model == null) {
            prov.simpleBlock(ctx.get(),new ModelFile.UncheckedModelFile("block/dirt"));
            return;
        }
        prov.getVariantBuilder(ctx.getEntry())
                .forAllStatesExcept(state -> {
                    Direction.Axis axis = state.getValue(BlockStateProperties.AXIS);
                    return ConfiguredModel.builder()
                            .modelFile(model.apply(state))
                            .uvLock(uvLock)
                            .rotationX(axis == Direction.Axis.Y ? 0 : 90)
                            .rotationY(axis == Direction.Axis.X ? 90 : axis == Direction.Axis.Z ? 180 : 0)
                            .build();
                }, BlockStateProperties.WATERLOGGED);
    }

    public static <T extends DirectionalAxisKineticBlock> void directionalAxisBlock(DataGenContext<Block, T> ctx,
                                                                                    RegistrateBlockstateProvider prov, BiFunction<BlockState, Boolean, ModelFile> modelFunc) {
        if (modelFunc == null) {
            prov.simpleBlock(ctx.get(),new ModelFile.UncheckedModelFile("block/dirt"));
            return;
        }
        prov.getVariantBuilder(ctx.getEntry())
                .forAllStates(state -> {

                    boolean alongFirst = state.getValue(DirectionalAxisKineticBlock.AXIS_ALONG_FIRST_COORDINATE);
                    Direction direction = state.getValue(DirectionalAxisKineticBlock.FACING);
                    boolean vertical = direction.getAxis()
                            .isHorizontal() && (direction.getAxis() == Direction.Axis.X) == alongFirst;
                    int xRot = direction == Direction.DOWN ? 270 : direction == Direction.UP ? 90 : 0;
                    int yRot = direction.getAxis()
                            .isVertical() ? alongFirst ? 0 : 90 : (int) direction.toYRot();

                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state, vertical))
                            .rotationX(xRot)
                            .rotationY(yRot)
                            .build();
                });
    }

    public static <T> ModelFile gearboxModel(RegistrateProvider p, String casing, String type){
        String casingKey = type.equals("item_vertical") ? "gearbox" : "1";
        String topKey = type.equals("item_vertical") ? "gearbox_top" : "0";
        if (isValidProvider(p))
          return  Objects.requireNonNull(createModelInBlock(p, "gearbox/" + casing + "/" + type))
                    .parent(new ModelFile.UncheckedModelFile("create:block/gearbox/"+type))
                    .texture(topKey,getCasingTexture(casing))
                    .texture(casingKey,getGearboxTexture(casing))
                  .texture("particle",getCasingTexture(casing));

        return null;
    }

    public static <T> ModelFile shaftModel(RegistrateProvider p,String shaft){
        if (isValidProvider(p)) {
            ModelBuilder<? extends ModelBuilder<?>> file = Objects.requireNonNull(createModelInBlock(p, "shaft/" + shaft))
                    .parent(new ModelFile.UncheckedModelFile("create:block/shaft"))
                    .texture("0", getShaftTexture(shaft))
                    .texture("1", getShaftTexture(shaft) + (!shaft.equals("mldeg") ? "_top" : ""))
                    .texture("particle",getShaftTexture(shaft));
            if (shaft.equals("glass")) file = file.renderType("cutout_mipped");
            return file;
        }
        return null;
    }

    public static <T> ModelFile cogwheelModel(RegistrateProvider p,String cogwheel,boolean shaft){
        if (isValidProvider(p))
            return Objects.requireNonNull(createModelInBlock(p, "cogwheel"+(shaft?"":"_shaftless")+"/" + cogwheel))
                    .parent(new ModelFile.UncheckedModelFile("create:block/cogwheel"+(shaft?"":"_shaftless")))
                    .texture("1_2",getCogwheelTexture(cogwheel))
                    .texture("particle",getShaftTexture(cogwheel));
        return null;
    }

    public static <T> ModelFile largeCogwheelModel(RegistrateProvider p,String cogwheel,boolean shaft){
        if (isValidProvider(p))
            return Objects.requireNonNull(createModelInBlock(p, "large_cogwheel"+(shaft?"":"_shaftless")+"/" + cogwheel))
                    .parent(new ModelFile.UncheckedModelFile("create:block/large_cogwheel"+(shaft?"":"_shaftless")))
                    .texture("4",getLargeCogwheelTexture(cogwheel))
                    .texture("particle",getShaftTexture(cogwheel));
        return null;
    }





    //For the block, the model is only the casing block so we don't need to have a block per shaft type
    public static <T> ModelFile encasedShaftModel(RegistrateProvider p,String shaft, String casing, boolean item){
        if (!item)
            return Objects.requireNonNull(createModelInBlock(p, "encased_shaft/" + casing))
                    .parent(new ModelFile.UncheckedModelFile("create:block/encased_shaft/block"))
                    .texture("casing",getCasingTexture(casing))
                    .texture("opening",getGearboxTexture(casing))
                    .texture("particle",getCasingTexture(casing));
        else
            return Objects.requireNonNull(createModelInBlock(p, "encased_shaft/items/" + shaft + "/" + casing))
                    .parent(new ModelFile.UncheckedModelFile("create:block/encased_shaft/item"))
                    .texture("casing",getCasingTexture(casing))
                    .texture("opening",getGearboxTexture(casing))
                    .texture("1_0",getShaftTexture(shaft))
                    .texture("1_1", getShaftTexture(shaft) + (!shaft.equals("mldeg") ? "_top": ""))
                    .texture("particle",getCasingTexture(casing));
    }

    public static ModelBuilder<? extends ModelBuilder<?>> createModelInBlock(RegistrateProvider p, String path){
        if (p instanceof RegistrateBlockstateProvider provider)
            return provider.models()
                    .getBuilder("block/"+path);
        else if (p instanceof RegistrateItemModelProvider provider)
            return provider.getBuilder("block/"+path);
        return null;
    }

    public static boolean isValidProvider(RegistrateProvider p){
        return p instanceof RegistrateBlockstateProvider || p instanceof RegistrateItemModelProvider;
    }


    public static String getCasingTexture(String casing){
        if (casing.equals("normal")) return Create.ID+":block/andesite_casing";
        String modid = getModForCasing(casing);
        if (casing.equals("industrial_iron") || casing.equals("weathered_iron")) return modid + ":block/"+casing+"_block";
        return modid + ":block/"+casing+"_casing";
    }

    public static String getModForCasing(String casing){
        if (casing.equals("brass") || casing.equals("andesite") || casing.equals("copper") || casing.equals("railway") || casing.equals("industrial_iron") || casing.equals("creative") || casing.equals("weathered_iron") || casing.equals("shadow_steel") || casing.equals("refined_radiance")) return Create.ID;
        return CreateCasing.MODID;
    }

    public static String getGearboxTexture(String casing){
        if (casing.equals("andesite") || casing.equals("normal")) return Create.ID+":block/gearbox";
        if (casing.equals("brass")) return Create.ID + ":block/"+casing+"_gearbox";
        return CreateCasing.MODID + ":block/gearbox/"+casing;
    }

    public static String getGearboxTopTexture(String casing){
        if (casing.equals("andesite") || casing.equals("normal")) return Create.ID+":block/gearbox_top";
        return CreateCasing.MODID + ":block/gearbox_top/"+casing;
    }


    public static String getShaftTexture(String shaft){
        if (shaft.equals("normal")) return Create.ID + ":block/axis";
        if (shaft.equals("bamboo")) return "minecraft:block/stripped_bamboo_block";
        if (isWoodenShaft(shaft)) return "minecraft:block/stripped_"+shaft+"_" + (shaft.equals("crimson") || shaft.equals("warped") ? "stem": "log");
        return CreateCasing.MODID + ":block/shaft/"+shaft;
    }


    public static String getCogwheelTexture(String cogwheel) {
        if (cogwheel.equals("normal")) return Create.ID + ":block/cogwheel";
        return CreateCasing.MODID + ":block/cogwheel/"+cogwheel;
    }

    public static String getLargeCogwheelTexture(String cogwheel) {
        if (cogwheel.equals("normal")) return Create.ID + ":block/large_cogwheel";
        return CreateCasing.MODID + ":block/large_cogwheel/"+cogwheel;
    }

    public static String getCogwheelSideTexture(String casing) {
        if (casing.equals("andesite") || casing.equals("brass")) return Create.ID + ":block/"+casing+"_encased_cogwheel_side";
        return CreateCasing.MODID + ":block/encased_cogwheel/"+casing;
    }

    public static String getLargeCogwheelSideTexture(String casing) {
        return getCogwheelSideTexture(casing) + "_connected";
    }

    public static String getPressPart(String casing,String part) {
        if (casing.equals("normal")) return Create.ID + ":block/mechanical_press_"+part;
        return CreateCasing.MODID + ":block/press_"+part+"/"+casing;
    }

    public static String getMixerPart(String casing,String part) {
        if (casing.equals("normal")) return Create.ID + ":block/mixer_base_"+part;
        return CreateCasing.MODID + ":block/mixer_"+part+"/"+casing;
    }

    public static String getSawTexture(String casing,String part) {
        if (casing.equals("normal")) return Create.ID + ":block/mechanical_saw"+part;
        return CreateCasing.MODID + ":block/mechanical_saw"+part+"/"+casing;
    }


    public static String getFanPart(String casing,String part) {
        if (casing.equals("normal")) return Create.ID + ":block/fan_"+part;
        return CreateCasing.MODID + ":block/fan_"+part+"/"+casing;
    }

    public static String getDepotPart(String casing,String part) {
        if (casing.equals("normal")) return Create.ID + ":block/depot_"+part;
        return CreateCasing.MODID + ":block/depot_"+part+"/"+casing;
    }

    public static String getChainDrivePart(String casing,String partSuffix) {
        String part = partSuffix.equals("2") ? "end" : "middle";
        if (casing.equals("normal")) return Create.ID + ":block/encased_chain_drive"+part;
        return CreateCasing.MODID + ":block/encased_chain_drive_"+part+"/"+casing;
    }
    public static String getChainDriveSideTexture(String casing) {
        if (casing.equals("normal")) return Create.ID + ":block/encased_chain_drive_side";
        return CreateCasing.MODID + ":block/encased_chain_drive/"+casing;
    }
    public static String getAdjustableChainGearshiftTexture(String casing,boolean powered) {
        if (casing.equals("normal")) return Create.ID + ":block/adjustable_chain_gearshift"+(powered ? "_powered":"");
        return CreateCasing.MODID + ":block/adjustable_chain_gearshift"+(powered ? "_powered":"")+"/"+casing;
    }
    public static String getConveyorPortTexture(String casing) {
        if (casing.equals("normal")) return Create.ID + ":block/conveyor_port";
        return CreateCasing.MODID + ":block/conveyor_port/"+casing;
    }

    public static String getConveyorCasingTexture(String casing) {
        if (casing.equals("normal")) return Create.ID + ":block/conveyor_casing";
        return CreateCasing.MODID + ":block/conveyor_casing/"+casing;
    }

    public static String getBullWheelTexture(String casing) {
        if (casing.equals("normal")) return Create.ID + ":block/bullwheel";
        return CreateCasing.MODID + ":block/bullwheel/"+casing;
    }

    public static String getGearshiftTexture(String casing,boolean powered) {
        if (casing.equals("normal")) return Create.ID + ":block/gearshift_"+(powered ? "on" : "off");
        return CreateCasing.MODID + ":block/gearshift_"+(powered ? "on" : "off")+"/"+casing;
    }

    public static String getClutchTexture(String casing,boolean powered) {
        if (casing.equals("normal")) return Create.ID + ":block/clutch_"+(powered ? "on" : "off");
        return CreateCasing.MODID + ":block/clutch_"+(powered ? "on" : "off")+"/"+casing;
    }

    public static String getFunnelFrameTexture(String casing) {
        if (casing.equals("normal")) return Create.ID + ":block/funnel/andesite_funnel_frame";
        if (casing.equals("brass")) return Create.ID + ":block/funnel/brass_funnel_frame";
        if (casing.equals("copper")) return Create.ID + ":block/funnel/copper_funnel_frame";
        return CreateCasing.MODID + ":block/funnel_frame/"+casing;
    }

    public static String getShortCasingTexture(String casing){
        if (casing.equals("normal") || casing.equals("andesite")) return Create.ID+":block/andesite_casing_short";
        return CreateCasing.MODID + ":block/casing_short/" + casing;
    }

    public static String getVeryShortCasingTexture(String casing){
        if (casing.equals("normal") || casing.equals("andesite")) return Create.ID+":block/andesite_casing_very_short";
        return CreateCasing.MODID + ":block/casing_very_short/" + casing;
    }

    public static String getPistonCasingTexture(String casing){
        if (casing.equals("normal") || casing.equals("andesite")) return Create.ID+":block/andesite_casing_piston";
        return CreateCasing.MODID + ":block/casing_piston/" + casing;
    }

    public static String getPortableStorageInterfaceTexture(String casing){
        if (casing.equals("normal") || casing.equals("andesite")) return Create.ID+":block/portable_storage_interface";
        return CreateCasing.MODID + ":block/portable_storage_interface/" + casing;
    }

    public static String getPistonBottomTexture(String casing){
        if (casing.equals("normal") || casing.equals("andesite")) return Create.ID+":block/piston_bottom";
        return CreateCasing.MODID + ":block/piston_bottom/" + casing;
    }

    public static String getPistonInnerTexture(String casing){
        if (casing.equals("normal") || casing.equals("andesite")) return Create.ID+":block/piston_inner";
        return CreateCasing.MODID + ":block/piston_inner/" + casing;
    }

    public static String getRollerCasingTexture(String casing){
        if (casing.equals("normal") || casing.equals("andesite")) return Create.ID+":block/roller_casing";
        return CreateCasing.MODID + ":block/roller_casing/" + casing;
    }
    public static String getRollerMetalTexture(String casing){
        if (casing.equals("normal") || casing.equals("andesite")) return Create.ID+":block/roller_metal";
        return CreateCasing.MODID + ":block/roller_metal/" + casing;
    }




    private static boolean isWoodenShaft(String shaft){
        return WoodType.values().anyMatch(w->w.name().toLowerCase().equalsIgnoreCase(shaft));
    }

}
