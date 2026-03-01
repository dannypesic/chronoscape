package com.dpesic.mycoscape.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.WorldlyContainer;



public abstract class AbstractMachineBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer{
    private final Component DEFAULT_NAME;
    protected NonNullList<ItemStack> items;
    public int toggled;

    public AbstractMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int containerSize, Component name) {
        super(type, pos, state);
        this.items = NonNullList.withSize(containerSize, ItemStack.EMPTY);
        this.DEFAULT_NAME = name;

    }
    protected Component getDefaultName() {
        return DEFAULT_NAME;
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public int getContainerSize() {
        return this.items.size();
    }

    protected void setItems(final NonNullList<ItemStack> items) {
        this.items = items;
    }

    protected abstract AbstractContainerMenu createMenu(final int containerId, final Inventory inventory);

    public void toggle() {
        toggled = toggled == 0 ? 1 : 0;
    }

    protected abstract void tick(Level level, BlockPos pos, BlockState state);

    public static void serverTick( Level level, BlockPos pos, BlockState state, AbstractMachineBlockEntity be) {
        be.tick(level, pos, state);
    };

    public abstract int[] getSlotsForFace(Direction direction);

    public abstract boolean canPlaceItemThroughFace(int slot, ItemStack itemStack, Direction direction);

    public abstract boolean canTakeItemThroughFace(int slot, ItemStack itemStack, Direction direction);


}