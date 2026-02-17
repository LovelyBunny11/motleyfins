package com.cosmocat.motleyfins.mixin;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.registry.MotleyFinsRegistries;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
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

import static com.cosmocat.motleyfins.MotleyFinsNeoforge.*;

@Mixin(value = MotleyFinsRegistries.class, remap = false)
public class RegistryInitializerNeoforge {

    @Deprecated
    @Overwrite
    public static <T extends Block> Supplier<@NotNull T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends @NotNull T> blockConstructor, Supplier<Block.Properties> propertiesSupplier) {
        return BLOCKS.register(name, () -> blockConstructor.apply(propertiesSupplier.get().setId(ResourceKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath(BLOCKS.getNamespace(), name)))));
    }

    @Deprecated
    @Overwrite
    public static <T extends Item> Supplier<@NotNull T> registerItem(String name, Function<Item.Properties, ? extends @NotNull T> func, Supplier<Item.Properties> props) {
        return ITEMS.register(name, () -> func.apply(props.get().setId(ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(ITEMS.getNamespace(), name)))));
    }

    @Deprecated
    @Overwrite
    public static <T extends Entity> Supplier<EntityType<@NotNull T>> registerEntity(String name, EntityType.Builder<@NotNull T> builder) {
        return ENTITY_TYPES.register(name, () -> builder.build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, name))));
    }

    @Deprecated
    @Overwrite
    public static <T> Supplier<DataComponentType<@NotNull T>> registerComponentType(String name, UnaryOperator<DataComponentType.Builder<@NotNull T>> builder) {
        return DATA_COMPONENTS.registerComponentType(name, builder);
    }

    @Deprecated
    @Overwrite
    public static <T extends SoundEvent> Supplier<@NotNull T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        return SOUND_EVENTS.register(name, soundEvent);
    }
}
