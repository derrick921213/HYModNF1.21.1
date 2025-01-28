package com.huanyu_mod.core.register;

import com.huanyu_mod.command.*;
import com.huanyu_mod.core.IHYEng;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = IHYEng.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class HYCommands {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("/hyc")
                .then(testCmd.register())
                .then(dim_teleport.register())
                .then(fly_trigger.register())
        );
    }
}
