package fr.iglee42.createcasing.blocks.fluids;

import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlock;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomFluidValveBlock extends FluidValveBlock {
    public CustomFluidValveBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends FluidValveBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.FLUID_VALVE.get();
    }
}
