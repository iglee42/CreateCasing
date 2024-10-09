package fr.iglee42.createcasing.registries;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.Create;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

import static fr.iglee42.createcasing.CreateCasing.MODID;

public class ModPartialModels {

    public static final PartialModel

            BRASS_MIXER_HEAD = block("brass_mixer/head"), COPPER_MIXER_HEAD = block("copper_mixer/head"), RAILWAY_MIXER_HEAD = block("railway_mixer/head"), INDUSTRIAL_IRON_MIXER_HEAD = block("industrial_iron_mixer/head"),

    GLASS_SHAFT = block("glass_shaft"),

    COPPER_BELT_COVER_X = block("belt_cover/copper_belt_cover_x"), COPPER_BELT_COVER_Z = block("belt_cover/copper_belt_cover_z"),
    RAILWAY_BELT_COVER_X = block("belt_cover/railway_belt_cover_x"), RAILWAY_BELT_COVER_Z = block("belt_cover/railway_belt_cover_z"),
    INDUSTRIAL_IRON_BELT_COVER_X = block("belt_cover/industrial_iron_belt_cover_x"), INDUSTRIAL_IRON_BELT_COVER_Z = block("belt_cover/industrial_iron_belt_cover_z"),
    CREATIVE_BELT_COVER_X = block("belt_cover/creative_belt_cover_x"), CREATIVE_BELT_COVER_Z = block("belt_cover/creative_belt_cover_z")
            ;

    public static final Map<String,PartialModel> COGS_MODELS = new HashMap<>();
    public static final Map<String,PartialModel> LARGE_COGS_MODELS = new HashMap<>();

    public static PartialModel block(String path) {
        return new PartialModel(new ResourceLocation(MODID, "block/" + path));
    }

    public static void init() {
        String[] woods = new String[]{"oak","birch","acacia","jungle","warped","dark_oak","crimson","mangrove","cherry","bamboo"};
        for (String w : woods) {
          COGS_MODELS.put(w,ModPartialModels.block("cogwheel_shaftless/"+ w));
          LARGE_COGS_MODELS.put(w,ModPartialModels.block("large_cogwheel_shaftless/"+ w));
        }
    }

}
