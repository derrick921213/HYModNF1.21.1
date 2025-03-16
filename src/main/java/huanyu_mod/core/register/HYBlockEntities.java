package huanyu_mod.core.register;

import huanyu_mod.blockentity.*;
import huanyu_mod.core.IHYEng;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.function.Supplier;

public class HYBlockEntities {
    private final static String CLASS_NAME = "HYBlockEntities";
    public final static DeferredRegister<BlockEntityType<?>> DR = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, IHYEng.MOD_ID);

    public static Collection<DeferredHolder<BlockEntityType<?>, ? extends BlockEntityType<?>>> register(IEventBus eventBus) {
        DR.register(eventBus);
        var getEntries = DR.getEntries();
        System.out.println(CLASS_NAME + " registered: " + getEntries);
        return getEntries;
    }

    /**
     * Deferred Underneath
     */
    public final static Supplier<BlockEntityType<?>> DIMENSION_EDITOR_BE = DR.register(
            "dimension_editor_be", () -> BlockEntityType.Builder.of(
                    dimensionEditorBE::new, HYBlocks.DIMENSION_EDITOR.get()).build(null));

    public final static Supplier<BlockEntityType<?>> DEBUG_BLOCK_00_BE = DR.register(
            "debug_block00_be", () -> BlockEntityType.Builder.of(
                    debugBlock00BE::new, HYBlocks.DEBUG_BLOCK00.get()).build(null));

    public final static Supplier<BlockEntityType<?>> DEBUG_BLOCK_01_BE = DR.register(
            "debug_block01_be", () -> BlockEntityType.Builder.of(
                    debugBlock01BE::new, HYBlocks.DEBUG_BLOCK01.get()).build(null));
}
