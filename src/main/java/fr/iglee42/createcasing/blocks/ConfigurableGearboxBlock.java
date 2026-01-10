package fr.iglee42.createcasing.blocks;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import fr.iglee42.createcasing.config.ModConfigs;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class ConfigurableGearboxBlock extends KineticBlock implements IBE<GearboxBlockEntity> {

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

    public ConfigurableGearboxBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(NORTH,false)
                .setValue(EAST,false)
                .setValue(SOUTH,false)
                .setValue(WEST,false)
                .setValue(UP,false)
                .setValue(DOWN,false)
        );
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.Y;
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        Direction face = context.getClickedFace();
        if (!state.getValue(getPropertyByDirection(face))) {
            if (ModConfigs.common().kinetics.configurableGearboxRequiresShaft.get()) return super.onSneakWrenched(state, context);
            state = state.setValue(getPropertyByDirection(face), true);
            if (ModConfigs.common().kinetics.configurableGearboxChangeTwoFaces.get())
                state = state.setValue(getPropertyByDirection(face.getOpposite()), true);
        } else {
            state = state.setValue(getPropertyByDirection(face),false);
            if (ModConfigs.common().kinetics.configurableGearboxChangeTwoFaces.get())state = state.setValue(getPropertyByDirection(face.getOpposite()),false);
            if (ModConfigs.common().kinetics.configurableGearboxRequiresShaft.get()){
                if (context.getPlayer() != null && !context.getPlayer().isCreative())
                    context.getPlayer().addItem(AllBlocks.SHAFT.asStack());
            }
        }

        return super.onWrenched(state, context);
    }

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        if (context.getLevel().isClientSide) return InteractionResult.sidedSuccess(true);
        Direction face = context.getClickedFace().getOpposite();
        if (!state.getValue(getPropertyByDirection(face))) {
            if (ModConfigs.common().kinetics.configurableGearboxRequiresShaft.get()) return super.onSneakWrenched(state, context);
            state = state.setValue(getPropertyByDirection(face), true);
            if (ModConfigs.common().kinetics.configurableGearboxChangeTwoFaces.get())
                state = state.setValue(getPropertyByDirection(face.getOpposite()), true);
        } else {
            state = state.setValue(getPropertyByDirection(face),false);
            if (ModConfigs.common().kinetics.configurableGearboxChangeTwoFaces.get())state = state.setValue(getPropertyByDirection(face.getOpposite()),false);
            if (ModConfigs.common().kinetics.configurableGearboxRequiresShaft.get()){
                if (context.getPlayer() != null && !context.getPlayer().isCreative())
                    context.getPlayer().addItem(AllBlocks.SHAFT.asStack());
            }
        }
        KineticBlockEntity.switchToBlockState(context.getLevel(), context.getClickedPos(), state);
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (level.isClientSide) return InteractionResult.sidedSuccess(true);
        if (player.getItemInHand(hand).isEmpty()) return InteractionResult.PASS;
        ItemStack stack = player.getItemInHand(hand);
        Direction face = result.getDirection();
        if (!stack.is(AllBlocks.SHAFT.asItem())) return InteractionResult.PASS;
        if (player.isCrouching()) face = face.getOpposite();
        if (!state.getValue(getPropertyByDirection(face))) {
            if (!ModConfigs.common().kinetics.configurableGearboxRequiresShaft.get()) return InteractionResult.PASS;
            state = state.setValue(getPropertyByDirection(face), true);
            if (ModConfigs.common().kinetics.configurableGearboxChangeTwoFaces.get())
                state = state.setValue(getPropertyByDirection(face.getOpposite()), true);
            KineticBlockEntity.switchToBlockState(level, pos, state);
            if (!player.isCreative())
                stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return state.getValue(getPropertyByDirection(face));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
        super.createBlockStateDefinition(builder);
    }

    @Override
    protected boolean areStatesKineticallyEquivalent(BlockState oldState, BlockState newState) {
        return super.areStatesKineticallyEquivalent(oldState, newState) && oldState.getValues().equals(newState.getValues());
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> stacks = super.getDrops(state, builder);
        if (!ModConfigs.common().kinetics.configurableGearboxRequiresShaft.get()) return stacks;
        int shaftCount = 0;
        if (ModConfigs.common().kinetics.configurableGearboxChangeTwoFaces.get()){
            if (state.getValue(UP)) shaftCount++;
            if (state.getValue(NORTH)) shaftCount++;
            if (state.getValue(WEST)) shaftCount++;
        } else {
            if (state.getValue(UP)) shaftCount++;
            if (state.getValue(DOWN)) shaftCount++;
            if (state.getValue(NORTH)) shaftCount++;
            if (state.getValue(SOUTH)) shaftCount++;
            if (state.getValue(WEST)) shaftCount++;
            if (state.getValue(EAST)) shaftCount++;
        }

        if (shaftCount > 0) stacks.add(AllBlocks.SHAFT.asStack(shaftCount));
        return stacks;
    }

    public static BooleanProperty getPropertyByDirection(Direction direction){
        return switch (direction){
            case DOWN -> DOWN;
            case UP -> UP;
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            case EAST -> EAST;
        };
    }

    @Override
    public Class<GearboxBlockEntity> getBlockEntityClass() {
        return GearboxBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends GearboxBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.CONFIGURABLE_GEARBOX.get();
    }
}