package fr.iglee42.createcasing.compatibility;

import com.cyvack.create_crystal_clear.Create_Crystal_Clear;
import com.cyvack.create_crystal_clear.blocks.ModBlocks;
import com.cyvack.create_crystal_clear.blocks.glass_encased_cogwheel.GlassEncasedCogwheel;
import com.cyvack.create_crystal_clear.blocks.glass_encased_shaft.GlassEncasedShaftBlock;
import com.cyvack.create_crystal_clear.compat.blocks.AlloyedCompatBlocks;
import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock.AXIS;

public class CreateCrystalClearCompatibility {
    private static ArrayList<GlassEncasedShaftBlock> encasedShaft = new ArrayList<>() {
        {
            add(ModBlocks.ANDESITE_GLASS_ENCASED_SHAFT.get());
            add(ModBlocks.ANDESITE_CLEAR_GLASS_ENCASED_SHAFT.get());
            add(ModBlocks.BRASS_GLASS_ENCASED_SHAFT.get());
            add(ModBlocks.BRASS_CLEAR_GLASS_ENCASED_SHAFT.get());
            add(ModBlocks.TRAIN_GLASS_ENCASED_SHAFT.get());
            add(ModBlocks.TRAIN_CLEAR_GLASS_ENCASED_SHAFT.get());
            if (Create_Crystal_Clear.isAlloyedLoaded) {add(AlloyedCompatBlocks.STEEL_GLASS_ENCASED_SHAFT.get());}
        }
    };

    private static GlassEncasedCogwheel[] largeCogs = new GlassEncasedCogwheel[] {
            ModBlocks.ANDESITE_GLASS_ENCASED_LARGE_COGWHEEL.get(),
            ModBlocks.BRASS_GLASS_ENCASED_LARGE_COGWHEEL.get(),
            ModBlocks.TRAIN_GLASS_ENCASED_LARGE_COGWHEEL.get(),
            ModBlocks.ANDESITE_CLEAR_GLASS_ENCASED_LARGE_COGWHEEL.get(),
            ModBlocks.BRASS_CLEAR_GLASS_ENCASED_LARGE_COGWHEEL.get(),
            ModBlocks.TRAIN_CLEAR_GLASS_ENCASED_LARGE_COGWHEEL.get()};

    private static GlassEncasedCogwheel[] smallCogs =  new GlassEncasedCogwheel[] {
            ModBlocks.ANDESITE_GLASS_ENCASED_COGWHEEL.get(),
            ModBlocks.BRASS_GLASS_ENCASED_COGWHEEL.get(),
            ModBlocks.TRAIN_GLASS_ENCASED_COGWHEEL.get(),
            ModBlocks.ANDESITE_CLEAR_GLASS_ENCASED_COGWHEEL.get(),
            ModBlocks.BRASS_CLEAR_GLASS_ENCASED_COGWHEEL.get(),
            ModBlocks.TRAIN_CLEAR_GLASS_ENCASED_COGWHEEL.get()};

    public static boolean isModLoaded(){
        return ModList.get().isLoaded("create_crystal_clear");
    }

    public static boolean checkShaft(ItemStack heldItem, Level world, BlockPos pos, BlockState state) {
        if (!isModLoaded()) return false;
        if (encasedShaft.stream().anyMatch(s->s.getCasing().isIn(heldItem))){
            GlassEncasedShaftBlock s = encasedShaft.stream().filter(sa->sa.getCasing().isIn(heldItem)).findFirst().get();
            if (world.isClientSide) {
                    return false;
            }

            KineticTileEntity.switchToBlockState(world, pos, s.defaultBlockState().setValue(AXIS, state.getValue(AXIS)));
            return true;
        }
        return false;
    }


    public static boolean checkCogs(boolean isLarge, ItemStack heldItem, Level world, BlockPos pos, BlockState state){
        if (!isModLoaded()) return false;
        List<GlassEncasedCogwheel> cogs = isLarge ? Arrays.stream(largeCogs).toList() : Arrays.stream(smallCogs).toList();
        if (cogs.stream().anyMatch(s->s.getCasing().isIn(heldItem))){
            GlassEncasedCogwheel encasedCog = cogs.stream().filter(sa->sa.getCasing().isIn(heldItem)).findFirst().get();
            if (world.isClientSide) {
                return false;
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
                        encasedState = encasedState.cycle(d.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT : EncasedCogwheelBlock.BOTTOM_SHAFT);
                    }
                }
            }

            KineticTileEntity.switchToBlockState(world, pos, encasedState);
            return true;
        }
        return false;
    }
}
