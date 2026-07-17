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
            familyBuilder(MotleyFinsBlocks.WHITE_SANDSTONE)
            .wall(MotleyFinsBlocks.WHITE_SANDSTONE_WALL)
            .stairs(MotleyFinsBlocks.WHITE_SANDSTONE_STAIRS)
            .slab(MotleyFinsBlocks.WHITE_SANDSTONE_SLAB)
            .chiseled(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE)
            .cut(MotleyFinsBlocks.CUT_WHITE_SANDSTONE)
            .dontGenerateRecipe().getFamily();

    public static final BlockFamily CUT_WHITE_SANDSTONE =
            familyBuilder(MotleyFinsBlocks.CUT_WHITE_SANDSTONE)
                    .slab(MotleyFinsBlocks.CUT_WHITE_SANDSTONE_SLAB).getFamily();

    public static final BlockFamily SMOOTH_WHITE_SANDSTONE =
            familyBuilder(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE)
                    .stairs(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS)
                    .slab(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE_SLAB).getFamily();

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
