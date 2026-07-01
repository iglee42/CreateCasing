package fr.iglee42.createcasing.utils;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.registry.SimpleRegistry;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.config.EncasedConfigs;
import fr.iglee42.createcasing.fluids.FluidSet;
import fr.iglee42.createcasing.fluids.FluidSets;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EncasedStressKeysModifier {
    
    private static final SimpleRegistry<Block, Block> STRESS_KEY_LOOKUP = SimpleRegistry.create();
    private static final List<CasingType> CASING_TYPES = List.of(
            new CasingType(AllBlocks.ANDESITE_ENCASED_SHAFT, CasingSet::doesGenerateShaft, CasingSet::getShaft),
            new CasingType(AllBlocks.ANDESITE_ENCASED_COGWHEEL, CasingSet::doesGenerateCogwheel, CasingSet::getCogwheel),
            new CasingType(AllBlocks.ANDESITE_ENCASED_LARGE_COGWHEEL, CasingSet::doesGenerateLargeCogwheel, CasingSet::getLargeCogwheel),
            new CasingType(AllBlocks.ENCASED_FLUID_PIPE, CasingSet::doesGenerateFluidPipe, CasingSet::getFluidPipe),
            new CasingType(AllBlocks.GEARBOX, CasingSet::doesGenerateGearbox, CasingSet::getGearbox),
            new CasingType(AllBlocks.MECHANICAL_PRESS, CasingSet::doesGeneratePress, CasingSet::getPress),
            new CasingType(AllBlocks.MECHANICAL_MIXER, CasingSet::doesGenerateMixer, CasingSet::getMixer),
            new CasingType(AllBlocks.DEPOT, CasingSet::doesGenerateDepot, CasingSet::getDepot),
            new CasingType(AllBlocks.ENCASED_CHAIN_DRIVE, CasingSet::doesGenerateChainDrive, CasingSet::getChainDrive),
            new CasingType(AllBlocks.ADJUSTABLE_CHAIN_GEARSHIFT, CasingSet::doesGenerateChainGearshift, CasingSet::getChainGearshift),
            new CasingType(CasingSets.ANDESITE::getConfigurableGearbox, CasingSet::doesGenerateConfigurableGearbox, CasingSet::getConfigurableGearbox),
            new CasingType(AllBlocks.CHAIN_CONVEYOR, CasingSet::doesGenerateChainConveyor, CasingSet::getChainConveyor),
            new CasingType(AllBlocks.GEARSHIFT, CasingSet::doesGenerateGearshift, CasingSet::getGearshift),
            new CasingType(AllBlocks.CLUTCH, CasingSet::doesGenerateClutch, CasingSet::getClutch),
            new CasingType(CasingSets.ANDESITE::getAutoClutch, CasingSet::doesGenerateAutoClutch, CasingSet::getAutoClutch),
            new CasingType(AllBlocks.DEPLOYER, CasingSet::doesGenerateDeployer, CasingSet::getDeployer),
            new CasingType(AllBlocks.PORTABLE_STORAGE_INTERFACE, CasingSet::doesGenerateStorageInterface, CasingSet::getStorageInterface),
            new CasingType(AllBlocks.ENCASED_FAN, CasingSet::doesGenerateEncasedFan, CasingSet::getEncasedFan),
            new CasingType(AllBlocks.MECHANICAL_HARVESTER, CasingSet::doesGenerateHarvester, CasingSet::getHarvester),
            new CasingType(AllBlocks.MECHANICAL_SAW, CasingSet::doesGenerateSaw, CasingSet::getSaw),
            new CasingType(AllBlocks.MECHANICAL_DRILL, CasingSet::doesGenerateDrill, CasingSet::getDrill),
            new CasingType(AllBlocks.MECHANICAL_PLOUGH, CasingSet::doesGeneratePlough, CasingSet::getPlough),
            new CasingType(AllBlocks.MECHANICAL_ROLLER, CasingSet::doesGenerateRoller, CasingSet::getRoller)
    );
    private static final List<FluidType> FLUID_TYPES = List.of(
            new FluidType(AllBlocks.FLUID_PIPE, FluidSet::doesGenerateFluidPipe, FluidSet::getFluidPipe),
            new FluidType(AllBlocks.GLASS_FLUID_PIPE, FluidSet::doesGenerateFluidPipe, FluidSet::getGlassFluidPipe),
            new FluidType(AllBlocks.MECHANICAL_PUMP, FluidSet::doesGeneratePump, FluidSet::getPump),
            new FluidType(AllBlocks.SMART_FLUID_PIPE, FluidSet::doesGenerateSmartFluidPipe, FluidSet::getSmartFluidPipe),
            new FluidType(AllBlocks.FLUID_TANK, FluidSet::doesGenerateFluidTank, FluidSet::getFluidTank),
            new FluidType(AllBlocks.STEAM_ENGINE, FluidSet::doesGenerateSteamEngine, FluidSet::getSteamEngine),
            new FluidType(AllBlocks.ITEM_DRAIN, FluidSet::doesGenerateItemDrain, FluidSet::getItemDrain),
            new FluidType(AllBlocks.FLUID_VALVE, FluidSet::doesGenerateFluidValve, FluidSet::getFluidValve),
            new FluidType(AllBlocks.COPPER_VALVE_HANDLE, FluidSet::doesGenerateValveHandle, FluidSet::getValveHandle),
            new FluidType(AllBlocks.HOSE_PULLEY, FluidSet::doesGenerateHosePulley, FluidSet::getHosePulley),
            new FluidType(AllBlocks.PORTABLE_FLUID_INTERFACE, FluidSet::doesGeneratePortableFluidInterface, FluidSet::getPortableFluidInterface),
            new FluidType(AllBlocks.STEAM_WHISTLE, FluidSet::doesGenerateWhistle, FluidSet::getWhistle),
            new FluidType(AllBlocks.SPOUT, FluidSet::doesGenerateSpout, FluidSet::getSpout)
    );

    public static Block getStressKey(Block block) {
        ensureLookupInitialized();
        Block key = STRESS_KEY_LOOKUP.get(block);
        return key == null ? block : key;
    }

    private static boolean initialized = false;

    private static void ensureLookupInitialized() {
        if (initialized)
            return;

        initialized = true;

        rebuildLookup();
    }



    private static void rebuildLookup() {
        STRESS_KEY_LOOKUP.invalidate();

        for (CasingSet set : CasingSets.getSets()) {
            for (CasingType type : CASING_TYPES) {
                if (!type.predicate().test(set))
                    continue;

                Block custom = type.getter().apply(set);
                if (custom != null)
                    STRESS_KEY_LOOKUP.register(custom, type.keySupplier().get());
            }
        }

        for (FluidSet set : FluidSets.getSets()) {
            for (FluidType type : FLUID_TYPES) {
                if (!type.predicate().test(set))
                    continue;

                Block custom = type.getter().apply(set);
                if (custom != null)
                    STRESS_KEY_LOOKUP.register(custom, type.keySupplier().get());
            }
        }
    }

    private record CasingType(
            Supplier<? extends Block> keySupplier,
            Predicate<CasingSet> predicate,
            Function<CasingSet, Block> getter
    ) {}

    private record FluidType(
            Supplier<? extends Block> keySupplier,
            Predicate<FluidSet> predicate,
            Function<FluidSet, Block> getter
    ) {}





}
