//MCreator ModMenus
package com.huanyu_mod.core.register;

import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.world.menu.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class HYMenus {
	private final static String CLASS_NAME = HYEng.getCurrentClassName();
	public final static DeferredRegister<MenuType<?>> DR = DeferredRegister.create(Registries.MENU, HYEng.MOD_ID);

	public final static DeferredHolder<MenuType<?>, MenuType<dimensionEditorGuiMenu>> DIMENSION_EDITOR_GUI =
			DR.register("dimension_editor_gui", () -> IMenuTypeExtension.create(dimensionEditorGuiMenu::new));

	public static Collection<DeferredHolder<MenuType<?>, ? extends MenuType<?>>> register(IEventBus eventBus) {
		DR.register(eventBus);
		var getEntries = DR.getEntries();
		System.out.println(CLASS_NAME + " registered: " + getEntries);
		return getEntries;
	}
}
