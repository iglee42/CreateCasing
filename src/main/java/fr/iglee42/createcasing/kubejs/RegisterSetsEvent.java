package fr.iglee42.createcasing.kubejs;

import dev.latvian.mods.kubejs.error.KubeRuntimeException;
import dev.latvian.mods.kubejs.event.EventResult;
import dev.latvian.mods.kubejs.event.KubeStartupEvent;
import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.script.ConsoleJS;
import dev.latvian.mods.kubejs.script.SourceLine;
import dev.latvian.mods.kubejs.util.KubeResourceLocation;
import dev.latvian.mods.rhino.Context;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.kubejs.builders.CasingSetBuilder;
import fr.iglee42.createcasing.kubejs.builders.TransmissionSetBuilder;
import fr.iglee42.createcasing.transmissions.TransmissionSets;

import java.util.LinkedList;
import java.util.List;

public class RegisterSetsEvent implements KubeStartupEvent {
    public final List<CasingSetBuilder> casingSets;
    public final List<TransmissionSetBuilder> transmissionSets;

    public RegisterSetsEvent() {
        this.casingSets = new LinkedList<>();
        this.transmissionSets = new LinkedList<>();
    }

    public CasingSetBuilder createCasing(Context cx, String name) {
        var sourceLine = SourceLine.of(cx);
        var b = new CasingSetBuilder(name);
        ConsoleJS.STARTUP.warn("[Create Encased] You're using an experimental KubeJS Plugin ! BlockStates and models are not generated. Crash may appears !");

        b.sourceLine = sourceLine;
        casingSets.add(b);

        return b;
    }

    public TransmissionSetBuilder createTransmission(Context cx, String name) {
        var sourceLine = SourceLine.of(cx);
        var b = new TransmissionSetBuilder(name);
        ConsoleJS.STARTUP.warn("[Create Encased] You're using an experimental KubeJS Plugin ! BlockStates and models are not generated. Crash may appears !");

        b.sourceLine = sourceLine;
        transmissionSets.add(b);

        return b;
    }


    @Override
    public void afterPosted(EventResult result) {
        casingSets.forEach(builder->{
            CasingSets.register(builder.getName(),builder.getOptions());
        });

        transmissionSets.forEach(builder->{
            TransmissionSets.register(builder.getName(),builder.getOptions());
        });
    }
}
