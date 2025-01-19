package com.huanyu_mod.dataGenInModding;

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

@EventBusSubscriber(modid = HuanYuMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class _DataGenInModding {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        tagProviderBlock tagProviderBlock = generator.addProvider(event.includeServer(), new tagProviderBlock(
                packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new tagProviderItem(
                packOutput, lookupProvider, tagProviderBlock.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new LootTableProvider(
                packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(
                        lootTableProviderBlock::new, LootContextParamSets.BLOCK)),
                lookupProvider));
        generator.addProvider(event.includeServer(), new recipeProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new worldProvider(packOutput, lookupProvider));
    }
}
