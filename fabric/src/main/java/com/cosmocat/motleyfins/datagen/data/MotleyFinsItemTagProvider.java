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
        getOrCreateTagBuilder(ItemTags.SAND)
                .add(MotleyFinsBlocks.WHITE_SAND.asItem());
        getOrCreateTagBuilder(ItemTags.STAIRS)
                .add(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS.asItem())
                .add(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.SLABS)
                .add(MotleyFinsBlocks.WHITE_SANDSTONE_SLAB.asItem())
                .add(MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB.asItem())
                .add(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.WALLS)
                .add(MotleyFinsBlocks.WHITE_SANDSTONE_WALL.asItem());
        getOrCreateTagBuilder(MotleyFinsTags.Items.PARROTFISH_FOOD)
                .add(Items.TUBE_CORAL_BLOCK)
                .add(Items.TUBE_CORAL)
                .add(Items.TUBE_CORAL_FAN)
                .add(Items.BRAIN_CORAL_BLOCK)
                .add(Items.BRAIN_CORAL)
                .add(Items.BRAIN_CORAL_FAN)
                .add(Items.BUBBLE_CORAL_BLOCK)
                .add(Items.BUBBLE_CORAL)
                .add(Items.BUBBLE_CORAL_FAN)
                .add(Items.FIRE_CORAL_BLOCK)
                .add(Items.FIRE_CORAL)
                .add(Items.FIRE_CORAL_FAN)
                .add(Items.HORN_CORAL_BLOCK)
                .add(Items.HORN_CORAL)
                .add(Items.HORN_CORAL_FAN)
                .add(Items.DEAD_TUBE_CORAL_BLOCK)
                .add(Items.DEAD_TUBE_CORAL)
                .add(Items.DEAD_TUBE_CORAL_FAN)
                .add(Items.DEAD_BRAIN_CORAL_BLOCK)
                .add(Items.DEAD_BRAIN_CORAL)
                .add(Items.DEAD_BRAIN_CORAL_FAN)
                .add(Items.DEAD_BUBBLE_CORAL_BLOCK)
                .add(Items.DEAD_BUBBLE_CORAL)
                .add(Items.DEAD_BUBBLE_CORAL_FAN)
                .add(Items.DEAD_FIRE_CORAL_BLOCK)
                .add(Items.DEAD_FIRE_CORAL)
                .add(Items.DEAD_FIRE_CORAL_FAN)
                .add(Items.DEAD_HORN_CORAL_BLOCK)
                .add(Items.DEAD_HORN_CORAL)
                .add(Items.DEAD_HORN_CORAL_FAN);
    }
}