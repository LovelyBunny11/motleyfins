package com.cosmocat.motleyfins.entity;

import com.cosmocat.motleyfins.block.MotleyFinsBlocks;
import com.cosmocat.motleyfins.data.MotleyFinsDataComponents;
import com.cosmocat.motleyfins.data.MotleyFinsTags;
import com.cosmocat.motleyfins.item.MotleyFinsItems;
import com.cosmocat.motleyfins.particle.MotleyFinsParticles;
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
import net.minecraft.util.*;
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
import net.minecraft.world.entity.item.ItemEntity;
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

    protected static final Variant[] COMMON_VARIANTS = List.of(Variant.BLUE, Variant.TRICOLOR, Variant.YELLOWTAIL, Variant.STOPLIGHT, Variant.REDBAND).toArray(Variant[]::new);

    public static final EntityDataAccessor<@NotNull Integer> DATA_TYPE_ID = SynchedEntityData.defineId(Parrotfish.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<@NotNull Boolean> IS_DROPPING_SAND = SynchedEntityData.defineId(Parrotfish.class, EntityDataSerializers.BOOLEAN);

    private int ticksAfterLastSandDrop = 0;
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

    private @NotNull ItemEntity createWhiteSandToDrop() {
        double d0 = this.getEyeY() - (double)0.3F;
        ItemEntity itementity = new ItemEntity(this.level(), this.getX(), d0, this.getZ(), new ItemStack(MotleyFinsBlocks.WHITE_SAND.asItem()));
        itementity.setPickUpDelay(40);
        itementity.setThrower(this);

        float f8 = Mth.sin(this.getXRot() * ((float)Math.PI / 180F));
        float f2 = Mth.cos(this.getXRot() * ((float)Math.PI / 180F));
        float f3 = Mth.sin( this.getYRot() * ((float)Math.PI / 180F));
        float f4 = Mth.cos(this.getYRot() * ((float)Math.PI / 180F));
        float f5 = this.random.nextFloat() * ((float)Math.PI * 2F);
        float f6 = 0.02F * this.random.nextFloat();
        itementity.setDeltaMovement(-((double)(-f3 * f2 * 0.3F) + Math.cos(f5) * (double)f6), -(-f8 * 0.3F + 0.1F + (this.random.nextFloat() - this.random.nextFloat()) * 0.1F), -((double)(f4 * f2 * 0.3F) + Math.sin(f5) * (double)f6));

        return itementity;
    }

    public ItemEntity dropWhiteSand() {
        if (this.level().isClientSide()) {
            this.swing(InteractionHand.MAIN_HAND);
            return null;
        } else {
            ItemEntity itementity = this.createWhiteSandToDrop();
            this.level().addFreshEntity(itementity);

            return itementity;
        }
    }


    @Override
    public void aiStep() {
        super.aiStep();
        ++this.ticksAfterLastSandDrop;
        ItemEntity item = null;

        if (this.isDroppingSand()) {
            ++this.sandDroppingTicks;
            if ((this.random.nextInt(0, 30) == 0)) {
                item = this.dropWhiteSand();
                this.playSound(SoundEvents.ITEM_PICKUP, 0.25F, 3.0F);
                this.ticksAfterLastSandDrop = 0;
            } if (this.sandDroppingTicks > 200) {
                this.setDroppingSand(false);
                this.sandDroppingTicks = 0;
                this.sandDroppingCooldown = 6000;
            }
            if (this.random.nextInt(5) == 0) {
                int d = this.random.nextInt(1, 5);
                for (int i = 0; i < d; i++) {
                    double d0 = this.random.nextGaussian() * 0.02;
                    double d1 = this.random.nextGaussian() * 0.02;
                    double d2 = this.random.nextGaussian() * 0.02;
                    this.level().addParticle(MotleyFinsParticles.WHITE_SAND, this.getX(), this.getY(), this.getZ(), d0, d1, d2);
                }
            }
        } else {
            if (this.sandDroppingCooldown > 0) {
                --this.sandDroppingCooldown;
            }
        }

        if (item != null) {
            if (this.ticksAfterLastSandDrop == 10) {
                double d0 = this.random.nextGaussian() * 0.02;
                double d1 = this.random.nextGaussian() * 0.02;
                double d2 = this.random.nextGaussian() * 0.02;
                item.level().addParticle(MotleyFinsParticles.WHITE_SAND, item.getX(), item.getY(), item.getZ(), d0, d1, d2);
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
        output.store("Variant", Variant.CODEC, this.getVariant());
        output.putBoolean("IsDroppingSand", this.entityData.get(IS_DROPPING_SAND));
        output.putInt("SandDroppingTicks", this.sandDroppingTicks);
        output.putInt("SandDroppingCooldown", this.sandDroppingCooldown);
    }

    public void readAdditionalSaveData(@NotNull ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setVariant(input.read("Variant", Variant.CODEC).orElse(Variant.RED));
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
        return this.isInWater() && !(this.isDroppingSand()) && (this.sandDroppingCooldown == 0);
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
        }  else {
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
            this.setVariant(Variant.byId(tag.getIntOr("Variant", 0)));
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

    public void setVariant(Variant variant) {
        this.entityData.set(DATA_TYPE_ID, variant.id);
    }

    public Variant getVariant() {
        return Variant.byId(this.entityData.get(DATA_TYPE_ID));
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
        RandomSource randomSource = level.getRandom();

        Variant parrotfishVariant;
        if (spawnGroupData instanceof ParrotfishGroupData parrotfishGroupData) {
            parrotfishVariant = parrotfishGroupData.variant;
        } else if ((double)randomSource.nextFloat() < 0.75) {
            parrotfishVariant = Util.getRandom(COMMON_VARIANTS, randomSource);
            spawnGroupData = new ParrotfishGroupData(this, parrotfishVariant);
        } else {
            this.isSchool = false;
            parrotfishVariant = Variant.byId(randomSource.nextInt((Variant.values().length) + 1));
        }

        this.setVariant(parrotfishVariant);
        return spawnGroupData;
    }

    public static boolean checkParrotfishSpawnRules(EntityType<@NotNull Parrotfish> parrotfish, LevelAccessor level, EntitySpawnReason reason, BlockPos blockPos, RandomSource randomSource) {
        return (level.getFluidState(blockPos.below()).is(FluidTags.WATER) || level.getBlockState(blockPos.below()).is(BlockTags.CORAL_BLOCKS)) &&
                (level.getFluidState(blockPos).is(FluidTags.WATER) || level.getBlockState(blockPos).is(BlockTags.CORALS)) &&
                level.getFluidState(blockPos.above()).is(FluidTags.WATER) &&
                WaterAnimal.checkSurfaceWaterAnimalSpawnRules(parrotfish, level, reason, blockPos, randomSource);
    }


    static class ParrotfishGroupData extends SchoolSpawnGroupData {
        final Variant variant;

        ParrotfishGroupData(Parrotfish parrotfish, Variant variant) {
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
        STOPLIGHT(7, "stoplight"),
        REDBAND(8, "redband");

        private static final IntFunction<Variant> BY_ID = ByIdMap.sparse(Variant::id, values(), RED);
        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
        public static final StreamCodec<@NotNull ByteBuf, @NotNull Variant> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Variant::id);

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

        public static Variant byId(int pId) {
            return BY_ID.apply(pId);
        }

        public record Id(int id) {
            public static final Codec<Id> CODEC = Codec.INT.xmap(Id::new, Id::id);

            public int id() {
                return this.id;
            }

        }
    }

}
