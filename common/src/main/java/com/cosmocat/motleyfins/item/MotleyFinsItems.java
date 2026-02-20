package com.cosmocat.motleyfins.item;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Function;

public class MotleyFinsItems {
    public static Map<ResourceKey<@NotNull Item>, Item> REGISTRIES;

    public static final Item PARROTFISH_BUCKET = registerItem("parrotfish_bucket",
            (properties) -> new MotleyFinsMobBucketItem(MotleyFinsEntities.PARROTFISH, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, properties), new Item.Properties().stacksTo(1));

    public static final Item PARROTFISH_SPAWN_EGG = registerItem("parrotfish_spawn_egg",
            SpawnEggItem::new, new Item.Properties().spawnEgg(MotleyFinsEntities.PARROTFISH));

    public static final Item PARROTFISH = registerItem("parrotfish",
            Item::new, new Item.Properties().food(Foods.PUFFERFISH, Consumables.PUFFERFISH));

    public static void init() {}

    public static Item registerItem(String name, Function<Item.Properties, ? extends @NotNull Item> func, Item.Properties props) {
        ResourceKey<@NotNull Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
        Item item = func.apply(props.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);

        REGISTRIES.put(key, item);
        return item;
    }
}
