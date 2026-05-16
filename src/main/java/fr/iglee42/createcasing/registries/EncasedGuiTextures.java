package fr.iglee42.createcasing.registries;

import fr.iglee42.createcasing.CreateCasing;
import net.createmod.catnip.gui.TextureSheetSegment;
import net.createmod.catnip.gui.UIRenderHelper;
import net.createmod.catnip.gui.element.ScreenElement;
import net.createmod.catnip.theme.Color;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public enum EncasedGuiTextures implements ScreenElement, TextureSheetSegment {

	AUTOMATIC_CLUTCH("automatic_clutch", 254, 104);

	;


	public static final int FONT_COLOR = 0x575F7A;

	public final ResourceLocation location;
	private final int width;
	private final int height;
	private final int startX;
	private final int startY;

	EncasedGuiTextures(String location, int width, int height) {
		this(location, 0, 0, width, height);
	}

	EncasedGuiTextures(String location, int startX, int startY, int width, int height) {
		this(CreateCasing.MODID, location, startX, startY, width, height);
	}

	EncasedGuiTextures(String namespace, String location, int startX, int startY, int width, int height) {
		this.location = ResourceLocation.fromNamespaceAndPath(namespace, "textures/gui/" + location + ".png");
		this.width = width;
		this.height = height;
		this.startX = startX;
		this.startY = startY;
	}

	@Override
	public ResourceLocation getLocation() {
		return location;
	}

	@OnlyIn(Dist.CLIENT)
	public void render(GuiGraphics graphics, int x, int y) {
		graphics.blit(location, x, y, startX, startY, width, height);
	}

	@OnlyIn(Dist.CLIENT)
	public void render(GuiGraphics graphics, int x, int y, Color c) {
		bind();
		UIRenderHelper.drawColoredTexture(graphics, c, x, y, startX, startY, width, height);
	}

	@Override
	public int getStartX() {
		return startX;
	}

	@Override
	public int getStartY() {
		return startY;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
}
