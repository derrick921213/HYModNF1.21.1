package com.huanyu_mod.core.register;

import com.huanyu_mod.core.HYEng;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HYBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> DR_BLOCK_ENTITY_TYPE = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, HYEng.MOD_ID);

}
