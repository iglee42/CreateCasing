package fr.iglee42.createcasing;

import com.simibubi.create.CreateClient;
import fr.iglee42.createcasing.compatibility.createextendedcogs.CreateExtendedCogwheelsPartials;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class CreateCasingClient {

    public static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        if (CreateCasing.isExtendedCogsLoaded())CreateExtendedCogwheelsPartials.init();

        forgeEventBus.addListener(CreateClient::clientInit);

    }

    public static void clientInit(final FMLClientSetupEvent event) {
    }
}