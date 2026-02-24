package com.dpesic.mycoscape.block.entity;

import com.dpesic.mycoscape.block.machine.BiogeneratorBlock;
import com.dpesic.mycoscape.core.ModBlockEntityTypes;
import com.dpesic.mycoscape.inventory.BiogeneratorMenu;
import com.dpesic.mycoscape.sound.BioreactorSound;
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
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.WorldlyContainer;



public class AbstractMachineBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer{
    private static final Component DEFAULT_NAME = Component.translatable("container.biogenerator");
    protected NonNullList<ItemStack> items;
    private int burnTimeRemaining;
    public int toggled;
    private int necroTimeRemaining;
    protected final ContainerData dataAccess;
    private boolean soundPlaying = false;
    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1};
    private static final int[] SLOTS_EMPTY = new int[0];

    public AbstractMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BIOGENERATOR_BLOCK_ENTITY.get(), pos, state);
        this.items = NonNullList.withSize(2, ItemStack.EMPTY);
        this.dataAccess = new ContainerData() {
            public int get(final int dataId) {
                switch (dataId) {
                    case 0 -> {
                        return AbstractMachineBlockEntity.this.burnTimeRemaining;
                    }
                    case 1 -> {
                        return AbstractMachineBlockEntity.this.toggled;
                    }
                    case 2 -> {
                        return AbstractMachineBlockEntity.this.necroTimeRemaining;
                    }
                    default -> {
                        return 0;
                    }
                }
            }

            public void set(final int dataId, final int value) {
                switch (dataId) {
                    case 0 -> AbstractMachineBlockEntity.this.burnTimeRemaining = value;
                    case 1 -> AbstractMachineBlockEntity.this.toggled = value;
                    case 2 -> AbstractMachineBlockEntity.this.necroTimeRemaining = value;

                }

            }

            public int getCount() {
                return 3;
            }
        };

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

    protected AbstractContainerMenu createMenu(final int containerId, final Inventory inventory) {
        return new BiogeneratorMenu( containerId, inventory, this, this.dataAccess);
    }

    protected void loadAdditional(final ValueInput input) {
        super.loadAdditional(input);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(input, this.items);
        this.burnTimeRemaining = input.getShortOr("burn_time_remaining", (short)0);
        this.toggled = input.getShortOr("toggled", (short)0);
        this.necroTimeRemaining = input.getShortOr("necroshroom_time_remaining", (short)0);

    }

    protected void saveAdditional(final ValueOutput output) {
        super.saveAdditional(output);
        output.putShort("burn_time_remaining", (short)this.burnTimeRemaining);
        output.putShort("toggled", (short)this.toggled);
        output.putShort("necroshroom_time_remaining", (short)this.necroTimeRemaining);
        ContainerHelper.saveAllItems(output, this.items);
    }

    public void toggle() {
        toggled = toggled == 0 ? 1 : 0;
    }

    private static boolean canBurnFuel(final NonNullList<ItemStack> items,  AbstractMachineBlockEntity be) {
        return be.burnTimeRemaining <= 0 && !items.getFirst().isEmpty() && be.necroTimeRemaining > 0;
    }

    private static boolean canBurnNecro(final NonNullList<ItemStack> items,  AbstractMachineBlockEntity be) {
        return be.necroTimeRemaining <= 0 && !items.get(0).isEmpty() && !items.get(1).isEmpty();
    }

    private static void burnFuel(final NonNullList<ItemStack> items) {
        items.getFirst().shrink(1);
    }
    private static void burnNecro(final NonNullList<ItemStack> items) {
        items.get(1).shrink(1);
    }

    private static boolean isSoundPlaying(AbstractMachineBlockEntity be) {
        return be.soundPlaying;
    }

    public static void serverTick( Level level, BlockPos pos, BlockState state, AbstractMachineBlockEntity be) {
        if (be.toggled == 1) {
            if (be.burnTimeRemaining > 0) {be.burnTimeRemaining--;}
            if (be.necroTimeRemaining > 0) {be.necroTimeRemaining--;}

            if (!isSoundPlaying(be) && be.burnTimeRemaining > 0) {
                Minecraft.getInstance().getSoundManager()
                        .play(new BioreactorSound(be));
                be.soundPlaying = true;
            }

            if (canBurnFuel(be.items, be)) {
                burnFuel(be.items);
                be.burnTimeRemaining = 200;
            }

            if (canBurnNecro(be.items, be)) {
                burnNecro(be.items);
                be.necroTimeRemaining = 600;
            }
        // also add hopper stuff that would be useful
        }
        else {
            be.soundPlaying = false;
        }
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


}