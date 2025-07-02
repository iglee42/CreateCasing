package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(value = BeltBlockEntity.CasingType.class,remap = false)
public class BeltCasingTypeMixin {
    @Shadow
    @Final
    @Mutable
    private static BeltBlockEntity.CasingType[] $VALUES;

    @Invoker("<init>")
    public static BeltBlockEntity.CasingType encased$initInvoker(String internalName, int internalId){
        throw new AssertionError();
    }

    @Inject(method = "<clinit>",at = @At("TAIL"))
    private static void cmr$clinit(CallbackInfo ci) {
        EncasedBlocks.COPPER_BELT_CASING = encased$addVariant("COPPER");
        EncasedBlocks.RAILWAY_BELT_CASING = encased$addVariant("RAILWAY");
        EncasedBlocks.INDUSTRIAL_IRON_BELT_CASING = encased$addVariant("INDUSTRIAL_IRON");
        EncasedBlocks.WEATHERED_IRON_BELT_CASING = encased$addVariant("WEATHERED_IRON");
        EncasedBlocks.CREATIVE_BELT_CASING = encased$addVariant("CREATIVE");
        EncasedBlocks.REFINED_RADIANCE_BELT_CASING = encased$addVariant("REFINED_RADIANCE");
        EncasedBlocks.SHADOW_STEEL_BELT_CASING = encased$addVariant("SHADOW_STEEL");
    }

    @Unique
    private static BeltBlockEntity.CasingType encased$addVariant(String internalName) {
        ArrayList<BeltBlockEntity.CasingType> variants = new ArrayList<>(Arrays.asList($VALUES));
        BeltBlockEntity.CasingType casing = encased$initInvoker(internalName, variants.get(variants.size() - 1).ordinal() + 1);
        variants.add(casing);
        BeltCasingTypeMixin.$VALUES = variants.toArray(new BeltBlockEntity.CasingType[0]);
        return casing;
    }
}
