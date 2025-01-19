package com.huanyu_mod.dataGenInGame;

import com.huanyu_mod.HuanYuMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class worldPro extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, context -> {
                debug_dim03.setDimensionType(context);
            })
            .add(Registries.LEVEL_STEM, context -> {
                debug_dim03.setLevelStem(context);
            });

    public worldPro(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(HuanYuMod.MOD_ID));
    }
}
