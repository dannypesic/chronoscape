package com.dpesic.mycoscape.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

public record ToggleBiogeneratorPacket(BlockPos pos) implements CustomPacketPayload {

    public static final Type<ToggleBiogeneratorPacket> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath("mycoscape", "toggle_biogenerator"));

    public static final StreamCodec<FriendlyByteBuf, ToggleBiogeneratorPacket> STREAM_CODEC =
            StreamCodec.of(
                    (buf, pkt) -> buf.writeBlockPos(pkt.pos),
                    buf -> new ToggleBiogeneratorPacket(buf.readBlockPos())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
