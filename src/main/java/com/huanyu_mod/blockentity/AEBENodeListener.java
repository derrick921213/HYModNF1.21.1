package com.huanyu_mod.blockentity;

import appeng.api.networking.IGridNode;
import appeng.api.networking.IGridNodeListener;
import com.huanyu_mod.core.IHYEng;

public class AEBENodeListener<T extends IAEGridConnectedBE> implements IGridNodeListener<T> {
    public final static String CLASS_NAME = IHYEng.getCurrentClassName();
    public static final AEBENodeListener<IAEGridConnectedBE> INSTANCE = new AEBENodeListener<>();

    @Override
    public void onGridChanged(T nodeOwner, IGridNode node) {
        IHYEng.sysOut(CLASS_NAME, "onGridChanged");
        IGridNodeListener.super.onGridChanged(nodeOwner, node);
    }

    @Override
    public void onInWorldConnectionChanged(T nodeOwner, IGridNode node) {
        IHYEng.sysOut(CLASS_NAME, "onInWorldConnectionChanged");
        IGridNodeListener.super.onInWorldConnectionChanged(nodeOwner, node);
    }

    @Override
    public void onSaveChanges(T nodeOwner, IGridNode node) {
        IHYEng.sysOut(CLASS_NAME, "onSaveChanges");
        nodeOwner.saveChanges();
    }

    @Override
    public void onStateChanged(T nodeOwner, IGridNode node, State state) {
        IHYEng.sysOut(CLASS_NAME, "onStateChanged");
        nodeOwner.onMainNodeStateChanged(state);
    }
}
