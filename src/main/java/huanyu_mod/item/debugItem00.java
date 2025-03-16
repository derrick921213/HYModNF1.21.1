package huanyu_mod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class debugItem00 extends Item {
    public final static String CLASS_NAME = "debugItem00";
    public final static Item.Properties ITEM_PROPERTIES = new Item.Properties()
            .rarity(Rarity.EPIC);

    public debugItem00() {
        super(ITEM_PROPERTIES);
    }
}
