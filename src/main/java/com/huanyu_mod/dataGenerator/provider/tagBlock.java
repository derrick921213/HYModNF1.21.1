package com.huanyu_mod.dataGenerator.provider;

import com.huanyu_mod.core.IHYEng;
import com.huanyu_mod.core.register.HYBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class tagBlock extends BlockTagsProvider {
    public tagBlock(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, IHYEng.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        /*this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(ModBlocks.SAPPHIRE_ORE.get()).addTag(Tags.Blocks.ORES);*/

        this.tag(BlockTags.create(IHYEng.makeRL("debug_block"))).add(
                HYBlocks.DEBUG_BLOCK00.get(),
                HYBlocks.DEBUG_BLOCK01.get(),
                HYBlocks.DEBUG_BLOCK02.get()
        );

    }
}