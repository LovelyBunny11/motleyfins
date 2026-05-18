package com.cosmocat.motleyfins.event;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import com.cosmocat.motleyfins.data.MotleyFinsDataComponents;
import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.entity.Parrotfish;
import com.cosmocat.motleyfins.item.MotleyFinsItems;
import com.cosmocat.motleyfins.particle.MotleyFinsParticles;
import com.cosmocat.motleyfins.sound.MotleyFinsSoundEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = MotleyFins.MOD_ID)
public class MotleyFinsEvents {

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.ITEM)) {
            MotleyFinsItems.init();
        } else if (event.getRegistryKey().equals(Registries.BLOCK)) {
            MotleyFinsBlocks.init();
        } else  if (event.getRegistryKey().equals(Registries.ENTITY_TYPE)) {
            MotleyFinsEntities.init();
        } else  if (event.getRegistryKey().equals(Registries.DATA_COMPONENT_TYPE)) {
            MotleyFinsDataComponents.init();
        } else  if (event.getRegistryKey().equals(Registries.SOUND_EVENT)) {
            MotleyFinsSoundEvents.init();
        } else  if (event.getRegistryKey().equals(Registries.PARTICLE_TYPE)) {
            MotleyFinsParticles.init();
        }
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(MotleyFinsEntities.PARROTFISH, Parrotfish.createMobAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(MotleyFinsEntities.PARROTFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Parrotfish::checkParrotfishSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), MotleyFinsBlocks.CUT_WHITE_SANDSTONE.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), MotleyFinsBlocks.WHITE_SANDSTONE_WALL.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), MotleyFinsBlocks.WHITE_SANDSTONE_SLAB.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), MotleyFinsBlocks.WHITE_SANDSTONE.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        } if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.insertAfter(Items.RED_SANDSTONE.getDefaultInstance(), MotleyFinsBlocks.WHITE_SANDSTONE.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.RED_SANDSTONE.getDefaultInstance(), MotleyFinsBlocks.WHITE_SAND.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        } if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.insertAfter(Items.TROPICAL_FISH_BUCKET.getDefaultInstance(), MotleyFinsItems.PARROTFISH_BUCKET.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        } if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.insertAfter(Items.TROPICAL_FISH.getDefaultInstance(), MotleyFinsItems.PARROTFISH.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        } if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.insertAfter(Items.NAUTILUS_SPAWN_EGG.getDefaultInstance(), MotleyFinsItems.PARROTFISH_SPAWN_EGG.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
