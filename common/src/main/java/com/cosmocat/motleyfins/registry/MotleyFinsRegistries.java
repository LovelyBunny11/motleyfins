package com.cosmocat.motleyfins.registry;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MotleyFinsRegistries {

    public static <T extends Block> Supplier<@NotNull T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends @NotNull T> blockConstructor, Supplier<Block.Properties> propertiesSupplier) {
        throw new AssertionError();
    }

    public static <T extends Item> Supplier<@NotNull T> registerItem(String name, Function<Item.Properties, ? extends @NotNull T> func, Supplier<Item.Properties> props) {
        throw new AssertionError();
    }

    public static <T extends Entity> Supplier<EntityType<@NotNull T>> registerEntity(String name, EntityType.Builder<@NotNull T> builder) {
        throw new AssertionError();
    }

    public static <T> Supplier<DataComponentType<T>> registerComponentType(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        throw new AssertionError();
    }
}
