package com.huanyu_mod.procedures;

import com.huanyu_mod.HuanYuMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = HuanYuMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class tick {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
        CompoundTag nbt = serverPlayer.getPersistentData();
        if (!nbt.contains("hyd", CompoundTag.TAG_COMPOUND)) nbt.put("hyd", new CompoundTag());
        nbt = nbt.getCompound("hyd");
        fly.tick(serverPlayer, nbt);
        test.tick(serverPlayer, nbt);
    }
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        /*Scoreboard scoreboard = event.level.getScoreboard();
        Objective objective = scoreboard.getObjective("hyscoredata");
        if (objective == null)
            objective = scoreboard.addObjective(
                    "hyscoredata",
                    ObjectiveCriteria.DUMMY,
                    Component.literal("hyscoredata"), // 顯示名稱
                    ObjectiveCriteria.RenderType.INTEGER
            );*/
    }
}
