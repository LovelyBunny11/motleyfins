package com.cosmocat.motleyfins.block;

import com.cosmocat.motleyfins.MotleyFins;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class MotleyFinsBlocks {

    public static final Block WHITE_SAND = registerBlock("white_sand",
            new ColoredFallingBlock(new ColorRGBA(12893615), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND)));
    public static final Block WHITE_SANDSTONE = registerBlock("white_sandstone",
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
    public static final Block WHITE_SANDSTONE_STAIRS = registerBlock("white_sandstone_stairs",
            new StairBlock(MotleyFinsBlocks.WHITE_SANDSTONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE)));
    public static final Block WHITE_SANDSTONE_SLAB = registerBlock("white_sandstone_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE)));
    public static final Block WHITE_SANDSTONE_WALL = registerBlock("white_sandstone_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE)));
    public static final Block CHISELED_WHITE_SANDSTONE = registerBlock("chiseled_white_sandstone",
            new Block(BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE)));
    public static final Block SMOOTH_WHITE_SANDSTONE = registerBlock("smooth_white_sandstone",
            new Block(BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE)));
    public static final Block SMOOTH_WHITE_SANDSTONE_STAIRS = registerBlock("smooth_white_sandstone_stairs",
            new StairBlock(MotleyFinsBlocks.WHITE_SANDSTONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE)));
    public static final Block SMOOTH_WHITE_SANDSTONE_SLAB = registerBlock("smooth_white_sandstone_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE)));
    public static final Block CUT_WHITE_SANDSTONE = registerBlock("cut_white_sandstone",
            new Block(BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE)));
    public static final Block CUT_WHITE_SANDSTONE_SLAB = registerBlock("cut_white_sandstone_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.CUT_WHITE_SANDSTONE)));

    public static Block registerBlock(String name, Block block) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID, name);
        BlockItem blockItem = new BlockItem(block, new Item.Properties());
        Registry.register(BuiltInRegistries.ITEM, id, blockItem);


        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static void init() {}
}
