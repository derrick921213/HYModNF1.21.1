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
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class debug_block00_be extends AENetworkBE {
    public final static String CLASS_NAME = IHYEng.getCurrentClassName();
    private final static Supplier<BlockEntityType<debug_block00_be>> BLOCK_ENTITY = HYBlockEntities.DEBUG_BLOCK_00_BE;
    private String string;

    //protected final ContainerData data;
    public debug_block00_be(BlockPos blockPos, BlockState blockState) {
        super(BLOCK_ENTITY.get(), blockPos, blockState);
        this.string = "INIT";
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

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider lookupProvider) {
        super.loadAdditional(compoundTag, lookupProvider);
        this.string = compoundTag.getString("stringT");
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider lookupProvider) {
        super.saveAdditional(compoundTag, lookupProvider);
        compoundTag.putString("stringT", this.string);
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
}
