package huanyu_mod.item;

import huanyu_mod.procedure.chisel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public class debugItem02 extends Item {
    public final static String CLASS_NAME = "debugItem00";
    public final static Item.Properties ITEM_PROPERTIES = new Item.Properties()
            .rarity(Rarity.EPIC)
            .durability(64)
            .stacksTo(1);

    public debugItem02() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        return chisel.execute(context);
    }
}
