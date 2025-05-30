package fr.iglee42.createcasing.registries;

import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import fr.iglee42.createcasing.CreateCasing;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SpriteShifter;


public class ModSprites {

    public static final CTSpriteShiftEntry RAILWAY_ENCASED_COGWHEEL_SIDE;
    public static final CTSpriteShiftEntry RAILWAY_ENCASED_COGWHEEL_OTHERSIDE;
    public static final CTSpriteShiftEntry COPPER_ENCASED_COGWHEEL_SIDE;
    public static final CTSpriteShiftEntry COPPER_ENCASED_COGWHEEL_OTHERSIDE;
    public static final CTSpriteShiftEntry SHADOW_STEEL_ENCASED_COGWHEEL_SIDE;
    public static final CTSpriteShiftEntry SHADOW_STEEL_ENCASED_COGWHEEL_OTHERSIDE;
    public static final CTSpriteShiftEntry REFINED_RADIANCE_ENCASED_COGWHEEL_SIDE;
    public static final CTSpriteShiftEntry REFINED_RADIANCE_ENCASED_COGWHEEL_OTHERSIDE;
    //public static final CTSpriteShiftEntry CREATIVE_CASING;
    public static final CTSpriteShiftEntry CREATIVE_ENCASED_COGWHEEL_SIDE;
    public static final CTSpriteShiftEntry CREATIVE_ENCASED_COGWHEEL_OTHERSIDE;

    public static final SpriteShiftEntry COPPER_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casings/copper");
    public static final SpriteShiftEntry RAILWAY_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casings/railway");
    public static final SpriteShiftEntry INDUSTRIAL_IRON_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casings/industrial_iron");
    public static final SpriteShiftEntry WEATHERED_IRON_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casings/weathered_iron");
    public static final SpriteShiftEntry CREATIVE_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casings/creative");

    public static final SpriteShiftEntry INDUSTRIAL_IRON = getFromCreate("block/industrial_iron_block");
    public static final SpriteShiftEntry WEATHERED_IRON = getFromCreate("block/weathered_iron_block");


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
    private static SpriteShiftEntry getFromCreate(String location) {
        return SpriteShifter.get(Create.asResource(location), CreateCasing.asResource(location));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, CreateCasing.asResource("block/" + blockTextureName), CreateCasing.asResource("block/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
        return getCT(type, blockTextureName, blockTextureName);
    }


    public static CTSpriteShiftEntry getEntryForCasing(String casing){
        return switch (casing){
            case "andesite"-> AllSpriteShifts.ANDESITE_CASING;
            case "brass"-> AllSpriteShifts.BRASS_CASING;
            case "copper"-> AllSpriteShifts.COPPER_CASING;
            case "railway"-> AllSpriteShifts.RAILWAY_CASING;
            case "creative"-> AllSpriteShifts.CREATIVE_CASING;
            case "shadow_steel"-> AllSpriteShifts.SHADOW_STEEL_CASING;
            case "refined_radiance"-> AllSpriteShifts.REFINED_RADIANCE_CASING;
            default -> null;
        };
    }

    public static CTSpriteShiftEntry getEntryForSide(String casing){
        return switch (casing){
            case "andesite"-> AllSpriteShifts.ANDESITE_ENCASED_COGWHEEL_SIDE;
            case "brass"-> AllSpriteShifts.BRASS_ENCASED_COGWHEEL_SIDE;
            case "copper"-> ModSprites.COPPER_ENCASED_COGWHEEL_SIDE;
            case "railway"-> ModSprites.RAILWAY_ENCASED_COGWHEEL_SIDE;
            case "creative"-> ModSprites.CREATIVE_ENCASED_COGWHEEL_SIDE;
            case "shadow_steel"-> ModSprites.SHADOW_STEEL_ENCASED_COGWHEEL_SIDE;
            case "refined_radiance"-> ModSprites.REFINED_RADIANCE_ENCASED_COGWHEEL_SIDE;
            default -> null;
        };
    }

    public static CTSpriteShiftEntry getEntryForOtherSide(String casing){
        return switch (casing){
            case "andesite"-> AllSpriteShifts.ANDESITE_ENCASED_COGWHEEL_OTHERSIDE;
            case "brass"-> AllSpriteShifts.BRASS_ENCASED_COGWHEEL_OTHERSIDE;
            case "copper"-> ModSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE;
            case "railway"-> ModSprites.RAILWAY_ENCASED_COGWHEEL_OTHERSIDE;
            case "creative"-> ModSprites.CREATIVE_ENCASED_COGWHEEL_OTHERSIDE;
            case "shadow_steel"-> ModSprites.SHADOW_STEEL_ENCASED_COGWHEEL_OTHERSIDE;
            case "refined_radiance"-> ModSprites.REFINED_RADIANCE_ENCASED_COGWHEEL_OTHERSIDE;
            default -> null;
        };
    }
    static {
        RAILWAY_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheels/railway");
        RAILWAY_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheels/railway");
        COPPER_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheels/copper");
        COPPER_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheels/copper");
        SHADOW_STEEL_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheels/shadow");
        SHADOW_STEEL_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheels/shadow");
        REFINED_RADIANCE_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheels/radiance");
        REFINED_RADIANCE_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheels/radiance");
        //CREATIVE_CASING = omni("creative_casing");
        CREATIVE_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheels/creative");
        CREATIVE_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheels/creative");
    }

}
