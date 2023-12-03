package fr.iglee42.createcasing.compat.kubejs;


import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.client.LangEventJS;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import dev.latvian.mods.kubejs.generator.DataJsonGenerator;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.compat.kubejs.gearbox.GearboxBuilderJs;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

public class KubeJSCompatPlugin extends KubeJSPlugin {

    public static CreateRegistrate REGISTRATE = CreateRegistrate.create("createcasing-kubejs");

    public static ResourceLocation asResource(String s) {
        return new ResourceLocation("createcasing-kubejs",s);
    }


    @Override
    public void init() {
        /*CreateCasing.LOGGER.info("KubeJs plugin Launch");
        REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());*/
    }

    @Override
    public void registerEvents() {
        /*CreateCasingEventsJS.GROUP.register();

        CreateCasing.LOGGER.info("KubeJS Events Registers");*/
    }

    @Override
    public void generateAssetJsons(AssetJsonGenerator generator) {
        CreateCasingBuilderBaseJS.BUILDERS.forEach(b->b.generateAssetJsons(generator));
    }

    @Override
    public void generateDataJsons(DataJsonGenerator generator) {
        CreateCasingBuilderBaseJS.BUILDERS.forEach(b->b.generateDataJsons(generator));
    }

    @Override
    public void generateLang(LangEventJS event) {
        CreateCasingBuilderBaseJS.BUILDERS.forEach(b->b.generateLang(event));
    }
}
