package fr.iglee42.createcasing.blocks.fluids;

import com.simibubi.create.content.fluids.spout.SpoutBlock;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomSpoutBlock extends SpoutBlock {
    public CustomSpoutBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends SpoutBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.SPOUT.get();
    }
}
