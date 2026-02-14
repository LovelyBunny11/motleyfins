package com.cosmocat.motleyfins.datagen.data;

import com.cosmocat.motleyfins.data.MotleyFinsTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

public class MotleyFinsBiomeTagProvider extends FabricTagProvider.FabricValueLookupTagProvider<Biome> {
    public MotleyFinsBiomeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture, biome -> ResourceKey.create(Registries.BIOME, ));
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(MotleyFinsTags.Biomes.SPAWNS_PARROTFISH)
    }
}