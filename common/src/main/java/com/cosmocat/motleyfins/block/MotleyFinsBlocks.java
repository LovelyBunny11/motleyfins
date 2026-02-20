package com.cosmocat.motleyfins.block;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.item.MotleyFinsItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Function;

public class MotleyFinsBlocks {

    public static Map<ResourceKey<@NotNull Block>, Block> REGISTRIES;

    public static final Block WHITE_SAND = registerBlock("white_sand",
            (properties) -> new SandBlock(new ColorRGBA(12893615), properties), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND));
    public static final Block WHITE_SANDSTONE = registerBlock("white_sandstone",
            Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F));
    public static final Block WHITE_SANDSTONE_STAIRS = registerBlock("white_sandstone_stairs",
            (properties) -> new StairBlock(MotleyFinsBlocks.WHITE_SANDSTONE.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE));
    public static final Block WHITE_SANDSTONE_SLAB = registerBlock("white_sandstone_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE));
    public static final Block WHITE_SANDSTONE_WALL = registerBlock("white_sandstone_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE));
    public static final Block CHISELED_WHITE_SANDSTONE = registerBlock("chiseled_white_sandstone",
            Block::new, BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE));
    public static final Block SMOOTH_WHITE_SANDSTONE = registerBlock("smooth_white_sandstone",
            Block::new, BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE));
    public static final Block SMOOTH_WHITE_SANDSTONE_STAIRS = registerBlock("smooth_white_sandstone_stairs",
            (properties) -> new StairBlock(MotleyFinsBlocks.WHITE_SANDSTONE.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE));
    public static final Block SMOOTH_WHITE_SANDSTONE_SLAB = registerBlock("smooth_white_sandstone_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE));
    public static final Block CUT_WHITE_SANDSTONE = registerBlock("cut_white_sandstone",
            Block::new, BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE));
    public static final Block CUT_WHITE_SANDSTONE_SLAB = registerBlock("cut_white_sandstone_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.CUT_WHITE_SANDSTONE));

    public static Block registerBlock(String name, Function<BlockBehaviour.Properties, ? extends @NotNull Block> blockConstructor, BlockBehaviour.Properties properties) {
        ResourceKey<@NotNull Block> blockKey = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
        ResourceKey<@NotNull Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));

        Block block = blockConstructor.apply(properties.setId(blockKey));
        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());

        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        MotleyFinsItems.REGISTRIES.put(itemKey, blockItem);
        REGISTRIES.put(blockKey, block);
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    public static void init() {}
}
