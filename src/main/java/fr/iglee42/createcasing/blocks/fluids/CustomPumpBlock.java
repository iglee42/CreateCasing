package fr.iglee42.createcasing.blocks.fluids;

import com.simibubi.create.content.fluids.pipes.GlassFluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.StraightPipeBlockEntity;
import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomPumpBlock extends PumpBlock {
    public CustomPumpBlock(Properties properties) {
        super(properties);
    }


    @Override
    public BlockEntityType<? extends PumpBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.MECHANICAL_PUMP.get();
    }
}
