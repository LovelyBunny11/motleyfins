package com.cosmocat.motleyfins.sound;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.registry.MotleyFinsRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class MotleyFinsSoundEvents {
    public static final Supplier<SoundEvent> PARROTFISH_AMBIENT = registerSound("entity.parrotfish.ambient");
    public static final Supplier<SoundEvent> PARROTFISH_DEATH = registerSound("entity.parrotfish.death");
    public static final Supplier<SoundEvent> PARROTFISH_FLOP = registerSound("entity.parrotfish.flop");
    public static final Supplier<SoundEvent> PARROTFISH_HURT = registerSound("entity.parrotfish.hurt");
    public static final Supplier<SoundEvent> PARROTFISH_EAT = registerSound("entity.parrotfish.eat");

    public static void init() {}

    public static Supplier<SoundEvent> registerSound(String name) {
        return MotleyFinsRegistries.registerSoundEvent(name, () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name)));
    }
}
