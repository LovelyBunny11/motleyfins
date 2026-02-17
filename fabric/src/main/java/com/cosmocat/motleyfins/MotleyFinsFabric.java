package com.cosmocat.motleyfins;

import com.cosmocat.motleyfins.data.MotleyFinsTags;
import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.entity.Parrotfish;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.MobCategory;

public class MotleyFinsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        MotleyFins.init();
        FabricDefaultAttributeRegistry.register(MotleyFinsEntities.PARROTFISH.get(), Parrotfish.createMobAttributes());

        BiomeModifications.addSpawn(BiomeSelectors.tag(MotleyFinsTags.Biomes.SPAWNS_PARROTFISH), MobCategory.WATER_AMBIENT, MotleyFinsEntities.PARROTFISH.get(), 25, 3, 6);
    }
}