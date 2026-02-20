package com.cosmocat.motleyfins.datagen.data;

import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class MotleyFinsBlockLootTableProvider extends FabricBlockLootTableProvider {
    public MotleyFinsBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.dropSelf(MotleyFinsBlocks.WHITE_SAND);

        this.dropSelf(MotleyFinsBlocks.WHITE_SANDSTONE);
        this.dropSelf(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS);
        this.dropSelf(MotleyFinsBlocks.WHITE_SANDSTONE_SLAB);
        this.dropSelf(MotleyFinsBlocks.WHITE_SANDSTONE_WALL);
        this.dropSelf(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE);

        this.dropSelf(MotleyFinsBlocks.CUT_WHITE_SANDSTONE);
        this.dropSelf(MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB);

        this.dropSelf(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE);
        this.dropSelf(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS);
        this.dropSelf(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB);
    }
}