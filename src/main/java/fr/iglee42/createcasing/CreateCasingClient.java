package fr.iglee42.createcasing;

import fr.iglee42.createcasing.ponder.ModPonderTags;
import fr.iglee42.createcasing.ponder.PonderIndex;
import fr.iglee42.createcasing.registries.ModPartialModels;
import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

public class CreateCasingClient {

        ModPartialModels.init();
    public static void onCtorClient(IEventBus modEventBus) {
        IEventBus neoEventBus = NeoForge.EVENT_BUS;

        //if (CreateCasing.isExtendedCogsLoaded())CreateExtendedCogwheelsPartials.init();

        modEventBus.addListener(CreateCasingClient::clientInit);

    }

    public static void clientInit(final FMLClientSetupEvent event) {

        ModPonderTags.register();
        PonderIndex.register();

    }
}