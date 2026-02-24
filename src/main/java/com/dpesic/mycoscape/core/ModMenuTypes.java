package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.inventory.BiogeneratorMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(Registries.MENU, Mycoscape.MODID);

    public static final Supplier<MenuType<BiogeneratorMenu>> BIOGENERATOR_MENU = MENU_TYPES.register("biogenerator_menu", () -> IMenuTypeExtension.create(BiogeneratorMenu::new));
}
