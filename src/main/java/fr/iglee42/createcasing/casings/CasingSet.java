package fr.iglee42.createcasing.casings;

import com.google.common.base.Preconditions;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class CasingSet {

    private final String name;
    private final Supplier<CTSpriteShiftEntry> ctSprite;
    private final @Nullable Supplier<CTSpriteShiftEntry> cogSideSprite;
    private final @Nullable Supplier<CTSpriteShiftEntry> cogOtherSideSprite;
    private final @Nullable Supplier<SpriteShiftEntry> beltSprite;


    private @Nullable BeltBlockEntity.CasingType beltCasingType;
    private Supplier<PartialModel> alongXBeltModel;
    private Supplier<PartialModel> alongZBeltModel;

    private @Nullable Supplier<? extends Block> casingBlock;
    private @Nullable Supplier<? extends Block> shaftBlock;
    private @Nullable Supplier<? extends Block> cogwheelBlock;
    private @Nullable Supplier<? extends Block> largeCogwheelBlock;
    private @Nullable Supplier<? extends Block> fluidPipeBlock;
    private @Nullable Supplier<? extends Block> gearboxBlock;
    private @Nullable Supplier<? extends BlockItem> verticalGearboxBlockItem;
    private @Nullable Supplier<? extends Block> pressBlock;
    private @Nullable Supplier<? extends Block> mixerBlock;
    private @Nullable Supplier<? extends Block> depotBlock;
    private @Nullable Supplier<? extends Block> chainDriveBlock;
    private @Nullable Supplier<? extends Block> chainGearshiftBlock;
    private @Nullable Supplier<? extends Block> configurableGearboxBlock;
    private @Nullable Supplier<? extends Block> chainConveyorBlock;
    private @Nullable Supplier<? extends Block> gearshiftBlock;
    private @Nullable Supplier<? extends Block> clutchBlock;
    private @Nullable Supplier<? extends Block> deployerBlock;
    private @Nullable Supplier<? extends Block> storageInterfaceBlock;
    private @Nullable Supplier<? extends Block> encasedFanBlock;
    private @Nullable Supplier<? extends Block> harvesterBlock;
    private @Nullable Supplier<? extends Block> sawBlock;
    private @Nullable Supplier<? extends Block> drillBlock;
    private @Nullable Supplier<? extends Block> ploughBlock;
    private @Nullable Supplier<? extends Block> rollerBlock;

    private final @Nullable Supplier<PartialModel> chainConveyorWheelModel;
    private final @Nullable Supplier<PartialModel> chainConveyorGuardModel;
    private final @Nullable Supplier<PartialModel> chainConveyorShaftModel;
    private final @Nullable Supplier<PartialModel> mixerHeadModel;
    private final @Nullable Supplier<PartialModel> drillHeadModel;


    private final boolean casing;
    private final boolean shaft;
    private final boolean cogwheel;
    private final boolean largeCogwheel;
    private final boolean fluidPipe;
    private final boolean belt;
    private final boolean gearbox;
    private final boolean press;
    private final boolean mixer;
    private final boolean depot;
    private final boolean chainDrive;
    private final boolean chainGearshift;
    private final boolean configurableGearbox;
    private final boolean chainConveyor;
    private final boolean gearshift;
    private final boolean clutch;
    private final boolean deployer;
    private final boolean storageInterface;
    private final boolean encasedFan;
    private final boolean harvester;
    private final boolean saw;
    private final boolean drill;
    private final boolean plough;
    private final boolean roller;
    private final boolean encasedWoodenShaft;
    private final boolean encasedWoodenCogwheel;
    private final boolean encasedWoodenLargeCogwheel;

    private final boolean isKJSGenerated;

    protected CasingSet(String name, Options options) {
        this.name = name;
        Preconditions.checkNotNull(options.ctSprite,"Connected Texture Sprite Supplier can't be null");
        ctSprite = options.ctSprite;
        cogSideSprite = options.cogSideSprite;
        cogOtherSideSprite = options.cogOtherSideSprite;
        beltSprite = options.beltSprite;
        casingBlock = options.existingCasing;
        casing = options.casing;
        shaft = options.shaft;
        cogwheel = options.cogwheel;
        largeCogwheel = options.largeCogwheel;
        fluidPipe = options.fluidPipe;
        belt = options.belt;
        gearbox = options.gearbox;
        press = options.press;
        mixer = options.mixer;
        depot = options.depot;
        chainDrive = options.chainDrive;
        chainGearshift = options.chainGearshift;
        configurableGearbox = options.configurableGearbox;
        chainConveyor = options.chainConveyor;
        gearshift = options.gearshift;
        clutch = options.clutch;
        deployer = options.deployer;
        storageInterface = options.storageInterface;
        encasedFan = options.encasedFan;
        harvester = options.harvester;
        saw = options.saw;
        drill = options.drill;
        plough = options.plough;
        roller = options.roller;
        encasedWoodenShaft = options.encasedWoodenShaft;
        encasedWoodenCogwheel = options.encasedWoodenCogwheel;
        encasedWoodenLargeCogwheel = options.encasedWoodenLargeCogwheel;

        chainConveyorGuardModel = options.chainConveyorGuardModel;
        chainConveyorWheelModel = options.chainConveyorWheelModel;
        chainConveyorShaftModel = options.chainConveyorShaftModel;
        mixerHeadModel = options.mixerHeadModel;
        drillHeadModel = options.drillHeadModel;
        isKJSGenerated = options.kjsGenerated;

        if (options.existingShaft != null) shaftBlock = options.existingShaft;
        if (options.existingCogwheel != null) cogwheelBlock = options.existingCogwheel;
        if (options.existingLargeCogwheel != null) largeCogwheelBlock = options.existingLargeCogwheel;
        if (options.existingFluidPipe != null) fluidPipeBlock = options.existingFluidPipe;
        if (options.existingGearbox != null) gearboxBlock = options.existingGearbox;
        if (options.existingVerticalGearboxItem != null) verticalGearboxBlockItem = options.existingVerticalGearboxItem;
        if (options.existingPress != null) pressBlock = options.existingPress;
        if (options.existingMixer != null) mixerBlock = options.existingMixer;
        if (options.existingDepot != null) depotBlock = options.existingDepot;
        if (options.existingChainDrive != null) chainDriveBlock = options.existingChainDrive;
        if (options.existingChainGearshift != null) chainGearshiftBlock = options.existingChainGearshift;
        if (options.existingChainConveyor != null) chainConveyorBlock = options.existingChainConveyor;
        if (options.existingGearshift != null) gearshiftBlock = options.existingGearshift;
        if (options.existingClutch != null) clutchBlock = options.existingClutch;
        if (options.existingDeployer != null) deployerBlock = options.existingDeployer;
        if (options.existingStorageInterface != null) storageInterfaceBlock = options.existingStorageInterface;
        if (options.existingEncasedFan != null) encasedFanBlock = options.existingEncasedFan;
        if (options.existingHarvester != null) harvesterBlock = options.existingHarvester;
        if (options.existingSaw != null) sawBlock = options.existingSaw;
        if (options.existingDrill != null) drillBlock = options.existingDrill;
        if (options.existingPlough != null) ploughBlock = options.existingPlough;
        if (options.existingRoller != null) rollerBlock = options.existingRoller;
    }

    public String getName() {
        return name;
    }

    public boolean doesGenerateCasing(){
        return casing;
    }
    public boolean doesGenerateCogwheel(){
        return cogwheel;
    }
    public boolean doesGenerateShaft(){
        return shaft;
    }
    public boolean doesGenerateLargeCogwheel(){
        return largeCogwheel;
    }
    public boolean doesGenerateFluidPipe(){
        return fluidPipe;
    }
    public boolean doesGenerateBelt(){
        return belt;
    }
    public boolean doesGenerateGearbox(){
        return gearbox;
    }
    public boolean doesGeneratePress(){
        return press;
    }
    public boolean doesGenerateMixer(){
        return mixer;
    }
    public boolean doesGenerateDepot(){
        return depot;
    }
    public boolean doesGenerateChainDrive(){
        return chainDrive;
    }
    public boolean doesGenerateChainGearshift(){
        return chainGearshift;
    }
    public boolean doesGenerateConfigurableGearbox(){
        return configurableGearbox;
    }
    public boolean doesGenerateChainConveyor(){
        return chainConveyor;
    }
    public boolean doesGenerateGearshift(){
        return gearshift;
    }
    public boolean doesGenerateClutch(){
        return clutch;
    }
    public boolean doesGenerateDeployer(){
        return deployer;
    }
    public boolean doesGenerateStorageInterface(){
        return storageInterface;
    }
    public boolean doesGenerateEncasedFan(){
        return encasedFan;
    }
    public boolean doesGenerateHarvester(){
        return harvester;
    }
    public boolean doesGenerateSaw(){
        return saw;
    }
    public boolean doesGenerateDrill(){
        return drill;
    }
    public boolean doesGeneratePlough(){
        return plough;
    }
    public boolean doesGenerateRoller(){
        return roller;
    }
    public boolean doesGenerateEncasedWoodenShaft(){
        return encasedWoodenShaft;
    }
    public boolean doesGenerateEncasedWoodenCogwheel(){
        return encasedWoodenCogwheel;
    }
    public boolean doesGenerateEncasedWoodenLargeCogwheel(){
        return encasedWoodenLargeCogwheel;
    }

    @Nullable
    public Supplier<? extends Block> getCasingSupplier() {
        return casingBlock;
    }

    @Nullable
    public Block getCasing() {
        return casingBlock == null ? null : casingBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getShaftSupplier() {
        return shaftBlock;
    }

    @Nullable
    public Block getShaft() {
        return shaftBlock == null ? null : shaftBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getCogwheelSupplier() {
        return cogwheelBlock;
    }

    @Nullable
    public Block getCogwheel() {
        return cogwheelBlock == null ? null : cogwheelBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getLargeCogwheelSupplier() {
        return largeCogwheelBlock;
    }

    @Nullable
    public Block getLargeCogwheel() {
        return largeCogwheelBlock == null ? null : largeCogwheelBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getFluidPipeSupplier() {
        return fluidPipeBlock;
    }

    @Nullable
    public Block getFluidPipe() {
        return fluidPipeBlock == null ? null : fluidPipeBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getGearboxSupplier() {
        return gearboxBlock;
    }

    @Nullable
    public Block getGearbox() {
        return gearboxBlock == null ? null : gearboxBlock.get();
    }

    @Nullable
    public Supplier<? extends BlockItem> getVerticalGearboxItemSupplier() {
        return verticalGearboxBlockItem;
    }

    @Nullable
    public BlockItem getVerticalGearboxItem() {
        return verticalGearboxBlockItem == null ? null : verticalGearboxBlockItem.get();
    }

    @Nullable
    public Supplier<? extends Block> getPressSupplier() {
        return pressBlock;
    }

    @Nullable
    public Block getPress() {
        return pressBlock == null ? null : pressBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getMixerSupplier() {
        return mixerBlock;
    }

    @Nullable
    public Block getMixer() {
        return mixerBlock == null ? null : mixerBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getDepotSupplier() {
        return depotBlock;
    }

    @Nullable
    public Block getDepot() {
        return depotBlock == null ? null : depotBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getChainDriveSupplier() {
        return chainDriveBlock;
    }

    @Nullable
    public Block getChainDrive() {
        return chainDriveBlock == null ? null : chainDriveBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getChainGearshiftSupplier() {
        return chainGearshiftBlock;
    }

    @Nullable
    public Block getChainGearshift() {
        return chainGearshiftBlock == null ? null : chainGearshiftBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getConfigurableGearboxSupplier() {
        return configurableGearboxBlock;
    }

    @Nullable
    public Block getConfigurableGearbox() {
        return configurableGearboxBlock == null ? null : configurableGearboxBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getChainConveyorSupplier() {
        return chainConveyorBlock;
    }

    @Nullable
    public Block getChainConveyor() {
        return chainConveyorBlock == null ? null : chainConveyorBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getGearshiftSupplier() {
        return gearshiftBlock;
    }

    @Nullable
    public Block getGearshift() {
        return gearshiftBlock == null ? null : gearshiftBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getClutchSupplier() {
        return clutchBlock;
    }

    @Nullable
    public Block getClutch() {
        return clutchBlock == null ? null : clutchBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getDeployerSupplier() {
        return deployerBlock;
    }

    @Nullable
    public Block getDeployer() {
        return deployerBlock == null ? null : deployerBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getStorageInterfaceSupplier() {
        return storageInterfaceBlock;
    }

    @Nullable
    public Block getStorageInterface() {
        return storageInterfaceBlock == null ? null : storageInterfaceBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getEncasedFanSupplier() {
        return encasedFanBlock;
    }

    @Nullable
    public Block getEncasedFan() {
        return encasedFanBlock == null ? null : encasedFanBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getHarvesterSupplier() {
        return harvesterBlock;
    }

    @Nullable
    public Block getHarvester() {
        return harvesterBlock == null ? null : harvesterBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getSawSupplier() {
        return sawBlock;
    }

    @Nullable
    public Block getSaw() {
        return sawBlock == null ? null : sawBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getDrillSupplier() {
        return drillBlock;
    }

    @Nullable
    public Block getDrill() {
        return drillBlock == null ? null : drillBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getPloughSupplier() {
        return ploughBlock;
    }

    @Nullable
    public Block getPlough() {
        return ploughBlock == null ? null : ploughBlock.get();
    }

    @Nullable
    public Supplier<? extends Block> getRollerSupplier() {
        return rollerBlock;
    }

    @Nullable
    public Block getRoller() {
        return rollerBlock == null ? null : rollerBlock.get();
    }

    @Nullable
    public BeltBlockEntity.CasingType getBeltCasingType() {
        return beltCasingType;
    }

    public void setCasing(@Nonnull Supplier<? extends Block> casing){
        if (getCasingSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a casing that has already been referenced");
        casingBlock = casing;
    }

    public void setBeltCasingType(BeltBlockEntity.CasingType type){
        if (getBeltCasingType() != null)
            throw new UnsupportedOperationException("You cannot modify a belt casing type that has already been referenced");
        beltCasingType = type;
    }

    public void setShaft(@Nonnull Supplier<? extends Block> shaft){
        if (getShaftSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a shaft that has already been referenced");
        shaftBlock = shaft;
    }

    public void setCogwheel(@Nonnull Supplier<? extends Block> cogwheel){
        if (getCogwheelSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a cogwheel that has already been referenced");
        cogwheelBlock = cogwheel;
    }

    public void setLargeCogwheel(@Nonnull Supplier<? extends Block> cogwheel){
        if (getLargeCogwheelSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a large cogwheel that has already been referenced");
        largeCogwheelBlock = cogwheel;
    }

    public void setFluidPipe(@Nonnull Supplier<? extends Block> pipe){
        if (getFluidPipeSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a fluid pipe that has already been referenced");
        fluidPipeBlock = pipe;
    }

    public void setGearbox(@Nonnull Supplier<? extends Block> gearbox){
        if (getGearboxSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a gearbox that has already been referenced");
        gearboxBlock = gearbox;
    }

    public void setVerticalGearboxItem(@Nonnull Supplier<? extends BlockItem> gearbox){
        if (getVerticalGearboxItemSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a vertical gearbox item that has already been referenced");
        verticalGearboxBlockItem = gearbox;
    }

    public void setPress(@Nonnull Supplier<? extends Block> press){
        if (getPressSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a press that has already been referenced");
        pressBlock = press;
    }

    public void setMixer(@Nonnull Supplier<? extends Block> mixer){
        if (getMixerSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a mixer that has already been referenced");
        mixerBlock = mixer;
    }

    public void setDepot(@Nonnull Supplier<? extends Block> depot){
        if (getDepotSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a depot that has already been referenced");
        depotBlock = depot;
    }

    public void setChainDrive(@Nonnull Supplier<? extends Block> chainDrive){
        if (getChainDriveSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a chain drive that has already been referenced");
        chainDriveBlock = chainDrive;
    }

    public void setChainGearshift(@Nonnull Supplier<? extends Block> chainGearshift){
        if (getChainGearshift() != null)
            throw new UnsupportedOperationException("You cannot modify a chain gearshift that has already been referenced");
        chainGearshiftBlock = chainGearshift;
    }

    public void setConfigurableGearbox(@Nonnull Supplier<? extends Block> configurableGearbox){
        if (getConfigurableGearboxSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a configurable gearbox that has already been referenced");
        configurableGearboxBlock = configurableGearbox;
    }

    public void setChainConveyor(@Nonnull Supplier<? extends Block> chainConveyor){
        if (getChainConveyorSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a chain conveyor that has already been referenced");
        chainConveyorBlock = chainConveyor;
    }

    public void setGearshift(@Nonnull Supplier<? extends Block> gearshift){
        if (getGearshiftSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a gearshift that has already been referenced");
        gearshiftBlock = gearshift;
    }

    public void setClutch(@Nonnull Supplier<? extends Block> clutch){
        if (getClutchSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a clutch that has already been referenced");
        clutchBlock = clutch;
    }

    public void setDeployer(@Nonnull Supplier<? extends Block> deployer){
        if (getDeployerSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a deployer that has already been referenced");
        deployerBlock = deployer;
    }

    public void setStorageInterface(@Nonnull Supplier<? extends Block> storageInterface){
        if (getStorageInterfaceSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a portable storage interface that has already been referenced");
        storageInterfaceBlock = storageInterface;
    }

    public void setEncasedFan(@Nonnull Supplier<? extends Block> fan){
        if (getEncasedFanSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify an encased fan that has already been referenced");
        encasedFanBlock = fan;
    }

    public void setHarvester(@Nonnull Supplier<? extends Block> harvester){
        if (getHarvesterSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a harvester that has already been referenced");
        harvesterBlock = harvester;
    }

    public void setSaw(@Nonnull Supplier<? extends Block> saw){
        if (getSawSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a saw that has already been referenced");
        sawBlock = saw;
    }

    public void setDrill(@Nonnull Supplier<? extends Block> drill){
        if (getDrillSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a drill that has already been referenced");
        drillBlock = drill;
    }

    public void setPlough(@Nonnull Supplier<? extends Block> plough){
        if (getPloughSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a plough that has already been referenced");
        ploughBlock = plough;
    }

    public void setRoller(@Nonnull Supplier<? extends Block> roller){
        if (getRollerSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a roller that has already been referenced");
        rollerBlock = roller;
    }


    @Nullable
    public CTSpriteShiftEntry getConnectedTextureSprite() {
        return ctSprite.get();
    }

    @Nullable
    public CTSpriteShiftEntry getCogSideSprite() {
        return cogSideSprite != null ? cogSideSprite.get() : null;
    }

    @Nullable
    public CTSpriteShiftEntry getCogOtherSideSprite() {
        return cogOtherSideSprite != null ? cogOtherSideSprite.get() : null;
    }

    @Nullable
    public SpriteShiftEntry getBeltSprite() {
        return beltSprite != null ? beltSprite.get() : null;
    }

    public PartialModel getBeltPartialModel(boolean alongX){
        return alongX ? alongXBeltModel.get() : alongZBeltModel.get();
    }

    @Nullable
    public PartialModel getChainConveyorGuardModel() {
        return chainConveyorGuardModel != null ? chainConveyorGuardModel.get() : null;
    }

    @Nullable
    public PartialModel getChainConveyorWheelModel() {
        return chainConveyorWheelModel != null ? chainConveyorWheelModel.get() : null;
    }

    @Nullable
    public PartialModel getChainConveyorShaftModel() {
        return chainConveyorShaftModel != null ? chainConveyorShaftModel.get() : null;
    }

    @Nullable
    public PartialModel getMixerHeadModel() {
        return mixerHeadModel != null ? mixerHeadModel.get() : null;
    }

    public PartialModel getDrillHeadModel() {
        return drillHeadModel != null ? drillHeadModel.get() : null;
    }

    public boolean isInSet(Block block){
        return block.equals(getCasing()) || block.equals(getShaft()) || block.equals(getCogwheel()) || block.equals(getLargeCogwheel()) || block.equals(getFluidPipe())
                || block.equals(getGearbox()) || block.equals(getPress()) || block.equals(getMixer()) || block.equals(getDepot())
                || block.equals(getChainDrive()) || block.equals(getChainGearshift()) || block.equals(getConfigurableGearbox()) || block.equals(getChainConveyor())
                || block.equals(getGearshift()) || block.equals(getClutch()) || block.equals(getDeployer()) || block.equals(getStorageInterface())
                || block.equals(getEncasedFan()) || block.equals(getHarvester()) || block.equals(getSaw()) || block.equals(getDrill())
                || block.equals(getPlough()) || block.equals(getRoller());
    }

    public boolean isKJSGenerated() {
        return isKJSGenerated;
    }

    public static class Options {
        private @Nullable Supplier<CTSpriteShiftEntry> ctSprite;
        private @Nullable Supplier<CTSpriteShiftEntry> cogSideSprite;
        private @Nullable Supplier<CTSpriteShiftEntry> cogOtherSideSprite;
        private @Nullable Supplier<SpriteShiftEntry> beltSprite;
        private @Nullable Supplier<? extends Block> existingCasing;
        private boolean casing;
        private boolean shaft;
        private boolean cogwheel;
        private boolean largeCogwheel;
        private boolean fluidPipe;
        private boolean belt;
        private boolean gearbox;
        private boolean press;
        private boolean mixer;
        private boolean depot;
        private boolean chainDrive;
        private boolean chainGearshift;
        private boolean configurableGearbox;
        private boolean chainConveyor;
        private boolean gearshift;
        private boolean clutch;
        private boolean deployer;
        private boolean storageInterface;
        private boolean encasedFan;
        private boolean harvester;
        private boolean saw;
        private boolean drill;
        private boolean plough;
        private boolean roller;
        private boolean kjsGenerated = false;
        private boolean encasedWoodenShaft;
        private boolean encasedWoodenCogwheel;
        private boolean encasedWoodenLargeCogwheel;
        private Supplier<PartialModel> alongXBeltModel;
        private Supplier<PartialModel> alongZBeltModel;

        private Supplier<PartialModel> chainConveyorWheelModel;
        private Supplier<PartialModel> chainConveyorGuardModel;
        private Supplier<PartialModel> chainConveyorShaftModel;
        private Supplier<PartialModel> mixerHeadModel;
        private Supplier<PartialModel> drillHeadModel;


        private @Nullable Supplier<? extends Block> existingShaft;
        private @Nullable Supplier<? extends Block> existingCogwheel;
        private @Nullable Supplier<? extends Block> existingLargeCogwheel;
        private @Nullable Supplier<? extends Block> existingFluidPipe;
        private @Nullable Supplier<? extends Block> existingGearbox;
        private @Nullable Supplier<? extends BlockItem> existingVerticalGearboxItem;
        private @Nullable Supplier<? extends Block> existingPress;
        private @Nullable Supplier<? extends Block> existingMixer;
        private @Nullable Supplier<? extends Block> existingDepot;
        private @Nullable Supplier<? extends Block> existingChainDrive;
        private @Nullable Supplier<? extends Block> existingChainGearshift;
        private @Nullable Supplier<? extends Block> existingChainConveyor;
        private @Nullable Supplier<? extends Block> existingGearshift;
        private @Nullable Supplier<? extends Block> existingClutch;
        private @Nullable Supplier<? extends Block> existingDeployer;
        private @Nullable Supplier<? extends Block> existingStorageInterface;
        private @Nullable Supplier<? extends Block> existingEncasedFan;
        private @Nullable Supplier<? extends Block> existingHarvester;
        private @Nullable Supplier<? extends Block> existingSaw;
        private @Nullable Supplier<? extends Block> existingDrill;
        private @Nullable Supplier<? extends Block> existingPlough;
        private @Nullable Supplier<? extends Block> existingRoller;

        public Options() {
            ctSprite = null;
            cogSideSprite = null;
            cogOtherSideSprite = null;
            beltSprite = null;
            existingCasing = null;
            casing = false;
            shaft = false;
            cogwheel = false;
            largeCogwheel = false;
            fluidPipe = false;
            belt = false;
            gearbox = false;
            alongXBeltModel = ()->null;
            alongZBeltModel = ()->null;
        }

        public Options ctSprite(Supplier<CTSpriteShiftEntry> ctSprite) {
            Preconditions.checkNotNull(ctSprite,"Connected Texture Sprite Supplier can't be null");
            this.ctSprite = ctSprite;
            return this;
        }

        public Options existingCasing(Supplier<? extends Block> casing) {
            this.existingCasing = casing;
            this.casing = false;
            return this;
        }

        public Options casing() {
            Preconditions.checkState(existingCasing == null,"Cannot create a casing if an existing casing was already set");
            this.casing = true;
            return this;
        }

        public Options shaft() {
            this.shaft = true;
            return this;
        }


        public Options cogwheel(@Nullable Supplier<CTSpriteShiftEntry> sideSprite,@Nullable Supplier<CTSpriteShiftEntry> otherSideSprite) {
            this.cogwheel = true;
            if (sideSprite != null && cogSideSprite == null) this.cogSideSprite = sideSprite;
            if (otherSideSprite != null && cogOtherSideSprite == null) this.cogOtherSideSprite = otherSideSprite;
            return this;
        }

        public Options largeCogwheel() {
            this.largeCogwheel = true;
            return this;
        }

        public Options fluidPipe() {
            this.fluidPipe = true;
            return this;
        }

        public Options belt(@Nonnull Supplier<SpriteShiftEntry> sprite,Supplier<PartialModel> alongXBeltModel,Supplier<PartialModel> alongZBeltModel){
            this.belt = true;
            this.beltSprite = sprite;
            this.alongXBeltModel = alongXBeltModel;
            this.alongZBeltModel = alongZBeltModel;
            return this;
        }

        public Options gearbox(){
            this.gearbox = true;
            return this;
        }

        public Options press(){
            this.press = true;
            return this;
        }

        public Options mixer(Supplier<PartialModel> headModel){
            this.mixer = true;
            this.mixerHeadModel = headModel;
            return this;
        }

        public Options depot(){
            this.depot = true;
            return this;
        }

        public Options deployer(){
            this.deployer = true;
            return this;
        }

        public Options portableStorageInterface(){
            this.storageInterface = true;
            return this;
        }

        public Options chainDrive(){
            this.chainDrive = true;
            return this;
        }

        public Options chainGearshift(){
            this.chainGearshift = true;
            return this;
        }

        public Options configurableGearbox(){
            this.configurableGearbox = true;
            return this;
        }

        public Options gearshift(){
            this.gearshift = true;
            return this;
        }

        public Options clutch(){
            this.clutch = true;
            return this;
        }

        public Options encasedFan(){
            this.encasedFan = true;
            return this;
        }

        public Options harvester(){
            this.harvester = true;
            return this;
        }

        public Options saw(){
            this.saw = true;
            return this;
        }

        public Options drill(Supplier<PartialModel> headModel){
            this.drill = true;
            this.drillHeadModel = headModel;
            return this;
        }

        public Options plough(){
            this.plough = true;
            return this;
        }

        public Options roller(){
            this.roller = true;
            return this;
        }

        public Options encasedWoodenShaft(){
            this.encasedWoodenShaft = true;
            return this;
        }

        public Options encasedWoodenCogwheel(){
            this.encasedWoodenCogwheel = true;
            return this;
        }
        public Options encasedWoodenLargeCogwheel(){
            this.encasedWoodenLargeCogwheel = true;
            return this;
        }

        public Options chainConveyor(Supplier<PartialModel> guard,Supplier<PartialModel> wheel,Supplier<PartialModel> shaft){
            this.chainConveyor = true;
            this.chainConveyorGuardModel = guard;
            this.chainConveyorWheelModel = wheel;
            this.chainConveyorShaftModel = shaft;
            return this;
        }

        public Options processingBlocks(Supplier<PartialModel> mixerHeadModel){
            return press().mixer(mixerHeadModel).depot().deployer().encasedFan();
        }

        public Options complexTransmissionBlocks(Supplier<PartialModel> conveyorGuard,Supplier<PartialModel> conveyorWheel,Supplier<PartialModel> conveyorShaft){
            return gearbox().chainDrive().chainGearshift().configurableGearbox().chainConveyor(conveyorGuard,conveyorWheel,conveyorShaft).gearshift().clutch();
        }

        public Options simpleTransmissions(@Nullable Supplier<CTSpriteShiftEntry> cogwheelSideSprite,@Nullable Supplier<CTSpriteShiftEntry> cogwheelOtherSideSprite){
            return shaft().cogwheel(cogwheelSideSprite,cogwheelOtherSideSprite).largeCogwheel();
        }

        public Options encasedCustomTransmissionBlocks(){
            return encasedWoodenShaft().encasedWoodenCogwheel().encasedWoodenLargeCogwheel();
        }

        public Options contraptionBlocks(Supplier<PartialModel> headModel){
            return portableStorageInterface().harvester().saw().drill(headModel).plough().roller();
        }

        public Options fluids(){
            return fluidPipe();
        }

        public Options everythingExceptCasing(Supplier<CTSpriteShiftEntry> ctSprite,@Nonnull Supplier<SpriteShiftEntry> beltSprite,Supplier<PartialModel> alongXBeltModel,Supplier<PartialModel> alongZBeltModel,@Nullable Supplier<CTSpriteShiftEntry> cogwheelSideSprite,@Nullable Supplier<CTSpriteShiftEntry> cogwheelOtherSideSprite,Supplier<PartialModel> conveyorGuard,Supplier<PartialModel> conveyorWheel,Supplier<PartialModel> conveyorShaft,Supplier<PartialModel> mixerHeadModel,Supplier<PartialModel> drillHeadModel){
            return ctSprite(ctSprite).contraptionBlocks(drillHeadModel).encasedCustomTransmissionBlocks().simpleTransmissions(cogwheelSideSprite,cogwheelOtherSideSprite).belt(beltSprite,alongXBeltModel,alongZBeltModel).processingBlocks(mixerHeadModel).complexTransmissionBlocks(conveyorGuard,conveyorWheel,conveyorShaft).fluids();
        }

        public Options everything(Supplier<CTSpriteShiftEntry> ctSprite,@Nonnull Supplier<SpriteShiftEntry> beltSprite,Supplier<PartialModel> alongXBeltModel,Supplier<PartialModel> alongZBeltModel,@Nullable Supplier<CTSpriteShiftEntry> cogwheelSideSprite,@Nullable Supplier<CTSpriteShiftEntry> cogwheelOtherSideSprite,Supplier<PartialModel> conveyorGuard,Supplier<PartialModel> conveyorWheel,Supplier<PartialModel> conveyorShaft,Supplier<PartialModel> mixerHeadModel,Supplier<PartialModel> drillHeadModel){
            return casing().everythingExceptCasing(ctSprite, beltSprite, alongXBeltModel, alongZBeltModel, cogwheelSideSprite, cogwheelOtherSideSprite,conveyorGuard,conveyorWheel,conveyorShaft,mixerHeadModel,drillHeadModel);
        }

        Options existingShaft(Supplier<? extends Block> shaft) {
            this.existingShaft = shaft;
            this.shaft = false;
            return this;
        }

        Options existingCogwheel(Supplier<? extends Block> cogwheel) {
            this.existingCogwheel = cogwheel;
            this.cogwheel = false;
            return this;
        }

        Options existingLargeCogwheel(Supplier<? extends Block> largeCogwheel) {
            this.existingLargeCogwheel = largeCogwheel;
            this.largeCogwheel = false;
            return this;
        }

        Options existingFluidPipe(Supplier<? extends Block> fluidPipe) {
            this.existingFluidPipe = fluidPipe;
            this.fluidPipe = false;
            return this;
        }

        Options existingGearbox(Supplier<? extends Block> gearbox,Supplier<? extends BlockItem> existingVerticalGearboxItem) {
            this.existingGearbox = gearbox;
            this.existingVerticalGearboxItem = existingVerticalGearboxItem;
            this.gearbox = false;
            return this;
        }

        Options existingPress(Supplier<? extends Block> press) {
            this.existingPress = press;
            this.press = false;
            return this;
        }

        Options existingMixer(Supplier<? extends Block> mixer) {
            this.existingMixer = mixer;
            this.mixer = false;
            return this;
        }

        Options existingDepot(Supplier<? extends Block> depot) {
            this.existingDepot = depot;
            this.depot = false;
            return this;
        }

        Options existingChainDrive(Supplier<? extends Block> chainDrive) {
            this.existingChainDrive = chainDrive;
            this.chainDrive = false;
            return this;
        }

        Options existingChainGearshift(Supplier<? extends Block> chainGearshift) {
            this.existingChainGearshift = chainGearshift;
            this.chainGearshift = false;
            return this;
        }

        Options existingChainConveyor(Supplier<? extends Block> chainConveyor) {
            this.existingChainConveyor = chainConveyor;
            this.chainConveyor = false;
            return this;
        }

        Options existingGearshift(Supplier<? extends Block> gearshift) {
            this.existingGearshift = gearshift;
            this.gearshift = false;
            return this;
        }

        Options existingClutch(Supplier<? extends Block> clutch) {
            this.existingClutch = clutch;
            this.clutch = false;
            return this;
        }

        Options existingDeployer(Supplier<? extends Block> deployer) {
            this.existingDeployer = deployer;
            this.deployer = false;
            return this;
        }

        Options existingPortableStorageInterface(Supplier<? extends Block> storageInterface) {
            this.existingStorageInterface = storageInterface;
            this.storageInterface = false;
            return this;
        }

        Options existingEncasedFan(Supplier<? extends Block> fan) {
            this.existingEncasedFan = fan;
            this.encasedFan = false;
            return this;
        }

        Options existingHarvester(Supplier<? extends Block> harvester) {
            this.existingHarvester = harvester;
            this.harvester = false;
            return this;
        }

        Options existingSaw(Supplier<? extends Block> saw) {
            this.existingSaw = saw;
            this.saw = false;
            return this;
        }

        Options existingDrill(Supplier<? extends Block> drill) {
            this.existingDrill = drill;
            this.drill = false;
            return this;
        }

        Options existingPlough(Supplier<? extends Block> plough) {
            this.existingPlough = plough;
            this.plough = false;
            return this;
        }

        Options existingRoller(Supplier<? extends Block> roller) {
            this.existingRoller = roller;
            this.roller = false;
            return this;
        }

        public Options kjsGenerated(){
            this.kjsGenerated = true;
            return this;
        }
    }
}
