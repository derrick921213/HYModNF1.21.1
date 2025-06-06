package huanyu_mod.world.dimension;

import huanyu_mod.core.IHYEng;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
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

public class debugDim00 {
    public final static String CLASS_NAME = "debugDim00";
    public final static ResourceKey<LevelStem> LEVEL_STEM_RESOURCE_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            IHYEng.makeRL(CLASS_NAME));
    public final static ResourceKey<Level> LEVEL_RESOURCE_KEY = ResourceKey.create(Registries.DIMENSION,
            IHYEng.makeRL(CLASS_NAME + "_level"));
    public final static ResourceKey<DimensionType> DIMENSION_TYPE_RESOURCE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE,
            IHYEng.makeRL(CLASS_NAME + "_type"));
    public final static DimensionType DE_DIMENSION_TYPE = new DimensionType(
            OptionalLong.of(6100),
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
    );

    public static void setDimensionType(BootstrapContext<DimensionType> context) {
        context.register(DIMENSION_TYPE_RESOURCE_KEY, DE_DIMENSION_TYPE);
    }
    public static void setLevelStem(BootstrapContext<LevelStem> context) {
        HolderGetter<DimensionType> dimensionTypeHG = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<Biome> biomeHG = context.lookup(Registries.BIOME);
        HolderGetter<NoiseGeneratorSettings> noiseGenSHG = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeHG.getOrThrow(Biomes.THE_VOID)),
                noiseGenSHG.getOrThrow(NoiseGeneratorSettings.AMPLIFIED)
        );
        NoiseBasedChunkGenerator noiseChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeHG.getOrThrow(Biomes.THE_VOID)),
                                Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeHG.getOrThrow(Biomes.THE_VOID))
                        ))),
                noiseGenSHG.getOrThrow(NoiseGeneratorSettings.AMPLIFIED)
        );
        context.register(LEVEL_STEM_RESOURCE_KEY, new LevelStem(
                dimensionTypeHG.getOrThrow(DIMENSION_TYPE_RESOURCE_KEY),
                wrappedChunkGenerator
        ));
    }
}
