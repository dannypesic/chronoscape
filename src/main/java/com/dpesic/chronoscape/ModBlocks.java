package com.dpesic.chronoscape;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.SoundType;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Chronoscape.MODID);

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

};

