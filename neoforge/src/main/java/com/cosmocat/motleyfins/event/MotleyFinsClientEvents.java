package com.cosmocat.motleyfins.event;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.entity.model.ParrotfishModel;
import com.cosmocat.motleyfins.entity.render.ParrotfishRenderer;
import com.cosmocat.motleyfins.particle.MotleyFinsParticles;
import com.cosmocat.motleyfins.particle.WhiteSandParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = MotleyFins.MOD_ID, value = Dist.CLIENT)
public class MotleyFinsClientEvents {

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ParrotfishModel.PARROTFISH_LAYER, ParrotfishModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MotleyFinsEntities.PARROTFISH, ParrotfishRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(MotleyFinsParticles.WHITE_SAND, WhiteSandParticle.Provider::new);
    }
}
