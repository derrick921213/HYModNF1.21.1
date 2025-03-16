package huanyu_mod.blockentity;

import appeng.api.networking.IGridNode;
import appeng.api.networking.IGridNodeListener;
import appeng.me.helpers.IGridConnectedBlockEntity;

public class AEGridNodeListener<T extends IGridConnectedBlockEntity> implements IGridNodeListener<T> {
    public static final AEGridNodeListener<IGridConnectedBlockEntity> INSTANCE = new AEGridNodeListener<>();

    @Override
    public void onSaveChanges(T nodeOwner, IGridNode node) {
        nodeOwner.saveChanges();
    }

    @Override
    public void onStateChanged(T nodeOwner, IGridNode node, State state) {
        nodeOwner.onMainNodeStateChanged(state);
    }
}
