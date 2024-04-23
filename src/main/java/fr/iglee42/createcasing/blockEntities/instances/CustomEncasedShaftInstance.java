package fr.iglee42.createcasing.blockEntities.instances;

import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.ShaftInstance;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import fr.iglee42.createcasing.blocks.customs.EncasedCustomShaftBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CustomEncasedShaftInstance extends ShaftInstance<KineticBlockEntity> {
    public CustomEncasedShaftInstance(MaterialManager materialManager, KineticBlockEntity blockEntity) {
        super(materialManager, blockEntity);
    }

    @Override
    protected BlockState getRenderedBlockState() {
        return ((EncasedCustomShaftBlock)blockEntity.getBlockState().getBlock()).getShaft().get().defaultBlockState().setValue(ShaftBlock.AXIS,getRotationAxis());
    }
}
