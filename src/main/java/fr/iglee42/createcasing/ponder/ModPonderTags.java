package fr.iglee42.createcasing.ponder;

import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.PonderTag;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.registries.ModBlocks;

public class ModPonderTags{

	public static final PonderTag ENCASED_BLOCKS = create("encased_blocks").item(ModBlocks.BRASS_GEARBOX.get())
			.defaultLang("Create : Encased", "Components which added by Create Encased")
			.addToIndex();


	private static PonderTag create(String id) {
		return new PonderTag(CreateCasing.asResource(id));
	}


	public static void register() {


		PonderRegistry.TAGS.forTag(AllPonderTags.CREATIVE)
						.add(ModBlocks.CREATIVE_COGWHEEL);

		PonderRegistry.TAGS.forTag(ENCASED_BLOCKS)
			.add(ModBlocks.BRASS_GEARBOX)
			.add(ModBlocks.COPPER_GEARBOX)
			.add(ModBlocks.RAILWAY_GEARBOX)
			.add(ModBlocks.BRASS_MIXER)
			.add(ModBlocks.COPPER_MIXER)
			.add(ModBlocks.RAILWAY_MIXER)
			.add(ModBlocks.BRASS_PRESS)
			.add(ModBlocks.COPPER_PRESS)
			.add(ModBlocks.RAILWAY_PRESS)
			.add(ModBlocks.CREATIVE_COGWHEEL);

	}

}
