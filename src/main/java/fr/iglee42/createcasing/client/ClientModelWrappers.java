package fr.iglee42.createcasing.client;

import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import fr.iglee42.createcasing.fluids.EncasedFluidTankModel;
import fr.iglee42.createcasing.fluids.EncasedPipeAttachmentModel;
import net.minecraft.client.resources.model.BakedModel;

public final class ClientModelWrappers {

    private ClientModelWrappers() {
    }

    public static NonNullFunction<BakedModel, ? extends BakedModel> encasedPipeAttachment(String setName) {
        return model -> EncasedPipeAttachmentModel.withAO(model, setName);
    }

    public static NonNullFunction<BakedModel, ? extends BakedModel> encasedFluidTank(CTSpriteShiftEntry side,
                                                                                    CTSpriteShiftEntry top,
                                                                                    CTSpriteShiftEntry inner) {
        return model -> new EncasedFluidTankModel(model, side, top, inner);
    }
}
