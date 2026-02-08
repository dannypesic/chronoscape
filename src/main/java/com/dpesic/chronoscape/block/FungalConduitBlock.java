package com.dpesic.chronoscape.block;

import com.dpesic.chronoscape.core.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;

public class FungalConduitBlock extends Block{
    private static final VoxelShape SHAPE;
    public static final EnumProperty<Direction> FACING;

    public FungalConduitBlock(final BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public @Nullable BlockState getStateForPlacement(final BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    protected int getInputSignal(final Level level, final BlockPos pos, final BlockState state) {
        Direction direction = state.getValue(FACING);
        BlockPos targetPos = pos.relative(direction);
        int input = level.getSignal(targetPos, direction);
        if (input >= 15) {
            return input;
        } else {
            BlockState targetBlockState = level.getBlockState(targetPos);
            return Math.max(input, targetBlockState.is(Blocks.REDSTONE_WIRE) ? targetBlockState.getValue(RedStoneWireBlock.POWER) : 0);
        }
    }

    private BlockPos findRandomLog(
            BlockPos blockPos,
            Level level,
            int xRadius,
            int yRadius,
            int zRadius
    ) {
        BlockPos chosen = null;
        int found = 0;
        RandomSource random = level.getRandom();

        for (int x = -xRadius; x <= xRadius; x++) {
            for (int y = -yRadius; y <= yRadius; y++) {
                for (int z = -zRadius; z <= zRadius; z++) {
                    BlockPos checkPos = blockPos.offset(x, y, z);
                    BlockState state = level.getBlockState(checkPos);

                    if (state.is(BlockTags.LOGS)) {
                        found++;

                        // 1 / found chance to replace
                        if (random.nextInt(found) == 0) {
                            chosen = checkPos;
                        }
                    }
                }
            }
        }

        return chosen; // null if no logs found
    }


    public InteractionResult useItemOn(ItemStack held, BlockState state, Level level, BlockPos pos, //change to randomtick
                                       Player player, InteractionHand hand, BlockHitResult hit) {
        int signal = getInputSignal(level, pos, state);

        // custom signal behavior
        if (signal == 1) {
            if (!level.isClientSide()) {
                BlockPos woodPos = findRandomLog(pos, level, 5, 5, 5);
                if (woodPos != null) {
                    level.playSound(null, woodPos, SoundEvents.CREAKING_AMBIENT, SoundSource.BLOCKS, 1.0F, 0.6F + level.random.nextFloat() * 0.2F);
                    level.setBlock(woodPos, ModBlocks.ROTWOOD.get().withPropertiesOf(level.getBlockState(woodPos)), 3);
                }
            }

            return InteractionResult.SUCCESS;
        }



        return InteractionResult.PASS;
    }

    @Override
    protected void neighborChanged(final BlockState state, final Level level, final BlockPos pos, final Block block, final @Nullable Orientation orientation, final boolean movedByPiston) {
        //do stuff on signal strength change
    }

    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    protected boolean useShapeForLightOcclusion(final BlockState state) {
        return true;
    }

    @Override
    public BlockState rotate(final BlockState state, final Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }
    @Override
    public BlockState mirror(final BlockState state, final Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPE;
    }
    @Override
    protected BlockState updateShape(final BlockState state, final LevelReader level, final ScheduledTickAccess ticks, final BlockPos pos, final Direction directionToNeighbour, final BlockPos neighbourPos, final BlockState neighbourState, final RandomSource random) {
        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        SHAPE = Block.column(16.0F, 0.0F, 8.0F);}
}
