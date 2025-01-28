package com.huanyu_mod.blockentity;

import com.huanyu_mod.core.IHYEng;
import com.huanyu_mod.core.register.HYBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.function.Supplier;

public class dimension_editor_be extends BlockEntity {
    public final static String CLASS_NAME = IHYEng.getCurrentClassName();
    private final static Supplier<BlockEntityType<dimension_editor_be>> BLOCK_ENTITY = HYBlockEntities.DIMENSION_EDITOR_BE;
    private String string;
    private UUID uuid;
    public dimension_editor_be(BlockPos blockPos, BlockState blockState) {
        super(BLOCK_ENTITY.get(), blockPos, blockState);
        string = "INIT";
        uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
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
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
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
}
