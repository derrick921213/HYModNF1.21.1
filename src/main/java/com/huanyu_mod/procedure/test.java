package com.huanyu_mod.procedure;

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
        MinecraftServer server = context.getSource().getServer();
        Registry<DimensionType> dimensionTypeRegistry =
                server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
        for (Map.Entry<ResourceKey<DimensionType>, DimensionType> dimensionEntry : dimensionTypeRegistry.entrySet()) {
            ResourceKey<DimensionType> dimensionKey = dimensionEntry.getKey();
            DimensionType dimension = dimensionEntry.getValue();
            System.out.println(test.class.getName() + " Output: " + dimension);
        }
        for (ServerLevel level : server.getAllLevels()) {
            System.out.println("Loaded Dimension: " + level.dimension().location());
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
