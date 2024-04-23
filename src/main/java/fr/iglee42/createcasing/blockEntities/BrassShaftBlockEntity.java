package fr.iglee42.createcasing.blockEntities;

import com.simibubi.create.content.kinetics.RotationPropagator;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BrassShaftBlockEntity extends MetalShaftBlockEntity {



    public static int BASE_STRESS = 1024;

    protected int configuredStress = BASE_STRESS;
    public BrassShaftBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide) {
            if (stress > configuredStress) {
                RotationPropagator.handleRemoved(level, getBlockPos(), this);
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

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        compound.putInt("configuredStress", configuredStress);
        super.write(compound, clientPacket);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        configuredStress = compound.getInt("configuredStress");
        super.read(compound, clientPacket);
    }
}
