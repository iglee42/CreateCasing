package fr.iglee42.createcasing.blockEntities;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import fr.iglee42.createcasing.registries.EncasedSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MetalShaftBlockEntity extends BracketedKineticBlockEntity {
    public MetalShaftBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        super.tick();


        if (EncasedBlocks.MLDEG_SHAFT.has(getBlockState())){
            if (getSpeed() == 256 || getSpeed() == -256){
                if (lazyTickCounter % 60 == 0) EncasedSounds.MLDEG.playAt(level,worldPosition,0.25f,0.5f,false);
            }
        }
    }

}
