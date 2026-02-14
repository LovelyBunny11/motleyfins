package com.cosmocat.motleyfins.datagen.assets;

import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import com.cosmocat.motleyfins.item.MotleyFinsItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class MotleyFinsModelProvider extends FabricModelProvider {
    public MotleyFinsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(MotleyFinsBlocks.WHITE_SAND.get());
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(MotleyFinsItems.PARROTFISH_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MotleyFinsItems.PARROTFISH_BUCKET.get(), ModelTemplates.FLAT_ITEM);
    }

    @Override
    public String getName() {
        return "MotleyFinsModelProvider";
    }
}