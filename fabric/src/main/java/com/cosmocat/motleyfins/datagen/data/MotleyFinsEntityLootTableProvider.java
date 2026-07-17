package com.cosmocat.motleyfins.datagen.data;

import com.cosmocat.motleyfins.entity.MotleyFinsEntities;
import com.cosmocat.motleyfins.item.MotleyFinsItems;
import com.google.common.collect.Sets;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider;
import net.fabricmc.fabric.impl.datagen.loot.FabricLootTableProviderImpl;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class MotleyFinsEntityLootTableProvider extends EntityLootSubProvider implements FabricLootTableProvider {
    private final FabricDataOutput output;
    private final CompletableFuture<HolderLookup.Provider> registryLookupFuture;
    private final Set<ResourceLocation> excludedFromStrictValidation = new HashSet<>();

    public MotleyFinsEntityLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(FeatureFlagSet.of(), registryLookup.join());
        this.output = dataOutput;
        this.registryLookupFuture = registryLookup;
    }

    @Override
    public void generate() {
        this.add(MotleyFinsEntities.PARROTFISH, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(MotleyFinsItems.PARROTFISH).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.BONE_MEAL)).when(LootItemRandomChanceCondition.randomChance(0.05F))));
    }

    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
        this.generate();

        for (Map.Entry<EntityType<?>, Map<ResourceKey<LootTable>, LootTable.Builder>> entry : map.entrySet()) {
            ResourceKey<LootTable> registryKey = entry.getKey().getDefaultLootTable();

            if (registryKey == BuiltInLootTables.EMPTY) {
                continue;
            }

            biConsumer.accept(registryKey, entry.getValue().get(registryKey));
        }

        if (output.isStrictValidationEnabled()) {
            Set<ResourceLocation> missing = Sets.newHashSet();

            for (ResourceLocation entityId : BuiltInRegistries.ENTITY_TYPE.keySet()) {
                if (entityId.getNamespace().equals(output.getModId())) {
                    ResourceKey<LootTable> entityLootTableId = BuiltInRegistries.ENTITY_TYPE.get(entityId).getDefaultLootTable();

                    if (entityLootTableId.location().getNamespace().equals(output.getModId())) {
                        if (!map.containsKey(entityLootTableId)) {
                            missing.add(entityId);
                        }
                    }
                }
            }

            missing.removeAll(excludedFromStrictValidation);

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing loot table(s) for %s".formatted(missing));
            }
        }
    }

    public void excludeFromStrictValidation(EntityType<?> entityType) {
        excludedFromStrictValidation.add(BuiltInRegistries.ENTITY_TYPE.getKey(entityType));
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return FabricLootTableProviderImpl.run(output, this, LootContextParamSets.ENTITY, this.output, registryLookupFuture);
    }

    @Override
    public String getName() {
        return "Entity Loot Tables";
    }
}
