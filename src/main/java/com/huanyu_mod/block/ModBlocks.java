package com.huanyu_mod.block;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(HuanYuMod.MOD_ID);

    public static final DeferredBlock<Block> DEBUG_BLOCK = registerBlock(
            "debug_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.AMETHYST)
                    .strength(1f, 1200f)
                    .lightLevel(s -> 15)
                    .noOcclusion()
            )
    );
    public static final DeferredBlock<Block> DEBUG_BLOCK2 = registerBlock(
            "debug_block2",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.AMETHYST)
                    .strength(1f, 1200f)
                    .lightLevel(s -> 15)
                    .noOcclusion()
            )
    );

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> deferredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, deferredBlock);
        return deferredBlock;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new BlockItem.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
