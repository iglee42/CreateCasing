package fr.iglee42.createcasing.screen;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.content.schematics.cannon.ConfigureSchematicannonPacket;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.widget.*;
import com.simibubi.create.foundation.utility.CreateLang;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.blockEntities.BrassShaftBlockEntity;
import fr.iglee42.createcasing.packets.ConfigureBrassShaftPacket;
import fr.iglee42.createcasing.registries.ModGuiTextures;
import fr.iglee42.createcasing.registries.ModIcons;
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
import java.util.stream.Stream;

@OnlyIn(Dist.CLIENT)
public class BrassShaftScreen extends AbstractSimiScreen {

    protected BrassShaftBlockEntity be;
    protected ModGuiTextures background= ModGuiTextures.BRASS_SHAFT;
    protected ScrollInput maxStressWidget;
    private SelectionScrollInput scrollInput;
    private Label scrollInputLabel;
    protected List<IconButton> operationButtons;



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

        brassShaftWidget  = new ElementWidget(x + 51, y + 48)
                .showingElement(GuiGameElement.of(be.getBlockState().getBlock()));
        addRenderableWidget(brassShaftWidget);


        scrollInput = new SelectionScrollInput(x + 51, y + 25, 145, 18);
        scrollInputLabel = new Label(x + 53, y + 29, CommonComponents.EMPTY).withShadow();
        scrollInput.forOptions(BrassShaftBlockEntity.Mode.getComponents())
                .titled(Component.translatable(CreateCasing.MODID+".brass_shaft.mode"))
                .writingTo(scrollInputLabel)
                .setState(be.getMode().ordinal());
        addRenderableWidgets(scrollInputLabel,scrollInput);

        Label label = new Label(x + 76, y + 52, Component.empty()).withShadow();
        maxStressWidget = new ScrollInput(x + 75 , y + 48, 120, 18);
        maxStressWidget.withRange(0,Integer.MAX_VALUE)
                .writingTo(label)
                .withStepFunction((context)->context.control ? (context.shift ? 1024 : 512) : context.shift ? 128 : 1)
                .titled(Component.translatable("createcasing.brass_shaft.max_stress"))
                .format(i-> Component.literal(addSpacesEveryThreeDigits(i)));
        maxStressWidget.setState(be.getMaxSupportedStress());
        maxStressWidget.onChanged();
        addRenderableWidgets(label,maxStressWidget);
        confirmButton = new IconButton(x + background.getWidth() - 33, y + background.getHeight() - 24, AllIcons.I_CONFIRM);
        confirmButton.withCallback(this::onClose);
        addRenderableWidget(confirmButton);

        operationButtons = new ArrayList<>(3);

        List<AllIcons> icons = ImmutableList.of(AllIcons.I_MTD_LEFT, ModIcons.I_EQUALS,
                AllIcons.I_MTD_RIGHT);
        for (int i = 0; i < 3; i++) {
            IconButton operationButton = new IconButton(x + 33 + i * 18, y + background.getHeight() - 24, icons.get(i));
            int operation = i;
            operationButton.withCallback(() -> {
                PacketDistributor.sendToServer(new ConfigureBrassShaftPacket(be.getBlockPos(), maxStressWidget.getState(),scrollInput.getState(),operation));
            });
            operationButton.setToolTip(BrassShaftBlockEntity.Operation.getComponents().get(i));
            operationButtons.add(operationButton);
        }
        addRenderableWidgets(operationButtons);
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
                        .GuiRenderBuilder>at(x + background.getWidth() - 20, y + background.getHeight() - 56, -200)
                .scale(5)
                .render(graphics);
        graphics.drawString(font, title, x + (background.getWidth() - 8) / 2 - font.width(title) / 2, y + 4, 0x592424, false);
    }

    @Override
    public void tick() {
        super.tick();
        for (int operation = 0; operation < operationButtons.size(); operation++) {
            operationButtons.get(operation).green = operation == be.getOperation().ordinal();
        }
    }

    @Override
    public void removed() {
        PacketDistributor.sendToServer(new ConfigureBrassShaftPacket(be.getBlockPos(), maxStressWidget.getState(),scrollInput.getState(),be.getOperation().ordinal()));
    }
}
