package com.huanyu_mod.world.inventory;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class dimension_editor_interfaceInv extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final static HashMap<String, Object> guiState = new HashMap<>();
	public final Level level;
	public final Player player;
	public int blockX, blockY, blockZ;
	private ContainerLevelAccess access = ContainerLevelAccess.NULL;
	private IItemHandler internal;
	private final Map<Integer, Slot> customSlots = new HashMap<>();
	private boolean bound = false;
	private Supplier<Boolean> boundItemMatcher = null;
	private Entity boundEntity = null;
	private BlockEntity boundBlockEntity = null;

	public dimension_editor_interfaceInv(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(ModMenus.DIMENSION_EDITOR_INTERFACE.get(), id);
		this.player = inv.player;
		this.level = inv.player.level();
		this.internal = new ItemStackHandler(0);
		BlockPos pos;
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.blockX = pos.getX();
			this.blockY = pos.getY();
			this.blockZ = pos.getZ();
			access = ContainerLevelAccess.create(level, pos);
		}
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		if (this.bound) {
			if (this.boundItemMatcher != null)
				return this.boundItemMatcher.get();
			else if (this.boundBlockEntity != null)
				return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
			else if (this.boundEntity != null)
				return this.boundEntity.isAlive();
		}
		return true;
	}

	@Override
	public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
		return ItemStack.EMPTY;
	}

	public Map<Integer, Slot> get() {
		return customSlots;
	}
}
