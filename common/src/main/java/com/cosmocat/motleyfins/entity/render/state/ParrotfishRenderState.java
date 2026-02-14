package com.cosmocat.motleyfins.entity.render.state;

import com.cosmocat.motleyfins.entity.Parrotfish;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class ParrotfishRenderState extends LivingEntityRenderState {

    public final AnimationState swimAnimationState = new AnimationState();

    public final AnimationState flopAnimationState = new AnimationState();

    public final AnimationState swimPoopAnimationState = new AnimationState();

    public Parrotfish.Variant variant;

    public ParrotfishRenderState() {
        this.variant = Parrotfish.Variant.RED;
    }

}
