package fr.iglee42.createcasing.kubejs.builders;

import com.google.common.base.Preconditions;
import dev.latvian.mods.rhino.util.HideFromJS;
import fr.iglee42.createcasing.transmissions.TransmissionSet;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class TransmissionSetBuilder {

    private final String name;
    private TransmissionSet.Options options;


    public TransmissionSetBuilder(String name) {
        this.name = name;
        this.options = new TransmissionSet.Options().kjsGenerated();
    }

    @HideFromJS
    public String getName() {
        return name;
    }

    @HideFromJS
    public TransmissionSet.Options getOptions() {
        return options;
    }

    public TransmissionSetBuilder item(Supplier<? extends Item> item) {
        Preconditions.checkNotNull(item,"Item Supplier can't be null");
        this.options.item(item);
        return this;
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
        return item(item).shaft().cogwheel().largeCogwheel();
    }
}
