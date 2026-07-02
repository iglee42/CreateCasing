package fr.iglee42.createcasing.compat.sliceanddice;

import com.possible_triangle.sliceanddice.SDConstantsKt;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class SliceAndDicePonders implements PonderPlugin {
    @Override
    public String getModId() {
        return SDConstantsKt.MOD_ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        EncasedSliceAndDiceCompat.registerPonderScenes(helper.withKeyFunction(like-> BuiltInRegistries.ITEM.getKey(like.asItem())));
    }
}
