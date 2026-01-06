package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.fan.EncasedFanBlock;
import com.simibubi.create.content.kinetics.fan.EncasedFanBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomEncasedFanBlock extends EncasedFanBlock {
    public CustomEncasedFanBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends EncasedFanBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.ENCASED_FAN.get();
    }
}
