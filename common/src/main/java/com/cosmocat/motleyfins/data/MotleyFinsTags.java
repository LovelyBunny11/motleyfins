package com.cosmocat.motleyfins.data;

import com.cosmocat.motleyfins.MotleyFins;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class MotleyFinsTags {

    public static void init() {}

    private static TagKey<Block> blockTag(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
    }

    private static TagKey<Item> itemTag(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
    }

    private static TagKey<Biome> biomeTag(String name) {
        return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
    }

    public class Items {
        public static final TagKey<Item> PARROTFISH_FOOD = itemTag("parrotfish_food");
    }

    public class Biomes {
        public static final TagKey<Biome> SPAWNS_PARROTFISH = biomeTag("spawns_parrotfish");
    }
}
