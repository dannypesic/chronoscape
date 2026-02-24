package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import com.dpesic.mycoscape.block.machine.BiogeneratorBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.SoundType;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, Mycoscape.MODID);


    public static final DeferredHolder<Block, BlewitFungusBlock> BLEWIT_FUNGUS = BLOCKS.register(
            "blewit_fungus",
            registryName -> new BlewitFungusBlock(
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .noCollision()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.ROOTS)
            )
    );

    public static final DeferredHolder<Block, MorelFungusBlock> MOREL_FUNGUS = BLOCKS.register(
            "morel_fungus",
            registryName -> new MorelFungusBlock(
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .noCollision()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.ROOTS)
            )
    );

    public static final DeferredHolder<Block, JackOLanternFungusBlock> JACK_O_LANTERN_FUNGUS = BLOCKS.register(
            "jack_o_lantern_fungus",
            registryName -> new JackOLanternFungusBlock(
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .noCollision()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.ROOTS)
            )
    );

    public static final DeferredHolder<Block, NecroshroomFungusBlock> NECROSHROOM_FUNGUS = BLOCKS.register(
            "necroshroom_fungus",
            registryName -> new NecroshroomFungusBlock(
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .noCollision()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.ROOTS)
            )
    );

    public static final DeferredHolder<Block, FungalConduitBlock> FUNGAL_CONDUIT = BLOCKS.register(
            "fungal_conduit",
            registryName -> new FungalConduitBlock(
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .sound(SoundType.ANCIENT_DEBRIS)
                            .destroyTime(0.2f)
                            .explosionResistance(0.5f)
            )
    );

    public static final DeferredHolder<Block, RotatedPillarBlock> ROTWOOD = BLOCKS.register(
            "rotwood",
            registryName -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .sound(SoundType.MANGROVE_ROOTS)
                            .destroyTime(0.5f)
                            .explosionResistance(0.5f)
            )
    );

    public static final DeferredHolder<Block, ClusterBlock> IMBUED_CLUSTER = BLOCKS.register(
            "imbued_cluster",
            registryName -> new ClusterBlock(
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .sound(SoundType.AMETHYST_CLUSTER)
                            .destroyTime(1.5f)
                            .explosionResistance(1.5f)
            )
    );

    public static final DeferredHolder<Block, ClusterBlock> DEPLETED_CLUSTER = BLOCKS.register(
            "depleted_cluster",
            registryName -> new ClusterBlock(
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .sound(SoundType.AMETHYST_CLUSTER)
                            .destroyTime(1.5f)
                            .explosionResistance(1.5f)
            )
    );

    public static final DeferredHolder<Block, Block> FUNGAL_SUBSTRATE = BLOCKS.register(
            "fungal_substrate",
            registryName -> new Block(
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .sound(SoundType.MUDDY_MANGROVE_ROOTS)
                            .destroyTime(0.5f)
                            .explosionResistance(0.5f)
            )
    );
    public static final DeferredHolder<Block, BiogeneratorBlock> BIOGENERATOR = BLOCKS.register(
            "biogenerator",
            registryName -> new BiogeneratorBlock(
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .sound(SoundType.MUDDY_MANGROVE_ROOTS)
                            .destroyTime(0.5f)
                            .explosionResistance(0.5f)
            )
    );
}