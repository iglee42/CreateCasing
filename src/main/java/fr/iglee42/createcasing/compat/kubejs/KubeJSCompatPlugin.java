package fr.iglee42.createcasing.compat.kubejs;


import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import dev.latvian.mods.kubejs.generator.DataJsonGenerator;
import fr.iglee42.createcasing.CreateCasing;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Map;

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
    public void generateAssetJsons(AssetJsonGenerator generator) {
        CreateCasingBuilderBaseJS.BUILDERS.forEach(b->b.generateAssetJsons(generator));
    }

    @Override
    public void generateDataJsons(DataJsonGenerator generator) {
        CreateCasingBuilderBaseJS.BUILDERS.forEach(b->b.generateDataJsons(generator));
    }

    @Override
    public void generateLang(Map<String, String> lang) {
        CreateCasingBuilderBaseJS.BUILDERS.forEach(b->b.generateLang(lang));
    }
}
