package fr.iglee42.createcasing.registries;

import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.simibubi.create.content.redstone.displayLink.source.NixieTubeDisplaySource;
import com.tterrag.registrate.util.entry.RegistryEntry;
import fr.iglee42.createcasing.displaySources.AutoClutchDisplaySource;

import java.util.function.Supplier;

import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;

public class EncasedDisplaySources {

    public static final RegistryEntry<DisplaySource, AutoClutchDisplaySource> AUTO_CLUTCH = simple("auto_clutch", AutoClutchDisplaySource::new);


    private static <T extends DisplaySource> RegistryEntry<DisplaySource, T> simple(String name, Supplier<T> supplier) {
        return REGISTRATE.displaySource(name, supplier).register();
    }

    public static void register() {
    }
}
