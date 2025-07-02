package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import fr.iglee42.createcasing.CreateCasing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AllSpriteShifts.class,remap = false)
public abstract class AllSpriteShiftsMixin {

    @Inject(method = "getCT(Lcom/simibubi/create/foundation/block/connected/CTType;Ljava/lang/String;)Lcom/simibubi/create/foundation/block/connected/CTSpriteShiftEntry;", at = @At("HEAD"), cancellable = true)
    private static void encased$modifyCreativeCT(CTType type, String blockTextureName, CallbackInfoReturnable<CTSpriteShiftEntry> cir){
        if (blockTextureName.equals("creative_casing") && type.equals(AllCTTypes.RECTANGLE))
            cir.setReturnValue(CTSpriteShifter.getCT(AllCTTypes.OMNIDIRECTIONAL, Create.asResource("block/" + blockTextureName), CreateCasing.asResource("block/" + blockTextureName + "_connected")));
    }

}
