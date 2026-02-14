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