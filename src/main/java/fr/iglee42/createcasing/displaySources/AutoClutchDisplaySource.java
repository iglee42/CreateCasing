package fr.iglee42.createcasing.displaySources;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.source.PercentOrProgressBarDisplaySource;
import com.simibubi.create.foundation.gui.ModularGuiLineBuilder;
import com.simibubi.create.foundation.utility.CreateLang;
import fr.iglee42.createcasing.blockEntities.AutoClutchBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class AutoClutchDisplaySource extends PercentOrProgressBarDisplaySource {

    @Override
    protected boolean allowsLabeling(DisplayLinkContext context) {
        return true;
    }

    @Override
    protected Float getProgress(DisplayLinkContext context) {
        if (! (context.getSourceBlockEntity() instanceof AutoClutchBlockEntity be)) return 0f;
        return be.calculatePercentage();
    }

    @Override
    protected boolean progressBarActive(DisplayLinkContext context) {
        return context.sourceConfig()
                .getInt("Mode") != 0;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initConfigurationWidgets(DisplayLinkContext context, ModularGuiLineBuilder builder, boolean isFirstLine) {
        super.initConfigurationWidgets(context, builder, isFirstLine);
        if (isFirstLine)
            return;
        builder.addSelectionScrollInput(0, 120,
                (si, l) -> si.forOptions(CreateLang.translatedOptions("display_source.redstone_power", "number", "progress_bar"))
                        .titled(CreateLang.translateDirect("display_source.redstone_power.display")),
                "Mode");
    }

}

