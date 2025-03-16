package huanyu_mod.core.register;

import huanyu_mod.core.IHYEng;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.function.Supplier;

public class HYTabs {
    private final static String CLASS_NAME = "HYTabs";
    public final static DeferredRegister<CreativeModeTab> DR = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IHYEng.MOD_ID);

    public static Collection<DeferredHolder<CreativeModeTab, ? extends CreativeModeTab>> register(IEventBus eventBus) {
        DR.register(eventBus);
        var getEntries = DR.getEntries();
        System.out.println(CLASS_NAME + " registered: " + getEntries);
        return getEntries;
    }

    /**
     * Deferred Underneath
     */
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(HYItems.DEBUG_ITEM00);
        }
    }

    public final static Supplier<CreativeModeTab> DEBUG_TAB00 = DR.register(
            "debug_tab00",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creative_tab.huanyu_mod.debug_tab00"))
                    .icon(() -> new ItemStack(HYItems.DEBUG_ITEM00.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(HYItems.DEBUG_ITEM00);
                        output.accept(HYItems.DEBUG_ITEM01);
                        output.accept(HYItems.DEBUG_ITEM02);
                        output.accept(HYBlocks.DEBUG_BLOCK00);
                        output.accept(HYBlocks.DEBUG_BLOCK01);
                        output.accept(HYBlocks.DEBUG_BLOCK02);
                    })
                    .build()
    );

    public final static Supplier<CreativeModeTab> DEBUG_TAB01 = DR.register(
            "huanyu_tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(IHYEng.makeRL("debug_tab00"))
                    .title(Component.translatable("creative_tab.huanyu_mod.huanyu_tab"))
                    .icon(() -> new ItemStack(Blocks.BEACON))
                    .displayItems((parameters, output) -> {
                        output.accept(HYBlocks.DIMENSION_EDITOR);
                    })
                    .build()
    );
}
