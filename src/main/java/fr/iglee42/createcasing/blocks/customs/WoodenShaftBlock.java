package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import fr.iglee42.createcasing.blockEntities.WoodenShaftBlockEntity;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class WoodenShaftBlock extends ShaftBlock {
    public WoodenShaftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return ModBlockEntities.WOODEN_SHAFT.get();
    }
}
