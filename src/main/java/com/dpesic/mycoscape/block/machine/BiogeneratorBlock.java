package com.dpesic.mycoscape.block.machine;

import com.dpesic.mycoscape.block.entity.AbstractMachineBlockEntity;
import com.dpesic.mycoscape.block.entity.BiogeneratorBlockEntity;
import com.dpesic.mycoscape.core.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import org.jspecify.annotations.Nullable;

public class BiogeneratorBlock extends AbstractMachineBlock implements EntityBlock {
    public static final EnumProperty<Direction> FACING;

    // The important part is implementing the EntityBlock interface and overriding the #newBlockEntity method.
    // Constructor deferring to super.
    public BiogeneratorBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public @Nullable BlockState getStateForPlacement(final BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    // Return a new instance of our block entity here.
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BiogeneratorBlockEntity(pos, state);
    }

    @Override
    protected BlockState rotate(final BlockState state, final Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntityTypes.BIOGENERATOR_BLOCK_ENTITY.get(), AbstractMachineBlockEntity::serverTick);
    }


    static {
        FACING = HorizontalDirectionalBlock.FACING;
    }


}