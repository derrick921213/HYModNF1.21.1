package com.huanyu_mod.core.register;

import com.huanyu_mod.block.*;
import com.huanyu_mod.core.HYEng;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.function.Supplier;

public class HYBlocks {
    private static final String CLASS_NAME = HYEng.getCurrentClassName();
    public static final DeferredRegister.Blocks DR = DeferredRegister.createBlocks(HYEng.MOD_ID);

    //DeferredBlock Underneath
    public static final DeferredBlock<Block> DIMENSION_EDITOR = registerBlock(
            "dimension_editor", dimension_editor::new, dimension_editor.itemProperties());
    public static final DeferredBlock<Block> DEBUG_BLOCK00 = registerBlock(
            "debug_block00", debug_block00::new, debug_block00.itemProperties());
    public static final DeferredBlock<Block> DEBUG_BLOCK01 = registerBlock(
            "debug_block01",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.AMETHYST)
                    .strength(1f, 1200f)
                    .lightLevel(s -> 15)
                    .noOcclusion()
            ),
            new Item.Properties()
    );
    public static final DeferredBlock<Block> DEBUG_BLOCK02 = registerBlock(
            "debug_block02", debug_block02::new, debug_block02.itemProperties());


    private static <T extends Block> DeferredBlock<T> registerBlock(
            String name, Supplier<T> block, Item.Properties itemProperties) {
        DeferredBlock<T> deferredBlock = DR.register(name, block);
        registerBlockItem(name, deferredBlock, itemProperties);
        return deferredBlock;
    }
    private static <T extends Block> void registerBlockItem(
            String name, DeferredBlock<T> block, Item.Properties itemProperties) {
        HYItems.DR.register(name, () -> new BlockItem(block.get(), itemProperties));
    }
    public static Collection<DeferredHolder<Block, ? extends Block>> register(IEventBus eventBus) {
        DR.register(eventBus);
        var getEntries = DR.getEntries();
        System.out.println(CLASS_NAME + " registered: " + getEntries);
        return getEntries;
    }
}
