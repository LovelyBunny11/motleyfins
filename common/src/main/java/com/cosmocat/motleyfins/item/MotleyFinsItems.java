package com.cosmocat.motleyfins.item;

import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.registry.MotleyFinsRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Supplier;

public class MotleyFinsItems {
    public static final Supplier<Item> PARROTFISH_BUCKET = MotleyFinsRegistries.registerItem("parrotfish_bucket",
            (properties) -> new MotleyFinsMobBucketItem(MotleyFinsEntities.PARROTFISH.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, properties), () -> new Item.Properties().stacksTo(1));

    public static final Supplier<Item> PARROTFISH_SPAWN_EGG = MotleyFinsRegistries.registerItem("parrotfish_spawn_egg",
            SpawnEggItem::new, () -> new Item.Properties().spawnEgg(MotleyFinsEntities.PARROTFISH.get()));

    public static void init() {}
}
