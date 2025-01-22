package com.huanyu_mod.payload;

import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.procedure.test;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record dimensionEditorPayload(int buttonID, Vec3 blockPos) implements CustomPacketPayload {
    public static final Type<dimensionEditorPayload> TYPE =
            new Type<>(HYEng.makeRL("my_data1"));
    public static final StreamCodec<RegistryFriendlyByteBuf, dimensionEditorPayload> STREAM_CODEC = StreamCodec.of(
            (RegistryFriendlyByteBuf buffer, dimensionEditorPayload data) -> {
                buffer.writeInt(data.buttonID);
                buffer.writeVec3(data.blockPos);
            }, (RegistryFriendlyByteBuf buffer) ->
                    new dimensionEditorPayload(buffer.readInt(), buffer.readVec3()));
    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleDataAction(Player player, int buttonID, Vec3 blockPos) {
        try {
            Level level = player.level();
            System.out.println(dimensionEditorPayload.class.getName() + " Output: " + player.getName());
            System.out.println(dimensionEditorPayload.class.getName() + " Output: " + buttonID);
            System.out.println(dimensionEditorPayload.class.getName() + " Output: " + blockPos);

            if (!level.hasChunkAt(new BlockPos((int) blockPos.x, (int) blockPos.y, (int) blockPos.z))) return;
            switch (buttonID) {
                case 0: break;
                case 1:
                    test.executeU(level, blockPos, player);
                    break;
                case 2: break;
                case 3: break;
                default: break;
            }
        } catch (Exception e) {

        }
    }

    public static class ServerPayloadHandler {
        public static void handleDataOnNetwork(final dimensionEditorPayload data, final IPayloadContext context) {
            if (context.flow() == PacketFlow.SERVERBOUND) {
                context.enqueueWork(() -> {
                    Player player = context.player();
                    int buttonID = data.buttonID;
                    Vec3 blockPos = data.blockPos;
                    handleDataAction(player, buttonID, blockPos);
                }).exceptionally(e -> {
                    context.connection().disconnect(Component.literal(e.getMessage()));
                    return null;
                });
            }
        }
    }
    public static class ClientPayloadHandler {
        public static void handleDataOnNetwork(final dimensionEditorPayload data, final IPayloadContext context) {
            System.out.println("Received data on client: " + data);
        }
    }
}
