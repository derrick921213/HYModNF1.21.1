package com.huanyu_mod.procedure;

import com.huanyu_mod.core.HYEng;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.OptionalLong;

public class mymod {
    public static final String DIM_NAME = HYEng.getCurrentClassName();
    public static final ResourceKey<LevelStem> LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM,
            HYEng.makeRL(DIM_NAME));
    public static final ResourceKey<Level> DIMENSION_LEVEL = ResourceKey.create(Registries.DIMENSION,
            HYEng.makeRL((DIM_NAME + "_level")));
    public static final ResourceKey<DimensionType> DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            HYEng.makeRL((DIM_NAME + "_type")));

    public static void onServerStarting(RegisterEvent event) {
        DimensionType customDimensionType = new DimensionType(
                OptionalLong.of(6200),
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

        if (event.getRegistryKey() == Registries.DIMENSION_TYPE) {
            event.register(Registries.DIMENSION_TYPE, helper -> {helper.register(
                    HYEng.makeRL("custom_dimension_type"), customDimensionType);
            });
            System.out.println(mymod.class.getName() + " Output: Generate Success");
        }

        /*MinecraftServer server = event.getServer();

        // 動態註冊 DimensionType
        Registry<DimensionType> dimensionTypeRegistry =
                server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
        dimensionTypeRegistry.registerOrOverride(
                DIMENSION_TYPE.location(),
                customDimensionType,
                Lifecycle.stable()
        );*/
    }
}

