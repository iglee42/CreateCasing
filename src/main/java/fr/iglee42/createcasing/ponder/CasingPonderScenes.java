package fr.iglee42.createcasing.ponder;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import com.simibubi.create.infrastructure.ponder.scenes.BeltScenes;
import com.simibubi.create.infrastructure.ponder.scenes.ChainDriveScenes;
import com.simibubi.create.infrastructure.ponder.scenes.KineticsScenes;
import com.simibubi.create.infrastructure.ponder.scenes.ProcessingScenes;
import com.simibubi.create.infrastructure.ponder.scenes.highLogistics.FrogAndConveyorScenes;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.registries.ModBlocks;
import fr.iglee42.createcasing.registries.ModItems;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class CasingPonderScenes {

	public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {

		PonderSceneRegistrationHelper<ItemProviderEntry<?,?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);


		HELPER.forComponents(ModBlocks.REFINED_RADIANCE_GEARBOX,ModBlocks.SHADOW_STEEL_GEARBOX,ModBlocks.WEATHERED_IRON_GEARBOX,ModBlocks.BRASS_GEARBOX,ModBlocks.COPPER_GEARBOX,ModBlocks.RAILWAY_GEARBOX,ModBlocks.INDUSTRIAL_IRON_GEARBOX,ModBlocks.CREATIVE_GEARBOX, ModItems.VERTICAL_BRASS_GEARBOX,ModItems.VERTICAL_COPPER_GEARBOX,ModItems.VERTICAL_RAILWAY_GEARBOX,ModItems.VERTICAL_INDUSTRIAL_IRON_GEARBOX,ModItems.VERTICAL_CREATIVE_GEARBOX)
				.addStoryBoard(Create.asResource("gearbox"), KineticsScenes::gearbox, AllCreatePonderTags.KINETIC_RELAYS);

		HELPER.forComponents(ModBlocks.REFINED_RADIANCE_MIXER,ModBlocks.SHADOW_STEEL_MIXER,ModBlocks.WEATHERED_IRON_MIXER,ModBlocks.BRASS_MIXER,ModBlocks.COPPER_MIXER,ModBlocks.RAILWAY_MIXER,ModBlocks.INDUSTRIAL_IRON_MIXER,ModBlocks.CREATIVE_MIXER).addStoryBoard(Create.asResource("mechanical_mixer/mixing"), ProcessingScenes::mixing);
		HELPER.forComponents(ModBlocks.REFINED_RADIANCE_PRESS,ModBlocks.SHADOW_STEEL_PRESS,ModBlocks.WEATHERED_IRON_PRESS,ModBlocks.BRASS_PRESS,ModBlocks.COPPER_PRESS,ModBlocks.RAILWAY_PRESS,ModBlocks.INDUSTRIAL_IRON_PRESS,ModBlocks.CREATIVE_PRESS)
				.addStoryBoard(Create.asResource("mechanical_press/pressing"), ProcessingScenes::pressing)
				.addStoryBoard(Create.asResource("mechanical_press/compacting"), ProcessingScenes::compacting);

		HELPER.forComponents(ModBlocks.CREATIVE_COGWHEEL).addStoryBoard("creative_cogwheel",CustomPonderScenes::creativeCogwheel,AllCreatePonderTags.KINETIC_SOURCES);

		HELPER.forComponents(ModBlocks.REFINED_RADIANCE_DEPOT,ModBlocks.SHADOW_STEEL_DEPOT,ModBlocks.WEATHERED_IRON_DEPOT,ModBlocks.BRASS_DEPOT,ModBlocks.COPPER_DEPOT,ModBlocks.RAILWAY_DEPOT,ModBlocks.INDUSTRIAL_IRON_DEPOT,ModBlocks.CREATIVE_DEPOT).addStoryBoard("depot", BeltScenes::depot);


		HELPER.forComponents(ModBlocks.REFINED_RADIANCE_CHAIN_DRIVE,ModBlocks.SHADOW_STEEL_CHAIN_DRIVE,ModBlocks.WEATHERED_IRON_CHAIN_DRIVE,ModBlocks.BRASS_CHAIN_DRIVE,ModBlocks.COPPER_CHAIN_DRIVE,ModBlocks.RAILWAY_CHAIN_DRIVE,ModBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE,ModBlocks.CREATIVE_CHAIN_DRIVE).addStoryBoard(Create.asResource("chain_drive/relay"), ChainDriveScenes::chainDriveAsRelay);
		HELPER.forComponents(ModBlocks.REFINED_RADIANCE_CHAIN_GEARSHIFT,ModBlocks.SHADOW_STEEL_CHAIN_GEARSHIFT,ModBlocks.REFINED_RADIANCE_CHAIN_DRIVE,ModBlocks.SHADOW_STEEL_CHAIN_DRIVE,ModBlocks.WEATHERED_IRON_CHAIN_DRIVE,ModBlocks.WEATHERED_IRON_CHAIN_GEARSHIFT,ModBlocks.BRASS_CHAIN_DRIVE,ModBlocks.COPPER_CHAIN_DRIVE,ModBlocks.RAILWAY_CHAIN_DRIVE,ModBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE,ModBlocks.CREATIVE_CHAIN_DRIVE,ModBlocks.BRASS_CHAIN_GEARSHIFT,ModBlocks.COPPER_CHAIN_GEARSHIFT,ModBlocks.RAILWAY_CHAIN_GEARSHIFT,ModBlocks.INDUSTRIAL_IRON_CHAIN_GEARSHIFT,ModBlocks.CREATIVE_CHAIN_GEARSHIFT)
				.addStoryBoard(Create.asResource("chain_drive/gearshift"), ChainDriveScenes::adjustableChainGearshift);

		HELPER.forComponents(ModBlocks.REFINED_RADIANCE_CONFIGURABLE_GEARBOX,ModBlocks.SHADOW_STEEL_CONFIGURABLE_GEARBOX,ModBlocks.WEATHERED_IRON_CONFIGURABLE_GEARBOX,ModBlocks.ANDESITE_CONFIGURABLE_GEARBOX,ModBlocks.BRASS_CONFIGURABLE_GEARBOX,ModBlocks.COPPER_CONFIGURABLE_GEARBOX,ModBlocks.RAILWAY_CONFIGURABLE_GEARBOX,ModBlocks.INDUSTRIAL_IRON_CONFIGURABLE_GEARBOX,ModBlocks.CREATIVE_CONFIGURABLE_GEARBOX)
				.addStoryBoard(CreateCasing.asResource("configurable_gearbox"), CustomPonderScenes::configurableGearbox);

		HELPER.forComponents(ModBlocks.REFINED_RADIANCE_CHAIN_CONVEYOR,ModBlocks.SHADOW_STEEL_CHAIN_CONVEYOR,ModBlocks.WEATHERED_IRON_CHAIN_CONVEYOR,ModBlocks.BRASS_CHAIN_CONVEYOR,ModBlocks.COPPER_CHAIN_CONVEYOR,ModBlocks.RAILWAY_CHAIN_CONVEYOR,ModBlocks.INDUSTRIAL_IRON_CHAIN_CONVEYOR,ModBlocks.CREATIVE_CHAIN_CONVEYOR)
				.addStoryBoard(Create.asResource("high_logistics/chain_conveyor"), FrogAndConveyorScenes::conveyor);
	}


}
