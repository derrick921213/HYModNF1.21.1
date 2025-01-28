package com.huanyu_mod.core.register;

import com.huanyu_mod.core.IHYEng;
import com.huanyu_mod.world.dimension.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.*;

public class HYDimensions {
    private final static String CLASS_NAME = IHYEng.getCurrentClassName();
    public final static DeferredRegister<DimensionType> DR_TYPE =
            DeferredRegister.create(Registries.DIMENSION_TYPE, IHYEng.MOD_ID);

    public final static DeferredHolder<DimensionType, DimensionType> DEBUG_DIM02 =
            DR_TYPE.register("debug_dim02", () -> debug_dim02.DE_DIMENSION_TYPE);

    /*public static Collection<DeferredHolder<DimensionType, ? extends DimensionType>> register(IEventBus eventBus) {
        DR_TYPE.register(eventBus);
        Collection<DeferredHolder<DimensionType, ? extends DimensionType>> getEntries = DR_TYPE.getEntries();
        System.out.println(CLASS_NAME + " registered: " + getEntries);
        return getEntries;
    }*/

    public static Collection<DeferredHolder<DimensionType, ? extends DimensionType>> register(MinecraftServer server) {
        Registry<DimensionType> dimensionTypeRegistry = server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
        ResourceLocation debugDimensionId = IHYEng.makeRL("debug_dim02");
        DimensionType debugDimensionType = debug_dim02.DE_DIMENSION_TYPE;

        if (dimensionTypeRegistry.containsKey(debugDimensionId)) {
            System.out.println(CLASS_NAME + ": Dimension " + debugDimensionId + " is already registered.");
            return null;
        }
        HolderGetter<Biome> biomeHG = server.registryAccess().lookupOrThrow(Registries.BIOME);
        Holder<Biome> biomeHolder = biomeHG.getOrThrow(Biomes.THE_VOID);

        HolderGetter<StructureSet> structureSetHG = server.registryAccess().lookupOrThrow(Registries.STRUCTURE_SET);
        Optional<HolderSet<StructureSet>> structures = Optional.empty();

        FlatLevelGeneratorSettings flatSettings = new FlatLevelGeneratorSettings(Optional.empty(), biomeHolder, List.of());
        flatSettings = flatSettings.withBiomeAndLayers(debug_dim02.FLAT_LAYER_INFOS, structures, biomeHolder);
        FlatLevelSource flatChunkGenerator = new FlatLevelSource(flatSettings);


        Collection<DeferredHolder<DimensionType, ? extends DimensionType>> getEntries = DR_TYPE.getEntries();
        System.out.println(CLASS_NAME + " registered: " + getEntries);
        return getEntries;
    }
}
