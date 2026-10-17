package fr.iglee42.createcasing.sets.transmissions;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.data.recipe.CommonMetal;
import fr.iglee42.createcasing.blocks.cogwheels.WoodenCogwheelBlock;
import fr.iglee42.createcasing.blocks.shafts.GlassShaftBlock;
import fr.iglee42.createcasing.blocks.shafts.WoodenShaftBlock;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import fr.iglee42.createcasing.registries.EncasedTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.common.Tags;

import java.util.*;
import java.util.function.UnaryOperator;

public class TransmissionSets {

    private static final List<TransmissionSet> sets = new ArrayList<>();
    private static final Map<WoodType,TransmissionSet> woodSets = new HashMap<>();

    public static final TransmissionSet ANDESITE = register("andesite",options->options
            .item(EncasedTags.EItemTags.ANDESITE_PLATES.tag)
            .existingShaft(AllBlocks.SHAFT)
            .cogwheel()
            .largeCogwheel()
    );

    public static final TransmissionSet BRASS = register("brass",options->options
            .item(CommonMetal.BRASS.plates)
            .everything()
    );

    public static final TransmissionSet COPPER = register("copper",options->options
            .item(CommonMetal.COPPER.plates)
            .everything()
    );

    public static final TransmissionSet ZINC = register("zinc",options->options
            .item(CommonMetal.ZINC.plates)
            .everything()
    );

    public static final TransmissionSet MLDEG = register("mldeg",options->options
            .item(()-> Items.BLACKSTONE)
            .shaft()
    );

    public static final TransmissionSet GLASS = register("glass",options->options
            .item(Tags.Items.GLASS_BLOCKS_COLORLESS)
            .shaftBlockEntityType(()-> EncasedBlockEntities.GLASS_SHAFT.get())
            .shaftConstructor(()-> GlassShaftBlock::new)
            .shaft()
    );



    static {
        for (WoodType woodType : new WoodType[]{WoodType.ACACIA,WoodType.BIRCH,WoodType.BAMBOO,WoodType.CHERRY,WoodType.CRIMSON,WoodType.DARK_OAK,WoodType.OAK,WoodType.JUNGLE,WoodType.MANGROVE,WoodType.WARPED}) {
            TransmissionSet set = register(woodType.name().toLowerCase(Locale.ROOT), options->options
                    .item(()-> BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodType.name().toLowerCase(Locale.ROOT) + "_planks")))
                    .shaftConstructor(()-> WoodenShaftBlock::new)
                    .cogwheelConstructor(()-> (props,bool)-> bool ? WoodenCogwheelBlock.large(props) : WoodenCogwheelBlock.small(props))
                    .shaftBlockEntityType(()-> EncasedBlockEntities.WOODEN_SHAFT.get())
                    .cogwheelBlockEntityType(()-> EncasedBlockEntities.WOODEN_COGWHEELS.get())
                    .largeCogwheelBlockEntityType(()-> EncasedBlockEntities.WOODEN_COGWHEELS.get())
                    .everything()
            );
            woodSets.put(woodType, set);
        }

        TransmissionSet spruceSet = register("spruce", options->options
                .item(()-> Items.SPRUCE_PLANKS)
                .shaft()
                .shaftConstructor(()-> WoodenShaftBlock::new)
                .shaftBlockEntityType(()-> EncasedBlockEntities.WOODEN_SHAFT.get())
                .existingCogwheel( AllBlocks.COGWHEEL)
                .existingLargeCogwheel(AllBlocks.LARGE_COGWHEEL)
        );

        woodSets.put(WoodType.SPRUCE,spruceSet);
    }


    public static TransmissionSet register(String id, UnaryOperator<TransmissionSet.Options> options){
        String name = id.toLowerCase(Locale.ROOT);
        if (sets.stream().anyMatch(set->set.getName().equalsIgnoreCase(name)))
            throw new IllegalArgumentException("A transmission set with name `" + name + "` already exists !");
        TransmissionSet set = new TransmissionSet(name,options.apply(new TransmissionSet.Options()));
        sets.add(set);
        return set;
    }

    public static List<TransmissionSet> getSets() {
        return ImmutableList.copyOf(sets);
    }

    public static List<TransmissionSet> getWoodSets() {
        return ImmutableList.copyOf(woodSets.values());
    }

    public static TransmissionSet getWoodSet(WoodType woodType){
        return woodSets.get(woodType);
    }

    public static WoodType getWoodTypeForSet(TransmissionSet set) {
        if (!woodSets.containsValue(set)) return null;
        return woodSets.entrySet().stream().filter(e->e.getValue().equals(set)).map(Map.Entry::getKey).findFirst().orElse(null);
    }
}
