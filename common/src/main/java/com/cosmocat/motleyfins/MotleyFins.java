package com.cosmocat.motleyfins;

import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import com.cosmocat.motleyfins.data.MotleyFinsDataComponents;
import com.cosmocat.motleyfins.data.MotleyFinsTags;
import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.item.MotleyFinsItems;
import com.cosmocat.motleyfins.sound.MotleyFinsSoundEvents;

public class MotleyFins {

    public static final String MOD_ID = "motleyfins";

    public static void init() {
        MotleyFinsBlocks.init();
        MotleyFinsItems.init();
        MotleyFinsSoundEvents.init();
        MotleyFinsEntities.init();
        MotleyFinsDataComponents.init();
        MotleyFinsTags.init();
    }
}