package com.huanyu_mod.dataGenInModding;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.world.dimension.debug_dim00;
import com.huanyu_mod.world.dimension.debug_dim01;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class worldProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, context -> {
                debug_dim00.setDimensionType(context);
                debug_dim01.setDimensionType(context);
            })
            .add(Registries.LEVEL_STEM, context -> {
                debug_dim00.setLevelStem(context);
                debug_dim01.setLevelStem(context);
            });
            /*.add(Registries.DIMENSION_TYPE, debug_dim00::setDimensionType)
            .add(Registries.LEVEL_STEM, debug_dim00::setLevelStem)*/

    public worldProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(HuanYuMod.MOD_ID));
    }
}
