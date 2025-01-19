package com.huanyu_mod.procedure;

import com.huanyu_mod.block.ModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class chisel {
    private static final Map<Block, Block> CLICK_BLOCK_MAP = Map.of(
            Blocks.GRASS_BLOCK, Blocks.DIRT,
            Blocks.DIRT, Blocks.GRASS_BLOCK,
            ModBlocks.DEBUG_BLOCK00.get(), ModBlocks.DEBUG_BLOCK01.get()
    );

    public static InteractionResult execute(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        if(CLICK_BLOCK_MAP.containsKey(clickedBlock)) {
            if(!(level.isClientSide())) {
                level.setBlockAndUpdate(context.getClickedPos(), CLICK_BLOCK_MAP.get(clickedBlock).defaultBlockState());
                context.getItemInHand().hurtAndBreak(
                        1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
                );
                level.playSound(null, context.getClickedPos(), SoundEvents.BELL_BLOCK, SoundSource.BLOCKS);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
