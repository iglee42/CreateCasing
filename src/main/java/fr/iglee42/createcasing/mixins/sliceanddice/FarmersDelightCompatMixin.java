package fr.iglee42.createcasing.mixins.sliceanddice;

import com.possible_triangle.sliceanddice.compat.FarmersDelightCompat;
import com.simibubi.create.compat.jei.category.*;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

import java.util.Objects;

@Mixin(value = FarmersDelightCompat.class,remap = false)
public class FarmersDelightCompatMixin {

    @Inject(method = "addCatalysts",at = @At("RETURN"))
    private void inject(IRecipeCatalystRegistration registration, CallbackInfo ci){
        CasingSets.getSets().stream().filter(set-> Objects.nonNull(set.getSlicer())).filter(CasingSet::doesGenerateSlicer)
                .forEach(set->registration.addRecipeCatalyst(set.getSlicer(), FDRecipeTypes.CUTTING));
    }
}
