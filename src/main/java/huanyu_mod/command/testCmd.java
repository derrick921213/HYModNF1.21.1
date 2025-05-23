package huanyu_mod.command;

import huanyu_mod.procedure.test;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class testCmd {
    public static LiteralArgumentBuilder<CommandSourceStack> register() {
        return Commands.literal("test")
                .executes(arguments -> {
                    test.executeCmd(arguments);
                    return 0;
                });
    }
}