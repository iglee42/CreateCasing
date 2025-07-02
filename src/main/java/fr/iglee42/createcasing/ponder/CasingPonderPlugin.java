package fr.iglee42.createcasing.ponder;

import fr.iglee42.createcasing.CreateCasing;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class CasingPonderPlugin implements PonderPlugin {
    @Override
    public String getModId() {
        return CreateCasing.MODID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        CasingPonderScenes.register(helper);
    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        CasingPonderTags.register(helper);
    }
}
