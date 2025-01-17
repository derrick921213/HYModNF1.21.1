package com.huanyu_mod.procedures;

/*import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.OptionalLong;

public class MyMod {

    public static final ResourceKey<DimensionType> CUSTOM_DIMENSION_TYPE_KEY =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation("mymod", "custom_dimension_type"));

    public MyMod() {
        // 註冊事件
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
    }

    private void onServerStarting(ServerStartingEvent event) {
        MinecraftServer server = event.getServer();

        // 創建自訂的 DimensionType
        DimensionType customDimensionType = new DimensionType(
                OptionalLong.of(6000L), // 固定時間（6000為中午，設為空代表日夜循環）
                true,                   // 是否具有自然光
                false,                  // 是否會產生星星
                false,                  // 是否會產生雲
                true,                   // 是否會產生霧
                1.0F,                   // 時間流速倍率
                false,                  // 是否使用終界天空效果
                false,                  // 是否自然生成
                256,                    // 世界高度
                DimensionType.DEFAULT_OVERWORLD_EFFECTS, // 預設的世界效果
                1.0F,                   // 模擬距離倍率
                new ResourceLocation("mymod", "custom_dimension_effects"), // 自訂世界效果
                0.0F                    // 生成距離縮放倍率
        );

        // 動態註冊 DimensionType
        Registry<DimensionType> dimensionTypeRegistry = server.registryAccess().registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
        dimensionTypeRegistry.registerOrOverride(
                CUSTOM_DIMENSION_TYPE_KEY.location(),
                customDimensionType,
                Lifecycle.stable()
        );

        System.out.println("自訂的 DimensionType 已成功註冊！");
    }
}*/

