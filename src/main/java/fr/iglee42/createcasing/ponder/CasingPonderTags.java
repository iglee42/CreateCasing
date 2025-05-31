package fr.iglee42.createcasing.ponder;

import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import net.createmod.catnip.registry.RegisteredObjectsHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class CasingPonderTags {


	public static final ResourceLocation ENCASED_BLOCKS = loc("encased_blocks");


	private static ResourceLocation loc(String id) {
		return CreateCasing.asResource(id);
	}


	public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
		PonderTagRegistrationHelper<RegistryEntry<?,?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);
		PonderTagRegistrationHelper<ItemLike> itemHelper = helper.withKeyFunction(
				RegisteredObjectsHelper::getKeyOrThrow);

		helper.registerTag(ENCASED_BLOCKS).item(EncasedBlocks.BRASS_GEARBOX.get(),true,false)
				.title("Create Encased")
				.description("Components added by Create Encased")
				.addToIndex()
				.register();


		HELPER.addToTag(AllCreatePonderTags.CREATIVE)
						.add(EncasedBlocks.CREATIVE_COGWHEEL);

		List<ItemProviderEntry<?,?>> entries = List.of(
				EncasedBlocks.CREATIVE_COGWHEEL,
				EncasedBlocks.BRASS_GEARBOX, EncasedBlocks.COPPER_GEARBOX, EncasedBlocks.RAILWAY_GEARBOX, EncasedBlocks.INDUSTRIAL_IRON_GEARBOX, EncasedBlocks.CREATIVE_GEARBOX, EncasedBlocks.WEATHERED_IRON_GEARBOX, EncasedBlocks.REFINED_RADIANCE_GEARBOX, EncasedBlocks.SHADOW_STEEL_GEARBOX,
				EncasedBlocks.BRASS_MIXER, EncasedBlocks.COPPER_MIXER, EncasedBlocks.RAILWAY_MIXER, EncasedBlocks.INDUSTRIAL_IRON_MIXER, EncasedBlocks.CREATIVE_MIXER, EncasedBlocks.WEATHERED_IRON_MIXER, EncasedBlocks.REFINED_RADIANCE_MIXER, EncasedBlocks.SHADOW_STEEL_MIXER,
				EncasedBlocks.BRASS_PRESS, EncasedBlocks.COPPER_PRESS, EncasedBlocks.RAILWAY_PRESS, EncasedBlocks.INDUSTRIAL_IRON_PRESS, EncasedBlocks.CREATIVE_PRESS, EncasedBlocks.WEATHERED_IRON_PRESS, EncasedBlocks.REFINED_RADIANCE_PRESS, EncasedBlocks.SHADOW_STEEL_PRESS,
				EncasedBlocks.BRASS_DEPOT, EncasedBlocks.COPPER_DEPOT, EncasedBlocks.RAILWAY_DEPOT, EncasedBlocks.INDUSTRIAL_IRON_DEPOT, EncasedBlocks.CREATIVE_DEPOT, EncasedBlocks.WEATHERED_IRON_DEPOT, EncasedBlocks.REFINED_RADIANCE_DEPOT, EncasedBlocks.SHADOW_STEEL_DEPOT,
				EncasedBlocks.BRASS_CHAIN_DRIVE, EncasedBlocks.COPPER_CHAIN_DRIVE, EncasedBlocks.RAILWAY_CHAIN_DRIVE, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE, EncasedBlocks.CREATIVE_CHAIN_DRIVE, EncasedBlocks.WEATHERED_IRON_CHAIN_DRIVE, EncasedBlocks.REFINED_RADIANCE_CHAIN_DRIVE, EncasedBlocks.SHADOW_STEEL_CHAIN_DRIVE,
				EncasedBlocks.BRASS_CHAIN_GEARSHIFT, EncasedBlocks.COPPER_CHAIN_GEARSHIFT, EncasedBlocks.RAILWAY_CHAIN_GEARSHIFT, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_GEARSHIFT, EncasedBlocks.CREATIVE_CHAIN_GEARSHIFT, EncasedBlocks.WEATHERED_IRON_CHAIN_GEARSHIFT, EncasedBlocks.REFINED_RADIANCE_CHAIN_GEARSHIFT, EncasedBlocks.SHADOW_STEEL_CHAIN_GEARSHIFT,
				EncasedBlocks.ANDESITE_CONFIGURABLE_GEARBOX, EncasedBlocks.BRASS_CONFIGURABLE_GEARBOX, EncasedBlocks.COPPER_CONFIGURABLE_GEARBOX, EncasedBlocks.RAILWAY_CONFIGURABLE_GEARBOX, EncasedBlocks.INDUSTRIAL_IRON_CONFIGURABLE_GEARBOX, EncasedBlocks.CREATIVE_CONFIGURABLE_GEARBOX, EncasedBlocks.WEATHERED_IRON_CONFIGURABLE_GEARBOX, EncasedBlocks.REFINED_RADIANCE_CONFIGURABLE_GEARBOX, EncasedBlocks.SHADOW_STEEL_CONFIGURABLE_GEARBOX,
				EncasedBlocks.BRASS_CHAIN_CONVEYOR, EncasedBlocks.COPPER_CHAIN_CONVEYOR, EncasedBlocks.RAILWAY_CHAIN_CONVEYOR, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_CONVEYOR, EncasedBlocks.CREATIVE_CHAIN_CONVEYOR, EncasedBlocks.WEATHERED_IRON_CHAIN_CONVEYOR, EncasedBlocks.REFINED_RADIANCE_CHAIN_CONVEYOR, EncasedBlocks.SHADOW_STEEL_CHAIN_CONVEYOR);

		entries.forEach(e->HELPER.addToTag(ENCASED_BLOCKS).add(e));

		HELPER.addToTag(AllCreatePonderTags.KINETIC_RELAYS).add(EncasedBlocks.ANDESITE_CONFIGURABLE_GEARBOX);


	}

}
