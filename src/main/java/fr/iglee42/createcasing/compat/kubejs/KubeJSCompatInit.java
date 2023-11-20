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
        if(ModList.get().isLoaded("kubejs")){
            CreateCasing.LOGGER.info("Event posting...");
            new CasingEventJs().post(ScriptType.STARTUP,"createencased","casing");
            new GearboxEventJs().post(ScriptType.STARTUP,"createencased","gearbox");
            new EncasedEventJs().post(ScriptType.STARTUP,"createencased","encased");
            new DepotEventJs().post(ScriptType.STARTUP,"createencased","depot");
            new MixerEventJs().post(ScriptType.STARTUP,"createencased","mixer");
            new PressEventJs().post(ScriptType.STARTUP,"createencased","press");
        }
    }
}
