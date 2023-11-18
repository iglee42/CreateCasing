package fr.iglee42.createcasing.compat.kubejs;

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
        if(ModList.get().isLoaded("kubejs")){
            CreateCasing.LOGGER.info("Event posting...");
            CreateCasingEventsJS.CASING.post(new CasingEventJs());
            CreateCasingEventsJS.GEARBOX.post(new GearboxEventJs());
            CreateCasingEventsJS.ENCASED.post(new EncasedEventJs());
            CreateCasingEventsJS.DEPOT.post(new DepotEventJs());
            CreateCasingEventsJS.MIXER.post(new MixerEventJs());
            CreateCasingEventsJS.PRESS.post(new PressEventJs());
        }
    }
}
