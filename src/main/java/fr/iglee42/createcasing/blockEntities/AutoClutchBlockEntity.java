package fr.iglee42.createcasing.blockEntities;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.blocks.AutoClutchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.TickPriority;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

public class AutoClutchBlockEntity extends SplitShaftBlockEntity {


    public static int BASE_STRESS = 1024;

    protected int configuredValue = BASE_STRESS;
    protected Mode mode = Mode.USED_STRESS;
    protected Operation operation = Operation.GREATER;

    private boolean active;
    private boolean previousActive;

    public AutoClutchBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public float getRotationSpeedModifier(Direction face) {
        if (!hasSource()) return 1;
        if (face == getSourceFacing()) return 1;
        return active ? 0 : 1;
    }

    @Override
    public void tick() {
        super.tick();
        if (getLevel() == null) return;
        if (getLevel().isClientSide()) return;
        active = switch (mode) {
            case USED_STRESS -> operation.isValid((int) stress, configuredValue);
            case REMAINING_STRESS -> operation.isValid((int) (capacity - stress), configuredValue);
            case MAX_STRESS -> operation.isValid((int) capacity, configuredValue);
            case SPEED -> operation.isValid((int) Mth.abs(speed), configuredValue);
        };

        if (active != previousActive || getBlockState().getValue(AutoClutchBlock.ACTIVE) != active){
            previousActive = active;
            detachKinetics(getLevel(),worldPosition,true);
            getLevel().setBlock(getBlockPos(),getBlockState().setValue(AutoClutchBlock.ACTIVE,active), AutoClutchBlock.UPDATE_CLIENTS);
        }
    }


    public void detachKinetics(Level worldIn, BlockPos pos, boolean reAttachNextTick) {
        BlockEntity be = worldIn.getBlockEntity(pos);
        if (be == null || !(be instanceof KineticBlockEntity))
            return;
        RotationPropagator.handleRemoved(worldIn, pos, (KineticBlockEntity) be);

        // Re-attach next tick
        if (reAttachNextTick)
            worldIn.scheduleTick(pos, getBlockState().getBlock(), 1, TickPriority.EXTREMELY_HIGH);
    }

    public float getCapacity(){
        return capacity;
    }

    public int getConfiguredValue(){
        return configuredValue;
    }

    public void setConfiguredValue(int stress) {
        configuredValue = stress;
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
        super.write(compound,provider, clientPacket);
        compound.putInt("configuredValue", configuredValue);
        compound.putString("mode", mode.getSerializedName());
        compound.putString("operation", operation.getSerializedName());
        if (clientPacket) compound.putBoolean("active", active);
    }

    @Override
    protected void read(CompoundTag compound, HolderLookup.Provider provider, boolean clientPacket) {
        super.read(compound, provider, clientPacket);
        configuredValue = compound.getInt("configuredValue");
        mode = Mode.byName(compound.getString("mode"));
        operation = Operation.byName(compound.getString("operation"));
        if (compound.contains("active")) active = compound.getBoolean("active");
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public enum Mode implements StringRepresentable {
        USED_STRESS, REMAINING_STRESS, MAX_STRESS, SPEED;


        @Override
        public String getSerializedName() {
            return name().toLowerCase();
        }

        public static List<? extends Component> getComponents(){
            return Arrays.stream(values())
                    .map(m-> CreateCasing.MODID + ".auto_clutch.mode."+m.getSerializedName())
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
        LESS((stress,capacity)->stress < capacity,"<"),
        EQUALS(Objects::equals,"="),
        GREATER((stress,capacity)->stress > capacity,">");


        final BiPredicate<Integer,Integer> isTrue;
        public final String formatted;

        Operation(BiPredicate<Integer, Integer> isTrue, String formatted) {
            this.isTrue = isTrue;
            this.formatted = formatted;
        }

        @Override
        public String getSerializedName() {
            return name().toLowerCase();
        }

        public static List<? extends Component> getComponents(){
            return Arrays.stream(values())
                    .map(m-> CreateCasing.MODID + ".auto_clutch.operation."+m.getSerializedName())
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
