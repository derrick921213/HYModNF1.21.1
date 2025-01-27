package com.huanyu_mod.blockentity;

import appeng.api.networking.*;
import appeng.api.networking.security.IActionHost;
import appeng.api.util.AEColor;
import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.core.register.HYBlockEntities;
import com.huanyu_mod.core.register.HYBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Supplier;

public class debug_block00_be extends BlockEntity implements IActionHost, IInWorldGridNodeHost, IGridNodeListener<debug_block00_be> {
    public final static String CLASS_NAME = HYEng.getCurrentClassName();
    private final static Supplier<BlockEntityType<debug_block00_be>> BLOCK_ENTITY = HYBlockEntities.DEBUG_BLOCK_00_BE;
    private String string;
    private final Set<Direction> direction = Set.of(Direction.values());
    private final IManagedGridNode MANAGED_NODE = GridHelper.createManagedNode(this, this)
            .setVisualRepresentation(new ItemStack(HYBlocks.DEBUG_BLOCK00))
            .setFlags(GridFlags.DENSE_CAPACITY)
            .setIdlePowerUsage(0.88)
            .setExposedOnSides(direction)
            .setInWorldNode(true);

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
        //this.getManagedNode().loadFromNBT(compoundTag);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider lookupProvider) {
        super.saveAdditional(compoundTag, lookupProvider);
        compoundTag.putString("stringT", this.string);
        //this.getManagedNode().saveToNBT(compoundTag);
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
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    public void nodeCreate() {
        if (!this.MANAGED_NODE.isReady()) {
            HYEng.sysOut(CLASS_NAME, "create");
            GridHelper.onFirstTick(this, debug_block00_be::onFirstTick);
        }
    }

    private void onFirstTick() {
        this.MANAGED_NODE.create(this.getLevel(), this.getBlockPos());
    }

    public void nodeDestroy() {
        HYEng.sysOut(CLASS_NAME, "destory");
        this.MANAGED_NODE.destroy();
    }

    @Override
    public void onChunkUnloaded() {
        super.onChunkUnloaded();
        nodeDestroy();
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        nodeDestroy();
    }

    @Override
    public void clearRemoved() {
        super.clearRemoved();
        GridHelper.onFirstTick(this, debug_block00_be::nodeCreate);
    }

    @Override
    public void onSaveChanges(debug_block00_be nodeOwner, IGridNode node) {
        nodeOwner.setChanged();
    }

    @Nullable
    @Override
    public IGridNode getGridNode(Direction dir) {
        var node = this.MANAGED_NODE.getNode();
        return node;
    }

    @Nullable
    @Override
    public IGridNode getActionableNode() {
        return this.MANAGED_NODE.getNode();
    }

    public IManagedGridNode getManagedNode() {
        return this.MANAGED_NODE;
    }
}
