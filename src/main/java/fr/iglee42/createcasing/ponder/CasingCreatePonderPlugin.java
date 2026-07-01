package fr.iglee42.createcasing.ponder;

import com.simibubi.create.Create;
import fr.iglee42.createcasing.CreateCasing;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class CasingCreatePonderPlugin implements PonderPlugin {
    @Override
    public String getModId() {
        return Create.ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        CasingPonderScenes.register(helper);
    }

}
