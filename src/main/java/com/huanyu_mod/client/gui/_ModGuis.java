//MCreator ModScreens
package com.huanyu_mod.client.gui;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.world.inventory._ModMenus;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = HuanYuMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class _ModGuis {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(_ModMenus.DIMENSION_EDITOR_INTERFACE.get(), dimensionEditorInterfaceGui::new);
	}
}
