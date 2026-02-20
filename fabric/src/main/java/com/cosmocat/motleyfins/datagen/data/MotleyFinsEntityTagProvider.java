package com.cosmocat.motleyfins.datagen.data;

import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class MotleyFinsEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public MotleyFinsEntityTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider wrapperLookup) {
        valueLookupBuilder(EntityTypeTags.AQUATIC).add(MotleyFinsEntities.PARROTFISH);
        valueLookupBuilder(EntityTypeTags.AXOLOTL_HUNT_TARGETS).add(MotleyFinsEntities.PARROTFISH);
        valueLookupBuilder(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH).add(MotleyFinsEntities.PARROTFISH);
    }
}