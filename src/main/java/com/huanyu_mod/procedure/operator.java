package com.huanyu_mod.procedure;

import com.huanyu_mod.core.IHYEng;
import com.huanyu_mod.core.file_action.json;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

import java.util.List;
import java.util.UUID;

public class operator {
    public final static String CLASS_NAME = IHYEng.getCurrentClassName();
    public static void giveOperator(ServerPlayer player) {
        GameProfile profile = player.getGameProfile();
        MinecraftServer server = player.getServer();
        if (server == null) return;
        List<String> opsList = json.loadJsonListString(IHYEng.makeRL("hy_datas.json"), "opsUUID", server);
        PlayerList playerList = server.getPlayerList();
        for (String operator : opsList) {
            try {
                var uuid = UUID.fromString(operator); //Dont remove
                if (profile.getId().equals(uuid)) {
                    if(!playerList.isOp(profile)) playerList.op(profile);
                    player.displayClientMessage(Component.literal("You are operator"), false);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(CLASS_NAME + " Is not UUID: " + operator);
            }
        }
    }
}
