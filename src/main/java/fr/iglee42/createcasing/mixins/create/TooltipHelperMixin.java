package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.utility.Components;
import fr.iglee42.createcasing.compatibility.createextendedcogs.CreateExtendedCogwheelsCompat;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TooltipHelper.class,remap = false)
public class TooltipHelperMixin {

    @Inject(method = "buildToolTip",at = @At(value = "HEAD"))
    private static void inject(String translationKey, ItemStack stack, CallbackInfoReturnable<ItemDescription> cir){
       if (ModList.get().isLoaded("extendedgears")){
           if (ForgeRegistries.ITEMS.getKey(stack.getItem()).getNamespace().equals(CreateExtendedCogwheelsCompat.REGISTER_MODID)){
               AllSections module = AllSections.UNASSIGNED;
               ItemDescription tooltip = new ItemDescription(module.getTooltipPalette());
               String summaryKey = translationKey + ".summary";
               if (I18n.exists(summaryKey))
                   tooltip = tooltip.withSummary(Components.literal(I18n.get(summaryKey)));
               for (int i = 1; i < 100; i++) {
                   String conditionKey = translationKey + ".condition" + i;
                   String behaviourKey = translationKey + ".behaviour" + i;
                   if (!I18n.exists(conditionKey))
                       break;
                   if (i == 1)
                       tooltip.getLinesOnShift()
                               .add(Components.immutableEmpty());
                   tooltip.withBehaviour(I18n.get(conditionKey), I18n.get(behaviourKey));
               }
               for (int i = 1; i < 100; i++) {
                   String controlKey = translationKey + ".control" + i;
                   String actionKey = translationKey + ".action" + i;
                   if (!I18n.exists(controlKey))
                       break;
                   tooltip.withControl(I18n.get(controlKey), I18n.get(actionKey));
               }

               cir.setReturnValue(tooltip.createTabs());
           }
       }
    }

}