package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.utility.Iterate;
import fr.iglee42.createcasing.compatibility.CreateCrystalClearCompatibility;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;

import static com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock.AXIS;

@Mixin(CogWheelBlock.class)
public abstract class CogWheelBlockMixin {

    @Shadow public abstract boolean isLargeCog();

    /**
     * @author iglee42
     * @reason Adding new casing
     */
    @Overwrite
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray){
        if (!player.isShiftKeyDown() && player.mayBuild()) {
            ItemStack heldItem = player.getItemInHand(hand);
            if (CreateCrystalClearCompatibility.checkCogs(isLargeCog(),heldItem,world,pos,state)) return InteractionResult.SUCCESS;
            List<EncasedCogwheelBlock> largesCogs = new ArrayList<>();
            ForgeRegistries.BLOCKS.getKeys().stream().filter(r->ForgeRegistries.BLOCKS.getValue(r) instanceof EncasedCogwheelBlock ecb && ecb.isLargeCog()).forEach(r->largesCogs.add((EncasedCogwheelBlock) ForgeRegistries.BLOCKS.getValue(r)));
            List<EncasedCogwheelBlock> smallCogs = new ArrayList<>();
            ForgeRegistries.BLOCKS.getKeys().stream().filter(r->ForgeRegistries.BLOCKS.getValue(r) instanceof EncasedCogwheelBlock ecb && ecb.isSmallCog()).forEach(r->smallCogs.add((EncasedCogwheelBlock) ForgeRegistries.BLOCKS.getValue(r)));
            EncasedCogwheelBlock[] encasedBlocks = this.isLargeCog() ?  largesCogs.toArray(new EncasedCogwheelBlock[]{}): smallCogs.toArray(new EncasedCogwheelBlock[]{});
            EncasedCogwheelBlock[] var9 = encasedBlocks;
            int var10 = encasedBlocks.length;

            for(int var11 = 0; var11 < var10; ++var11) {
                EncasedCogwheelBlock encasedCog = var9[var11];
                if (encasedCog.getCasing().isIn(heldItem)) {
                    if (world.isClientSide) {
                        return InteractionResult.SUCCESS;
                    }

                    BlockState encasedState = encasedCog.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                    Direction[] var14 = Iterate.directionsInAxis(state.getValue(AXIS));
                    int var15 = var14.length;

                    for(int var16 = 0; var16 < var15; ++var16) {
                        Direction d = var14[var16];
                        BlockState adjacentState = world.getBlockState(pos.relative(d));
                        if (adjacentState.getBlock() instanceof IRotate) {
                            IRotate def = (IRotate)adjacentState.getBlock();
                            if (def.hasShaftTowards(world, pos.relative(d), adjacentState, d.getOpposite())) {
                                encasedState = (BlockState)encasedState.cycle(d.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT : EncasedCogwheelBlock.BOTTOM_SHAFT);
                            }
                        }
                    }

                    KineticTileEntity.switchToBlockState(world, pos, encasedState);
                    return InteractionResult.SUCCESS;
                }
            }

        }
        return InteractionResult.PASS;
    }

}
