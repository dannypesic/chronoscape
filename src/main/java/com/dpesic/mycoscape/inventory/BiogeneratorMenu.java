package com.dpesic.mycoscape.inventory;

import com.dpesic.mycoscape.block.entity.AbstractMachineBlockEntity;
import com.dpesic.mycoscape.core.ModMenuTypes;

import com.dpesic.mycoscape.tags.MycoscapeItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BiogeneratorMenu extends AbstractContainerMenu {
    private final Container container;
    protected final Level level;
    private final AbstractMachineBlockEntity be;
    private final ContainerData data;
    private final BlockPos pos;

    public BiogeneratorMenu(int containerId, Inventory playerInv, FriendlyByteBuf buf) {
        this(containerId, playerInv, getBE(playerInv, buf), new SimpleContainerData(3));
    }

    public BiogeneratorMenu(int containerId, Inventory playerInv, AbstractMachineBlockEntity be, ContainerData data) {
        super(ModMenuTypes.BIOGENERATOR_MENU.get(), containerId);
        this.be = be;
        this.pos = be.getBlockPos();
        this.container = be;
        this.data = data;
        checkContainerSize(container, 2);
        checkContainerDataCount(data, 3);
        this.level = playerInv.player.level();
        this.addSlot(new BiogeneratorFuelSlot(this, container, 0, 80, 19));
        this.addSlot(new BiogeneratorNecroshroomSlot(this, container, 1, 80, 55));
        this.addStandardInventorySlots(playerInv, 8, 84);
        this.addDataSlots(data);
    }

    private static AbstractMachineBlockEntity getBE(Inventory playerInv, FriendlyByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity e = playerInv.player.level().getBlockEntity(pos);
        if (e instanceof AbstractMachineBlockEntity be) return be;
        throw new IllegalStateException("Expected AbstractMachineBlockEntity at " + pos + ", got: " + e);
    }

    protected boolean isFuel(final ItemStack itemStack) {
        return itemStack.is(MycoscapeItemTags.BIOGENERATOR_FUEL);
    }

    protected boolean isNecroshroom(final ItemStack itemStack) {
        return itemStack.is(MycoscapeItemTags.NECROSHROOM);
    }

    public boolean stillValid(final Player player) {
        return this.container.stillValid(player);
    }

    public boolean isToggled() {return this.data.get(1) > 0;}

    public float getBurnProgress() {
        int burnDuration = this.data.get(0);
        return Mth.clamp( (float) burnDuration / 200.0f, 0.0F, 1.0F);
    }

    public float getNecroProgress() {
        int necroDuration = this.data.get(2);
        return Mth.clamp( (float) necroDuration / 600.0f, 0.0F, 1.0F);
    }

    public ItemStack quickMoveStack(final Player player, final int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();
            if (slotIndex != 0 && slotIndex != 1) {
                if (this.isFuel(stack)) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isNecroshroom(stack)) {
                    if (!this.moveItemStackTo(stack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= 2 && slotIndex < 29) {
                    if (!this.moveItemStackTo(stack, 29, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= 29 && slotIndex < 38 && !this.moveItemStackTo(stack, 2, 29, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 2, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == clicked.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);


        }
        return clicked;
    }

    public BlockPos getBlockPos() {
        return pos;
    }




}