package com.cosmocat.motleyfins.client;

import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.entity.model.ParrotfishModel;
import com.cosmocat.motleyfins.entity.render.ParrotfishRenderer;
import com.cosmocat.motleyfins.particle.MotleyFinsParticles;
import com.cosmocat.motleyfins.particle.WhiteSandParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class MotleyFinsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(MotleyFinsEntities.PARROTFISH, ParrotfishRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ParrotfishModel.PARROTFISH_LAYER, ParrotfishModel::createBodyLayer);
        ParticleFactoryRegistry.getInstance().register(MotleyFinsParticles.WHITE_SAND, WhiteSandParticle.Provider::new);
    }
}
