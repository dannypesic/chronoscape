package com.dpesic.mycoscape.block.entity;

import com.dpesic.mycoscape.block.machine.BiogeneratorBlock;
import com.dpesic.mycoscape.core.ModBlockEntityTypes;
import com.dpesic.mycoscape.inventory.BiogeneratorMenu;
import com.dpesic.mycoscape.sound.BiogeneratorSound;
import com.dpesic.mycoscape.tags.MycoscapeItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class BiogeneratorBlockEntity extends AbstractMachineBlockEntity{
    private static final Component name = Component.translatable("container.biogenerator");
    private int burnTimeRemaining;
    private int necroTimeRemaining;
    protected final ContainerData dataAccess;
    private boolean soundPlaying = false;
    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1};
    private static final int[] SLOTS_EMPTY = new int[0];

    public BiogeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BIOGENERATOR_BLOCK_ENTITY.get(), pos, state, 2, name);
        this.dataAccess = new ContainerData() { //use super dataBuilder
            public int get(final int dataId) {
                switch (dataId) {
                    case 0 -> {
                        return burnTimeRemaining;
                    }
                    case 1 -> {
                        return toggled;
                    }
                    case 2 -> {
                        return necroTimeRemaining;
                    }
                    default -> {
                        return 0;
                    }
                }
            }

            public void set(final int dataId, final int value) {
                switch (dataId) {
                    case 0 -> burnTimeRemaining = value;
                    case 1 -> toggled = value;
                    case 2 -> necroTimeRemaining = value;

                }

            }

            public int getCount() {
                return 3;
            }
        };
    }

    protected void loadAdditional(final ValueInput input) {
        super.loadAdditional(input);
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(input, items);
        burnTimeRemaining = input.getShortOr("burn_time_remaining", (short) 0);
        toggled = input.getShortOr("toggled", (short) 0);
        necroTimeRemaining = input.getShortOr("necroshroom_time_remaining", (short) 0);

    }

    protected void saveAdditional(final ValueOutput output) {
        super.saveAdditional(output);
        output.putShort("burn_time_remaining", (short) burnTimeRemaining);
        output.putShort("toggled", (short) toggled);
        output.putShort("necroshroom_time_remaining", (short) necroTimeRemaining);
        ContainerHelper.saveAllItems(output, items);
    }

    @Override
    protected AbstractContainerMenu createMenu(final int containerId, final Inventory inventory) {
        return new BiogeneratorMenu( containerId, inventory, this, dataAccess);
    }

    private boolean mayPlace(int slot, ItemStack item) {
        if (slot == 0 && item.is(MycoscapeItemTags.BIOGENERATOR_FUEL)) {
            return true;
        }
        return slot == 1 && item.is(MycoscapeItemTags.NECROSHROOM);
    }

    public int[] getSlotsForFace(Direction direction) {
        if (this.level == null) {
            return SLOTS_EMPTY;
        }

        Direction energyIODirection = this.getBlockState().getValue(BiogeneratorBlock.FACING);

        if (direction == energyIODirection || direction == Direction.DOWN) {
            return SLOTS_EMPTY;
        }
        if (direction == Direction.UP) {
            return SLOTS_FOR_UP;
        }
        return SLOTS_FOR_SIDES;

    }

    public boolean canPlaceItemThroughFace(int slot, ItemStack itemStack, Direction direction) {
        return toggled == 1 && mayPlace(slot, itemStack);
    }

    public boolean canTakeItemThroughFace(int slot, ItemStack itemStack, Direction direction) {
        return false;
    }



    private boolean canBurnFuel(final NonNullList<ItemStack> items) {
        return burnTimeRemaining <= 0 && !items.getFirst().isEmpty() && necroTimeRemaining > 0;
    }

    private boolean canBurnNecro(final NonNullList<ItemStack> items) {
        return necroTimeRemaining <= 0 && !items.get(0).isEmpty() && !items.get(1).isEmpty();
    }

    private static void burnFuel(final NonNullList<ItemStack> items) {
        items.getFirst().shrink(1);
    }
    private static void burnNecro(final NonNullList<ItemStack> items) {
        items.get(1).shrink(1);
    }

    @Override
    protected void tick( Level level, BlockPos pos, BlockState state) {
        if (toggled == 1) {
            if (burnTimeRemaining > 0) {burnTimeRemaining--;}
            if (necroTimeRemaining > 0) {necroTimeRemaining--;}

            if (!(soundPlaying) && burnTimeRemaining > 0) {
                Minecraft.getInstance().getSoundManager()
                        .play(new BiogeneratorSound(this));
                soundPlaying = true;
            }

            if (canBurnFuel(items)) {
                burnFuel(items);
                burnTimeRemaining = 200;
            }

            if (canBurnNecro(items)) {
                burnNecro(items);
                necroTimeRemaining = 600;
            }
        }
        else {
            soundPlaying = false;
        }
    }

}
