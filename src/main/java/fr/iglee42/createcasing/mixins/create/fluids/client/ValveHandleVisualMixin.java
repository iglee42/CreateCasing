package fr.iglee42.createcasing.mixins.create.fluids.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.kinetics.crank.ValveHandleVisual;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.sets.fluids.FluidSet;
import fr.iglee42.createcasing.sets.fluids.FluidSets;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(value = ValveHandleVisual.class,remap = false)
public class ValveHandleVisualMixin {


    @WrapOperation(method = "<init>",at= @At(value = "INVOKE", target = "Ldev/engine_room/flywheel/lib/model/Models;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;)Ldev/engine_room/flywheel/api/model/Model;",ordinal = 0))
    private Model encased$replaceValve(PartialModel partial, Operation<Model> original, @Local(name = "state") BlockState state){
        Optional<FluidSet> set = FluidSets.getSets().stream().filter(FluidSet::doesGenerateValveHandle).filter(s->s.isInSet(state.getBlock())).findFirst();
        if(set.isPresent())return Models.partial(set.get().getValveHandleModel());
        return original.call(partial);
    }


}
