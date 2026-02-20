package com.cosmocat.motleyfins.entity.render;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.entity.Parrotfish;
import com.cosmocat.motleyfins.entity.model.ParrotfishModel;
import com.cosmocat.motleyfins.entity.render.state.ParrotfishRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ParrotfishRenderer extends MobRenderer<@NotNull Parrotfish, @NotNull ParrotfishRenderState, @NotNull ParrotfishModel> {
    private static final Identifier RED_LOCATION = Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/red.png");
    private static final Identifier BLUE_LOCATION = Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/blue.png");
    private static final Identifier GREEN_LOCATION = Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/green.png");
    private static final Identifier CYAN_LOCATION = Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/cyan.png");
    private static final Identifier GRAY_LOCATION = Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/gray.png");
    private static final Identifier TRICOLOR_LOCATION = Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/tricolor.png");
    private static final Identifier YELLOWTAIL_LOCATION = Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/yellowtail.png");
    private static final Identifier STOPLIGHT_LOCATION = Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID,"textures/entity/parrotfish/stoplight.png");

    public ParrotfishRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ParrotfishModel(pContext.bakeLayer(ParrotfishModel.PARROTFISH_LAYER)), 0.5f);
    }

    @Override
    public ParrotfishRenderState createRenderState() {
        return new ParrotfishRenderState();
    }

    public @NotNull Identifier getTextureLocation(ParrotfishRenderState state) {
        return switch (state.variant) {
            case RED -> RED_LOCATION;
            case BLUE -> BLUE_LOCATION;
            case GREEN -> GREEN_LOCATION;
            case CYAN -> CYAN_LOCATION;
            case GRAY -> GRAY_LOCATION;
            case TRICOLOR -> TRICOLOR_LOCATION;
            case YELLOWTAIL -> YELLOWTAIL_LOCATION;
            case STOPLIGHT -> STOPLIGHT_LOCATION;
        };
    }

    @Override
    public void extractRenderState(Parrotfish parrotfish, ParrotfishRenderState renderState, float partialTick) {
        super.extractRenderState(parrotfish, renderState, partialTick);
        renderState.variant = parrotfish.getVariant();
        renderState.swimAnimationState.copyFrom(parrotfish.swimAnimationState);
        renderState.flopAnimationState.copyFrom(parrotfish.flopAnimationState);
        renderState.swimPoopAnimationState.copyFrom(parrotfish.swimPoopAnimationState);
    }

}