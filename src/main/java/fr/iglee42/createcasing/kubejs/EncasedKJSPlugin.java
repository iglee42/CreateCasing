package fr.iglee42.createcasing.kubejs;

import dev.architectury.event.forge.EventHandlerImplClient;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.generator.KubeAssetGenerator;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import dev.latvian.mods.kubejs.script.TypeWrapperRegistry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.kubejs.wrappers.KJSCTTypeWrapper;
import fr.iglee42.createcasing.kubejs.wrappers.KJSSpriteShiftWrapper;
import net.createmod.catnip.render.SpriteShiftEntry;

import java.util.function.Supplier;

public class EncasedKJSPlugin implements KubeJSPlugin {

    public EncasedKJSPlugin() {
        CreateCasing.KJS_HANDLER = new KJSExternalHandlerImpl();
    }

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(EncasedKJSEvents.GROUP);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("SpriteShifts", KJSSpriteShiftWrapper.class);
        bindings.add("CTType", KJSCTTypeWrapper.class);
    }

    @Override
    public void init() {
    }

    @Override
    public void generateAssets(KubeAssetGenerator generator) {

    }
}
