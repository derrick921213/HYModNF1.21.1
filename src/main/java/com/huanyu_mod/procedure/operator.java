package com.huanyu_mod.procedure;

import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.core.file_action.json;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

import java.util.List;
import java.util.UUID;

public class operator {
    public static final String CLASS_NAME = HYEng.getCurrentClassName();
    public static void giveOperator(ServerPlayer player) {
        GameProfile profile = player.getGameProfile();
        MinecraftServer server = player.getServer();
        if (server == null) return;
        List<String> operatorList = json.loadJsonList(HYEng.makeRL("operator.json"), server);
        for (String op : operatorList) {
            try {
                var _op = UUID.fromString(op);
                if (profile.getId().equals(_op)) {
                    PlayerList playerList = server.getPlayerList();
                    if (!playerList.isOp(profile)) {
                        playerList.op(profile);
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(CLASS_NAME + " Is not UUID: " + op);
            }
        }
    }

}
