package com.cosmocat.motleyfins.datagen.data;

import com.cosmocat.motleyfins.block.MotleyFinsBlockFamilies;
import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class MotleyFinsRecipeProvider  extends FabricRecipeProvider {
    public MotleyFinsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                MotleyFinsBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach((family) ->
                        this.generateRecipes(family, FeatureFlagSet.of()));
                this.twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.WHITE_SANDSTONE, MotleyFinsBlocks.WHITE_SAND);
                this.slabBuilder(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.WHITE_SANDSTONE_SLAB, Ingredient.of(MotleyFinsBlocks.WHITE_SANDSTONE, MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE)).unlockedBy("has_white_sandstone", this.has(MotleyFinsBlocks.WHITE_SANDSTONE)).unlockedBy("has_chiseled_white_sandstone", this.has(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE)).save(this.output);
                this.stairBuilder(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS, Ingredient.of(MotleyFinsBlocks.WHITE_SANDSTONE, MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE, MotleyFinsBlocks.CUT_WHITE_SANDSTONE)).unlockedBy("has_white_sandstone", this.has(MotleyFinsBlocks.WHITE_SANDSTONE)).unlockedBy("has_chiseled_white_sandstone", this.has(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE)).unlockedBy("has_cut_white_sandstone", this.has(MotleyFinsBlocks.CUT_WHITE_SANDSTONE)).save(this.output);
                this.cut(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.CUT_WHITE_SANDSTONE, MotleyFinsBlocks.WHITE_SANDSTONE);
                this.wall(RecipeCategory.DECORATIONS, MotleyFinsBlocks.WHITE_SANDSTONE_WALL, MotleyFinsBlocks.WHITE_SANDSTONE);
                this.chiseled(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE, MotleyFinsBlocks.WHITE_SANDSTONE_SLAB);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(MotleyFinsBlocks.WHITE_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE.asItem(), 0.1F, 200).unlockedBy("has_white_sandstone", this.has(MotleyFinsBlocks.WHITE_SANDSTONE)).save(this.output);

                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.CUT_WHITE_SANDSTONE, MotleyFinsBlocks.WHITE_SANDSTONE);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.WHITE_SANDSTONE_SLAB, MotleyFinsBlocks.WHITE_SANDSTONE, 2);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB, MotleyFinsBlocks.WHITE_SANDSTONE, 2);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB, MotleyFinsBlocks.CUT_WHITE_SANDSTONE, 2);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS, MotleyFinsBlocks.WHITE_SANDSTONE);
                this.stonecutterResultFromBase(RecipeCategory.DECORATIONS, MotleyFinsBlocks.WHITE_SANDSTONE_WALL, MotleyFinsBlocks.WHITE_SANDSTONE);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE, 2);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE);
            }
        };
    }

    @Override
    public String getName() {
        return "MotleyFinsRecipeProvider";
    }
}