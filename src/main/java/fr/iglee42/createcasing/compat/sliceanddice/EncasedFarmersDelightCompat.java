package fr.iglee42.createcasing.compat.sliceanddice;

import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

import java.util.Objects;

public class EncasedFarmersDelightCompat
{

    public static void registerCatalysts(IRecipeCatalystRegistration registration) {
        CasingSets.getSets().stream().filter(set-> Objects.nonNull(set.getSlicer())).filter(CasingSet::doesGenerateSlicer)
                .forEach(set->registration.addRecipeCatalyst(set.getSlicer(), FDRecipeTypes.CUTTING));
    }
}
