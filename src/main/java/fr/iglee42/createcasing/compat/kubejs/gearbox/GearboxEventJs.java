package fr.iglee42.createcasing.compat.kubejs.gearbox;

import com.mojang.logging.LogUtils;
import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.event.StartupEventJS;
import fr.iglee42.createcasing.CreateCasing;

public class GearboxEventJs extends StartupEventJS {

    public GearboxBuilderJs create(String name){
        name = name.replaceFirst(".*:","").replace(":","_");
        CreateCasing.LOGGER.info("Trying to Create KubeJS gearbox " + name);
        return new GearboxBuilderJs(name);
    }

}
