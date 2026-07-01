package fr.iglee42.createcasing.mixins.create.fluids;

import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = SpoutBlockEntity.class,remap = false)
public interface SpoutBlockEntityAccessor {

    @Accessor("tank")
    SmartFluidTankBehaviour encased$getTank();

}
