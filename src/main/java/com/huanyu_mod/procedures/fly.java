package com.huanyu_mod.procedures;

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

    public static void execute(CommandContext<CommandSourceStack> arguments) {
        try {
            if (arguments.getNodes().stream().anyMatch(node -> node.getNode().getName().equals("players"))) {
                for (Player players : EntityArgument.getPlayers(arguments, "players")) {
                    if (players.level().isClientSide()) continue;
                    if (players instanceof ServerPlayer player) {
                        CompoundTag nbt = player.getPersistentData().getCompound("hyd");
                        nbt.putBoolean(nbtName, !nbt.getBoolean(nbtName));
                    }
                }
            } else {
                if (arguments.getSource().getEntity() instanceof ServerPlayer player) {
                    CompoundTag nbt = player.getPersistentData().getCompound("hyd");
                    nbt.putBoolean(nbtName, !nbt.getBoolean(nbtName));
                }
            }
        } catch (CommandSyntaxException e) {
            //proceduresLOGGER.log(java.util.logging.Level.SEVERE, "An error occurred at ", e);
        }
        return;
    }
}


