package fr.iglee42.createcasing.registries;

import java.util.function.BiConsumer;

import com.simibubi.create.Create;
import fr.iglee42.createcasing.CreateCasing;
import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.createmod.catnip.client.ConflictSafeKeyMapping;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyModifier;

@EventBusSubscriber(Dist.CLIENT)
public enum EncasedKeys {

	MATERIAL_SELECTOR("material_selector", GLFW.GLFW_KEY_LEFT_ALT, "Open Recaser Material Selector"),
	;

	private KeyMapping keybind;
	private final String description;
	private final String translation;
	private final int key;
	private final boolean modifiable;
	private final boolean conflictSafe;

	EncasedKeys(int defaultKey) {
		this("", defaultKey, "");
	}

	EncasedKeys(String description, int defaultKey, String translation) {
		this(description, defaultKey, translation, false);
	}

	EncasedKeys(String description, int defaultKey, String translation, boolean conflictSafe) {
		this.description = CreateCasing.MODID + ".keyinfo." + description;
		this.key = defaultKey;
		this.modifiable = !description.isEmpty();
		this.translation = translation;
		this.conflictSafe = conflictSafe;
	}

	public static void provideLang(BiConsumer<String, String> consumer) {
		for (EncasedKeys key : values())
			if (key.modifiable)
				consumer.accept(key.description, key.translation);
	}

	@SubscribeEvent
	public static void register(RegisterKeyMappingsEvent event) {
		for (EncasedKeys key : values()) {
			if (key.conflictSafe) {
				key.keybind = new ConflictSafeKeyMapping(key.description, key.key, CreateCasing.NAME);
			} else {
				key.keybind = new KeyMapping(key.description, key.key, CreateCasing.NAME);
			}
			if (!key.modifiable)
				continue;

			event.register(key.keybind);
		}
	}

	public KeyMapping getKeybind() {
		return keybind;
	}

	public boolean isPressed() {
		if (!modifiable)
			return isKeyDown(key);
		return keybind.isDown();
	}

	public String getBoundKey() {
		return keybind.getTranslatedKeyMessage()
			.getString()
			.toUpperCase();
	}

	public boolean doesModifierAndCodeMatch(int code) {
		boolean codeMatches = code == keybind.getKey().getValue();

		boolean modifierMatches;
		KeyModifier modifier = keybind.getKeyModifier();
		if (modifier == KeyModifier.NONE) {
			modifierMatches = true;
		} else {
			modifierMatches = KeyModifier.getActiveModifiers().contains(modifier);
		}

		return codeMatches && modifierMatches;
	}

	public static boolean isKeyDown(int key) {
		return InputConstants.isKeyDown(Minecraft.getInstance()
			.getWindow()
			.getWindow(), key);
	}

	public static boolean isMouseButtonDown(int button) {
		return GLFW.glfwGetMouseButton(Minecraft.getInstance()
			.getWindow()
			.getWindow(), button) == 1;
	}

}
