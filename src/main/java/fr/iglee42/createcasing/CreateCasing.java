package fr.iglee42.createcasing;

import com.cyvack.create_crystal_clear.Create_Crystal_Clear;
import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.mojang.logging.LogUtils;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.ShaftlessCogwheelBlock;
import com.simibubi.create.CreateClient;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.compatibility.CreateCrystalExtendedCompat;
import fr.iglee42.createcasing.compatibility.createcrystalclear.CreateCrystalClearCompatibility;
import fr.iglee42.createcasing.compatibility.createextendedcogs.CreateExtendedCogwheelsCompat;
import fr.iglee42.createcasing.compatibility.createextendedcogs.CustomGlassCogwheelCompat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

@Mod(CreateCasing.MODID)
public class CreateCasing {

    public static final String MODID = "createcasing";

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

    public CreateCasing() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get()
                .getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
        if (isExtendedCogsLoaded())CreateExtendedCogwheelsCompat.REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());

        if (isExtendedCogsLoaded()) ExtendedCogwheels.registrate().addRegisterCallback(Registry.BLOCK_REGISTRY, CreateExtendedCogwheelsCompat::register);
        if (isCrystalClearLoaded() && isExtendedCogsLoaded()) Create_Crystal_Clear.registrate().addRegisterCallback(Registry.BLOCK_REGISTRY, CreateCrystalExtendedCompat::registerCogs);

        ModBlocks.register();
        ModTiles.register();

        if (ModList.get().isLoaded("create_crystal_clear")) CreateCrystalClearCompatibility.register();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> CreateCasingClient.onCtorClient(modEventBus, forgeEventBus));

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static boolean isExtendedCogsLoaded() {
        return ModList.get().isLoaded("extendedgears");
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    public static boolean isCrystalClearLoaded(){
        return ModList.get().isLoaded("create_crystal_clear");
    }


}
