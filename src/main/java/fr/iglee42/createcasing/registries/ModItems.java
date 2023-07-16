package fr.iglee42.createcasing.registries;

import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.ItemEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.items.CustomVerticalGearboxItem;
import net.minecraft.world.item.Item;

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

    public static final ItemEntry<Item> PRESS_PLATE =
            REGISTRATE.item("press_plate",Item::new)
                    .register();

    public static void register(){}
}