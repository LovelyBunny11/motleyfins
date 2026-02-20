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
        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
                .addOptional(MotleyFinsBlocks.WHITE_SAND);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_SLAB)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_WALL)
                .addOptional(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE)
                .addOptional(MotleyFinsBlocks.CUT_WHITE_SANDSTONE)
                .addOptional(MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB)
                .addOptional(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE)
                .addOptional(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS)
                .addOptional(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB);
        valueLookupBuilder(BlockTags.SAND)
                .addOptional(MotleyFinsBlocks.WHITE_SAND);
        valueLookupBuilder(BlockTags.STAIRS)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS)
                .addOptional(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS);
        valueLookupBuilder(BlockTags.SLABS)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_SLAB)
                .addOptional(MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB)
                .addOptional(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB);
        valueLookupBuilder(BlockTags.WALLS)
                .addOptional(MotleyFinsBlocks.WHITE_SANDSTONE_WALL);
    }
}