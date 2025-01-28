//MCreator ModScreens
package com.huanyu_mod.core.register;

import com.huanyu_mod.client.screen.*;
import com.huanyu_mod.core.IHYEng;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = IHYEng.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HYScreens {
	@SubscribeEvent
	public static void register(RegisterMenuScreensEvent event) {
		event.register(HYMenus.DIMENSION_EDITOR_GUI.get(), dimensionEditorGuiScreen::new);
	}
}
