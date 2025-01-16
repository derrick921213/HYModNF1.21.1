package com.huanyu_mod.dataGenInGame;

import com.huanyu_mod.HuanYuMod;
import net.minecraft.SharedConstants;
import net.minecraft.WorldVersion;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelResource;

import java.nio.file.Files;
import java.nio.file.Path;

public class DataGenInGaming {
    public static void generateData(MinecraftServer server) {
        try {
            Path dataPackPath = server.getWorldPath(LevelResource.DATAPACK_DIR);
            Files.createDirectories(dataPackPath
                    .resolve(HuanYuMod.MOD_ID)
                    .resolve("data")
                    .resolve(HuanYuMod.MOD_ID));
            WorldVersion worldVersion = new CustomWorldVersion(
                    server.getServerVersion(),
                    server.getServerVersion(),
                    SharedConstants.getProtocolVersion()
            );
            //DataGenerator generator = new DataGenerator(outputPath, worldVersion, false);
            //PackOutput packOutput = generator.getPackOutput();
            /*ExistingFileHelper existingFileHelper = new ExistingFileHelper(
                    Collections.emptyList(), new HashSet<>().add(), false, null, null);
            CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

            tagProBlock _tagProBlock = generator.addProvider(true, new tagProBlock(packOutput, generator.getLookupProvider(), existingFileHelper));
            generator.addProvider(true, new tagProItem(packOutput, generator.getLookupProvider(), _tagProBlock.contentsGetter(), existingFileHelper));
            generator.addProvider(true, new LootTableProvider(
                    packOutput, Collections.emptySet(),
                    List.of(new LootTableProvider.SubProviderEntry(lootTableProBlock::new, LootContextParamSets.BLOCK)),
                    generator.getLookupProvider()));
            generator.addProvider(true, new recipePro(packOutput, generator.getLookupProvider()));
            generator.addProvider(true, new worldPro(packOutput, generator.getLookupProvider()));

            generator.run();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}