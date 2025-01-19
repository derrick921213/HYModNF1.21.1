package com.huanyu_mod.procedure;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;

public class create_new_dimension {
    public create_new_dimension() {
        ResourceKey<DimensionType> CUSTOM_DIMENSION_TYPE = ResourceKey.create(
                Registries.DIMENSION_TYPE,
                ResourceLocation.parse("huanyu_mod:custom_dimension_type1")
        );
    }
    /*public static Holder<DimensionType> getDimensionTypeHolder(MinecraftServer server) {
        return server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE).getHolderOrThrow(Hyperbox.DIMENSION_TYPE_KEY);
    }
    public static DimensionType getDimensionType(MinecraftServer server) {
        return (DimensionType)getDimensionTypeHolder(server).value();
    }
    public static LevelStem createDimension(MinecraftServer server) {
        return new LevelStem(getDimensionTypeHolder(server), new HyperboxChunkGenerator(server));
    }*/
}
