package fr.iglee42.createcasing.compat.kubejs.press;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import fr.iglee42.createcasing.CreateCasing;

public class PressEventJs extends StartupEventJS {

    public PressBuilderJs create(String name){
        name = name.replaceFirst(".*:","").replace(":","_");
        CreateCasing.LOGGER.info("Trying to Create KubeJS Press " + name);
        return new PressBuilderJs(name);
    }

}
