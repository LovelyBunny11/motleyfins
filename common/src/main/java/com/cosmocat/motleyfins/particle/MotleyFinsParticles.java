package com.cosmocat.motleyfins.particle;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import static com.cosmocat.motleyfins.MotleyFins.MOD_ID;

public class MotleyFinsParticles {

    public static final SimpleParticleType WHITE_SAND = register("white_sand", false);

    public static void init() {}

    private static SimpleParticleType register(String key, boolean overrideLimiter) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, key), new SimpleParticleType(overrideLimiter));
    }
}
