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

import java.util.UUID;

public class dimensionEditorBE extends BlockEntity {
    public final static String CLASS_NAME = "dimensionEditorBE";
    private String string;
    private UUID uuid;
    public dimensionEditorBE(BlockPos pos, BlockState state) {
        super(HYBlockEntities.DIMENSION_EDITOR_BE.get(), pos, state);
        string = "INIT";
        uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
    }
    public String getString() {
        return this.string;
    }
    public void setString(String string) {
        this.string = string;
    }
    public UUID getUuid() {
        return this.uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag compoundTag, @NotNull HolderLookup.Provider lookupProvider) {
        super.loadAdditional(compoundTag, lookupProvider);
        this.string = compoundTag.getString("stringT");
        this.uuid = compoundTag.getUUID("uuid");
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compoundTag, @NotNull HolderLookup.Provider lookupProvider) {
        super.saveAdditional(compoundTag, lookupProvider);
        compoundTag.putString("stringT", this.string);
        compoundTag.putUUID("uuid", this.uuid);
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
}
