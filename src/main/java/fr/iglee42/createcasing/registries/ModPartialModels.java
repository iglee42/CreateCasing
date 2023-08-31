package fr.iglee42.createcasing.registries;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.Create;
import net.minecraft.resources.ResourceLocation;

import static fr.iglee42.createcasing.CreateCasing.MODID;

public class ModPartialModels {

    public static final PartialModel

            BRASS_MIXER_POLE = block("brass_mixer/pole"), BRASS_MIXER_HEAD = block("brass_mixer/head"),
            COPPER_MIXER_POLE = block("copper_mixer/pole"), COPPER_MIXER_HEAD = block("copper_mixer/head"),
            RAILWAY_MIXER_POLE = block("railway_mixer/pole"), RAILWAY_MIXER_HEAD = block("railway_mixer/head"),
            INDUSTRIAL_IRON_MIXER_POLE = block("industrial_iron_mixer/pole"), INDUSTRIAL_IRON_MIXER_HEAD = block("industrial_iron_mixer/head"),

            GLASS_SHAFT = block("glass_shaft")

            ;

    private static PartialModel block(String path) {
        return new PartialModel(new ResourceLocation(MODID,"block/"+path));
    }

    public static void init(){}

}
