package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.compat.jei.CreateJEI;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.compat.jei.category.MixingCategory;
import com.simibubi.create.compat.jei.category.PressingCategory;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(value = CreateJEI.class,remap = false)
public class CreateJeiMixin {

    @Shadow @Final private List<CreateRecipeCategory<?>> allCategories;

    @Inject(method = "registerRecipeCatalysts",at = @At("RETURN"))
    private void inject(IRecipeCatalystRegistration registration, CallbackInfo ci){

        for (CreateRecipeCategory<?> c : this.allCategories) {
            if (c instanceof MixingCategory) {
                CasingSets.getSets().stream().filter(set-> Objects.nonNull(set.getMixer()))
                        .forEach(set->registration.addRecipeCatalyst(set.getMixer(),c.getRecipeType()));
            }
            if (c instanceof PressingCategory || c.getRecipeType().getUid().getPath().equals("packing") || c.getRecipeType().getUid().getPath().equals("automatic_packing")) {
                CasingSets.getSets().stream().filter(set-> Objects.nonNull(set.getPress()))
                        .forEach(set->registration.addRecipeCatalyst(set.getPress(),c.getRecipeType()));
            }
        }
    }

}
