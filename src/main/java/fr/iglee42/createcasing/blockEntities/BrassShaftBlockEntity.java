package fr.iglee42.createcasing.blockEntities;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import fr.iglee42.createcasing.CreateCasing;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

public class BrassShaftBlockEntity extends MetalShaftBlockEntity {


    public static int BASE_STRESS = 1024;

    protected int configuredStress = BASE_STRESS;
    protected Mode mode = Mode.USED_STRESS;
    protected Operation operation = Operation.GREATER;

    public BrassShaftBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide){
            switch (mode){
                case USED_STRESS -> {
                    if (operation.isValid((int) stress,configuredStress)) RotationPropagator.handleRemoved(level,getBlockPos(),this);
                }
                case REMAINING_STRESS -> {
                    if (operation.isValid((int) (capacity - stress),configuredStress)) RotationPropagator.handleRemoved(level,getBlockPos(),this);
                }
                case MAX_STRESS -> {
                    if (operation.isValid((int) capacity,configuredStress)) RotationPropagator.handleRemoved(level,getBlockPos(),this);
                }
            }
        }
    }

    public float getCapacity(){
        return capacity;
    }

    public int getMaxSupportedStress(){
        return configuredStress;
    }

    public void setMaxSupportedStress(int stress) {
        configuredStress = stress;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public float getStress(){
        return stress;
    }

    @Override
    protected void write(CompoundTag compound, HolderLookup.Provider provider, boolean clientPacket) {
        compound.putInt("configuredStress", configuredStress);
        compound.putString("mode", mode.getSerializedName());
        compound.putString("operation", operation.getSerializedName());
        super.write(compound,provider, clientPacket);
    }

    @Override
    protected void read(CompoundTag compound, HolderLookup.Provider provider, boolean clientPacket) {
        configuredStress = compound.getInt("configuredStress");
        mode = Mode.byName(compound.getString("mode"));
        operation = Operation.byName(compound.getString("operation"));
        super.read(compound, provider, clientPacket);
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public enum Mode implements StringRepresentable {
        USED_STRESS, REMAINING_STRESS, MAX_STRESS;


        @Override
        public String getSerializedName() {
            return name().toLowerCase();
        }

        public static List<? extends Component> getComponents(){
            return Arrays.stream(values())
                    .map(m-> CreateCasing.MODID + ".brass_shaft.mode."+m.getSerializedName())
                    .map(Component::translatable)
                    .toList();
        }

        public static Mode byId(int mode){
            return Arrays.stream(values()).filter(m->m.ordinal() == mode).findFirst().orElse(USED_STRESS);
        }
        public static Mode byName(String mode){
            return Arrays.stream(values()).filter(m-> m.getSerializedName().equals(mode)).findFirst().orElse(USED_STRESS);
        }
    }

    public enum Operation implements StringRepresentable {
        LESS((stress,capacity)->stress < capacity),
        EQUALS(Objects::equals),
        GREATER((stress,capacity)->stress > capacity);


        final BiPredicate<Integer,Integer> isTrue;

        Operation(BiPredicate<Integer, Integer> isTrue) {
            this.isTrue = isTrue;
        }

        @Override
        public String getSerializedName() {
            return name().toLowerCase();
        }

        public static List<? extends Component> getComponents(){
            return Arrays.stream(values())
                    .map(m-> CreateCasing.MODID + ".brass_shaft.operation."+m.getSerializedName())
                    .map(Component::translatable)
                    .toList();
        }

        public static Operation byId(int mode){
            return Arrays.stream(values()).filter(m->m.ordinal() == mode).findFirst().orElse(GREATER);
        }
        public static Operation byName(String mode){
            return Arrays.stream(values()).filter(m-> m.getSerializedName().equals(mode)).findFirst().orElse(GREATER);
        }

        public boolean isValid(int stress, int capacity){
            return isTrue.test(stress,capacity);
        }

    }
}
