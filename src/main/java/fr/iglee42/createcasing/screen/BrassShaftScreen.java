package fr.iglee42.createcasing.screen;

import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.widget.IconButton;
import com.simibubi.create.foundation.gui.widget.Label;
import com.simibubi.create.foundation.gui.widget.ScrollInput;
import fr.iglee42.createcasing.blockEntities.BrassShaftBlockEntity;
import fr.iglee42.createcasing.packets.ConfigureBrassShaftPacket;
import fr.iglee42.createcasing.registries.ModGuiTextures;
import fr.iglee42.createcasing.registries.ModPackets;
import net.createmod.catnip.gui.AbstractSimiScreen;
import net.createmod.catnip.gui.element.GuiGameElement;
import net.createmod.catnip.gui.widget.AbstractSimiWidget;
import net.createmod.catnip.gui.widget.ElementWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;

@OnlyIn(Dist.CLIENT)
public class BrassShaftScreen extends AbstractSimiScreen {

    protected BrassShaftBlockEntity be;
    protected ModGuiTextures background= ModGuiTextures.BRASS_SHAFT;
    protected ScrollInput maxStressWidget;

    protected AbstractSimiWidget brassShaftWidget;

    private IconButton confirmButton;

    public BrassShaftScreen(BrassShaftBlockEntity be) {
        super(be.getBlockState().getBlock().getName());
        this.be = be;
    }

    @Override
    protected void init() {
        setWindowSize(background.getWidth(), background.getHeight());
        super.init();
        clearWidgets();

        int x = guiLeft;
        int y = guiTop;

        brassShaftWidget  = new ElementWidget(x + 33, y + 38)
                .showingElement(GuiGameElement.of(be.getBlockState().getBlock()));
        addRenderableWidget(brassShaftWidget);

        Label label = new Label(x + 65 + 20, y + 43, Component.empty()).withShadow();


        maxStressWidget = new ScrollInput(x + 56 + 20, y + 38, 144, 18)
                .withRange(0, (int) be.getCapacity())
                .writingTo(label)
                .withShiftStep(128)
                .withStepFunction((context)->context.control ? (context.shift ? 1024 : 512) : 1)
                .titled(Component.translatable("tooltip.createcasing.brass_shaft_max_stress"))
                .calling(state -> {
                    label.setX(x + 65 + 40 - font.width(label.text) / 2);
                });
        maxStressWidget.setState(be.getMaxSupportedStress());
        maxStressWidget.onChanged();
        addRenderableWidgets(label,maxStressWidget);
        confirmButton = new IconButton(x + background.getWidth() - 33, y + background.getHeight() - 24, AllIcons.I_CONFIRM);
        confirmButton.withCallback(this::onClose);
        addRenderableWidget(confirmButton);
    }

    @Override
    protected void renderWindow(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        int x = guiLeft;
        int y = guiTop;

        background.render(graphics, x, y);
        GuiGameElement.of(be.getBlockState().getBlock()).<GuiGameElement
                        .GuiRenderBuilder>at(x + background.getWidth() - 20, y + background.getHeight() - 56, -200)
                .scale(5)
                .render(graphics);
        graphics.drawString(font, title, x + (background.getWidth() - 8) / 2 - font.width(title) / 2, y + 4, 0x592424, false);
    }



    @Override
    public void removed() {
        PacketDistributor.sendToServer(new ConfigureBrassShaftPacket(be.getBlockPos(), maxStressWidget.getState()));
    }
}
