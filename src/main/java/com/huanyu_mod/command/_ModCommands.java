package com.huanyu_mod.command;

import com.huanyu_mod.core.HYEng;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = HYEng.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class _ModCommands {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("/hyc")
                .then(testCmd.register())
                .then(dimTpCmd.register())
                .then(flyCmd.register())
        );
    }
}
