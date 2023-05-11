package fr.iglee42.createcasing.compatibility.createextendedcogs;

import com.cyvack.create_crystal_clear.Create_Crystal_Clear;
import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelTileEntity;
import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.ShaftlessCogwheelBlock;
import com.simibubi.create.Create;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Iterate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.compatibility.createcrystalclear.CreateCrystalClearCompatibility;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock.AXIS;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class CreateExtendedCogwheelsCompat {

    public static final String REGISTER_MODID = "createcasing-extendedgears";
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(REGISTER_MODID);

    public static boolean isModLoaded(){return ModList.get().isLoaded("extendedgears");}
    public static BlockEntityEntry<CustomCogwheelTileEntity> COGWHEEL = REGISTRATE.tileEntity("extended_cogs_tile", CustomCogwheelTileEntity::new)
            .instance(() -> EncasedCogWheelTileInstanceCompat::new, false)
            .renderer(() -> EncasedCogWheelTileRendererCompat::new)
            .register();
    private static NonNullSupplier<? extends Block>[] getCogs() {
        NonNullSupplier<? extends Block>[] blocks = new NonNullSupplier[] {};
        REGISTRATE.getAll(Registry.BLOCK_REGISTRY).stream().filter(b->b.get() instanceof CustomCogwheelCompat || b.get() instanceof CustomGlassCogwheelCompat).forEach(r->blocks[blocks.length] = r::get);
        return blocks;
    }

    public static void register() {
        forCogs(r-> {
            CustomCogwheelBlock cog = (CustomCogwheelBlock) ExtendedCogwheels.registrate().get(r.getPath(),Registry.BLOCK_REGISTRY).get();
            HalfShaftCogwheelBlock half = (HalfShaftCogwheelBlock) ExtendedCogwheels.registrate().get((cog.isLargeCog() ? "large_" : "") +"half_shaft_"+cog.getMaterial().asId() + "_cogwheel",Registry.BLOCK_REGISTRY).get();
            ShaftlessCogwheelBlock shaftless = (ShaftlessCogwheelBlock) ExtendedCogwheels.registrate().get((cog.isLargeCog() ? "large_" : "") +"shaftless_"+cog.getMaterial().asId() + "_cogwheel",Registry.BLOCK_REGISTRY).get();
            String name = getCogName(cog.getRegistryName().getPath().toString());
            Create.REGISTRATE.getAll(Registry.BLOCK_REGISTRY).stream().filter(b -> b.get() instanceof CasingBlock).forEach(b -> {
                BlockEntry<? extends Block> block = ((BlockEntry<?>) b);
                REGISTRATE.block(b.getId().getPath().replace("_casing","") + "_"  +(cog.isLargeCog() ? "large_" : "") + name+"_encased_cogwheel", p -> new CustomCogwheelCompat(cog.isLargeCog(), p, (BlockEntry<CasingBlock>) block,cog,half,shaftless))
                        .properties(p -> p.color(MaterialColor.PODZOL))
                        .properties(BlockBehaviour.Properties::noOcclusion)
                        .addLayer(()-> RenderType::cutoutMipped)
                        .transform(axeOrPickaxe())
                        .register();
            });
        });
    }

    public static String getCogName(String name) {
        return name.replace("large_","").replace("_cogwheel","");
    }

    public static void forCogs(Consumer<ResourceLocation> forEach) {
        List<ResourceLocation> cogwheels = new ArrayList<>();
        ExtendedCogwheels.registrate().getAll(Registry.BLOCK_REGISTRY).stream().filter(b->b.getId().getPath().endsWith("_cogwheel") && !b.getId().getPath().contains("shaftless_") && !b.getId().getPath().contains("half_shaft_")).forEach(b-> cogwheels.add(b.getId()));
        cogwheels.stream().filter(r->ExtendedCogwheels.registrate().get(r.getPath(),Registry.BLOCK_REGISTRY)!= null).forEach(forEach);
    }

    public static boolean checkCogs(Level world, BlockState state, BlockPos pos, ItemStack heldItem) {
        AtomicBoolean out = new AtomicBoolean(false);
        List<CustomCogwheelCompat> cogs = new ArrayList<>();
        ForgeRegistries.BLOCKS.getKeys().stream().filter(r -> ForgeRegistries.BLOCKS.getValue(r) instanceof CustomCogwheelCompat).forEach(r -> cogs.add((CustomCogwheelCompat) ForgeRegistries.BLOCKS.getValue(r)));
        cogs.stream().filter(c->(c.getCogwheel() == state.getBlock() || c.getHalfCog() == state.getBlock() || c.getShaftlessCog() == state.getBlock()) && ((CogWheelBlock)state.getBlock()).isLargeCog() == c.isLargeCog() && c.getCasing().isIn(heldItem)).findFirst().ifPresent(encasedCog->{
            if (world.isClientSide) {
               out.set(true);
               return;
            }

            BlockState encasedState = encasedCog.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            Direction[] var14 = Iterate.directionsInAxis(state.getValue(AXIS));

            for (Direction d : var14) {
                BlockState adjacentState = world.getBlockState(pos.relative(d));
                if (adjacentState.getBlock() instanceof IRotate) {
                    IRotate def = (IRotate) adjacentState.getBlock();
                    if (def.hasShaftTowards(world, pos.relative(d), adjacentState, d.getOpposite())) {
                        encasedState = encasedState.cycle(d.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT : EncasedCogwheelBlock.BOTTOM_SHAFT);
                    }
                }
            }

            KineticTileEntity.switchToBlockState(world, pos, encasedState);
            out.set(true);
        });
        if (CreateCasing.isCrystalClearLoaded()){
            if (CreateCrystalClearCompatibility.checkExtendedCogs(world,state,pos,heldItem)) out.set(true);
        }
        return out.get();
    }
}
