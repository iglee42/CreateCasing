package fr.iglee42.createcasing;

import com.mojang.logging.LogUtils;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.simibubi.create.CreateClient;
import com.simibubi.create.foundation.data.CreateRegistrate;
import fr.iglee42.createcasing.compatibility.createcrystalclear.CreateCrystalClearCompatibility;
import fr.iglee42.createcasing.compatibility.createextendedcogs.CreateExtendedCogwheelsCompat;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
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

    public static final CreativeModeTab TAB = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon() {return ModBlocks.BRASS_GEARBOX.asStack();}
    };
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

    public CreateCasing() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get()
                .getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
        //if (isExtendedCogsLoaded())CreateExtendedCogwheelsCompat.REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());

        //if (isExtendedCogsLoaded()) ExtendedCogwheels.registrate().addRegisterCallback(Registry.BLOCK_REGISTRY, CreateExtendedCogwheelsCompat::register);

        ModBlocks.register();
        ModBlockEntities.register();

        if (isCrystalClearLoaded()) CreateCrystalClearCompatibility.register();

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
