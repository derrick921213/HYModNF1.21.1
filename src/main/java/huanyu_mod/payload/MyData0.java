package huanyu_mod.payload;

import huanyu_mod.core.IHYEng;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record MyData0(String name) implements CustomPacketPayload {
    public final static Type<MyData0> TYPE =
            new Type<>(IHYEng.makeRL("my_data0"));
    public final static StreamCodec<ByteBuf, MyData0> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            MyData0::name,
            MyData0::new
    );
    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class ServerPayloadHandler {
        public static void handleDataOnNetwork(final MyData0 data, final IPayloadContext context) {
            System.out.println("Received data on server: " + data.name());
        }
    }
    public static class ClientPayloadHandler {
        public static void handleDataOnNetwork(final MyData0 data, final IPayloadContext context) {
            System.out.println("Received data on client: " + data);
        }
    }
}
