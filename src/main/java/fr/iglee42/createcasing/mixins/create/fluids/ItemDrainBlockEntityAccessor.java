package fr.iglee42.createcasing.mixins.create.fluids;

import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import com.simibubi.create.content.fluids.drain.ItemDrainItemHandler;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = ItemDrainBlockEntity.class,remap = false)
public interface ItemDrainBlockEntityAccessor {

    @Accessor("itemHandlers")
    Map<Direction, ItemDrainItemHandler> encased$getItemHandlers();

    @Accessor("internalTank")
    SmartFluidTankBehaviour encased$getInternalTank();

}
