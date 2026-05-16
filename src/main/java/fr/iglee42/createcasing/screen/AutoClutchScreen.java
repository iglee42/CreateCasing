package fr.iglee42.createcasing.screen;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.widget.*;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.blockEntities.AutoClutchBlockEntity;
import fr.iglee42.createcasing.blocks.AutoClutchBlock;
import fr.iglee42.createcasing.packets.ConfigureAutoClutchPacket;
import fr.iglee42.createcasing.registries.EncasedGuiTextures;
import fr.iglee42.createcasing.registries.EncasedIcons;
import net.createmod.catnip.gui.AbstractSimiScreen;
import net.createmod.catnip.gui.element.GuiGameElement;
import net.createmod.catnip.gui.widget.AbstractSimiWidget;
import net.createmod.catnip.gui.widget.ElementWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class AutoClutchScreen extends AbstractSimiScreen {

    protected AutoClutchBlockEntity be;
    protected EncasedGuiTextures background= EncasedGuiTextures.AUTOMATIC_CLUTCH;
    protected ScrollInput maxStressWidget;
    private SelectionScrollInput scrollInput;
    private SelectionScrollInput opsInput;
    private Label scrollInputLabel;


    protected AbstractSimiWidget clutchWidget;
    private IconButton confirmButton;

    public AutoClutchScreen(AutoClutchBlockEntity be) {
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

        clutchWidget = new ElementWidget(x + 51, y + 48)
                .showingElement(GuiGameElement.of(be.getBlockState().getBlock()));
        addRenderableWidget(clutchWidget);


        scrollInput = new SelectionScrollInput(x + 51, y + 25, 145, 18);
        scrollInputLabel = new Label(x + 53, y + 29, CommonComponents.EMPTY).withShadow();
        scrollInput.forOptions(AutoClutchBlockEntity.Mode.getComponents())
                .titled(Component.translatable(CreateCasing.MODID+".auto_clutch.mode"))
                .writingTo(scrollInputLabel)
                .setState(be.getMode().ordinal());
        addRenderableWidgets(scrollInputLabel,scrollInput);

        Label valueLabel = new Label(x + 105, y + 52, Component.empty()).withShadow();
        maxStressWidget = new ScrollInput(x + 104, y + 48, 91, 18);
        maxStressWidget.withRange(0,Integer.MAX_VALUE)
                .writingTo(valueLabel)
                .withStepFunction((context)->context.control ? (context.shift ? 1024 : 512) : context.shift ? 128 : 1)
                .titled(Component.translatable("createcasing.auto_clutch.configured_value"))
                .format(i-> Component.literal(addSpacesEveryThreeDigits(i)));
        maxStressWidget.setState(be.getConfiguredValue());
        maxStressWidget.onChanged();
        addRenderableWidgets(valueLabel,maxStressWidget);
        confirmButton = new IconButton(x + background.getWidth() - 33, y + background.getHeight() - 24, AllIcons.I_CONFIRM);
        confirmButton.withCallback(this::onClose);
        addRenderableWidget(confirmButton);

        opsInput = new SelectionScrollInput(x+ 75 , y+ 48,24,18);
        Label opsLabel = new Label(x + 80, y + 52, Component.empty()).withShadow();
        opsInput.forOptions(AutoClutchBlockEntity.Operation.getComponents())
                .setState(be.getOperation().ordinal())
                .titled(Component.translatable("createcasing.auto_clutch.operation"))
                .format(state-> Component.literal(" " + AutoClutchBlockEntity.Operation.values()[state].formatted))
                .writingTo(opsLabel)
                .calling(state->{
                    PacketDistributor.sendToServer(new ConfigureAutoClutchPacket(be.getBlockPos(), maxStressWidget.getState(),scrollInput.getState(),state));
                });

        addRenderableWidgets(opsLabel,opsInput);
    }

    private static String addSpacesEveryThreeDigits(int number) {
        String numberStr = String.valueOf(number);
        StringBuilder formatted = new StringBuilder();

        int length = numberStr.length();
        int count = 0;

        for (int i = length - 1; i >= 0; i--) {
            formatted.insert(0, numberStr.charAt(i));
            count++;
            if (count % 3 == 0 && i > 0) {
                formatted.insert(0, " ");
            }
        }

        return formatted.toString();
    }

    @Override
    protected void renderWindow(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        int x = guiLeft;
        int y = guiTop;

        background.render(graphics, x, y);
        GuiGameElement.of(be.getBlockState().getBlock()).<GuiGameElement
                        .GuiRenderBuilder>at(x + background.getWidth(), y + background.getHeight() - 56, -200)
                .scale(5)
                .render(graphics);
        graphics.drawString(font, title, x + (background.getWidth() - 8) / 2 - font.width(title) / 2, y + 4, 0x592424, false);
    }

    @Override
    public void removed() {
        PacketDistributor.sendToServer(new ConfigureAutoClutchPacket(be.getBlockPos(), maxStressWidget.getState(),scrollInput.getState(),be.getOperation().ordinal()));
    }
}
