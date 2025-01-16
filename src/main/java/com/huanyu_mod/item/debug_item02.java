package com.huanyu_mod.item;

import com.huanyu_mod.procedures.chisel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public class debug_item02 extends Item {

    public debug_item02() {
        super(new Properties()
                .durability(64)
                .stacksTo(1)
                .rarity(Rarity.EPIC)
        );
    }
    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        return chisel.execute(context);
    }
}