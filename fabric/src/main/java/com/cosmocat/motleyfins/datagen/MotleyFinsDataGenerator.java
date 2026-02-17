package com.cosmocat.motleyfins.datagen;

import com.cosmocat.motleyfins.datagen.assets.MotleyFinsModelProvider;
import com.cosmocat.motleyfins.datagen.data.MotleyFinsBiomeTagProvider;
import com.cosmocat.motleyfins.datagen.data.MotleyFinsBlockTagProvider;
import com.cosmocat.motleyfins.datagen.data.MotleyFinsItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MotleyFinsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // Assets //
        pack.addProvider(MotleyFinsModelProvider::new);

        // Data //
        pack.addProvider(MotleyFinsItemTagProvider::new);
        pack.addProvider(MotleyFinsBlockTagProvider::new);
        pack.addProvider(MotleyFinsBiomeTagProvider::new);
    }

}