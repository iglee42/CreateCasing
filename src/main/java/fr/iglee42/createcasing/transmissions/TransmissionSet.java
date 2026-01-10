package fr.iglee42.createcasing.transmissions;

import com.google.common.base.Preconditions;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class TransmissionSet {

    private final String name;
    private final Supplier<? extends Item> item;
    private @Nullable BlockEntry<? extends ShaftBlock> shaftBlock;
    private @Nullable BlockEntry<? extends CogWheelBlock> cogwheelBlock;
    private @Nullable BlockEntry<? extends CogWheelBlock> largeCogwheelBlock;
    private @Nullable Supplier<Function<BlockBehaviour.Properties,? extends ShaftBlock>> shaftConstructor;
    private @Nullable Supplier<BiFunction<BlockBehaviour.Properties,Boolean,? extends CogWheelBlock>> cogwheelConstructor;

    private @Nullable Supplier<BlockEntityType<?>> shaftBeType;
    private @Nullable Supplier<BlockEntityType<?>> cogwheelBeType;
    private @Nullable Supplier<BlockEntityType<?>> largeCogwheelBeType;

    private final boolean shaft;
    private final boolean cogwheel;
    private final boolean largeCogwheel;
    private final boolean notEncasable;
    private final boolean isKJSGenerated;


    protected TransmissionSet(String name, Options options) {
        this.name = name;
        this.item = options.item;
        shaft = options.shaft;
        cogwheel = options.cogwheel;
        largeCogwheel = options.largeCogwheel;

        this.notEncasable = options.notEncasable;
        isKJSGenerated = options.isKJSGenerated;

        this.shaftConstructor = options.shaftConstructor;
        this.cogwheelConstructor = options.cogwheelConstructor;
        this.shaftBeType = options.shaftBeType;
        this.cogwheelBeType = options.cogwheelBeType;
        this.largeCogwheelBeType = options.largeCogwheelBeType;

        if (options.existingShaft != null) shaftBlock = options.existingShaft;
        if (options.existingCogwheel != null) cogwheelBlock = options.existingCogwheel;
        if (options.existingLargeCogwheel != null) largeCogwheelBlock = options.existingLargeCogwheel;
    }

    public String getName() {
        return name;
    }

    public boolean doesGenerateCogwheel(){
        return cogwheel;
    }
    public boolean doesGenerateShaft(){
        return shaft;
    }
    public boolean doesGenerateLargeCogwheel(){
        return largeCogwheel;
    }

    public Item getItem() {
        return item.get();
    }

    @Nullable
    public BlockEntry<? extends ShaftBlock> getShaftSupplier() {
        return shaftBlock;
    }

    @Nullable
    public ShaftBlock getShaft() {
        return shaftBlock == null ? null : shaftBlock.get();
    }

    @Nullable
    public BlockEntry<? extends CogWheelBlock> getCogwheelSupplier() {
        return cogwheelBlock;
    }

    @Nullable
    public CogWheelBlock getCogwheel() {
        return cogwheelBlock == null ? null : cogwheelBlock.get();
    }

    @Nullable
    public BlockEntry<? extends CogWheelBlock> getLargeCogwheelSupplier() {
        return largeCogwheelBlock;
    }

    @Nullable
    public CogWheelBlock getLargeCogwheel() {
        return largeCogwheelBlock == null ? null : largeCogwheelBlock.get();
    }

    @Nullable
    public Function<BlockBehaviour.Properties,? extends ShaftBlock> getShaftConstructor(){
        return shaftConstructor == null ? null : shaftConstructor.get();
    }

    @Nullable
    public BiFunction<BlockBehaviour.Properties,Boolean,? extends CogWheelBlock> getCogwheelConstructor(){
        return cogwheelConstructor == null ? null : cogwheelConstructor.get();
    }

    @Nullable
    public BlockEntityType<?> getShaftBlockEntityType(){
        return shaftBeType == null ? null : shaftBeType.get();
    }

    @Nullable
    public BlockEntityType<?> getCogwheelBlockEntityType(){
        return cogwheelBeType == null ? null : cogwheelBeType.get();
    }

    @Nullable
    public BlockEntityType<?> getLargeCogwheelBlockEntityType(){
        return largeCogwheelBeType == null ? null : largeCogwheelBeType.get();
    }

    public void setShaft(@Nonnull BlockEntry<? extends ShaftBlock> shaft){
        if (getShaftSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a shaft that has already been referenced");
        shaftBlock = shaft;
    }

    public void setCogwheel(@Nonnull BlockEntry<? extends CogWheelBlock> cogwheel){
        if (getCogwheelSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a cogwheel that has already been referenced");
        cogwheelBlock = cogwheel;
    }

    public void setLargeCogwheel(@Nonnull BlockEntry<? extends CogWheelBlock> cogwheel){
        if (getLargeCogwheelSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a large cogwheel that has already been referenced");
        largeCogwheelBlock = cogwheel;
    }

    public boolean isInSet(Block block){
        return  block.equals(getShaft()) || block.equals(getCogwheel()) || block.equals(getLargeCogwheel());
    }

    public boolean isNotEncasable() {
        return notEncasable;
    }

    public boolean isKJSGenerated() {
        return isKJSGenerated;
    }

    public static class Options {
        private Supplier<? extends Item> item;
        private boolean shaft;
        private boolean cogwheel;
        private boolean largeCogwheel;
        private boolean notEncasable;
        private boolean isKJSGenerated;

        private @Nullable BlockEntry<? extends ShaftBlock> existingShaft;
        private @Nullable BlockEntry<? extends CogWheelBlock> existingCogwheel;
        private @Nullable BlockEntry<? extends CogWheelBlock> existingLargeCogwheel;

        private @Nullable Supplier<Function<BlockBehaviour.Properties,? extends ShaftBlock>> shaftConstructor;
        private @Nullable Supplier<BiFunction<BlockBehaviour.Properties,Boolean,? extends CogWheelBlock>> cogwheelConstructor;

        private @Nullable Supplier<BlockEntityType<?>> shaftBeType;
        private @Nullable Supplier<BlockEntityType<?>> cogwheelBeType;
        private @Nullable Supplier<BlockEntityType<?>> largeCogwheelBeType;


        public Options() {
            shaft = false;
            cogwheel = false;
            largeCogwheel = false;
        }

        public Options item(Supplier<? extends Item> item) {
            Preconditions.checkNotNull(item,"Item Supplier can't be null");
            this.item = item;
            return this;
        }

        public Options shaft() {
            this.shaft = true;
            return this;
        }


        public Options cogwheel() {
            this.cogwheel = true;
            return this;
        }

        public Options largeCogwheel() {
            this.largeCogwheel = true;
            return this;
        }

        public Options notEncasable() {
            this.notEncasable = true;
            return this;
        }


        public Options shaftConstructor(Supplier<Function<BlockBehaviour.Properties,? extends ShaftBlock>> constructor){
            this.shaftConstructor = constructor;
            return this;
        }

        public Options cogwheelConstructor(Supplier<BiFunction<BlockBehaviour.Properties,Boolean,? extends CogWheelBlock>> constructor){
            this.cogwheelConstructor = constructor;
            return this;
        }

        public Options shaftBlockEntityType(Supplier<BlockEntityType<?>> beType){
            this.shaftBeType = beType;
            return this;
        }

        public Options cogwheelBlockEntityType(Supplier<BlockEntityType<?>> beType){
            this.cogwheelBeType = beType;
            return this;
        }

        public Options largeCogwheelBlockEntityType(Supplier<BlockEntityType<?>> beType){
            this.largeCogwheelBeType = beType;
            return this;
        }

        public Options everything(Supplier<? extends Item> item){
            return item(item).shaft().cogwheel().largeCogwheel();
        }

        Options existingShaft(BlockEntry<? extends ShaftBlock> shaft) {
            this.existingShaft = shaft;
            this.shaft = false;
            return this;
        }

        Options existingCogwheel(BlockEntry<? extends CogWheelBlock> cogwheel) {
            this.existingCogwheel = cogwheel;
            this.cogwheel = false;
            return this;
        }

        Options existingLargeCogwheel(BlockEntry<? extends CogWheelBlock> largeCogwheel) {
            this.existingLargeCogwheel = largeCogwheel;
            this.largeCogwheel = false;
            return this;
        }

        public Options kjsGenerated(){
            this.isKJSGenerated = true;
            return this;
        }


    }
}
