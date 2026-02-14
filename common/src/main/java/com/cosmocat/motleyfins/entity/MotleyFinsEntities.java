package com.cosmocat.motleyfins.entity;

import com.cosmocat.motleyfins.registry.MotleyFinsRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class MotleyFinsEntities {

    public static final Supplier<EntityType<Parrotfish>> PARROTFISH =
            MotleyFinsRegistries.registerEntity("parrotfish", EntityType.Builder.<Parrotfish>of(Parrotfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.8F, 0.4F));

    public static void init() {}
}
