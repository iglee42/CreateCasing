package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.compat.jei.CreateJEI;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.compat.jei.category.MixingCategory;
import com.simibubi.create.compat.jei.category.PressingCategory;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = CreateJEI.class,remap = false)
public class CreateJeiMixin {

    @Shadow @Final private List<CreateRecipeCategory<?>> allCategories;

    @Inject(method = "registerRecipeCatalysts",at = @At("RETURN"))
    private void inject(IRecipeCatalystRegistration registration, CallbackInfo ci){

        for (CreateRecipeCategory<?> c : this.allCategories) {
            if (c instanceof MixingCategory) {
                registration.addRecipeCatalyst(EncasedBlocks.BRASS_MIXER.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.COPPER_MIXER.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.RAILWAY_MIXER.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.INDUSTRIAL_IRON_MIXER.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.WEATHERED_IRON_MIXER.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.CREATIVE_MIXER.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.REFINED_RADIANCE_MIXER.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.SHADOW_STEEL_MIXER.asStack(), c.getRecipeType());
            }
            if (c instanceof PressingCategory || c.getRecipeType().getUid().getPath().equals("packing") || c.getRecipeType().getUid().getPath().equals("automatic_packing")) {
                registration.addRecipeCatalyst(EncasedBlocks.BRASS_PRESS.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.COPPER_PRESS.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.RAILWAY_PRESS.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.INDUSTRIAL_IRON_PRESS.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.WEATHERED_IRON_PRESS.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.CREATIVE_PRESS.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.REFINED_RADIANCE_PRESS.asStack(), c.getRecipeType());
                registration.addRecipeCatalyst(EncasedBlocks.SHADOW_STEEL_PRESS.asStack(), c.getRecipeType());
            }
        }
    }

}
