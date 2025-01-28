package com.huanyu_mod.blockentity;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;

import appeng.api.networking.IGrid;
import appeng.api.networking.IGridNode;
import appeng.api.networking.IGridNodeListener;
import appeng.api.networking.IInWorldGridNodeHost;
import appeng.api.networking.IManagedGridNode;
import appeng.api.networking.security.IActionHost;
import appeng.api.orientation.BlockOrientation;

public interface IAEGridConnectedBE extends IActionHost, IInWorldGridNodeHost {

    IManagedGridNode getMainNode();

    default Set<Direction> getGridConnectableSides(BlockOrientation orientation) {
        return EnumSet.allOf(Direction.class);
    }

    @Nullable
    default IGridNode getGridNode() {
        return getMainNode().getNode();
    }

    @Override
    default IGridNode getGridNode(Direction dir) {
        var node = this.getMainNode().getNode();
        return node;
    }

    default boolean ifGridPresent(Consumer<IGrid> action) {
        return getMainNode().ifPresent(action);
    }

    /**
     * Used to save changes in the grid nodes contained in the block entity to disk. Implemented in
     * {@link AEBaseBlockEntity#saveChanges()}
     */
    void saveChanges();

    default void onMainNodeStateChanged(IGridNodeListener.State reason) {
    }

    @Override
    default IGridNode getActionableNode() {
        return getMainNode().getNode();
    }

    default void setOwner(Player owner) {
        getMainNode().setOwningPlayer(owner);
    }
}
