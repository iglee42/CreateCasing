package fr.iglee42.createcasing.changeAcces;

import com.simibubi.create.content.contraptions.relays.elementary.SimpleKineticTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


/*
This class allow to make custom cogwheel
 */

public class PublicSimpleKinecticTileEntity extends SimpleKineticTileEntity {
    public PublicSimpleKinecticTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }


}
