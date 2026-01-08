package fr.iglee42.createcasing.blocks.customs;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.contraptions.actors.harvester.HarvesterBlock;
import com.simibubi.create.content.contraptions.actors.harvester.HarvesterBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

public class CustomHarvesterBlock extends HarvesterBlock {
    public static final MapCodec<CustomHarvesterBlock> CODEC = simpleCodec(CustomHarvesterBlock::new);

    public CustomHarvesterBlock(Properties p_i48377_1_) {
        super(p_i48377_1_);
    }

    @Override
    public BlockEntityType<? extends HarvesterBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.HARVESTER.get();
    }

    @Override
    protected @NotNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }
}
