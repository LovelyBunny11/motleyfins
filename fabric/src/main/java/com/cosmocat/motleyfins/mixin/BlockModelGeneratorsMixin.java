package com.cosmocat.motleyfins.mixin;

import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(value = BlockModelGenerators.class)
public class BlockModelGeneratorsMixin {
    @Shadow
    public static final Map<Block, TexturedModel> TEXTURED_MODELS;

    static {
        TEXTURED_MODELS = ImmutableMap.<Block, TexturedModel>builder().put(Blocks.SANDSTONE, TexturedModel.TOP_BOTTOM_WITH_WALL.get(Blocks.SANDSTONE)).put(Blocks.RED_SANDSTONE, TexturedModel.TOP_BOTTOM_WITH_WALL.get(Blocks.RED_SANDSTONE)).put(Blocks.SMOOTH_SANDSTONE, TexturedModel.createAllSame(TextureMapping.getBlockTexture(Blocks.SANDSTONE, "_top"))).put(Blocks.SMOOTH_RED_SANDSTONE, TexturedModel.createAllSame(TextureMapping.getBlockTexture(Blocks.RED_SANDSTONE, "_top"))).put(Blocks.CUT_SANDSTONE, TexturedModel.COLUMN.get(Blocks.SANDSTONE).updateTextures((textureMapping) -> textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.CUT_SANDSTONE)))).put(Blocks.CUT_RED_SANDSTONE, TexturedModel.COLUMN.get(Blocks.RED_SANDSTONE).updateTextures((textureMapping) -> textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.CUT_RED_SANDSTONE)))).put(Blocks.QUARTZ_BLOCK, TexturedModel.COLUMN.get(Blocks.QUARTZ_BLOCK)).put(Blocks.SMOOTH_QUARTZ, TexturedModel.createAllSame(TextureMapping.getBlockTexture(Blocks.QUARTZ_BLOCK, "_bottom"))).put(Blocks.BLACKSTONE, TexturedModel.COLUMN_WITH_WALL.get(Blocks.BLACKSTONE)).put(Blocks.DEEPSLATE, TexturedModel.COLUMN_WITH_WALL.get(Blocks.DEEPSLATE)).put(Blocks.CHISELED_QUARTZ_BLOCK, TexturedModel.COLUMN.get(Blocks.CHISELED_QUARTZ_BLOCK).updateTextures((textureMapping) -> textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.CHISELED_QUARTZ_BLOCK)))).put(Blocks.CHISELED_SANDSTONE, TexturedModel.COLUMN.get(Blocks.CHISELED_SANDSTONE).updateTextures((textureMapping) -> {
            textureMapping.put(TextureSlot.END, TextureMapping.getBlockTexture(Blocks.SANDSTONE, "_top"));
            textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.CHISELED_SANDSTONE));
        })).put(Blocks.CHISELED_RED_SANDSTONE, TexturedModel.COLUMN.get(Blocks.CHISELED_RED_SANDSTONE).updateTextures((textureMapping) -> {
            textureMapping.put(TextureSlot.END, TextureMapping.getBlockTexture(Blocks.RED_SANDSTONE, "_top"));
            textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.CHISELED_RED_SANDSTONE));
        })).put(Blocks.CHISELED_TUFF_BRICKS, TexturedModel.COLUMN_WITH_WALL.get(Blocks.CHISELED_TUFF_BRICKS)).put(Blocks.CHISELED_TUFF, TexturedModel.COLUMN_WITH_WALL.get(Blocks.CHISELED_TUFF))
        // Adding MotleyFins Blocks //
        .put(MotleyFinsBlocks.WHITE_SANDSTONE, TexturedModel.TOP_BOTTOM_WITH_WALL.get(MotleyFinsBlocks.WHITE_SANDSTONE)).put(MotleyFinsBlocks.SMOOTH_WHITE_SANDSTONE, TexturedModel.createAllSame(TextureMapping.getBlockTexture(MotleyFinsBlocks.WHITE_SANDSTONE, "_top"))).put(MotleyFinsBlocks.CUT_WHITE_SANDSTONE, TexturedModel.COLUMN.get(MotleyFinsBlocks.WHITE_SANDSTONE).updateTextures((textureMapping) -> textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(MotleyFinsBlocks.CUT_WHITE_SANDSTONE)))).put(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE, TexturedModel.COLUMN.get(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE).updateTextures((textureMapping) -> {
            textureMapping.put(TextureSlot.END, TextureMapping.getBlockTexture(MotleyFinsBlocks.WHITE_SANDSTONE, "_top"));
            textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(MotleyFinsBlocks.CHISELED_WHITE_SANDSTONE));
        })).build();

    }
}
