package com.huanyu_mod.core.register;

import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.world.dimension.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.dimension.DimensionType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class HYDimensions {
    private static final String CLASS_NAME = HYEng.getCurrentClassName();
    public static final DeferredRegister<DimensionType> DR_TYPE =
            DeferredRegister.create(Registries.DIMENSION_TYPE, HYEng.MOD_ID);

    public static final DeferredHolder<DimensionType, DimensionType> DEBUG_DIM02 =
            DR_TYPE.register("debug_dim02", () -> debug_dim02.DE_DIMENSION_TYPE);

    public static Collection<DeferredHolder<DimensionType, ? extends DimensionType>> register(IEventBus eventBus) {
        DR_TYPE.register(eventBus);
        Collection<DeferredHolder<DimensionType, ? extends DimensionType>> getEntries = DR_TYPE.getEntries();
        System.out.println(CLASS_NAME + " registered: " + getEntries);
        return getEntries;
    }

    public static Collection<DeferredHolder<DimensionType, ? extends DimensionType>> register(MinecraftServer server) {
        ResourceLocation debugDimensionId = HYEng.makeRL("debug_dim02");

        Registry<DimensionType> dimensionTypeRegistry =
                server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
        if (dimensionTypeRegistry.containsKey(debugDimensionId)) {
            System.out.println(CLASS_NAME + ": Dimension " + debugDimensionId + " is already registered.");
            return null;
        }

        DimensionType debugDimensionType = debug_dim02.DE_DIMENSION_TYPE;
        //DR_DIMENSION_TYPE.register("debug_dim02", () -> debug_dim02.DE_DIMENSION_TYPE);
        net.minecraft.core.Registry.register(dimensionTypeRegistry, debugDimensionId, debugDimensionType);

        Collection<DeferredHolder<DimensionType, ? extends DimensionType>> getEntries = DR_TYPE.getEntries();
        System.out.println(CLASS_NAME + " registered: " + getEntries);
        return getEntries;
    }

    public static ServerLevel createCustomDimension(MinecraftServer server, ResourceLocation dimensionId) {
        /*ResourceLocation debugDimensionId = ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, "debug_dim02");
        ResourceKey<Level> dimensionKey = ResourceKey.create(Registries.DIMENSION, debugDimensionId);
        if (server.getLevel(dimensionKey) != null) {
            System.out.println("Dimension " + debugDimensionId + " already exists! Returning existing dimension...");
            return server.getLevel(dimensionKey);
        }

        RegistryAccess registryAccess = server.registryAccess();
        HolderGetter<DimensionType> dimensionTypeHolderGetter = registryAccess
                .registryOrThrow(Registries.DIMENSION_TYPE)
                .;
        Holder<DimensionType> dimensionTypeHolder = dimensionTypeHolderGetter
                .getOrThrow(debug_dim02.DE_DIMENSION_TYPE). // 使用 Overworld 作為基礎維度類型（根據需要修改）
                        orElseThrow();

        HolderGetter<ChunkGenerator> chunkGeneratorHolderGetter = registryAccess
                .registryOrThrow(RegistryAccess.CHUNK_GENERATOR)
                .asHolderGetter();
        Holder<ChunkGenerator> chunkGeneratorHolder = chunkGeneratorHolderGetter
                .getOrThrow(WorldPreset.NORMAL). // 使用普通世界生成器（根據需要修改）
                        orElseThrow();

        // 創建新的 LevelStem（維度配置）
        LevelStem newLevelStem = new LevelStem(dimensionTypeHolder, chunkGeneratorHolder);

        // 將維度添加到伺服器的 LevelManager
        server.overworld().getServer().createLevel(
                resourceLevelStem.newInstance()
        );*/


        return null;
    }
}
