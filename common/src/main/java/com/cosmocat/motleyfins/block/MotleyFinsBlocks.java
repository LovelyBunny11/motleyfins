package com.cosmocat.motleyfins.block;

import com.cosmocat.motleyfins.registry.MotleyFinsRegistries;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public class MotleyFinsBlocks {
    public static final Supplier<Block> WHITE_SAND = MotleyFinsRegistries.registerBlock("white_sand",
            (properties) -> new SandBlock(new ColorRGBA(12893615), properties),() -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND));

    public static void init() {}
}
