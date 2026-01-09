package fr.iglee42.createcasing.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface EncasedKJSEvents {
    EventGroup GROUP = EventGroup.of("CEncasedEvents");

    EventHandler REGISTER_SETS = GROUP.startup("registerSets",()-> RegisterSetsEvent.class);
}
