package com.huanyu_mod.blockentity;

import appeng.api.networking.*;
import appeng.api.networking.security.IActionHost;
import appeng.api.util.AEColor;
import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.core.register.HYBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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
    private final IManagedGridNode MANAGED_NODE = createManagedNode()
            .setExposedOnSides(direction)
            .setInWorldNode(true)
            .setIdlePowerUsage(0.87)
            .setGridColor(AEColor.GREEN);

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

    protected IManagedGridNode createManagedNode() {
        return GridHelper.createManagedNode(this, this);
    }

    public IManagedGridNode getManagedNode() {
        return this.MANAGED_NODE;
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider lookupProvider) {
        super.loadAdditional(compoundTag, lookupProvider);
        this.string = compoundTag.getString("stringT");
        //this.getManagedNode().loadFromNBT(compoundTag);
    }

    @Override
    public void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider lookupProvider) {
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

    private void nodeCreate() {
        if (!this.MANAGED_NODE.isReady()) {
            this.MANAGED_NODE.create(this.getLevel(), this.getBlockPos());
        }
    }

    private void nodeDestroy() {
        this.getManagedNode().destroy();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        nodeCreate();
    }

    @Override
    public void clearRemoved() {
        super.clearRemoved();
        GridHelper.onFirstTick(this, debug_block00_be::nodeCreate);
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
    public void onSaveChanges(debug_block00_be nodeOwner, IGridNode node) {
        nodeOwner.setChanged();
    }

    @Nullable
    @Override
    public IGridNode getGridNode(Direction dir) {
        var node = this.getManagedNode().getNode();
        return node;
        // We use the node rather than getGridConnectableSides since the node is already using absolute sides
        /*if (node.isExposedOnSide(dir)) {
            return node;
        }
        return null;*/
    }

    @Nullable
    @Override
    public IGridNode getActionableNode() {
        return this.getManagedNode().getNode();
    }
}
