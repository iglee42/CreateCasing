package fr.iglee42.createcasing.kubejs;


import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.kubejs.wrappers.KJSCTTypeWrapper;
import fr.iglee42.createcasing.kubejs.wrappers.KJSSpriteShiftWrapper;
import net.createmod.catnip.render.SpriteShiftEntry;

import java.util.function.Supplier;

public class EncasedKJSPlugin extends KubeJSPlugin {

    public EncasedKJSPlugin() {
        CreateCasing.KJS_HANDLER = new KJSExternalHandlerImpl();
    }


    @Override
    public void registerEvents() {
        EncasedKJSEvents.GROUP.register();
    }

    @Override
    public void registerBindings(BindingsEvent bindings) {
        bindings.add("SpriteShifts", KJSSpriteShiftWrapper.class);
        bindings.add("CTType", KJSCTTypeWrapper.class);
    }

}
