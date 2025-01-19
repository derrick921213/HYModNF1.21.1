package com.huanyu_mod;

import com.huanyu_mod.block._ModBlocks;
import com.huanyu_mod.item._ModItems;
import com.huanyu_mod.creativeTab._ModTabs;
import com.huanyu_mod.procedure.mymod;
import com.huanyu_mod.world.inventory._ModMenus;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(HuanYuMod.MOD_ID)
// The value here should match an entry in the META-INF/neoforge.mods.toml file
public class HuanYuMod {
    public static final String MOD_ID = "huanyu_mod";
    public static final String MOD_AUTHER = "HuanYu_";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static String getCurrentClassName() {
        return Thread.currentThread().getStackTrace()[2].getClassName()
                .substring(Thread.currentThread().getStackTrace()[2].getClassName().lastIndexOf('.') + 1);
    }

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public HuanYuMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        /*
        Register ourselves for server and other game events we are interested in.
        Note that this is necessary if and only if we want *this* class (HuanYuMod) to respond directly to events.
        Do not add this line { NeoForge.EVENT_BUS.register(this); }
            if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        */
        _ModItems.register(modEventBus);
        _ModBlocks.register(modEventBus);
        modEventBus.addListener(_ModTabs::addCreative);
        _ModTabs.register(modEventBus);
        _ModMenus.register(modEventBus);

        modEventBus.addListener(mymod::onServerStarting);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    /* -------------------- Common Event -------------------- */
    private void commonSetup(final FMLCommonSetupEvent event) {}
    //@SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        /*RegistryAccess.Writable registryAccess = (RegistryAccess.Writable) server.registryAccess();
        Registry<DimensionType> dimensionTypeRegistry = registryAccess.ownedRegistryOrThrow(Registries.DIMENSION_TYPE);

        dimensionTypeRegistry.register(
                ResourceLocation.fromNamespaceAndPath(HuanYuMod.MOD_ID, "custom_dimension"),
                customDimensionType,
                Lifecycle.stable()
        );*/
    }
}
