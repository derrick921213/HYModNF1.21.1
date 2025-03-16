package huanyu_mod.block;

import huanyu_mod.blockentity.HYEntityBlockExBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HYEntityBlockEx extends Block implements EntityBlock {
    public final static String CLASS_NAME = "HYEntityBlockEx";
    public final static BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties
            .of();
    public final static Item.Properties ITEM_PROPERTIES = new Item.Properties();

    public HYEntityBlockEx() {
        super(BLOCK_PROPERTIES);
    }

    /**
     * Block State
     */

    /**
     * Block Entity
     */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new HYEntityBlockExBE(blockPos, blockState);
    }

    /**
     * Block Action
     */
}
