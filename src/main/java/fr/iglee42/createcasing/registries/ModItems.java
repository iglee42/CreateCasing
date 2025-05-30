package fr.iglee42.createcasing.registries;

import com.simibubi.create.content.legacy.NoGravMagicalDohickyItem;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.items.CustomVerticalGearboxItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Objects;
import java.util.function.Supplier;

import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;
import static fr.iglee42.createcasing.registries.EncasedBlockStateGens.gearboxModel;


public class ModItems {

    static {
        REGISTRATE.setCreativeTab(ModCreativeModeTabs.MAIN_TAB);
    }

    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_BRASS_GEARBOX =createVerticalGearboxItem("brass",p->new CustomVerticalGearboxItem(p,ModBlocks.BRASS_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_COPPER_GEARBOX =createVerticalGearboxItem("copper",p->new CustomVerticalGearboxItem(p,ModBlocks.COPPER_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_RAILWAY_GEARBOX =createVerticalGearboxItem("railway",p->new CustomVerticalGearboxItem(p,ModBlocks.RAILWAY_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_CREATIVE_GEARBOX =createVerticalGearboxItem("creative",p->new CustomVerticalGearboxItem(p,ModBlocks.CREATIVE_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_INDUSTRIAL_IRON_GEARBOX =createVerticalGearboxItem("industrial_iron",p->new CustomVerticalGearboxItem(p,ModBlocks.INDUSTRIAL_IRON_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_WEATHERED_IRON_GEARBOX =createVerticalGearboxItem("weathered_iron",p->new CustomVerticalGearboxItem(p,ModBlocks.WEATHERED_IRON_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_REFINED_RADIANCE_GEARBOX =createVerticalGearboxItem("refined_radiance",p->new CustomVerticalGearboxItem(p,ModBlocks.REFINED_RADIANCE_GEARBOX.get()));
    public static final ItemEntry<CustomVerticalGearboxItem> VERTICAL_SHADOW_STEEL_GEARBOX =createVerticalGearboxItem("shadow_steel",p->new CustomVerticalGearboxItem(p,ModBlocks.SHADOW_STEEL_GEARBOX.get()));


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
