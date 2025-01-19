package com.huanyu_mod.dataGenInModding;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class tagProviderBlock extends BlockTagsProvider {
    public tagProviderBlock(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HuanYuMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        /*this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(ModBlocks.SAPPHIRE_ORE.get()).addTag(Tags.Blocks.ORES);*/

        this.tag(BlockTags.create(ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, "debug_block"))).add(
                ModBlocks.DEBUG_BLOCK00.get(),
                ModBlocks.DEBUG_BLOCK01.get(),
                ModBlocks.DEBUG_BLOCK02.get()
        );

    }
}