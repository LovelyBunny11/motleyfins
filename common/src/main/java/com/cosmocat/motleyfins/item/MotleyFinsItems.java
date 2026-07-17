package com.cosmocat.motleyfins.item;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.material.Fluids;

public class MotleyFinsItems {
    public static final Item PARROTFISH_BUCKET = registerItem("parrotfish_bucket",
            new MotleyFinsMobBucketItem(MotleyFinsEntities.PARROTFISH, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1)));

    public static final Item PARROTFISH_SPAWN_EGG = registerItem("parrotfish_spawn_egg",
            new SpawnEggItem(MotleyFinsEntities.PARROTFISH, 10422810, 7710463, new Item.Properties()));

    public static final Item PARROTFISH = registerItem("parrotfish",
            new Item(new Item.Properties().food(Foods.PUFFERFISH)));

    public static void init() {}

    public static Item registerItem(String name, Item item) {
        ResourceLocation itemID = ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID, name);

        return Registry.register(BuiltInRegistries.ITEM, itemID, item);
    }
}
