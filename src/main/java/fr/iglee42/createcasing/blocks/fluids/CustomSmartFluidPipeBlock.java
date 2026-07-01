package fr.iglee42.createcasing.blocks.fluids;

import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlockEntity;
import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomSmartFluidPipeBlock extends SmartFluidPipeBlock {
    public CustomSmartFluidPipeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends SmartFluidPipeBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.SMART_FLUID_PIPE.get();
    }
}
