package fr.iglee42.createcasing.mixins.create.catnip;

import net.createmod.catnip.render.StitchedSprite;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(value = StitchedSprite.class, remap = false)
public class StitchedSpriteMixin {
    @Shadow @Final @Mutable
    private static Map<ResourceLocation, List<StitchedSprite>> ALL;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void replaceHashMapWithConcurtentHashMap(CallbackInfo ci) {
        ALL = new ConcurrentHashMap<>();
    }
}
