package com.cosmocat.motleyfins.block;

import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

public class MotleyFinsBlockFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    public static final BlockFamily WHITE_SANDSTONE =
            familyBuilder(MotleyFinsBlocks.WHITE_SANDSTONE.get())
            .wall(MotleyFinsBlocks.WHITE_SANDSTONE_WALL.get())
            .stairs(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS.get())
            .slab(MotleyFinsBlocks.WHITE_SANDSTONE_SLAB.get())
            .chiseled(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE.get())
            .cut(MotleyFinsBlocks.CUT_WHITE_SANDSTONE.get())
            .dontGenerateRecipe().getFamily();

    public static final BlockFamily CUT_WHITE_SANDSTONE =
            familyBuilder(MotleyFinsBlocks.CUT_WHITE_SANDSTONE.get())
                    .slab(MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB.get()).getFamily();

    public static final BlockFamily SMOOTH_WHITE_SANDSTONE =
            familyBuilder(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE.get())
                    .stairs(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.get())
                    .slab(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB.get()).getFamily();

    private static BlockFamily.Builder familyBuilder(Block baseBlock) {
        BlockFamily.Builder blockfamily$builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockfamily = MAP.put(baseBlock, blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(baseBlock));
        } else {
            return blockfamily$builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }

}
