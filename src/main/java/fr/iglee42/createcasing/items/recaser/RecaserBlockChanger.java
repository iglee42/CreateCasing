package fr.iglee42.createcasing.items.recaser;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import fr.iglee42.createcasing.sets.SetBase;
import fr.iglee42.createcasing.utils.QuadFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock.AXIS;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.*;

public class RecaserBlockChanger {

    private InteractionResult placementResult = InteractionResult.PASS;

    public <T extends SetBase<?,?>, B extends BlockEntity> void replace(Level level, BlockPos pos, T oldSet, T newSet, Function<T, Block> getter, Property<?>... properties) {
        replace(level, pos, oldSet, newSet, getter, (lvl,blockPos,be,store)->{}, (lvl,blockPos,be,store)->{}, properties);
    }

    public <T extends SetBase<?,?>, B extends BlockEntity> void replace(Level level, BlockPos pos, T oldSet, T newSet, Function<T, Block> getter, PostAction<B> postAction, Property<?>... properties) {
        replace(level, pos, oldSet, newSet, getter, (lvl,blockPos,be,store)->{}, postAction, properties);
    }

    public <T extends SetBase<?, ?>, B extends BlockEntity> void replace(Level level, BlockPos pos, T oldSet, T newSet, Function<T, Block> getter, PreAction<B> preAction, PostAction<B> postAction, Property<?>... properties) {
        if (placementResult.consumesAction())
            return;
        BlockState oldState = level.getBlockState(pos);
        if (!oldSet.isInSet(oldState.getBlock()))
            return;

        if (!oldState.is(getter.apply(oldSet)))
            return;

        Block block = getter.apply(newSet);

        if (block == null)
            return;

        BlockState newState = block.defaultBlockState();
        ActionStore values = new ActionStore();

        preAction.execute(level, pos, (B) level.getBlockEntity(pos), values);

        for (Property<?> prop : properties) {
            if (!oldState.hasProperty(prop)) continue;
            if (!newState.hasProperty(prop)) continue;
            newState = copyProperty(oldState, newState, prop);
        }

        placementResult = changeBlock(level, pos, oldState, newState);

        postAction.execute(level, pos, (B) level.getBlockEntity(pos), values);
    }

    private static <T extends Comparable<T>> BlockState copyProperty(
            BlockState oldState,
            BlockState newState,
            Property<T> property
    ) {
        return newState.setValue(property, oldState.getValue(property));
    }

    public InteractionResult getPlacementResult() {
        return placementResult;
    }

    public static InteractionResult changeBlock(Level level, BlockPos blockPos, BlockState oldState, BlockState newState) {
        if (oldState.hasProperty(WATERLOGGED) && newState.hasProperty(WATERLOGGED))
            newState = newState.setValue(WATERLOGGED, oldState.getValue(WATERLOGGED));
        KineticBlockEntity.switchToBlockState(level, blockPos, Block.updateFromNeighbourShapes(newState, level, blockPos));
        level.levelEvent(2001, blockPos, Block.getId(newState));
        return InteractionResult.SUCCESS;
    }

    public final class ActionStore {

        private final Map<Key<?>, Object> values = new HashMap<>();

        public <T> void set(Key<T> key, T value) {
            values.put(key, value);
        }

        @SuppressWarnings("unchecked")
        public <T> T get(Key<T> key) {
            return (T) values.get(key);
        }

        public <T> boolean contains(Key<T> key) {
            return values.containsKey(key);
        }

        public static final class Key<T> {
        }
    }

    @FunctionalInterface
    public interface PreAction<T extends BlockEntity> {

        void execute(
                Level level,
                BlockPos pos,
                T oldBlockEntity,
                ActionStore store
        );
    }

    @FunctionalInterface
    public interface PostAction<T extends BlockEntity> {

        void execute(
                Level level,
                BlockPos pos,
                T newBlockEntity,
                ActionStore store
        );
    }


}
