package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.drill.DrillBlock;
import com.simibubi.create.content.kinetics.drill.DrillBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomDrillBlock extends DrillBlock {
    public CustomDrillBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends DrillBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.DRILL.get();
    }
}
