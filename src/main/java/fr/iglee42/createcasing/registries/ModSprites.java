package fr.iglee42.createcasing.registries;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import com.simibubi.create.foundation.block.render.SpriteShiftEntry;
import com.simibubi.create.foundation.block.render.SpriteShifter;
import fr.iglee42.createcasing.CreateCasing;


public class ModSprites {

    public static final CTSpriteShiftEntry RAILWAY_ENCASED_COGWHEEL_SIDE;
    public static final CTSpriteShiftEntry RAILWAY_ENCASED_COGWHEEL_OTHERSIDE;
    public static final CTSpriteShiftEntry COPPER_ENCASED_COGWHEEL_SIDE;
    public static final CTSpriteShiftEntry COPPER_ENCASED_COGWHEEL_OTHERSIDE;
    public static final CTSpriteShiftEntry SHADOW_ENCASED_COGWHEEL_SIDE;
    public static final CTSpriteShiftEntry SHADOW_ENCASED_COGWHEEL_OTHERSIDE;
    public static final CTSpriteShiftEntry RADIANCE_ENCASED_COGWHEEL_SIDE;
    public static final CTSpriteShiftEntry RADIANCE_ENCASED_COGWHEEL_OTHERSIDE;
    //public static final CTSpriteShiftEntry CREATIVE_CASING;
    public static final CTSpriteShiftEntry CREATIVE_ENCASED_COGWHEEL_SIDE;
    public static final CTSpriteShiftEntry CREATIVE_ENCASED_COGWHEEL_OTHERSIDE;

    public static final SpriteShiftEntry COPPER_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/copper_belt_casing");
    public static final SpriteShiftEntry RAILWAY_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/copper_belt_casing");
    public static final SpriteShiftEntry INDUSTRIAL_IRON_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/copper_belt_casing");
    public static final SpriteShiftEntry CREATIVE_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/copper_belt_casing");

    private static CTSpriteShiftEntry horizontal(String name) {
        return getCT(AllCTTypes.HORIZONTAL, name);
    }

    private static CTSpriteShiftEntry vertical(String name) {
        return getCT(AllCTTypes.VERTICAL, name);
    }
    private static CTSpriteShiftEntry omni(String name) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }

    private static SpriteShiftEntry get(String originalLocation, String targetLocation) {
        return SpriteShifter.get(CreateCasing.asResource(originalLocation), CreateCasing.asResource(targetLocation));
    }

    private static SpriteShiftEntry getFromCreate(String originalLocation, String targetLocation) {
        return SpriteShifter.get(Create.asResource(originalLocation), CreateCasing.asResource(targetLocation));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, CreateCasing.asResource("block/" + blockTextureName), CreateCasing.asResource("block/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
        return getCT(type, blockTextureName, blockTextureName);
    }


    static {
        RAILWAY_ENCASED_COGWHEEL_SIDE = vertical("railway_encased_cogwheel_side");
        RAILWAY_ENCASED_COGWHEEL_OTHERSIDE = horizontal("railway_encased_cogwheel_side");
        COPPER_ENCASED_COGWHEEL_SIDE = vertical("copper_encased_cogwheel_side");
        COPPER_ENCASED_COGWHEEL_OTHERSIDE = horizontal("copper_encased_cogwheel_side");
        SHADOW_ENCASED_COGWHEEL_SIDE = vertical("shadow_encased_cogwheel_side");
        SHADOW_ENCASED_COGWHEEL_OTHERSIDE = horizontal("shadow_encased_cogwheel_side");
        RADIANCE_ENCASED_COGWHEEL_SIDE = vertical("radiance_encased_cogwheel_side");
        RADIANCE_ENCASED_COGWHEEL_OTHERSIDE = horizontal("radiance_encased_cogwheel_side");
        //CREATIVE_CASING = omni("creative_casing");
        CREATIVE_ENCASED_COGWHEEL_SIDE = vertical("creative_encased_cogwheel_side");
        CREATIVE_ENCASED_COGWHEEL_OTHERSIDE = horizontal("creative_encased_cogwheel_side");
    }

}
