package huanyu_mod.core.register;

import huanyu_mod.core.IHYEng;
import huanyu_mod.payload.MyData0;
import huanyu_mod.payload.dimensionEditorPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = IHYEng.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class HYPayloads {
    @SubscribeEvent
    public static void registerPayloadHandlers(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(IHYEng.MOD_ID).executesOn(HandlerThread.NETWORK);
        registrar.playBidirectional(
                dimensionEditorPayload.TYPE, dimensionEditorPayload.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        dimensionEditorPayload.ClientPayloadHandler::handleDataOnNetwork,
                        dimensionEditorPayload.ServerPayloadHandler::handleDataOnNetwork
                )
        );
        registrar.playBidirectional(
                MyData0.TYPE, MyData0.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        MyData0.ClientPayloadHandler::handleDataOnNetwork,
                        MyData0.ServerPayloadHandler::handleDataOnNetwork
                )
        );

    }
}