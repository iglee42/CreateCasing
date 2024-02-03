package fr.iglee42.createcasing.registries;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.ItemEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.items.CustomVerticalGearboxItem;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;


public class ModItems {

    static {
        REGISTRATE.creativeModeTab(() -> CreateCasing.TAB);
    }

    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_BRASS_GEARBOX =
            REGISTRATE.item("vertical_brass_gearbox", (p)->new CustomVerticalGearboxItem(p,ModBlocks.BRASS_GEARBOX.get()))
                    .model(AssetLookup.customBlockItemModel("brass_gearbox", "item_vertical"))
                    .register();

    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_COPPER_GEARBOX =
            REGISTRATE.item("vertical_copper_gearbox", (p)->new CustomVerticalGearboxItem(p,ModBlocks.COPPER_GEARBOX.get()))
                    .model(AssetLookup.customBlockItemModel("copper_gearbox", "item_vertical"))
                    .register();
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_RAILWAY_GEARBOX =
            REGISTRATE.item("vertical_railway_gearbox", (p)->new CustomVerticalGearboxItem(p,ModBlocks.RAILWAY_GEARBOX.get()))
                    .model(AssetLookup.customBlockItemModel("railway_gearbox", "item_vertical"))
                    .register();

    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_CREATIVE_GEARBOX =
            REGISTRATE.item("vertical_creative_gearbox", (p)->new CustomVerticalGearboxItem(p,ModBlocks.CREATIVE_GEARBOX.get()))
                    .model(AssetLookup.customBlockItemModel("creative_gearbox", "item_vertical"))
                    .register();

    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_INDUSTRIAL_IRON_GEARBOX =
            REGISTRATE.item("vertical_industrial_iron_gearbox", (p)->new CustomVerticalGearboxItem(p,ModBlocks.INDUSTRIAL_IRON_GEARBOX.get()))
                    .model(AssetLookup.customBlockItemModel("industrial_iron_gearbox", "item_vertical"))
                    .register();

    public static final ItemEntry<Item> CHORIUM_INGOT =
            REGISTRATE.item("chorium_ingot", Item::new)
                    .properties(p->p.rarity(Rarity.EPIC))
                    .register();

    public static final ItemEntry<SequencedAssemblyItem> PROCESSING_CHORIUM =
            REGISTRATE.item("processing_chorium", SequencedAssemblyItem::new)
                    .properties(p->p.rarity(Rarity.EPIC))
                    .onRegisterAfter(Registry.ITEM_REGISTRY, CreateCasing::hideItem)
                    .register();

    public static void register(){}
}
