package fr.iglee42.createcasing.mixins.create.fluids;

import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import net.createmod.catnip.animation.LerpedFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = FluidValveBlockEntity.class,remap = false)
public interface FluidValveBlockEntityAccessor {

    @Accessor("pointer")
    LerpedFloat encased$getPointer();
}
