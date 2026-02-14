package com.dpesic.mycoscape.block;

import com.dpesic.mycoscape.core.ModBlocks;
import com.dpesic.mycoscape.tags.MycoscapeBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;

public class FungalConduitBlock extends Block{
    private static final VoxelShape SHAPE;
    public static final EnumProperty<Direction> FACING;
    public static final IntegerProperty POWER = BlockStateProperties.POWER;


    public FungalConduitBlock(final BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(POWER,0));
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

    private BlockPos findRandomBlock(
            BlockPos blockPos,
            Level level,
            TagKey<Block> blockTag,
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

                    if (state.is(blockTag)) {
                        found++;

                        // 1 / found chance to replace
                        if (random.nextInt(found) == 0) {
                            chosen = checkPos;
                        }
                    }
                }
            }
        }

        return chosen; // null if no blocks found
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        int signal = getInputSignal(level, pos, state);

        if (rand.nextInt(5) == 0) {

            // custom signal behavior
            if (signal == 15) {
                if (!level.isClientSide()) {
                    BlockPos woodPos = findRandomBlock(pos, level, BlockTags.LOGS, 5, 5, 5);
                    if (woodPos != null) {
                        level.playSound(null, woodPos, SoundEvents.CREAKING_AMBIENT, SoundSource.BLOCKS, 1.0F, 0.6F + level.random.nextFloat() * 0.2F);
                        level.setBlock(woodPos, ModBlocks.ROTWOOD.get().withPropertiesOf(level.getBlockState(woodPos)), 3);
                    }
                }

            }
            if (signal < 15 && signal != 0) {
                if (!level.isClientSide()) {
                    BlockPos clusterPos = findRandomBlock(pos, level, MycoscapeBlockTags.IMBUED_CLUSTER, 5, 5, 5);
                    if (clusterPos != null) {
                        level.playSound(null, clusterPos, SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 1.0F, 0.6F + level.random.nextFloat() * 0.3F);
                        level.setBlock(clusterPos, ModBlocks.DEPLETED_CLUSTER.get().withPropertiesOf(level.getBlockState(clusterPos)), 3);
                    }
                }

            }
        }

    }

    @Override
    protected void neighborChanged(final BlockState state, final Level level, final BlockPos pos, final Block block, final @Nullable Orientation orientation, final boolean movedByPiston) {
        int current = getInputSignal(level, pos, state);
        int previous = state.getValue(POWER);

        if (current != previous) {

            if (current == 15) {
                level.playSound(null, pos, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 1.5F, 0.8F);
            }

            if (current < 15 && current > 0 && previous == 0) {
                level.playSound(null, pos, SoundEvents.BEACON_DEACTIVATE, SoundSource.BLOCKS, 1.5F, 0.8F);
            }

            level.setBlock(pos, state.setValue(POWER, current), 3);
        }

    }

    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, POWER);
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
