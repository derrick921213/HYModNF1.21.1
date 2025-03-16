package huanyu_mod.dataGenerator;

import huanyu_mod.core.IHYEng;
import huanyu_mod.dataGenerator.provider.*;
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

@EventBusSubscriber(modid = IHYEng.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class _DataGenInModding {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        tagBlock tagProviderBlock = generator.addProvider(event.includeServer(), new tagBlock(
                packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new tagItem(
                packOutput, lookupProvider, tagProviderBlock.contentsGetter(), existingFileHelper));
        /**/
        generator.addProvider(event.includeServer(), new LootTableProvider(
                packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(
                        lootTableBlock::new, LootContextParamSets.BLOCK)),
                lookupProvider));
        generator.addProvider(event.includeServer(), new recipe(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new dimension(packOutput, lookupProvider));

    }
}
