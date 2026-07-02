package fr.iglee42.createcasing.registries;

import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import fr.iglee42.createcasing.fluids.EncasedFluidTankModel;
import fr.iglee42.createcasing.fluids.EncasedPipeAttachmentModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public final class EncasedBlockRegistrationHelpersClient {

    private EncasedBlockRegistrationHelpersClient() {
    }

    public static Supplier<Supplier<RenderType>> cutoutMipped() {
        return () -> RenderType::cutoutMipped;
    }

    public static <T extends Block> NonNullConsumer<? super T> pipeAttachmentModel() {
        return CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO);
    }

    public static <T extends Block> NonNullConsumer<? super T> bracketedKineticBlockModel() {
        return CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new);
    }

    public static <T extends Block> NonNullConsumer<? super T> encasedPipeAttachmentModel(String name) {
        return CreateRegistrate.blockModel(() -> model -> EncasedPipeAttachmentModel.withAO(model, name));
    }

    public static <T extends Block> NonNullConsumer<? super T> encasedFluidTankModel(CTSpriteShiftEntry sideSprite, CTSpriteShiftEntry topSprite,
                                                                                    CTSpriteShiftEntry innerSprite) {
        return CreateRegistrate.blockModel(() -> model -> new EncasedFluidTankModel(model, sideSprite, topSprite, innerSprite));
    }
}
