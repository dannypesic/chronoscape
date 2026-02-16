package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.item.BlewitItem;
import com.dpesic.mycoscape.item.JackOLanternMushroomItem;
import com.dpesic.mycoscape.item.MorelItem;
import com.dpesic.mycoscape.item.NecroshroomItem;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    private ModItems() {}


    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Mycoscape.MODID);



    public static final DeferredItem<Item> IMBUED_SHARD = ITEMS.registerSimpleItem(
            "imbued_shard"
    );

    public static final DeferredItem<Item> DEPLETED_SHARD = ITEMS.registerSimpleItem(
            "depleted_shard"
    );

    public static final DeferredItem<Item> ENRICHED_ALLOY = ITEMS.registerSimpleItem(
            "enriched_alloy"
    );

    @SuppressWarnings("removal")
    public static final DeferredItem<BlewitItem> BLEWIT = ITEMS.registerItem(
            "blewit",
            BlewitItem::new,
            new Item.Properties()
    );

    @SuppressWarnings("removal")
    public static final DeferredItem<MorelItem> MOREL = ITEMS.registerItem(
            "morel",
            MorelItem::new,
            new Item.Properties()
    );

    @SuppressWarnings("removal")
    public static final DeferredItem<JackOLanternMushroomItem> JACK_O_LANTERN_MUSHROOM = ITEMS.registerItem(
            "jack_o_lantern_mushroom",
            JackOLanternMushroomItem::new,
            new Item.Properties()
    );

    @SuppressWarnings("removal")
    public static final DeferredItem<NecroshroomItem> NECROSHROOM = ITEMS.registerItem(
            "necroshroom",
            NecroshroomItem::new,
            new Item.Properties()
    );


    // Block Items
    @SuppressWarnings("removal")
    public static final DeferredItem<BlockItem> FUNGAL_SUBSTRATE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            "fungal_substrate",
            ModBlocks.FUNGAL_SUBSTRATE,
            new Item.Properties()
    );

    @SuppressWarnings("removal")
    public static final DeferredItem<BlockItem> ROTWOOD_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            "rotwood",
            ModBlocks.ROTWOOD,
            new Item.Properties()
    );

    @SuppressWarnings("removal")
    public static final DeferredItem<BlockItem> FUNGAL_CONDUIT_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            "fungal_conduit",
            ModBlocks.FUNGAL_CONDUIT,
            new Item.Properties()
    );

    @SuppressWarnings("removal")
    public static final DeferredItem<BlockItem> IMBUED_CLUSTER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            "imbued_cluster",
            ModBlocks.IMBUED_CLUSTER,
            new Item.Properties()
    );

    @SuppressWarnings("removal")
    public static final DeferredItem<BlockItem> DEPLETED_CLUSTER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            "depleted_cluster",
            ModBlocks.DEPLETED_CLUSTER,
            new Item.Properties()
    );

    @SuppressWarnings("removal")
    public static final DeferredItem<BlockItem> BIOGENERATOR_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            "biogenerator",
            ModBlocks.BIOGENERATOR,
            new Item.Properties()
    );
}