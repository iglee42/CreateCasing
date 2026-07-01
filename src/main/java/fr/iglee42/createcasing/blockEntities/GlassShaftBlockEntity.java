package fr.iglee42.createcasing.blockEntities;

import fr.iglee42.createcasing.blocks.shafts.EncasedCustomShaftBlock;
import fr.iglee42.createcasing.config.EncasedConfigs;
import fr.iglee42.createcasing.transmissions.TransmissionSets;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class GlassShaftBlockEntity extends CustomShaftBlockEntity {
    public GlassShaftBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        super.tick();

        if (EncasedConfigs.common().kinetics.shouldGlassShaftBreak.get() && TransmissionSets.GLASS.getShaft() != null) {
            if (isOverStressed()) {
                if (source != null){
                    if (!(getLevel().getBlockState(source).is(TransmissionSets.GLASS.getShaft()) || (getLevel().getBlockState(source).getBlock() instanceof EncasedCustomShaftBlock sh && sh.getShaft().get().defaultBlockState().is(TransmissionSets.GLASS.getShaft()))))
                        getLevel().destroyBlock(worldPosition,false);
                } else {
                    getLevel().destroyBlock(worldPosition, false);
                }
            }
        }

    }


}
