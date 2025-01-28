package com.huanyu_mod.command;

import com.huanyu_mod.core.IHYEng;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.FakePlayerFactory;

public class dim_teleport {
    public final static String CLASS_NAME = IHYEng.getCurrentClassName();

    public static LiteralArgumentBuilder<CommandSourceStack> register() { return Commands.literal("dimTp")
            .then(Commands.argument("dimension", DimensionArgument.dimension())
                    .executes(arguments -> {
                        execute(arguments);
                        return 0;
                    }).then(Commands.argument("position", BlockPosArgument.blockPos())
                            .executes(arguments -> {
                                execute(arguments);
                                return 0;
                            })
                    )
            ).then(Commands.argument("players", EntityArgument.players())
                    .then(Commands.argument("dimension", DimensionArgument.dimension())
                            .executes(arguments -> {
                                execute(arguments);
                                return 0;
                            }).then(Commands.argument("position", BlockPosArgument.blockPos())
                                    .executes(arguments -> {
                                        execute(arguments);
                                        return 0;
                                    })
                            )
                    )
            );
    }

    private static void execute(CommandContext<CommandSourceStack> context)  {
        try {
            Level level = context.getSource().getUnsidedLevel();
            ServerPlayer executePlayer = context.getSource().getPlayer();
            if (!(executePlayer instanceof ServerPlayer)) {
                if (level instanceof ServerLevel _serverLevel) {
                    executePlayer = FakePlayerFactory.getMinecraft(_serverLevel);
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
        } catch (CommandSyntaxException e) {
            //proceduresLOGGER.log(java.util.logging.Level.SEVERE, "An error occurred at ", e);
        }
    }
}
