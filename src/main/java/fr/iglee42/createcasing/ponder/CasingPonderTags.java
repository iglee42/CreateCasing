package fr.iglee42.createcasing.ponder;

import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.registries.ModBlocks;
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

		helper.registerTag(ENCASED_BLOCKS).item(ModBlocks.BRASS_GEARBOX.get(),true,false)
				.title("Create Encased")
				.description("Components added by Create Encased")
				.addToIndex()
				.register();


		HELPER.addToTag(AllCreatePonderTags.CREATIVE)
						.add(ModBlocks.CREATIVE_COGWHEEL);

		List<ItemProviderEntry<?,?>> entries = List.of(
				ModBlocks.CREATIVE_COGWHEEL,
				ModBlocks.BRASS_GEARBOX,ModBlocks.COPPER_GEARBOX,ModBlocks.RAILWAY_GEARBOX,ModBlocks.INDUSTRIAL_IRON_GEARBOX,ModBlocks.CREATIVE_GEARBOX,ModBlocks.WEATHERED_IRON_GEARBOX,ModBlocks.REFINED_RADIANCE_GEARBOX,ModBlocks.SHADOW_STEEL_GEARBOX,
				ModBlocks.BRASS_MIXER,ModBlocks.COPPER_MIXER,ModBlocks.RAILWAY_MIXER,ModBlocks.INDUSTRIAL_IRON_MIXER,ModBlocks.CREATIVE_MIXER,ModBlocks.WEATHERED_IRON_MIXER,ModBlocks.REFINED_RADIANCE_MIXER,ModBlocks.SHADOW_STEEL_MIXER,
				ModBlocks.BRASS_PRESS,ModBlocks.COPPER_PRESS,ModBlocks.RAILWAY_PRESS,ModBlocks.INDUSTRIAL_IRON_PRESS,ModBlocks.CREATIVE_PRESS,ModBlocks.WEATHERED_IRON_PRESS,ModBlocks.REFINED_RADIANCE_PRESS,ModBlocks.SHADOW_STEEL_PRESS,
				ModBlocks.BRASS_DEPOT,ModBlocks.COPPER_DEPOT,ModBlocks.RAILWAY_DEPOT,ModBlocks.INDUSTRIAL_IRON_DEPOT,ModBlocks.CREATIVE_DEPOT,ModBlocks.WEATHERED_IRON_DEPOT,ModBlocks.REFINED_RADIANCE_DEPOT,ModBlocks.SHADOW_STEEL_DEPOT,
				ModBlocks.BRASS_CHAIN_DRIVE,ModBlocks.COPPER_CHAIN_DRIVE,ModBlocks.RAILWAY_CHAIN_DRIVE,ModBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE,ModBlocks.CREATIVE_CHAIN_DRIVE,ModBlocks.WEATHERED_IRON_CHAIN_DRIVE,ModBlocks.REFINED_RADIANCE_CHAIN_DRIVE,ModBlocks.SHADOW_STEEL_CHAIN_DRIVE,
				ModBlocks.BRASS_CHAIN_GEARSHIFT,ModBlocks.COPPER_CHAIN_GEARSHIFT,ModBlocks.RAILWAY_CHAIN_GEARSHIFT,ModBlocks.INDUSTRIAL_IRON_CHAIN_GEARSHIFT,ModBlocks.CREATIVE_CHAIN_GEARSHIFT,ModBlocks.WEATHERED_IRON_CHAIN_GEARSHIFT,ModBlocks.REFINED_RADIANCE_CHAIN_GEARSHIFT,ModBlocks.SHADOW_STEEL_CHAIN_GEARSHIFT,
				ModBlocks.ANDESITE_CONFIGURABLE_GEARBOX,ModBlocks.BRASS_CONFIGURABLE_GEARBOX,ModBlocks.COPPER_CONFIGURABLE_GEARBOX,ModBlocks.RAILWAY_CONFIGURABLE_GEARBOX,ModBlocks.INDUSTRIAL_IRON_CONFIGURABLE_GEARBOX,ModBlocks.CREATIVE_CONFIGURABLE_GEARBOX,ModBlocks.WEATHERED_IRON_CONFIGURABLE_GEARBOX,ModBlocks.REFINED_RADIANCE_CONFIGURABLE_GEARBOX,ModBlocks.SHADOW_STEEL_CONFIGURABLE_GEARBOX,
				ModBlocks.BRASS_CHAIN_CONVEYOR,ModBlocks.COPPER_CHAIN_CONVEYOR,ModBlocks.RAILWAY_CHAIN_CONVEYOR,ModBlocks.INDUSTRIAL_IRON_CHAIN_CONVEYOR,ModBlocks.CREATIVE_CHAIN_CONVEYOR,ModBlocks.WEATHERED_IRON_CHAIN_CONVEYOR,ModBlocks.REFINED_RADIANCE_CHAIN_CONVEYOR,ModBlocks.SHADOW_STEEL_CHAIN_CONVEYOR);

		entries.forEach(e->HELPER.addToTag(ENCASED_BLOCKS).add(e));

		HELPER.addToTag(AllCreatePonderTags.KINETIC_RELAYS).add(ModBlocks.ANDESITE_CONFIGURABLE_GEARBOX);


	}

}
