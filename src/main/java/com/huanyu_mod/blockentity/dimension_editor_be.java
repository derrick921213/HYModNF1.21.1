package com.huanyu_mod.blockentity;

import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.core.register.HYBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class dimension_editor_be extends BlockEntity {
    public final static String CLASS_NAME = HYEng.getCurrentClassName();
    private String string = "init";
    private UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
    //protected final ContainerData data;
    public dimension_editor_be(BlockPos blockPos, BlockState blockState) {
        super(HYBlockEntities.DIMENSION_EDITOR_BE.get(), blockPos, blockState);
        /*this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return 0;
            }

            @Override
            public void set(int i, int i1) {
                dimension_editor_be.this.test = 66;
            }

            @Override
            public int getCount() {
                return 0;
            }
        };*/
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider lookupProvider) {
        super.loadAdditional(compoundTag, lookupProvider);
        this.string = compoundTag.getString("stringT");
        this.uuid = compoundTag.getUUID("uuid");
    }

    @Override
    public void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider lookupProvider) {
        super.saveAdditional(compoundTag, lookupProvider);
        compoundTag.putString("stringT", this.string);
        compoundTag.putUUID("uuid", this.uuid);
    }

    @NotNull
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag compoundTag = new CompoundTag();
        saveAdditional(compoundTag, provider);
        return compoundTag;
    }

    @Override
    public void handleUpdateTag(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.handleUpdateTag(compoundTag, provider);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider provider) {
        super.onDataPacket(connection, packet, provider);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
    }

    public String getString() {
        return this.string;
    }
    public void writeString(String value) {
        this.string = value;
    }
    public UUID getUuid() {
        return this.uuid;
    }
    public void writeUuid(UUID value) {
        this.uuid = value;
    }
}
