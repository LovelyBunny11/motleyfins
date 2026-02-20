package com.cosmocat.motleyfins.client;

import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.entity.model.ParrotfishModel;
import com.cosmocat.motleyfins.entity.render.ParrotfishRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class MotleyFinsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(MotleyFinsEntities.PARROTFISH, ParrotfishRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ParrotfishModel.PARROTFISH_LAYER, ParrotfishModel::createBodyLayer);
    }
}
