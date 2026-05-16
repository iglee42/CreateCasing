package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.kinetics.chainDrive.ChainDriveBlock;
import fr.iglee42.createcasing.blocks.customs.CustomChainDriveBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ChainDriveBlock.class, remap = false)
public class ChainDriveBlockMixin {

    @Inject(method = "updateShape", at = @At("HEAD"), cancellable = true)
    private void encased$dontConnectToInvalidCustom(
            BlockState stateIn,
            Direction face,
            BlockState neighbour,
            LevelAccessor worldIn,
            BlockPos currentPos,
            BlockPos facingPos,
            CallbackInfoReturnable<BlockState> cir
    ) {

        Block self = stateIn.getBlock();
        Block other = neighbour.getBlock();

        boolean selfCustom = self instanceof CustomChainDriveBlock;
        boolean otherCustom = other instanceof CustomChainDriveBlock;

        /*
         * Vanilla <-> Custom
         *
         * Disconnect the two
         */
        if (selfCustom != otherCustom) {
            cir.setReturnValue(createCasing$disconnect(stateIn, face));
            return;
        }

        /*
         * Custom A <-> Custom B
         * Different Types
         */
        if (selfCustom) {
            CustomChainDriveBlock selfDrive = (CustomChainDriveBlock) self;
            CustomChainDriveBlock otherDrive = (CustomChainDriveBlock) other;

            if (!selfDrive.getType().equals(otherDrive.getType())) {
                cir.setReturnValue(createCasing$disconnect(stateIn, face));
            }
        }
    }

    @Inject(method = "areBlocksConnected", at = @At("HEAD"), cancellable = true)
    private static void encased$dontConnectCustom(
            BlockState state,
            BlockState other,
            Direction facing,
            CallbackInfoReturnable<Boolean> cir
    ) {

        boolean isCustom = state.getBlock() instanceof CustomChainDriveBlock;
        boolean isOtherCustom = other.getBlock() instanceof CustomChainDriveBlock;

        /*
         * Vanilla <-> Custom
         */
        if (isCustom != isOtherCustom) {
            cir.setReturnValue(false);
            return;
        }

        /*
         * Custom A <-> Custom B
         */
        if (isCustom) {
            CustomChainDriveBlock chainDrive = (CustomChainDriveBlock) state.getBlock();
            CustomChainDriveBlock otherDrive = (CustomChainDriveBlock) other.getBlock();

            if (!chainDrive.getType().equals(otherDrive.getType())) {
                cir.setReturnValue(false);
            }
        }
    }

    @Unique
    private BlockState createCasing$disconnect(BlockState stateIn, Direction face) {

        ChainDriveBlock.Part part = stateIn.getValue(ChainDriveBlock.PART);
        Direction.Axis axis = stateIn.getValue(ChainDriveBlock.AXIS);

        boolean connectionAlongFirst =
                stateIn.getValue(ChainDriveBlock.CONNECTED_ALONG_FIRST_COORDINATE);

        Direction.Axis faceAxis = face.getAxis();

        boolean facingAlongFirst =
                axis == Direction.Axis.X
                        ? faceAxis.isVertical()
                        : faceAxis == Direction.Axis.X;

        boolean positive =
                face.getAxisDirection() == Direction.AxisDirection.POSITIVE;

        /*
         * Same logic from vanilla Create
         */

        if (axis == faceAxis) {
            return stateIn;
        }

        if (facingAlongFirst != connectionAlongFirst || part == ChainDriveBlock.Part.NONE) {
            return stateIn;
        }

        if (part == ChainDriveBlock.Part.MIDDLE) {
            return stateIn.setValue(
                    ChainDriveBlock.PART,
                    positive
                            ? ChainDriveBlock.Part.END
                            : ChainDriveBlock.Part.START
            );
        }

        if ((part == ChainDriveBlock.Part.START) == positive) {
            return stateIn.setValue(
                    ChainDriveBlock.PART,
                    ChainDriveBlock.Part.NONE
            );
        }

        return stateIn;
    }
}
