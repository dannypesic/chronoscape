package com.dpesic.mycoscape.client;

import com.dpesic.mycoscape.client.screen.BiogeneratorScreen;
import com.dpesic.mycoscape.core.ModMenuTypes;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public final class ClientModEvents {
    private ClientModEvents() {}

    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.BIOGENERATOR_MENU.get(), BiogeneratorScreen::new);
    }
}
