package com.dpesic.chronoscape;

import net.minecraft.world.level.block.state.BlockState;

public class JackOLanternMushroomItem extends BlewitItem{

    public JackOLanternMushroomItem(Properties props) {
        super(props);
    }
    @Override
    protected BlockState placeBlockstate() {
        return ModBlocks.JACK_O_LANTERN_FUNGUS.get().defaultBlockState();
    }
}
