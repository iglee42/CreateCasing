package fr.iglee42.createcasing.registries.data;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags.AllItemTags;
import com.simibubi.create.foundation.data.TagGen.CreateTagsProvider;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.registries.EncasedTags;
import fr.iglee42.createcasing.sets.casings.CasingSets;
import fr.iglee42.createcasing.sets.fluids.FluidSets;
import fr.iglee42.createcasing.sets.transmissions.TransmissionSets;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;

public class EncasedRegistrateTags {

	public static void addGenerators() {
		REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, EncasedRegistrateTags::genBlockTags);
		REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, EncasedRegistrateTags::genItemTags);
	}

	private static void genBlockTags(RegistrateTagsProvider<Block> provIn) {
		CreateTagsProvider<Block> prov = new CreateTagsProvider<>(provIn, Block::builtInRegistryHolder);
		forEachRecasableBlock((blockTag, itemTag, block) -> {
			if (block != null && !isEncasedGenerated(block))
				prov.tag(blockTag.tag).add(block);
		});
	}

	private static void genItemTags(RegistrateTagsProvider<Item> provIn) {
		CreateTagsProvider<Item> prov = new CreateTagsProvider<>(provIn, Item::builtInRegistryHolder);
		forEachRecasableBlock((blockTag, itemTag, block) -> {
			if (block != null && !isEncasedGenerated(block) && block.asItem() != Items.AIR)
				prov.tag(itemTag.tag).add(block.asItem());
		});
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

	private static boolean isEncasedGenerated(Block block) {
		return CreateCasing.MODID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace());
	}

	private static void forEachRecasableBlock(TaggedBlockConsumer consumer) {
		CasingSets.getSets().forEach(set -> {
			consumer.accept(EncasedTags.EBlockTags.GEARBOXES, EncasedTags.EItemTags.GEARBOXES, set.getGearbox());
			consumer.accept(EncasedTags.EBlockTags.MIXERS, EncasedTags.EItemTags.MIXERS, set.getMixer());
			consumer.accept(EncasedTags.EBlockTags.PRESSES, EncasedTags.EItemTags.PRESSES, set.getPress());
			consumer.accept(EncasedTags.EBlockTags.DEPOTS, EncasedTags.EItemTags.DEPOTS, set.getDepot());
			consumer.accept(EncasedTags.EBlockTags.CHAIN_DRIVES, EncasedTags.EItemTags.CHAIN_DRIVES, set.getChainDrive());
			consumer.accept(EncasedTags.EBlockTags.CHAIN_GEARSHIFTS, EncasedTags.EItemTags.CHAIN_GEARSHIFTS, set.getChainGearshift());
			consumer.accept(EncasedTags.EBlockTags.CONFIGURABLE_GEARBOXES, EncasedTags.EItemTags.CONFIGURABLE_GEARBOXES, set.getConfigurableGearbox());
			consumer.accept(EncasedTags.EBlockTags.CHAIN_CONVEYORS, EncasedTags.EItemTags.CHAIN_CONVEYORS, set.getChainConveyor());
			consumer.accept(EncasedTags.EBlockTags.GEARSHIFTS, EncasedTags.EItemTags.GEARSHIFTS, set.getGearshift());
			consumer.accept(EncasedTags.EBlockTags.CLUTCHES, EncasedTags.EItemTags.CLUTCHES, set.getClutch());
			consumer.accept(EncasedTags.EBlockTags.AUTO_CLUTCHES, EncasedTags.EItemTags.AUTO_CLUTCHES, set.getAutoClutch());
			consumer.accept(EncasedTags.EBlockTags.DEPLOYERS, EncasedTags.EItemTags.DEPLOYERS, set.getDeployer());
			consumer.accept(EncasedTags.EBlockTags.STORAGE_INTERFACES, EncasedTags.EItemTags.STORAGE_INTERFACES, set.getStorageInterface());
			consumer.accept(EncasedTags.EBlockTags.ENCASED_FANS, EncasedTags.EItemTags.ENCASED_FANS, set.getEncasedFan());
			consumer.accept(EncasedTags.EBlockTags.HARVESTERS, EncasedTags.EItemTags.HARVESTERS, set.getHarvester());
			consumer.accept(EncasedTags.EBlockTags.SAWS, EncasedTags.EItemTags.SAWS, set.getSaw());
			consumer.accept(EncasedTags.EBlockTags.DRILLS, EncasedTags.EItemTags.DRILLS, set.getDrill());
			consumer.accept(EncasedTags.EBlockTags.PLOUGHS, EncasedTags.EItemTags.PLOUGHS, set.getPlough());
			consumer.accept(EncasedTags.EBlockTags.ROLLERS, EncasedTags.EItemTags.ROLLERS, set.getRoller());
		});

		TransmissionSets.getSets().forEach(set -> {
			consumer.accept(EncasedTags.EBlockTags.SHAFTS, EncasedTags.EItemTags.SHAFTS, set.getShaft());
			consumer.accept(EncasedTags.EBlockTags.COGWHEELS, EncasedTags.EItemTags.COGWHEELS, set.getCogwheel());
			consumer.accept(EncasedTags.EBlockTags.LARGE_COGWHEELS, EncasedTags.EItemTags.LARGE_COGWHEELS, set.getLargeCogwheel());
		});

		FluidSets.getSets().forEach(set -> {
			consumer.accept(EncasedTags.EBlockTags.FLUID_PIPES, EncasedTags.EItemTags.FLUID_PIPES, set.getFluidPipe());
			consumer.accept(EncasedTags.EBlockTags.SMART_FLUID_PIPES, EncasedTags.EItemTags.SMART_FLUID_PIPES, set.getSmartFluidPipe());
			consumer.accept(EncasedTags.EBlockTags.MECHANICAL_PUMPS, EncasedTags.EItemTags.MECHANICAL_PUMPS, set.getPump());
			consumer.accept(EncasedTags.EBlockTags.FLUID_TANKS, EncasedTags.EItemTags.FLUID_TANKS, set.getFluidTank());
			consumer.accept(EncasedTags.EBlockTags.STEAM_ENGINES, EncasedTags.EItemTags.STEAM_ENGINES, set.getSteamEngine());
			consumer.accept(EncasedTags.EBlockTags.ITEM_DRAINS, EncasedTags.EItemTags.ITEM_DRAINS, set.getItemDrain());
			consumer.accept(EncasedTags.EBlockTags.FLUID_VALVES, EncasedTags.EItemTags.FLUID_VALVES, set.getFluidValve());
			consumer.accept(EncasedTags.EBlockTags.HOSE_PULLEYS, EncasedTags.EItemTags.HOSE_PULLEYS, set.getHosePulley());
			consumer.accept(EncasedTags.EBlockTags.PORTABLE_FLUID_INTERFACES, EncasedTags.EItemTags.PORTABLE_FLUID_INTERFACES, set.getPortableFluidInterface());
			consumer.accept(EncasedTags.EBlockTags.STEAM_WHISTLES, EncasedTags.EItemTags.STEAM_WHISTLES, set.getWhistle());
			consumer.accept(EncasedTags.EBlockTags.SPOUTS, EncasedTags.EItemTags.SPOUTS, set.getSpout());
		});
	}

	@FunctionalInterface
	private interface TaggedBlockConsumer {
		void accept(EncasedTags.EBlockTags blockTag, EncasedTags.EItemTags itemTag, Block block);
	}

}
