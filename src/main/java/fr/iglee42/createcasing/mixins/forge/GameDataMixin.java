package fr.iglee42.createcasing.mixins.forge;

import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraftforge.registries.GameData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GameData.class,remap = false)
public class GameDataMixin {

    @Inject(method = "postRegisterEvents",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/SpawnPlacements;fireSpawnPlacementEvent()V",shift = At.Shift.AFTER))
    private static void encased$fireAddsOfBeTypes(CallbackInfo ci){
        EncasedBlockEntities.modifyBlockEntity();
    }

}
