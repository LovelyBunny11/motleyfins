package com.cosmocat.motleyfins.mixin;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.registry.MotleyFinsRegistries;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Mixin(MotleyFinsRegistries.class)
public class RegistryInitializerFabric {

    @Deprecated
    @Overwrite
    public static <T extends Block> Supplier<@NotNull T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends @NotNull T> func, Supplier<Block.Properties> props) {
        ResourceKey<@NotNull Block> key = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
        T block = func.apply(props.get().setId(key));
        Registry.register(BuiltInRegistries.BLOCK, key, block);

        return () -> block;
    }

    @Deprecated
    @Overwrite
    public static <T extends Item> Supplier<@NotNull T> registerItem(String name, Function<Item.Properties, ? extends @NotNull T> func, Supplier<Item.Properties> props) {
        ResourceKey<@NotNull Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
        T item = func.apply(props.get().setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);

        return () -> item;
    }

    @Deprecated
    @Overwrite
    public static <T extends Entity> Supplier<EntityType<@NotNull T>> registerEntity(String name, EntityType.Builder<@NotNull T> builder) {
        ResourceKey<@NotNull EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
        EntityType<@NotNull T> entity = Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name))));
        return () -> entity;
    }

    @Deprecated
    @Overwrite
    public static <T> Supplier<DataComponentType<@NotNull T>> registerComponentType(String name, UnaryOperator<DataComponentType.Builder<@NotNull T>> builder) {
        ResourceKey<@NotNull DataComponentType<?>> key = ResourceKey.create(Registries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name));
        DataComponentType<@NotNull T> dataComponentType = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, key, builder.apply(DataComponentType.builder()).build());
        return () -> dataComponentType;
    }
}
