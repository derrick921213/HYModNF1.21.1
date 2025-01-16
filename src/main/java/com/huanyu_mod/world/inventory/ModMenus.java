//MCreator ModMenus
package com.huanyu_mod.world.inventory;

import com.huanyu_mod.HuanYuMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {
	public static final DeferredRegister<MenuType<?>> DR_Menu = DeferredRegister.create(Registries.MENU, HuanYuMod.MOD_ID);

	public static final DeferredHolder<MenuType<?>, MenuType<dimension_editor_interfaceInv>> DIMENSION_EDITOR_INTERFACE =
			DR_Menu.register("dimension_editor_interface", () -> IMenuTypeExtension.create(dimension_editor_interfaceInv::new));

	public static void register(IEventBus eventBus) {
		DR_Menu.register(eventBus);
	}
}
