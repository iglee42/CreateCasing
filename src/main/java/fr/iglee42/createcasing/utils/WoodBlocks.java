package fr.iglee42.createcasing.utils;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

public enum WoodBlocks {
    OAK(Items.OAK_PLANKS, EncasedBlocks.OAK_SHAFT, EncasedBlocks.OAK_COGWHEEL, EncasedBlocks.OAK_LARGE_COGWHEEL),
    SPRUCE(Items.SPRUCE_PLANKS, EncasedBlocks.SPRUCE_SHAFT, AllBlocks.COGWHEEL,AllBlocks.LARGE_COGWHEEL),
    BIRCH(Items.BIRCH_PLANKS, EncasedBlocks.BIRCH_SHAFT, EncasedBlocks.BIRCH_COGWHEEL, EncasedBlocks.BIRCH_LARGE_COGWHEEL),
    ACACIA(Items.ACACIA_PLANKS, EncasedBlocks.ACACIA_SHAFT, EncasedBlocks.ACACIA_COGWHEEL, EncasedBlocks.ACACIA_LARGE_COGWHEEL),
    DARK_OAK(Items.DARK_OAK_PLANKS, EncasedBlocks.DARK_OAK_SHAFT, EncasedBlocks.DARK_OAK_COGWHEEL, EncasedBlocks.DARK_OAK_LARGE_COGWHEEL),
    JUNGLE(Items.JUNGLE_PLANKS, EncasedBlocks.JUNGLE_SHAFT, EncasedBlocks.JUNGLE_COGWHEEL, EncasedBlocks.JUNGLE_LARGE_COGWHEEL),
    MANGROVE(Items.MANGROVE_PLANKS, EncasedBlocks.MANGROVE_SHAFT, EncasedBlocks.MANGROVE_COGWHEEL, EncasedBlocks.MANGROVE_LARGE_COGWHEEL),
    CHERRY(Items.CHERRY_PLANKS, EncasedBlocks.CHERRY_SHAFT, EncasedBlocks.CHERRY_COGWHEEL, EncasedBlocks.CHERRY_LARGE_COGWHEEL),
    BAMBOO(Items.BAMBOO_PLANKS, EncasedBlocks.BAMBOO_SHAFT, EncasedBlocks.BAMBOO_COGWHEEL, EncasedBlocks.BAMBOO_LARGE_COGWHEEL),
    CRIMSON(Items.CRIMSON_PLANKS, EncasedBlocks.CRIMSON_SHAFT, EncasedBlocks.CRIMSON_COGWHEEL, EncasedBlocks.CRIMSON_LARGE_COGWHEEL),
    WARPED(Items.WARPED_PLANKS, EncasedBlocks.WARPED_SHAFT, EncasedBlocks.WARPED_COGWHEEL, EncasedBlocks.WARPED_LARGE_COGWHEEL),
    ANDESITE(AllItems.ANDESITE_ALLOY.get(), AllBlocks.SHAFT,null,null),
    BRASS(AllItems.BRASS_INGOT.get(), EncasedBlocks.BRASS_SHAFT,null,null),
    GLASS(Items.GLASS, EncasedBlocks.GLASS_SHAFT,null,null),
    MLDEG(Items.BLACKSTONE, EncasedBlocks.MLDEG_SHAFT,null,null),
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
