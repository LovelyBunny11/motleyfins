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
        valueLookupBuilder(BlockTags.SAND)
                .addOptional(MotleyFinsBlocks.WHITE_SAND.get());
        valueLookupBuilder(BlockTags.STAIRS)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS.get())
                .addOptional(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.get());
        valueLookupBuilder(BlockTags.SLABS)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_SLAB.get())
                .addOptional(MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB.get())
                .addOptional(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB.get());
        valueLookupBuilder(BlockTags.WALLS)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_WALL.get());
    }
}