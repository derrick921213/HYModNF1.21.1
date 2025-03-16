package huanyu_mod.blockentity;

import appeng.api.networking.*;
import appeng.blockentity.grid.AENetworkBlockEntity;
import appeng.me.helpers.BlockEntityNodeListener;
import huanyu_mod.core.register.HYBlockEntities;
import huanyu_mod.core.register.HYBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class debugBlock01BE extends AENetworkBlockEntity {
    public final static String CLASS_NAME = "debugBlock01BE";
    private final IManagedGridNode mainNode = GridHelper.createManagedNode(this, BlockEntityNodeListener.INSTANCE)
            .setExposedOnSides(Set.of(Direction.values()))
            .setFlags(GridFlags.DENSE_CAPACITY)
            .setInWorldNode(true)
            .setIdlePowerUsage(2)
            .setVisualRepresentation(new ItemStack(HYBlocks.DEBUG_BLOCK01));

    public debugBlock01BE(BlockPos pos, BlockState state) {
        super(HYBlockEntities.DEBUG_BLOCK_01_BE.get(), pos, state);
    }

    /*@Override
    public void loadAdditional(@NotNull CompoundTag compoundTag, @NotNull HolderLookup.Provider lookupProvider) {
        super.loadAdditional(compoundTag, lookupProvider);
    }*/

    @Override
    public void saveAdditional(@NotNull CompoundTag compoundTag, @NotNull HolderLookup.Provider lookupProvider) {
        super.saveAdditional(compoundTag, lookupProvider);
    }

    @NotNull
    @Override
    public CompoundTag getUpdateTag(@NotNull HolderLookup.Provider provider) {
        CompoundTag compoundTag = new CompoundTag();
        saveAdditional(compoundTag, provider);
        return compoundTag;
    }

    @Override
    public void handleUpdateTag(@NotNull CompoundTag compoundTag, @NotNull HolderLookup.Provider provider) {
        super.handleUpdateTag(compoundTag, provider);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(@NotNull Connection connection, @NotNull ClientboundBlockEntityDataPacket packet, @NotNull HolderLookup.Provider provider) {
        super.onDataPacket(connection, packet, provider);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }



    /*
    @Override
    public void clearRemoved() {
        super.clearRemoved();
        GridHelper.onFirstTick(this, debugBlock01BE::onFirstTick);
    }

    private void onFirstTick() {
        this.MANAGED_NODE.create(this.getLevel(), this.getBlockPos());
    }

    @Override
    public void onChunkUnloaded() {
        super.onChunkUnloaded();
        this.MANAGED_NODE.destroy();
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        this.MANAGED_NODE.destroy();
    }

    @Override
    public IManagedGridNode getMainNode() {
        return this.MANAGED_NODE;
    }

    @Nullable
    @Override
    public IGridNode getGridNode(Direction dir) {
        var node = this.MANAGED_NODE.getNode();
        return node;
    }

    @Override
    public void saveChanges() {
        this.setChanged();
    }

    @Nullable
    @Override
    public IGridNode getActionableNode() {
        return this.MANAGED_NODE.getNode();
    }
    */
}
