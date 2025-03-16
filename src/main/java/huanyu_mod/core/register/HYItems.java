package huanyu_mod.core.register;

import huanyu_mod.core.IHYEng;
import huanyu_mod.item.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class HYItems {
    private final static String CLASS_NAME = "HYItems";
    public final static DeferredRegister.Items DR = DeferredRegister.createItems(IHYEng.MOD_ID);

    public static Collection<?> register(IEventBus eventBus) {
        DR.register(eventBus);
        var getEntries = DR.getEntries();
        System.out.println(CLASS_NAME + " registered: " + getEntries);
        return getEntries;
    }

    /**
     * Deferred Underneath
     */
    public final static DeferredItem<?> DEBUG_ITEM00 = DR.register("debug_item00", debugItem00::new);
    public final static DeferredItem<?> DEBUG_ITEM01 = DR.register(
            "debug_item01",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)
            )
    );
    public final static DeferredItem<?> DEBUG_ITEM02 = DR.register("debug_item02", debugItem02::new);
}
