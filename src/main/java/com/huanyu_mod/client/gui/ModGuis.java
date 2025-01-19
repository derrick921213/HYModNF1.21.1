//MCreator ModScreens
package com.huanyu_mod.client.gui;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.world.inventory.ModMenus;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = HuanYuMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModGuis {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(ModMenus.DIMENSION_EDITOR_INTERFACE.get(), dimensionEditorInterfaceGui::new);
	}
}
