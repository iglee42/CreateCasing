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

    public static final CTSpriteShiftEntry RAILWAY_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheel/railway");
    public static final CTSpriteShiftEntry RAILWAY_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheel/railway");
    public static final CTSpriteShiftEntry COPPER_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheel/copper");
    public static final CTSpriteShiftEntry COPPER_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheel/copper");
    public static final CTSpriteShiftEntry SHADOW_STEEL_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheel/shadow");
    public static final CTSpriteShiftEntry SHADOW_STEEL_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheel/shadow");
    public static final CTSpriteShiftEntry REFINED_RADIANCE_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheel/radiance");
    public static final CTSpriteShiftEntry REFINED_RADIANCE_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheel/radiance");
    public static final CTSpriteShiftEntry CREATIVE_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheel/creative");
    public static final CTSpriteShiftEntry CREATIVE_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheel/creative");
    public static final CTSpriteShiftEntry ZINC_ENCASED_COGWHEEL_SIDE = vertical("encased_cogwheel/zinc");
    public static final CTSpriteShiftEntry ZINC_ENCASED_COGWHEEL_OTHERSIDE = horizontal("encased_cogwheel/zinc");
    //public static final CTSpriteShiftEntry CREATIVE_CASING;
    public static final CTSpriteShiftEntry ZINC_CASING = omni("casing/zinc");


    public static final SpriteShiftEntry COPPER_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/copper");
    public static final SpriteShiftEntry RAILWAY_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/railway");
    public static final SpriteShiftEntry INDUSTRIAL_IRON_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/industrial_iron");
    public static final SpriteShiftEntry WEATHERED_IRON_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/weathered_iron");
    public static final SpriteShiftEntry CREATIVE_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/creative");
    public static final SpriteShiftEntry REFINED_RADIANCE_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/refined_radiance");
    public static final SpriteShiftEntry SHADOW_STEEL_BELT_CASING = getFromCreate("block/belt/brass_belt_casing", "block/belt_casing/shadow_steel");

    public static final SpriteShiftEntry INDUSTRIAL_IRON = getFromCreate("block/industrial_iron_block");
    public static final SpriteShiftEntry WEATHERED_IRON = getFromCreate("block/weathered_iron_block");

    public static final CTSpriteShiftEntry ANDESITE_FLUID_TANK = getCT(AllCTTypes.RECTANGLE, "fluid_tank/andesite");
    public static final CTSpriteShiftEntry ANDESITE_FLUID_TANK_TOP = getCT(AllCTTypes.RECTANGLE, "fluid_tank_top/andesite");
    public static final CTSpriteShiftEntry ANDESITE_FLUID_TANK_INNER = getCT(AllCTTypes.RECTANGLE, "fluid_tank_inner/andesite");

    public static final CTSpriteShiftEntry BRASS_FLUID_TANK = getCT(AllCTTypes.RECTANGLE, "fluid_tank/brass");
    public static final CTSpriteShiftEntry BRASS_FLUID_TANK_TOP = getCT(AllCTTypes.RECTANGLE, "fluid_tank_top/brass");
    public static final CTSpriteShiftEntry BRASS_FLUID_TANK_INNER = getCT(AllCTTypes.RECTANGLE, "fluid_tank_inner/brass");

    public static final CTSpriteShiftEntry ZINC_FLUID_TANK = getCT(AllCTTypes.RECTANGLE, "fluid_tank/zinc");
    public static final CTSpriteShiftEntry ZINC_FLUID_TANK_TOP = getCT(AllCTTypes.RECTANGLE, "fluid_tank_top/zinc");
    public static final CTSpriteShiftEntry ZINC_FLUID_TANK_INNER = getCT(AllCTTypes.RECTANGLE, "fluid_tank_inner/zinc");


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
