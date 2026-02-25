package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.block.entity.AbstractMachineBlockEntity;
import com.dpesic.mycoscape.network.machine.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;


@EventBusSubscriber(modid = Mycoscape.MODID)
public class Networking {

    public static <T extends CustomPacketPayload & HasPos> void registerTogglePacket(PayloadRegistrar registrar, CustomPacketPayload.Type<T> type, StreamCodec<FriendlyByteBuf, T> streamCodec) {
        registrar.playToServer(
                type,
                streamCodec,
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

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {

        PayloadRegistrar registrar = event.registrar("1");

        registerTogglePacket(registrar, BiogeneratorTogglePacket.TYPE, BiogeneratorTogglePacket.STREAM_CODEC);
    }
}
