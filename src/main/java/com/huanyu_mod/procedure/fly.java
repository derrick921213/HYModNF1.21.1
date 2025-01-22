package com.huanyu_mod.procedure;

import com.huanyu_mod.core.HYEng;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class fly {
    private static final String nbtName = "mayfly";

    public static void tick(ServerPlayer player, CompoundTag nbt) {
        if (!nbt.contains(nbtName)) nbt.putBoolean(nbtName, false);
        if (player.isCreative() || player.isSpectator()) return;
        if (nbt.getBoolean(nbtName)) {
            if (!player.getAbilities().mayfly) {
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
                player.displayClientMessage(Component.translatable("message.huanyu_mod.enable_fly"), true);
            }
        } else {
            if (player.getAbilities().mayfly) {
                player.getAbilities().mayfly = false;
                player.onUpdateAbilities();
                player.displayClientMessage(Component.translatable("message.huanyu_mod.disable_fly"), true);
            }
        }
        return;
    }

    public static void execute(CommandContext<CommandSourceStack> context) {
        try {
            if (context.getNodes().stream().anyMatch(node -> node.getNode().getName().equals("players"))) {
                for (Player players : EntityArgument.getPlayers(context, "players")) {
                    if (players.level().isClientSide()) continue;
                    if (players instanceof ServerPlayer player) {
                        CompoundTag nbt = player.getPersistentData().getCompound("hyd");
                        nbt.putBoolean(nbtName, !nbt.getBoolean(nbtName));
                    }
                }
            } else {
                if (context.getSource().getEntity() instanceof ServerPlayer player) {
                    CompoundTag nbt = player.getPersistentData().getCompound("hyd");
                    nbt.putBoolean(nbtName, !nbt.getBoolean(nbtName));
                }
            }
        } catch (CommandSyntaxException e) {
            HYEng.LOGGER.error("An error occurred at ", e);
        }
        return;
    }
}
