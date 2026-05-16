package fr.iglee42.createcasing.ponder;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import com.simibubi.create.infrastructure.ponder.scenes.*;
import com.simibubi.create.infrastructure.ponder.scenes.highLogistics.FrogAndConveyorScenes;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import fr.iglee42.createcasing.registries.EncasedItems;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class CasingPonderScenes {

	public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {

		PonderSceneRegistrationHelper<ItemLike> HELPER = helper.withKeyFunction(like->BuiltInRegistries.ITEM.getKey(like.asItem()));


		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateGearbox).map(CasingSet::getGearbox).toList())
				.addStoryBoard(Create.asResource("gearbox"), KineticsScenes::gearbox, AllCreatePonderTags.KINETIC_RELAYS);
		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateGearbox).map(CasingSet::getVerticalGearboxItem).toList())
				.addStoryBoard(Create.asResource("gearbox"), KineticsScenes::gearbox, AllCreatePonderTags.KINETIC_RELAYS);

		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateMixer).map(CasingSet::getMixer).toList()).addStoryBoard(Create.asResource("mechanical_mixer/mixing"), ProcessingScenes::mixing);
		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGeneratePress).map(CasingSet::getPress).toList())
				.addStoryBoard(Create.asResource("mechanical_press/pressing"), ProcessingScenes::pressing)
				.addStoryBoard(Create.asResource("mechanical_press/compacting"), ProcessingScenes::compacting);

		HELPER.forComponents(EncasedBlocks.CREATIVE_COGWHEEL).addStoryBoard("creative_cogwheel",CustomPonderScenes::creativeCogwheel,AllCreatePonderTags.KINETIC_SOURCES);

		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateDepot).map(CasingSet::getDepot).toList()).addStoryBoard("depot", BeltScenes::depot);


		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateChainDrive).map(CasingSet::getChainDrive).toList()).addStoryBoard(Create.asResource("chain_drive/relay"), ChainDriveScenes::chainDriveAsRelay);
		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateChainGearshift).map(CasingSet::getChainGearshift).toList())
				.addStoryBoard(Create.asResource("chain_drive/gearshift"), ChainDriveScenes::adjustableChainGearshift);
		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateChainDrive).map(CasingSet::getChainDrive).toList())
				.addStoryBoard(Create.asResource("chain_drive/gearshift"), ChainDriveScenes::adjustableChainGearshift);

		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateConfigurableGearbox).map(CasingSet::getConfigurableGearbox).toList())
				.addStoryBoard(CreateCasing.asResource("configurable_gearbox"), CustomPonderScenes::configurableGearbox);

		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateConfigurableGearbox).map(CasingSet::getChainConveyor).toList())
				.addStoryBoard(Create.asResource("high_logistics/chain_conveyor"), FrogAndConveyorScenes::conveyor);

		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateClutch).map(CasingSet::getClutch).toList())
				.addStoryBoard(Create.asResource("clutch"), KineticsScenes::clutch,AllCreatePonderTags.KINETIC_RELAYS);

		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateGearshift).map(CasingSet::getGearshift).toList())
				.addStoryBoard(Create.asResource("gearshift"), KineticsScenes::gearshift,AllCreatePonderTags.KINETIC_RELAYS);

		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateDeployer).map(CasingSet::getDeployer).toList())
				.addStoryBoard(Create.asResource("deployer/filter"), DeployerScenes::filter, AllCreatePonderTags.KINETIC_APPLIANCES)
				.addStoryBoard(Create.asResource("deployer/modes"), DeployerScenes::modes)
				.addStoryBoard(Create.asResource("deployer/processing"), DeployerScenes::processing)
				.addStoryBoard(Create.asResource("deployer/redstone"), DeployerScenes::redstone)
				.addStoryBoard(Create.asResource("deployer/contraption"), DeployerScenes::contraption, AllCreatePonderTags.CONTRAPTION_ACTOR);

		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateStorageInterface).map(CasingSet::getStorageInterface).toList())
				.addStoryBoard(Create.asResource("portable_interface/transfer"), MovementActorScenes::psiTransfer, AllCreatePonderTags.CONTRAPTION_ACTOR)
				.addStoryBoard(Create.asResource("portable_interface/redstone"), MovementActorScenes::psiRedstone);

		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateEncasedFan).map(CasingSet::getEncasedFan).toList())
				.addStoryBoard(Create.asResource("fan/direction"), FanScenes::direction, AllCreatePonderTags.KINETIC_APPLIANCES)
				.addStoryBoard(Create.asResource("fan/processing"), FanScenes::processing);

		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateHarvester).map(CasingSet::getHarvester).toList())
				.addStoryBoard(Create.asResource("harvester"), MovementActorScenes::harvester);
		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGeneratePlough).map(CasingSet::getPlough).toList())
				.addStoryBoard(Create.asResource("plough"), MovementActorScenes::plough);
		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateRoller).map(CasingSet::getRoller).toList())
				.addStoryBoard(Create.asResource("mechanical_roller/clear_and_pave"), RollerScenes::clearAndPave)
				.addStoryBoard(Create.asResource("mechanical_roller/fill"), RollerScenes::fill);
		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateSaw).map(CasingSet::getSaw).toList())
				.addStoryBoard(Create.asResource("mechanical_saw/processing"), MechanicalSawScenes::processing, AllCreatePonderTags.KINETIC_APPLIANCES)
				.addStoryBoard(Create.asResource("mechanical_saw/breaker"), MechanicalSawScenes::treeCutting)
				.addStoryBoard(Create.asResource("mechanical_saw/contraption"), MechanicalSawScenes::contraption, AllCreatePonderTags.CONTRAPTION_ACTOR);
		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateDrill).map(CasingSet::getDrill).toList())
				.addStoryBoard(Create.asResource("mechanical_drill/breaker"), MechanicalDrillScenes::breaker, AllCreatePonderTags.KINETIC_APPLIANCES)
				.addStoryBoard(Create.asResource("mechanical_drill/contraption"), MechanicalDrillScenes::contraption,
						AllCreatePonderTags.CONTRAPTION_ACTOR);

		HELPER.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateAutoClutch).map(CasingSet::getAutoClutch).toList())
				.addStoryBoard(CreateCasing.asResource("auto_clutch"), CustomPonderScenes::autoClutch);
	}


}
