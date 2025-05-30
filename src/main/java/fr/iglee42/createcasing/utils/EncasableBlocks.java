package fr.iglee42.createcasing.utils;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.kinetics.chainDrive.ChainDriveBlock;
import com.simibubi.create.content.kinetics.chainDrive.ChainGearshiftBlock;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlock;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlock;
import com.simibubi.create.content.logistics.depot.DepotBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.registries.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

public enum EncasableBlocks {

    ANDESITE(AllBlocks.ANDESITE_CASING,AllBlocks.GEARBOX,AllBlocks.MECHANICAL_MIXER,AllBlocks.MECHANICAL_PRESS,AllBlocks.DEPOT,AllBlocks.ENCASED_CHAIN_DRIVE,AllBlocks.ADJUSTABLE_CHAIN_GEARSHIFT,ModBlocks.ANDESITE_CONFIGURABLE_GEARBOX),
    BRASS(AllBlocks.BRASS_CASING, ModBlocks.BRASS_GEARBOX,ModBlocks.BRASS_MIXER,ModBlocks.BRASS_PRESS,ModBlocks.BRASS_DEPOT,ModBlocks.BRASS_CHAIN_DRIVE,ModBlocks.BRASS_CHAIN_GEARSHIFT,ModBlocks.BRASS_CONFIGURABLE_GEARBOX),
    COPPER(AllBlocks.COPPER_CASING, ModBlocks.COPPER_GEARBOX,ModBlocks.COPPER_MIXER,ModBlocks.COPPER_PRESS,ModBlocks.COPPER_DEPOT,ModBlocks.COPPER_CHAIN_DRIVE,ModBlocks.COPPER_CHAIN_GEARSHIFT,ModBlocks.COPPER_CONFIGURABLE_GEARBOX),
    RAILWAY(AllBlocks.RAILWAY_CASING, ModBlocks.RAILWAY_GEARBOX,ModBlocks.RAILWAY_MIXER,ModBlocks.RAILWAY_PRESS,ModBlocks.RAILWAY_DEPOT,ModBlocks.RAILWAY_CHAIN_DRIVE,ModBlocks.RAILWAY_CHAIN_GEARSHIFT,ModBlocks.RAILWAY_CONFIGURABLE_GEARBOX),
    INDUSTRIAL_IRON(AllBlocks.INDUSTRIAL_IRON_BLOCK, ModBlocks.INDUSTRIAL_IRON_GEARBOX,ModBlocks.INDUSTRIAL_IRON_MIXER,ModBlocks.INDUSTRIAL_IRON_PRESS,ModBlocks.INDUSTRIAL_IRON_DEPOT,ModBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE,ModBlocks.INDUSTRIAL_IRON_CHAIN_GEARSHIFT,ModBlocks.INDUSTRIAL_IRON_CONFIGURABLE_GEARBOX),
    WEATHERED_IRON(AllBlocks.WEATHERED_IRON_BLOCK, ModBlocks.WEATHERED_IRON_GEARBOX,ModBlocks.WEATHERED_IRON_MIXER,ModBlocks.WEATHERED_IRON_PRESS,ModBlocks.WEATHERED_IRON_DEPOT,ModBlocks.WEATHERED_IRON_CHAIN_DRIVE,ModBlocks.WEATHERED_IRON_CHAIN_GEARSHIFT,ModBlocks.WEATHERED_IRON_CONFIGURABLE_GEARBOX),
    CREATIVE(ModBlocks.CREATIVE_CASING, ModBlocks.CREATIVE_GEARBOX,ModBlocks.CREATIVE_MIXER,ModBlocks.CREATIVE_PRESS,ModBlocks.CREATIVE_DEPOT,ModBlocks.CREATIVE_CHAIN_DRIVE,ModBlocks.CREATIVE_CHAIN_GEARSHIFT,ModBlocks.CREATIVE_CONFIGURABLE_GEARBOX),
;

    private final BlockEntry<? extends Block> casing;
    private final BlockEntry<? extends Block> gearbox;
    private final BlockEntry<? extends Block> mixer;
    private final BlockEntry<? extends Block> press;
    private final BlockEntry<? extends Block> depot;
    private final BlockEntry<? extends Block> chainDrive;
    private final BlockEntry<? extends Block> adjustableChainDrive;
    private final BlockEntry<? extends Block> configurableGearbox;

    EncasableBlocks(BlockEntry<? extends Block> casing, BlockEntry<? extends Block> gearbox, BlockEntry<? extends Block> mixer, BlockEntry<? extends Block> press, BlockEntry<? extends Block> depot, BlockEntry<? extends Block> chainDrive, BlockEntry<? extends Block> adjustableChainDrive, BlockEntry<? extends Block> configurableGearbox) {
        this.casing = casing;
        this.gearbox = gearbox;
        this.mixer = mixer;
        this.press = press;
        this.depot = depot;
        this.chainDrive = chainDrive;
        this.adjustableChainDrive = adjustableChainDrive;
        this.configurableGearbox = configurableGearbox;
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

    public boolean isInSet(BlockState state){
        return casing.has(state) || gearbox.has(state) || mixer.has(state) || depot.has(state) || chainDrive.has(state) || adjustableChainDrive.has(state) || configurableGearbox.has(state);
    }


}
