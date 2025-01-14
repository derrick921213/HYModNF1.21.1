package com.huanyu_mod.block;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks DR_BLOCKS = DeferredRegister.createBlocks(HuanYuMod.MOD_ID);

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

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block, Item.Properties itemProperties) {
        DeferredBlock<T> deferredBlock = DR_BLOCKS.register(name, block);
            registerBlockItem(name, deferredBlock, itemProperties);
        return deferredBlock;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block, Item.Properties itemProperties) {
        ModItems.DR_ITEMS.register(name, () -> new BlockItem(block.get(), itemProperties));
    }
    public static void register(IEventBus eventBus) {
        DR_BLOCKS.register(eventBus);
    }
}
