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

    GLASS_SHAFT = block("glass_shaft");

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
