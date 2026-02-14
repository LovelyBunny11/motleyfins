package com.cosmocat.motleyfins.entity;

import com.cosmocat.motleyfins.data.MotleyFinsDataComponents;
import com.cosmocat.motleyfins.item.MotleyFinsItems;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.fish.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

public class Parrotfish extends AbstractSchoolingFish {

    public static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(Parrotfish.class, EntityDataSerializers.INT);

    public Parrotfish(EntityType<? extends @NotNull AbstractSchoolingFish> entityType, Level level) {
        super(entityType, level);
    }

    public Parrotfish(Level level, double pX, double pY, double pZ) {
        this(MotleyFinsEntities.PARROTFISH.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25F));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6, 1.4, EntitySelector.NO_SPECTATORS));
        this.goalSelector.addGoal(4, new ParrotfishSwimGoal(this));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    // Bucketable //

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MotleyFinsItems.PARROTFISH_BUCKET.get());
    }

    @Override
    public void saveToBucketTag(ItemStack stack) {
        super.saveToBucketTag(stack);
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, (tag) -> {
            tag.putInt("Variant", this.getVariant().id);
        });
    }

    public void loadFromBucketTag(CompoundTag tag) {
        super.loadFromBucketTag(tag);
        if (tag.contains("Variant")) {
            this.setVariant(Parrotfish.Variant.byId(tag.getIntOr("Variant", 0)));
        }
    }

    // Spawning //

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance pDifficulty, EntitySpawnReason pReason, SpawnGroupData pSpawnData) {
        Parrotfish.Variant catfishVariant = getRandomParrotfishVariant(level);
        this.setVariant(catfishVariant);
        return super.finalizeSpawn(level, pDifficulty, pReason, pSpawnData);
    }

    // Pathfinding //

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        return level.getBlockState(pos.below()).is(BlockTags.CORAL_BLOCKS) || level.getBlockState(pos).is(BlockTags.CORAL_PLANTS) ? 10.0F : 0.0F;
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WaterBoundPathNavigation(Parrotfish.this, level) {
            @Override
            public boolean isStableDestination(BlockPos pos) {
                return super.isStableDestination(pos) && (this.level.getBlockState(pos.below()).is(BlockTags.CORAL_BLOCKS))  || this.level.getBlockState(pos).is(BlockTags.CORAL_PLANTS);
            }
        };
    }

    // Data //

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_TYPE_ID, 0);
    }

    public void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("Variant", Parrotfish.Variant.CODEC, this.getVariant());
    }

    public void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setVariant(input.read("Variant", Parrotfish.Variant.CODEC).orElse(Variant.RED));
    }

    // Sounds //

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.TROPICAL_FISH_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.TROPICAL_FISH_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.TROPICAL_FISH_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.TROPICAL_FISH_FLOP;
    }

    // Animations //
    public final AnimationState swimAnimationState = new AnimationState();

    public final AnimationState flopAnimationState = new AnimationState();

    public final AnimationState swimPoopAnimationState = new AnimationState();

    public void setupAnimationStates() {
        if (this.isInWater()) {
            if (flopAnimationState.isStarted()) {
                flopAnimationState.stop();
            }
            if (!swimAnimationState.isStarted()) {
                swimAnimationState.start(this.tickCount);
            }
        }

        if (!this.isInWater()) {
            if (swimAnimationState.isStarted()) {
                swimAnimationState.stop();
            }
            if (!flopAnimationState.isStarted()) {
                flopAnimationState.start(this.tickCount);
            }
        }
    }

    // Variant //

    public void setVariant(Parrotfish.Variant variant) {
        this.entityData.set(DATA_TYPE_ID, variant.id);
    }

    public Parrotfish.Variant getVariant() {
        return Parrotfish.Variant.byId(this.entityData.get(DATA_TYPE_ID));
    }

    private static Parrotfish.Variant getRandomParrotfishVariant(LevelAccessor level) {
        return level.getRandom().nextBoolean() ? Variant.RED : Variant.BLUE;
    }

    @Override
    public <T> T get(DataComponentType<? extends T> componentType) {
        return componentType == MotleyFinsDataComponents.PARROTFISH_VARIANT.get() ? castComponentValue((DataComponentType<T>)componentType, this.getVariant()) : super.get(componentType);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter componentGetter) {
        this.applyImplicitComponentIfPresent(componentGetter, MotleyFinsDataComponents.PARROTFISH_VARIANT.get());
        super.applyImplicitComponents(componentGetter);
    }

    @Override
    protected <T> boolean applyImplicitComponent(DataComponentType<T> componentType, T $) {
        if (componentType == MotleyFinsDataComponents.PARROTFISH_VARIANT.get()) {
            this.setVariant(castComponentValue(MotleyFinsDataComponents.PARROTFISH_VARIANT.get(), $));
            return true;
        } else {
            return super.applyImplicitComponent(componentType, $);
        }
    }

    public enum Variant implements StringRepresentable {

        RED(0, "red"),
        BLUE(1, "blue");

        private static final IntFunction<Parrotfish.Variant> BY_ID = ByIdMap.sparse(Parrotfish.Variant::id, values(), RED);
        public static final Codec<Parrotfish.Variant> CODEC = StringRepresentable.fromEnum(Parrotfish.Variant::values);
        public static final StreamCodec<ByteBuf, Parrotfish.Variant> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Parrotfish.Variant::id);

        final int id;
        private final String name;
        private final Component displayName;

        Variant(int pId, String pName) {
            this.id = pId;
            this.name = pName;
            this.displayName = Component.translatable("entity.naturality.parrotfish.type." + this.name);
        }

        public String getSerializedName() {
            return this.name;
        }

        public Component displayName() {
            return this.displayName;
        }

        public static Parrotfish.Variant getType(int pVariantId) {
            return Parrotfish.Variant.byId(pVariantId & '\uffff');
        }

        public int id() {
            return this.id;
        }

        public static Parrotfish.Variant byId(int pId) {
            return BY_ID.apply(pId);
        }

        public record Id(int id) {
            public static final Codec<Parrotfish.Variant.Id> CODEC = Codec.INT.xmap(Parrotfish.Variant.Id::new, Parrotfish.Variant.Id::id);

            public Id(int id) {
                this.id = id;
            }

            public int id() {
                return this.id;
            }

        }
    }

    // Goals //

    static class ParrotfishSwimGoal extends RandomSwimmingGoal {
        private final Parrotfish fish;

        public ParrotfishSwimGoal(Parrotfish mob) {
            super(mob, 1.0D, 80);
            this.fish = mob;
        }

        public static float getProbabilityToMove(Parrotfish fish) {
            float probability = 0.5F;
            return probability;
        }

        @Override
        public boolean canUse() {
            return (this.fish.getRandom().nextFloat() <= getProbabilityToMove(this.fish)) && super.canUse();
        }

        @Override
        protected Vec3 getPosition() {
            return getRandomSwimmablePos(this.fish, 10, 10);
        }

        public static Vec3 getRandomSwimmablePos(Parrotfish pathfinder, int radius, int verticalDistance) {
            Vec3 vec3 = DefaultRandomPos.getPos(pathfinder, radius, verticalDistance);

            for(int i = 0; vec3 != null && !pathfinder.level().getBlockState(BlockPos.containing(vec3)).isPathfindable(PathComputationType.WATER) && i++ < 10; vec3 = DefaultRandomPos.getPos(pathfinder, radius, verticalDistance)) {
            }

            if (vec3 != null && pathfinder.getNavigation().isStableDestination(BlockPos.containing(vec3))) {
                return vec3;
            } else {
                return null;
            }
        }

    }

}
