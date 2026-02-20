package com.cosmocat.motleyfins.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.fish.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.fish.TropicalFish;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = TropicalFish.class)
public abstract class TropicalFishMixin extends AbstractSchoolingFish {

    @Shadow
    @Final
    public static List<TropicalFish.Variant> COMMON_VARIANTS;

    public TropicalFishMixin(EntityType<? extends @NotNull AbstractSchoolingFish> entityType, Level level) {
        super(entityType, level);
    }

    static {
        ArrayList<TropicalFish.Variant> common_variants = new ArrayList<>(COMMON_VARIANTS);
        common_variants.removeIf((variant) -> variant.equals(new TropicalFish.Variant(TropicalFish.Pattern.DASHER, DyeColor.CYAN, DyeColor.YELLOW)));
        common_variants.removeIf((variant) -> variant.equals(new TropicalFish.Variant(TropicalFish.Pattern.DASHER, DyeColor.CYAN, DyeColor.PINK)));
        COMMON_VARIANTS = List.copyOf(common_variants);
    }
}
