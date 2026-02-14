package com.cosmocat.motleyfins;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(MotleyFins.MOD_ID)
public class MotleyFinsNeoforge {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MotleyFins.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MotleyFins.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MotleyFins.MOD_ID);
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MotleyFins.MOD_ID);

    public MotleyFinsNeoforge(IEventBus eventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        MotleyFins.init();
    }
}