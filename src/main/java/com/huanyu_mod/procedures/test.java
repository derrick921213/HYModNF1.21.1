package com.huanyu_mod.procedures;

import com.huanyu_mod.HuanYuMod;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.storage.LevelResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {
    private static final Logger LOGGER = Logger.getLogger(test.class.getName());
    public static void tick(ServerPlayer player, CompoundTag nbt) {

    }
    public static void executeU(UseOnContext context) {
        System.out.println(test.class.getName() + " Output: " + context);
    }
    public static void executeA(CommandContext<CommandSourceStack> context) {
        System.out.println(test.class.getName() + " Output: " +
                context.getSource().getServer().getServerDirectory().toAbsolutePath());

        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, "tags/block/mineable/pickaxe.json");
        Path dataPackPath = context.getSource().getServer().getWorldPath(LevelResource.DATAPACK_DIR);
        try {
            Resource resource = context.getSource().getServer().getResourceManager().getResource(resourceLocation).get();
            Path targetFolder = dataPackPath.resolve(HuanYuMod.MOD_ID).resolve("data").resolve(HuanYuMod.MOD_ID);
            Files.createDirectories(targetFolder);
            Path targetFile = targetFolder.resolve("new" + ".json");
            InputStream inputStream = resource.open();
            OutputStream outputStream = Files.newOutputStream(targetFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            inputStream.transferTo(outputStream);

            //Files.copy(resource.open(), targetFolder, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(test.class.getName() + " Output: " + targetFolder);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred at ", e);
        }
    }
}
