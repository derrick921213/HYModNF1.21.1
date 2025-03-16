package huanyu_mod.core.register;

import huanyu_mod.block.*;
import huanyu_mod.core.IHYEng;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.function.Supplier;

public class HYBlocks {
    private final static String CLASS_NAME = "HYBlocks";
    public final static DeferredRegister.Blocks DR = DeferredRegister.createBlocks(IHYEng.MOD_ID);
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block, Item.Properties itemProperties) {
        DeferredBlock<T> deferredBlock = DR.register(name, block);
        registerBlockItem(name, deferredBlock, itemProperties);
        return deferredBlock;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block, Item.Properties itemProperties) {
        HYItems.DR.register(name, () -> new BlockItem(block.get(), itemProperties));
    }
    public static Collection<?> register(IEventBus eventBus) {
        DR.register(eventBus);
        var getEntries = DR.getEntries();
        System.out.println(CLASS_NAME + " registered: " + getEntries);
        return getEntries;
    }

    /**
     * Deferred Underneath
     */
    public final static DeferredBlock<?> DIMENSION_EDITOR = registerBlock(
            "dimension_editor", dimensionEditor::new, dimensionEditor.ITEM_PROPERTIES);

    public final static DeferredBlock<?> DEBUG_BLOCK00 = registerBlock(
            "debug_block00", debugBlock00::new, debugBlock00.ITEM_PROPERTIES);
    public final static DeferredBlock<?> DEBUG_BLOCK01 = registerBlock(
            "debug_block01", debugBlock01::new, debugBlock01.ITEM_PROPERTIES);
    public final static DeferredBlock<?> DEBUG_BLOCK02 = registerBlock(
            "debug_block02", debugBlock02::new, debugBlock02.ITEM_PROPERTIES);
}
