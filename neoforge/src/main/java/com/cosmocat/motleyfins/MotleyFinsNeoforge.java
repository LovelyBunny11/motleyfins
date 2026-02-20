package com.cosmocat.motleyfins;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(MotleyFins.MOD_ID)
public class MotleyFinsNeoforge {

    public MotleyFinsNeoforge() {
        NeoForge.EVENT_BUS.register(this);
        MotleyFins.init();
    }
}