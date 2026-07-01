package fr.iglee42.createcasing.blockEntities;

import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import fr.iglee42.createcasing.blocks.shafts.EncasedCustomShaftBlock;
import fr.iglee42.createcasing.config.EncasedConfigs;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import fr.iglee42.createcasing.transmissions.TransmissionSets;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CustomEncasedShaftBlockEntity extends SimpleKineticBlockEntity {
    public CustomEncasedShaftBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        super.tick();

        if (TransmissionSets.GLASS.getShaft() != null && ((EncasedCustomShaftBlock) getBlockState().getBlock()).getShaft().get().defaultBlockState().is(TransmissionSets.GLASS.getShaft())) {
            if (EncasedConfigs.common().kinetics.shouldGlassShaftBreak.get()) {
                if (isOverStressed()) {
                    if (source != null) {
                        if (!(getLevel().getBlockState(source).is(TransmissionSets.GLASS.getShaft())) || (getLevel().getBlockState(source).getBlock() instanceof EncasedCustomShaftBlock sh &&sh.getShaft().get().defaultBlockState().is(TransmissionSets.GLASS.getShaft()))) {
                            getLevel().destroyBlock(worldPosition, false);
                        }
                    } else getLevel().destroyBlock(worldPosition, false);
                }
            }
        }

        if (EncasedBlocks.isWoodenShaftHasState(((EncasedCustomShaftBlock) getBlockState().getBlock()).getShaft().get().defaultBlockState())) {
            if (EncasedConfigs.common().kinetics.shouldWoodenShaftBreak.get()) {
                if ((getSpeed() > EncasedConfigs.common().kinetics.maxSpeedWoodenShaft.get() || getSpeed() < -EncasedConfigs.common().kinetics.maxSpeedWoodenShaft.get()))
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
