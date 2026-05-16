package fr.iglee42.createcasing.ponder;

import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import net.createmod.catnip.registry.RegisteredObjectsHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
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

		helper.registerTag(ENCASED_BLOCKS).item(()-> CasingSets.BRASS.getGearbox().asItem(),true,false)
				.title("Create Encased")
				.description("Components added by Create Encased")
				.addToIndex()
				.register();


		HELPER.addToTag(AllCreatePonderTags.CREATIVE)
						.add(EncasedBlocks.CREATIVE_COGWHEEL);
		HELPER.addToTag(ENCASED_BLOCKS).add(EncasedBlocks.CREATIVE_COGWHEEL);
		itemHelper.addToTag(ENCASED_BLOCKS).add(CasingSets.ANDESITE.getConfigurableGearbox()).add(CasingSets.ANDESITE.getAutoClutch());
		itemHelper.addToTag(AllCreatePonderTags.KINETIC_RELAYS).add(CasingSets.ANDESITE.getConfigurableGearbox()).add(CasingSets.ANDESITE.getAutoClutch());


	}

}
