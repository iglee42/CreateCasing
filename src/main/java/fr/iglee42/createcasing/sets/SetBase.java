package fr.iglee42.createcasing.sets;

import com.google.common.base.Preconditions;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class SetBase<T extends SetBase<T, O>, O extends SetBase.Options<T, O>> {

    private final String name;
    private final Function<RegistryAccess, HolderSet<Item>> item;
    private final boolean isKJSGenerated;

    protected SetBase(String name, O options) {
        this.name = name;
        Preconditions.checkNotNull(options.item, "Item Supplier for the set "+ name +" cannot be null");
        this.item = options.item;
        this.isKJSGenerated = options.isKJSGenerated;
    }

    public String getName() {
        return name;
    }

    public HolderSet<Item> getItems(RegistryAccess access){
        return item.apply(access);
    }

    public boolean isItemForSet(RegistryAccess access, ItemLike item){
        return getItems(access).contains(item.asItem().builtInRegistryHolder());
    }

    public boolean isKJSGenerated() {
        return isKJSGenerated;
    }

    public abstract boolean isInSet(Block block);

    public abstract static class Options<T extends SetBase<T, O>, O extends Options<T, O>> {

        Function<RegistryAccess, HolderSet<Item>> item;
        boolean isKJSGenerated = false;

        public O item(Function<RegistryAccess, HolderSet<Item>> item) {
            if (this.item != null)
                throw new UnsupportedOperationException("An item has already be set");
            this.item = item;
            return self();
        }

        public O item(TagKey<Item> tag) {
            if (this.item != null)
                throw new UnsupportedOperationException("An item has already be set");
            this.item = registries ->
                    registries
                            .registryOrThrow(Registries.ITEM)
                            .getTag(tag)
                            .map(Options::<Item>castHolderSet)
                            .orElse(HolderSet.empty());
            return self();
        }

        public O item(Supplier<ItemLike> item) {
            if (this.item != null)
                throw new UnsupportedOperationException("An item has already be set");
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