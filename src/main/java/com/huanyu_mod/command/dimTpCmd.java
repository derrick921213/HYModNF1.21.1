package com.huanyu_mod.command;

import com.huanyu_mod.procedure.dimensionTeleport;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;

public class dimTpCmd {
    public static LiteralArgumentBuilder<CommandSourceStack> register() {
        return Commands.literal("dimTp")
                .then(Commands.argument("dimension", DimensionArgument.dimension())
                        .executes(arguments -> {
                            dimensionTeleport.execute(arguments);
                            return 0;
                        }).then(Commands.argument("position", BlockPosArgument.blockPos())
                                .executes(arguments -> {
                                    dimensionTeleport.execute(arguments);
                                    return 0;
                                })
                        )
                ).then(Commands.argument("players", EntityArgument.players())
                        .then(Commands.argument("dimension", DimensionArgument.dimension())
                                .executes(arguments -> {
                                    dimensionTeleport.execute(arguments);
                                    return 0;
                                }).then(Commands.argument("position", BlockPosArgument.blockPos())
                                        .executes(arguments -> {
                                            dimensionTeleport.execute(arguments);
                                            return 0;
                                        })
                                )
                        )
                );
    }
}
