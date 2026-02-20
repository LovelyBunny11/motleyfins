package com.cosmocat.motleyfins.entity;

import com.cosmocat.motleyfins.MotleyFins;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.jetbrains.annotations.NotNull;

public class MotleyFinsEntities {

    public static final EntityType<@NotNull Parrotfish> PARROTFISH =
            registerEntity("parrotfish", EntityType.Builder.of(Parrotfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.9F, 0.6F).eyeHeight(0.25F));

    public static void init() {}

    public static <T extends Entity> EntityType<@NotNull T> registerEntity(String name, EntityType.Builder<@NotNull T> builder) {
        ResourceKey<@NotNull EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name))));
    }
}
