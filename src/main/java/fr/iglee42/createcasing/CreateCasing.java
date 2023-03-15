package fr.iglee42.createcasing;

import com.mojang.logging.LogUtils;
import com.rabbitminers.extendedgears.ExtendedGears;
import com.simibubi.create.foundation.data.CreateRegistrate;
import fr.iglee42.createcasing.compatibility.createcrystalclear.CreateCrystalClearCompatibility;
import fr.iglee42.createcasing.compatibility.createextendedcogs.CreateExtendedCogwheelsCompat;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(CreateCasing.MODID)
public class CreateCasing {

    public static final String MODID = "createcasing";

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

    public CreateCasing() {
        REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
        if (ModList.get().isLoaded("extendedgears"))ExtendedGears.registrate().addRegisterCallback(Registry.BLOCK_REGISTRY, () -> {
            if (CreateExtendedCogwheelsCompat.isModLoaded()){
                CreateExtendedCogwheelsCompat.REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
                CreateExtendedCogwheelsCompat.register();
            }
        });

        ModBlocks.register();
        ModTiles.register();

        if (ModList.get().isLoaded("create_crystal_clear")) CreateCrystalClearCompatibility.register();


        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    public static boolean isCrystalClearLoaded(){
        return ModList.get().isLoaded("create_crystal_clear");
    }


}
