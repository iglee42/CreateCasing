package fr.iglee42.createcasing.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueHandler;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModel;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModelRenderer;
import com.simibubi.create.foundation.item.render.PartialItemModelRenderer;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.items.recaser.RecaserItem;
import fr.iglee42.createcasing.registries.EncasedItems;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.IItemDecorator;

public class RecaserItemRenderer extends CustomRenderedItemModelRenderer {

	public static final IItemDecorator DECORATOR = (guiGraphics, font, stack, xOffset, yOffset) -> {
		LocalPlayer player = Minecraft.getInstance().player;
		if (player == null) {
			return false;
		}

		RecaserItem.Ammo ammo = RecaserItem.getAmmo(player, stack);
		if (ammo == null || EncasedItems.RECASER.is(ammo.stack())) {
			return false;
		}

		PoseStack poseStack = guiGraphics.pose();
		poseStack.pushPose();
		poseStack.translate(xOffset + 8, yOffset + 8, 100);
		poseStack.scale(.5f, .5f, .5f);
		guiGraphics.renderItem(ammo.stack(), 0, 0);
		poseStack.popPose();
		return false;
	};

	protected static final PartialModel GEAR = PartialModel.of(Create.asResource("item/wrench/gear"));
	protected static final PartialModel ROLL = PartialModel.of(CreateCasing.asResource("item/recaser/roll"));

	@Override
	protected void render(ItemStack stack, CustomRenderedItemModel model, PartialItemModelRenderer renderer, ItemDisplayContext transformType,
		PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
		renderer.render(model.getOriginalModel(), light);

		ms.pushPose();
		float xOffset = -1/16f;
		ms.translate(-xOffset, 0, 0);
		ms.mulPose(Axis.YP.rotationDegrees(ScrollValueHandler.getScroll(AnimationTickHolder.getPartialTicks())));
		ms.translate(xOffset, 0, 0);
		renderer.render(GEAR.get(), light);
		ms.popPose();

		ms.pushPose();
		xOffset = -0.5f / 16f;
		float zOffset = 0.5f / 16f;
		float yOffset = -6.5f / 16f;
		ms.translate(-xOffset, -yOffset, -zOffset);
		ms.mulPose(Axis.ZN.rotationDegrees(ScrollValueHandler.getScroll(AnimationTickHolder.getPartialTicks())));
		ms.translate(xOffset, yOffset, zOffset);
		renderer.render(ROLL.get(), light);
		ms.popPose();

	}

}
