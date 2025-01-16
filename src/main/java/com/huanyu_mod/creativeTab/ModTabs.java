package com.huanyu_mod.creativeTab;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.block.ModBlocks;
import com.huanyu_mod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> DR_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HuanYuMod.MOD_ID);

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.DEBUG_ITEM00);
        }
    }
    public static final Supplier<CreativeModeTab> DEBUG_TAB00 = DR_TABS.register(
            "debug_tab00",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creative_tab.huanyu_mod.debug_tab00"))
                    .icon(() -> new ItemStack(ModItems.DEBUG_ITEM00.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.DEBUG_ITEM00);
                        output.accept(ModItems.DEBUG_ITEM01);
                        output.accept(ModItems.DEBUG_ITEM02);
                        output.accept(ModBlocks.DEBUG_BLOCK00);
                        output.accept(ModBlocks.DEBUG_BLOCK01);
                        output.accept(ModBlocks.DEBUG_BLOCK02);
                    })
                    .build()
    );
    public static final Supplier<CreativeModeTab> DEBUG_TAB01 = DR_TABS.register(
            "huanyu_tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, "debug_tab00"))
                    .title(Component.translatable("creative_tab.huanyu_mod.huanyu_tab"))
                    .icon(() -> new ItemStack(Blocks.BEACON))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.DIMENSION_EDITOR);
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        DR_TABS.register(eventBus);
    }
}
