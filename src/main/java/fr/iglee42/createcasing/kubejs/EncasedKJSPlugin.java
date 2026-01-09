package fr.iglee42.createcasing.kubejs;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import fr.iglee42.createcasing.CreateCasing;

public class EncasedKJSPlugin implements KubeJSPlugin {

    public EncasedKJSPlugin() {
        CreateCasing.KJS_HANDLER = new KJSExternalHandlerImpl();
    }

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(EncasedKJSEvents.GROUP);
    }
}
