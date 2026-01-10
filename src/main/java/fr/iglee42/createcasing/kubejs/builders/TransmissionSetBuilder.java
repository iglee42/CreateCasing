package fr.iglee42.createcasing.kubejs.builders;

import com.google.common.base.Preconditions;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.latvian.mods.kubejs.script.SourceLine;
import dev.latvian.mods.rhino.util.HideFromJS;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import fr.iglee42.createcasing.transmissions.TransmissionSet;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TransmissionSetBuilder {

    private final String name;
    public SourceLine sourceLine;
    private TransmissionSet.Options options;


    public TransmissionSetBuilder(String name) {
        this.name = name;
        this.sourceLine = SourceLine.UNKNOWN;
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
