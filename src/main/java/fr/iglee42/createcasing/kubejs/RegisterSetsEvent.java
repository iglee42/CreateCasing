package fr.iglee42.createcasing.kubejs;

import dev.latvian.mods.kubejs.event.EventResult;
import dev.latvian.mods.kubejs.event.StartupEventJS;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.Context;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.kubejs.builders.CasingSetBuilder;
import fr.iglee42.createcasing.kubejs.builders.TransmissionSetBuilder;
import fr.iglee42.createcasing.transmissions.TransmissionSets;

import java.util.LinkedList;
import java.util.List;

public class RegisterSetsEvent extends StartupEventJS {
    public final List<CasingSetBuilder> casingSets;
    public final List<TransmissionSetBuilder> transmissionSets;

    public RegisterSetsEvent() {
        this.casingSets = new LinkedList<>();
        this.transmissionSets = new LinkedList<>();
    }

    public CasingSetBuilder createCasing(String name) {
        var b = new CasingSetBuilder(name);
        ConsoleJS.STARTUP.warn("[Create Encased] You're using an experimental KubeJS Plugin ! BlockStates and models are not generated. Crash may appears !");

        casingSets.add(b);

        return b;
    }

    public TransmissionSetBuilder createTransmission(String name) {
        var b = new TransmissionSetBuilder(name);
        ConsoleJS.STARTUP.warn("[Create Encased] You're using an experimental KubeJS Plugin ! BlockStates and models are not generated. Crash may appears !");

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
