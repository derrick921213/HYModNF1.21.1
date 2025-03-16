package huanyu_mod.core.register;

import huanyu_mod.core.IHYEng;
import huanyu_mod.procedure.*;
import huanyu_mod.command.*;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = IHYEng.MOD_ID)
public class HYProcedures {
    public final static String CLASS_NAME = "HYProcedures";
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
            flightTrigger.tick(serverPlayer);
            test.tick(serverPlayer);
        }
    }
}