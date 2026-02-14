package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.block.*;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.SoundType;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Mycoscape.MODID);

    @SuppressWarnings("removal")
    public static final DeferredBlock<BlewitFungusBlock> BLEWIT_FUNGUS = BLOCKS.registerBlock(
            "blewit_fungus",
            BlewitFungusBlock::new,
            BlockBehaviour.Properties.of()
                    .noCollision()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.ROOTS)
    );

    @SuppressWarnings("removal")
    public static final DeferredBlock<MorelFungusBlock> MOREL_FUNGUS = BLOCKS.registerBlock(
            "morel_fungus",
            MorelFungusBlock::new,
            BlockBehaviour.Properties.of()
                    .noCollision()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.ROOTS)
    );

    @SuppressWarnings("removal")
    public static final DeferredBlock<JackOLanternFungusBlock> JACK_O_LANTERN_FUNGUS = BLOCKS.registerBlock(
            "jack_o_lantern_fungus",
            JackOLanternFungusBlock::new,
            BlockBehaviour.Properties.of()
                    .noCollision()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.ROOTS)
    );

    @SuppressWarnings("removal")
    public static final DeferredBlock<NecroshroomFungusBlock> NECROSHROOM_FUNGUS = BLOCKS.registerBlock(
            "necroshroom_fungus",
            NecroshroomFungusBlock::new,
            BlockBehaviour.Properties.of()
                    .noCollision()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.ROOTS)
    );

    @SuppressWarnings("removal")
    public static final DeferredBlock<FungalConduitBlock> FUNGAL_CONDUIT = BLOCKS.registerBlock(
            "fungal_conduit",
            FungalConduitBlock::new,
            BlockBehaviour.Properties.of()
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .destroyTime(0.2f)
                    .explosionResistance(0.5f)

    );

    @SuppressWarnings("removal")
    public static final DeferredBlock<RotatedPillarBlock> ROTWOOD = BLOCKS.registerBlock(
            "rotwood",
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.of()
                    .sound(SoundType.MANGROVE_ROOTS)
                    .destroyTime(0.5f)
                    .explosionResistance(0.5f)

    );

    @SuppressWarnings("removal")
    public static final DeferredBlock<ClusterBlock> IMBUED_CLUSTER = BLOCKS.registerBlock(
            "imbued_cluster",
            ClusterBlock::new,
            BlockBehaviour.Properties.of()
                    .sound(SoundType.AMETHYST_CLUSTER)
                    .destroyTime(1.5f)
                    .explosionResistance(1.5f)

    );

    @SuppressWarnings("removal")
    public static final DeferredBlock<ClusterBlock> DEPLETED_CLUSTER = BLOCKS.registerBlock(
            "depleted_cluster",
            ClusterBlock::new,
            BlockBehaviour.Properties.of()
                    .sound(SoundType.AMETHYST_CLUSTER)
                    .destroyTime(1.5f)
                    .explosionResistance(1.5f)

    );

    @SuppressWarnings("removal")
    public static final DeferredBlock<Block> FUNGAL_SUBSTRATE = BLOCKS.registerSimpleBlock(
            "fungal_substrate",
            BlockBehaviour.Properties.of()
                    .sound(SoundType.MUDDY_MANGROVE_ROOTS)
                    .destroyTime(0.5f)
                    .explosionResistance(0.5f)
    );

};