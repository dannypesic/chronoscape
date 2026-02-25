package com.dpesic.mycoscape.network;

import com.dpesic.mycoscape.block.entity.AbstractMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

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

    //   STREAM_CODEC,
//                (packet, context) -> context.enqueueWork(() -> {
//                    var player = context.player();
//                    var level = player.level();
//
//                    var be = level.getBlockEntity(packet.pos());

    public static void register(PayloadRegistrar registrar) {
        registrar.playToServer(
                TYPE,
                STREAM_CODEC,
                (packet, context) -> context.enqueueWork(()-> {

                    var player = context.player();
                    var level = player.level();

                    var be = level.getBlockEntity(packet.pos());

                    if (be instanceof AbstractMachineBlockEntity machine) {

                        machine.toggle();
                        machine.setChanged();
                    }
                }
                )
        );
    }
}
