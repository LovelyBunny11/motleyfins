package com.cosmocat.motleyfins.sound;

import com.cosmocat.motleyfins.MotleyFins;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MotleyFinsSoundEvents {
    public static Map<ResourceKey<@NotNull SoundEvent>, SoundEvent> REGISTRIES;

    public static final SoundEvent PARROTFISH_AMBIENT = registerSound("entity.parrotfish.ambient");
    public static final SoundEvent PARROTFISH_DEATH = registerSound("entity.parrotfish.death");
    public static final SoundEvent PARROTFISH_FLOP = registerSound("entity.parrotfish.flop");
    public static final SoundEvent PARROTFISH_HURT = registerSound("entity.parrotfish.hurt");
    public static final SoundEvent PARROTFISH_EAT = registerSound("entity.parrotfish.eat");

    public static void init() {}

    public static SoundEvent registerSound(String name) {
        ResourceKey<@NotNull SoundEvent> key = ResourceKey.create(Registries.SOUND_EVENT, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
        SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));

        REGISTRIES.put(key, soundEvent);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, key, soundEvent);
    }
}
