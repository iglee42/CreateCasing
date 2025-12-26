package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltModel;
import com.simibubi.create.foundation.model.BakedQuadHelper;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import fr.iglee42.createcasing.registries.EncasedSprites;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(value = BeltModel.class,remap = false)
public class BeltModelMixin {


    @Shadow @Final public static ModelProperty<BeltBlockEntity.CasingType> CASING_PROPERTY;

    @Inject(method = "getParticleIcon",at = @At(value = "RETURN", ordinal = 2), cancellable = true,locals = LocalCapture.CAPTURE_FAILSOFT)
    private void encased$customParticle(ModelData data, CallbackInfoReturnable<TextureAtlasSprite> cir, BeltBlockEntity.CasingType type){
        CasingSets.getSets().stream().filter(CasingSet::doesGenerateBelt).filter(set-> Objects.equals(type,set.getBeltCasingType())).findFirst().ifPresent(set->{
            if (set.getConnectedTextureSprite() == null)
                cir.setReturnValue(set.getBeltSprite().getOriginal());
            else cir.setReturnValue(set.getConnectedTextureSprite().getOriginal());
        });
    }

    @Inject(method = "getQuads",at = @At(value = "INVOKE", target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z",ordinal = 0,shift = At.Shift.AFTER),locals = LocalCapture.CAPTURE_FAILSOFT)
    private void encased$customCasingCover(BlockState state, Direction side, RandomSource rand, ModelData extraData, RenderType renderType, CallbackInfoReturnable<List<BakedQuad>> cir, List<BakedQuad> quads, boolean cover, BeltBlockEntity.CasingType type, boolean brassCasing, boolean alongX, BakedModel coverModel){
        CasingSets.getSets().stream().filter(CasingSet::doesGenerateBelt).filter(set-> Objects.equals(type,set.getBeltCasingType())).findFirst().ifPresent(set->{
            quads.removeAll(coverModel.getQuads(state, side, rand, extraData, renderType));
            quads.addAll(set.getBeltPartialModel(alongX).get().getQuads(state, side, rand, extraData, renderType));
        });
    }

    @Inject(method = "getQuads",at = @At(value = "INVOKE",target = "Ljava/util/List;size()I",ordinal = 0,shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void encased$customCasingType(BlockState state, Direction side, RandomSource rand, ModelData extraData, RenderType renderType, CallbackInfoReturnable<List<BakedQuad>> cir, List<BakedQuad> quads, boolean cover, BeltBlockEntity.CasingType type, boolean brassCasing, int i){
        AtomicBoolean mustReturn = new AtomicBoolean(false);
        CasingSets.getSets().stream().filter(CasingSet::doesGenerateBelt).filter(set-> Objects.equals(type,set.getBeltCasingType())).findFirst().ifPresent(set->{
            cir.setReturnValue(createEncased$getQuadsForSprite(quads,set.getBeltSprite()));
            mustReturn.set(true);
        });
        if (mustReturn.get()) return;
    }

    @Unique
    private static List<BakedQuad> createEncased$getQuadsForSprite(List<BakedQuad> quads, SpriteShiftEntry spriteShift){
        for (int i = 0; i < quads.size(); i++) {
            BakedQuad quad = quads.get(i);
            TextureAtlasSprite original = quad.getSprite();
            if (original != spriteShift.getOriginal())
                continue;

            BakedQuad newQuad = BakedQuadHelper.clone(quad);
            int[] vertexData = newQuad.getVertices();

            for (int vertex = 0; vertex < 4; vertex++) {
                float u = BakedQuadHelper.getU(vertexData, vertex);
                float v = BakedQuadHelper.getV(vertexData, vertex);
                BakedQuadHelper.setU(vertexData, vertex, spriteShift.getTargetU(u));
                BakedQuadHelper.setV(vertexData, vertex, spriteShift.getTargetV(v));
            }

            quads.set(i, newQuad);
        }


        return quads;
    }

}
