package fr.iglee42.createcasing.utils;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.registries.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

public enum WoodBlocks {
    OAK(Items.OAK_PLANKS,ModBlocks.OAK_SHAFT,ModBlocks.OAK_COGWHEEL,ModBlocks.OAK_LARGE_COGWHEEL),
    SPRUCE(Items.SPRUCE_PLANKS,ModBlocks.SPRUCE_SHAFT, AllBlocks.COGWHEEL,AllBlocks.LARGE_COGWHEEL),
    BIRCH(Items.BIRCH_PLANKS,ModBlocks.BIRCH_SHAFT,ModBlocks.BIRCH_COGWHEEL,ModBlocks.BIRCH_LARGE_COGWHEEL),
    ACACIA(Items.ACACIA_PLANKS,ModBlocks.ACACIA_SHAFT,ModBlocks.ACACIA_COGWHEEL,ModBlocks.ACACIA_LARGE_COGWHEEL),
    DARK_OAK(Items.DARK_OAK_PLANKS,ModBlocks.DARK_OAK_SHAFT,ModBlocks.DARK_OAK_COGWHEEL,ModBlocks.DARK_OAK_LARGE_COGWHEEL),
    JUNGLE(Items.JUNGLE_PLANKS,ModBlocks.JUNGLE_SHAFT,ModBlocks.JUNGLE_COGWHEEL,ModBlocks.JUNGLE_LARGE_COGWHEEL),
    MANGROVE(Items.MANGROVE_PLANKS,ModBlocks.MANGROVE_SHAFT,ModBlocks.MANGROVE_COGWHEEL,ModBlocks.MANGROVE_LARGE_COGWHEEL),
    CHERRY(Items.CHERRY_PLANKS,ModBlocks.CHERRY_SHAFT,ModBlocks.CHERRY_COGWHEEL,ModBlocks.CHERRY_LARGE_COGWHEEL),
    BAMBOO(Items.BAMBOO_PLANKS,ModBlocks.BAMBOO_SHAFT,ModBlocks.BAMBOO_COGWHEEL,ModBlocks.BAMBOO_LARGE_COGWHEEL),
    CRIMSON(Items.CRIMSON_PLANKS,ModBlocks.CRIMSON_SHAFT,ModBlocks.CRIMSON_COGWHEEL,ModBlocks.CRIMSON_LARGE_COGWHEEL),
    WARPED(Items.WARPED_PLANKS,ModBlocks.WARPED_SHAFT,ModBlocks.WARPED_COGWHEEL,ModBlocks.WARPED_LARGE_COGWHEEL),
    ANDESITE(AllItems.ANDESITE_ALLOY.get(), AllBlocks.SHAFT,null,null),
    BRASS(AllItems.BRASS_INGOT.get(), ModBlocks.BRASS_SHAFT,null,null),
    GLASS(Items.GLASS,ModBlocks.GLASS_SHAFT,null,null),
    MLDEG(Items.BLACKSTONE,ModBlocks.MLDEG_SHAFT,null,null),
    ;


    private final Item item;
    private final BlockEntry<? extends Block> shaft;
    private final BlockEntry<? extends Block> cogwheel;
    private final BlockEntry<? extends Block> largeCogwheel;

    WoodBlocks(Item item, BlockEntry<? extends Block> shaft, BlockEntry<? extends Block> cogwheel, BlockEntry<? extends Block> largeCogwheel) {
        this.item = item;
        this.shaft = shaft;
        this.cogwheel = cogwheel;
        this.largeCogwheel = largeCogwheel;
    }

    public static WoodBlocks getBlockByItem(Item item){
        return Arrays.stream(values()).filter(e->e.item.equals(item)).findFirst().orElse(null);
    }


    public static boolean hasBlocksForItem(Item item){
        return getBlockByItem(item) != null;
    }

    public Item getItem() {
        return item;
    }

    public BlockEntry<? extends Block> getShaft() {
        return shaft;
    }

    public BlockEntry<? extends Block> getCogwheel() {
        return cogwheel;
    }

    public BlockEntry<? extends Block> getLargeCogwheel() {
        return largeCogwheel;
    }

    public boolean hasShaft(){
        return getShaft() != null;
    }

    public boolean hasCogwheel(){
        return getCogwheel() != null;
    }

    public boolean hasLargeCogwheel(){
        return getLargeCogwheel() != null;
    }

    public static boolean isShaft(BlockState state){
        return Arrays.stream(values()).anyMatch(b->b.hasShaft() && b.getShaft().has(state));
    }

    public static boolean isCogwheel(BlockState state){
        return Arrays.stream(values()).anyMatch(b->b.hasCogwheel() && b.getCogwheel().has(state));
    }

    public static boolean isLargeCogwheel(BlockState state){
        return Arrays.stream(values()).anyMatch(b->b.hasLargeCogwheel() && b.getLargeCogwheel().has(state));
    }

    public boolean isInSet(BlockState state){
        return (hasShaft() && shaft.has(state)) || (hasCogwheel() && cogwheel.has(state)) || (hasLargeCogwheel() && largeCogwheel.has(state));
    }
}
