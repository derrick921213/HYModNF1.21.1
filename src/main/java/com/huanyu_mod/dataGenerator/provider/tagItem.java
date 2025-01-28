package com.huanyu_mod.dataGenerator.provider;

import com.huanyu_mod.core.IHYEng;
import com.huanyu_mod.core.register.HYBlocks;
import com.huanyu_mod.core.register.HYItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class tagItem extends ItemTagsProvider {
    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(ItemTags.create(IHYEng.makeRL("debug_item"))).add(
                HYItems.DEBUG_ITEM00.get(),
                HYItems.DEBUG_ITEM01.get(),
                HYItems.DEBUG_ITEM02.get()
        );

        this.tag(ItemTags.create(IHYEng.makeRL("debug_block")))
                .add(HYBlocks.DEBUG_BLOCK00.get().asItem())
                .add(HYBlocks.DEBUG_BLOCK01.get().asItem())
                .add(HYBlocks.DEBUG_BLOCK02.get().asItem());

    }

    public tagItem(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> tags, ExistingFileHelper existingFileHelper) {
        super(output, registries, tags, IHYEng.MOD_ID, existingFileHelper);
    }
}
