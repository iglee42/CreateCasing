package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import fr.iglee42.createcasing.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.lang.reflect.InvocationTargetException;


@Mixin(value = BeltBlockEntity.class)
public abstract class BeltBlockEntityMixin {


    @Shadow public BeltBlockEntity.CasingType casing;

    @Shadow public abstract BeltBlockEntity getControllerBE();

    @Inject(method = "setCasingType",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;levelEvent(ILnet/minecraft/core/BlockPos;I)V",shift = At.Shift.BEFORE),locals = LocalCapture.CAPTURE_FAILSOFT,cancellable = true)
    private void encased$changeParticle(BeltBlockEntity.CasingType type, CallbackInfo ci, BlockState blockState, boolean shouldBlockHaveCasing) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        /*if (casing.equals(ModBlocks.COPPER_BELT_CASING)){
            Level level = getControllerBE().getLevel();
            BlockPos pos = (BlockPos) BeltBlockEntity.class.getField("worldPosition").get(this);
            level.levelEvent(2001, pos,
                    Block.getId(AllBlocks.COPPER_CASING.getDefaultState()));
            if (blockState.getValue(BeltBlock.CASING) != shouldBlockHaveCasing)
                KineticBlockEntity.switchToBlockState(level, pos,
                        blockState.setValue(BeltBlock.CASING, shouldBlockHaveCasing));
            casing = type;
            BeltBlockEntity.class.getMethod("setChanged").invoke(this);
            BeltBlockEntity.class.getMethod("sendData").invoke(this);
            ci.cancel();
        }
        if (casing.equals(ModBlocks.RAILWAY_BELT_CASING)){
            Level level = getControllerBE().getLevel();
            BlockPos pos = (BlockPos) BeltBlockEntity.class.getField("worldPosition").get(this);
            level.levelEvent(2001, pos,
                    Block.getId( AllBlocks.RAILWAY_CASING.getDefaultState()));
            if (blockState.getValue(BeltBlock.CASING) != shouldBlockHaveCasing)
                KineticBlockEntity.switchToBlockState(level, pos,
                        blockState.setValue(BeltBlock.CASING, shouldBlockHaveCasing));
            casing = type;
            BeltBlockEntity.class.getMethod("setChanged").invoke(this);
            BeltBlockEntity.class.getMethod("sendData").invoke(this);
            ci.cancel();
        }
        if (casing.equals(ModBlocks.INDUSTRIAL_IRON_BELT_CASING)){
            Level level = getControllerBE().getLevel();
            BlockPos pos = (BlockPos) BeltBlockEntity.class.getField("worldPosition").get(this);
            level.levelEvent(2001, pos,
                    Block.getId( AllBlocks.INDUSTRIAL_IRON_BLOCK.getDefaultState()));
            if (blockState.getValue(BeltBlock.CASING) != shouldBlockHaveCasing)
                KineticBlockEntity.switchToBlockState(level, pos,
                        blockState.setValue(BeltBlock.CASING, shouldBlockHaveCasing));
            casing = type;
            BeltBlockEntity.class.getMethod("setChanged").invoke(this);
            BeltBlockEntity.class.getMethod("sendData").invoke(this);
            ci.cancel();
        }
        if (casing.equals(ModBlocks.CREATIVE_BELT_CASING)){
            Level level = getControllerBE().getLevel();
            BlockPos pos = (BlockPos) BeltBlockEntity.class.getField("worldPosition").get(this);
            level.levelEvent(2001, pos,
                    Block.getId(ModBlocks.CREATIVE_CASING.getDefaultState()));
            if (blockState.getValue(BeltBlock.CASING) != shouldBlockHaveCasing)
                KineticBlockEntity.switchToBlockState(level, pos,
                        blockState.setValue(BeltBlock.CASING, shouldBlockHaveCasing));
            casing = type;
            BeltBlockEntity.class.getMethod("setChanged").invoke(this);
            BeltBlockEntity.class.getMethod("sendData").invoke(this);
            ci.cancel();
        }*/
    }


}
