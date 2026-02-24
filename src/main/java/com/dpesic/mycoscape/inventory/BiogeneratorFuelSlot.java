package com.dpesic.mycoscape.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BiogeneratorFuelSlot extends Slot {
    private final BiogeneratorMenu menu;

    public BiogeneratorFuelSlot(final BiogeneratorMenu menu, final Container container, final int slot, final int x, final int y) {
        super(container, slot, x, y);
        this.menu = menu;
    }

    public boolean mayPlace(final ItemStack itemStack) {
        return this.menu.isFuel(itemStack);
    }

    public int getMaxStackSize(final ItemStack itemStack) {
        return super.getMaxStackSize(itemStack);
    }

}