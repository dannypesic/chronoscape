package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.block.entity.AbstractMachineBlockEntity;

import com.dpesic.mycoscape.network.ToggleBiogeneratorPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Mycoscape.MODID)
public class Networking {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {

        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(
                ToggleBiogeneratorPacket.TYPE,
                ToggleBiogeneratorPacket.STREAM_CODEC,
                (packet, context) -> {

                    var player = context.player();
                    var level = player.level();

                    var be = level.getBlockEntity(packet.pos());

                    if (be instanceof AbstractMachineBlockEntity machine) {

                        machine.toggle();

                        machine.setChanged();
                    }
                }
        );
    }
}
