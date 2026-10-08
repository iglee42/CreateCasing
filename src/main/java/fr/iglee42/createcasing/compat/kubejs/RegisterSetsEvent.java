package fr.iglee42.createcasing.compat.kubejs;

import dev.latvian.mods.kubejs.event.EventResult;
import dev.latvian.mods.kubejs.event.KubeStartupEvent;
import fr.iglee42.createcasing.sets.casings.CasingSets;
import fr.iglee42.createcasing.compat.kubejs.builders.CasingSetBuilder;
import fr.iglee42.createcasing.compat.kubejs.builders.TransmissionSetBuilder;
import fr.iglee42.createcasing.sets.transmissions.TransmissionSets;

import java.util.LinkedList;
import java.util.List;

public class RegisterSetsEvent implements KubeStartupEvent {
    public final List<CasingSetBuilder> casingSets;
    public final List<TransmissionSetBuilder> transmissionSets;

    public RegisterSetsEvent() {
        this.casingSets = new LinkedList<>();
        this.transmissionSets = new LinkedList<>();
    }

    // TODO update KJS compat for new system

    /*public CasingSetBuilder createCasing(Context cx, String name, Function<RegistryAccess, HolderSet<Item>> item) {
        var sourceLine = SourceLine.of(cx);
        var b = new CasingSetBuilder(name, item);
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
    }*/


    @Override
    public void afterPosted(EventResult result) {
        casingSets.forEach(builder->{
            //CasingSets.register(builder.getName(),builder.getOptions());
        });

        transmissionSets.forEach(builder->{
            //TransmissionSets.register(builder.getName(),builder.getOptions());
        });
    }
}
