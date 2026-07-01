package fr.iglee42.createcasing.fluids;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.recipe.CommonMetal;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.registries.EncasedItems;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import fr.iglee42.createcasing.registries.EncasedSprites;
import fr.iglee42.createcasing.registries.EncasedTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.*;

public class FluidSets {

    private static final List<FluidSet> sets = new ArrayList<>();

    public static final FluidSet ANDESITE = register("andesite",new FluidSet.Options()
            .everything(EncasedTags.EItemTags.ANDESITE_ALLOY_INGOTS.tag, EncasedTags.EItemTags.ANDESITE_PLATES.tag,()->AllBlocks.ANDESITE_CASING.get(),AllTags.AllItemTags.ANDESITE_ALLOY_STORAGE_BLOCKS.tag,
                    ()-> EncasedSprites.ANDESITE_FLUID_TANK,()->EncasedSprites.ANDESITE_FLUID_TANK_TOP,()->EncasedSprites.ANDESITE_FLUID_TANK_INNER,
                    ()-> EncasedPartialModels.ANDESITE_GAUGE,()->EncasedPartialModels.ANDESITE_GAUGE_DIAL,
                    ()->EncasedPartialModels.ANDESITE_VALVE_HANDLE,
                    ()->EncasedPartialModels.ANDESITE_HOSE_PULLEY_HALF_MAGNET, ()->EncasedPartialModels.ANDESITE_HOSE_PULLEY_MAGNET,
                    ()->EncasedPartialModels.ANDESITE_FLUID_INTERFACE_TOP,
                    ()->EncasedPartialModels.ANDESITE_SPOUT_BOTTOM));


    public static final FluidSet BRASS = register("brass",new FluidSet.Options()
            .everything(CommonMetal.BRASS.ingots,CommonMetal.BRASS.plates,()->AllBlocks.BRASS_CASING.get(),CommonMetal.BRASS.storageBlocks.items(),
                    ()-> EncasedSprites.BRASS_FLUID_TANK,()->EncasedSprites.BRASS_FLUID_TANK_TOP,()->EncasedSprites.BRASS_FLUID_TANK_INNER,
                    ()-> EncasedPartialModels.BRASS_GAUGE,()->EncasedPartialModels.BRASS_GAUGE_DIAL,
                    ()->EncasedPartialModels.BRASS_VALVE_HANDLE,
                    ()->EncasedPartialModels.BRASS_HOSE_PULLEY_HALF_MAGNET, ()->EncasedPartialModels.BRASS_HOSE_PULLEY_MAGNET,
                    ()->EncasedPartialModels.BRASS_FLUID_INTERFACE_TOP,
                    ()->EncasedPartialModels.BRASS_SPOUT_BOTTOM));


    public static final FluidSet COPPER = register("copper",new FluidSet.Options()
            .base(CommonMetal.COPPER.ingots, CommonMetal.COPPER.plates, ()->AllBlocks.COPPER_CASING.get(),CommonMetal.COPPER.storageBlocks.items())
            .existingPipe(AllBlocks.FLUID_PIPE,AllBlocks.GLASS_FLUID_PIPE)
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

    public static final FluidSet ZINC = register("zinc",new FluidSet.Options()
        .everything(CommonMetal.ZINC.ingots, CommonMetal.ZINC.plates, ()-> CasingSets.ZINC.getCasing(),CommonMetal.ZINC.storageBlocks.items(),
                ()-> EncasedSprites.ZINC_FLUID_TANK,()->EncasedSprites.ZINC_FLUID_TANK_TOP,()->EncasedSprites.ZINC_FLUID_TANK_INNER,
                ()-> EncasedPartialModels.ZINC_GAUGE,()->EncasedPartialModels.ZINC_GAUGE_DIAL,
                ()->EncasedPartialModels.ZINC_VALVE_HANDLE,
                ()->EncasedPartialModels.ZINC_HOSE_PULLEY_HALF_MAGNET, ()->EncasedPartialModels.ZINC_HOSE_PULLEY_MAGNET,
                ()->EncasedPartialModels.ZINC_FLUID_INTERFACE_TOP,
                ()->EncasedPartialModels.ZINC_SPOUT_BOTTOM));

    public static FluidSet register(String id, FluidSet.Options options){
        String name = id.toLowerCase(Locale.ROOT);
        if (sets.stream().anyMatch(set->set.getName().equalsIgnoreCase(name)))
            throw new IllegalArgumentException("A fluid set with name `" + name + "` already exists !");
        FluidSet set = new FluidSet(name,options);
        sets.add(set);
        return set;
    }

    public static List<FluidSet> getSets() {
        return ImmutableList.copyOf(sets);
    }
}
