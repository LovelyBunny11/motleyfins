package com.cosmocat.motleyfins.datagen.data;

import com.cosmocat.motleyfins.block.MotleyFinsBlockFamilies;
import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class MotleyFinsRecipeProvider extends FabricRecipeProvider {
    public MotleyFinsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {
        MotleyFinsBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach((family) ->
                generateRecipes(recipeOutput, family));
        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.WHITE_SANDSTONE, MotleyFinsBlocks.WHITE_SAND);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.WHITE_SANDSTONE_SLAB, Ingredient.of(MotleyFinsBlocks.WHITE_SANDSTONE, MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE)).unlockedBy("has_white_sandstone", has(MotleyFinsBlocks.WHITE_SANDSTONE)).unlockedBy("has_chiseled_white_sandstone", has(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE)).save(recipeOutput);
        stairBuilder(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS, Ingredient.of(MotleyFinsBlocks.WHITE_SANDSTONE, MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE, MotleyFinsBlocks.CUT_WHITE_SANDSTONE)).unlockedBy("has_white_sandstone", has(MotleyFinsBlocks.WHITE_SANDSTONE)).unlockedBy("has_chiseled_white_sandstone", has(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE)).unlockedBy("has_cut_white_sandstone", has(MotleyFinsBlocks.CUT_WHITE_SANDSTONE)).save(recipeOutput);
        cut(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.CUT_WHITE_SANDSTONE, MotleyFinsBlocks.WHITE_SANDSTONE);
        wall(recipeOutput, RecipeCategory.DECORATIONS, MotleyFinsBlocks.WHITE_SANDSTONE_WALL, MotleyFinsBlocks.WHITE_SANDSTONE);
        chiseled(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE, MotleyFinsBlocks.WHITE_SANDSTONE_SLAB);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(MotleyFinsBlocks.WHITE_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE.asItem(), 0.1F, 200).unlockedBy("has_white_sandstone", has(MotleyFinsBlocks.WHITE_SANDSTONE)).save(recipeOutput);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.CUT_WHITE_SANDSTONE, MotleyFinsBlocks.WHITE_SANDSTONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.WHITE_SANDSTONE_SLAB, MotleyFinsBlocks.WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB, MotleyFinsBlocks.WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB, MotleyFinsBlocks.CUT_WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS, MotleyFinsBlocks.WHITE_SANDSTONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, MotleyFinsBlocks.WHITE_SANDSTONE_WALL, MotleyFinsBlocks.WHITE_SANDSTONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE);
    }

    public static void generateRecipes(RecipeOutput recipeOutput, BlockFamily blockFamily) {
        blockFamily.getVariants().forEach((variant, block) -> {
            BiFunction<ItemLike, ItemLike, RecipeBuilder> biFunction = SHAPE_BUILDERS.get(variant);
            ItemLike itemLike = getBaseBlock(blockFamily, variant);
            if (biFunction != null) {
                RecipeBuilder recipeBuilder = biFunction.apply(block, itemLike);
                blockFamily.getRecipeGroupPrefix().ifPresent((string) -> recipeBuilder.group(string + (variant == BlockFamily.Variant.CUT ? "" : "_" + variant.getRecipeGroup())));
                recipeBuilder.unlockedBy(blockFamily.getRecipeUnlockedBy().orElseGet(() -> getHasName(itemLike)), has(itemLike));
                recipeBuilder.save(recipeOutput);
            }

            if (variant == BlockFamily.Variant.CRACKED) {
                smeltingResultFromBase(recipeOutput, block, itemLike);
            }
        });
    }


    @Override
    public @NotNull String getName() {
        return "MotleyFinsRecipeProvider";
    }
}