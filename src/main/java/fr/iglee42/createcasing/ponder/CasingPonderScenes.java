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
	}


}
