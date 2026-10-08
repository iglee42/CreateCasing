package fr.iglee42.createcasing.registries;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.legacy.RefinedRadianceItem;
import com.simibubi.create.content.legacy.ShadowSteelItem;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.recipe.CommonMetal;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.sets.casings.CasingSets;
import fr.iglee42.createcasing.items.CustomVerticalGearboxItem;
import fr.iglee42.createcasing.items.recaser.RecaserItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.Objects;

import static com.simibubi.create.AllTags.AllItemTags.CREATE_INGOTS;
import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;
import static fr.iglee42.createcasing.registries.EncasedBlockStateGens.gearboxModel;


public class EncasedItems {

    static {
        REGISTRATE.setCreativeTab(EncasedCreativeModeTabs.MAIN_TAB);
    }

    public static final ItemEntry<RecaserItem> RECASER = REGISTRATE.item("recaser", RecaserItem::new)
            .properties(p -> p.stacksTo(1))
            .model(AssetLookup.itemModelWithPartials())
            .register();


    public static ItemEntry<CustomVerticalGearboxItem> createVerticalGearboxItem(String name, NonNullFunction<Item.Properties, CustomVerticalGearboxItem> function){
        return REGISTRATE.item("vertical_"+name+"_gearbox", function)
                .model((ctx,prov)->prov.getBuilder(ctx.getName()).parent(Objects.requireNonNull(gearboxModel(prov, name, "item_vertical"))))
                .register();
    }

    public static final ItemEntry<Item> CHORIUM_INGOT =
            REGISTRATE.item("chorium_ingot", Item::new)
                    .properties(p->p.rarity(Rarity.EPIC))
                    .tag(EncasedTags.EItemTags.CHORIUM_INGOTS.tag)
                    .tag(CREATE_INGOTS.tag)
                    .register();

    public static final ItemEntry<SequencedAssemblyItem> PROCESSING_CHORIUM =
            REGISTRATE.item("processing_chorium", SequencedAssemblyItem::new)
                    .properties(p->p.rarity(Rarity.EPIC))
                    .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem)
                    .register();

    public static final ItemEntry<Item> ANDESITE_SHEET =
            REGISTRATE.item("andesite_sheet", Item::new)
                    .tag(EncasedTags.EItemTags.ANDESITE_PLATES.tag)
                    .tag(AllTags.AllItemTags.PLATES.tag)
                    .register();

    public static final ItemEntry<Item> ZINC_SHEET =
            REGISTRATE.item("zinc_sheet", Item::new)
                    .tag(CommonMetal.ZINC.plates)
                    .register();

    public static final ItemEntry<ShadowSteelItem> SHADOW_STEEL_SHEET =
            REGISTRATE.item("shadow_steel_sheet", ShadowSteelItem::new)
                    .tag(EncasedTags.EItemTags.SHADOW_STEEL_PLATES.tag)
                    .register();


    public static final ItemEntry<RefinedRadianceItem> REFINED_RADIANCE_SHEET =
            REGISTRATE.item("refined_radiance_sheet", RefinedRadianceItem::new)
                    .tag(EncasedTags.EItemTags.REFINED_RADIANCE_PLATES.tag)
                    .register();

    public static final ItemEntry<Item> INDUSTRIAL_IRON_SHEET =
            REGISTRATE.item("industrial_iron_sheet", Item::new)
                    .tag(EncasedTags.EItemTags.INDUSTRIAL_IRON_PLATES.tag)
                    .register();


    public static final ItemEntry<Item> WEATHERED_IRON_SHEET =
            REGISTRATE.item("weathered_iron_sheet", Item::new)
                    .tag(EncasedTags.EItemTags.WEATHERED_IRON_PLATES.tag)
                    .register();

    public static final ItemEntry<Item> CHORIUM_SHEET =
            REGISTRATE.item("chorium_sheet", Item::new)
                    .tag(EncasedTags.EItemTags.CHORIUM_PLATES.tag)
                    .register();

    public static void register(){
        CasingSets.getSets().forEach(set->{
            if (set.doesGenerateGearbox())
                set.setVerticalGearboxItem(createVerticalGearboxItem(set.getName(),p->new CustomVerticalGearboxItem(p,set.getGearbox())));
        });
    }
}
