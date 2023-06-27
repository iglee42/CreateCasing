package fr.iglee42.createcasing;

import fr.iglee42.createcasing.ponder.ModPonderTags;
import fr.iglee42.createcasing.ponder.PonderIndex;
import fr.iglee42.createcasing.registries.ModPartialModels;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class CreateCasingClient {

    public static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        ModPartialModels.init();
        //if (CreateCasing.isExtendedCogsLoaded())CreateExtendedCogwheelsPartials.init();

        modEventBus.addListener(CreateCasingClient::clientInit);

    }

    public static void clientInit(final FMLClientSetupEvent event) {

        ModPonderTags.register();
        PonderIndex.register();

    }
}
