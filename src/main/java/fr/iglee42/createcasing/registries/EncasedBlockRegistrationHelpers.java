package fr.iglee42.createcasing.registries;

import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;

public final class EncasedBlockRegistrationHelpers {

    private EncasedBlockRegistrationHelpers() {
    }

    public static <B extends Block, P> BlockBuilder<B, P> cutoutMipped(BlockBuilder<B, P> builder) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            return builder.addLayer(EncasedBlockRegistrationHelpersClient.cutoutMipped());
        }
        return builder;
    }

    public static <T extends Block> NonNullConsumer<? super T> pipeAttachmentModel() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            return EncasedBlockRegistrationHelpersClient.pipeAttachmentModel();
        }
        return EncasedBlockRegistrationHelpers::noop;
    }

    public static <T extends Block> NonNullConsumer<? super T> bracketedKineticBlockModel() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            return EncasedBlockRegistrationHelpersClient.bracketedKineticBlockModel();
        }
        return EncasedBlockRegistrationHelpers::noop;
    }

    public static <T extends Block> NonNullConsumer<? super T> encasedPipeAttachmentModel(String name) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            return EncasedBlockRegistrationHelpersClient.encasedPipeAttachmentModel(name);
        }
        return EncasedBlockRegistrationHelpers::noop;
    }

    public static <T extends Block> NonNullConsumer<? super T> encasedFluidTankModel(CTSpriteShiftEntry sideSprite, CTSpriteShiftEntry topSprite,
                                                                                    CTSpriteShiftEntry innerSprite) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            return EncasedBlockRegistrationHelpersClient.encasedFluidTankModel(sideSprite, topSprite, innerSprite);
        }
        return EncasedBlockRegistrationHelpers::noop;
    }

    private static void noop(Object ignored) {
    }
}
