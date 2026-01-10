package fr.iglee42.createcasing.transmissions;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import fr.iglee42.createcasing.blocks.cogwheels.WoodenCogwheelBlock;
import fr.iglee42.createcasing.blocks.shafts.BrassShaftBlock;
import fr.iglee42.createcasing.blocks.shafts.GlassShaftBlock;
import fr.iglee42.createcasing.blocks.shafts.WoodenShaftBlock;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.*;

public class TransmissionSets {

    private static final List<TransmissionSet> sets = new ArrayList<>();
    private static final Map<WoodType,TransmissionSet> woodSets = new HashMap<>();

    public static final TransmissionSet ANDESITE = register("andesite",new TransmissionSet.Options()
            .item(()-> AllItems.ANDESITE_ALLOY.get())
            .existingShaft(AllBlocks.SHAFT)
    );

    public static final TransmissionSet BRASS = register("brass",new TransmissionSet.Options()
            .item(()-> AllItems.BRASS_INGOT.get())
            .shaft()
            .notEncasable()
            .shaftConstructor(()-> BrassShaftBlock::new)
            .shaftBlockEntityType(()->EncasedBlockEntities.BRASS_SHAFT.get())
    );

    public static final TransmissionSet MLDEG = register("mldeg",new TransmissionSet.Options()
            .item(()-> Items.BLACKSTONE)
            .shaft()
    );


    public static final TransmissionSet GLASS = register("glass",new TransmissionSet.Options()
            .item(()-> Items.GLASS)
            .shaftBlockEntityType(()-> EncasedBlockEntities.GLASS_SHAFT.get())
            .shaftConstructor(()-> GlassShaftBlock::new)
            .shaft()
    );



    static {
        for (WoodType woodType : new WoodType[]{WoodType.ACACIA,WoodType.BIRCH,WoodType.BAMBOO,WoodType.CHERRY,WoodType.CRIMSON,WoodType.DARK_OAK,WoodType.OAK,WoodType.JUNGLE,WoodType.MANGROVE,WoodType.WARPED}) {
            TransmissionSet set = register(woodType.name().toLowerCase(Locale.ROOT), new TransmissionSet.Options()
                    .shaftConstructor(()-> WoodenShaftBlock::new)
                    .cogwheelConstructor(()-> (props,bool)-> bool ? WoodenCogwheelBlock.large(props) : WoodenCogwheelBlock.small(props))
                    .shaftBlockEntityType(()-> EncasedBlockEntities.WOODEN_SHAFT.get())
                    .cogwheelBlockEntityType(()-> EncasedBlockEntities.WOODEN_COGWHEELS.get())
                    .largeCogwheelBlockEntityType(()-> EncasedBlockEntities.WOODEN_COGWHEELS.get())
                    .everything(()-> BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodType.name().toLowerCase(Locale.ROOT) + "_planks")))
            );
            woodSets.put(woodType, set);
        }

        TransmissionSet spruceSet = register("spruce", new TransmissionSet.Options()
                .item(()-> Items.SPRUCE_PLANKS)
                .shaft()
                .shaftConstructor(()-> WoodenShaftBlock::new)
                .shaftBlockEntityType(()-> EncasedBlockEntities.WOODEN_SHAFT.get())
                .existingCogwheel( AllBlocks.COGWHEEL)
                .existingLargeCogwheel(AllBlocks.LARGE_COGWHEEL)
        );

        woodSets.put(WoodType.SPRUCE,spruceSet);
    }


    public static TransmissionSet register(String id, TransmissionSet.Options options){
        String name = id.toLowerCase(Locale.ROOT);
        if (sets.stream().anyMatch(set->set.getName().equalsIgnoreCase(name)))
            throw new IllegalArgumentException("A transmission set with name `" + name + "` already exists !");
        TransmissionSet set = new TransmissionSet(name,options);
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
