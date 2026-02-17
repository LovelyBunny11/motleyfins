package com.cosmocat.motleyfins.item;

import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.entity.Parrotfish;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

public class MotleyFinsMobBucketItem extends MobBucketItem {
    private final EntityType<? extends @NotNull Mob> type;

    public MotleyFinsMobBucketItem(EntityType<? extends @NotNull Mob> type, Fluid content, SoundEvent emptySound, Properties properties) {
        super(type, content, emptySound, properties);
        this.type = type;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull TooltipDisplay display, @NotNull Consumer<Component> tooltipComponents, @NotNull TooltipFlag flag) {
        if (this.type == MotleyFinsEntities.PARROTFISH.get()) {
            CustomData customdata = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
            if (customdata.isEmpty()) {
                return;
            }

            Optional<Parrotfish.Variant.Id> optional = customdata.copyTag().read(Parrotfish.Variant.Id.CODEC.fieldOf("Variant"));
            if (optional.isPresent()) {
                Parrotfish.Variant variant = Parrotfish.Variant.byId(optional.get().id());

                tooltipComponents.accept(variant.displayName().plainCopy().withStyle(ChatFormatting.ITALIC, ChatFormatting.WHITE));
            }

        }
    }
}
