package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import fr.iglee42.createcasing.compatibility.createextendedcogs.CreateExtendedCogwheelsCompat;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CreateRegistrate.class,remap = false)
public class CreateRegistrateMixin {

    @Inject(cancellable = true,method = "getSection(Lnet/minecraftforge/registries/IForgeRegistryEntry;)Lcom/simibubi/create/content/AllSections;",at = @At("HEAD"))
    private void inject(IForgeRegistryEntry<?> entry, CallbackInfoReturnable<AllSections> cir){
        if (ModList.get().isLoaded("extendedgears")) {
            if (entry.getRegistryName().getNamespace().equals(CreateExtendedCogwheelsCompat.REGISTER_MODID)) {
                cir.setReturnValue(AllSections.UNASSIGNED);
            }
        }
    }

}
