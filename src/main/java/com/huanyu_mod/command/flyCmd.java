package com.huanyu_mod.command;

import com.huanyu_mod.procedures.fly;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;

public class flyCmd {
    public static LiteralArgumentBuilder<CommandSourceStack> register() {
        return Commands.literal("fly")
                .executes(arguments -> {
                    fly.execute(arguments);
                    return 0;
                }).then(Commands.argument("players", EntityArgument.players())
                        .executes(arguments -> {
                            fly.execute(arguments);
                            return 0;
                        }).then(Commands.argument("bool", BoolArgumentType.bool())
                                .executes(arguments -> {
                                    fly.execute(arguments);
                                    return 0;
                                })
                        )
                );
    }
}
