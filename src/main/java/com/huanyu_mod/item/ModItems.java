package com.huanyu_mod.item;

import com.huanyu_mod.HuanYuMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items DR_ITEMS = DeferredRegister.createItems(HuanYuMod.MOD_ID);

    public static final DeferredItem<Item> DEBUG_ITEM00 = DR_ITEMS.register("debug_item00", debug_item00::new);
    public static final DeferredItem<Item> DEBUG_ITEM01 = DR_ITEMS.register(
            "debug_item01",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)
            )
    );
    public static final DeferredItem<Item> DEBUG_ITEM02 = DR_ITEMS.register("debug_item02", debug_item02::new);

    public static void register(IEventBus eventBus) {
        DR_ITEMS.register(eventBus);
    }
}
