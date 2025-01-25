package com.huanyu_mod.core.register;

import com.huanyu_mod.blockentity.dimension_editor_be;
import com.huanyu_mod.core.HYEng;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.function.Supplier;

public class HYBlockEntities {
    private final static String CLASS_NAME = HYEng.getCurrentClassName();
    public final static DeferredRegister<BlockEntityType<?>> DR = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, HYEng.MOD_ID);

    public final static Supplier<BlockEntityType<dimension_editor_be>> DIMENSION_EDITOR_BE = DR.register(
            "dimension_editor_be", () -> BlockEntityType.Builder.of(
                    dimension_editor_be::new,
                    HYBlocks.DIMENSION_EDITOR.get()
                    ).build(null));

    public static Collection<DeferredHolder<BlockEntityType<?>, ? extends BlockEntityType<?>>> register(IEventBus eventBus) {
        DR.register(eventBus);
        var getEntries = DR.getEntries();
        System.out.println(CLASS_NAME + " registered: " + getEntries);
        return getEntries;
    }
}
