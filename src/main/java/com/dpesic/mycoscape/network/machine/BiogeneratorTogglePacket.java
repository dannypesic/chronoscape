package com.dpesic.mycoscape.network.machine;

import com.dpesic.mycoscape.network.HasPos;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record BiogeneratorTogglePacket(BlockPos pos)  implements CustomPacketPayload, HasPos {
    public static final Type<BiogeneratorTogglePacket> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath("mycoscape", "toggle_biogenerator"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<FriendlyByteBuf, BiogeneratorTogglePacket> STREAM_CODEC =
            StreamCodec.of(
                    (buf, pkt) -> buf.writeBlockPos(pkt.pos),
                    buf -> new BiogeneratorTogglePacket(buf.readBlockPos())
            );

}
