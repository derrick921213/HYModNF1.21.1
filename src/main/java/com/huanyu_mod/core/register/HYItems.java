package com.huanyu_mod.core.register;

import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.item.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class HYItems {
    private final static String CLASS_NAME = HYEng.getCurrentClassName();
    public final static DeferredRegister.Items DR = DeferredRegister.createItems(HYEng.MOD_ID);

    //DeferredItem Underneath
    public final static DeferredItem<debug_item00> DEBUG_ITEM00 = DR.register("debug_item00", debug_item00::new);
    public final static DeferredItem<Item> DEBUG_ITEM01 = DR.register(
            "debug_item01",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)
            )
    );
    public final static DeferredItem<debug_item02> DEBUG_ITEM02 = DR.register("debug_item02", debug_item02::new);

    public static Collection register(IEventBus eventBus) {
        DR.register(eventBus);
        var getEntries = DR.getEntries();
        System.out.println(CLASS_NAME + " registered: " + getEntries);
        return getEntries;
    }
}
