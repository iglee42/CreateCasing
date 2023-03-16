package fr.iglee42.createcasing.compatibility.createcrystalclear;

import com.cyvack.create_crystal_clear.blocks.ModBlocks;
import com.cyvack.create_crystal_clear.blocks.ModSpriteShifts;
import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.cyvack.create_crystal_clear.blocks.glass_encased_cogwheel.GlassEncasedCogwheel;
import com.cyvack.create_crystal_clear.blocks.glass_encased_shaft.GlassEncasedShaftBlock;
import com.cyvack.create_crystal_clear.data_gen.BlockBuilders;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.contraptions.fluids.PipeAttachmentModel;
import com.simibubi.create.content.contraptions.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.FluidPipeTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.*;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.Iterate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import fr.iglee42.createcasing.changeAcces.PublicSimpleKinecticTileEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.cyvack.create_crystal_clear.data_gen.BlockBuilders.glassEncasedShaftBuilder;
import static com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock.AXIS;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;
import static fr.iglee42.createcasing.CreateCasing.isCrystalClearLoaded;


public class CreateCrystalClearCompatibility {

    public static final BlockEntry<GlassEncasedShaftBlock> COPPER_GLASS_ENCASED_SHAFT = glassEncasedShaft("copper",false, (p)->new GlassEncasedShaftBlockCompat(p,ModBlocks.COPPER_GLASS_CASING));
    public static final BlockEntry<GlassEncasedShaftBlock> COPPER_CLEAR_GLASS_ENCASED_SHAFT = glassEncasedShaft("copper",true, (p)->new GlassEncasedShaftBlockCompat(p,ModBlocks.COPPER_CLEAR_GLASS_CASING));

    public static final BlockEntry<GlassEncasedCogwheel> COPPER_GLASS_ENCASED_COGWHEEL = glassEncasedCogwheel("copper",false,false, (p)->new GlassEncasedCogwheelBlockCompat(false,p,ModBlocks.COPPER_GLASS_CASING));
    public static final BlockEntry<GlassEncasedCogwheel> COPPER_CLEAR_GLASS_ENCASED_COGWHEEL = glassEncasedCogwheel("copper",false,true, (p)->new GlassEncasedCogwheelBlockCompat(false,p,ModBlocks.COPPER_CLEAR_GLASS_CASING));
    public static final BlockEntry<GlassEncasedCogwheel> COPPER_GLASS_ENCASED_LARGE_COGWHEEL = glassEncasedCogwheel("copper",true,false, (p)->new GlassEncasedCogwheelBlockCompat(true,p,ModBlocks.COPPER_GLASS_CASING));
    public static final BlockEntry<GlassEncasedCogwheel> COPPER_CLEAR_GLASS_ENCASED_LARGE_COGWHEEL = glassEncasedCogwheel("copper",true,true, (p)->new GlassEncasedCogwheelBlockCompat(true,p,ModBlocks.COPPER_CLEAR_GLASS_CASING));

    public static final BlockEntry<GlassEncasedPipeBlockCompat> ANDESITE_GLASS_ENCASED_PIPE = createPipe("andesite",false,ModBlocks.ANDESITE_GLASS_CASING,ModSpriteShifts.ANDESITE_GLASS_CASING);
    public static final BlockEntry<GlassEncasedPipeBlockCompat> ANDESITE_CLEAR_GLASS_ENCASED_PIPE = createPipe("andesite",true,ModBlocks.ANDESITE_CLEAR_GLASS_CASING,ModSpriteShifts.ANDESITE_CLEAR_GLASS_CASING);
    public static final BlockEntry<GlassEncasedPipeBlockCompat> BRASS_GLASS_ENCASED_PIPE = createPipe("brass",false,ModBlocks.BRASS_GLASS_CASING,ModSpriteShifts.BRASS_GLASS_CASING);
    public static final BlockEntry<GlassEncasedPipeBlockCompat> BRASS_CLEAR_GLASS_ENCASED_PIPE = createPipe("brass",true,ModBlocks.BRASS_CLEAR_GLASS_CASING,ModSpriteShifts.BRASS_CLEAR_GLASS_CASING);
    public static final BlockEntry<GlassEncasedPipeBlockCompat> TRAIN_GLASS_ENCASED_PIPE = createPipe("railway",false,ModBlocks.TRAIN_GLASS_CASING,ModSpriteShifts.TRAIN_GLASS_CASING);
    public static final BlockEntry<GlassEncasedPipeBlockCompat> TRAIN_CLEAR_GLASS_ENCASED_PIPE = createPipe("railway",true,ModBlocks.TRAIN_CLEAR_GLASS_CASING,ModSpriteShifts.TRAIN_CLEAR_GLASS_CASING);
    public static final BlockEntry<GlassEncasedPipeBlockCompat> COPPER_GLASS_ENCASED_PIPE = createPipe("copper",false,ModBlocks.COPPER_GLASS_CASING,ModSpriteShifts.COPPER_GLASS_CASING);
    public static final BlockEntry<GlassEncasedPipeBlockCompat> COPPER_CLEAR_GLASS_ENCASED_PIPE = createPipe("copper",true,ModBlocks.COPPER_CLEAR_GLASS_CASING,ModSpriteShifts.COPPER_CLEAR_GLASS_CASING);

    public static final BlockEntityEntry<KineticTileEntity> GLASS_SHAFT_TILE = REGISTRATE
            .tileEntity("encased_glass_shaft", KineticTileEntity::new)
            .instance(() -> ShaftInstance::new, false)
            .validBlocks(COPPER_CLEAR_GLASS_ENCASED_SHAFT,COPPER_GLASS_ENCASED_SHAFT)
            .renderer(() -> ShaftRenderer::new)
            .register();
    public static final BlockEntityEntry<PublicSimpleKinecticTileEntity> GLASS_COGWHEEL_TILE = REGISTRATE
            .tileEntity("encased_glass_cogwheel", PublicSimpleKinecticTileEntity::new)
            .instance(() -> EncasedCogInstance::small, false)
            .validBlocks(COPPER_GLASS_ENCASED_COGWHEEL,COPPER_CLEAR_GLASS_ENCASED_COGWHEEL)
            .renderer(() -> EncasedCogRenderer::small)
            .register();
    public static final BlockEntityEntry<PublicSimpleKinecticTileEntity> GLASS_COGWHEEL_LARGE_TILE = REGISTRATE
            .tileEntity("encased_glass_cogwheel_large", PublicSimpleKinecticTileEntity::new)
            .instance(() -> EncasedCogInstance::large, false)
            .validBlocks(COPPER_GLASS_ENCASED_LARGE_COGWHEEL,COPPER_CLEAR_GLASS_ENCASED_LARGE_COGWHEEL)
            .renderer(() -> EncasedCogRenderer::large)
            .register();
    public static final BlockEntityEntry<FluidPipeTileEntity> GLASS_PIPE_TILE = REGISTRATE
            .tileEntity("encased_glass_pipe", FluidPipeTileEntity::new)
            .validBlocks(ANDESITE_GLASS_ENCASED_PIPE,ANDESITE_CLEAR_GLASS_ENCASED_PIPE,BRASS_GLASS_ENCASED_PIPE,BRASS_CLEAR_GLASS_ENCASED_PIPE,TRAIN_GLASS_ENCASED_PIPE,TRAIN_CLEAR_GLASS_ENCASED_PIPE,COPPER_GLASS_ENCASED_PIPE,COPPER_CLEAR_GLASS_ENCASED_PIPE)
            .register();

    public static BlockEntry<GlassEncasedPipeBlockCompat> createPipe(String name,boolean clear, BlockEntry<GlassCasing> casing, CTSpriteShiftEntry sprite){
        return REGISTRATE.block(name + (clear ? "_clear": "") +"_glass_encased_fluid_pipe", (p)->new GlassEncasedPipeBlockCompat(p,casing))
                .initialProperties(()->Blocks.GLASS)
                .addLayer(()->RenderType::cutout)
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

    public static boolean isModLoaded(){
        return ModList.get().isLoaded("create_crystal_clear");
    }

    public static void register() {}

    public static BlockEntry<GlassEncasedShaftBlock> glassEncasedShaft(String casingType, Boolean clear, NonNullFunction<BlockBehaviour.Properties, GlassEncasedShaftBlock> factory) {
        String name = clear ? casingType + "_clear" : casingType;
        return (REGISTRATE.block(name + "_glass_encased_shaft", factory).transform(glassEncasedShaftBuilder(name + "_glass", () -> ModSpriteShifts.omni(name + "_glass_casing"))).register());
    }

    public static BlockEntry<GlassEncasedCogwheel> glassEncasedCogwheel(String casingType, Boolean large, Boolean clear, NonNullFunction<BlockBehaviour.Properties, GlassEncasedCogwheel> factory){
        String name = clear? casingType+"_clear" : casingType;
        return !large?
                //small cog
                REGISTRATE
                        .block(name+"_glass_encased_cogwheel", factory)
                        .transform(BlockBuilders.glassencasedCogwheel(casingType, clear,
                                ()-> ModSpriteShifts.omni(name+"_glass_casing")))
                        .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(ModSpriteShifts.omni(name+"_glass_casing"),
                                Couple.create(ModSpriteShifts.vertical("encased_cogwheels/" + casingType +"_encased_cogwheel_side"),
                                        ModSpriteShifts.horizontal("encased_cogwheels/" + casingType +"_encased_cogwheel_side")))))
                        .register() :
                //Large Cog
                REGISTRATE
                        .block(name+ "_glass_encased_large_cogwheel", factory)
                        .transform(BlockBuilders.glassencasedLargeCogwheel(casingType, clear,
                                () -> ModSpriteShifts.omni(name+"_glass_casing")))
                        .register();
    }
    public static boolean checkCogs(boolean isLarge,Level world,ItemStack heldItem,BlockPos pos, BlockState state){
        AtomicBoolean out = new AtomicBoolean(false);
        if (ModList.get().isLoaded("create_crystal_clear")){
            List<GlassEncasedCogwheel> glassCogs = new ArrayList<>(){
                {
                    if (isLarge) {
                        add(CreateCrystalClearCompatibility.COPPER_GLASS_ENCASED_LARGE_COGWHEEL.get());
                        add(CreateCrystalClearCompatibility.COPPER_CLEAR_GLASS_ENCASED_LARGE_COGWHEEL.get());
                    } else {
                        add(CreateCrystalClearCompatibility.COPPER_GLASS_ENCASED_COGWHEEL.get());
                        add(CreateCrystalClearCompatibility.COPPER_CLEAR_GLASS_ENCASED_COGWHEEL.get());
                    }
                }
            };
            glassCogs.stream().filter(s->s.getCasing().isIn(heldItem))
                    .findFirst().ifPresent(s->{
                        if (!world.isClientSide) {
                            BlockState encasedState = s.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                            Direction[] var14 = Iterate.directionsInAxis(state.getValue(AXIS));

                            for (Direction d : var14) {
                                BlockState adjacentState = world.getBlockState(pos.relative(d,1));
                                if (adjacentState.getBlock() instanceof IRotate) {
                                    IRotate def = (IRotate) adjacentState.getBlock();
                                    if (def.hasShaftTowards(world, pos.relative(d,1), adjacentState, d.getOpposite())) {
                                        encasedState = encasedState.cycle(d.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT : EncasedCogwheelBlock.BOTTOM_SHAFT);
                                    }
                                }
                            }

                            KineticTileEntity.switchToBlockState(world, pos, encasedState);
                        }
                        out.set(true);
                    });
        }
        return out.get();
    }

    public static boolean checkAxisPipes(Level world, BlockPos pos, ItemStack heldItem, Direction.Axis axis) {
        AtomicBoolean out = new AtomicBoolean(false);
        if (isCrystalClearLoaded()) {
            List<GlassEncasedPipeBlockCompat> glassPipes = new ArrayList<>();
            ForgeRegistries.BLOCKS.getKeys().stream().filter(r -> ForgeRegistries.BLOCKS.getValue(r) instanceof GlassEncasedPipeBlockCompat).forEach(r -> glassPipes.add((GlassEncasedPipeBlockCompat) ForgeRegistries.BLOCKS.getValue(r)));
            glassPipes.stream().filter(p -> p.getCasing().isIn(heldItem))
                    .findFirst().ifPresent(s -> {
                        if (!world.isClientSide) {
                            BlockState newState = s.defaultBlockState();
                            Direction[] var8 = Iterate.directionsInAxis(axis);

                            for (Direction d : var8) {
                                newState = newState.setValue(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(d), true);
                            }

                            FluidTransportBehaviour.cacheFlows(world, pos);
                            world.setBlockAndUpdate(pos, newState);
                            FluidTransportBehaviour.loadFlows(world, pos);
                        }

                        out.set(true);
                    });
        }
        return out.get();
    }

    public static boolean checkPipes(Level world,ItemStack heldItem,BlockPos pos, BlockState state) {
        AtomicBoolean out = new AtomicBoolean(false);
        if (isCrystalClearLoaded()){
            List<GlassEncasedPipeBlockCompat> glassPipes = new ArrayList<>();
            ForgeRegistries.BLOCKS.getKeys().stream().filter(r -> ForgeRegistries.BLOCKS.getValue(r) instanceof GlassEncasedPipeBlockCompat).forEach(r -> glassPipes.add((GlassEncasedPipeBlockCompat) ForgeRegistries.BLOCKS.getValue(r)));
            glassPipes.stream().filter(p->p.getCasing().isIn(heldItem))
                    .findFirst().ifPresent(s->{
                        if (!world.isClientSide) {
                            FluidTransportBehaviour.cacheFlows(world, pos);
                            world.setBlockAndUpdate(pos, EncasedPipeBlock.transferSixWayProperties(state, s.defaultBlockState()));
                            FluidTransportBehaviour.loadFlows(world, pos);
                        }

                        out.set(true);
                    });
        }
        return out.get();
    }


    public static boolean checkShaft(Level world, ItemStack heldItem, BlockPos pos, BlockState state) {
        AtomicBoolean out = new AtomicBoolean(false);
        if (isCrystalClearLoaded()){
            List<GlassEncasedShaftBlock> glassShafts = new ArrayList<>(){
                {
                    if (isCrystalClearLoaded()) {
                        add(CreateCrystalClearCompatibility.COPPER_GLASS_ENCASED_SHAFT.get());
                        add(CreateCrystalClearCompatibility.COPPER_CLEAR_GLASS_ENCASED_SHAFT.get());
                    }
                }
            };
            glassShafts.stream().filter(s->s.getCasing().isIn(heldItem))
                    .findFirst().ifPresent(s->{
                        if (!world.isClientSide) {
                            KineticTileEntity.switchToBlockState(world, pos, s.defaultBlockState().setValue(AXIS, state.getValue(AXIS)));
                        }
                        out.set(true);
                    });
        }
        return out.get();
    }





}
