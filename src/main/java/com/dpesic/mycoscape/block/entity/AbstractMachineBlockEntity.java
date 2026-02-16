package com.dpesic.mycoscape.block.entity;

import com.dpesic.mycoscape.core.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AbstractMachineBlockEntity extends BlockEntity {
    public AbstractMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BIOGENERATOR_BLOCK_ENTITY.get(), pos, state);
    }
}