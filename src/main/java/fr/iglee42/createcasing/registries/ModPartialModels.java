package fr.iglee42.createcasing.registries;


import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.CreateCasing;

import java.util.HashMap;
import java.util.Map;

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
        return PartialModel.of(CreateCasing.asResource("block/" + path));
    }

    public static void init() {
        String[] woods = new String[]{"oak","birch","acacia","jungle","warped","dark_oak","crimson","mangrove","cherry","bamboo"};
        for (String w : woods) {
          COGS_MODELS.put(w,ModPartialModels.block("cogwheel/"+ w));
          LARGE_COGS_MODELS.put(w,ModPartialModels.block("large_cogwheel_shaftless/"+ w));
        }
    }

}
