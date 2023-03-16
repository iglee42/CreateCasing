package fr.iglee42.createcasing.compatibility.createextendedcogs;

import com.cyvack.create_crystal_clear.Create_Crystal_Clear;
import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.rabbitminers.extendedgears.ExtendedGears;
import com.rabbitminers.extendedgears.basecog.MetalCogWheel;
import com.rabbitminers.extendedgears.tileentities.CogWheelKineticTileEntity;
import com.simibubi.create.Create;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogInstance;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import fr.iglee42.createcasing.compatibility.createcrystalclear.CreateCrystalClearCompatibility;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class CreateExtendedCogwheelsCompat {

    public static final String REGISTER_MODID = "createcasing-extendedgears";
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(REGISTER_MODID);

    public static boolean isModLoaded(){return ModList.get().isLoaded("extendedgears");}

    public static BlockEntityEntry<CogWheelKineticTileEntity> COGWHEEL = REGISTRATE.tileEntity("extended_cogs_tile", CogWheelKineticTileEntity::new)
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
            MetalCogWheel cog = (MetalCogWheel) ExtendedGears.registrate().get(r.getPath(),Registry.BLOCK_REGISTRY).get();
            String name = getCogName(r.getPath().toString());
            Create.REGISTRATE.getAll(Registry.BLOCK_REGISTRY).stream().filter(b -> b.get() instanceof CasingBlock).forEach(b -> {
                BlockEntry<? extends Block> block = ((BlockEntry<?>) b);
                REGISTRATE.block(b.getId().getPath().replace("_casing","") + "_"  +(cog.isLargeCog() ? "large_" : "") + name+"_encased_cogwheel", p -> new CustomCogwheelCompat(cog.isLargeCog(), p, (BlockEntry<CasingBlock>) block,cog))
                        .properties(p -> p.color(MaterialColor.PODZOL))
                        .properties(BlockBehaviour.Properties::noOcclusion)
                        .addLayer(()-> RenderType::cutoutMipped)
                        .transform(axeOrPickaxe())
                        .register();
            });
           /*if (CreateCasing.isCrystalClearLoaded()) {
                Create_Crystal_Clear.registrate().getAll(Registry.BLOCK_REGISTRY).stream().filter(b -> b != null && b.get() instanceof GlassCasing).forEach(b -> {
                    BlockEntry<? extends Block> block = ((BlockEntry<?>) b);
                    REGISTRATE.block(b.getId().getPath().replace("_casing", "") + "_" + (cog.isLargeCog() ? "large_" : "") + name + "_encased_cogwheel", p -> new CustomGlassCogwheelCompat(cog.isLargeCog(), p, (BlockEntry<GlassCasing>) block, cog))
                            .properties(p -> p.color(MaterialColor.PODZOL))
                            .properties(BlockBehaviour.Properties::noOcclusion)
                            .addLayer(() -> RenderType::cutoutMipped)
                            .transform(axeOrPickaxe())
                            .register();
                });
            }*/
        });
    }

    private static String getCogName(String name) {
        return name.replace("large_","").replace("_cogwheel","");
    }

    private static void forCogs(Consumer<ResourceLocation> forEach) {
        List<ResourceLocation> cogwheels = new ArrayList<>();
        ExtendedGears.registrate().getAll(Registry.BLOCK_REGISTRY).stream().filter(b->b.getId().getPath().endsWith("_cogwheel")).forEach(b-> cogwheels.add(b.getId()));
        cogwheels.stream().filter(r->ExtendedGears.registrate().get(r.getPath(),Registry.BLOCK_REGISTRY)!= null).forEach(forEach);
    }
}
