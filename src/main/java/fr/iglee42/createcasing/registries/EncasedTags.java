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
		SHAFTS(CREATE),
		COGWHEELS(CREATE),
		LARGE_COGWHEELS(CREATE),
		GEARBOXES(CREATE),
		MIXERS(CREATE),
		PRESSES(CREATE),
		DEPOTS(CREATE),
		CHAIN_DRIVES(CREATE),
		CHAIN_GEARSHIFTS(CREATE),
		CONFIGURABLE_GEARBOXES(CREATE),
		CHAIN_CONVEYORS(CREATE),
		GEARSHIFTS(CREATE),
		CLUTCHES(CREATE),
		AUTO_CLUTCHES(CREATE),
		DEPLOYERS(CREATE),
		STORAGE_INTERFACES(CREATE),
		ENCASED_FANS(CREATE),
		HARVESTERS(CREATE),
		SAWS(CREATE),
		DRILLS(CREATE),
		PLOUGHS(CREATE),
		ROLLERS(CREATE),
		FLUID_PIPES(CREATE),
		SMART_FLUID_PIPES(CREATE),
		MECHANICAL_PUMPS(CREATE),
		FLUID_TANKS(CREATE),
		STEAM_ENGINES(CREATE),
		ITEM_DRAINS(CREATE),
		FLUID_VALVES(CREATE),
		HOSE_PULLEYS(CREATE),
		PORTABLE_FLUID_INTERFACES(CREATE),
		STEAM_WHISTLES(CREATE),
		SPOUTS(CREATE),
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
		ANDESITE_ALLOY_INGOTS(COMMON,"ingots/andesite_alloy"),

		SHADOW_STEEL_PLATES(COMMON,"plates/shadow_steel"),
		SHADOW_STEEL_INGOTS(COMMON,"ingots/shadow_steel"),

		REFINED_RADIANCE_PLATES(COMMON,"plates/refined_radiance"),
		REFINED_RADIANCE_INGOTS(COMMON,"ingots/refined_radiance"),

		INDUSTRIAL_IRON_PLATES(COMMON,"plates/industrial_iron"),
		WEATHERED_IRON_PLATES(COMMON,"plates/weathered_iron"),

		CHORIUM_PLATES(COMMON,"plates/chorium"),
		CHORIUM_INGOTS(COMMON,"ingots/chorium"),

		SHAFTS(CREATE),
		COGWHEELS(CREATE),
		LARGE_COGWHEELS(CREATE),
		GEARBOXES(CREATE),
		MIXERS(CREATE),
		PRESSES(CREATE),
		DEPOTS(CREATE),
		CHAIN_DRIVES(CREATE),
		CHAIN_GEARSHIFTS(CREATE),
		CONFIGURABLE_GEARBOXES(CREATE),
		CHAIN_CONVEYORS(CREATE),
		GEARSHIFTS(CREATE),
		CLUTCHES(CREATE),
		AUTO_CLUTCHES(CREATE),
		DEPLOYERS(CREATE),
		STORAGE_INTERFACES(CREATE),
		ENCASED_FANS(CREATE),
		HARVESTERS(CREATE),
		SAWS(CREATE),
		DRILLS(CREATE),
		PLOUGHS(CREATE),
		ROLLERS(CREATE),
		FLUID_PIPES(CREATE),
		SMART_FLUID_PIPES(CREATE),
		MECHANICAL_PUMPS(CREATE),
		FLUID_TANKS(CREATE),
		STEAM_ENGINES(CREATE),
		ITEM_DRAINS(CREATE),
		FLUID_VALVES(CREATE),
		HOSE_PULLEYS(CREATE),
		PORTABLE_FLUID_INTERFACES(CREATE),
		STEAM_WHISTLES(CREATE),
		SPOUTS(CREATE),
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
