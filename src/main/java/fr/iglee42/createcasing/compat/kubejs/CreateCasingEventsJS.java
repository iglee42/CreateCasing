package fr.iglee42.createcasing.compat.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import fr.iglee42.createcasing.compat.kubejs.casing.CasingEventJs;
import fr.iglee42.createcasing.compat.kubejs.depot.DepotEventJs;
import fr.iglee42.createcasing.compat.kubejs.encased.EncasedEventJs;
import fr.iglee42.createcasing.compat.kubejs.gearbox.GearboxEventJs;
import fr.iglee42.createcasing.compat.kubejs.mixer.MixerEventJs;
import fr.iglee42.createcasing.compat.kubejs.press.PressEventJs;

public interface CreateCasingEventsJS {
    EventGroup GROUP = EventGroup.of("CreateCasingEvents");

    EventHandler CASING = GROUP.startup("casing", () -> CasingEventJs.class);
    EventHandler GEARBOX = GROUP.startup("gearbox", () -> GearboxEventJs.class);
    EventHandler ENCASED = GROUP.startup("encased", () -> EncasedEventJs.class);
    EventHandler DEPOT = GROUP.startup("depot", () -> DepotEventJs.class);
    EventHandler MIXER = GROUP.startup("mixer", () -> MixerEventJs.class);
    EventHandler PRESS = GROUP.startup("press", () -> PressEventJs.class);
}
