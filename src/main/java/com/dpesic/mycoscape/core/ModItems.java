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
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    private ModItems() {}


    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, Mycoscape.MODID);

    public static <T extends Block> DeferredHolder<Item, BlockItem> registerBlockItem(String name, DeferredHolder<Block, T> block) {
        return ITEMS.register(
                name,
                registryName -> new BlockItem(
                        block.get(),
                        new Item.Properties()
                                .setId(ResourceKey.create(Registries.ITEM, registryName))
                )
        );
    }

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
    public static final DeferredHolder<Item, BlockItem> FUNGAL_SUBSTRATE_BLOCK_ITEM = registerBlockItem("fungal_substrate", ModBlocks.FUNGAL_SUBSTRATE);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_BLOCK_ITEM = registerBlockItem("rotwood", ModBlocks.ROTWOOD);

    public static final DeferredHolder<Item, BlockItem> FUNGAL_CONDUIT_BLOCK_ITEM = registerBlockItem("fungal_conduit", ModBlocks.FUNGAL_CONDUIT);

    public static final DeferredHolder<Item, BlockItem> IMBUED_CLUSTER_BLOCK_ITEM = registerBlockItem("imbued_cluster", ModBlocks.IMBUED_CLUSTER);

    public static final DeferredHolder<Item, BlockItem> DEPLETED_CLUSTER_BLOCK_ITEM = registerBlockItem("depleted_cluster", ModBlocks.DEPLETED_CLUSTER);


}
