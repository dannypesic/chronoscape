package com.dpesic.mycoscape.block.machine;

import com.dpesic.mycoscape.block.entity.AbstractMachineBlockEntity;
import com.dpesic.mycoscape.block.entity.BiogeneratorBlockEntity;
import com.dpesic.mycoscape.core.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public abstract class AbstractMachineBlock extends Block implements EntityBlock {
    public AbstractMachineBlock(Properties props) {
        super(props);
    }

    public abstract BlockEntity newBlockEntity(BlockPos pos, BlockState state);

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide() && player instanceof ServerPlayer sp) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof AbstractMachineBlockEntity machine) {
                sp.openMenu(machine, buf -> buf.writeBlockPos(pos));
            }
        }

        return level.isClientSide()
                ? InteractionResult.SUCCESS
                : InteractionResult.SUCCESS_SERVER;
    }


    @SuppressWarnings("unchecked")
    public static <E extends BlockEntity, A extends BlockEntity> @Nullable BlockEntityTicker<A> createTickerHelper(
            BlockEntityType<A> type, BlockEntityType<E> checkedType, BlockEntityTicker<? super E> ticker
    ) {
        return checkedType == type ? (BlockEntityTicker<A>) ticker : null;
    }


}
