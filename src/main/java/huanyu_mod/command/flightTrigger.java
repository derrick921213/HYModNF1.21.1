package huanyu_mod.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class flightTrigger {
    public final static String CLASS_NAME = "flightTrigger";
    private final static String NBT_NAME = "mayfly";

    public static LiteralArgumentBuilder<CommandSourceStack> register() {
        return Commands.literal("fly")
                .executes(arguments -> {
                    fly(arguments);
                    return 1;
                }).then(Commands.argument("players", EntityArgument.players())
                        .executes(arguments -> {
                            flyPlayers(arguments);
                            return 1;
                        }).then(Commands.argument("bool", BoolArgumentType.bool())
                                .executes(arguments -> {
                                    flyPlayersBool(arguments);
                                    return 1;
                                })
                        )
                );
    }

    public static void tick(ServerPlayer serverPlayer) {
        CompoundTag compoundTag = serverPlayer.getPersistentData();
        if (!compoundTag.contains("hyd", CompoundTag.TAG_COMPOUND)) {
            compoundTag.put("hyd", new CompoundTag());
        }
        compoundTag = compoundTag.getCompound("hyd");
        if (!compoundTag.contains(NBT_NAME)) {
            compoundTag.putBoolean(NBT_NAME, false);
        }
        if (serverPlayer.isCreative() || serverPlayer.isSpectator()) {
            return;
        }
        if (compoundTag.getBoolean(NBT_NAME)) {
            if (!serverPlayer.getAbilities().mayfly) {
                serverPlayer.getAbilities().mayfly = true;
                serverPlayer.onUpdateAbilities();
                serverPlayer.displayClientMessage(Component.translatable("message.huanyu_mod.enable_fly"), true);
            }
        } else {
            if (serverPlayer.getAbilities().mayfly) {
                serverPlayer.getAbilities().mayfly = false;
                serverPlayer.onUpdateAbilities();
                serverPlayer.displayClientMessage(Component.translatable("message.huanyu_mod.disable_fly"), true);
            }
        }
    }

    private static void fly(CommandContext<CommandSourceStack> context) {
        if (context.getSource().getEntity() instanceof ServerPlayer player) {
            CompoundTag compoundTag = player.getPersistentData().getCompound("hyd");
            compoundTag.putBoolean(NBT_NAME, !compoundTag.getBoolean(NBT_NAME));
        }
    }

    private static void flyPlayers(CommandContext<CommandSourceStack> context) {
        try {
            for (Player players : EntityArgument.getPlayers(context, "players")) {
                if (players.level().isClientSide()) continue;
                if (players instanceof ServerPlayer player) {
                    CompoundTag compoundTag = player.getPersistentData().getCompound("hyd");
                    compoundTag.putBoolean(NBT_NAME, !compoundTag.getBoolean(NBT_NAME));
                }
            }
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static void flyPlayersBool(CommandContext<CommandSourceStack> context) {
        try {
            for (Player players : EntityArgument.getPlayers(context, "players")) {
                if (players.level().isClientSide()) continue;
                if (players instanceof ServerPlayer player) {
                    CompoundTag compoundTag = player.getPersistentData().getCompound("hyd");
                    compoundTag.putBoolean(NBT_NAME, context.getArgument("bool", Boolean.class));
                }
            }
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
