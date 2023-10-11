package fr.iglee42.createcasing.compat.kubejs;

import dev.latvian.mods.kubejs.script.ScriptType;
import fr.iglee42.createcasing.compat.kubejs.casing.CasingEventJs;
import fr.iglee42.createcasing.compat.kubejs.encased.EncasedEventJs;
import fr.iglee42.createcasing.compat.kubejs.gearbox.GearboxEventJs;
import net.minecraftforge.fml.ModList;

public class KubeJSCompatInit {

    public static void init(){
        if(ModList.get().isLoaded("kubejs")){
            CreateCasingEventsJS.CASING.post(new CasingEventJs());
            CreateCasingEventsJS.GEARBOX.post(new GearboxEventJs());
            CreateCasingEventsJS.ENCASED.post(new EncasedEventJs());
        }
    }
}
