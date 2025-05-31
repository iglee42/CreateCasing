package fr.iglee42.createcasing.api.blocks;

import com.simibubi.create.content.kinetics.press.MechanicalPressBlock;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ApiPressBlock extends MechanicalPressBlock {
    public ApiPressBlock(Properties properties) {
        super(properties);
    }


    @Override
    public BlockEntityType<? extends MechanicalPressBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.API_PRESS.get();
    }
}
