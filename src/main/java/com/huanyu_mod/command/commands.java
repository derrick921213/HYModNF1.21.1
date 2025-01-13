package com.huanyu_mod.command;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.procedures.dimension_teleport;
import com.huanyu_mod.procedures.fly;
import com.huanyu_mod.procedures.test;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = HuanYuMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class commands {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("hyc")

                .then(Commands.literal("test")
                        .executes(arguments -> {
                            //test.execute(arguments);
                            return 0;
                        })
                ).then(Commands.literal("dimtp")
                        .then(Commands.argument("dimension", DimensionArgument.dimension())
                                .executes(arguments -> {
                                    dimension_teleport.execute(arguments);
                                    return 0;
                                }).then(Commands.argument("position", BlockPosArgument.blockPos())
                                        .executes(arguments -> {
                                            dimension_teleport.execute(arguments);
                                            return 0;
                                        })
                                )
                        ).then(Commands.argument("players", EntityArgument.players())
                                .then(Commands.argument("dimension", DimensionArgument.dimension())
                                        .executes(arguments -> {
                                            dimension_teleport.execute(arguments);
                                            return 0;
                                        }).then(Commands.argument("position", BlockPosArgument.blockPos())
                                                .executes(arguments -> {
                                                    dimension_teleport.execute(arguments);
                                                    return 0;
                                                })
                                        )
                                )
                        )
                ).then(Commands.literal("fly")
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
                        )
                )

        );
    }
}

