package com.huanyu_mod.procedures;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.procedures.test;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.data.DataGenerator;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.phys.Vec3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class test {
    private static final Logger LOGGER = Logger.getLogger(test.class.getName());
    public static void tick(ServerPlayer player, CompoundTag nbt) {

    }
    public static void executeU(Level level, Vec3 blockPos, Player player) {
        System.out.println(test.class.getName() + " Output: " + level);
        System.out.println(test.class.getName() + " Output: " + blockPos);
        System.out.println(test.class.getName() + " Output: " + player.getClass());
        if (level instanceof ServerLevel serverLevel) {
            Path dataPackPath = serverLevel.getServer().getWorldPath(LevelResource.DATAPACK_DIR);
            System.out.println(test.class.getName() + " Output: " + dataPackPath.toAbsolutePath());
        }
        if (player instanceof ServerPlayer serverPlayer) {
            Path dataPackPath = serverPlayer.getServer().getWorldPath(LevelResource.DATAPACK_DIR);
            System.out.println(test.class.getName() + " Output: " + dataPackPath.toAbsolutePath());
        }
    }
    public static void executeA(CommandContext<CommandSourceStack> context) {
        System.out.println(test.class.getName() + " Output: " +
                context.getSource().getServer().getServerDirectory().toAbsolutePath());

        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, "tags");
        Path dataPackPath = context.getSource().getServer().getWorldPath(LevelResource.DATAPACK_DIR);
        try {
            /*
            Resource resource = context.getSource().getServer().getResourceManager().getResource(resourceLocation).get();
            Path targetFile = targetFolder.resolve("new");
            System.out.println(test.class.getName() + " Output: " + targetFile);
            InputStream inputStream = resource.open();
            OutputStream outputStream = Files.newOutputStream(targetFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            inputStream.transferTo(outputStream);
            */
            Path targetFolder = dataPackPath.resolve(HuanYuMod.MOD_ID).resolve("data").resolve(HuanYuMod.MOD_ID);
            Files.createDirectories(targetFolder);
            Path targetFile = targetFolder.resolve("new");
            //DataGenerator generator = new DataGenerator(outputPath, worldVersion, false);
            //Files.copy(resource.open(), targetFolder, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(test.class.getName() + " Output: " + targetFolder);
        } catch (IOException e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "An error occurred at ", e);
        }
    }
}
