package fr.iglee42.createcasing.utils;

import com.simibubi.create.AllBlocks;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

public enum EncasableBlocks {

    ANDESITE(AllBlocks.ANDESITE_CASING,AllBlocks.GEARBOX,AllBlocks.MECHANICAL_MIXER,AllBlocks.MECHANICAL_PRESS,AllBlocks.DEPOT,AllBlocks.ENCASED_CHAIN_DRIVE,AllBlocks.ADJUSTABLE_CHAIN_GEARSHIFT, EncasedBlocks.ANDESITE_CONFIGURABLE_GEARBOX,AllBlocks.CHAIN_CONVEYOR),
    BRASS(AllBlocks.BRASS_CASING, EncasedBlocks.BRASS_GEARBOX, EncasedBlocks.BRASS_MIXER, EncasedBlocks.BRASS_PRESS, EncasedBlocks.BRASS_DEPOT, EncasedBlocks.BRASS_CHAIN_DRIVE, EncasedBlocks.BRASS_CHAIN_GEARSHIFT, EncasedBlocks.BRASS_CONFIGURABLE_GEARBOX, EncasedBlocks.BRASS_CHAIN_CONVEYOR),
    COPPER(AllBlocks.COPPER_CASING, EncasedBlocks.COPPER_GEARBOX, EncasedBlocks.COPPER_MIXER, EncasedBlocks.COPPER_PRESS, EncasedBlocks.COPPER_DEPOT, EncasedBlocks.COPPER_CHAIN_DRIVE, EncasedBlocks.COPPER_CHAIN_GEARSHIFT, EncasedBlocks.COPPER_CONFIGURABLE_GEARBOX, EncasedBlocks.COPPER_CHAIN_CONVEYOR),
    RAILWAY(AllBlocks.RAILWAY_CASING, EncasedBlocks.RAILWAY_GEARBOX, EncasedBlocks.RAILWAY_MIXER, EncasedBlocks.RAILWAY_PRESS, EncasedBlocks.RAILWAY_DEPOT, EncasedBlocks.RAILWAY_CHAIN_DRIVE, EncasedBlocks.RAILWAY_CHAIN_GEARSHIFT, EncasedBlocks.RAILWAY_CONFIGURABLE_GEARBOX, EncasedBlocks.RAILWAY_CHAIN_CONVEYOR),
    INDUSTRIAL_IRON(AllBlocks.INDUSTRIAL_IRON_BLOCK, EncasedBlocks.INDUSTRIAL_IRON_GEARBOX, EncasedBlocks.INDUSTRIAL_IRON_MIXER, EncasedBlocks.INDUSTRIAL_IRON_PRESS, EncasedBlocks.INDUSTRIAL_IRON_DEPOT, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_GEARSHIFT, EncasedBlocks.INDUSTRIAL_IRON_CONFIGURABLE_GEARBOX, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_CONVEYOR),
    WEATHERED_IRON(AllBlocks.WEATHERED_IRON_BLOCK, EncasedBlocks.WEATHERED_IRON_GEARBOX, EncasedBlocks.WEATHERED_IRON_MIXER, EncasedBlocks.WEATHERED_IRON_PRESS, EncasedBlocks.WEATHERED_IRON_DEPOT, EncasedBlocks.WEATHERED_IRON_CHAIN_DRIVE, EncasedBlocks.WEATHERED_IRON_CHAIN_GEARSHIFT, EncasedBlocks.WEATHERED_IRON_CONFIGURABLE_GEARBOX, EncasedBlocks.WEATHERED_IRON_CHAIN_CONVEYOR),
    REFINED_RADIANCE(AllBlocks.REFINED_RADIANCE_CASING, EncasedBlocks.REFINED_RADIANCE_GEARBOX, EncasedBlocks.REFINED_RADIANCE_MIXER, EncasedBlocks.REFINED_RADIANCE_PRESS, EncasedBlocks.REFINED_RADIANCE_DEPOT, EncasedBlocks.REFINED_RADIANCE_CHAIN_DRIVE, EncasedBlocks.REFINED_RADIANCE_CHAIN_GEARSHIFT, EncasedBlocks.REFINED_RADIANCE_CONFIGURABLE_GEARBOX, EncasedBlocks.REFINED_RADIANCE_CHAIN_CONVEYOR),
    SHADOW_STEEL(AllBlocks.SHADOW_STEEL_CASING, EncasedBlocks.SHADOW_STEEL_GEARBOX, EncasedBlocks.SHADOW_STEEL_MIXER, EncasedBlocks.SHADOW_STEEL_PRESS, EncasedBlocks.SHADOW_STEEL_DEPOT, EncasedBlocks.SHADOW_STEEL_CHAIN_DRIVE, EncasedBlocks.SHADOW_STEEL_CHAIN_GEARSHIFT, EncasedBlocks.SHADOW_STEEL_CONFIGURABLE_GEARBOX, EncasedBlocks.SHADOW_STEEL_CHAIN_CONVEYOR),
    CREATIVE(EncasedBlocks.CREATIVE_CASING, EncasedBlocks.CREATIVE_GEARBOX, EncasedBlocks.CREATIVE_MIXER, EncasedBlocks.CREATIVE_PRESS, EncasedBlocks.CREATIVE_DEPOT, EncasedBlocks.CREATIVE_CHAIN_DRIVE, EncasedBlocks.CREATIVE_CHAIN_GEARSHIFT, EncasedBlocks.CREATIVE_CONFIGURABLE_GEARBOX, EncasedBlocks.CREATIVE_CHAIN_CONVEYOR),
;

    private final BlockEntry<? extends Block> casing;
    private final BlockEntry<? extends Block> gearbox;
    private final BlockEntry<? extends Block> mixer;
    private final BlockEntry<? extends Block> press;
    private final BlockEntry<? extends Block> depot;
    private final BlockEntry<? extends Block> chainDrive;
    private final BlockEntry<? extends Block> adjustableChainDrive;
    private final BlockEntry<? extends Block> configurableGearbox;
    private final BlockEntry<? extends Block> chainConveyor;

    EncasableBlocks(BlockEntry<? extends Block> casing, BlockEntry<? extends Block> gearbox, BlockEntry<? extends Block> mixer, BlockEntry<? extends Block> press, BlockEntry<? extends Block> depot, BlockEntry<? extends Block> chainDrive, BlockEntry<? extends Block> adjustableChainDrive, BlockEntry<? extends Block> configurableGearbox, BlockEntry<? extends Block> chainConveyor) {
        this.casing = casing;
        this.gearbox = gearbox;
        this.mixer = mixer;
        this.press = press;
        this.depot = depot;
        this.chainDrive = chainDrive;
        this.adjustableChainDrive = adjustableChainDrive;
        this.configurableGearbox = configurableGearbox;
        this.chainConveyor = chainConveyor;
    }

    public static EncasableBlocks getBlockByCasing(BlockState casing){
        return Arrays.stream(values()).filter(e->e.casing.has(casing)).findFirst().orElse(null);
    }

    public static EncasableBlocks getBlockByCasing(Item casing){
        return Arrays.stream(values()).filter(e->e.casing.is(casing)).findFirst().orElse(null);
    }

    public static boolean hasBlocksForCasing(BlockState casing){
        return getBlockByCasing(casing) != null;
    }

    public static boolean hasBlocksForCasing(Item casing){
        return getBlockByCasing(casing) != null;
    }

    public BlockEntry<? extends Block> getCasing() {
        return casing;
    }

    public BlockEntry<? extends Block> getGearbox() {
        return gearbox;
    }

    public BlockEntry<? extends Block> getMixer() {
        return mixer;
    }

    public BlockEntry<? extends Block> getPress() {
        return press;
    }

    public BlockEntry<? extends Block> getDepot() {
        return depot;
    }

    public BlockEntry<? extends Block> getChainDrive() {
        return chainDrive;
    }

    public BlockEntry<? extends Block> getAdjustableChainDrive() {
        return adjustableChainDrive;
    }

    public BlockEntry<? extends Block> getConfigurableGearbox() {
        return configurableGearbox;
    }

    public BlockEntry<? extends Block> getChainConveyor() {
        return chainConveyor;
    }

    public static boolean isGearbox(BlockState state){
        return Arrays.stream(values()).anyMatch(b->b.getGearbox().has(state));
    }

    public static boolean isMixer(BlockState state){
        return Arrays.stream(values()).anyMatch(b->b.getMixer().has(state));
    }

    public static boolean isPress(BlockState state){
        return Arrays.stream(values()).anyMatch(b->b.getPress().has(state));
    }

    public static boolean isDepot(BlockState state){
        return Arrays.stream(values()).anyMatch(b->b.getDepot().has(state));
    }

    public static boolean isChainDrive(BlockState state){
        return Arrays.stream(values()).anyMatch(b->b.getChainDrive().has(state));
    }

    public static boolean isAdjustableChainDrive(BlockState state){
        return Arrays.stream(values()).anyMatch(b->b.getAdjustableChainDrive().has(state));
    }
    public static boolean isConfigurableGearbox(BlockState state){
        return Arrays.stream(values()).anyMatch(b->b.getConfigurableGearbox().has(state));
    }
    public static boolean isChainConveyor(BlockState state){
        return Arrays.stream(values()).anyMatch(b->b.getChainConveyor().has(state));
    }

    public boolean isInSet(BlockState state){
        return casing.has(state) || gearbox.has(state) || mixer.has(state) || depot.has(state) || chainDrive.has(state) || adjustableChainDrive.has(state) || configurableGearbox.has(state) || chainConveyor.has(state);
    }


}
