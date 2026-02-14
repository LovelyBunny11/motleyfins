package com.cosmocat.motleyfins.data;

import com.cosmocat.motleyfins.entity.Parrotfish;
import com.cosmocat.motleyfins.registry.MotleyFinsRegistries;
import net.minecraft.core.component.DataComponentType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class MotleyFinsDataComponents {
    public static final Supplier<DataComponentType<Parrotfish.@NotNull Variant>> PARROTFISH_VARIANT = MotleyFinsRegistries.registerComponentType(
            "parrotfish/variant", (component) -> component.persistent(Parrotfish.Variant.CODEC).networkSynchronized(Parrotfish.Variant.STREAM_CODEC));

    public static void init() {}
}
