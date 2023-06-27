package fr.iglee42.createcasing.ponder;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.Create;
import com.simibubi.create.content.trains.track.TrackBlock;
import com.simibubi.create.content.trains.track.TrackMaterial;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;
import com.simibubi.create.infrastructure.ponder.DebugScenes;
import com.simibubi.create.infrastructure.ponder.scenes.*;
import com.simibubi.create.infrastructure.ponder.scenes.fluid.*;
import com.simibubi.create.infrastructure.ponder.scenes.trains.*;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.registries.ModBlocks;
import fr.iglee42.createcasing.registries.ModItems;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.injection.At;

public class PonderIndex {

	static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(CreateCasing.MODID);


	public static void register() {
		// Register storyboards here
		// (!) Added entries require re-launch
		// (!) Modifications inside storyboard methods only require re-opening the ui


		HELPER.forComponents(ModBlocks.BRASS_GEARBOX,ModBlocks.COPPER_GEARBOX,ModBlocks.RAILWAY_GEARBOX, ModItems.VERTICAL_BRASS_GEARBOX,ModItems.VERTICAL_COPPER_GEARBOX,ModItems.VERTICAL_RAILWAY_GEARBOX)
				.addStoryBoard("gearbox", KineticsScenes::gearbox, AllPonderTags.KINETIC_RELAYS);

		HELPER.forComponents(ModBlocks.BRASS_MIXER,ModBlocks.COPPER_MIXER,ModBlocks.RAILWAY_MIXER).addStoryBoard("mechanical_mixer/mixing", ProcessingScenes::mixing);
		HELPER.forComponents(ModBlocks.BRASS_PRESS,ModBlocks.COPPER_PRESS,ModBlocks.RAILWAY_PRESS)
				.addStoryBoard("mechanical_press/pressing", ProcessingScenes::pressing)
				.addStoryBoard("mechanical_press/compacting", ProcessingScenes::compacting);


	}


}
