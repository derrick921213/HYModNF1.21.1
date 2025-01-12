package com.huanyu_mod.item;

import com.huanyu_mod.HuanYuMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HuanYuMod.MOD_ID);

    public static final DeferredItem<Item> DEBUG_ITEM = ITEMS.register(
            "debug_item",
            () -> new Item(new Item.Properties())
    );
    public static final DeferredItem<Item> DEBUG_ITEM2 = ITEMS.register(
            "debug_item2",
            () -> new Item(new Item.Properties())
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
