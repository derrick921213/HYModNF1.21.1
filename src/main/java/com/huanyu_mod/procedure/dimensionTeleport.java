package com.huanyu_mod.procedure;

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

public class dimensionTeleport {
    public static void execute(CommandContext<CommandSourceStack> context)  {
        try {
            Level world = context.getSource().getUnsidedLevel();
            ServerPlayer executePlayer = context.getSource().getPlayer();
            if (!(executePlayer instanceof ServerPlayer)) {
                if (world instanceof ServerLevel _servLevel) {
                    executePlayer = FakePlayerFactory.getMinecraft(_servLevel);
                } else return;
            }
            ServerLevel targetLevel;
            if (context.getNodes().stream().anyMatch(node -> node.getNode().getName().equals("dimension"))) {
                targetLevel = DimensionArgument.getDimension(context, "dimension");
            } else {
                targetLevel = executePlayer.serverLevel();
            }
            double x, y, z;
            if (context.getNodes().stream().anyMatch(node -> node.getNode().getName().equals("position"))) {
                x = BlockPosArgument.getBlockPos(context, "position").getX() + 0.5;
                y = BlockPosArgument.getBlockPos(context, "position").getY();
                z = BlockPosArgument.getBlockPos(context, "position").getZ() + 0.5;
            } else {
                x = executePlayer.getX();
                y = executePlayer.getY();
                z = executePlayer.getZ();
            }
            float yRot = executePlayer.getYRot();
            float xRot = executePlayer.getXRot();
            int count = 0;
            if (context.getNodes().stream().anyMatch(node -> node.getNode().getName().equals("players"))) {
                for (Player players : EntityArgument.getPlayers(context, "players")) {
                    if(players.level().isClientSide()) continue;
                    if (players instanceof ServerPlayer _player) {
                        _player.teleportTo(targetLevel, x, y, z, yRot, xRot);
                        count++;
                    } else {
                        context.getSource().sendFailure(Component.translatable("message.huanyu_mod.teleport_fail", players.getName()));
                    }
                }
                int finalCount = count;
                context.getSource().sendSuccess(() -> Component.translatable("message.huanyu_mod.teleport_success", finalCount), false);
            } else {
                executePlayer.teleportTo(targetLevel, x, y, z, yRot, xRot);
            }
            return;
        } catch (CommandSyntaxException e) {
            //proceduresLOGGER.log(java.util.logging.Level.SEVERE, "An error occurred at ", e);
            return;
        }
    }
}
