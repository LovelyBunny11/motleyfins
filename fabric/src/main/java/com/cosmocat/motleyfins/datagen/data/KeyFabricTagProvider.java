package com.cosmocat.motleyfins.datagen.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public abstract class KeyFabricTagProvider<T> extends FabricTagProvider<@NotNull T> {

    public KeyFabricTagProvider(FabricDataOutput output, ResourceKey<? extends @NotNull Registry<@NotNull T>> registryKey, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registryKey, registriesFuture);
    }

    public abstract static class FabricValueLookupTagProvider<T> extends FabricTagProvider<T> {
        private final Function<T, ResourceKey<T>> valueToKey;

        protected FabricValueLookupTagProvider(FabricDataOutput output, ResourceKey<? extends Registry<T>> registryKey, CompletableFuture<HolderLookup.Provider> registriesFuture, Function<T, ResourceKey<T>> valueToKey) {
            super(output, registryKey, registriesFuture);
            this.valueToKey = valueToKey;
        }

        protected TagAppender<@NotNull ResourceKey<@NotNull T>, T> valueLookupBuilder(TagKey<T> tag) {
            TagBuilder tagBuilder = this.getOrCreateRawBuilder(tag);
            return TagAppender.forBuilder(tagBuilder);
        }
    }
}
