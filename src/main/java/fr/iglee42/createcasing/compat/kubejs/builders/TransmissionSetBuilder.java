package fr.iglee42.createcasing.compat.kubejs.builders;

import com.google.common.base.Preconditions;
import dev.latvian.mods.kubejs.script.SourceLine;
import dev.latvian.mods.rhino.util.HideFromJS;
import fr.iglee42.createcasing.sets.transmissions.TransmissionSet;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class TransmissionSetBuilder {

    private String name;
    public SourceLine sourceLine;
    private TransmissionSet.Options options;


    /*public TransmissionSetBuilder(String name) {
        this.name = name;
        this.sourceLine = SourceLine.UNKNOWN;
        this.options = new TransmissionSet.Options().kjsGenerated();
    }*/

    @HideFromJS
    public String getName() {
        return name;
    }

    @HideFromJS
    public TransmissionSet.Options getOptions() {
        return options;
    }

    public TransmissionSetBuilder shaft() {
        this.options.shaft();
        return this;
    }


    public TransmissionSetBuilder cogwheel() {
        this.options.cogwheel();
        return this;
    }

    public TransmissionSetBuilder largeCogwheel() {
        this.options.largeCogwheel();
        return this;
    }

    public TransmissionSetBuilder notEncasable() {
        this.options.notEncasable();
        return this;
    }

    public TransmissionSetBuilder everything(Supplier<? extends Item> item){
        return shaft().cogwheel().largeCogwheel();
    }
}
