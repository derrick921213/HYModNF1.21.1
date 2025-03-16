package huanyu_mod.blockentity;

import huanyu_mod.core.register.HYBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class debugBlock00BE extends BlockEntity {
    public final static String CLASS_NAME = "debugBlock00BE";

    //protected final ContainerData data;
    public debugBlock00BE(BlockPos pos, BlockState state) {
        super(HYBlockEntities.DEBUG_BLOCK_00_BE.get(), pos, state);
        /*this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return 0;
            }

            @Override
            public void set(int i, int i1) {
                debug_block00_be.this.test = 66;
            }

            @Override
            public int getCount() {
                return 0;
            }
        };*/
    }

    /**
     * Storing Data
     */
    @Override
    protected void loadAdditional(@NotNull CompoundTag compoundTag, @NotNull HolderLookup.Provider lookupProvider) {
        super.loadAdditional(compoundTag, lookupProvider);
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compoundTag, @NotNull HolderLookup.Provider lookupProvider) {
        super.saveAdditional(compoundTag, lookupProvider);
    }

    /**
     * Syncing on Chunk Load
     */
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

    /**
     * Syncing on Block Update
     */
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
}
