package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.deployer.DeployerBlock;
import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomDeployerBlock extends DeployerBlock {
    public CustomDeployerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends DeployerBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.DEPLOYER.get();
    }
}
