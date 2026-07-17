package com.cosmocat.motleyfins.datagen.data;

import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class MotleyFinsBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public MotleyFinsBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(MotleyFinsBlocks.WHITE_SAND);
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(MotleyFinsBlocks.WHITE_SANDSTONE)
                .add(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS)
                .add(MotleyFinsBlocks.WHITE_SANDSTONE_SLAB)
                .add(MotleyFinsBlocks.WHITE_SANDSTONE_WALL)
                .add(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE)
                .add(MotleyFinsBlocks.CUT_WHITE_SANDSTONE)
                .add(MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB)
                .add(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE)
                .add(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS)
                .add(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB);
        getOrCreateTagBuilder(BlockTags.SAND)
                .add(MotleyFinsBlocks.WHITE_SAND);
        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS)
                .add(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS);
        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(MotleyFinsBlocks.WHITE_SANDSTONE_SLAB)
                .add(MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB)
                .add(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB);
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(MotleyFinsBlocks.WHITE_SANDSTONE_WALL);
    }
}