package fr.iglee42.createcasing.mixins.create.fluids;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = HosePulleyBlockEntity.class,remap = false)
public interface HosePulleyBlockEntityAccessor {

    @Accessor("handler")
    HosePulleyFluidHandler encased$getHandler();
}
