package com.huanyu_mod.dataGenInGame;

import net.minecraft.WorldVersion;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.storage.DataVersion;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class CustomWorldVersion implements WorldVersion {
    private final String name;
    private final String id;
    private final int protocolVersion;

    public CustomWorldVersion(String name, String id, int protocolVersion) {
        this.name = name;
        this.id = id;
        this.protocolVersion = protocolVersion;
    }

    @Override
    public @NotNull DataVersion getDataVersion() {
        return new DataVersion(protocolVersion, id);
    }
    @Override
    public @NotNull String getId() {
        return id;
    }
    @Override
    public @NotNull String getName() {
        return name;
    }
    @Override
    public int getProtocolVersion() {
        return protocolVersion;
    }
    @Override
    public int getPackVersion(@NotNull PackType packType) {
        return packType == PackType.SERVER_DATA ? 10 : 7; // 示例數據版本
    }
    @Override
    public @NotNull Date getBuildTime() {
        return new Date();
    }
    @Override
    public boolean isStable() {
        return true; // 默認假設為穩定版本
    }
}