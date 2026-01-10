package fr.iglee42.createcasing.blocks.shafts;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class WoodenShaftBlock extends CustomShaftBlock {


    public WoodenShaftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.WOODEN_SHAFT.get();
    }

}
