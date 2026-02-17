package com.cosmocat.motleyfins.entity;

import com.cosmocat.motleyfins.registry.MotleyFinsRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class MotleyFinsEntities {

    public static final Supplier<EntityType<@NotNull Parrotfish>> PARROTFISH =
            MotleyFinsRegistries.registerEntity("parrotfish", EntityType.Builder.of(Parrotfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.9F, 0.6F).eyeHeight(0.25F));

    public static void init() {}
}
