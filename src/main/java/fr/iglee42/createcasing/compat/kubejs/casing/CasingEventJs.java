package fr.iglee42.createcasing.compat.kubejs.casing;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import fr.iglee42.createcasing.CreateCasing;

public class CasingEventJs extends StartupEventJS {

    public CasingBuilderJs create(String name){
        name = name.replaceFirst(".*:","").replace(":","_");
        CreateCasing.LOGGER.info("Trying to Create KubeJS casing " + name);
        return new CasingBuilderJs(name);
    }

}
