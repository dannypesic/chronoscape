package com.dpesic.chronoscape;

import net.minecraft.world.level.block.state.BlockState;

public class MorelItem extends BlewitItem{

    public MorelItem(Properties props) {
        super(props);
    }
    @Override
    protected BlockState placeBlockstate() {
        return ModBlocks.MOREL_FUNGUS.get().defaultBlockState();
    }
}
