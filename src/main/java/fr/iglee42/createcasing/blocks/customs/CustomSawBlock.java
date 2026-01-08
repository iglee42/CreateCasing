package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.saw.SawBlock;
import com.simibubi.create.content.kinetics.saw.SawBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;

public class CustomSawBlock extends SawBlock {
    public CustomSawBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends SawBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.SAW.get();
    }
}
