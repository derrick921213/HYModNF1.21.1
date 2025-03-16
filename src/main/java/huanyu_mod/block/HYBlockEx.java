package huanyu_mod.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class HYBlockEx extends Block {
    public final static String CLASS_NAME = "HYBlockEx";
    public final static BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties
            .of();
    public final static Item.Properties ITEM_PROPERTIES = new Item.Properties();

    public HYBlockEx() {
        super(BLOCK_PROPERTIES);
    }

    /**
     * Block State
     */

    /**
     * Block Action
     */
}
