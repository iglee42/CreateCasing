package fr.iglee42.createcasing.registries.data;

import java.util.ArrayList;
import java.util.List;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.AllTags.AllBlockTags;
import com.simibubi.create.AllTags.AllEntityTags;
import com.simibubi.create.AllTags.AllFluidTags;
import com.simibubi.create.AllTags.AllItemTags;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.TagGen;
import com.simibubi.create.foundation.data.TagGen.CreateTagsProvider;
import com.simibubi.create.foundation.data.recipe.Mods;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;

import fr.iglee42.createcasing.registries.EncasedTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import net.neoforged.neoforge.common.Tags;

import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;

public class EncasedRegistrateTags {

	public static void addGenerators() {
		REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, EncasedRegistrateTags::genBlockTags);
		REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, EncasedRegistrateTags::genItemTags);
	}

	private static void genBlockTags(RegistrateTagsProvider<Block> provIn) {
		CreateTagsProvider<Block> prov = new CreateTagsProvider<>(provIn, Block::builtInRegistryHolder);


	}

	private static void genItemTags(RegistrateTagsProvider<Item> provIn) {
		CreateTagsProvider<Item> prov = new CreateTagsProvider<>(provIn, Item::builtInRegistryHolder);
		prov.tag(AllItemTags.PLATES.tag)
				.addOptionalTags(
						EncasedTags.EItemTags.ANDESITE_PLATES.tag,
						EncasedTags.EItemTags.SHADOW_STEEL_PLATES.tag,
						EncasedTags.EItemTags.REFINED_RADIANCE_PLATES.tag,
						EncasedTags.EItemTags.INDUSTRIAL_IRON_PLATES.tag,
						EncasedTags.EItemTags.WEATHERED_IRON_PLATES.tag,
						EncasedTags.EItemTags.CHORIUM_PLATES.tag
				);

		prov.tag(EncasedTags.EItemTags.ANDESITE_ALLOY_INGOTS.tag)
				.add(AllItems.ANDESITE_ALLOY.asItem());

		prov.tag(EncasedTags.EItemTags.SHADOW_STEEL_INGOTS.tag)
				.add(AllItems.SHADOW_STEEL.asItem());

		prov.tag(EncasedTags.EItemTags.REFINED_RADIANCE_INGOTS.tag)
				.add(AllItems.REFINED_RADIANCE.asItem());

		prov.tag(AllItemTags.CREATE_INGOTS.tag)
						.add(AllItems.SHADOW_STEEL.asItem(),
								AllItems.REFINED_RADIANCE.asItem());

		prov.tag(Tags.Items.INGOTS)
				.addOptionalTags(
						EncasedTags.EItemTags.ANDESITE_ALLOY_INGOTS.tag,
						EncasedTags.EItemTags.REFINED_RADIANCE_INGOTS.tag,
						EncasedTags.EItemTags.SHADOW_STEEL_INGOTS.tag,
						EncasedTags.EItemTags.CHORIUM_INGOTS.tag
				);

	}

}
