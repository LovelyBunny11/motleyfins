package com.cosmocat.motleyfins.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

public class WhiteSandParticle extends SingleQuadParticle {
    protected final SpriteSet sprites;

    WhiteSandParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, sprites.first());
        this.gravity = 0.0125F;
        this.sprites = sprites;
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;
        this.quadSize = 0.175F * (this.random.nextFloat() * 0.5F + 0.5F) * 2.0F;
        this.lifetime = 60 + this.random.nextInt(12);
        this.setSpriteFromAge(sprites);
    }

    @Override
    public int getLightColor(float partialTick) {
        BlockPos blockPos = BlockPos.containing(this.x, this.y, this.z);
        return this.level.hasChunkAt(blockPos) ? LevelRenderer.getLightColor(this.level, blockPos) : 0;
    }

    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().move(x, y, z));
        this.setLocationFromBoundingbox();
    }

    @Override
    public SingleQuadParticle.@NotNull Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.setAlpha(1.0F - (float) this.age / (float) this.lifetime);
    }

    public record Provider(SpriteSet sprites) implements ParticleProvider<@NotNull SimpleParticleType> {

        public Particle createParticle(SimpleParticleType simpleParticleType, @NotNull ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, @NotNull RandomSource randomSource) {
                return new WhiteSandParticle(clientLevel, d, e, f, g, h, i, this.sprites);
            }
        }
}
