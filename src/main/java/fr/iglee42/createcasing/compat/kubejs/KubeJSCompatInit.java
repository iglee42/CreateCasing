package fr.iglee42.createcasing.compat.kubejs;

import dev.latvian.mods.kubejs.script.ScriptType;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.compat.kubejs.casing.CasingEventJs;
import fr.iglee42.createcasing.compat.kubejs.depot.DepotEventJs;
import fr.iglee42.createcasing.compat.kubejs.encased.EncasedEventJs;
import fr.iglee42.createcasing.compat.kubejs.gearbox.GearboxEventJs;
import fr.iglee42.createcasing.compat.kubejs.mixer.MixerEventJs;
import fr.iglee42.createcasing.compat.kubejs.press.PressEventJs;
import net.minecraftforge.fml.ModList;

public class KubeJSCompatInit {

    public static void init(){
        /*if(ModList.get().isLoaded("kubejs")){

            CreateCasing.LOGGER.info("Event posting...");
            CreateCasingEventsJS.CASING.post(ScriptType.STARTUP,new CasingEventJs());
            CreateCasingEventsJS.GEARBOX.post(ScriptType.STARTUP,new GearboxEventJs());
            CreateCasingEventsJS.ENCASED.post(ScriptType.STARTUP,new EncasedEventJs());
            CreateCasingEventsJS.DEPOT.post(ScriptType.STARTUP,new DepotEventJs());
            CreateCasingEventsJS.MIXER.post(ScriptType.STARTUP,new MixerEventJs());
            CreateCasingEventsJS.PRESS.post(ScriptType.STARTUP,new PressEventJs());
        }*/
    }
}
