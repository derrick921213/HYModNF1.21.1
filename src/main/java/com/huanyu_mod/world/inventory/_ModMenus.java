//MCreator ModMenus
package com.huanyu_mod.world.inventory;

import com.huanyu_mod.HuanYuMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class _ModMenus {
	public static final DeferredRegister<MenuType<?>> DR_Menu = DeferredRegister.create(Registries.MENU, HuanYuMod.MOD_ID);

	public static final DeferredHolder<MenuType<?>, MenuType<dimensionEditorInterfaceInv>> DIMENSION_EDITOR_INTERFACE =
			DR_Menu.register("dimension_editor_interface", () -> IMenuTypeExtension.create(dimensionEditorInterfaceInv::new));

	public static void register(IEventBus eventBus) {
		DR_Menu.register(eventBus);
	}
}
