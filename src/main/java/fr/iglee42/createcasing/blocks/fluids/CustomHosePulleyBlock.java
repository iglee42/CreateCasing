package fr.iglee42.createcasing.blocks.fluids;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlock;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomHosePulleyBlock extends HosePulleyBlock {
    public CustomHosePulleyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends HosePulleyBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.HOSE_PULLEY.get();
    }
}
