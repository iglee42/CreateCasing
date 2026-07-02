package fr.iglee42.createcasing;

import com.simibubi.create.content.logistics.depot.DepotBehaviour;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import fr.iglee42.createcasing.commands.CreateCasingCommand;
import fr.iglee42.createcasing.compat.sliceanddice.EncasedSliceAndDiceCompat;
import fr.iglee42.createcasing.config.EncasedConfigs;
import fr.iglee42.createcasing.kubejs.KJSExternalHandler;
import fr.iglee42.createcasing.mixins.create.DeployerBlockEntityAccessor;
import fr.iglee42.createcasing.mixins.create.fluids.FluidTankBlockEntityAccessor;
import fr.iglee42.createcasing.mixins.create.fluids.ItemDrainBlockEntityAccessor;
import fr.iglee42.createcasing.mixins.create.fluids.SpoutBlockEntityAccessor;
import fr.iglee42.createcasing.registries.*;
import net.createmod.catnip.lang.FontHelper;
import net.createmod.catnip.platform.CatnipServices;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Mod(CreateCasing.MODID)
public class CreateCasing {

    public static final String MODID = "createcasing";

    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID)
            .defaultCreativeTab((ResourceKey<CreativeModeTab>) null);
    public static List<ItemLike> hidedItems = new ArrayList<>();

    public static KJSExternalHandler KJS_HANDLER = () -> {};

    static {
        REGISTRATE.setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                .andThen(TooltipModifier.mapNull(KineticStats.create(item))));
    }
    public CreateCasing(IEventBus modEventBus, ModContainer container) {
        IEventBus neoForgeEventBus = NeoForge.EVENT_BUS;


        REGISTRATE.registerEventListeners(modEventBus);

        EncasedSounds.prepare();
        EncasedBlocks.register();
        EncasedItems.register();
        EncasedBlockEntities.register();
        EncasedCreativeModeTabs.register(modEventBus);
        EncasedPackets.register();

        EncasedConfigs.register(ModLoadingContext.get(),container);

        if (ModList.get().isLoaded("sliceanddice"))
            EncasedSliceAndDiceCompat.register(modEventBus);

        CatnipServices.PLATFORM.executeOnClientOnly(() -> () -> CreateCasingClient.onCtorClient(modEventBus));

        neoForgeEventBus.addListener(this::registerCommands);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(EncasedSounds::register);
        modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(EventPriority.LOWEST, this::gatherData);


    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }


    public static void hideItem(ItemLike it){
        hidedItems.add(it);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void registerCommands(RegisterCommandsEvent event){
        if (!FMLEnvironment.production) new CreateCasingCommand(event.getDispatcher());
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event){
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                EncasedBlockEntities.DEPOT.get(),
                (be, context) -> be.getBehaviour(DepotBehaviour.TYPE).itemHandler
        );

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                EncasedBlockEntities.DEPLOYER.get(),
                (be, context) ->  {
                    DeployerBlockEntityAccessor accessor = (DeployerBlockEntityAccessor) be;
                    if (accessor.getInvHandler() == null)
                        accessor.invokeInitHandler();
                    return accessor.getInvHandler();
                }
        );

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                EncasedBlockEntities.SAW.get(),
                (be, context) -> {
                    if (context != Direction.DOWN)
                        return be.inventory;
                    return null;
                }
        );

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                EncasedBlockEntities.FLUID_TANK.get(),
                (be, context) -> {
                    FluidTankBlockEntityAccessor accessor = (FluidTankBlockEntityAccessor) be;
                    if (accessor.encased$getFluidCapability() == null)
                        accessor.encased$refreshCapability();
                    return accessor.encased$getFluidCapability();
                }
        );

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                EncasedBlockEntities.ITEM_DRAIN.get(),
                (be, context) -> {
                    if (context != null && context.getAxis().isHorizontal())
                        return ((ItemDrainBlockEntityAccessor)be).encased$getItemHandlers().get(context);
                    return null;
                }
        );

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                EncasedBlockEntities.ITEM_DRAIN.get(),
                (be, context) -> {
                    if (context != Direction.UP)
                        return ((ItemDrainBlockEntityAccessor)be).encased$getInternalTank().getCapability();
                    return null;
                }
        );

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                EncasedBlockEntities.SPOUT.get(),
                (be, context) -> {
                    if (context != Direction.DOWN)
                        return ((SpoutBlockEntityAccessor)be).encased$getTank().getCapability();
                    return null;
                }
        );
    }

    private void gatherData(GatherDataEvent event) {
        //event.getGenerator().addProvider(true, REGISTRATE.setDataProvider(new RegistrateDataProvider(REGISTRATE, MODID, event)));
        event.getGenerator().addProvider(event.includeServer(),new EncasedRecipeGens(event.getGenerator().getPackOutput(),event.getLookupProvider()));
    }
}
