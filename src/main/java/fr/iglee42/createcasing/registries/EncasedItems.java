package fr.iglee42.createcasing.registries;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.items.CustomVerticalGearboxItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.Objects;

import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;
import static fr.iglee42.createcasing.registries.EncasedBlockStateGens.gearboxModel;


public class EncasedItems {

    static {
        REGISTRATE.setCreativeTab(EncasedCreativeModeTabs.MAIN_TAB);
    }

    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_BRASS_GEARBOX =createVerticalGearboxItem("brass",p->new CustomVerticalGearboxItem(p, EncasedBlocks.BRASS_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_COPPER_GEARBOX =createVerticalGearboxItem("copper",p->new CustomVerticalGearboxItem(p, EncasedBlocks.COPPER_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_RAILWAY_GEARBOX =createVerticalGearboxItem("railway",p->new CustomVerticalGearboxItem(p, EncasedBlocks.RAILWAY_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_CREATIVE_GEARBOX =createVerticalGearboxItem("creative",p->new CustomVerticalGearboxItem(p, EncasedBlocks.CREATIVE_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_INDUSTRIAL_IRON_GEARBOX =createVerticalGearboxItem("industrial_iron",p->new CustomVerticalGearboxItem(p, EncasedBlocks.INDUSTRIAL_IRON_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_WEATHERED_IRON_GEARBOX =createVerticalGearboxItem("weathered_iron",p->new CustomVerticalGearboxItem(p, EncasedBlocks.WEATHERED_IRON_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_REFINED_RADIANCE_GEARBOX =createVerticalGearboxItem("refined_radiance",p->new CustomVerticalGearboxItem(p, EncasedBlocks.REFINED_RADIANCE_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_SHADOW_STEEL_GEARBOX =createVerticalGearboxItem("shadow_steel",p->new CustomVerticalGearboxItem(p, EncasedBlocks.SHADOW_STEEL_GEARBOX.get()));


    public static ItemEntry<CustomVerticalGearboxItem> createVerticalGearboxItem(String name, NonNullFunction<Item.Properties, CustomVerticalGearboxItem> function){
        return REGISTRATE.item("vertical_"+name+"_gearbox", function)
                .model((ctx,prov)->prov.getBuilder(ctx.getName()).parent(Objects.requireNonNull(gearboxModel(prov, name, "item_vertical"))))
                .register();
    }

    public static final ItemEntry<Item> CHORIUM_INGOT =
            REGISTRATE.item("chorium_ingot", Item::new)
                    .properties(p->p.rarity(Rarity.EPIC))
                    .register();

    public static final ItemEntry<SequencedAssemblyItem> PROCESSING_CHORIUM =
            REGISTRATE.item("processing_chorium", SequencedAssemblyItem::new)
                    .properties(p->p.rarity(Rarity.EPIC))
                    .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem)
                    .register();

    public static void register(){}
}
