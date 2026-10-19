package fr.iglee42.createcasing.registries.data;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.DeployingRecipeGen;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.registries.EncasedTags;
import fr.iglee42.createcasing.sets.SetBase;
import fr.iglee42.createcasing.sets.casings.CasingSet;
import fr.iglee42.createcasing.sets.casings.CasingSets;
import fr.iglee42.createcasing.sets.fluids.FluidSet;
import fr.iglee42.createcasing.sets.fluids.FluidSets;
import fr.iglee42.createcasing.sets.transmissions.TransmissionSet;
import fr.iglee42.createcasing.sets.transmissions.TransmissionSets;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class EncasedRecipeGens extends DeployingRecipeGen {

    private final CompletableFuture<HolderLookup.Provider> registries;

    public EncasedRecipeGens(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, CreateCasing.MODID);
        this.registries = registries;

        for (CasingSet set : CasingSets.getSets()) {
            addRecipes(set, EncasedTags.EItemTags.GEARBOXES, CasingSet::getGearbox);
            addRecipes(set, EncasedTags.EItemTags.MIXERS, CasingSet::getMixer);
            addRecipes(set, EncasedTags.EItemTags.PRESSES, CasingSet::getPress);
            addRecipes(set, EncasedTags.EItemTags.DEPOTS, CasingSet::getDepot);
            addRecipes(set, EncasedTags.EItemTags.CHAIN_DRIVES, CasingSet::getChainDrive);
            addRecipes(set, EncasedTags.EItemTags.CHAIN_GEARSHIFTS, CasingSet::getChainGearshift);
            addRecipes(set, EncasedTags.EItemTags.CONFIGURABLE_GEARBOXES, CasingSet::getConfigurableGearbox);
            addRecipes(set, EncasedTags.EItemTags.CHAIN_CONVEYORS, CasingSet::getChainConveyor);
            addRecipes(set, EncasedTags.EItemTags.GEARSHIFTS, CasingSet::getGearshift);
            addRecipes(set, EncasedTags.EItemTags.CLUTCHES, CasingSet::getClutch);
            addRecipes(set, EncasedTags.EItemTags.AUTO_CLUTCHES, CasingSet::getAutoClutch);
            addRecipes(set, EncasedTags.EItemTags.DEPLOYERS, CasingSet::getDeployer);
            addRecipes(set, EncasedTags.EItemTags.STORAGE_INTERFACES, CasingSet::getStorageInterface);
            addRecipes(set, EncasedTags.EItemTags.ENCASED_FANS, CasingSet::getEncasedFan);
            addRecipes(set, EncasedTags.EItemTags.HARVESTERS, CasingSet::getHarvester);
            addRecipes(set, EncasedTags.EItemTags.SAWS, CasingSet::getSaw);
            addRecipes(set, EncasedTags.EItemTags.DRILLS, CasingSet::getDrill);
            addRecipes(set, EncasedTags.EItemTags.PLOUGHS, CasingSet::getPlough);
            addRecipes(set, EncasedTags.EItemTags.ROLLERS, CasingSet::getRoller);
        }

        for (TransmissionSet set : TransmissionSets.getSets()) {
            addRecipes(set, EncasedTags.EItemTags.SHAFTS, TransmissionSet::getShaft);
            addRecipes(set, EncasedTags.EItemTags.COGWHEELS, TransmissionSet::getCogwheel);
            addRecipes(set, EncasedTags.EItemTags.LARGE_COGWHEELS, TransmissionSet::getLargeCogwheel);
        }

        for (FluidSet set : FluidSets.getSets()) {
            addRecipes(set, EncasedTags.EItemTags.FLUID_PIPES, FluidSet::getFluidPipe);
            addRecipes(set, EncasedTags.EItemTags.SMART_FLUID_PIPES, FluidSet::getSmartFluidPipe);
            addRecipes(set, EncasedTags.EItemTags.MECHANICAL_PUMPS, FluidSet::getPump);
            addRecipes(set, EncasedTags.EItemTags.FLUID_TANKS, FluidSet::getFluidTank);
            addRecipes(set, EncasedTags.EItemTags.STEAM_ENGINES, FluidSet::getSteamEngine);
            addRecipes(set, EncasedTags.EItemTags.ITEM_DRAINS, FluidSet::getItemDrain);
            addRecipes(set, EncasedTags.EItemTags.FLUID_VALVES, FluidSet::getFluidValve);
            addRecipes(set, EncasedTags.EItemTags.HOSE_PULLEYS, FluidSet::getHosePulley);
            addRecipes(set, EncasedTags.EItemTags.PORTABLE_FLUID_INTERFACES, FluidSet::getPortableFluidInterface);
            addRecipes(set, EncasedTags.EItemTags.STEAM_WHISTLES, FluidSet::getWhistle);
            addRecipes(set, EncasedTags.EItemTags.SPOUTS, FluidSet::getSpout);
        }

        register(recipeOutput->{ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CasingSets.ANDESITE.getAutoClutch())
                .requires(AllBlocks.ANDESITE_CASING)
                .requires(AllBlocks.SHAFT)
                .requires(AllItems.ELECTRON_TUBE)
                .unlockedBy("has_electron_tube", inventoryTrigger(ItemPredicate.Builder.item().of(AllItems.ELECTRON_TUBE.get()).build()))
                .save(recipeOutput,CreateCasing.asResource("crafting/auto_clutch"));
        });

        register(recipeOutput-> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CasingSets.ANDESITE.getConfigurableGearbox())
                .pattern(" S ")
                .pattern("SCS")
                .pattern(" S ")
                .define('S', AllBlocks.LARGE_COGWHEEL)
                .define('C', AllBlocks.GEARBOX)
                .unlockedBy("has_gearbox", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.GEARBOX.get()).build()))
                .save(recipeOutput,CreateCasing.asResource("crafting/configurable_gearbox")));
    }

    private <T extends SetBase<?, ?>> void addRecipes(T set, EncasedTags.EItemTags category,
                                                      Function<T, ? extends ItemLike> resultGetter) {
        ItemLike result = resultGetter.apply(set);
        if (result == null || result.asItem() == Items.AIR)
            return;

        ResourceLocation recipeId = CreateCasing.asResource(category.name().toLowerCase() + "/" + set.getName());
        Ingredient categoryIngredient = Ingredient.of(category.tag);
        TagKey<Item> categoryTag = category.tag;

        Optional<Ingredient> itemIngredient = getItemIngredient(set, recipeId.getPath());
        if (itemIngredient.isEmpty())
            return;

        register(recipeOutput -> {
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result)
                    .requires(categoryIngredient)
                    .requires(itemIngredient.get())
                    .unlockedBy("has_matching_block", inventoryTrigger(
                            ItemPredicate.Builder.item()
                                    .of(categoryTag)
                                    .build()))
                    .save(recipeOutput, recipeId.withPrefix("crafting/").withSuffix("_manual_only"));
        });

        register(recipeOutput -> {
            getBuilder(recipeId)
                    .require(categoryIngredient)
                    .require(itemIngredient.get())
                    .output(result)
                    .build(recipeOutput);
        });
    }

    private Optional<Ingredient> getItemIngredient(SetBase<?, ?> set, String recipePath) {
        HolderLookup.Provider provider = registries.join();
        Optional<Ingredient> ingredient = set.getItemIngredient(provider);
        if (ingredient.isEmpty())
            CreateCasing.LOGGER.warn("Skipping recipes for set {} because its item source cannot be represented as a recipe ingredient", recipePath);
        return ingredient;
    }
}