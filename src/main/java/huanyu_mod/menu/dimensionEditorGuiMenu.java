package huanyu_mod.menu;

import huanyu_mod.blockentity.dimensionEditorBE;
import huanyu_mod.core.register.HYMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
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

public class dimensionEditorGuiMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final static String CLASS_NAME = "dimensionEditorGuiMenu";
	public final static HashMap<String, Object> GUI_STATE = new HashMap<>();
	public final Level level;
	public final Player player;
	public final BlockPos blockPos;
	public final dimensionEditorBE blockEntity;
	private ContainerLevelAccess levelAccess;
	private IItemHandler itemHandler;
	private final Map<Integer, Slot> customSlots = new HashMap<>();

	public dimensionEditorGuiMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(HYMenus.DIMENSION_EDITOR_GUI.get(), id);
		this.player = inv.player;
		this.level = inv.player.level();
		this.blockPos = extraData.readBlockPos();
		BlockEntity blockEntity = level.getBlockEntity(blockPos);
		if (blockEntity instanceof dimensionEditorBE _blockEntity) {
			this.blockEntity = _blockEntity;
		} else {
			throw new IllegalStateException(CLASS_NAME + " Wrong BlockEntity");
		}
		this.levelAccess = ContainerLevelAccess.create(level, blockPos);
		this.itemHandler = new ItemStackHandler(0);
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		if (this.blockEntity == null) return false;
		return AbstractContainerMenu.stillValid(this.levelAccess, player, this.blockEntity.getBlockState().getBlock());
	}

	@Override
	public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
		return ItemStack.EMPTY;
	}
	public Map<Integer, Slot> get() {
		return customSlots;
	}
}
