package fr.iglee42.createcasing.casings;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllSpriteShifts;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import fr.iglee42.createcasing.registries.EncasedSprites;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CasingSets {

    private static final List<CasingSet> sets = new ArrayList<>();

    public static final CasingSet ANDESITE = register("andesite",new CasingSet.Options()
            .existingCasing(()-> AllBlocks.ANDESITE_CASING.get())
            .ctSprite(()->AllSpriteShifts.ANDESITE_CASING)
            .existingShaft(()->AllBlocks.ANDESITE_ENCASED_SHAFT.get())
            .existingCogwheel(()->AllBlocks.ANDESITE_ENCASED_COGWHEEL.get())
            .existingLargeCogwheel(()->AllBlocks.ANDESITE_ENCASED_LARGE_COGWHEEL.get())
            .existingDepot(()->AllBlocks.DEPOT.get())
            .existingPress(()->AllBlocks.MECHANICAL_PRESS.get())
            .existingMixer(()->AllBlocks.MECHANICAL_MIXER.get())
            .existingChainDrive(()->AllBlocks.ENCASED_CHAIN_DRIVE.get())
            .existingChainGearshift(()->AllBlocks.ADJUSTABLE_CHAIN_GEARSHIFT.get())
            .existingChainConveyor(()->AllBlocks.CHAIN_CONVEYOR.get())
            .existingGearshift(()->AllBlocks.GEARSHIFT.get())
            .existingClutch(()->AllBlocks.CLUTCH.get())
            .existingGearbox(()->AllBlocks.GEARBOX.get(),()-> AllItems.VERTICAL_GEARBOX.get())
            .existingDeployer(()->AllBlocks.DEPLOYER.get())
            .fluids()
            .encasedCustomTransmissionBlocks()
            .configurableGearbox()
    );
    public static final CasingSet BRASS = register("brass",new CasingSet.Options()
            .existingCasing(()-> AllBlocks.BRASS_CASING.get())
            .ctSprite(()->AllSpriteShifts.BRASS_CASING)
            .existingShaft(()->AllBlocks.BRASS_ENCASED_SHAFT.get())
            .existingCogwheel(()->AllBlocks.BRASS_ENCASED_COGWHEEL.get())
            .existingLargeCogwheel(()->AllBlocks.BRASS_ENCASED_LARGE_COGWHEEL.get())
            .fluids()
            .encasedCustomTransmissionBlocks()
            .complexTransmissionBlocks(()->EncasedPartialModels.BRASS_CONVEYOR_GUARD,()->EncasedPartialModels.BRASS_CONVEYOR_WHEEL,()->EncasedPartialModels.BRASS_CONVEYOR_SHAFT)
            .processingBlocks(()->EncasedPartialModels.BRASS_MIXER_HEAD)
    );
    public static final CasingSet COPPER = register("copper",new CasingSet.Options()
            .existingCasing(()->AllBlocks.COPPER_CASING.get())
            .ctSprite(()->AllSpriteShifts.COPPER_CASING)
            .shaft()
            .cogwheel(()-> EncasedSprites.COPPER_ENCASED_COGWHEEL_SIDE,()->EncasedSprites.COPPER_ENCASED_COGWHEEL_OTHERSIDE)
            .largeCogwheel()
            .encasedCustomTransmissionBlocks()
            .existingFluidPipe(()->AllBlocks.ENCASED_FLUID_PIPE.get())
            .belt(()->EncasedSprites.COPPER_BELT_CASING,()-> EncasedPartialModels.COPPER_BELT_COVER_X,()->EncasedPartialModels.COPPER_BELT_COVER_Z)
            .complexTransmissionBlocks(()->EncasedPartialModels.COPPER_CONVEYOR_GUARD,()->EncasedPartialModels.COPPER_CONVEYOR_WHEEL,()->EncasedPartialModels.COPPER_CONVEYOR_SHAFT)
            .processingBlocks(()->EncasedPartialModels.COPPER_MIXER_HEAD)
    );
    public static final CasingSet RAILWAY = register("railway",new CasingSet.Options()
            .existingCasing(()->AllBlocks.RAILWAY_CASING.get())
            .everythingExceptCasing(()->AllSpriteShifts.RAILWAY_CASING,()->EncasedSprites.RAILWAY_BELT_CASING,()-> EncasedPartialModels.RAILWAY_BELT_COVER_X,()->EncasedPartialModels.RAILWAY_BELT_COVER_Z,()-> EncasedSprites.RAILWAY_ENCASED_COGWHEEL_SIDE,()->EncasedSprites.RAILWAY_ENCASED_COGWHEEL_OTHERSIDE,()->EncasedPartialModels.RAILWAY_CONVEYOR_GUARD,()->EncasedPartialModels.RAILWAY_CONVEYOR_WHEEL,()->EncasedPartialModels.RAILWAY_CONVEYOR_SHAFT,()->EncasedPartialModels.RAILWAY_MIXER_HEAD)
    );
    public static final CasingSet SHADOW_STEEL = register("shadow_steel",new CasingSet.Options()
            .existingCasing(()->AllBlocks.SHADOW_STEEL_CASING.get())
            .everythingExceptCasing(()->AllSpriteShifts.SHADOW_STEEL_CASING,()->EncasedSprites.SHADOW_STEEL_BELT_CASING,()-> EncasedPartialModels.SHADOW_STEEL_BELT_COVER_X,()->EncasedPartialModels.SHADOW_STEEL_BELT_COVER_Z,()->EncasedSprites.SHADOW_STEEL_ENCASED_COGWHEEL_SIDE,()->EncasedSprites.SHADOW_STEEL_ENCASED_COGWHEEL_OTHERSIDE,()->EncasedPartialModels.SHADOW_STEEL_CONVEYOR_GUARD,()->EncasedPartialModels.SHADOW_STEEL_CONVEYOR_WHEEL,()->EncasedPartialModels.SHADOW_STEEL_CONVEYOR_SHAFT,()->EncasedPartialModels.SHADOW_STEEL_MIXER_HEAD)
    );
    public static final CasingSet REFINED_RADIANCE = register("refined_radiance",new CasingSet.Options()
            .existingCasing(()->AllBlocks.REFINED_RADIANCE_CASING.get())
            .everythingExceptCasing(()->AllSpriteShifts.REFINED_RADIANCE_CASING,()->EncasedSprites.REFINED_RADIANCE_BELT_CASING,()-> EncasedPartialModels.REFINED_RADIANCE_BELT_COVER_X,()->EncasedPartialModels.REFINED_RADIANCE_BELT_COVER_Z,()->EncasedSprites.REFINED_RADIANCE_ENCASED_COGWHEEL_SIDE,()->EncasedSprites.REFINED_RADIANCE_ENCASED_COGWHEEL_OTHERSIDE,()->EncasedPartialModels.REFINED_RADIANCE_CONVEYOR_GUARD,()->EncasedPartialModels.REFINED_RADIANCE_CONVEYOR_WHEEL,()->EncasedPartialModels.REFINED_RADIANCE_CONVEYOR_SHAFT,()->EncasedPartialModels.REFINED_RADIANCE_MIXER_HEAD)
    );
    public static final CasingSet CREATIVE = register("creative",new CasingSet.Options()
            .everything(()->AllSpriteShifts.CREATIVE_CASING,()->EncasedSprites.CREATIVE_BELT_CASING,()-> EncasedPartialModels.CREATIVE_BELT_COVER_X,()->EncasedPartialModels.CREATIVE_BELT_COVER_Z,()->EncasedSprites.CREATIVE_ENCASED_COGWHEEL_SIDE,()->EncasedSprites.CREATIVE_ENCASED_COGWHEEL_OTHERSIDE,()->EncasedPartialModels.CREATIVE_CONVEYOR_GUARD,()->EncasedPartialModels.CREATIVE_CONVEYOR_WHEEL,()->EncasedPartialModels.CREATIVE_CONVEYOR_SHAFT,()->EncasedPartialModels.CREATIVE_MIXER_HEAD)
    );
    public static final CasingSet INDUSTRIAL_IRON = register("industrial_iron",new CasingSet.Options()
            .existingCasing(()->AllBlocks.INDUSTRIAL_IRON_BLOCK.get())
            .everythingExceptCasing(()->null,()->EncasedSprites.INDUSTRIAL_IRON_BELT_CASING,()-> EncasedPartialModels.INDUSTRIAL_IRON_BELT_COVER_X,()->EncasedPartialModels.INDUSTRIAL_IRON_BELT_COVER_Z,()->null,()->null,()->EncasedPartialModels.INDUSTRIAL_IRON_CONVEYOR_GUARD,()->EncasedPartialModels.INDUSTRIAL_IRON_CONVEYOR_WHEEL,()->EncasedPartialModels.INDUSTRIAL_IRON_CONVEYOR_SHAFT,()->EncasedPartialModels.INDUSTRIAL_IRON_MIXER_HEAD)

    );
    public static final CasingSet WEATHERED_IRON = register("weathered_iron",new CasingSet.Options()
            .existingCasing(()->AllBlocks.WEATHERED_IRON_BLOCK.get())
            .everythingExceptCasing(()->null,()->EncasedSprites.WEATHERED_IRON_BELT_CASING,()-> EncasedPartialModels.WEATHERED_IRON_BELT_COVER_X,()->EncasedPartialModels.WEATHERED_IRON_BELT_COVER_Z,()->null,()->null,()->EncasedPartialModels.WEATHERED_IRON_CONVEYOR_GUARD,()->EncasedPartialModels.WEATHERED_IRON_CONVEYOR_WHEEL,()->EncasedPartialModels.WEATHERED_IRON_CONVEYOR_SHAFT,()->EncasedPartialModels.WEATHERED_IRON_MIXER_HEAD)
    );

    public static CasingSet register(String id, CasingSet.Options options){
        String name = id.toLowerCase(Locale.ROOT);
        if (sets.stream().anyMatch(set->set.getName().equalsIgnoreCase(name)))
            throw new IllegalArgumentException("A casing set with name `" + name + "` already exists !");
        CasingSet set = new CasingSet(name,options);
        sets.add(set);
        return set;
    }

    public static List<CasingSet> getSets() {
        return ImmutableList.copyOf(sets);
    }
}
