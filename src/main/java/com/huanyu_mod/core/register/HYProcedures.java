package com.huanyu_mod.core.register;

import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.procedure.*;
import com.mojang.authlib.GameProfile;
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

@EventBusSubscriber(modid = HYEng.MOD_ID)
public class HYProcedures {
    public static final String CLASS_NAME = HYEng.getCurrentClassName();
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {}
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            operator.giveOperator(serverPlayer);
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