package fr.iglee42.createcasing.ponder;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;
import com.simibubi.create.infrastructure.ponder.scenes.BeltScenes;
import com.simibubi.create.infrastructure.ponder.scenes.ChainDriveScenes;
import com.simibubi.create.infrastructure.ponder.scenes.KineticsScenes;
import com.simibubi.create.infrastructure.ponder.scenes.ProcessingScenes;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.registries.ModBlocks;
import fr.iglee42.createcasing.registries.ModItems;

public class PonderIndex {

	static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(CreateCasing.MODID);


	public static void register() {
		// Register storyboards here
		// (!) Added entries require re-launch
		// (!) Modifications inside storyboard methods only require re-opening the ui


		HELPER.forComponents(ModBlocks.BRASS_GEARBOX,ModBlocks.COPPER_GEARBOX,ModBlocks.RAILWAY_GEARBOX,ModBlocks.INDUSTRIAL_IRON_GEARBOX,ModBlocks.CREATIVE_GEARBOX, ModItems.VERTICAL_BRASS_GEARBOX,ModItems.VERTICAL_COPPER_GEARBOX,ModItems.VERTICAL_RAILWAY_GEARBOX,ModItems.VERTICAL_INDUSTRIAL_IRON_GEARBOX,ModItems.VERTICAL_CREATIVE_GEARBOX)
				.addStoryBoard(Create.asResource("gearbox"), KineticsScenes::gearbox, AllPonderTags.KINETIC_RELAYS);

		HELPER.forComponents(ModBlocks.BRASS_MIXER,ModBlocks.COPPER_MIXER,ModBlocks.RAILWAY_MIXER,ModBlocks.INDUSTRIAL_IRON_MIXER,ModBlocks.CREATIVE_MIXER).addStoryBoard(Create.asResource("mechanical_mixer/mixing"), ProcessingScenes::mixing);
		HELPER.forComponents(ModBlocks.BRASS_PRESS,ModBlocks.COPPER_PRESS,ModBlocks.RAILWAY_PRESS,ModBlocks.INDUSTRIAL_IRON_PRESS,ModBlocks.CREATIVE_PRESS)
				.addStoryBoard(Create.asResource("mechanical_press/pressing"), ProcessingScenes::pressing)
				.addStoryBoard(Create.asResource("mechanical_press/compacting"), ProcessingScenes::compacting);

		HELPER.forComponents(ModBlocks.CREATIVE_COGWHEEL).addStoryBoard("creative_cogwheel",CustomPonderScenes::creativeCogwheel);

		HELPER.forComponents(ModBlocks.BRASS_DEPOT,ModBlocks.COPPER_DEPOT,ModBlocks.RAILWAY_DEPOT,ModBlocks.INDUSTRIAL_IRON_DEPOT,ModBlocks.CREATIVE_DEPOT).addStoryBoard("depot", BeltScenes::depot);


		HELPER.forComponents(ModBlocks.BRASS_CHAIN_DRIVE,ModBlocks.COPPER_CHAIN_DRIVE,ModBlocks.RAILWAY_CHAIN_DRIVE,ModBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE,ModBlocks.CREATIVE_CHAIN_DRIVE).addStoryBoard(Create.asResource("chain_drive/relay"), ChainDriveScenes::chainDriveAsRelay);
		HELPER.forComponents(ModBlocks.BRASS_CHAIN_DRIVE,ModBlocks.COPPER_CHAIN_DRIVE,ModBlocks.RAILWAY_CHAIN_DRIVE,ModBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE,ModBlocks.CREATIVE_CHAIN_DRIVE,ModBlocks.BRASS_CHAIN_GEARSHIFT,ModBlocks.COPPER_CHAIN_GEARSHIFT,ModBlocks.RAILWAY_CHAIN_GEARSHIFT,ModBlocks.INDUSTRIAL_IRON_CHAIN_GEARSHIFT,ModBlocks.CREATIVE_CHAIN_GEARSHIFT)
				.addStoryBoard(Create.asResource("chain_drive/gearshift"), ChainDriveScenes::adjustableChainGearshift);


	}


}
