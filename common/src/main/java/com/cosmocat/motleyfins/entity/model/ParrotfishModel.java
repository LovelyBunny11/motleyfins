package com.cosmocat.motleyfins.entity.model;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.entity.animation.ParrotfishAnimations;
import com.cosmocat.motleyfins.entity.render.state.ParrotfishRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.Identifier;

public class ParrotfishModel extends EntityModel<ParrotfishRenderState> {
    public static final ModelLayerLocation PARROTFISH_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(MotleyFins.MOD_ID, "parrotfish"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart body_front;
    private final ModelPart bottom_fin;
    private final ModelPart left_fin;
    private final ModelPart right_fin;
    private final ModelPart top_fin;
    private final ModelPart body_back;
    private final ModelPart tailfin;
    private final KeyframeAnimation swimAnimation;
    private final KeyframeAnimation flopAnimation;
    private final KeyframeAnimation swimPoopAnimation;

	public ParrotfishModel(ModelPart root) {
        super(root.getChild("root"));
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.body_front = this.body.getChild("body_front");
        this.bottom_fin = this.body_front.getChild("bottom_fin");
        this.left_fin = this.body_front.getChild("left_fin");
        this.right_fin = this.body_front.getChild("right_fin");
        this.top_fin = this.body_front.getChild("top_fin");
        this.body_back = this.body.getChild("body_back");
        this.tailfin = this.body_back.getChild("tailfin");
        this.swimAnimation = ParrotfishAnimations.SWIM.bake(root);
        this.flopAnimation = ParrotfishAnimations.FLOP.bake(root);
        this.swimPoopAnimation = ParrotfishAnimations.SWIM_POOP.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 20.7188F, -0.5313F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body_front = body.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.4375F, -4.9375F, 4.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -4.4375F, -6.9375F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 30).addBox(-1.0F, -0.4375F, -6.9375F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 30).addBox(0.0F, 2.5625F, 3.0625F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.7188F, -1.5313F));

        PartDefinition bottom_fin = body_front.addOrReplaceChild("bottom_fin", CubeListBuilder.create().texOffs(24, 30).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.5625F, 1.0625F));

        PartDefinition left_fin = body_front.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(28, 26).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.5625F, 2.0625F, 0.0F, 0.3491F, 0.0F));

        PartDefinition right_fin = body_front.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.5625F, 2.0625F, 0.0F, -0.3491F, 0.0F));

        PartDefinition top_fin = body_front.addOrReplaceChild("top_fin", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -2.0F, -4.0F, 0.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.4375F, 1.0625F));

        PartDefinition body_back = body.addOrReplaceChild("body_back", CubeListBuilder.create().texOffs(28, 19).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.7188F, 5.5313F));

        PartDefinition tailfin = body_back.addOrReplaceChild("tailfin", CubeListBuilder.create().texOffs(18, 19).addBox(0.0F, -3.0F, -1.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(ParrotfishRenderState state) {
        super.setupAnim(state);
        this.swimAnimation.apply(state.swimAnimationState, state.ageInTicks);
        this.flopAnimation.apply(state.flopAnimationState, state.ageInTicks);
        this.swimPoopAnimation.apply(state.swimPoopAnimationState, state.ageInTicks);
    }
}