package fr.iglee42.createcasing.compat.kubejs.mixer;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import fr.iglee42.createcasing.CreateCasing;

public class MixerEventJs extends StartupEventJS {

    public MixerBuilderJs create(String name){
        name = name.replaceFirst(".*:","").replace(":","_");
        CreateCasing.LOGGER.info("Trying to Create KubeJS Mixer " + name);
        return new MixerBuilderJs(name);
    }

}
