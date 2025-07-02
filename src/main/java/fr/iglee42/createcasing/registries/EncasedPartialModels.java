package fr.iglee42.createcasing.registries;


import com.simibubi.create.AllPartialModels;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.CreateCasing;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncasedPartialModels {

    public static final List<PartialModel> ALL_ENCASED_MODELS = new ArrayList<>();
    public static final PartialModel
            BRASS_MIXER_HEAD = block("mixer/brass/head"),
            COPPER_MIXER_HEAD = block("mixer/copper/head"),
            RAILWAY_MIXER_HEAD = block("mixer/railway/head"),
            INDUSTRIAL_IRON_MIXER_HEAD = block("mixer/industrial_iron/head"),
            WEATHERED_IRON_MIXER_HEAD = block("mixer/weathered_iron/head"),
            CREATIVE_MIXER_HEAD = block("mixer/creative/head"),
            REFINED_RADIANCE_MIXER_HEAD = block("mixer/refined_radiance/head"),
            SHADOW_STEEL_MIXER_HEAD = block("mixer/shadow_steel/head"),

    GLASS_SHAFT = block("shaft/glass"),
            BRASS_SHAFT = block("shaft/brass"),
            MLDEG_SHAFT = block("shaft/mldeg"),

    COPPER_BELT_COVER_X = block("belt_cover/copper_belt_cover_x"), COPPER_BELT_COVER_Z = block("belt_cover/copper_belt_cover_z"),
            RAILWAY_BELT_COVER_X = block("belt_cover/railway_belt_cover_x"), RAILWAY_BELT_COVER_Z = block("belt_cover/railway_belt_cover_z"),
            INDUSTRIAL_IRON_BELT_COVER_X = block("belt_cover/industrial_iron_belt_cover_x"), INDUSTRIAL_IRON_BELT_COVER_Z = block("belt_cover/industrial_iron_belt_cover_z"),
            CREATIVE_BELT_COVER_X = block("belt_cover/creative_belt_cover_x"), CREATIVE_BELT_COVER_Z = block("belt_cover/creative_belt_cover_z"),
            WEATHERED_IRON_BELT_COVER_X = block("belt_cover/weathered_iron_belt_cover_x"), WEATHERED_IRON_BELT_COVER_Z = block("belt_cover/weathered_iron_belt_cover_z"),
            REFINED_RADIANCE_BELT_COVER_X = block("belt_cover/refined_radiance_belt_cover_x"), REFINED_RADIANCE_BELT_COVER_Z = block("belt_cover/refined_radiance_belt_cover_z"),
            SHADOW_STEEL_BELT_COVER_X = block("belt_cover/shadow_steel_belt_cover_x"), SHADOW_STEEL_BELT_COVER_Z = block("belt_cover/shadow_steel_belt_cover_z"),

    BRASS_CONVEYOR_WHEEL = block("chain_conveyor/brass/wheel"),
    COPPER_CONVEYOR_WHEEL = block("chain_conveyor/copper/wheel"),
    RAILWAY_CONVEYOR_WHEEL = block("chain_conveyor/railway/wheel"),
    INDUSTRIAL_IRON_CONVEYOR_WHEEL = block("chain_conveyor/industrial_iron/wheel"),
    WEATHERED_IRON_CONVEYOR_WHEEL = block("chain_conveyor/weathered_iron/wheel"),
    CREATIVE_CONVEYOR_WHEEL = block("chain_conveyor/creative/wheel"),
    REFINED_RADIANCE_CONVEYOR_WHEEL = block("chain_conveyor/refined_radiance/wheel"),
    SHADOW_STEEL_CONVEYOR_WHEEL = block("chain_conveyor/shadow_steel/wheel"),

    BRASS_CONVEYOR_SHAFT = block("chain_conveyor/brass/shaft"),
            COPPER_CONVEYOR_SHAFT = block("chain_conveyor/copper/shaft"),
            RAILWAY_CONVEYOR_SHAFT = block("chain_conveyor/railway/shaft"),
            INDUSTRIAL_IRON_CONVEYOR_SHAFT = block("chain_conveyor/industrial_iron/shaft"),
            WEATHERED_IRON_CONVEYOR_SHAFT = block("chain_conveyor/weathered_iron/shaft"),
            CREATIVE_CONVEYOR_SHAFT = block("chain_conveyor/creative/shaft"),
            REFINED_RADIANCE_CONVEYOR_SHAFT = block("chain_conveyor/refined_radiance/shaft"),
            SHADOW_STEEL_CONVEYOR_SHAFT = block("chain_conveyor/shadow_steel/shaft"),
            BRASS_CONVEYOR_GUARD = block("chain_conveyor/brass/guard"),
            COPPER_CONVEYOR_GUARD = block("chain_conveyor/copper/guard"),
            RAILWAY_CONVEYOR_GUARD = block("chain_conveyor/railway/guard"),
            INDUSTRIAL_IRON_CONVEYOR_GUARD = block("chain_conveyor/industrial_iron/guard"),
            WEATHERED_IRON_CONVEYOR_GUARD = block("chain_conveyor/weathered_iron/guard"),
            CREATIVE_CONVEYOR_GUARD = block("chain_conveyor/creative/guard"),
            REFINED_RADIANCE_CONVEYOR_GUARD = block("chain_conveyor/refined_radiance/guard"),
            SHADOW_STEEL_CONVEYOR_GUARD = block("chain_conveyor/shadow_steel/guard");

    public static final Map<String, PartialModel> SHAFT_MODELS = new HashMap<>();
    public static final Map<String, PartialModel> COGS_MODELS = new HashMap<>();
    public static final Map<String, PartialModel> SHAFTLESS_COGS_MODELS = new HashMap<>();
    public static final Map<String, PartialModel> LARGE_COGS_MODELS = new HashMap<>();
    public static final Map<String, PartialModel> SHAFTLESS_LARGE_COGS_MODELS = new HashMap<>();

    static {
        String[] woods = new String[]{"oak", "birch", "acacia", "jungle", "warped", "dark_oak", "crimson", "mangrove", "cherry", "bamboo"};
        for (String w : woods) {
            SHAFT_MODELS.put(w, EncasedPartialModels.block("shaft/" + w));
            COGS_MODELS.put(w, EncasedPartialModels.block("cogwheel/" + w));
            SHAFTLESS_COGS_MODELS.put(w, EncasedPartialModels.block("cogwheel_shaftless/" + w));
            LARGE_COGS_MODELS.put(w, EncasedPartialModels.block("large_cogwheel/" + w));
            SHAFTLESS_LARGE_COGS_MODELS.put(w, EncasedPartialModels.block("large_cogwheel_shaftless/" + w));
        }

        SHAFT_MODELS.put("spruce", EncasedPartialModels.block("shaft/spruce"));
        SHAFT_MODELS.put("mldeg", EncasedPartialModels.block("shaft/mldeg"));
        SHAFT_MODELS.put("glass", EncasedPartialModels.block("shaft/glass"));
    }

    public static PartialModel block(String path) {
        PartialModel model = PartialModel.of(CreateCasing.asResource("block/" + path));
        if (ALL_ENCASED_MODELS != null) {
            ALL_ENCASED_MODELS.add(model);
        }
        return model;
    }

    public static void init() {


    }

    public static PartialModel getChainConveyorWheel(BlockState state){
        if (EncasedBlocks.BRASS_CHAIN_CONVEYOR.has(state)) return BRASS_CONVEYOR_WHEEL;
        if (EncasedBlocks.COPPER_CHAIN_CONVEYOR.has(state)) return COPPER_CONVEYOR_WHEEL;
        if (EncasedBlocks.RAILWAY_CHAIN_CONVEYOR.has(state)) return RAILWAY_CONVEYOR_WHEEL;
        if (EncasedBlocks.CREATIVE_CHAIN_CONVEYOR.has(state)) return CREATIVE_CONVEYOR_WHEEL;
        if (EncasedBlocks.INDUSTRIAL_IRON_CHAIN_CONVEYOR.has(state)) return INDUSTRIAL_IRON_CONVEYOR_WHEEL;
        if (EncasedBlocks.WEATHERED_IRON_CHAIN_CONVEYOR.has(state)) return WEATHERED_IRON_CONVEYOR_WHEEL;
        if (EncasedBlocks.REFINED_RADIANCE_CHAIN_CONVEYOR.has(state)) return REFINED_RADIANCE_CONVEYOR_WHEEL;
        if (EncasedBlocks.SHADOW_STEEL_CHAIN_CONVEYOR.has(state)) return SHADOW_STEEL_CONVEYOR_WHEEL;
        return AllPartialModels.CHAIN_CONVEYOR_WHEEL;
    }

    public static PartialModel getChainConveyorGuard(BlockState state){
        if (EncasedBlocks.BRASS_CHAIN_CONVEYOR.has(state)) return BRASS_CONVEYOR_GUARD;
        if (EncasedBlocks.COPPER_CHAIN_CONVEYOR.has(state)) return COPPER_CONVEYOR_GUARD;
        if (EncasedBlocks.RAILWAY_CHAIN_CONVEYOR.has(state)) return RAILWAY_CONVEYOR_GUARD;
        if (EncasedBlocks.CREATIVE_CHAIN_CONVEYOR.has(state)) return CREATIVE_CONVEYOR_GUARD;
        if (EncasedBlocks.INDUSTRIAL_IRON_CHAIN_CONVEYOR.has(state)) return INDUSTRIAL_IRON_CONVEYOR_GUARD;
        if (EncasedBlocks.WEATHERED_IRON_CHAIN_CONVEYOR.has(state)) return WEATHERED_IRON_CONVEYOR_GUARD;
        if (EncasedBlocks.REFINED_RADIANCE_CHAIN_CONVEYOR.has(state)) return REFINED_RADIANCE_CONVEYOR_GUARD;
        if (EncasedBlocks.SHADOW_STEEL_CHAIN_CONVEYOR.has(state)) return SHADOW_STEEL_CONVEYOR_GUARD;
        return AllPartialModels.CHAIN_CONVEYOR_GUARD;
    }

    public static PartialModel getChainConveyorShaft(BlockState state){
        if (EncasedBlocks.BRASS_CHAIN_CONVEYOR.has(state)) return BRASS_CONVEYOR_SHAFT;
        if (EncasedBlocks.COPPER_CHAIN_CONVEYOR.has(state)) return COPPER_CONVEYOR_SHAFT;
        if (EncasedBlocks.RAILWAY_CHAIN_CONVEYOR.has(state)) return RAILWAY_CONVEYOR_SHAFT;
        if (EncasedBlocks.CREATIVE_CHAIN_CONVEYOR.has(state)) return CREATIVE_CONVEYOR_SHAFT;
        if (EncasedBlocks.INDUSTRIAL_IRON_CHAIN_CONVEYOR.has(state)) return INDUSTRIAL_IRON_CONVEYOR_SHAFT;
        if (EncasedBlocks.WEATHERED_IRON_CHAIN_CONVEYOR.has(state)) return WEATHERED_IRON_CONVEYOR_SHAFT;
        if (EncasedBlocks.REFINED_RADIANCE_CHAIN_CONVEYOR.has(state)) return REFINED_RADIANCE_CONVEYOR_SHAFT;
        if (EncasedBlocks.SHADOW_STEEL_CHAIN_CONVEYOR.has(state)) return SHADOW_STEEL_CONVEYOR_SHAFT;
        return AllPartialModels.CHAIN_CONVEYOR_SHAFT;
    }
}
