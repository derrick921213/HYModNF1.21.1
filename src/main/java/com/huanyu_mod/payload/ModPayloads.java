package com.huanyu_mod.payload;

import com.huanyu_mod.HuanYuMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = HuanYuMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModPayloads {
    @SubscribeEvent
    public static void registerPayloadHandlers(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(HuanYuMod.MOD_ID).executesOn(HandlerThread.NETWORK);
        registrar.playBidirectional(
                MyData0.TYPE, MyData0.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        MyData0.ClientPayloadHandler::handleDataOnNetwork,
                        MyData0.ServerPayloadHandler::handleDataOnNetwork
                )
        );
        registrar.playBidirectional(
                dimensionEditorPayload.TYPE, dimensionEditorPayload.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        dimensionEditorPayload.ClientPayloadHandler::handleDataOnNetwork,
                        dimensionEditorPayload.ServerPayloadHandler::handleDataOnNetwork
                )
        );
    }
}