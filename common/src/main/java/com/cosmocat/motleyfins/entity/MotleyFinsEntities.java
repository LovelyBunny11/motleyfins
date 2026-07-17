package com.cosmocat.motleyfins.entity;

import com.cosmocat.motleyfins.MotleyFins;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.jetbrains.annotations.NotNull;

import static com.cosmocat.motleyfins.MotleyFins.MOD_ID;

public class MotleyFinsEntities {

    public static final EntityType<@NotNull Parrotfish> PARROTFISH =
            registerEntity("parrotfish", EntityType.Builder.of(Parrotfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.8F, 0.55F).eyeHeight(0.25F));

    public static void init() {}

    public static <T extends Entity> EntityType<@NotNull T> registerEntity(String name, EntityType.Builder<@NotNull T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, name), builder.build(name));
    }
}
