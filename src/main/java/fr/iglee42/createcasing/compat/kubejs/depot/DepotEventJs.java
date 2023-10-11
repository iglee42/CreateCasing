package fr.iglee42.createcasing.compat.kubejs.depot;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import fr.iglee42.createcasing.CreateCasing;

public class DepotEventJs extends StartupEventJS {

    public DepotBuilderJs create(String name){
        name = name.replaceFirst(".*:","").replace(":","_");
        CreateCasing.LOGGER.info("Trying to Create KubeJS Depot " + name);
        return new DepotBuilderJs(name);
    }

}
