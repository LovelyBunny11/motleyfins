package com.cosmocat.motleyfins.entity.render;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.entity.Parrotfish;
import com.cosmocat.motleyfins.entity.model.ParrotfishModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ParrotfishRenderer extends MobRenderer<@NotNull Parrotfish, @NotNull ParrotfishModel<Parrotfish>> {
    private static final ResourceLocation RED_LOCATION = ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/red.png");
    private static final ResourceLocation BLUE_LOCATION = ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/blue.png");
    private static final ResourceLocation GREEN_LOCATION = ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/green.png");
    private static final ResourceLocation CYAN_LOCATION = ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/cyan.png");
    private static final ResourceLocation GRAY_LOCATION = ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/gray.png");
    private static final ResourceLocation TRICOLOR_LOCATION = ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/tricolor.png");
    private static final ResourceLocation YELLOWTAIL_LOCATION = ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/yellowtail.png");
    private static final ResourceLocation STOPLIGHT_LOCATION = ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/stoplight.png");
    private static final ResourceLocation REDBAND_LOCATION = ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/redband.png");

    public ParrotfishRenderer(EntityRendererProvider.Context context) {
        super(context, new ParrotfishModel<>(context.bakeLayer(ParrotfishModel.PARROTFISH_LAYER)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Parrotfish parrotfish) {
        return switch (parrotfish.getVariant()) {
            case RED -> RED_LOCATION;
            case BLUE -> BLUE_LOCATION;
            case GREEN -> GREEN_LOCATION;
            case CYAN -> CYAN_LOCATION;
            case GRAY -> GRAY_LOCATION;
            case TRICOLOR -> TRICOLOR_LOCATION;
            case YELLOWTAIL -> YELLOWTAIL_LOCATION;
            case STOPLIGHT -> STOPLIGHT_LOCATION;
            case REDBAND -> REDBAND_LOCATION;
        };
    }
}