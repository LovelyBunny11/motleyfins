package com.cosmocat.motleyfins.item;

import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.registry.MotleyFinsRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;

import java.util.Objects;
import java.util.function.Supplier;

public class MotleyFinsItems {
    public static final Supplier<Item> WHITE_SAND = registerBlock(MotleyFinsBlocks.WHITE_SAND);

    public static final Supplier<Item> PARROTFISH_BUCKET = MotleyFinsRegistries.registerItem("parrotfish_bucket",
            (properties) -> new MobBucketItem(MotleyFinsEntities.PARROTFISH.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, properties), () -> new Item.Properties().stacksTo(1));

    public static final Supplier<Item> PARROTFISH_SPAWN_EGG = MotleyFinsRegistries.registerItem("parrotfish_spawn_egg",
            SpawnEggItem::new, () -> new Item.Properties().spawnEgg(MotleyFinsEntities.PARROTFISH.get()));

    public static Supplier<Item> registerBlock(Supplier<Block> block, Supplier<Item.Properties> props) {
        String name = block.get().builtInRegistryHolder().unwrapKey().orElseThrow().identifier().getPath();
        Objects.requireNonNull(block);
        return MotleyFinsRegistries.registerItem(name, (properties) -> new BlockItem(block.get(), properties.setId(ResourceKey.create(Registries.ITEM,
                block.get().builtInRegistryHolder().key().identifier())).useBlockDescriptionPrefix()), props);
    }

    public static Supplier<Item> registerBlock(Supplier<Block> block) {
        Objects.requireNonNull(block);
        return registerBlock(block, Item.Properties::new);
    }

    public static void init() {}
}
