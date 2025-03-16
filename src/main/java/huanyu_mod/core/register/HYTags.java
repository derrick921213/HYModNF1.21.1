package huanyu_mod.core.register;

import huanyu_mod.core.IHYEng;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class HYTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(IHYEng.makeRL(name));
        }
    }

    public static class Items {
        public final static TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(IHYEng.makeRL(name));
        }
    }
}
