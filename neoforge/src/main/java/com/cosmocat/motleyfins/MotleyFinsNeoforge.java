package com.cosmocat.motleyfins;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = MotleyFins.MOD_ID)
public class MotleyFinsNeoforge {

    public MotleyFinsNeoforge() {
        MotleyFins.init();
        NeoForge.EVENT_BUS.register(this);
    }
}