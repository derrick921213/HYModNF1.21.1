package com.huanyu_mod.worldgen.dimension;

import com.huanyu_mod.HuanYuMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public class debug_dim02 {
    private static final String dim_name = HuanYuMod.getCurrentClassName();
    public static final ResourceKey<LevelStem> LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, dim_name));
    public static final ResourceKey<Level> DIMENSION_LEVEL = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, (dim_name + "_level")));
    public static final ResourceKey<DimensionType> DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, (dim_name + "_type")));

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
        HolderGetter<StructureSet> structureSet = context.lookup(Registries.STRUCTURE_SET);
        List<FlatLayerInfo> layers = List.of(
                new FlatLayerInfo(1, Blocks.BEDROCK)
        );
        Optional<HolderSet<StructureSet>> structures = Optional.empty();
        Holder<Biome> biomeHolder = biomeSet.getOrThrow(Biomes.THE_VOID);
        FlatLevelGeneratorSettings flatSettings = new FlatLevelGeneratorSettings(Optional.empty(), biomeHolder, List.of());
        flatSettings = flatSettings.withBiomeAndLayers(layers, structures, biomeHolder);

        FlatLevelSource flatChunkGenerator = new FlatLevelSource(flatSettings);
        LevelStem levelStem = new LevelStem(dimType.getOrThrow(debug_dim00.DIMENSION_TYPE), flatChunkGenerator);
        context.register(LEVEL_STEM, levelStem);
    }
}
