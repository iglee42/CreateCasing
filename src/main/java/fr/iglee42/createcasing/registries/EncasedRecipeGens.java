package fr.iglee42.createcasing.registries;

import com.google.common.base.Supplier;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.BaseRecipeProvider;
import com.simibubi.create.foundation.data.recipe.CommonMetal;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.transmissions.TransmissionSets;
import net.createmod.catnip.registry.RegisteredObjectsHelper;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class EncasedRecipeGens extends BaseRecipeProvider{

    final List<GeneratedRecipe> all = new ArrayList<>();


    /*
     * End of recipe list
     */

    String currentFolder = "";
    String lastFolder = "";

    void enterFolder(String folder) {
        currentFolder +=(currentFolder.isEmpty() ? "" : "/")+ folder;
        lastFolder = folder;
    }

    void leftLastFolder(){
        currentFolder = currentFolder.substring(0,currentFolder.length() - ("/"+lastFolder).length());
    }

    GeneratedRecipeBuilder create(Supplier<ItemLike> result) {
        return new GeneratedRecipeBuilder("", result);
    }

    GeneratedRecipeBuilder create(String path,Supplier<ItemLike> result) {
        return new GeneratedRecipeBuilder(currentFolder + "/" + path, result);
    }


    GeneratedRecipeBuilder create(ItemProviderEntry<? extends ItemLike, ? extends ItemLike> result) {
        return create(result::get);
    }

    BaseRecipeProvider.GeneratedRecipe createSpecial(Function<CraftingBookCategory, Recipe<?>> builder, String recipeType,
                                                     String path) {
        ResourceLocation location = CreateCasing.asResource(recipeType + "/" + currentFolder + "/" + path);
        return register(consumer -> {
            SpecialRecipeBuilder b = SpecialRecipeBuilder.special(builder);
            b.save(consumer, location.toString());
        });
    }



    @Override
    public void buildRecipes(RecipeOutput output) {
        all.forEach(c -> c.register(output));
        CreateCasing.LOGGER.info("{} registered {} recipe{}", getName(), all.size(), all.size() == 1 ? "" : "s");
    }

    protected BaseRecipeProvider.GeneratedRecipe register(BaseRecipeProvider.GeneratedRecipe recipe) {
        all.add(recipe);
        return recipe;
    }

    class GeneratedRecipeBuilder {

        private String path;
        private String suffix;
        private Supplier<? extends ItemLike> result;
        List<ICondition> recipeConditions;

        private Supplier<ItemPredicate> unlockedBy;
        private int amount;

        private GeneratedRecipeBuilder(String path) {
            this.path = path;
            this.recipeConditions = new ArrayList<>();
            this.amount = 1;
            this.suffix = "";
        }

        public GeneratedRecipeBuilder(String path, Supplier<? extends ItemLike> result) {
            this(path);
            this.result = result;
        }


        GeneratedRecipeBuilder returns(int amount) {
            this.amount = amount;
            return this;
        }

        GeneratedRecipeBuilder unlockedBy(Supplier<? extends ItemLike> item) {
            this.unlockedBy = () -> ItemPredicate.Builder.item()
                    .of(item.get())
                    .build();
            return this;
        }

        GeneratedRecipeBuilder unlockedByTag(Supplier<TagKey<Item>> tag) {
            this.unlockedBy = () -> ItemPredicate.Builder.item()
                    .of(tag.get())
                    .build();
            return this;
        }

        GeneratedRecipeBuilder whenModLoaded(String modid) {
            return withCondition(new ModLoadedCondition(modid));
        }

        GeneratedRecipeBuilder whenModMissing(String modid) {
            return withCondition(new NotCondition(new ModLoadedCondition(modid)));
        }

        GeneratedRecipeBuilder withCondition(ICondition condition) {
            recipeConditions.add(condition);
            return this;
        }

        GeneratedRecipeBuilder suffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        // FIXME 5.1 refactor - recipe categories as markers instead of sections?
        BaseRecipeProvider.GeneratedRecipe viaShaped(UnaryOperator<ShapedRecipeBuilder> builder) {
            return register(recipeOutput -> {
                ShapedRecipeBuilder b =
                        builder.apply(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result.get(), amount));
                if (unlockedBy != null)
                    b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));
                RecipeOutput conditionalOutput = recipeOutput.withConditions(recipeConditions.toArray(new ICondition[0]));

                b.save(conditionalOutput, createLocation("crafting"));
            });
        }

        BaseRecipeProvider.GeneratedRecipe viaShapeless(UnaryOperator<ShapelessRecipeBuilder> builder) {
            return register(recipeOutput -> {
                ShapelessRecipeBuilder b =
                        builder.apply(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result.get(), amount));
                if (unlockedBy != null)
                    b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));

                RecipeOutput conditionalOutput = recipeOutput.withConditions(recipeConditions.toArray(new ICondition[0]));

                b.save(conditionalOutput, createLocation("crafting"));
            });
        }

        BaseRecipeProvider.GeneratedRecipe viaNetheriteSmithing(Supplier<? extends Item> base, Supplier<Ingredient> upgradeMaterial) {
            return register(consumer -> {
                SmithingTransformRecipeBuilder b =
                        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.of(base.get()), upgradeMaterial.get(), RecipeCategory.COMBAT, result.get()
                                        .asItem());
                b.unlocks("has_item", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(base.get())
                        .build()));
                b.save(consumer, createLocation("crafting"));
            });
        }

        private ResourceLocation createSimpleLocation(String recipeType) {
            return CreateCasing.asResource( path + suffix);
        }

        private ResourceLocation createLocation(String recipeType) {
            if (path.isEmpty())
                return CreateCasing.asResource(RegisteredObjectsHelper.getKeyOrThrow(result.get()) + suffix);
            return CreateCasing.asResource( path + suffix);
        }

        private ResourceLocation getRegistryName() {
            return RegisteredObjectsHelper.getKeyOrThrow(result.get()
                    .asItem());
        }

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCooking(Supplier<? extends ItemLike> item) {
            return unlockedBy(item).viaCookingIngredient(() -> Ingredient.of(item.get()));
        }

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCookingTag(Supplier<TagKey<Item>> tag) {
            return unlockedByTag(tag).viaCookingIngredient(() -> Ingredient.of(tag.get()));
        }

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCookingIngredient(Supplier<Ingredient> ingredient) {
            return new GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder(ingredient);
        }

        class GeneratedCookingRecipeBuilder {

            private Supplier<Ingredient> ingredient;
            private float exp;
            private int cookingTime;

            GeneratedCookingRecipeBuilder(Supplier<Ingredient> ingredient) {
                this.ingredient = ingredient;
                cookingTime = 200;
                exp = 0;
            }

            GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder forDuration(int duration) {
                cookingTime = duration;
                return this;
            }

            GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder rewardXP(float xp) {
                exp = xp;
                return this;
            }

            BaseRecipeProvider.GeneratedRecipe inFurnace() {
                return inFurnace(b -> b);
            }

            BaseRecipeProvider.GeneratedRecipe inFurnace(UnaryOperator<SimpleCookingRecipeBuilder> builder) {
                return create(RecipeSerializer.SMELTING_RECIPE, builder, SmeltingRecipe::new, 1);
            }

            BaseRecipeProvider.GeneratedRecipe inSmoker() {
                return inSmoker(b -> b);
            }

            BaseRecipeProvider.GeneratedRecipe inSmoker(UnaryOperator<SimpleCookingRecipeBuilder> builder) {
                create(RecipeSerializer.SMELTING_RECIPE, builder, SmeltingRecipe::new, 1);
                create(RecipeSerializer.CAMPFIRE_COOKING_RECIPE, builder, CampfireCookingRecipe::new, 3);
                return create(RecipeSerializer.SMOKING_RECIPE, builder, SmokingRecipe::new, .5f);
            }

            BaseRecipeProvider.GeneratedRecipe inBlastFurnace() {
                return inBlastFurnace(b -> b);
            }

            BaseRecipeProvider.GeneratedRecipe inBlastFurnace(UnaryOperator<SimpleCookingRecipeBuilder> builder) {
                create(RecipeSerializer.SMELTING_RECIPE, builder, SmeltingRecipe::new, 1);
                return create(RecipeSerializer.BLASTING_RECIPE, builder, BlastingRecipe::new, .5f);
            }

            private <T extends AbstractCookingRecipe> BaseRecipeProvider.GeneratedRecipe create(RecipeSerializer<T> serializer,
                                                                                                UnaryOperator<SimpleCookingRecipeBuilder> builder, AbstractCookingRecipe.Factory<T> factory, float cookingTimeModifier) {
                return register(recipeOutput -> {

                    SimpleCookingRecipeBuilder b = builder.apply(SimpleCookingRecipeBuilder.generic(ingredient.get(),
                            RecipeCategory.MISC, result.get(), exp,
                            (int) (cookingTime * cookingTimeModifier), serializer, factory));
                    if (unlockedBy != null)
                        b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));

                    RecipeOutput conditionalOutput = recipeOutput.withConditions(recipeConditions.toArray(new ICondition[0]));

                    b.save(
                            conditionalOutput,
                            createSimpleLocation(RegisteredObjectsHelper.getKeyOrThrow(serializer).getPath())
                    );
                });
            }
        }
    }


    public EncasedRecipeGens(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, CreateCasing.MODID);
        enterFolder("crafting");
        createForSetElement("adjustable_chain_drive",CasingSet::doesGenerateChainGearshift,CasingSet::getChainGearshift,(builder,set)->builder.viaShapeless(sh->sh.requires(set.getChainDrive()).requires(AllItems.ELECTRON_TUBE)));
        createForSetElement("chain_conveyor",CasingSet::doesGenerateChainConveyor,CasingSet::getChainConveyor,(builder,set)->builder.viaShaped(sh->
                sh.pattern(" C ")
                        .pattern("CAC")
                        .pattern(" C ")
                        .define('A', AllBlocks.LARGE_COGWHEEL)
                        .define('C',set.getCasing())));

        createForSetElement("chain_drive",CasingSet::doesGenerateChainDrive,CasingSet::getChainDrive,(builder,set)->builder.viaShapeless(sh->
                sh.requires(set.getCasing())
                        .requires(Tags.Items.NUGGETS_IRON)
                        .requires(Tags.Items.NUGGETS_IRON)
                        .requires(Tags.Items.NUGGETS_IRON)
        ));

        createForSetElement("clutch",CasingSet::doesGenerateClutch,CasingSet::getClutch,(builder,set)->builder.viaShapeless(sh->
                sh.requires(set.getCasing())
                        .requires(AllBlocks.SHAFT)
                        .requires(Tags.Items.DUSTS_REDSTONE)
        ));

        createForSetElement("auto_clutch",CasingSet::doesGenerateAutoClutch,CasingSet::getAutoClutch,(builder,set)->builder.viaShapeless(sh->
                sh.requires(set.getCasing())
                        .requires(AllBlocks.SHAFT)
                        .requires(Items.COMPASS)
        ));


        createForSetElement("configurable_gearbox",CasingSet::doesGenerateConfigurableGearbox,CasingSet::getConfigurableGearbox,(builder,set)->builder.viaShaped(sh->
                sh.pattern(" C ")
                        .pattern("CAC")
                        .pattern(" C ")
                        .define('C', AllBlocks.LARGE_COGWHEEL)
                        .define('A',set.getCasing())));

        createForSetElement("deployer",CasingSet::doesGenerateDeployer,CasingSet::getDeployer,(builder,set)->builder.viaShaped(sh->
                sh.pattern("B")
                        .pattern("C")
                        .pattern("I")
                        .define('B', AllItems.ELECTRON_TUBE)
                        .define('C',set.getCasing())
                        .define('I',AllItems.BRASS_HAND)
        ));
        createForSetElement("depot",CasingSet::doesGenerateDepot,CasingSet::getDepot,(builder,set)->builder.viaShapeless(sh->
                sh.requires(AllItems.ANDESITE_ALLOY)
                        .requires(set.getCasing())
        ));
        createForSetElement("encased_fan",CasingSet::doesGenerateEncasedFan,CasingSet::getEncasedFan,(builder,set)->builder.viaShaped(sh->
                sh.pattern("B")
                        .pattern("C")
                        .pattern("I")
                        .define('B', AllBlocks.SHAFT)
                        .define('C',set.getCasing())
                        .define('I',AllItems.PROPELLER)
        ));

        createForSetElement("gearbox",CasingSet::doesGenerateGearbox,CasingSet::getGearbox,(builder,set)->builder.viaShaped(sh->
                sh.pattern(" B ")
                        .pattern("BCB")
                        .pattern(" B ")
                        .define('B', AllBlocks.COGWHEEL)
                        .define('C',set.getCasing())
        ));

        createForSetElement("gearbox",CasingSet::doesGenerateGearbox,CasingSet::getGearbox,(builder,set)->builder.suffix("_from_conversion").viaShapeless(sh->
                sh.requires(set.getVerticalGearboxItem())
        ));

        createForSetElement("gearbox",CasingSet::doesGenerateGearbox,CasingSet::getVerticalGearboxItem,(builder,set)->builder.suffix("_vertical").viaShaped(sh->
                sh.pattern("B B")
                        .pattern(" C ")
                        .pattern("B B")
                        .define('B', AllBlocks.COGWHEEL)
                        .define('C',set.getCasing())
        ));

        createForSetElement("gearbox",CasingSet::doesGenerateGearbox,CasingSet::getVerticalGearboxItem,(builder,set)->builder.suffix("_vertical_from_conversion").viaShapeless(sh->
                sh.requires(set.getGearbox())
        ));
        createForSetElement("gearshift",CasingSet::doesGenerateGearshift,CasingSet::getGearshift,(builder,set)->builder.viaShapeless(sh->
                sh.requires(set.getCasing())
                        .requires(AllBlocks.COGWHEEL)
                        .requires(Tags.Items.DUSTS_REDSTONE)
        ));

        createForSetElement("mixer",CasingSet::doesGenerateMixer,CasingSet::getMixer,(builder,set)->builder.viaShaped(sh->
                sh.pattern("B")
                        .pattern("C")
                        .pattern("I")
                        .define('B', AllBlocks.COGWHEEL)
                        .define('C',set.getCasing())
                        .define('I',AllItems.WHISK)
        ));

        createForSetElement("portable_storage_interface",CasingSet::doesGenerateStorageInterface,CasingSet::getStorageInterface,(builder,set)->builder.viaShapeless(sh->
                sh.requires(set.getCasing())
                        .requires(AllBlocks.CHUTE)
        ));

        createForSetElement("press",CasingSet::doesGeneratePress,CasingSet::getPress,(builder,set)->builder.viaShaped(sh->
                sh.pattern("B")
                        .pattern("C")
                        .pattern("I")
                        .define('B', AllBlocks.SHAFT)
                        .define('C',set.getCasing())
                        .define('I',Tags.Items.STORAGE_BLOCKS_IRON)
        ));
        createForSetElement("roller",CasingSet::doesGenerateRoller,CasingSet::getRoller,(builder,set)->builder.viaShaped(sh->
                sh.pattern("B")
                        .pattern("C")
                        .pattern("I")
                        .define('B', AllItems.ELECTRON_TUBE)
                        .define('C',set.getCasing())
                        .define('I',AllBlocks.CRUSHING_WHEEL)
        ));

        createForSetElement("saw",CasingSet::doesGenerateSaw,CasingSet::getSaw,(builder,set)->builder.viaShaped(sh->
                sh.pattern(" B ")
                        .pattern("BIB")
                        .pattern(" C ")
                        .define('B', CommonMetal.IRON.plates)
                        .define('C',set.getCasing())
                        .define('I',Tags.Items.INGOTS_IRON)
        ));

        createForSetElement("plough",CasingSet::doesGeneratePlough,CasingSet::getPlough,(builder,set)->builder.viaShaped(sh->
                sh.pattern("BBB")
                        .pattern("III")
                        .pattern(" C ")
                        .define('B', CommonMetal.IRON.plates)
                        .define('C',set.getCasing())
                        .define('I',AllItems.ANDESITE_ALLOY)
        ));

        createForSetElement("harvester",CasingSet::doesGenerateHarvester,CasingSet::getHarvester,(builder,set)->builder.viaShaped(sh->
                sh.pattern("IBI")
                        .pattern("IBI")
                        .pattern(" C ")
                        .define('B', CommonMetal.IRON.plates)
                        .define('C',set.getCasing())
                        .define('I',AllItems.ANDESITE_ALLOY)
        ));


        createForSetElement("drill",CasingSet::doesGenerateDrill,CasingSet::getDrill,(builder,set)->builder.viaShaped(sh->
                sh.pattern(" I ")
                        .pattern("IBI")
                        .pattern(" C ")
                        .define('B', Tags.Items.INGOTS_IRON)
                        .define('C',set.getCasing())
                        .define('I',AllItems.ANDESITE_ALLOY)
        ));

        createForSetElement("slicer",CasingSet::doesGenerateSlicer,CasingSet::getSlicer,(builder,set)-> builder.withCondition(new ModLoadedCondition("sliceanddice")).viaShaped(sh->
                sh.pattern("A")
                        .pattern("B")
                        .pattern("C")
                        .define('A',AllBlocks.COGWHEEL)
                        .define('B',set.getCasing())
                        .define('C',AllBlocks.TURNTABLE)
                ));



    }

    private void createForSetElement(String folderName,Predicate<CasingSet> exists, Function<CasingSet,? extends ItemLike> block, BiFunction<GeneratedRecipeBuilder,CasingSet,GeneratedRecipe> recipeGenerator){
        enterFolder(folderName);
        CasingSets.getSets().stream().filter(exists).forEach(set->{
            recipeGenerator.apply(create(set.getName(),()->block.apply(set)).unlockedBy(set::getCasing),set);
        });
        leftLastFolder();
    }
}
