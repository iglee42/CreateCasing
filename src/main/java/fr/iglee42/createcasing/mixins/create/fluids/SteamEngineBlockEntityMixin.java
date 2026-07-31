package fr.iglee42.createcasing.mixins.create.fluids;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.fluids.FluidSets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SteamEngineBlockEntity.class,remap = false)
public class SteamEngineBlockEntityMixin extends BlockEntity {

    public SteamEngineBlockEntityMixin(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }

    @Inject(method = "isValid", at = @At(value = "HEAD"),cancellable = true)
    private void encased$allowAllTanks(CallbackInfoReturnable<Boolean> cir){
        if (level == null) return;
        Direction dir = SteamEngineBlock.getConnectedDirection(getBlockState()).getOpposite();
        BlockState state = level.getBlockState(getBlockPos().relative(dir));

        if (state.getBlock() instanceof FluidTankBlock && FluidSets.getSets().stream().anyMatch(set->set.isInSet(state.getBlock())))
            cir.setReturnValue(true);
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean encased$allowAllEngineTick(BlockEntry<?> instance, BlockState state, Operation<Boolean> original){
        if (state.getBlock() instanceof SteamEngineBlock) return true;
        return original.call(instance,state);
    }
}
