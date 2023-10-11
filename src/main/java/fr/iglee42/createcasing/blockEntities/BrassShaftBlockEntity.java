package fr.iglee42.createcasing.blockEntities;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import fr.iglee42.createcasing.blocks.customs.MetalShaftBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.ticks.TickPriority;

import java.util.ArrayList;
import java.util.List;

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
