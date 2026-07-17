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
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MotleyFinsMobBucketItem extends MobBucketItem {
    private final EntityType<? extends @NotNull Mob> type;

    public MotleyFinsMobBucketItem(EntityType<? extends @NotNull Mob> type, Fluid content, SoundEvent emptySound, Properties properties) {
        super(type, content, emptySound, properties);
        this.type = type;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        if (this.type == MotleyFinsEntities.PARROTFISH) {
            CustomData customdata = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
            if (customdata.isEmpty()) {
                return;
            }

            Parrotfish.Variant variant = Parrotfish.Variant.byId(customdata.copyTag().getInt("Variant"));
            tooltipComponents.add(variant.displayName().plainCopy().withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        }
    }
}
