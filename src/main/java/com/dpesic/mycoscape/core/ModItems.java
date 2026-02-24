package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.item.BlewitItem;
import com.dpesic.mycoscape.item.JackOLanternMushroomItem;
import com.dpesic.mycoscape.item.MorelItem;
import com.dpesic.mycoscape.item.NecroshroomItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    private ModItems() {}

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, Mycoscape.MODID);



    public static final DeferredHolder<Item, Item> IMBUED_SHARD = ITEMS.register(
            "imbued_shard",
            registryName -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );

    public static final DeferredHolder<Item, Item> DEPLETED_SHARD = ITEMS.register(
            "depleted_shard",
            registryName -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );


    public static final DeferredHolder<Item, Item> ENRICHED_ALLOY = ITEMS.register(
            "enriched_alloy",
            registryName -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );

    public static final DeferredHolder<Item, BlewitItem> BLEWIT = ITEMS.register(
            "blewit",
            registryName -> new BlewitItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );

    public static final DeferredHolder<Item, MorelItem> MOREL = ITEMS.register(
            "morel",
            registryName -> new MorelItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );

    public static final DeferredHolder<Item, JackOLanternMushroomItem> JACK_O_LANTERN_MUSHROOM = ITEMS.register(
            "jack_o_lantern_mushroom",
            registryName -> new JackOLanternMushroomItem( new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );

    public static final DeferredHolder<Item, NecroshroomItem> NECROSHROOM = ITEMS.register(
            "necroshroom",
            registryName -> new NecroshroomItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );

    //Block Items
    public static final DeferredHolder<Item, BlockItem> FUNGAL_SUBSTRATE_BLOCK_ITEM = ITEMS.register(
            "fungal_substrate",
            registryName -> new BlockItem(
                    ModBlocks.FUNGAL_SUBSTRATE.get(),
                    new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_BLOCK_ITEM = ITEMS.register(
            "rotwood",
            registryName -> new BlockItem(
                    ModBlocks.ROTWOOD.get(),
                    new Item.Properties()
                            .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );

    public static final DeferredHolder<Item, BlockItem> FUNGAL_CONDUIT_BLOCK_ITEM = ITEMS.register(
            "fungal_conduit",
            registryName -> new BlockItem(
                    ModBlocks.FUNGAL_CONDUIT.get(),
                    new Item.Properties()
                            .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );

    public static final DeferredHolder<Item, BlockItem> IMBUED_CLUSTER_BLOCK_ITEM = ITEMS.register(
            "imbued_cluster",
            registryName -> new BlockItem(
                    ModBlocks.IMBUED_CLUSTER.get(),
                    new Item.Properties()
                            .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );

    public static final DeferredHolder<Item, BlockItem> DEPLETED_CLUSTER_BLOCK_ITEM = ITEMS.register(
            "depleted_cluster",
            registryName -> new BlockItem(
                    ModBlocks.DEPLETED_CLUSTER.get(),
                    new Item.Properties()
                            .setId(ResourceKey.create(Registries.ITEM, registryName))
            )
    );

}