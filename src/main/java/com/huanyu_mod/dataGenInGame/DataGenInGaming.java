package com.huanyu_mod.dataGenInGame;

/*@EventBusSubscriber(modid = HuanYuMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenInGaming {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        /*tagProviderBlock _tagProBlock =
                generator.addProvider(event.includeServer(), new tagProviderBlock(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new tagProviderItem(packOutput, lookupProvider, _tagProBlock.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new LootTableProvider(
                packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(lootTableProBlock::new, LootContextParamSets.BLOCK)),
                lookupProvider));
        generator.addProvider(event.includeServer(), new recipeProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new worldProvider(packOutput, lookupProvider));
    }
}*/