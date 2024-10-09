package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltModel;
import com.simibubi.create.foundation.block.render.SpriteShiftEntry;
import com.simibubi.create.foundation.model.BakedQuadHelper;
import fr.iglee42.createcasing.registries.ModBlocks;
import fr.iglee42.createcasing.registries.ModPartialModels;
import fr.iglee42.createcasing.registries.ModSprites;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.data.ModelProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(value = BeltModel.class,remap = false)
public class BeltModelMixin {


    @Shadow @Final public static ModelProperty<BeltBlockEntity.CasingType> CASING_PROPERTY;

    @Inject(method = "getParticleIcon",at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraftforge/client/model/data/ModelData;get(Lnet/minecraftforge/client/model/data/ModelProperty;)Ljava/lang/Object;"), cancellable = true)
    private void encased$customParticle(ModelData data, CallbackInfoReturnable<TextureAtlasSprite> cir){
        BeltBlockEntity.CasingType type = data.get(CASING_PROPERTY);
        System.out.println(type);
        if (type.equals(ModBlocks.COPPER_BELT_CASING)) {
            cir.setReturnValue(AllSpriteShifts.COPPER_CASING.getOriginal());
        }
        if (type.equals(ModBlocks.RAILWAY_BELT_CASING)) {
            cir.setReturnValue(AllSpriteShifts.RAILWAY_CASING.getOriginal());
        }
        if (type.equals(ModBlocks.INDUSTRIAL_IRON_BELT_CASING)) {
            cir.setReturnValue(ModSprites.INDUSTRIAL_IRON.getOriginal());
        }
        if (type.equals(ModBlocks.CREATIVE_BELT_CASING)) {
            cir.setReturnValue(AllSpriteShifts.CREATIVE_CASING.getOriginal());
        }
    }

    @Inject(method = "getQuads",at = @At(value = "INVOKE", target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z",ordinal = 0,shift = At.Shift.AFTER),locals = LocalCapture.CAPTURE_FAILSOFT)
    private void encased$customCasingCover(BlockState state, Direction side, RandomSource rand, ModelData extraData, RenderType renderType, CallbackInfoReturnable<List<BakedQuad>> cir, List quads, boolean cover, BeltBlockEntity.CasingType type, boolean brassCasing, boolean alongX, BakedModel coverModel){
        if (type.equals(ModBlocks.COPPER_BELT_CASING)){
            quads.removeAll(coverModel.getQuads(state, side, rand, extraData, renderType));
            quads.addAll((alongX ? ModPartialModels.COPPER_BELT_COVER_X : ModPartialModels.COPPER_BELT_COVER_Z).get().getQuads(state, side, rand, extraData, renderType));
        }
        if (type.equals(ModBlocks.RAILWAY_BELT_CASING)){
            quads.removeAll(coverModel.getQuads(state, side, rand, extraData, renderType));
        quads.addAll((alongX ? ModPartialModels.RAILWAY_BELT_COVER_X : ModPartialModels.RAILWAY_BELT_COVER_Z).get().getQuads(state, side, rand, extraData, renderType));
        }
        if (type.equals(ModBlocks.INDUSTRIAL_IRON_BELT_CASING)){
            quads.removeAll(coverModel.getQuads(state, side, rand, extraData, renderType));
            quads.addAll((alongX ? ModPartialModels.INDUSTRIAL_IRON_BELT_COVER_X : ModPartialModels.INDUSTRIAL_IRON_BELT_COVER_Z).get().getQuads(state, side, rand, extraData, renderType));
        }
        if (type.equals(ModBlocks.CREATIVE_BELT_CASING)){
            quads.removeAll(coverModel.getQuads(state, side, rand, extraData, renderType));
            quads.addAll((alongX ? ModPartialModels.CREATIVE_BELT_COVER_X : ModPartialModels.CREATIVE_BELT_COVER_Z).get().getQuads(state, side, rand, extraData, renderType));
        }
    }

    @Inject(method = "getQuads",at = @At(value = "INVOKE",target = "Ljava/util/List;size()I",ordinal = 0,shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void encased$customCasingType(BlockState state, Direction side, RandomSource rand, ModelData extraData, RenderType renderType, CallbackInfoReturnable<List<BakedQuad>> cir, List quads, boolean cover, BeltBlockEntity.CasingType type, boolean brassCasing, int i){
        if (type.equals(ModBlocks.COPPER_BELT_CASING)){
            cir.setReturnValue(getQuadsForSprite(quads, ModSprites.COPPER_BELT_CASING));
            return;
        }
        if (type.equals(ModBlocks.RAILWAY_BELT_CASING)){
            cir.setReturnValue(getQuadsForSprite(quads, ModSprites.RAILWAY_BELT_CASING));
            return;
        }
        if (type.equals(ModBlocks.INDUSTRIAL_IRON_BELT_CASING)){
            cir.setReturnValue(getQuadsForSprite(quads, ModSprites.INDUSTRIAL_IRON_BELT_CASING));
            return;
        }
        if (type.equals(ModBlocks.CREATIVE_BELT_CASING)){
            cir.setReturnValue(getQuadsForSprite(quads, ModSprites.CREATIVE_BELT_CASING));
            return;
        }
    }

    private static List<BakedQuad> getQuadsForSprite(List<BakedQuad> quads, SpriteShiftEntry spriteShift){
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
