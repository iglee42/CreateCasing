package fr.iglee42.createcasing.compatibility;

import com.cyvack.create_crystal_clear.Create_Crystal_Clear;
import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.ShaftlessCogwheelBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.compatibility.createextendedcogs.CreateExtendedCogwheelsCompat;
import fr.iglee42.createcasing.compatibility.createextendedcogs.CustomGlassCogwheelCompat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class CreateCrystalExtendedCompat {
    public static void registerCogs() {
        CreateExtendedCogwheelsCompat.forCogs(r->{
            CustomCogwheelBlock cog = (CustomCogwheelBlock) ExtendedCogwheels.registrate().get(r.getPath(), Registry.BLOCK_REGISTRY).get();
            HalfShaftCogwheelBlock half = (HalfShaftCogwheelBlock) ExtendedCogwheels.registrate().get((cog.isLargeCog() ? "large_" : "") +"half_shaft_"+cog.getMaterial().asId() + "_cogwheel",Registry.BLOCK_REGISTRY).get();
            ShaftlessCogwheelBlock shaftless = (ShaftlessCogwheelBlock) ExtendedCogwheels.registrate().get((cog.isLargeCog() ? "large_" : "") +"shaftless_"+cog.getMaterial().asId() + "_cogwheel",Registry.BLOCK_REGISTRY).get();
            String name = cog.getMaterial().asId().toLowerCase();
            Create_Crystal_Clear.registrate().getAll(Registry.BLOCK_REGISTRY).stream().filter(b -> b != null && b.get() instanceof GlassCasing).forEach(b -> {
                BlockEntry<? extends Block> casing = ((BlockEntry<?>) b);
                CreateExtendedCogwheelsCompat.REGISTRATE.block(b.getId().getPath().replace("_casing","") + "_"  +(cog.isLargeCog() ? "large_" : "") + name+"_encased_cogwheel", p -> new CustomGlassCogwheelCompat(cog.isLargeCog(), p, (BlockEntry<GlassCasing>) casing,cog,half,shaftless))
                        .properties(p -> p.color(MaterialColor.PODZOL))
                        .properties(BlockBehaviour.Properties::noOcclusion)
                        .addLayer(()-> RenderType::cutoutMipped)
                        .transform(axeOrPickaxe())
                        .register();
            });
        });
    }
}
