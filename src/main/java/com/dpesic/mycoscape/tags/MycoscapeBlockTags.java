package com.dpesic.mycoscape.tags;

import com.dpesic.mycoscape.core.Mycoscape;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.Identifier;

public class MycoscapeBlockTags {

    public static final TagKey<Block> FUNGI_GROUND = mycoscapeTag("fungi_ground");
    public static final TagKey<Block> ROTWOOD = mycoscapeTag("rotwood");
    public static final TagKey<Block> IMBUED_CLUSTER = mycoscapeTag("imbued_cluster");

    private MycoscapeBlockTags() {
    }
    static Identifier id(String id) {
        return Identifier.fromNamespaceAndPath(Mycoscape.MODID, id);
    }

    public static TagKey<Block> mycoscapeTag(String path) {
        return TagKey.create(Registries.BLOCK, id(path));
    }
}
