package fr.iglee42.createcasing.api.blocks;

import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlock;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ApiMixerBlock extends MechanicalMixerBlock {
    public ApiMixerBlock(Properties properties) {
        super(properties);
    }


    @Override
    public BlockEntityType<? extends MechanicalMixerBlockEntity> getBlockEntityType() {
        return ModBlockEntities.API_MIXER.get();
    }
}
