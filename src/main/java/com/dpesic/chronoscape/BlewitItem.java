package com.dpesic.chronoscape;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class BlewitItem extends Item {
    public BlewitItem(Properties props) {
        super(props);
    }

    protected BlockState placeBlockstate() {
        return ModBlocks.BLEWIT_FUNGUS.get().defaultBlockState();
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos groundPos = ctx.getClickedPos();

        // On #dirt
        BlockState groundState = level.getBlockState(groundPos);
        if (!groundState.is(BlockTags.DIRT)) {
            return InteractionResult.PASS;
        }

        BlockPos placePos = groundPos.above();

        BlockPlaceContext placeCtx = new BlockPlaceContext(ctx);
        BlockState targetState = level.getBlockState(placePos);
        if (!targetState.canBeReplaced(placeCtx)) {
            return InteractionResult.PASS;
        }

        if (!level.mayInteract(ctx.getPlayer(), groundPos)) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {
            BlockState plantState = placeBlockstate();
            if (!plantState.canSurvive(level, placePos)) {
                return InteractionResult.PASS;
            }

            level.setBlock(placePos, plantState, 3);
            level.playSound(null, placePos, SoundEvents.ROOTS_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            ItemStack stack = ctx.getItemInHand();
            if (ctx.getPlayer() == null || !ctx.getPlayer().getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
