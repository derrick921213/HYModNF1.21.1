package huanyu_mod.world.dimension;

import huanyu_mod.core.IHYEng;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
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

public class debugDim01 {
    public final static String CLASS_NAME = "debugDim01";
    public final static ResourceKey<LevelStem> LEVEL_STEM_RESOURCE_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            IHYEng.makeRL(CLASS_NAME));
    public final static ResourceKey<Level> LEVEL_RESOURCE_KEY = ResourceKey.create(Registries.DIMENSION,
            IHYEng.makeRL(CLASS_NAME + "_level"));
    public final static ResourceKey<DimensionType> DIMENSION_TYPE_RESOURCE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE,
            IHYEng.makeRL(CLASS_NAME + "_type"));
    public final static DimensionType DE_DIMENSION_TYPE = new DimensionType(
            OptionalLong.of(6101),
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
    public final static List<FlatLayerInfo> FLAT_LAYER_INFOS = List.of(
            new FlatLayerInfo(1, Blocks.BEDROCK)
    );

    public static void setDimensionType(BootstrapContext<DimensionType> context) {
        context.register(DIMENSION_TYPE_RESOURCE_KEY, DE_DIMENSION_TYPE);
    }
    public static void setLevelStem(BootstrapContext<LevelStem> context) {
        HolderGetter<DimensionType> dimensionTypeHG = context.lookup(Registries.DIMENSION_TYPE);

        HolderGetter<Biome> biomeHG = context.lookup(Registries.BIOME);
        Holder<Biome> biomeHolder = biomeHG.getOrThrow(Biomes.THE_VOID);

        HolderGetter<StructureSet> structureSetHG = context.lookup(Registries.STRUCTURE_SET);
        Optional<HolderSet<StructureSet>> structures = Optional.empty();

        FlatLevelGeneratorSettings flatSettings = new FlatLevelGeneratorSettings(Optional.empty(), biomeHolder, List.of());
        flatSettings = flatSettings.withBiomeAndLayers(FLAT_LAYER_INFOS, structures, biomeHolder);
        FlatLevelSource flatChunkGenerator = new FlatLevelSource(flatSettings);
        context.register(LEVEL_STEM_RESOURCE_KEY, new LevelStem(
                dimensionTypeHG.getOrThrow(DIMENSION_TYPE_RESOURCE_KEY),
                flatChunkGenerator
        ));
    }
}
