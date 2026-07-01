package fr.iglee42.createcasing.registries;


import com.simibubi.create.Create;
import fr.iglee42.createcasing.CreateCasing;
import net.createmod.catnip.lang.Lang;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import static fr.iglee42.createcasing.registries.EncasedTags.NameSpace.*;

public class EncasedTags {

	public enum NameSpace {
		MOD(CreateCasing.MODID),
		CREATE(Create.ID),
		COMMON("c");

		public final String id;

		NameSpace(String id) {
			this.id = id;
		}

		public ResourceLocation id(String path) {
			return ResourceLocation.fromNamespaceAndPath(this.id, path);
		}

		public ResourceLocation id(Enum<?> entry, @Nullable String pathOverride) {
			return this.id(pathOverride != null ? pathOverride : Lang.asId(entry.name()));
		}
	}

	public enum EBlockTags {
		;

		public final TagKey<Block> tag;

		EBlockTags() {
			this(MOD);
		}

		EBlockTags(NameSpace namespace) {
			this(namespace, null);
		}

		EBlockTags(NameSpace namespace, @Nullable String pathOverride) {
			this.tag = TagKey.create(Registries.BLOCK, namespace.id(this, pathOverride));
		}

		@SuppressWarnings("deprecation")
		public boolean matches(Block block) {
			return block.builtInRegistryHolder()
				.is(tag);
		}

		public boolean matches(ItemStack stack) {
			return stack != null && stack.getItem() instanceof BlockItem blockItem && matches(blockItem.getBlock());
		}

		public boolean matches(BlockState state) {
			return state.is(tag);
		}

	}

	public enum EItemTags {
		ANDESITE_PLATES(COMMON,"plates/andesite_alloy"),
		ANDESITE_ALLOY_INGOTS(COMMON,"ingots/andesite_alloy")
		;

		public final TagKey<Item> tag;

		EItemTags() {
			this(MOD);
		}

		EItemTags(NameSpace namespace) {
			this(namespace, null);
		}

		EItemTags(NameSpace namespace, @Nullable String pathOverride) {
			this.tag = TagKey.create(Registries.ITEM, namespace.id(this, pathOverride));
		}

		@SuppressWarnings("deprecation")
		public boolean matches(Item item) {
			return item.builtInRegistryHolder()
				.is(tag);
		}

		public boolean matches(ItemStack stack) {
			return stack.is(tag);
		}
	}

}
