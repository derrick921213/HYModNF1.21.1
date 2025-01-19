package com.huanyu_mod.world.dimension;

import com.huanyu_mod.HuanYuMod;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.flat.FlatLayerInfo;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@EventBusSubscriber(modid = HuanYuMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class debug_dim03 {
    public static final String DIM_NAME = HuanYuMod.getCurrentClassName();
    public static final ResourceKey<LevelStem> LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, DIM_NAME));
    public static final ResourceKey<Level> DIMENSION_LEVEL = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, (DIM_NAME + "_level")));
    public static final ResourceKey<DimensionType> DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, (DIM_NAME + "_type")));

    public static void setDimensionType(BootstrapContext<DimensionType> context) {
        context.register(DIMENSION_TYPE, new DimensionType(
                OptionalLong.of(6103),
                false,
                false,
                false,
                false,
                1.0,
                true,
                true,
                0,
                512,
                512,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                1.0f,
                new DimensionType.MonsterSettings(true, false, ConstantInt.of(0), 0)
        ));
    }
    public static void setLevelStem(BootstrapContext<LevelStem> context) {
        HolderGetter<DimensionType> dimType = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<Biome> biomeSet = context.lookup(Registries.BIOME);
        HolderGetter<StructureSet> structureSet = context.lookup(Registries.STRUCTURE_SET);

        List<FlatLayerInfo> layers = List.of(
                new FlatLayerInfo(1, Blocks.BEDROCK)
        );

        Holder<Biome> biomeHolder = biomeSet.getOrThrow(Biomes.THE_VOID);
        Optional<HolderSet<StructureSet>> structures = Optional.empty();

        FlatLevelGeneratorSettings flatSettings = new FlatLevelGeneratorSettings(structures, biomeHolder, List.of());
        flatSettings = flatSettings.withBiomeAndLayers(layers, structures, biomeHolder);
        FlatLevelSource flatChunkGenerator = new FlatLevelSource(flatSettings);
        LevelStem levelStem = new LevelStem(dimType.getOrThrow(debug_dim00.DIMENSION_TYPE), flatChunkGenerator);

        context.register(LEVEL_STEM, levelStem);
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        MinecraftServer server = player.server;
        if (server == null) return;

        RegistryAccess registryAccess = (RegistryAccess) server.registryAccess();

        Registry<LevelStem> levelStemRegistry = registryAccess.registryOrThrow(Registries.LEVEL_STEM);
        if (levelStemRegistry.containsKey(debug_dim03.LEVEL_STEM.location())) {
            System.out.println("Dimension " + debug_dim03.LEVEL_STEM.location() + " already registered.");
            return;
        }

        DimensionType dimensionType = new DimensionType(
                OptionalLong.of(6103),
                false, false, false, false,
                1.0, true, true,
                0, 512, 512,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                1.0f,
                new DimensionType.MonsterSettings(true, false, ConstantInt.of(0), 0)
        );
        Registry<DimensionType> dimensionTypeRegistry = registryAccess.registryOrThrow(Registries.DIMENSION_TYPE);
        //dimensionTypeRegistry.register(DIMENSION_TYPE, dimensionType, Lifecycle.stable());

        System.out.println("Successfully registered dimension: " + debug_dim03.LEVEL_STEM.location());
    }
}
