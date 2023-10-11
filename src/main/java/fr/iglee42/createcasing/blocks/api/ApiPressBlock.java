package fr.iglee42.createcasing.blocks.api;

import com.simibubi.create.content.kinetics.press.MechanicalPressBlock;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ApiPressBlock extends MechanicalPressBlock {
    public ApiPressBlock(Properties properties) {
        super(properties);
    }


    @Override
    public BlockEntityType<? extends MechanicalPressBlockEntity> getBlockEntityType() {
        return ModBlockEntities.API_PRESS.get();
    }
}
