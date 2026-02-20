package com.cosmocat.motleyfins.event;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import com.cosmocat.motleyfins.data.MotleyFinsDataComponents;
import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.entity.Parrotfish;
import com.cosmocat.motleyfins.item.MotleyFinsItems;
import com.cosmocat.motleyfins.sound.MotleyFinsSoundEvents;
import net.minecraft.core.registries.Registries;
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
        event.register(Registries.BLOCK, register -> MotleyFinsBlocks.REGISTRIES.forEach(register::register));
        event.register(Registries.ITEM, register -> MotleyFinsItems.REGISTRIES.forEach(register::register));
        event.register(Registries.ENTITY_TYPE, register -> MotleyFinsEntities.REGISTRIES.forEach(register::register));
        event.register(Registries.DATA_COMPONENT_TYPE, register -> MotleyFinsDataComponents.REGISTRIES.forEach(register::register));
        event.register(Registries.SOUND_EVENT, register -> MotleyFinsSoundEvents.REGISTRIES.forEach(register::register));
    }
}
