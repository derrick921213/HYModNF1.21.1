package huanyu_mod.dataGenerator.provider;

import huanyu_mod.core.IHYEng;
import huanyu_mod.world.dimension.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class dimension extends DatapackBuiltinEntriesProvider {
    public dimension(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(IHYEng.MOD_ID));
    }

    public final static RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            /*
            .add(Registries.DIMENSION_TYPE, debug_dim00::setDimensionType)
            .add(Registries.LEVEL_STEM, debug_dim00::setLevelStem)
            */
            .add(Registries.DIMENSION_TYPE, context -> {
                debugDim00.setDimensionType(context);
                debugDim01.setDimensionType(context);
                //debug_dim02.setDimensionType(context);
            })
            .add(Registries.LEVEL_STEM, context -> {
                debugDim00.setLevelStem(context);
                debugDim01.setLevelStem(context);
            });
}
