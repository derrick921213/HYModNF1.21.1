package com.huanyu_mod.dataGenInModding;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.block._ModBlocks;
import com.huanyu_mod.item._ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class tagProviderItem extends ItemTagsProvider {
    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, "debug_item"))).add(
                _ModItems.DEBUG_ITEM00.get(),
                _ModItems.DEBUG_ITEM01.get(),
                _ModItems.DEBUG_ITEM02.get()
        );

        this.tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, "debug_block")))
                .add(_ModBlocks.DEBUG_BLOCK00.get().asItem())
                .add(_ModBlocks.DEBUG_BLOCK01.get().asItem())
                .add(_ModBlocks.DEBUG_BLOCK02.get().asItem());

    }

    public tagProviderItem(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> tags, ExistingFileHelper existingFileHelper) {
        super(output, registries, tags, HuanYuMod.MOD_ID, existingFileHelper);
    }
}
