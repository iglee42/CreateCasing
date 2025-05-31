package fr.iglee42.createcasing.ponder;

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
import fr.iglee42.createcasing.registries.EncasedBlocks;
import fr.iglee42.createcasing.registries.EncasedItems;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class CasingPonderScenes {

	public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {

		PonderSceneRegistrationHelper<ItemProviderEntry<?,?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);


		HELPER.forComponents(EncasedBlocks.REFINED_RADIANCE_GEARBOX, EncasedBlocks.SHADOW_STEEL_GEARBOX, EncasedBlocks.WEATHERED_IRON_GEARBOX, EncasedBlocks.BRASS_GEARBOX, EncasedBlocks.COPPER_GEARBOX, EncasedBlocks.RAILWAY_GEARBOX, EncasedBlocks.INDUSTRIAL_IRON_GEARBOX, EncasedBlocks.CREATIVE_GEARBOX, EncasedItems.VERTICAL_BRASS_GEARBOX, EncasedItems.VERTICAL_COPPER_GEARBOX, EncasedItems.VERTICAL_RAILWAY_GEARBOX, EncasedItems.VERTICAL_INDUSTRIAL_IRON_GEARBOX, EncasedItems.VERTICAL_CREATIVE_GEARBOX)
				.addStoryBoard(Create.asResource("gearbox"), KineticsScenes::gearbox, AllCreatePonderTags.KINETIC_RELAYS);

		HELPER.forComponents(EncasedBlocks.REFINED_RADIANCE_MIXER, EncasedBlocks.SHADOW_STEEL_MIXER, EncasedBlocks.WEATHERED_IRON_MIXER, EncasedBlocks.BRASS_MIXER, EncasedBlocks.COPPER_MIXER, EncasedBlocks.RAILWAY_MIXER, EncasedBlocks.INDUSTRIAL_IRON_MIXER, EncasedBlocks.CREATIVE_MIXER).addStoryBoard(Create.asResource("mechanical_mixer/mixing"), ProcessingScenes::mixing);
		HELPER.forComponents(EncasedBlocks.REFINED_RADIANCE_PRESS, EncasedBlocks.SHADOW_STEEL_PRESS, EncasedBlocks.WEATHERED_IRON_PRESS, EncasedBlocks.BRASS_PRESS, EncasedBlocks.COPPER_PRESS, EncasedBlocks.RAILWAY_PRESS, EncasedBlocks.INDUSTRIAL_IRON_PRESS, EncasedBlocks.CREATIVE_PRESS)
				.addStoryBoard(Create.asResource("mechanical_press/pressing"), ProcessingScenes::pressing)
				.addStoryBoard(Create.asResource("mechanical_press/compacting"), ProcessingScenes::compacting);

		HELPER.forComponents(EncasedBlocks.CREATIVE_COGWHEEL).addStoryBoard("creative_cogwheel",CustomPonderScenes::creativeCogwheel,AllCreatePonderTags.KINETIC_SOURCES);

		HELPER.forComponents(EncasedBlocks.REFINED_RADIANCE_DEPOT, EncasedBlocks.SHADOW_STEEL_DEPOT, EncasedBlocks.WEATHERED_IRON_DEPOT, EncasedBlocks.BRASS_DEPOT, EncasedBlocks.COPPER_DEPOT, EncasedBlocks.RAILWAY_DEPOT, EncasedBlocks.INDUSTRIAL_IRON_DEPOT, EncasedBlocks.CREATIVE_DEPOT).addStoryBoard("depot", BeltScenes::depot);


		HELPER.forComponents(EncasedBlocks.REFINED_RADIANCE_CHAIN_DRIVE, EncasedBlocks.SHADOW_STEEL_CHAIN_DRIVE, EncasedBlocks.WEATHERED_IRON_CHAIN_DRIVE, EncasedBlocks.BRASS_CHAIN_DRIVE, EncasedBlocks.COPPER_CHAIN_DRIVE, EncasedBlocks.RAILWAY_CHAIN_DRIVE, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE, EncasedBlocks.CREATIVE_CHAIN_DRIVE).addStoryBoard(Create.asResource("chain_drive/relay"), ChainDriveScenes::chainDriveAsRelay);
		HELPER.forComponents(EncasedBlocks.REFINED_RADIANCE_CHAIN_GEARSHIFT, EncasedBlocks.SHADOW_STEEL_CHAIN_GEARSHIFT, EncasedBlocks.REFINED_RADIANCE_CHAIN_DRIVE, EncasedBlocks.SHADOW_STEEL_CHAIN_DRIVE, EncasedBlocks.WEATHERED_IRON_CHAIN_DRIVE, EncasedBlocks.WEATHERED_IRON_CHAIN_GEARSHIFT, EncasedBlocks.BRASS_CHAIN_DRIVE, EncasedBlocks.COPPER_CHAIN_DRIVE, EncasedBlocks.RAILWAY_CHAIN_DRIVE, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE, EncasedBlocks.CREATIVE_CHAIN_DRIVE, EncasedBlocks.BRASS_CHAIN_GEARSHIFT, EncasedBlocks.COPPER_CHAIN_GEARSHIFT, EncasedBlocks.RAILWAY_CHAIN_GEARSHIFT, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_GEARSHIFT, EncasedBlocks.CREATIVE_CHAIN_GEARSHIFT)
				.addStoryBoard(Create.asResource("chain_drive/gearshift"), ChainDriveScenes::adjustableChainGearshift);

		HELPER.forComponents(EncasedBlocks.REFINED_RADIANCE_CONFIGURABLE_GEARBOX, EncasedBlocks.SHADOW_STEEL_CONFIGURABLE_GEARBOX, EncasedBlocks.WEATHERED_IRON_CONFIGURABLE_GEARBOX, EncasedBlocks.ANDESITE_CONFIGURABLE_GEARBOX, EncasedBlocks.BRASS_CONFIGURABLE_GEARBOX, EncasedBlocks.COPPER_CONFIGURABLE_GEARBOX, EncasedBlocks.RAILWAY_CONFIGURABLE_GEARBOX, EncasedBlocks.INDUSTRIAL_IRON_CONFIGURABLE_GEARBOX, EncasedBlocks.CREATIVE_CONFIGURABLE_GEARBOX)
				.addStoryBoard(CreateCasing.asResource("configurable_gearbox"), CustomPonderScenes::configurableGearbox);

		HELPER.forComponents(EncasedBlocks.REFINED_RADIANCE_CHAIN_CONVEYOR, EncasedBlocks.SHADOW_STEEL_CHAIN_CONVEYOR, EncasedBlocks.WEATHERED_IRON_CHAIN_CONVEYOR, EncasedBlocks.BRASS_CHAIN_CONVEYOR, EncasedBlocks.COPPER_CHAIN_CONVEYOR, EncasedBlocks.RAILWAY_CHAIN_CONVEYOR, EncasedBlocks.INDUSTRIAL_IRON_CHAIN_CONVEYOR, EncasedBlocks.CREATIVE_CHAIN_CONVEYOR)
				.addStoryBoard(Create.asResource("high_logistics/chain_conveyor"), FrogAndConveyorScenes::conveyor);
	}


}
