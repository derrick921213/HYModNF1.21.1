package huanyu_mod.payload;

import huanyu_mod.blockentity.dimensionEditorBE;
import huanyu_mod.core.IHYEng;
import huanyu_mod.procedure.test;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record dimensionEditorPayload(BlockPos blockPos, String _type, String string0, String string1, String string2) implements CustomPacketPayload {
    public final static String CLASS_NAME = "dimensionEditorPayload";
    public final static Type<dimensionEditorPayload> TYPE = new Type<>(IHYEng.makeRL("dimension_editor"));
    public final static StreamCodec<RegistryFriendlyByteBuf, dimensionEditorPayload> STREAM_CODEC = StreamCodec.of(
            (RegistryFriendlyByteBuf buffer, dimensionEditorPayload data) -> {
                buffer.writeBlockPos(data.blockPos);
                buffer.writeUtf(data._type);
                buffer.writeUtf(data.string0);
                buffer.writeUtf(data.string1);
                buffer.writeUtf(data.string2);
            },
            (RegistryFriendlyByteBuf buffer) -> new dimensionEditorPayload(
                    buffer.readBlockPos(),
                    buffer.readUtf(),
                    buffer.readUtf(),
                    buffer.readUtf(),
                    buffer.readUtf()
            )
    );
    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static dimensionEditorPayload of(BlockPos blockPos, String _type, String... strings) {
        String string0 = strings.length > 0 ? strings[0] : "";
        String string1 = strings.length > 1 ? strings[1] : "";
        String string2 = strings.length > 2 ? strings[2] : "";
        return new dimensionEditorPayload(blockPos, _type, string0, string1, string2);
    }

    public static class ServerPayloadHandler {
        private static dimensionEditorBE blockEntity;
        public static void handleDataOnNetwork(final dimensionEditorPayload data, final IPayloadContext context) {
            if (context.flow() == PacketFlow.SERVERBOUND) {
                context.enqueueWork(() -> {
                    handleDataAction(context.player(), data.blockPos, data._type, data.string0, data.string1);
                    blockEntity.setChanged();
                }).exceptionally(e -> {
                    context.connection().disconnect(Component.literal(e.getMessage()));
                    return null;
                });
            }
        }
        public static void handleDataAction(Player player, BlockPos blockPos, String _type, String string0, String string1) {
            if (!(player instanceof ServerPlayer)) return;
            Level level = player.level();
            if (level.getBlockEntity(blockPos) instanceof dimensionEditorBE _blockEntity)
                blockEntity = _blockEntity;
            else return;
            switch (_type) {
                case "close":
                    blockEntity.setString(string0);
                    blockEntity.setUuid(player.getUUID());
                    break;
                case "store":
                    switch (string0) {
                        case "1":
                            blockEntity.setString(string1);
                            break;
                    }
                    break;
                case "button":
                    switch (string0) {
                        case "0":
                            break;
                        case "1":
                            test.executeBlock(level, blockPos, player);
                            break;
                        case "2":
                            break;
                        case "3":
                            break;
                        default: break;
                    }
                case "edit_box":
                    break;
                default: break;
            }
        }
    }

    public static class ClientPayloadHandler {
        public static void handleDataOnNetwork(final dimensionEditorPayload data, final IPayloadContext context) {
            System.out.println("Received data on client: " + data);
        }
    }
}
