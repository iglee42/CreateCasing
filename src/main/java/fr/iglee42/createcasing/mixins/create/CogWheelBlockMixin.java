package fr.iglee42.createcasing.mixins.create;

import com.cyvack.create_crystal_clear.blocks.glass_encased_cogwheel.GlassEncasedCogwheel;
import com.cyvack.create_crystal_clear.blocks.glass_encased_shaft.GlassEncasedShaftBlock;
import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.utility.Iterate;
import fr.iglee42.createcasing.compatibility.createcrystalclear.CreateCrystalClearCompatibility;
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
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

import static com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock.AXIS;

@Mixin(value = CogWheelBlock.class,priority = 2000,remap = false)
public abstract class CogWheelBlockMixin {

    @Shadow public abstract boolean isLargeCog();

    /**
     * @author iglee42
     * @reason Adding new casing
     */
    @Inject(method = "use",at = @At("HEAD"),cancellable = true)
    public void use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray, CallbackInfoReturnable<InteractionResult> cir){
        if (!player.isShiftKeyDown() && player.mayBuild()) {
            ItemStack heldItem = player.getItemInHand(hand);
            List<EncasedCogwheelBlock> largesCogs = new ArrayList<>();
            ForgeRegistries.BLOCKS.getKeys().stream().filter(r->ForgeRegistries.BLOCKS.getValue(r) instanceof EncasedCogwheelBlock ecb && ecb.isLargeCog()).forEach(r->largesCogs.add((EncasedCogwheelBlock) ForgeRegistries.BLOCKS.getValue(r)));
            List<EncasedCogwheelBlock> smallCogs = new ArrayList<>();
            ForgeRegistries.BLOCKS.getKeys().stream().filter(r->ForgeRegistries.BLOCKS.getValue(r) instanceof EncasedCogwheelBlock ecb && ecb.isSmallCog()).forEach(r->smallCogs.add((EncasedCogwheelBlock) ForgeRegistries.BLOCKS.getValue(r)));
            if (isLargeCog()){
                largesCogs.stream().filter(c->c.getCasing().isIn(heldItem)).findFirst().ifPresent(encasedCog->{
                    if (world.isClientSide) {
                        cir.setReturnValue(InteractionResult.SUCCESS);
                    }

                    BlockState encasedState = encasedCog.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                    Direction[] var14 = Iterate.directionsInAxis(state.getValue(AXIS));

                    for (Direction d : var14) {
                        BlockState adjacentState = world.getBlockState(pos.relative(d));
                        if (adjacentState.getBlock() instanceof IRotate) {
                            IRotate def = (IRotate) adjacentState.getBlock();
                            if (def.hasShaftTowards(world, pos.relative(d), adjacentState, d.getOpposite())) {
                                encasedState = encasedState.cycle(d.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT : EncasedCogwheelBlock.BOTTOM_SHAFT);
                            }
                        }
                    }

                    KineticTileEntity.switchToBlockState(world, pos, encasedState);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                });
                List<GlassEncasedCogwheel> glassCogs = new ArrayList<>(){
                    {
                        if (CreateCrystalClearCompatibility.isModLoaded()) {
                            add(CreateCrystalClearCompatibility.COPPER_GLASS_ENCASED_LARGE_COGWHEEL.get());
                            add(CreateCrystalClearCompatibility.COPPER_CLEAR_GLASS_ENCASED_LARGE_COGWHEEL.get());
                        }
                    }
                };
                glassCogs.stream().filter(s->s.getCasing().isIn(heldItem))
                        .findFirst().ifPresent(s->{
                            if (world.isClientSide) {
                                cir.setReturnValue(InteractionResult.SUCCESS);
                            }

                            BlockState encasedState = s.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                            Direction[] var14 = Iterate.directionsInAxis(state.getValue(AXIS));

                            for (Direction d : var14) {
                                BlockState adjacentState = world.getBlockState(pos.relative(d));
                                if (adjacentState.getBlock() instanceof IRotate) {
                                    IRotate def = (IRotate) adjacentState.getBlock();
                                    if (def.hasShaftTowards(world, pos.relative(d), adjacentState, d.getOpposite())) {
                                        encasedState = encasedState.cycle(d.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT : EncasedCogwheelBlock.BOTTOM_SHAFT);
                                    }
                                }
                            }

                            KineticTileEntity.switchToBlockState(world, pos, encasedState);
                            cir.setReturnValue(InteractionResult.SUCCESS);
                        });
            } else {
                smallCogs.stream().filter(c->c.getCasing().isIn(heldItem)).findFirst().ifPresent(encasedCog->{
                    if (world.isClientSide) {
                        cir.setReturnValue(InteractionResult.SUCCESS);
                    }

                    BlockState encasedState = encasedCog.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                    Direction[] var14 = Iterate.directionsInAxis(state.getValue(AXIS));

                    for (Direction d : var14) {
                        BlockState adjacentState = world.getBlockState(pos.relative(d));
                        if (adjacentState.getBlock() instanceof IRotate) {
                            IRotate def = (IRotate) adjacentState.getBlock();
                            if (def.hasShaftTowards(world, pos.relative(d), adjacentState, d.getOpposite())) {
                                encasedState = encasedState.cycle(d.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT : EncasedCogwheelBlock.BOTTOM_SHAFT);
                            }
                        }
                    }

                    KineticTileEntity.switchToBlockState(world, pos, encasedState);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                });
                List<GlassEncasedCogwheel> glassCogs = new ArrayList<>(){
                    {
                        if (CreateCrystalClearCompatibility.isModLoaded()) {
                            add(CreateCrystalClearCompatibility.COPPER_GLASS_ENCASED_COGWHEEL.get());
                            add(CreateCrystalClearCompatibility.COPPER_CLEAR_GLASS_ENCASED_COGWHEEL.get());
                        }
                    }
                };
                glassCogs.stream().filter(s->s.getCasing().isIn(heldItem))
                        .findFirst().ifPresent(s->{
                            if (world.isClientSide) {
                                cir.setReturnValue(InteractionResult.SUCCESS);
                            }

                            BlockState encasedState = s.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                            Direction[] var14 = Iterate.directionsInAxis(state.getValue(AXIS));

                            for (Direction d : var14) {
                                BlockState adjacentState = world.getBlockState(pos.relative(d));
                                if (adjacentState.getBlock() instanceof IRotate) {
                                    IRotate def = (IRotate) adjacentState.getBlock();
                                    if (def.hasShaftTowards(world, pos.relative(d), adjacentState, d.getOpposite())) {
                                        encasedState = encasedState.cycle(d.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT : EncasedCogwheelBlock.BOTTOM_SHAFT);
                                    }
                                }
                            }

                            KineticTileEntity.switchToBlockState(world, pos, encasedState);
                            cir.setReturnValue(InteractionResult.SUCCESS);
                        });
            }
        }
    }

}
