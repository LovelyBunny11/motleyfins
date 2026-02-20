package com.cosmocat.motleyfins.entity;

import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import com.cosmocat.motleyfins.data.MotleyFinsDataComponents;
import com.cosmocat.motleyfins.data.MotleyFinsTags;
import com.cosmocat.motleyfins.item.MotleyFinsItems;
import com.cosmocat.motleyfins.sound.MotleyFinsSoundEvents;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.fish.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.fish.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.IntFunction;

public class Parrotfish extends AbstractSchoolingFish {

    protected static final Parrotfish.Variant[] COMMON_VARIANTS = List.of(Variant.TRICOLOR, Variant.YELLOWTAIL, Variant.STOPLIGHT).toArray(Variant[]::new);

    public static final EntityDataAccessor<@NotNull Integer> DATA_TYPE_ID = SynchedEntityData.defineId(Parrotfish.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<@NotNull Boolean> IS_DROPPING_SAND = SynchedEntityData.defineId(Parrotfish.class, EntityDataSerializers.BOOLEAN);

    private int sandDroppingTicks = 0;
    private int sandDroppingCooldown = 0;
    private boolean isSchool = true;

    public Parrotfish(EntityType<? extends @NotNull AbstractSchoolingFish> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public double getEyeY() {
        return super.getEyeY();
    }

    public static AttributeSupplier.@NotNull Builder createMobAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MOVEMENT_SPEED, 1.0F).add(Attributes.MAX_HEALTH, 5.0F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.2, (itemStack) -> itemStack.is(MotleyFinsTags.Items.PARROTFISH_FOOD), false));
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.isDroppingSand()) {
            ++this.sandDroppingTicks;
            if ((this.random.nextInt(0, 30) == 0)) {
                this.drop(new ItemStack(MotleyFinsBlocks.WHITE_SAND.asItem()), true, true);
                this.playSound(SoundEvents.ITEM_PICKUP, 0.25F, 3.0F);
            } if (this.sandDroppingTicks > 200) {
                this.setDroppingSand(false);
                this.sandDroppingTicks = 0;
                this.sandDroppingCooldown = 6000;
            }
        } else {
            if (this.sandDroppingCooldown > 0) {
                --this.sandDroppingCooldown;
            }
        }
    }

    // Data //

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_TYPE_ID, 0);
        builder.define(IS_DROPPING_SAND, false);
    }

    public void addAdditionalSaveData(@NotNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("Variant", Parrotfish.Variant.CODEC, this.getVariant());
        output.putBoolean("IsDroppingSand", this.entityData.get(IS_DROPPING_SAND));
        output.putInt("SandDroppingTicks", this.sandDroppingTicks);
        output.putInt("SandDroppingCooldown", this.sandDroppingCooldown);
    }

    public void readAdditionalSaveData(@NotNull ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setVariant(input.read("Variant", Parrotfish.Variant.CODEC).orElse(Variant.RED));
        this.setDroppingSand(input.getBooleanOr("IsDroppingSand", false));
        this.sandDroppingTicks = input.getIntOr("SandDroppingTicks", 0);
        this.sandDroppingCooldown = input.getIntOr("SandDroppingCooldown", 0);
    }

    // White Sand Obtaining //

    @Override
    protected @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (this.canFeed()) {
            if (isFood(itemstack)) {
                if (player instanceof ServerPlayer serverplayer) {
                    this.setDroppingSand(true);
                    this.usePlayerItem(serverplayer, hand, itemstack);
                    this.level().broadcastEntityEvent(this, (byte)18);
                    this.makeSound(MotleyFinsSoundEvents.PARROTFISH_EAT);

                    return InteractionResult.SUCCESS_SERVER;
                }
            }
        }

        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    private boolean canFeed() {
        return !(this.isDroppingSand()) && (this.sandDroppingCooldown == 0);
    }

    public static boolean isFood(ItemStack itemstack) {
        return itemstack.is(MotleyFinsTags.Items.PARROTFISH_FOOD);
    }

    public boolean isDroppingSand() {
        return this.entityData.get(IS_DROPPING_SAND);
    }

    void setDroppingSand(boolean isDroppingSand) {
        this.entityData.set(IS_DROPPING_SAND, isDroppingSand);
    }

    public void handleEntityEvent(byte event) {
        if (event == 18) {
            for(int i = 0; i < 7; ++i) {
                double d0 = this.random.nextGaussian() * 0.02;
                double d1 = this.random.nextGaussian() * 0.02;
                double d2 = this.random.nextGaussian() * 0.02;
                this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0F), this.getRandomY(), this.getRandomZ(1.0F), d0, d1, d2);
            }
        } else {
            super.handleEntityEvent(event);
        }

    }

    // Bucketable //

    @Override
    public @NotNull ItemStack getBucketItemStack() {
        return new ItemStack(MotleyFinsItems.PARROTFISH_BUCKET);
    }

    @Override
    public void saveToBucketTag(@NotNull ItemStack stack) {
        super.saveToBucketTag(stack);
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, (tag) -> tag.putInt("Variant", this.getVariant().id));
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, (tag) -> tag.putBoolean("IsDroppingSand", this.isDroppingSand()));
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, (tag) -> tag.putInt("SandDroppingTicks", this.sandDroppingTicks));
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, (tag) -> tag.putInt("SandDroppingCooldown", this.sandDroppingCooldown));
    }

    public void loadFromBucketTag(@NotNull CompoundTag tag) {
        super.loadFromBucketTag(tag);
        if (tag.contains("Variant")) {
            this.setVariant(Parrotfish.Variant.byId(tag.getIntOr("Variant", 0)));
        } if (tag.contains("IsDroppingSand")) {
            this.setDroppingSand(tag.getBooleanOr("IsDroppingSand", false));
        } if (tag.contains("SandDroppingTicks")) {
            this.sandDroppingTicks = tag.getIntOr("SandDroppingTicks", 0);
        } if (tag.contains("SandDroppingCooldown")) {
            this.sandDroppingCooldown = tag.getIntOr("SandDroppingCooldown", 0);
        }
    }

    // Sounds //

    @Override
    protected SoundEvent getAmbientSound() {
        return MotleyFinsSoundEvents.PARROTFISH_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MotleyFinsSoundEvents.PARROTFISH_DEATH;
    }

    @Override
    protected @NotNull SoundEvent getFlopSound() {
        return MotleyFinsSoundEvents.PARROTFISH_FLOP;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return MotleyFinsSoundEvents.PARROTFISH_HURT;
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
            if (this.isDroppingSand()) {
                if (swimAnimationState.isStarted()) {
                    swimAnimationState.stop();
                } if (!swimPoopAnimationState.isStarted()) {
                    swimPoopAnimationState.start(this.tickCount);
                }
            } else {
                if (swimPoopAnimationState.isStarted()) {
                    swimPoopAnimationState.stop();
                } if (!swimAnimationState.isStarted()) {
                    swimAnimationState.start(this.tickCount);
                }
            }
        } if (!this.isInWater()) {
            if (swimAnimationState.isStarted()) {
                swimAnimationState.stop();
            } if (!flopAnimationState.isStarted()) {
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

    private static Parrotfish.Variant getRandomParrotfishVariant(Parrotfish parrotfish, RandomSource randomSource, SpawnGroupData spawnGroupData) {
        Parrotfish.Variant parrotfishVariant;
        if (spawnGroupData instanceof ParrotfishGroupData parrotfishGroupData) {
            parrotfishVariant = parrotfishGroupData.variant;
        } else if ((double)randomSource.nextFloat() < 0.75) {
            parrotfishVariant = Util.getRandom(COMMON_VARIANTS, randomSource);
            spawnGroupData = new ParrotfishGroupData(parrotfish, parrotfishVariant);
        } else {
            parrotfish.isSchool = false;
            parrotfishVariant = Parrotfish.Variant.byId(randomSource.nextInt((Variant.values().length) + 1));
        }
        return parrotfishVariant;
    }

    @Override
    public <T> T get(@NotNull DataComponentType<? extends @NotNull T> componentType) {
        return componentType == MotleyFinsDataComponents.PARROTFISH_VARIANT ? castComponentValue(componentType, this.getVariant()) : super.get(componentType);
    }

    @Override
    protected void applyImplicitComponents(@NotNull DataComponentGetter componentGetter) {
        this.applyImplicitComponentIfPresent(componentGetter, MotleyFinsDataComponents.PARROTFISH_VARIANT);
        super.applyImplicitComponents(componentGetter);
    }

    @Override
    protected <T> boolean applyImplicitComponent(@NotNull DataComponentType<@NotNull T> componentType, T $) {
        if (componentType == MotleyFinsDataComponents.PARROTFISH_VARIANT) {
            this.setVariant(castComponentValue(MotleyFinsDataComponents.PARROTFISH_VARIANT, $));
            return true;
        } else {
            return super.applyImplicitComponent(componentType, $);
        }
    }
    // Spawning //

    @Override
    public boolean isMaxGroupSizeReached(int p_478570_) {
        return !this.isSchool;
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull EntitySpawnReason reason, SpawnGroupData spawnGroupData) {
        spawnGroupData = super.finalizeSpawn(level, difficulty, reason, spawnGroupData);

        this.setVariant(getRandomParrotfishVariant(this, level.getRandom(), spawnGroupData));
        return spawnGroupData;
    }

    public static boolean checkParrotfishSpawnRules(EntityType<@NotNull Parrotfish> parrotfish, LevelAccessor level, EntitySpawnReason reason, BlockPos blockPos, RandomSource randomSource) {
        return (level.getFluidState(blockPos.below()).is(FluidTags.WATER) || level.getBlockState(blockPos.below()).is(BlockTags.CORAL_BLOCKS)) &&
                (level.getFluidState(blockPos).is(FluidTags.WATER) || level.getBlockState(blockPos).is(BlockTags.CORALS)) &&
                level.getFluidState(blockPos.above()).is(FluidTags.WATER) &&
                WaterAnimal.checkSurfaceWaterAnimalSpawnRules(parrotfish, level, reason, blockPos, randomSource);
    }


    static class ParrotfishGroupData extends AbstractSchoolingFish.SchoolSpawnGroupData {
        final Parrotfish.Variant variant;

        ParrotfishGroupData(Parrotfish parrotfish, Parrotfish.Variant variant) {
            super(parrotfish);
            this.variant = variant;
        }
    }

    public enum Variant implements StringRepresentable {

        RED(0, "red"),
        BLUE(1, "blue"),
        GREEN(2, "green"),
        CYAN(3, "cyan"),
        GRAY(4, "gray"),
        TRICOLOR(5, "tricolor"),
        YELLOWTAIL(6, "yellowtail"),
        STOPLIGHT(7, "stoplight");

        private static final IntFunction<Parrotfish.Variant> BY_ID = ByIdMap.sparse(Parrotfish.Variant::id, values(), RED);
        public static final Codec<Parrotfish.Variant> CODEC = StringRepresentable.fromEnum(Parrotfish.Variant::values);
        public static final StreamCodec<@NotNull ByteBuf, Parrotfish.@NotNull Variant> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Parrotfish.Variant::id);

        final int id;
        private final String name;
        private final Component displayName;

        Variant(int pId, String pName) {
            this.id = pId;
            this.name = pName;
            this.displayName = Component.translatable("entity.motleyfins.parrotfish.type." + this.name);
        }

        public @NotNull String getSerializedName() {
            return this.name;
        }

        public Component displayName() {
            return this.displayName;
        }

        public int id() {
            return this.id;
        }

        public static Parrotfish.Variant byId(int pId) {
            return BY_ID.apply(pId);
        }

        public record Id(int id) {
            public static final Codec<Parrotfish.Variant.Id> CODEC = Codec.INT.xmap(Parrotfish.Variant.Id::new, Parrotfish.Variant.Id::id);

            public int id() {
                return this.id;
            }

        }
    }

}
