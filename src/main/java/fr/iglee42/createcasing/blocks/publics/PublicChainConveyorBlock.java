package fr.iglee42.createcasing.blocks.publics;

import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorBlock;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorBlockEntity;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class PublicChainConveyorBlock extends ChainConveyorBlock {
    public PublicChainConveyorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends ChainConveyorBlockEntity> getBlockEntityType() {
        return ModBlockEntities.CHAIN_CONVEYOR.get();
    }
}
