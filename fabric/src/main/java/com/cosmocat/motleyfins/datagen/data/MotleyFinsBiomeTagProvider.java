package com.cosmocat.motleyfins.datagen.data;

import com.cosmocat.motleyfins.data.MotleyFinsTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class MotleyFinsBiomeTagProvider extends BiomeTagsProvider {

    public MotleyFinsBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(MotleyFinsTags.Biomes.SPAWNS_PARROTFISH).add(Biomes.WARM_OCEAN);
    }
}