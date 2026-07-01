package fr.iglee42.createcasing.kubejs.builders;

import com.google.common.base.Preconditions;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.latvian.mods.kubejs.script.SourceLine;
import dev.latvian.mods.rhino.util.HideFromJS;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.registries.EncasedPartialModels;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class CasingSetBuilder {

    private final String name;
    public SourceLine sourceLine;
    private CasingSet.Options options;


    public CasingSetBuilder(String name) {
        this.name = name;
        this.sourceLine = SourceLine.UNKNOWN;
        this.options = new CasingSet.Options().kjsGenerated();
    }

    @HideFromJS
    public String getName() {
        return name;
    }

    @HideFromJS
    public CasingSet.Options getOptions() {
        return options;
    }

    public CasingSetBuilder ctSprite(Supplier<CTSpriteShiftEntry> ctSprite) {
        Preconditions.checkNotNull(ctSprite,"Connected Texture Sprite Supplier can't be null");
        this.options.ctSprite(ctSprite);
        return this;
    }

    public CasingSetBuilder existingCasing(ResourceLocation casingId) {
        this.options.existingCasing(()-> BuiltInRegistries.BLOCK.get(casingId));
        return this;
    }


    public CasingSetBuilder casing() {
        this.options.casing();
        return this;
    }

    public CasingSetBuilder shaft() {
        this.options.shaft();
        return this;
    }


    public CasingSetBuilder cogwheel(@Nullable Supplier<CTSpriteShiftEntry> sideSprite, @Nullable Supplier<CTSpriteShiftEntry> otherSideSprite) {
        this.options.cogwheel(sideSprite,otherSideSprite);
        return this;
    }

    public CasingSetBuilder largeCogwheel() {
        this.options.largeCogwheel();
        return this;
    }

    public CasingSetBuilder fluidPipe() {
        this.options.fluidPipe();
        return this;
    }

    public CasingSetBuilder belt(@Nonnull Supplier<SpriteShiftEntry> sprite, ResourceLocation alongXBeltModel, ResourceLocation alongZBeltModel){
        PartialModel alongXModel = EncasedPartialModels.block(alongXBeltModel);
        PartialModel alongZModel = EncasedPartialModels.block(alongZBeltModel);
        this.options.belt(sprite,()->alongXModel,()->alongZModel);
        return this;
    }

    public CasingSetBuilder gearbox(){
        this.options.gearbox();
        return this;
    }

    public CasingSetBuilder press(){
        this.options.press();
        return this;
    }

    public CasingSetBuilder mixer(ResourceLocation headModel){
        PartialModel head = EncasedPartialModels.block(headModel);
        this.options.mixer(()->head);
        return this;
    }

    public CasingSetBuilder depot(){
        this.options.depot();
        return this;
    }

    public CasingSetBuilder deployer(){
        this.options.deployer();
        return this;
    }

    public CasingSetBuilder portableStorageInterface(){
        this.options.portableStorageInterface();
        return this;
    }

    public CasingSetBuilder chainDrive(){
        this.options.chainDrive();
        return this;
    }

    public CasingSetBuilder chainGearshift(){
        this.options.chainGearshift();
        return this;
    }

    public CasingSetBuilder configurableGearbox(){
        this.options.configurableGearbox();
        return this;
    }

    public CasingSetBuilder gearshift(){
        this.options.gearshift();
        return this;
    }

    public CasingSetBuilder clutch(){
        this.options.clutch();
        return this;
    }

    public CasingSetBuilder autoClutch(){
        this.options.autoClutch();
        return this;
    }

    public CasingSetBuilder encasedFan(){
        this.options.encasedFan();
        return this;
    }

    public CasingSetBuilder harvester(){
        this.options.harvester();
        return this;
    }

    public CasingSetBuilder saw(){
        this.options.saw();
        return this;
    }

    public CasingSetBuilder drill(ResourceLocation headModel){
        PartialModel head = EncasedPartialModels.block(headModel);
        this.options.drill(()->head);
        return this;
    }

    public CasingSetBuilder plough(){
        this.options.plough();
        return this;
    }

    public CasingSetBuilder roller(ResourceLocation frameModel){
        PartialModel frame = EncasedPartialModels.block(frameModel);
        this.options.roller(()->frame);
        return this;
    }

    public CasingSetBuilder slicer(){
        this.options.slicer();
        return this;
    }

    public CasingSetBuilder encasedCustomShaft(){
        this.options.encasedCustomShaft();
        return this;
    }

    public CasingSetBuilder encasedCustomCogwheel(){
        this.options.encasedCustomCogwheel();
        return this;
    }
    public CasingSetBuilder encasedCustomLargeCogwheel(){
        this.options.encasedCustomLargeCogwheel();
        return this;
    }

    public CasingSetBuilder encasedCustomPipe(){
        this.options.encasedCustomPipe();
        return this;
    }

    public CasingSetBuilder chainConveyor(Supplier<PartialModel> guard, Supplier<PartialModel> wheel, Supplier<PartialModel> shaft){
        this.options.chainConveyor(guard,wheel,shaft);
        return this;
    }

    public CasingSetBuilder processingBlocks(ResourceLocation mixerHeadModel){
        return press().mixer(mixerHeadModel).depot().deployer().encasedFan().slicer();
    }

    public CasingSetBuilder complexTransmissionBlocks(Supplier<PartialModel> conveyorGuard, Supplier<PartialModel> conveyorWheel, Supplier<PartialModel> conveyorShaft){
        return gearbox().chainDrive().chainGearshift().configurableGearbox().chainConveyor(conveyorGuard,conveyorWheel,conveyorShaft).gearshift().clutch().autoClutch();
    }

    public CasingSetBuilder simpleTransmissions(@Nullable Supplier<CTSpriteShiftEntry> cogwheelSideSprite, @Nullable Supplier<CTSpriteShiftEntry> cogwheelOtherSideSprite){
        return shaft().cogwheel(cogwheelSideSprite,cogwheelOtherSideSprite).largeCogwheel();
    }

    public CasingSetBuilder encasedCustomTransmissionBlocks(){
        return encasedCustomShaft().encasedCustomCogwheel().encasedCustomLargeCogwheel();
    }

    public CasingSetBuilder contraptionBlocks(ResourceLocation drillHeadModel,ResourceLocation rollerFrameModel){
        return portableStorageInterface().harvester().saw().drill(drillHeadModel).plough().roller(rollerFrameModel);
    }

    public CasingSetBuilder fluids(){
        return fluidPipe().encasedCustomPipe();
    }

    public CasingSetBuilder everythingExceptCasing(Supplier<CTSpriteShiftEntry> ctSprite, @Nonnull Supplier<SpriteShiftEntry> beltSprite, ResourceLocation alongXBeltModel, ResourceLocation alongZBeltModel, @Nullable Supplier<CTSpriteShiftEntry> cogwheelSideSprite, @Nullable Supplier<CTSpriteShiftEntry> cogwheelOtherSideSprite, Supplier<PartialModel> conveyorGuard, Supplier<PartialModel> conveyorWheel, Supplier<PartialModel> conveyorShaft, ResourceLocation mixerHeadModel, ResourceLocation drillHeadModel,ResourceLocation rollerFrameModel){
        return ctSprite(ctSprite).contraptionBlocks(drillHeadModel,rollerFrameModel).encasedCustomTransmissionBlocks().simpleTransmissions(cogwheelSideSprite,cogwheelOtherSideSprite).belt(beltSprite,alongXBeltModel,alongZBeltModel).processingBlocks(mixerHeadModel).complexTransmissionBlocks(conveyorGuard,conveyorWheel,conveyorShaft).fluids();
    }

    public CasingSetBuilder everything(Supplier<CTSpriteShiftEntry> ctSprite, @Nonnull Supplier<SpriteShiftEntry> beltSprite, ResourceLocation alongXBeltModel, ResourceLocation alongZBeltModel, @Nullable Supplier<CTSpriteShiftEntry> cogwheelSideSprite, @Nullable Supplier<CTSpriteShiftEntry> cogwheelOtherSideSprite, Supplier<PartialModel> conveyorGuard, Supplier<PartialModel> conveyorWheel, Supplier<PartialModel> conveyorShaft, ResourceLocation mixerHeadModel, ResourceLocation drillHeadModel,ResourceLocation rollerFrameModel){
        return casing().everythingExceptCasing(ctSprite, beltSprite, alongXBeltModel, alongZBeltModel, cogwheelSideSprite, cogwheelOtherSideSprite,conveyorGuard,conveyorWheel,conveyorShaft,mixerHeadModel,drillHeadModel,rollerFrameModel);
    }
}
