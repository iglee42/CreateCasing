package fr.iglee42.createcasing.ponder;

import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.PonderTag;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.registries.ModBlocks;

import java.util.List;

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

		List<ItemProviderEntry<?>> entries = List.of(
				ModBlocks.CREATIVE_COGWHEEL,
				ModBlocks.BRASS_GEARBOX,ModBlocks.COPPER_GEARBOX,ModBlocks.RAILWAY_GEARBOX,ModBlocks.INDUSTRIAL_IRON_GEARBOX,ModBlocks.CREATIVE_GEARBOX,
				ModBlocks.BRASS_MIXER,ModBlocks.COPPER_MIXER,ModBlocks.RAILWAY_MIXER,ModBlocks.INDUSTRIAL_IRON_MIXER,ModBlocks.CREATIVE_MIXER,
				ModBlocks.BRASS_PRESS,ModBlocks.COPPER_PRESS,ModBlocks.RAILWAY_PRESS,ModBlocks.INDUSTRIAL_IRON_PRESS,ModBlocks.CREATIVE_PRESS,
				ModBlocks.BRASS_DEPOT,ModBlocks.COPPER_DEPOT,ModBlocks.RAILWAY_DEPOT,ModBlocks.INDUSTRIAL_IRON_DEPOT,ModBlocks.CREATIVE_DEPOT,
				ModBlocks.BRASS_CHAIN_DRIVE,ModBlocks.COPPER_CHAIN_DRIVE,ModBlocks.RAILWAY_CHAIN_DRIVE,ModBlocks.INDUSTRIAL_IRON_CHAIN_DRIVE,ModBlocks.CREATIVE_CHAIN_DRIVE,
				ModBlocks.BRASS_CHAIN_GEARSHIFT,ModBlocks.COPPER_CHAIN_GEARSHIFT,ModBlocks.RAILWAY_CHAIN_GEARSHIFT,ModBlocks.INDUSTRIAL_IRON_CHAIN_GEARSHIFT,ModBlocks.CREATIVE_CHAIN_GEARSHIFT);

		entries.forEach(e->PonderRegistry.TAGS.forTag(ENCASED_BLOCKS).add(e));


	}

}
