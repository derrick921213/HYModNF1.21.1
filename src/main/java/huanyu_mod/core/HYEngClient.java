package huanyu_mod.core;

import huanyu_mod.core.register.HYScreens;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = IHYEng.MOD_ID, dist = Dist.CLIENT)
public class HYEngClient extends HYEngBase{
    public HYEngClient(IEventBus modEventBus, ModContainer modContainer) {
        super(modEventBus, modContainer);
        modEventBus.addListener(HYScreens::register);
    }

    @Override
    public Level getClientLevel() {
        return Minecraft.getInstance().level;
    }
}
