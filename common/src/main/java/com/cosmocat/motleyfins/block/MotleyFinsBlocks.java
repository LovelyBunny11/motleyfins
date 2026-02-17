package com.cosmocat.motleyfins.block;

import com.cosmocat.motleyfins.registry.MotleyFinsRegistries;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

public class MotleyFinsBlocks {
    public static final Supplier<Block> WHITE_SAND = registerBlock("white_sand",
            (properties) -> new SandBlock(new ColorRGBA(12893615), properties),() -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND));
    public static final Supplier<Block> WHITE_SANDSTONE = registerBlock("white_sandstone",
            Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F));
    public static final Supplier<Block> WHITE_SANDSTONE_STAIRS = registerBlock("white_sandstone_stairs",
            (properties) -> new StairBlock(MotleyFinsBlocks.WHITE_SANDSTONE.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE.get()));
    public static final Supplier<Block> WHITE_SANDSTONE_SLAB = registerBlock("white_sandstone_slab",
            SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE.get()));
    public static final Supplier<Block> WHITE_SANDSTONE_WALL = registerBlock("white_sandstone_wall",
            WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE.get()));
    public static final Supplier<Block> CHISELED_WHITE_SANDSTONE = registerBlock("chiseled_white_sandstone",
            Block::new, () -> BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE.get()));
    public static final Supplier<Block> SMOOTH_WHITE_SANDSTONE = registerBlock("smooth_white_sandstone",
            Block::new, () -> BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE.get()));
    public static final Supplier<Block> SMOOTH_WHITE_SANDSTONE_STAIRS = registerBlock("smooth_white_sandstone_stairs",
            (properties) -> new StairBlock(MotleyFinsBlocks.WHITE_SANDSTONE.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE.get()));
    public static final Supplier<Block> SMOOTH_WHITE_SANDSTONE_SLAB = registerBlock("smooth_white_sandstone_slab",
            SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE.get()));
    public static final Supplier<Block> CUT_WHITE_SANDSTONE = registerBlock("cut_white_sandstone",
            Block::new, () -> BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.WHITE_SANDSTONE.get()));
    public static final Supplier<Block> CUT_WHITE_SANDSTONE_SLAB = registerBlock("cut_white_sandstone_slab",
            SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(MotleyFinsBlocks.CUT_WHITE_SANDSTONE.get()));

    public static <T extends Block> Supplier<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends @NotNull T> blockConstructor, Supplier<Block.Properties> propertiesSupplier) {
        Supplier<T> block = MotleyFinsRegistries.registerBlock(name, blockConstructor, propertiesSupplier);
        MotleyFinsRegistries.registerItem(name, (properties) -> new BlockItem(block.get(), properties), Item.Properties::new);
        return block;
    }

    public static void init() {}
}
