package com.cosmocat.motleyfins;

import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import com.cosmocat.motleyfins.data.MotleyFinsTags;
import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.entity.Parrotfish;
import com.cosmocat.motleyfins.item.MotleyFinsItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.Heightmap;

public class MotleyFinsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        MotleyFins.init();
        FabricDefaultAttributeRegistry.register(MotleyFinsEntities.PARROTFISH, Parrotfish.createMobAttributes());
        BiomeModifications.addSpawn(BiomeSelectors.tag(MotleyFinsTags.Biomes.SPAWNS_PARROTFISH), MobCategory.WATER_AMBIENT, MotleyFinsEntities.PARROTFISH, 25, 3, 6);
        SpawnPlacements.register(MotleyFinsEntities.PARROTFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Parrotfish::checkParrotfishSpawnRules);
        addItemsToCreativeTabs();
    }

    private static void addItemsToCreativeTabs() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register((entries) -> {
            entries.addAfter(Items.CUT_RED_SANDSTONE_SLAB, MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB);
            entries.addAfter(Items.CUT_RED_SANDSTONE_SLAB, MotleyFinsBlocks.CUT_WHITE_SANDSTONE);

            entries.addAfter(Items.CUT_RED_SANDSTONE_SLAB, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB);
            entries.addAfter(Items.CUT_RED_SANDSTONE_SLAB, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS);
            entries.addAfter(Items.CUT_RED_SANDSTONE_SLAB, MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE);

            entries.addAfter(Items.CUT_RED_SANDSTONE_SLAB, MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE);
            entries.addAfter(Items.CUT_RED_SANDSTONE_SLAB, MotleyFinsBlocks.WHITE_SANDSTONE_WALL);
            entries.addAfter(Items.CUT_RED_SANDSTONE_SLAB, MotleyFinsBlocks.WHITE_SANDSTONE_SLAB);
            entries.addAfter(Items.CUT_RED_SANDSTONE_SLAB, MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS);
            entries.addAfter(Items.CUT_RED_SANDSTONE_SLAB, MotleyFinsBlocks.WHITE_SANDSTONE);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register((entries) -> {
            entries.addAfter(Items.RED_SANDSTONE, MotleyFinsBlocks.WHITE_SANDSTONE);
            entries.addAfter(Items.RED_SANDSTONE, MotleyFinsBlocks.WHITE_SAND);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((entries) -> entries.addAfter(Items.TROPICAL_FISH_BUCKET, MotleyFinsItems.PARROTFISH_BUCKET));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register((entries) -> entries.addAfter(Items.TROPICAL_FISH, MotleyFinsItems.PARROTFISH));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register((entries) -> entries.addAfter(Items.NAUTILUS_SPAWN_EGG, MotleyFinsItems.PARROTFISH_SPAWN_EGG));
    }
}