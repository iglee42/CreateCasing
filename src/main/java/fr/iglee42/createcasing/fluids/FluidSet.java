package fr.iglee42.createcasing.fluids;

import com.google.common.base.Preconditions;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceBlock;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import com.simibubi.create.content.decoration.steamWhistle.WhistleExtenderBlock;
import com.simibubi.create.content.fluids.drain.ItemDrainBlock;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.GlassFluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlock;
import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.spout.SpoutBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.kinetics.crank.ValveHandleBlock;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FluidSet {

    private final String name;
    private final TagKey<Item> item;
    private final TagKey<Item> sheet;
    private TagKey<Item> block;
    private Supplier<? extends Block> casing;
    private final Supplier<CTSpriteShiftEntry> tankSideCtSprite;
    private final Supplier<CTSpriteShiftEntry> tankTopCtSprite;
    private final Supplier<CTSpriteShiftEntry> tankInnerCtSprite;
    private final Supplier<PartialModel> engineGaugeModel;
    private final Supplier<PartialModel> engineGaugeDialModel;
    private final Supplier<PartialModel> valveHandleModel;
    private final Supplier<PartialModel> hosePulleyHalfMagnetModel;
    private final Supplier<PartialModel> hosePulleyMagnetModel;
    private final Supplier<PartialModel> portableFluidInterfaceTopModel;
    private final Supplier<PartialModel> spoutBottomModel;
    private @Nullable BlockEntry<? extends FluidPipeBlock> fluidPipeBlock;
    private @Nullable BlockEntry<? extends GlassFluidPipeBlock> glassFluidPipeBlock;
    private @Nullable BlockEntry<? extends PumpBlock> pumpBlock;
    private @Nullable BlockEntry<? extends SmartFluidPipeBlock> smartFluidPipeBlock;
    private @Nullable BlockEntry<? extends FluidValveBlock> fluidValveBlock;
    private @Nullable BlockEntry<? extends ValveHandleBlock> valveHandleBlock;
    private @Nullable BlockEntry<? extends FluidTankBlock> fluidTankBlock;
    private @Nullable BlockEntry<? extends HosePulleyBlock> hosePulleyBlock;
    private @Nullable BlockEntry<? extends ItemDrainBlock> itemDrainBlock;
    private @Nullable BlockEntry<? extends PortableStorageInterfaceBlock> portableFluidInterfaceBlock;
    private @Nullable BlockEntry<? extends SteamEngineBlock> steamEngineBlock;
    private @Nullable BlockEntry<? extends WhistleBlock> whistleBlock;
    private @Nullable BlockEntry<? extends SpoutBlock> spoutBlock;

    private final boolean fluidPipe;
    private final boolean pump;
    private final boolean smartFluidPipe;
    private final boolean fluidValve;
    private final boolean valveHandle;
    private final boolean fluidTank;
    private final boolean hosePulley;
    private final boolean itemDrain;
    private final boolean portableFluidInterface;
    private final boolean steamEngine;
    private final boolean whistle;
    private final boolean spout;

    private final boolean notEncasable;
    private final boolean isKJSGenerated;


    protected FluidSet(String name, Options options) {
        this.name = name;
        Preconditions.checkNotNull(options.item,"Item Supplier for fluid set "+name+" can't be null");
        Preconditions.checkNotNull(options.sheet,"Sheet Supplier for fluid set "+name+" can't be null");
        Preconditions.checkNotNull(options.casing,"Casing Supplier for fluid set "+name+" can't be null");
        Preconditions.checkNotNull(options.block,"Block Supplier for fluid set "+name+" can't be null");
        this.item = options.item;
        this.sheet = options.sheet;
        this.block = options.block;
        this.casing = options.casing;

        this.tankSideCtSprite = options.tankSideCtSprite;
        this.tankTopCtSprite = options.tankTopCtSprite;
        this.tankInnerCtSprite = options.tankInnerCtSprite;

        this.engineGaugeModel = options.engineGaugeModel;
        this.engineGaugeDialModel = options.engineDialGaugeModel;
        this.valveHandleModel = options.valveHandleModel;
        this.hosePulleyHalfMagnetModel = options.hosePulleyHalfMagnetModel;
        this.hosePulleyMagnetModel = options.hosePulleyMagnetModel;
        this.portableFluidInterfaceTopModel = options.portableFluidInterfaceTopModel;
        this.spoutBottomModel = options.spoutBottomModel;

        fluidPipe = options.fluidPipe;
        pump = options.pump;
        smartFluidPipe = options.smartFluidPipe;
        fluidValve = options.fluidValve;
        valveHandle = options.valveHandle;
        fluidTank = options.fluidTank;
        hosePulley = options.hosePulley;
        itemDrain = options.itemDrain;
        portableFluidInterface = options.portableFluidInterface;
        steamEngine = options.steamEngine;
        whistle = options.whistle;
        spout = options.spout;

        this.notEncasable = options.notEncasable;
        isKJSGenerated = options.isKJSGenerated;


        if (options.existingFluidPipe != null) fluidPipeBlock = options.existingFluidPipe;
        if (options.existingGlassFluidPipe != null) glassFluidPipeBlock = options.existingGlassFluidPipe;
        if (options.existingPump != null) pumpBlock = options.existingPump;
        if (options.existingSmartFluidPipe != null) smartFluidPipeBlock = options.existingSmartFluidPipe;
        if (options.existingFluidValve != null) fluidValveBlock = options.existingFluidValve;
        if (options.existingValveHandle != null) valveHandleBlock = options.existingValveHandle;
        if (options.existingFluidTank != null) fluidTankBlock = options.existingFluidTank;
        if (options.existingHosePulley != null) hosePulleyBlock = options.existingHosePulley;
        if (options.existingItemDrain != null) itemDrainBlock = options.existingItemDrain;
        if (options.existingPortableFluidInterface != null) portableFluidInterfaceBlock = options.existingPortableFluidInterface;
        if (options.existingSteamEngine != null) steamEngineBlock = options.existingSteamEngine;
        if (options.existingWhistle != null) whistleBlock = options.existingWhistle;
    }

    public String getName() {
        return name;
    }

    public boolean doesGenerateFluidPipe(){
        return fluidPipe;
    }
    public boolean doesGeneratePump(){
        return pump;
    }
    public boolean doesGenerateSmartFluidPipe(){
        return smartFluidPipe;
    }
    public boolean doesGenerateFluidValve() {
        return fluidValve;
    }
    public boolean doesGenerateValveHandle() {
        return valveHandle;
    }
    public boolean doesGenerateFluidTank() {
        return fluidTank;
    }
    public boolean doesGenerateHosePulley() {
        return hosePulley;
    }
    public boolean doesGenerateItemDrain() {
        return itemDrain;
    }
    public boolean doesGeneratePortableFluidInterface() {
        return portableFluidInterface;
    }
    public boolean doesGenerateSteamEngine() {
        return steamEngine;
    }
    public boolean doesGenerateWhistle() {
        return whistle;
    }
    public boolean doesGenerateSpout() {
        return spout;
    }


    public TagKey<Item> getItem() {
        return item;
    }

    public TagKey<Item> getSheet() {
        return sheet;
    }

    public Block getCasing() {
        return casing.get();
    }

    public TagKey<Item> getBlock() {
        return block;
    }

    @Nullable
    public BlockEntry<? extends FluidPipeBlock> getFluidPipeSupplier() {
        return fluidPipeBlock;
    }

    @Nullable
    public FluidPipeBlock getFluidPipe() {
        return fluidPipeBlock == null ? null : fluidPipeBlock.get();
    }

    @Nullable
    public BlockEntry<? extends GlassFluidPipeBlock> getGlassFluidPipeSupplier() {
        return glassFluidPipeBlock;
    }

    @Nullable
    public GlassFluidPipeBlock getGlassFluidPipe() {
        return glassFluidPipeBlock == null ? null : glassFluidPipeBlock.get();
    }

    public void setFluidPipe(@Nonnull BlockEntry<? extends FluidPipeBlock> pipe,@Nonnull BlockEntry<? extends GlassFluidPipeBlock> glassFluidPipe){
        if (getFluidPipeSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a fluid pipe that has already been referenced");
        fluidPipeBlock = pipe;
        glassFluidPipeBlock = glassFluidPipe;
    }
    @Nullable
    public BlockEntry<? extends PumpBlock> getPumpSupplier() {
        return pumpBlock;
    }

    @Nullable
    public PumpBlock getPump() {
        return pumpBlock == null ? null : pumpBlock.get();
    }

    public void setPump(@Nonnull BlockEntry<? extends PumpBlock> pump){
        if (getPumpSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a pump that has already been referenced");
        pumpBlock = pump;
    }

    @Nullable
    public BlockEntry<? extends SmartFluidPipeBlock> getSmartFluidPipeSupplier() {
        return smartFluidPipeBlock;
    }

    @Nullable
    public SmartFluidPipeBlock getSmartFluidPipe() {
        return smartFluidPipeBlock == null ? null : smartFluidPipeBlock.get();
    }

    public void setSmartFluidPipe(@Nonnull BlockEntry<? extends SmartFluidPipeBlock> pipe){
        if (getSmartFluidPipeSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a smart fluid pipe that has already been referenced");
        smartFluidPipeBlock = pipe;
    }

    @Nullable
    public BlockEntry<? extends FluidValveBlock> getFluidValveSupplier() {
        return fluidValveBlock;
    }

    @Nullable
    public FluidValveBlock getFluidValve() {
        return fluidValveBlock == null ? null : fluidValveBlock.get();
    }

    public void setFluidValve(@Nonnull BlockEntry<? extends FluidValveBlock> valve) {
        if (getFluidValveSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a fluid valve that has already been referenced");
        fluidValveBlock = valve;
    }

    @Nullable
    public BlockEntry<? extends ValveHandleBlock> getValveHandleSupplier() {
        return valveHandleBlock;
    }

    @Nullable
    public ValveHandleBlock getValveHandle() {
        return valveHandleBlock == null ? null : valveHandleBlock.get();
    }

    public void setValveHandle(@Nonnull BlockEntry<? extends ValveHandleBlock> handle) {
        if (getValveHandleSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a valve handle that has already been referenced");
        valveHandleBlock = handle;
    }

    @Nullable
    public BlockEntry<? extends FluidTankBlock> getFluidTankSupplier() {
        return fluidTankBlock;
    }

    @Nullable
    public FluidTankBlock getFluidTank() {
        return fluidTankBlock == null ? null : fluidTankBlock.get();
    }

    public void setFluidTank(@Nonnull BlockEntry<? extends FluidTankBlock> tank) {
        if (getFluidTankSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a fluid tank that has already been referenced");
        fluidTankBlock = tank;
    }

    @Nullable
    public BlockEntry<? extends HosePulleyBlock> getHosePulleySupplier() {
        return hosePulleyBlock;
    }

    @Nullable
    public HosePulleyBlock getHosePulley() {
        return hosePulleyBlock == null ? null : hosePulleyBlock.get();
    }

    public void setHosePulley(@Nonnull BlockEntry<? extends HosePulleyBlock> hosePulley) {
        if (getHosePulleySupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a hose pulley that has already been referenced");
        hosePulleyBlock = hosePulley;
    }

    @Nullable
    public BlockEntry<? extends ItemDrainBlock> getItemDrainSupplier() {
        return itemDrainBlock;
    }

    @Nullable
    public ItemDrainBlock getItemDrain() {
        return itemDrainBlock == null ? null : itemDrainBlock.get();
    }

    public void setItemDrain(@Nonnull BlockEntry<? extends ItemDrainBlock> itemDrain) {
        if (getItemDrainSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify an item drain that has already been referenced");
        itemDrainBlock = itemDrain;
    }

    @Nullable
    public BlockEntry<? extends PortableStorageInterfaceBlock> getPortableFluidInterfaceSupplier() {
        return portableFluidInterfaceBlock;
    }

    @Nullable
    public PortableStorageInterfaceBlock getPortableFluidInterface() {
        return portableFluidInterfaceBlock == null ? null : portableFluidInterfaceBlock.get();
    }

    public void setPortableFluidInterface(@Nonnull BlockEntry<? extends PortableStorageInterfaceBlock> portableFluidInterface) {
        if (getPortableFluidInterfaceSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a portable fluid interface that has already been referenced");
        portableFluidInterfaceBlock = portableFluidInterface;
    }

    @Nullable
    public BlockEntry<? extends SteamEngineBlock> getSteamEngineSupplier() {
        return steamEngineBlock;
    }

    @Nullable
    public SteamEngineBlock getSteamEngine() {
        return steamEngineBlock == null ? null : steamEngineBlock.get();
    }

    public void setSteamEngine(@Nonnull BlockEntry<? extends SteamEngineBlock> steamEngine) {
        if (getSteamEngineSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a steam engine that has already been referenced");
        steamEngineBlock = steamEngine;
    }

    @Nullable
    public BlockEntry<? extends WhistleBlock> getWhistleSupplier() {
        return whistleBlock;
    }

    @Nullable
    public WhistleBlock getWhistle() {
        return whistleBlock == null ? null : whistleBlock.get();
    }

    public void setWhistle(@Nonnull BlockEntry<? extends WhistleBlock> whistle) {
        if (getWhistleSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a whistle that has already been referenced");
        whistleBlock = whistle;
    }

    @Nullable
    public BlockEntry<? extends SpoutBlock> getSpoutSupplier() {
        return spoutBlock;
    }

    @Nullable
    public SpoutBlock getSpout() {
        return spoutBlock == null ? null : spoutBlock.get();
    }

    public void setSpout(@Nonnull BlockEntry<? extends SpoutBlock> spout) {
        if (getSpoutSupplier() != null)
            throw new UnsupportedOperationException("You cannot modify a spout that has already been referenced");
        spoutBlock = spout;
    }


    public CTSpriteShiftEntry getTankSideSprite() {
        return tankSideCtSprite.get();
    }

    public CTSpriteShiftEntry getTankTopSprite() {
        return tankTopCtSprite.get();
    }

    public CTSpriteShiftEntry getTankInnerSprite() {
        return tankInnerCtSprite.get();
    }

    public PartialModel getEngineGaugeModel() {
        return engineGaugeModel.get();
    }

    public PartialModel getEngineGaugeDialModel() {
        return engineGaugeDialModel.get();
    }

    public PartialModel getValveHandleModel() {
        return valveHandleModel.get();
    }

    public PartialModel getHosePulleyHalfMagnetModel() {
        return hosePulleyHalfMagnetModel.get();
    }

    public PartialModel getHosePulleyMagnetModel() {
        return hosePulleyMagnetModel.get();
    }

    public PartialModel getPortableFluidInterfaceTopModel() {
        return portableFluidInterfaceTopModel.get();
    }

    public PartialModel getSpoutBottomModel() {
        return spoutBottomModel.get();
    }

    public boolean isInSet(Block block){
        return block.equals(getFluidPipe()) || block.equals(getGlassFluidPipe()) || block.equals(getPump()) || block.equals(getSmartFluidPipe())
                || block.equals(getFluidValve()) || block.equals(getValveHandle()) || block.equals(getFluidTank())
                || block.equals(getHosePulley()) || block.equals(getItemDrain()) || block.equals(getPortableFluidInterface())
                || block.equals(getSteamEngine()) || block.equals(getWhistle()) || block.equals(getSpout());
    }

    public List<? extends Block> getAllBlocks(){
        return Stream.of(
                getFluidPipe(),
                getGlassFluidPipe(),
                getPump(),
                getSmartFluidPipe(),
                getFluidValve(),
                getValveHandle(),
                getFluidTank(),
                getHosePulley(),
                getItemDrain(),
                getPortableFluidInterface(),
                getSteamEngine(),
                getWhistle(),
                getSpout()
        ).filter(Objects::nonNull).toList();
    }

    public boolean isNotEncasable() {
        return notEncasable;
    }

    public boolean isKJSGenerated() {
        return isKJSGenerated;
    }

    public static class Options {
        private TagKey<Item> item;
        private TagKey<Item> sheet;
        private TagKey<Item> block;
        private Supplier<? extends Block> casing;

        private Supplier<CTSpriteShiftEntry> tankSideCtSprite = ()->null;
        private Supplier<CTSpriteShiftEntry> tankTopCtSprite = ()->null;
        private Supplier<CTSpriteShiftEntry> tankInnerCtSprite = ()->null;
        private Supplier<PartialModel> engineGaugeModel;
        private Supplier<PartialModel> engineDialGaugeModel;
        private Supplier<PartialModel> valveHandleModel;
        private Supplier<PartialModel> hosePulleyHalfMagnetModel;
        private Supplier<PartialModel> hosePulleyMagnetModel;
        private Supplier<PartialModel> portableFluidInterfaceTopModel;
        private Supplier<PartialModel> spoutBottomModel;

        private boolean notEncasable;
        private boolean isKJSGenerated;

        private boolean fluidPipe;
        private boolean pump;
        private boolean smartFluidPipe;
        private boolean fluidValve;
        private boolean valveHandle;
        private boolean fluidTank;
        private boolean hosePulley;
        private boolean itemDrain;
        private boolean portableFluidInterface;
        private boolean steamEngine;
        private boolean whistle;
        private boolean spout;


        private @Nullable BlockEntry<? extends FluidPipeBlock> existingFluidPipe;
        private @Nullable BlockEntry<? extends GlassFluidPipeBlock> existingGlassFluidPipe;
        private @Nullable BlockEntry<? extends PumpBlock> existingPump;
        private @Nullable BlockEntry<? extends SmartFluidPipeBlock> existingSmartFluidPipe;
        private @Nullable BlockEntry<? extends FluidValveBlock> existingFluidValve;
        private @Nullable BlockEntry<? extends ValveHandleBlock> existingValveHandle;
        private @Nullable BlockEntry<? extends FluidTankBlock> existingFluidTank;
        private @Nullable BlockEntry<? extends HosePulleyBlock> existingHosePulley;
        private @Nullable BlockEntry<? extends ItemDrainBlock> existingItemDrain;
        private @Nullable BlockEntry<? extends PortableStorageInterfaceBlock> existingPortableFluidInterface;
        private @Nullable BlockEntry<? extends SteamEngineBlock> existingSteamEngine;
        private @Nullable BlockEntry<? extends WhistleBlock> existingWhistle;
        private @Nullable BlockEntry<? extends SpoutBlock> existingSpout;


        public Options item(TagKey<Item> item) {
            Preconditions.checkNotNull(item,"Item Supplier can't be null");
            this.item = item;
            return this;
        }

        public Options sheet(TagKey<Item> sheetItem) {
            Preconditions.checkNotNull(sheetItem,"Sheet Item Supplier can't be null");
            this.sheet = sheetItem;
            return this;
        }

        public Options casing(Supplier<? extends Block> casing) {
            Preconditions.checkNotNull(casing,"Casing Supplier can't be null");
            this.casing = casing;
            return this;
        }

        public Options block(TagKey<Item> block) {
            Preconditions.checkNotNull(block,"Block Supplier can't be null");
            this.block = block;
            return this;
        }

        public Options base(TagKey<Item> item, TagKey<Item> sheetItem, Supplier<? extends Block> casing, TagKey<Item> block) {
            return this.item(item).sheet(sheetItem).casing(casing).block(block);
        }


        public Options fluidPipe() {
            this.fluidPipe = true;
            return this;
        }

        public Options pump() {
            this.pump = true;
            return this;
        }

        public Options smartFluidPipe() {
            this.smartFluidPipe = true;
            return this;
        }

        public Options fluidValve() {
            this.fluidValve = true;
            return this;
        }

        public Options valveHandle(Supplier<PartialModel> valveHandle) {
            this.valveHandle = true;
            this.valveHandleModel = valveHandle;
            return this;
        }

        public Options fluidTank(Supplier<CTSpriteShiftEntry> tankSideCtSprite, Supplier<CTSpriteShiftEntry> tankTopCtSprite, Supplier<CTSpriteShiftEntry> tankInnerCtSprite) {
            this.tankSideCtSprite = tankSideCtSprite;
            this.tankTopCtSprite = tankTopCtSprite;
            this.tankInnerCtSprite = tankInnerCtSprite;
            this.fluidTank = true;
            return this;
        }

        public Options hosePulley(Supplier<PartialModel> hosePulleyHalfMagnetModel, Supplier<PartialModel> hosePulleyMagnetModel) {
            this.hosePulley = true;
            this.hosePulleyHalfMagnetModel = hosePulleyHalfMagnetModel;
            this.hosePulleyMagnetModel = hosePulleyMagnetModel;
            return this;
        }

        public Options itemDrain() {
            this.itemDrain = true;
            return this;
        }

        public Options portableFluidInterface(Supplier<PartialModel> portableFluidInterfaceTopModel) {
            this.portableFluidInterface = true;
            this.portableFluidInterfaceTopModel = portableFluidInterfaceTopModel;
            return this;
        }

        public Options steamEngine(Supplier<PartialModel> gauge,Supplier<PartialModel> gaugeDial) {
            this.steamEngine = true;
            this.engineGaugeModel = gauge;
            this.engineDialGaugeModel = gaugeDial;
            return this;
        }

        public Options whistle() {
            this.whistle = true;
            return this;
        }

        public Options spout(Supplier<PartialModel> spoutBottomModel) {
            this.spout = true;
            this.spoutBottomModel = spoutBottomModel;
            return this;
        }


        public Options notEncasable() {
            this.notEncasable = true;
            return this;
        }

        public Options everything(TagKey<Item> item, TagKey<Item> sheetItem, Supplier<? extends Block> casing, TagKey<Item> block,
                                  Supplier<CTSpriteShiftEntry> tankSideCtSprite, Supplier<CTSpriteShiftEntry> tankTopCtSprite, Supplier<CTSpriteShiftEntry> tankInnerCtSprite,
                                  Supplier<PartialModel> gauge,Supplier<PartialModel> gaugeDial,
                                  Supplier<PartialModel> valveHandle,
                                  Supplier<PartialModel> hosePulleyHalfMagnetModel, Supplier<PartialModel> hosePulleyMagnetModel,
                                  Supplier<PartialModel> portableFluidInterfaceTopModel,
                                  Supplier<PartialModel> spoutBottomModel) {
            return base(item, sheetItem, casing, block).fluidPipe().pump().smartFluidPipe().fluidValve().valveHandle(valveHandle).fluidTank(tankSideCtSprite, tankTopCtSprite, tankInnerCtSprite).hosePulley(hosePulleyHalfMagnetModel, hosePulleyMagnetModel).itemDrain().portableFluidInterface(portableFluidInterfaceTopModel).steamEngine(gauge,gaugeDial).whistle().spout(spoutBottomModel);
        }

        Options existingPipe(BlockEntry<? extends FluidPipeBlock> pipe, BlockEntry<? extends GlassFluidPipeBlock> glassPipe) {
            this.existingFluidPipe = pipe;
            this.existingGlassFluidPipe = glassPipe;
            this.fluidPipe = false;
            return this;
        }

        Options existingPump(BlockEntry<? extends PumpBlock> pump) {
            this.existingPump = pump;
            this.pump = false;
            return this;
        }


        Options existingSmartPipe(BlockEntry<? extends SmartFluidPipeBlock> smartPipe) {
            this.existingSmartFluidPipe = smartPipe;
            this.smartFluidPipe = false;
            return this;
        }

        Options existingValve(BlockEntry<? extends FluidValveBlock> valve){
            this.existingFluidValve = valve;
            this.fluidValve = false;
            return this;
        }

        Options existingValveHandle(BlockEntry<? extends ValveHandleBlock> handle){
            this.existingValveHandle = handle;
            this.valveHandle = false;
            return this;
        }

        Options existingTank(BlockEntry<? extends FluidTankBlock> tank){
            this.existingFluidTank = tank;
            this.fluidTank = false;
            return this;
        }

        Options existingHosePulley(BlockEntry<? extends HosePulleyBlock> hosePulley){
            this.existingHosePulley = hosePulley;
            this.hosePulley = false;
            return this;
        }

        Options existingItemDrain(BlockEntry<? extends ItemDrainBlock> itemDrain){
            this.existingItemDrain = itemDrain;
            this.itemDrain = false;
            return this;
        }

        Options existingPortableFluidInterface(BlockEntry<? extends PortableStorageInterfaceBlock> portableFluidInterface){
            this.existingPortableFluidInterface = portableFluidInterface;
            this.portableFluidInterface = false;
            return this;
        }

        Options existingSteamEngine(BlockEntry<? extends SteamEngineBlock> steamEngine){
            this.existingSteamEngine = steamEngine;
            this.steamEngine = false;
            return this;
        }

        Options existingWhistle(BlockEntry<? extends WhistleBlock> whistle){
            this.existingWhistle = whistle;
            this.whistle = false;
            return this;
        }

        Options existingSpout(BlockEntry<? extends SpoutBlock> spout){
            this.existingSpout = spout;
            this.spout = false;
            return this;
        }


        public Options kjsGenerated(){
            this.isKJSGenerated = true;
            return this;
        }


    }
}
