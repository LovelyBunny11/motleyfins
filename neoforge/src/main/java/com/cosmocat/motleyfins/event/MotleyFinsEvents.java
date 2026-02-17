package com.cosmocat.motleyfins.event;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.entity.Parrotfish;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = MotleyFins.MOD_ID)
public class MotleyFinsEvents {

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(MotleyFinsEntities.PARROTFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Parrotfish::checkParrotfishSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
    }

}
