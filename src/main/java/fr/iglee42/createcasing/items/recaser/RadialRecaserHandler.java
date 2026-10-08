package fr.iglee42.createcasing.items.recaser;

import com.simibubi.create.AllKeys;

import fr.iglee42.createcasing.registries.EncasedItems;
import fr.iglee42.createcasing.registries.EncasedKeys;
import net.createmod.catnip.gui.ScreenOpener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

public class RadialRecaserHandler {

	public static int COOLDOWN = 0;

	public static void clientTick() {
		if (COOLDOWN > 0 && !EncasedKeys.MATERIAL_SELECTOR.isPressed())
			COOLDOWN--;
	}

	public static void onKeyInput(int key, boolean pressed) {
		if (!pressed)
			return;

		if (!EncasedKeys.MATERIAL_SELECTOR.doesModifierAndCodeMatch(key))
			return;

		if (COOLDOWN > 0)
			return;

		Minecraft mc = Minecraft.getInstance();
		if (mc.gameMode == null || mc.gameMode.getPlayerMode() == GameType.SPECTATOR)
			return;

		LocalPlayer player = mc.player;
		if (player == null)
			return;

		ItemStack heldItem = player.getMainHandItem();
		if (heldItem.getItem() != EncasedItems.RECASER.get())
			return;

		RadialRecaserMenu.tryCreateFor(heldItem).ifPresent(ScreenOpener::open);
	}

}
