package fr.iglee42.createcasing;

import fr.iglee42.createcasing.ponder.CasingCreatePonderPlugin;
import fr.iglee42.createcasing.ponder.CasingPonderPlugin;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.common.NeoForge;

public class CreateCasingClient {

    public static void onCtorClient(IEventBus modEventBus) {
        IEventBus neoEventBus = NeoForge.EVENT_BUS;

        //if (CreateCasing.isExtendedCogsLoaded())CreateExtendedCogwheelsPartials.init();

        modEventBus.addListener(CreateCasingClient::clientInit);
        modEventBus.addListener(CreateCasingClient::onRegisterAdditionalModels);

    }

    public static void clientInit(final FMLClientSetupEvent event) {
        EncasedPartialModels.init();

        //CasingPonderTags.register();
        //CasingPonderScenes.register();

        PonderIndex.addPlugin(new CasingPonderPlugin());
        PonderIndex.addPlugin(new CasingCreatePonderPlugin());
    }

    public static void onRegisterAdditionalModels(ModelEvent.RegisterAdditional event){
        EncasedPartialModels.ALL_ENCASED_MODELS.forEach(m->event.register(ModelResourceLocation.standalone(m.modelLocation())));
    }
}