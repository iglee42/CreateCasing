package fr.iglee42.createcasing.sets.fluids;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.data.recipe.CommonMetal;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import fr.iglee42.createcasing.registries.EncasedSprites;
import fr.iglee42.createcasing.registries.EncasedTags;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.UnaryOperator;

public class FluidSets {

    private static final List<FluidSet> sets = new ArrayList<>();

    public static final FluidSet ANDESITE = register("andesite", options -> options
            .item(EncasedTags.EItemTags.ANDESITE_PLATES.tag)
            .everything(
                    () -> EncasedSprites.ANDESITE_FLUID_TANK, () -> EncasedSprites.ANDESITE_FLUID_TANK_TOP, () -> EncasedSprites.ANDESITE_FLUID_TANK_INNER,
                    () -> EncasedPartialModels.ANDESITE_GAUGE, () -> EncasedPartialModels.ANDESITE_GAUGE_DIAL,
                    () -> EncasedPartialModels.ANDESITE_VALVE_HANDLE,
                    () -> EncasedPartialModels.ANDESITE_HOSE_PULLEY_HALF_MAGNET, () -> EncasedPartialModels.ANDESITE_HOSE_PULLEY_MAGNET,
                    () -> EncasedPartialModels.ANDESITE_FLUID_INTERFACE_TOP,
                    () -> EncasedPartialModels.ANDESITE_SPOUT_BOTTOM));


    public static final FluidSet BRASS = register("brass", options -> options
            .item(CommonMetal.BRASS.plates)
            .everything(
                    () -> EncasedSprites.BRASS_FLUID_TANK, () -> EncasedSprites.BRASS_FLUID_TANK_TOP, () -> EncasedSprites.BRASS_FLUID_TANK_INNER,
                    () -> EncasedPartialModels.BRASS_GAUGE, () -> EncasedPartialModels.BRASS_GAUGE_DIAL,
                    () -> EncasedPartialModels.BRASS_VALVE_HANDLE,
                    () -> EncasedPartialModels.BRASS_HOSE_PULLEY_HALF_MAGNET, () -> EncasedPartialModels.BRASS_HOSE_PULLEY_MAGNET,
                    () -> EncasedPartialModels.BRASS_FLUID_INTERFACE_TOP,
                    () -> EncasedPartialModels.BRASS_SPOUT_BOTTOM));


    public static final FluidSet COPPER = register("copper", options -> options
            .item(CommonMetal.COPPER.plates)
            .existingPipe(AllBlocks.FLUID_PIPE, AllBlocks.GLASS_FLUID_PIPE)
            .existingPump(AllBlocks.MECHANICAL_PUMP)
            .existingSmartPipe(AllBlocks.SMART_FLUID_PIPE)
            .existingItemDrain(AllBlocks.ITEM_DRAIN)
            .existingHosePulley(AllBlocks.HOSE_PULLEY)
            .existingPortableFluidInterface(AllBlocks.PORTABLE_FLUID_INTERFACE)
            .existingSteamEngine(AllBlocks.STEAM_ENGINE)
            .existingTank(AllBlocks.FLUID_TANK)
            .existingWhistle(AllBlocks.STEAM_WHISTLE)
            .existingValve(AllBlocks.FLUID_VALVE)
            .existingValveHandle(AllBlocks.COPPER_VALVE_HANDLE)
    );

    public static final FluidSet ZINC = register("zinc", options -> options
            .item(CommonMetal.ZINC.plates)
            .everything(
                    () -> EncasedSprites.ZINC_FLUID_TANK, () -> EncasedSprites.ZINC_FLUID_TANK_TOP, () -> EncasedSprites.ZINC_FLUID_TANK_INNER,
                    () -> EncasedPartialModels.ZINC_GAUGE, () -> EncasedPartialModels.ZINC_GAUGE_DIAL,
                    () -> EncasedPartialModels.ZINC_VALVE_HANDLE,
                    () -> EncasedPartialModels.ZINC_HOSE_PULLEY_HALF_MAGNET, () -> EncasedPartialModels.ZINC_HOSE_PULLEY_MAGNET,
                    () -> EncasedPartialModels.ZINC_FLUID_INTERFACE_TOP,
                    () -> EncasedPartialModels.ZINC_SPOUT_BOTTOM));

    public static FluidSet register(String id, UnaryOperator<FluidSet.Options> options) {
        String name = id.toLowerCase(Locale.ROOT);
        if (sets.stream().anyMatch(set -> set.getName().equalsIgnoreCase(name)))
            throw new IllegalArgumentException("A fluid set with name `" + name + "` already exists !");
        FluidSet set = new FluidSet(name, options.apply(new FluidSet.Options()));
        sets.add(set);
        return set;
    }

    public static List<FluidSet> getSets() {
        return ImmutableList.copyOf(sets);
    }
}
