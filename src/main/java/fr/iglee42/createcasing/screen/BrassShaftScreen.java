package fr.iglee42.createcasing.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.gui.AbstractSimiScreen;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.element.GuiGameElement;
import com.simibubi.create.foundation.gui.widget.*;
import com.simibubi.create.foundation.utility.Components;
import fr.iglee42.createcasing.blockEntities.BrassShaftBlockEntity;
import fr.iglee42.createcasing.packets.ConfigureBrassShaftPacket;
import fr.iglee42.createcasing.registries.ModGuiTextures;
import fr.iglee42.createcasing.registries.ModPackets;
import net.minecraft.network.chat.Component;

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
        setWindowSize(background.width, background.height);
        super.init();
        clearWidgets();

        int x = guiLeft;
        int y = guiTop;

        brassShaftWidget  = new ElementWidget(x + 33, y + 38)
                .showingElement(GuiGameElement.of(be.getBlockState().getBlock()));
        addRenderableWidget(brassShaftWidget);

        Label label = new Label(x + 65 + 20, y + 43, Components.immutableEmpty()).withShadow();


        maxStressWidget = new ScrollInput(x + 56 + 20, y + 38, 144, 18)
                .withRange(0, (int) be.getCapacity())
                .writingTo(label)
                .withShiftStep(128)
                .withStepFunction((context)->context.control ? (context.shift ? 1024 : 512) : 1)
                .titled(Component.translatable("tooltip.createcasing.brass_shaft_max_stress"))
                .calling(state -> {
                    label.x = x + 65 + 40 - font.width(label.text) / 2;
                });
        maxStressWidget.setState(be.getMaxSupportedStress());
        maxStressWidget.onChanged();
        addRenderableWidgets(label,maxStressWidget);
        confirmButton = new IconButton(x + background.width - 33, y + background.height - 24, AllIcons.I_CONFIRM);
        confirmButton.withCallback(this::onClose);
        addRenderableWidget(confirmButton);
    }

    @Override
    protected void renderWindow(PoseStack graphics, int mouseX, int mouseY, float partialTicks) {
        int x = guiLeft;
        int y = guiTop;

        background.render(graphics, x, y);
        GuiGameElement.of(be.getBlockState().getBlock()).<GuiGameElement
                        .GuiRenderBuilder>at(x + background.width - 20, y + background.height - 56, -200)
                .scale(5)
                .render(graphics);
        font.draw(graphics,title, x + (background.width - 8) / 2 - font.width(title) / 2, y + 4, 0x592424);
    }



    @Override
    public void removed() {
        ModPackets.getChannel()
                .sendToServer(new ConfigureBrassShaftPacket(be.getBlockPos(), maxStressWidget.getState()));
    }
}
