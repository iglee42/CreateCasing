package fr.iglee42.createcasing.mixins.create.fluids;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.api.connectivity.ConnectivityHandler;
import com.simibubi.create.content.fluids.tank.FluidTankItem;
import com.simibubi.create.foundation.blockEntity.IMultiBlockEntityContainer;
import fr.iglee42.createcasing.blocks.fluids.CustomFluidTankBlock;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FluidTankItem.class,remap = false)
public class FluidTankItemMixin {

    @WrapOperation(method = "tryMultiPlace", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/api/connectivity/ConnectivityHandler;partAt(Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;"))
    private <T extends BlockEntity & IMultiBlockEntityContainer> T encased$workWithCustomTanks(BlockEntityType<?> type, BlockGetter level, BlockPos pos, Operation<T> original) {
        if (level.getBlockState(pos).getBlock() instanceof CustomFluidTankBlock)
            return ConnectivityHandler.partAt(EncasedBlockEntities.FLUID_TANK.get(), level, pos);
        return original.call(type, level, pos);
    }
}
