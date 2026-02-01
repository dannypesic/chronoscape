package com.dpesic.chronoscape;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MorelFungusBlock extends BlewitFungusBlock{

    public MorelFungusBlock(Properties props) {
        super(props);
    }

    @Override
    protected ItemStack dropItemstack() {
        int dropCount = 1;
        return new ItemStack(ModItems.MOREL.get(), dropCount);
    }

    @Override
    protected VoxelShape shapeMycelium() {
        return Block.column(14.0D, 0.0D, 2.0D);
    }

    @Override
    protected VoxelShape shapeMushroom() {
        return Block.column(14.0D, 0.0D, 15.0D);
    }


}
