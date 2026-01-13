package fr.iglee42.createcasing.registries;

import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import fr.iglee42.createcasing.CreateCasing;
import net.createmod.catnip.platform.CatnipServices;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SpriteShifter;
import net.minecraft.resources.ResourceLocation;


public class EncasedSprites {

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

    public static final SpriteShiftEntry COPPER_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/copper");
    public static final SpriteShiftEntry RAILWAY_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/railway");
    public static final SpriteShiftEntry INDUSTRIAL_IRON_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/industrial_iron");
    public static final SpriteShiftEntry WEATHERED_IRON_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/weathered_iron");
    public static final SpriteShiftEntry CREATIVE_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/creative");
    public static final SpriteShiftEntry REFINED_RADIANCE_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/refined_radiance");
    public static final SpriteShiftEntry SHADOW_STEEL_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/shadow_steel");

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
        return get(CreateCasing.asResource(originalLocation), CreateCasing.asResource(targetLocation));
    }

    private static SpriteShiftEntry getFromCreate(String originalLocation, String targetLocation) {
        return get(Create.asResource(originalLocation), CreateCasing.asResource(targetLocation));
    }
    private static SpriteShiftEntry getFromCreate(String location) {
        return get(Create.asResource(location), CreateCasing.asResource(location));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
        return getCT(type, CreateCasing.asResource("block/" + blockTextureName), CreateCasing.asResource("block/" + connectedTextureName + "_connected"));
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
            case "copper"-> EncasedSprites.COPPER_ENCASED_COGWHEEL_SIDE;
            case "railway"-> EncasedSprites.RAILWAY_ENCASED_COGWHEEL_SIDE;
            case "creative"-> EncasedSprites.CREATIVE_ENCASED_COGWHEEL_SIDE;
            case "shadow_steel"-> EncasedSprites.SHADOW_STEEL_ENCASED_COGWHEEL_SIDE;
            case "refined_radiance"-> EncasedSprites.REFINED_RADIANCE_ENCASED_COGWHEEL_SIDE;
            default -> null;
        };
    }

    public static CTSpriteShiftEntry getEntryForOtherSide(String casing){
        return switch (casing){
            case "andesite"-> AllSpriteShifts.ANDESITE_ENCASED_COGWHEEL_OTHERSIDE;
            case "brass"-> AllSpriteShifts.BRASS_ENCASED_COGWHEEL_OTHERSIDE;
            case "copper"-> EncasedSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE;
            case "railway"-> EncasedSprites.RAILWAY_ENCASED_COGWHEEL_OTHERSIDE;
            case "creative"-> EncasedSprites.CREATIVE_ENCASED_COGWHEEL_OTHERSIDE;
            case "shadow_steel"-> EncasedSprites.SHADOW_STEEL_ENCASED_COGWHEEL_OTHERSIDE;
            case "refined_radiance"-> EncasedSprites.REFINED_RADIANCE_ENCASED_COGWHEEL_OTHERSIDE;
            default -> null;
        };
    }
    static {
        RAILWAY_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheel/railway");
        RAILWAY_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheel/railway");
        COPPER_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheel/copper");
        COPPER_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheel/copper");
        SHADOW_STEEL_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheel/shadow");
        SHADOW_STEEL_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheel/shadow");
        REFINED_RADIANCE_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheel/radiance");
        REFINED_RADIANCE_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheel/radiance");
        //CREATIVE_CASING = omni("creative_casing");
        CREATIVE_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheel/creative");
        CREATIVE_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheel/creative");
    }

    private static CTSpriteShiftEntry getCT(CTType type, ResourceLocation blockTexture, ResourceLocation connectedTexture){
        CTSpriteShiftEntry entry = new CTSpriteShiftEntry(type);
        if (CatnipServices.PLATFORM.getEnv().isClient())
            entry.set(blockTexture, connectedTexture);
        return entry;
    }

    public static SpriteShiftEntry get(ResourceLocation originalLocation, ResourceLocation targetLocation) {
        SpriteShiftEntry entry = new SpriteShiftEntry();
        CatnipServices.PLATFORM.executeOnClientOnly(() -> () -> entry.set(originalLocation, targetLocation));
        return entry;
    }
}
