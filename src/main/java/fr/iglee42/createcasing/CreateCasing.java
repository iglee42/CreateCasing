package fr.iglee42.createcasing;

import com.mojang.logging.LogUtils;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import fr.iglee42.createcasing.compat.kubejs.KubeJSCompatInit;
import fr.iglee42.createcasing.config.ModConfigs;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import fr.iglee42.createcasing.registries.ModBlocks;
import fr.iglee42.createcasing.registries.ModPackets;
import fr.iglee42.createcasing.registries.ModSounds;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.simibubi.create.content.kinetics.base.HorizontalKineticBlock.HORIZONTAL_FACING;

@Mod(CreateCasing.MODID)
public class CreateCasing {

    public static final String MODID = "createcasing";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab TAB = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon() {return ModBlocks.BRASS_GEARBOX.asStack();}
    };


    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);
    public static List<ItemLike> hidedItems = new ArrayList<>();

    static {
        REGISTRATE.setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
                .andThen(TooltipModifier.mapNull(KineticStats.create(item))));
    }
    public CreateCasing() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get()
                .getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        ModConfigs.register(ModLoadingContext.get());

        REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());

        //if (isExtendedCogsLoaded())CreateExtendedCogwheelsCompat.REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());

        //if (isExtendedCogsLoaded()) ExtendedCogwheels.registrate().addRegisterCallback(Registry.BLOCK_REGISTRY, CreateExtendedCogwheelsCompat::register);

        ModSounds.prepare();
        ModBlocks.register();
        ModBlockEntities.register();
        ModPackets.registerPackets();

        LOGGER.info("MAIN CALL");
        ModBlocks.registerEncasedShafts();

        if (ModList.get().isLoaded("kubejs")) {
            KubeJSCompatInit.init();
        }

        //if (isCrystalClearLoaded()) CreateCrystalClearCompatibility.register();


        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> CreateCasingClient.onCtorClient(modEventBus, forgeEventBus));

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        forgeEventBus.addListener(this::onPlayerRightClickOnBlock);

        modEventBus.addGenericListener(SoundEvent.class,ModSounds::register);


        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }


    public static void hideItem(ItemLike it){
        hidedItems.add(it);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }




    private void onPlayerRightClickOnBlock(PlayerInteractEvent.RightClickBlock event){
        Level world = event.getPlayer().getLevel();
        if (event.getItemStack().isEmpty()) return;
        if (AllBlocks.MECHANICAL_PRESS.has(world.getBlockState(event.getPos()))){
            BlockState blockState = world.getBlockState(event.getPos());
            Direction facing = blockState.getValue(HORIZONTAL_FACING);
            if (event.getItemStack().is(AllBlocks.BRASS_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.BRASS_PRESS.getDefaultState().setValue(HORIZONTAL_FACING, facing));
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(AllBlocks.COPPER_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.COPPER_PRESS.getDefaultState().setValue(HORIZONTAL_FACING, facing));
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(AllBlocks.RAILWAY_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.RAILWAY_PRESS.getDefaultState().setValue(HORIZONTAL_FACING, facing));
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(AllBlocks.INDUSTRIAL_IRON_BLOCK.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.INDUSTRIAL_IRON_PRESS.getDefaultState().setValue(HORIZONTAL_FACING, facing));
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(ModBlocks.CREATIVE_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.CREATIVE_PRESS.getDefaultState().setValue(HORIZONTAL_FACING, facing));
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        } else if (AllBlocks.MECHANICAL_MIXER.has(world.getBlockState(event.getPos()))){
            if (event.getItemStack().is(AllBlocks.BRASS_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.BRASS_MIXER.getDefaultState());
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(AllBlocks.COPPER_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.COPPER_MIXER.getDefaultState());
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(AllBlocks.RAILWAY_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.RAILWAY_MIXER.getDefaultState());
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(AllBlocks.INDUSTRIAL_IRON_BLOCK.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.INDUSTRIAL_IRON_MIXER.getDefaultState());
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(ModBlocks.CREATIVE_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.CREATIVE_MIXER.getDefaultState());
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        } else if (AllBlocks.DEPOT.has(world.getBlockState(event.getPos()))){
            if (event.getItemStack().is(AllBlocks.BRASS_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.BRASS_DEPOT.getDefaultState());
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(AllBlocks.COPPER_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.COPPER_DEPOT.getDefaultState());
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(AllBlocks.RAILWAY_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.RAILWAY_DEPOT.getDefaultState());
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(AllBlocks.INDUSTRIAL_IRON_BLOCK.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.INDUSTRIAL_IRON_DEPOT.getDefaultState());
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else if (event.getItemStack().is(ModBlocks.CREATIVE_CASING.get().asItem())){
                world.setBlockAndUpdate(event.getPos(), ModBlocks.CREATIVE_DEPOT.getDefaultState());
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }


}
