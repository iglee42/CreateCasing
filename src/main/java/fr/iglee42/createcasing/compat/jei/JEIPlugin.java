package fr.iglee42.createcasing.compat.jei;

import fr.iglee42.createcasing.CreateCasing;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation("createcasing:jei");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IModPlugin.super.registerRecipes(registration);
        List<ItemStack> stacks = new ArrayList<>();
        CreateCasing.hidedItems.stream().filter(i->i != Items.AIR).forEach(i->stacks.add(new ItemStack(i)));
        registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,stacks);
    }
}
