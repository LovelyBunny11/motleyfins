package com.cosmocat.motleyfins.datagen.data;

import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import com.cosmocat.motleyfins.data.MotleyFinsTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class MotleyFinsItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public MotleyFinsItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider wrapperLookup) {
        valueLookupBuilder(ItemTags.SAND)
                .addOptional(MotleyFinsBlocks.WHITE_SAND.get().asItem());
        valueLookupBuilder(ItemTags.STAIRS)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS.get().asItem())
                .addOptional(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.get().asItem());
        valueLookupBuilder(ItemTags.SLABS)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_SLAB.get().asItem())
                .addOptional(MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB.get().asItem())
                .addOptional(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB.get().asItem());
        valueLookupBuilder(ItemTags.WALLS)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_WALL.get().asItem());
        valueLookupBuilder(MotleyFinsTags.Items.PARROTFISH_FOOD)
                .add(Items.TUBE_CORAL_BLOCK)
                .add(Items.BRAIN_CORAL_BLOCK)
                .add(Items.BUBBLE_CORAL_BLOCK)
                .add(Items.FIRE_CORAL_BLOCK)
                .add(Items.HORN_CORAL_BLOCK)
                .add(Items.DEAD_TUBE_CORAL_BLOCK)
                .add(Items.DEAD_BRAIN_CORAL_BLOCK)
                .add(Items.DEAD_BUBBLE_CORAL_BLOCK)
                .add(Items.DEAD_FIRE_CORAL_BLOCK)
                .add(Items.DEAD_HORN_CORAL_BLOCK);
    }
}