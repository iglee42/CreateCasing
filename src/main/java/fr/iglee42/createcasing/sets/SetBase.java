package fr.iglee42.createcasing.sets;

import com.google.common.base.Preconditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class SetBase<T extends SetBase<T, O>, O extends SetBase.Options<T, O>> {

    private final String name;
    private final Function<HolderLookup.Provider, HolderSet<Item>> item;
    private final TagKey<Item> itemTag;
    private final Supplier<ItemLike> itemSupplier;
    private final boolean isKJSGenerated;

    protected SetBase(String name, O options) {
        this.name = name;
        Preconditions.checkNotNull(options.item, "Item Supplier for the set " + name + " cannot be null");
        this.item = options.item;
        this.itemTag = options.itemTag;
        this.itemSupplier = options.itemSupplier;
        this.isKJSGenerated = options.isKJSGenerated;
    }

    public String getName() {
        return name;
    }

    public HolderSet<Item> getItems(HolderLookup.Provider registries) {
        return item.apply(registries);
    }

    public Optional<Ingredient> getItemIngredient(HolderLookup.Provider registries) {
        if (itemTag != null)
            return Optional.of(Ingredient.of(itemTag));
        if (itemSupplier != null)
            return Optional.of(Ingredient.of(itemSupplier.get()));

        HolderSet<Item> items = getItems(registries);
        ItemLike[] alternatives = items.stream()
                .map(Holder::value)
                .toArray(ItemLike[]::new);
        return alternatives.length == 0 ? Optional.empty() : Optional.of(Ingredient.of(alternatives));
    }

    public boolean isItemForSet(HolderLookup.Provider registries, ItemLike item) {
        return getItems(registries).contains(item.asItem().builtInRegistryHolder());
    }

    public boolean isKJSGenerated() {
        return isKJSGenerated;
    }

    public abstract boolean isInSet(Block block);

    public abstract static class Options<T extends SetBase<T, O>, O extends Options<T, O>> {

        Function<HolderLookup.Provider, HolderSet<Item>> item;
        TagKey<Item> itemTag;
        Supplier<ItemLike> itemSupplier;
        boolean isKJSGenerated = false;

        public O item(Function<HolderLookup.Provider, HolderSet<Item>> item) {
            if (this.item != null)
                throw new UnsupportedOperationException("An item has already be set");
            this.item = item;
            return self();
        }

        public O item(TagKey<Item> tag) {
            if (this.item != null)
                throw new UnsupportedOperationException("An item has already be set");
            this.itemTag = tag;
            this.item = registries ->
                    registries
                            .lookupOrThrow(Registries.ITEM)
                            .get(tag)
                            .map(Options::<Item>castHolderSet)
                            .orElse(HolderSet.empty());
            return self();
        }

        public O item(Supplier<ItemLike> item) {
            if (this.item != null)
                throw new UnsupportedOperationException("An item has already be set");
            this.itemSupplier = item;
            this.item = registries -> HolderSet.direct(item.get().asItem().builtInRegistryHolder());
            return self();
        }

        @SuppressWarnings("unchecked")
        private static <T> HolderSet<T> castHolderSet(HolderSet<?> set) {
            return (HolderSet<T>) set;
        }

        public O kjsGenerated() {
            this.isKJSGenerated = true;
            return self();
        }

        protected abstract O self();
    }
}