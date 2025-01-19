package com.huanyu_mod.world.dimension;

import com.huanyu_mod.HuanYuMod;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class debug_dim00 {
    private static final String DIM_NAME = HuanYuMod.getCurrentClassName();
    public static final ResourceKey<LevelStem> LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, DIM_NAME));
    public static final ResourceKey<Level> DIMENSION_LEVEL = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, (DIM_NAME + "_level")));
    public static final ResourceKey<DimensionType> DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, (DIM_NAME + "_type")));

    public static void setDimensionType(BootstrapContext<DimensionType> context) {
        context.register(DIMENSION_TYPE, new DimensionType(
                OptionalLong.of(6000),
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
        HolderGetter<NoiseGeneratorSettings> noiseSet = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeSet.getOrThrow(Biomes.THE_VOID)),
                noiseSet.getOrThrow(NoiseGeneratorSettings.AMPLIFIED)
        );

        NoiseBasedChunkGenerator noiseChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeSet.getOrThrow(Biomes.THE_VOID)),
                                Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeSet.getOrThrow(Biomes.THE_VOID))
                        ))),
                noiseSet.getOrThrow(NoiseGeneratorSettings.AMPLIFIED)
        );

        LevelStem levelStem = new LevelStem(dimType.getOrThrow(debug_dim00.DIMENSION_TYPE), wrappedChunkGenerator);
        context.register(LEVEL_STEM, levelStem);
    }
}
