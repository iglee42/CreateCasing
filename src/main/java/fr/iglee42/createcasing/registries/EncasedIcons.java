package fr.iglee42.createcasing.registries;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.foundation.gui.AllIcons;
import net.createmod.catnip.gui.element.DelegatedStencilElement;
import net.createmod.catnip.theme.Color;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

import static fr.iglee42.createcasing.CreateCasing.MODID;


public class EncasedIcons extends AllIcons {

	public static final ResourceLocation ICON_ATLAS = new ResourceLocation(MODID,"textures/gui/icons.png");
	public static final int ICON_ATLAS_SIZE = 256;

	private static int x = 0, y = -1;
	private final int iconX,iconY;



	public static final EncasedIcons
			I_EQUALS = newRow();

	public EncasedIcons(int x, int y) {
		super(x,y);
		iconX = x * 16;
		iconY = y * 16;
	}

	private static EncasedIcons next() {
		return new EncasedIcons(++x, y);
	}

	private static EncasedIcons newRow() {
		return new EncasedIcons(x = 0, ++y);
	}

	@OnlyIn(Dist.CLIENT)
	public void bind() {
		RenderSystem.setShaderTexture(0, ICON_ATLAS);
	}

	@OnlyIn(Dist.CLIENT)
	public void render(GuiGraphics graphics, int x, int y) {
		graphics.blit(ICON_ATLAS, x, y, 0, (float)this.iconX, (float)this.iconY, 16, 16, 256, 256);
	}

	@OnlyIn(Dist.CLIENT)
	public void render(PoseStack ms, MultiBufferSource buffer, int color) {
		VertexConsumer builder = buffer.getBuffer(RenderType.text(ICON_ATLAS));
		Matrix4f matrix = ms.last().pose();
		Color rgb = new Color(color);
		int light = 15728880;
		Vec3 vec1 = new Vec3(0.0, 0.0, 0.0);
		Vec3 vec2 = new Vec3(0.0, 1.0, 0.0);
		Vec3 vec3 = new Vec3(1.0, 1.0, 0.0);
		Vec3 vec4 = new Vec3(1.0, 0.0, 0.0);
		float u1 = (float)this.iconX * 1.0F / 256.0F;
		float u2 = (float)(this.iconX + 16) * 1.0F / 256.0F;
		float v1 = (float)this.iconY * 1.0F / 256.0F;
		float v2 = (float)(this.iconY + 16) * 1.0F / 256.0F;
		this.vertex(builder, matrix, vec1, rgb, u1, v1, light);
		this.vertex(builder, matrix, vec2, rgb, u1, v2, light);
		this.vertex(builder, matrix, vec3, rgb, u2, v2, light);
		this.vertex(builder, matrix, vec4, rgb, u2, v1, light);
	}

	@OnlyIn(Dist.CLIENT)
	private void vertex(VertexConsumer builder, Matrix4f matrix, Vec3 vec, Color rgb, float u, float v, int light) {
		builder.vertex(matrix, (float)vec.x, (float)vec.y, (float)vec.z).color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), 255).uv(u, v).uv2(light).endVertex();
	}

	@OnlyIn(Dist.CLIENT)
	public DelegatedStencilElement asStencil() {
		return (DelegatedStencilElement)(new DelegatedStencilElement()).withStencilRenderer((ms, w, h, alpha) -> {
			this.render(ms, 0, 0);
		}).withBounds(16, 16);
	}

}