package com.huanyu_mod.procedures;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.LevelAccessor;

public class open_dimension_editor_gui {
	/*public static InteractionResult execute(LevelAccessor level, double x, double y, double z, Player player) {
		try {
			if (player == null) return InteractionResult.FAIL;
			if (player instanceof ServerPlayer _player) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				_player.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("dimension_editorGUI");
					}

					@Override
					public boolean shouldTriggerClientSideContainerClosingOnOpen() {
						return false;
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new dimension_editor_INV(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
                return InteractionResult.SUCCESS;
			} else {
                return InteractionResult.FAIL;
            }
		} catch (Exception e) {
			//proceduresLOGGER.log(java.util.logging.Level.SEVERE, "An error occurred at ", e);
			return InteractionResult.FAIL;
		}
	}*/
}
