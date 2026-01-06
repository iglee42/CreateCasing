package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlock;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomMixerBlock extends MechanicalMixerBlock{

    public CustomMixerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends MechanicalMixerBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.MIXER.get();
    }

}
