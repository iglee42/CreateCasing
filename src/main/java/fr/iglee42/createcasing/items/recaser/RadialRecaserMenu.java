package fr.iglee42.createcasing.items.recaser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.packets.RadialRecaserMenuSubmitPacket;
import fr.iglee42.createcasing.registries.EncasedDataComponents;
import fr.iglee42.createcasing.registries.EncasedItems;
import fr.iglee42.createcasing.registries.EncasedKeys;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import com.simibubi.create.AllKeys;
import com.simibubi.create.foundation.gui.AllIcons;

import dev.engine_room.flywheel.lib.transform.TransformStack;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.gui.AbstractSimiScreen;
import net.createmod.catnip.gui.UIRenderHelper;
import net.createmod.catnip.gui.element.GuiGameElement;
import net.createmod.catnip.gui.element.RenderElement;
import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.platform.CatnipServices;
import net.createmod.catnip.theme.Color;
import net.createmod.ponder.enums.PonderGuiTextures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.properties.Property;

public class RadialRecaserMenu extends AbstractSimiScreen {


	private final ItemStack stack;
	private final int innerRadius = 50;
	private final int outerRadius = 110;

	private List<Item> allItems = List.of();
	private int ticksOpen;
	private int selectedItemIndex = 0;

	private final RenderElement iconScroll = RenderElement.of(PonderGuiTextures.ICON_SCROLL);
	private final RenderElement iconUp = RenderElement.of(AllIcons.I_PRIORITY_HIGH);
	private final RenderElement iconDown = RenderElement.of(AllIcons.I_PRIORITY_LOW);

	public static Optional<RadialRecaserMenu> tryCreateFor(ItemStack stack) {
		if (!stack.is(EncasedItems.RECASER)) return Optional.empty();
		if (RecaserItem.findAllAmmo(Minecraft.getInstance().player, Minecraft.getInstance().level.registryAccess())
				.stream().map(ItemStack::getItem)
				.distinct()
				.toList().size() < 2) return Optional.empty();
		return Optional.of(new RadialRecaserMenu(stack));
	}

	private RadialRecaserMenu(ItemStack stack) {
		this.stack = stack;

		allItems = new ArrayList<>();
		allItems.addAll(
				RecaserItem.findAllAmmo(Minecraft.getInstance().player, Minecraft.getInstance().level.registryAccess())
						.stream().map(ItemStack::getItem)
						.distinct()
						.toList()
		);

		if (stack.has(EncasedDataComponents.RECASER_PREFERRED_ITEM)) {
			Item preferredItem = stack.get(EncasedDataComponents.RECASER_PREFERRED_ITEM);
			int index = allItems.indexOf(preferredItem);
			if (index != -1) {
				selectedItemIndex = index;
			}
		}

	}

	@Override
	public void tick() {
		ticksOpen++;
		if (!Minecraft.getInstance().player.getMainHandItem().is(EncasedItems.RECASER))
			Minecraft.getInstance().setScreen(null);
		super.tick();
	}

	@Override
	protected void renderWindow(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		int x = this.width / 2;
		int y = this.height / 2;

		PoseStack ms = graphics.pose();

		ms.pushPose();
		ms.translate(x, y, 0);

		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		int mouseOffsetX = mouseX - this.width / 2;
		int mouseOffsetY = mouseY - this.height / 2;

		if (Mth.length(mouseOffsetX, mouseOffsetY) > innerRadius - 5) {
			double theta = Mth.atan2(mouseOffsetX, mouseOffsetY);

			float sectorSize = 360f / allItems.size();

			selectedItemIndex = (int) Math.floor(
				((-AngleHelper.deg(Mth.atan2(mouseOffsetX, mouseOffsetY)) + 180 + sectorSize / 2) % 360)
					/ sectorSize
			);

			renderDirectionIndicator(graphics, theta);
		}

		renderRadialSectors(graphics);

		UIRenderHelper.streak(graphics, 0, 0, 0, 32, 65, Color.BLACK.setAlpha(0.8f));
		UIRenderHelper.streak(graphics, 180, 0, 0, 32, 65, Color.BLACK.setAlpha(0.8f));

		//graphics.drawCenteredString(font, "Currently", 0, -13, UIRenderHelper.COLOR_TEXT.getFirst().getRGB());
		//graphics.drawCenteredString(font, "Changing:", 0, -3, UIRenderHelper.COLOR_TEXT.getFirst().getRGB());
		//graphics.drawCenteredString(font, propertyLabel, 0, 7, UIRenderHelper.COLOR_TEXT.getFirst().getRGB());

		ms.popPose();

	}

	private void renderRadialSectors(GuiGraphics graphics) {
		int sectors = allItems.size();
		if (sectors < 2)
			return;

		PoseStack poseStack = graphics.pose();
		LocalPlayer player = Minecraft.getInstance().player;
		if (player == null)
			return;

		float sectorAngle = 360f / sectors;
		int sectorWidth = outerRadius - innerRadius;

		poseStack.pushPose();

		for (int i = 0; i < sectors; i++) {
			Color innerColor = Color.WHITE.setAlpha(0.05f);
			Color outerColor = Color.WHITE.setAlpha(0.3f);
			Item item = allItems.get(i);

			poseStack.pushPose();

			if (i == selectedItemIndex) {
				innerColor.mixWith(new Color(0.8f, 0.8f, 0.2f, 0.2f), 0.5f);
				outerColor.mixWith(new Color(0.8f, 0.8f, 0.2f, 0.6f), 0.5f);

				UIRenderHelper.drawRadialSector(graphics, outerRadius + 2, outerRadius + 3, -(sectorAngle / 2 + 90), sectorAngle, outerColor, outerColor);
			}

			UIRenderHelper.drawRadialSector(graphics, innerRadius, outerRadius, -(sectorAngle / 2 + 90), sectorAngle, innerColor, outerColor);
			Color c = innerColor.copy().setAlpha(0.5f);
			UIRenderHelper.drawRadialSector(graphics, innerRadius - 3, innerRadius - 2, -(sectorAngle / 2 + 90), sectorAngle, c, c);

			TransformStack.of(poseStack)
				.translateY(-(sectorWidth / 2f + innerRadius))
				.rotateZDegrees(-i * sectorAngle);

			poseStack.translate(0, 0, 100);

			try {
				GuiGameElement.of(item)
						.scale(1.5f)
						.at(-12f, -12f)
						.render(graphics);
			} catch (Exception e) {
				CreateCasing.LOGGER.warn("Failed to render item in RadialRecaserMenu", e);
				allItems.remove(i);
				selectedItemIndex = 0;
				return;
			}

			poseStack.translate(0, 0, 50);

			if (i == selectedItemIndex) {
				graphics.drawCenteredString(font, item.getDescription(), 0, 15, UIRenderHelper.COLOR_TEXT.getFirst().getRGB());
			}

			poseStack.popPose();

			poseStack.pushPose();

			TransformStack.of(poseStack)
				.rotateZDegrees(sectorAngle / 2);

			poseStack.translate(0, -innerRadius - 20, 10);

			UIRenderHelper.angledGradient(graphics, -90, 0, 0, 0.5f, sectorWidth - 10, Color.WHITE.setAlpha(0.5f), Color.WHITE.setAlpha(0.15f));
			UIRenderHelper.angledGradient(graphics, 90, 0, 0, 0.5f, 25, Color.WHITE.setAlpha(0.5f), Color.WHITE.setAlpha(0.15f));
			poseStack.popPose();

			TransformStack.of(poseStack)
				.rotateZDegrees(sectorAngle);
		}

		poseStack.popPose();

	}

	private void renderDirectionIndicator(GuiGraphics graphics, double theta) {
		PoseStack poseStack = graphics.pose();

		float r = 0.8f;
		float g = 0.8f;
		float b = 0.8f;

		poseStack.pushPose();
		TransformStack.of(poseStack)
			.rotateZ((float) -theta)
			.translateY(innerRadius + 3)
			.translateZ(15);

		RenderSystem.setShader(GameRenderer::getPositionColorShader);

		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.begin(Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);

		Matrix4f mat = poseStack.last().pose();

		bufferbuilder.addVertex(mat, 0, 0, 0).setColor(r, g, b, 0.75f);

		bufferbuilder.addVertex(mat, 5, -5, 0).setColor(r, g, b, 0.4f);
		bufferbuilder.addVertex(mat, 3, -4.5f, 0).setColor(r, g, b, 0.4f);
		bufferbuilder.addVertex(mat, 0, -4.2f, 0).setColor(r, g, b, 0.4f);
		bufferbuilder.addVertex(mat, -3, -4.5f, 0).setColor(r, g, b, 0.4f);
		bufferbuilder.addVertex(mat, -5, -5, 0).setColor(r, g, b, 0.4f);

		BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());

		poseStack.popPose();
	}

	private void submitChange() {
		if (selectedItemIndex < 0 || selectedItemIndex >= allItems.size())
			return;
		Item selectedItem = allItems.get(selectedItemIndex);
		if (selectedItem != stack.get(EncasedDataComponents.RECASER_PREFERRED_ITEM)) {
			CatnipServices.NETWORK.sendToServer(new RadialRecaserMenuSubmitPacket(selectedItem));
		}

		onClose();
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		Color color = BACKGROUND_COLOR
			.scaleAlpha(Math.min(1, (ticksOpen + AnimationTickHolder.getPartialTicks()) / 20f));

		guiGraphics.fillGradient(0, 0, this.width, this.height, color.getRGB(), color.getRGB());
	}

	@Override
	public boolean keyReleased(int code, int scanCode, int modifiers) {
		InputConstants.Key mouseKey = InputConstants.getKey(code, scanCode);
		if (EncasedKeys.MATERIAL_SELECTOR.getKeybind().isActiveAndMatches(mouseKey)) {
			submitChange();
			return true;
		}
		return super.keyReleased(code, scanCode, modifiers);
	}

	@Override
	public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
		if (pButton == InputConstants.MOUSE_BUTTON_LEFT) {
			submitChange();
			return true;
		} else if (pButton == InputConstants.MOUSE_BUTTON_RIGHT) {
			onClose();
			return true;
		}

		return super.mouseClicked(pMouseX, pMouseY, pButton);
	}


	@Override
	public void removed() {
		RadialRecaserHandler.COOLDOWN = 2;

		super.removed();
	}
}
