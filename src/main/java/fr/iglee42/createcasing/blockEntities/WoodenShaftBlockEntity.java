package fr.iglee42.createcasing.blockEntities;

import fr.iglee42.createcasing.blocks.shafts.EncasedCustomShaftBlock;
import fr.iglee42.createcasing.config.EncasedConfigs;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class WoodenShaftBlockEntity extends CustomShaftBlockEntity {
    public WoodenShaftBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        if (EncasedConfigs.common().kinetics.shouldWoodenShaftBreak.get()) {
            if ((getSpeed() > EncasedConfigs.common().kinetics.maxSpeedWoodenShaft.get() || getSpeed() < -EncasedConfigs.common().kinetics.maxSpeedWoodenShaft.get())) {
                if (source != null) {
                    if (!(EncasedBlocks.isWoodenShaftHasState(getLevel().getBlockState(source))) || (getLevel().getBlockState(source).getBlock() instanceof EncasedCustomShaftBlock sh && EncasedBlocks.isWoodenShaftHasState(sh.getShaft().get().defaultBlockState())))
                        getLevel().destroyBlock(worldPosition, false);
                } else {
                    getLevel().destroyBlock(worldPosition, false);
                }
            }
        }

    }


}
