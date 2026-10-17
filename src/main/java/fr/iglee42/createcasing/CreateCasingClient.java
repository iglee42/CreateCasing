package fr.iglee42.createcasing;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.contraptions.wrench.RadialWrenchHandler;
import com.simibubi.create.content.equipment.toolbox.ToolboxHandlerClient;
import fr.iglee42.createcasing.client.RecaserItemRenderer;
import fr.iglee42.createcasing.items.recaser.RadialRecaserHandler;
import fr.iglee42.createcasing.ponder.CasingCreatePonderPlugin;
import fr.iglee42.createcasing.ponder.CasingPonderPlugin;
import fr.iglee42.createcasing.registries.EncasedItems;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.common.NeoForge;

public class CreateCasingClient {

    public static void onCtorClient(IEventBus modEventBus) {
        IEventBus neoEventBus = NeoForge.EVENT_BUS;

        //if (CreateCasing.isExtendedCogsLoaded())CreateExtendedCogwheelsPartials.init();

        modEventBus.addListener(CreateCasingClient::clientInit);
        modEventBus.addListener(CreateCasingClient::onRegisterAdditionalModels);
        modEventBus.addListener(CreateCasingClient::registerItemDecorations);

        neoEventBus.addListener(CreateCasingClient::clientTick);
        neoEventBus.addListener(CreateCasingClient::onKeyInput);
        neoEventBus.addListener(CreateCasingClient::onMouseInput);
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

    public static void registerItemDecorations(RegisterItemDecorationsEvent event) {
        event.register(EncasedItems.RECASER, RecaserItemRenderer.DECORATOR);
    }

    public static void clientTick(ClientTickEvent.Post event){
        RadialRecaserHandler.clientTick();
    }

    public static void onMouseInput(InputEvent.MouseButton.Pre event) {
        if (Minecraft.getInstance().screen != null)
            return;

        int button = event.getButton();
        boolean pressed = !(event.getAction() == 0);

        RadialRecaserHandler.onKeyInput(button, pressed);
    }

    public static void onKeyInput(InputEvent.Key event) {
        if (Minecraft.getInstance().screen != null)
            return;

        int key = event.getKey();
        boolean pressed = !(event.getAction() == 0);

        RadialRecaserHandler.onKeyInput(key, pressed);
    }


}