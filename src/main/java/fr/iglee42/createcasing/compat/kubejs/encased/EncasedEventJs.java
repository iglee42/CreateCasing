package fr.iglee42.createcasing.compat.kubejs.encased;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.compat.kubejs.EncasedBlockJSEnum;

public class EncasedEventJs extends StartupEventJS {
    public EncasedBuilderJs createShaft(String id){
        id = id.replaceFirst(".*:","").replace(":","_");
        CreateCasing.LOGGER.info("Trying to Create KubeJS Encased Shaft " + id);
        return new EncasedBuilderJs(id,EncasedBlockJSEnum.SHAFT);
    }
    public EncasedBuilderJs createCogwheel(String id){
        id = id.replaceFirst(".*:","").replace(":","_");
        CreateCasing.LOGGER.info("Trying to Create KubeJS Encased Cogwheel " + id);
        return new EncasedBuilderJs(id,EncasedBlockJSEnum.COGWHEEL);
    }
    public EncasedBuilderJs createLargeCogwheel(String id){
        id = id.replaceFirst(".*:","").replace(":","_");
        CreateCasing.LOGGER.info("Trying to Create KubeJS Encased Large Cogwheel " + id);
        return new EncasedBuilderJs(id,EncasedBlockJSEnum.LARGE_COGWHEEL);
    }
    public EncasedBuilderJs createPipe(String id){
        id = id.replaceFirst(".*:","").replace(":","_");
        CreateCasing.LOGGER.info("Trying to Create KubeJS Encased Pipe " + id);
        return new EncasedBuilderJs(id,EncasedBlockJSEnum.PIPE);
    }

    public EncasedBuilderJs createForCustomShaft(String id){
        id = id.replaceFirst(".*:","").replace(":","_");
        CreateCasing.LOGGER.info("Trying to Create KubeJS Encased Custom Shaft " + id);
        return new EncasedBuilderJs(id,EncasedBlockJSEnum.CUSTOM_SHAFT);
    }
}
