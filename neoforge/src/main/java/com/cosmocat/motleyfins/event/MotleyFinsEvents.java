package com.cosmocat.motleyfins.event;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import com.cosmocat.motleyfins.data.MotleyFinsDataComponents;
import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.entity.Parrotfish;
import com.cosmocat.motleyfins.item.MotleyFinsItems;
import com.cosmocat.motleyfins.sound.MotleyFinsSoundEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = MotleyFins.MOD_ID)
public class MotleyFinsEvents {

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(MotleyFinsEntities.PARROTFISH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Parrotfish::checkParrotfishSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        if (event.getRegistryKey().equals(BuiltInRegistries.ITEM)) {
            MotleyFinsItems.init();
        } else if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK)) {
            MotleyFinsBlocks.init();
        } else  if (event.getRegistryKey().equals(BuiltInRegistries.ENTITY_TYPE)) {
            MotleyFinsEntities.init();
        } else  if (event.getRegistryKey().equals(BuiltInRegistries.DATA_COMPONENT_TYPE)) {
            MotleyFinsDataComponents.init();
        } else  if (event.getRegistryKey().equals(BuiltInRegistries.SOUND_EVENT)) {
            MotleyFinsSoundEvents.init();
        }
    }
}
