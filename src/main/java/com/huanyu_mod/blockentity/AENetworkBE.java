package com.huanyu_mod.blockentity;

import appeng.api.networking.GridFlags;
import appeng.api.networking.GridHelper;
import appeng.api.networking.IGridNodeListener;
import appeng.api.networking.IManagedGridNode;
import com.huanyu_mod.core.IHYEng;
import com.huanyu_mod.core.register.HYBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class AENetworkBE extends BlockEntity implements IAEGridConnectedBE {
    /// {@link IGridNodeListener <debug_block00_be>}
    public final static String CLASS_NAME = IHYEng.getCurrentClassName();
    private final Set<Direction> direction = Set.of(Direction.values());
    private final IManagedGridNode mainNode = createMainNode()
            .setExposedOnSides(direction)
            .setFlags(GridFlags.DENSE_CAPACITY)
            .setIdlePowerUsage(0.87)
            .setInWorldNode(true)
            .setVisualRepresentation(new ItemStack(HYBlocks.DEBUG_BLOCK00));

    public AENetworkBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    protected IManagedGridNode createMainNode() {
        return GridHelper.createManagedNode(this, AEBENodeListener.INSTANCE);
    }

    public void onReady() {
        IHYEng.sysOut(CLASS_NAME, this.level, "onReady");
        this.getMainNode().create(this.getLevel(), this.getBlockPos());
    }

    @Override
    public void clearRemoved() {
        IHYEng.sysOut(CLASS_NAME, this.level, "clearRemoved");
        super.clearRemoved();
        GridHelper.onFirstTick(this, AENetworkBE::onReady);
    }

    @Override
    public void onChunkUnloaded() {
        IHYEng.sysOut(CLASS_NAME, this.level, "onChunkUnloaded");
        super.onChunkUnloaded();
        this.getMainNode().destroy();
    }

    @Override
    public void setRemoved() {
        IHYEng.sysOut(CLASS_NAME, this.level, "setRemoved");
        super.setRemoved();
        this.getMainNode().destroy();
    }

    public final IManagedGridNode getMainNode() {
        return this.mainNode;
    }

    @Override
    public void saveChanges() {
        if (this.level == null) {
            return;
        }

        if (this.level.isClientSide) {
            this.setChanged();
        }
    }
}