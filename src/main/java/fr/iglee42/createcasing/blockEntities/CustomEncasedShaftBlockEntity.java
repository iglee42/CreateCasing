package fr.iglee42.createcasing.blockEntities;

import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import fr.iglee42.createcasing.blocks.customs.CustomEncasedShaft;
import fr.iglee42.createcasing.config.ModConfigs;
import fr.iglee42.createcasing.registries.ModBlocks;
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

        if (ModBlocks.GLASS_SHAFT.has(((CustomEncasedShaft)getBlockState().getBlock()).getShaft().get().defaultBlockState())) {
            if (ModConfigs.common().kinetics.shouldGlassShaftBreak.get()) {
                if (isOverStressed() && !(ModBlocks.GLASS_SHAFT.has(getLevel().getBlockState(source))) || (getLevel().getBlockState(source).getBlock() instanceof CustomEncasedShaft sh && ModBlocks.GLASS_SHAFT.has(sh.getShaft().get().defaultBlockState()))) {
                    getLevel().destroyBlock(worldPosition, false);
                }
            }
        }

        if (ModBlocks.isWoodenShaftHasState(((CustomEncasedShaft)getBlockState().getBlock()).getShaft().get().defaultBlockState())){
            if (ModConfigs.common().kinetics.shouldWoodenShaftBreak.get()) {
                if ((getSpeed() > ModConfigs.common().kinetics.maxSpeedWoodenShaft.get() || getSpeed() < -ModConfigs.common().kinetics.maxSpeedWoodenShaft.get()) &&
                        !(ModBlocks.isWoodenShaftHasState(getLevel().getBlockState(source)) ||
                                (getLevel().getBlockState(source).getBlock() instanceof CustomEncasedShaft sh && ModBlocks.isWoodenShaftHasState(sh.getShaft().get().defaultBlockState())))) {
                    getLevel().destroyBlock(worldPosition, false);
                }
            }
        }
    }
}
