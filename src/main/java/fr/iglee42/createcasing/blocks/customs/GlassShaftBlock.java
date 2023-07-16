package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class GlassShaftBlock extends ShaftBlock {
    public GlassShaftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return ModBlockEntities.GLASS_SHAFT.get();
    }
}
