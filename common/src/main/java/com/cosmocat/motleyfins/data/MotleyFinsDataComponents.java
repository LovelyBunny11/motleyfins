package com.cosmocat.motleyfins.data;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.entity.Parrotfish;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.UnaryOperator;

public class MotleyFinsDataComponents {

    public static Map<ResourceKey<@NotNull DataComponentType<?>>, DataComponentType<?>> REGISTRIES;

    public static final DataComponentType<Parrotfish.@NotNull Variant> PARROTFISH_VARIANT = registerComponentType(
            "parrotfish/variant", (component) -> component.persistent(Parrotfish.Variant.CODEC).networkSynchronized(Parrotfish.Variant.STREAM_CODEC));

    public static void init() {}

    public static <T> DataComponentType<@NotNull T> registerComponentType(String name, UnaryOperator<DataComponentType.Builder<@NotNull T>> builder) {
        ResourceKey<@NotNull DataComponentType<?>> key = ResourceKey.create(Registries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
        DataComponentType<@NotNull T> dataComponentType = builder.apply(DataComponentType.builder()).build();

        REGISTRIES.put(key, dataComponentType);
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, key, dataComponentType);
    }
}
