package huanyu_mod.core.register;

import huanyu_mod.client.screen.*;
import huanyu_mod.core.IHYEng;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class HYScreens {
	public final static String CLASS_NAME = "HYScreens";

	/**
	 * Deferred Underneath
	 */
	public static void register(RegisterMenuScreensEvent event) {
		event.register(HYMenus.DIMENSION_EDITOR_GUI.get(), dimensionEditorGuiScreen::new);
	}
}
