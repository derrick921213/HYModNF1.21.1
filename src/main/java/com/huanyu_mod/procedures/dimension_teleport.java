package com.huanyu_mod.procedures;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.FakePlayerFactory;

public class dimension_teleport {
    public static boolean execute(CommandContext<CommandSourceStack> arguments)  {
        try {
            Level world = arguments.getSource().getUnsidedLevel();
            ServerPlayer executePlayer = arguments.getSource().getPlayer();
            if (!(executePlayer instanceof ServerPlayer)) {
                if (world instanceof ServerLevel _servLevel) {
                    executePlayer = FakePlayerFactory.getMinecraft(_servLevel);
                } else return false;
            }
            ServerLevel targetLevel;
            if (arguments.getNodes().stream().anyMatch(node -> node.getNode().getName().equals("dimension"))) {
                targetLevel = DimensionArgument.getDimension(arguments, "dimension");
            } else {
                targetLevel = executePlayer.serverLevel();
            }
            double x, y, z;
            if (arguments.getNodes().stream().anyMatch(node -> node.getNode().getName().equals("position"))) {
                x = BlockPosArgument.getBlockPos(arguments, "position").getX() + 0.5;
                y = BlockPosArgument.getBlockPos(arguments, "position").getY();
                z = BlockPosArgument.getBlockPos(arguments, "position").getZ() + 0.5;
            } else {
                x = executePlayer.getX();
                y = executePlayer.getY();
                z = executePlayer.getZ();
            }
            float yRot = executePlayer.getYRot();
            float xRot = executePlayer.getXRot();
            int count = 0;
            if (arguments.getNodes().stream().anyMatch(node -> node.getNode().getName().equals("players"))) {
                for (Player players : EntityArgument.getPlayers(arguments, "players")) {
                    if(players.level().isClientSide()) continue;
                    if (players instanceof ServerPlayer _player) {
                        _player.teleportTo(targetLevel, x, y, z, yRot, xRot);
                        count++;
                    } else {
                        arguments.getSource().sendFailure(Component.translatable("message.huanyu_mod.teleport_fail", players.getName()));
                    }
                }
                int finalCount = count;
                arguments.getSource().sendSuccess(() -> Component.translatable("message.huanyu_mod.teleport_success", finalCount), false);
            } else {
                executePlayer.teleportTo(targetLevel, x, y, z, yRot, xRot);
            }
            return true;
        } catch (CommandSyntaxException e) {
            //proceduresLOGGER.log(java.util.logging.Level.SEVERE, "An error occurred at ", e);
            return false;
        }
    }
}
