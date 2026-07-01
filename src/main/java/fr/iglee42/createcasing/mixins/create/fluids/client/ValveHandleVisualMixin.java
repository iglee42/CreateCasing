package fr.iglee42.createcasing.mixins.create.fluids.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.kinetics.crank.ValveHandleVisual;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import fr.iglee42.createcasing.fluids.FluidSet;
import fr.iglee42.createcasing.fluids.FluidSets;
import net.createmod.catnip.render.CachedBuffers;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(value = ValveHandleVisual.class,remap = false)
public class ValveHandleVisualMixin {


    @Redirect(method = "<init>",at= @At(value = "INVOKE", target = "Ldev/engine_room/flywheel/lib/model/Models;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;)Ldev/engine_room/flywheel/api/model/Model;",ordinal = 0))
    private Model encased$replaceValve(PartialModel partial, @Local(name = "state") BlockState state){
        Optional<FluidSet> set = FluidSets.getSets().stream().filter(FluidSet::doesGenerateValveHandle).filter(s->s.isInSet(state.getBlock())).findFirst();
        return set.map(fluidSet -> Models.partial(fluidSet.getValveHandleModel())).orElseGet(() -> Models.partial(partial));
    }


}
