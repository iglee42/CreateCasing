package fr.iglee42.createcasing.mixins.create.fluids;

import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = FluidTankBlockEntity.class,remap = false)
public interface FluidTankBlockEntityAccessor {

    @Accessor("fluidCapability")
    IFluidHandler encased$getFluidCapability();

    @Invoker("refreshCapability")
    void encased$refreshCapability();

}
