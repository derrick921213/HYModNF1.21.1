package com.huanyu_mod.core;

import com.huanyu_mod.core.register.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;

public abstract class HYEngBase implements HYEng{
    public static Object getServer;
    static HYEngBase INSTANCE;
    public HYEngBase(IEventBus modEventBus, ModContainer modContainer) {
        if (INSTANCE != null) {
            throw new IllegalStateException();
        }
        INSTANCE = this;

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        HYItems.register(modEventBus);
        HYBlockEntities.register(modEventBus);
        HYBlocks.register(modEventBus);
        modEventBus.addListener(HYTabs::addCreative);
        HYTabs.register(modEventBus);
        HYMenus.register(modEventBus);
        //_ModDimensions.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {}

    @Nullable
    @Override
    public MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

    @Override
    public Collection<ServerPlayer> getPlayers() {
        var server = getServer();
        if (server == null) {
            return Collections.emptyList();
        } else {
            return server.getPlayerList().getPlayers();
        }
    }
}
