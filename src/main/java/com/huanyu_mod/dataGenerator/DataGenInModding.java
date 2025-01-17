package com.huanyu_mod.dataGenerator;

import com.huanyu_mod.HuanYuMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DataGenInModding {
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        tagProBlock _tagProBlock =
                generator.addProvider(event.includeServer(), new tagProBlock(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new tagProItem(packOutput, lookupProvider, _tagProBlock.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new LootTableProvider(
                packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(lootTableProBlock::new, LootContextParamSets.BLOCK)),
                lookupProvider));
        generator.addProvider(event.includeServer(), new recipePro(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new worldPro(packOutput, lookupProvider));
    }
}