package com.huanyu_mod.procedure;

import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.core.HYEngBase;
import com.huanyu_mod.core.HYEngServer;
import com.huanyu_mod.core.register.HYDimensions;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.phys.Vec3;
import java.nio.file.Path;
import java.util.Map;
import java.util.logging.Logger;

public class test {
    public static final String CLASS_NAME = HYEng.getCurrentClassName();
    public static void tick(ServerPlayer player, CompoundTag nbt) {

    }
    public static void executeU(Level level, Vec3 blockPos, Player player) {
        System.out.println(CLASS_NAME + " Output: " + level);
        System.out.println(CLASS_NAME + " Output: " + blockPos);
        System.out.println(CLASS_NAME + " Output: " + player.getClass());
        if (level instanceof ServerLevel serverLevel) {
            Path dataPackPath = serverLevel.getServer().getWorldPath(LevelResource.DATAPACK_DIR);
            System.out.println(CLASS_NAME + " Output: " + dataPackPath.toAbsolutePath());
        }
        Path dataPackPath = HYEng.instance().getServer().getWorldPath(LevelResource.DATAPACK_DIR);
        System.out.println(CLASS_NAME + " Output: " + dataPackPath.toAbsolutePath());
    }
    public static void executeA(CommandContext<CommandSourceStack> context) {
        MinecraftServer server = context.getSource().getServer();

        HYDimensions.register(server);

        Registry<DimensionType> dimensionTypeRegistry =
                server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
        for (Map.Entry<ResourceKey<DimensionType>, DimensionType> dimensionEntry : dimensionTypeRegistry.entrySet()) {
            System.out.println(CLASS_NAME + " Dimension Key Found: " + dimensionEntry.getKey().location());
        }
        for (ServerLevel level : server.getAllLevels()) {
            System.out.println(CLASS_NAME + " Loaded Dimension: " + level.dimension().location());
        }
    }
    private static void temp() {
        /*PacketDistributor.sendToServer(new MyData0("Joe"));
        if (context.getSource().getPlayer() instanceof ServerPlayer serverPlayer) {
            System.out.println(test.class.getName() + " Output: " + serverPlayer.getUUID());
            System.out.println(test.class.getName() + " Output: " + serverPlayer.getUUID().getMostSignificantBits());
            System.out.println(test.class.getName() + " Output: " + serverPlayer.getUUID().getLeastSignificantBits());
            System.out.println(test.class.getName() + " Output: " + serverPlayer.getStringUUID());
        }*/
        /*System.out.println(test.class.getName() + " Output: " +
                context.getSource().getServer().getServerDirectory().toAbsolutePath());

        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, "tags");
        Path dataPackPath = context.getSource().getServer().getWorldPath(LevelResource.DATAPACK_DIR);
        try {

            Resource resource = context.getSource().getServer().getResourceManager().getResource(resourceLocation).get();
            Path targetFile = targetFolder.resolve("new");
            System.out.println(test.class.getName() + " Output: " + targetFile);
            InputStream inputStream = resource.open();
            OutputStream outputStream = Files.newOutputStream(targetFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            inputStream.transferTo(outputStream);

            Path targetFolder = dataPackPath.resolve(HuanYuMod.MOD_ID).resolve("data").resolve(HuanYuMod.MOD_ID);
            Files.createDirectories(targetFolder);
            Path targetFile = targetFolder.resolve("new");
            //DataGenerator generator = new DataGenerator(outputPath, worldVersion, false);
            //Files.copy(resource.open(), targetFolder, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(test.class.getName() + " Output: " + targetFolder);
        } catch (IOException e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "An error occurred at ", e);
        }*/
    }
}
