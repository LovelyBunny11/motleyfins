package com.cosmocat.motleyfins.entity.model;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.entity.Parrotfish;
import com.cosmocat.motleyfins.entity.animation.ParrotfishAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class ParrotfishModel<T extends Parrotfish> extends HierarchicalModel<T> {
    public static final ModelLayerLocation PARROTFISH_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MotleyFins.MOD_ID, "parrotfish"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart body_front;
    private final ModelPart beak;
    private final ModelPart right_fin;
    private final ModelPart left_fin;
    private final ModelPart body_back;
    private final ModelPart tail_fin;

    public ParrotfishModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.body_front = this.body.getChild("body_front");
        this.beak = this.body_front.getChild("beak");
        this.right_fin = this.body_front.getChild("right_fin");
        this.left_fin = this.body_front.getChild("left_fin");
        this.body_back = this.body.getChild("body_back");
        this.tail_fin = this.body_back.getChild("tail_fin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, -0.5F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body_front = body.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5556F, -2.7222F, -5.0556F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(16, 14).addBox(-1.5556F, -2.7222F, -6.0556F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 20).addBox(-0.0556F, -5.7222F, -4.0556F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(6, 24).addBox(-0.0556F, 3.2778F, -2.0556F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0556F, -0.2778F, -0.4444F));

        PartDefinition beak = body_front.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(24, 14).addBox(-1.0F, 0.0F, -0.49F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0556F, -0.7222F, -5.5556F));

        PartDefinition right_fin = body_front.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(16, 0).addBox(-3.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5556F, 1.2778F, -1.0556F, 0.0F, 0.9599F, 0.0F));

        PartDefinition left_fin = body_front.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(16, 4).addBox(0.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4444F, 1.2778F, -1.0556F, 0.0F, -0.9599F, 0.0F));

        PartDefinition body_back = body.addOrReplaceChild("body_back", CubeListBuilder.create().texOffs(0, 11).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 6.0F, 7.0F, new CubeDeformation(0.01F))
                .texOffs(0, 18).addBox(-0.5F, -6.0F, 0.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-0.5F, 3.0F, 3.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, -0.5F));

        PartDefinition tail_fin = body_back.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(16, 3).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 7.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T parrotfish, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(parrotfish.swimAnimationState, ParrotfishAnimations.SWIM, ageInTicks);
        this.animate(parrotfish.flopAnimationState, ParrotfishAnimations.FLOP, ageInTicks);
        this.animate(parrotfish.swimPoopAnimationState, ParrotfishAnimations.SWIM_SAND, ageInTicks);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}