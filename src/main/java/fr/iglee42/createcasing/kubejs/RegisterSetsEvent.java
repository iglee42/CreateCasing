package fr.iglee42.createcasing.kubejs;

import dev.latvian.mods.kubejs.error.KubeRuntimeException;
import dev.latvian.mods.kubejs.event.EventResult;
import dev.latvian.mods.kubejs.event.KubeStartupEvent;
import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.script.SourceLine;
import dev.latvian.mods.kubejs.util.KubeResourceLocation;
import dev.latvian.mods.rhino.Context;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.kubejs.builders.CasingSetBuilder;

import java.util.LinkedList;
import java.util.List;

public class RegisterSetsEvent implements KubeStartupEvent {
    public final List<CasingSetBuilder> casingSets;

    public RegisterSetsEvent() {
        this.casingSets = new LinkedList<>();
    }

    public CasingSetBuilder create(Context cx, String name) {
        var sourceLine = SourceLine.of(cx);
        var b = new CasingSetBuilder(name);

        b.sourceLine = sourceLine;
        casingSets.add(b);

        return b;
    }

    @Override
    public void afterPosted(EventResult result) {
        casingSets.forEach(builder->{
            CasingSets.register(builder.getName(),builder.getOptions());
        });
    }
}
