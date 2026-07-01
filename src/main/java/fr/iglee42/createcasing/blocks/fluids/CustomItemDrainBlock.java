package fr.iglee42.createcasing.blocks.fluids;

import com.simibubi.create.content.fluids.drain.ItemDrainBlock;
import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomItemDrainBlock extends ItemDrainBlock {
    public CustomItemDrainBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends ItemDrainBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.ITEM_DRAIN.get();
    }
}
