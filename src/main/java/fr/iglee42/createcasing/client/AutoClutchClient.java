package fr.iglee42.createcasing.client;

import fr.iglee42.createcasing.blockEntities.AutoClutchBlockEntity;
import fr.iglee42.createcasing.screen.AutoClutchScreen;
import net.createmod.catnip.gui.ScreenOpener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;

public final class AutoClutchClient {

    private AutoClutchClient() {
    }

    public static void openScreen(AutoClutchBlockEntity be, Player player) {
        if (!(player instanceof LocalPlayer))
            return;
        ScreenOpener.open(new AutoClutchScreen(be));
    }
}
