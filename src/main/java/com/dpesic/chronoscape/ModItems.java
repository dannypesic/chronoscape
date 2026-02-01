package com.dpesic.chronoscape;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    private ModItems() {}

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Chronoscape.MODID);



    public static final DeferredItem<Item> IMBUED_SHARD = ITEMS.registerSimpleItem(
            "imbued_shard"
    );

    public static final DeferredItem<BlewitItem> BLEWIT = ITEMS.registerItem(
            "blewit",
            BlewitItem::new,
            new Item.Properties()
    );

    public static final DeferredItem<MorelItem> MOREL = ITEMS.registerItem(
            "morel",
            MorelItem::new,
            new Item.Properties()
    );

    public static final DeferredItem<JackOLanternMushroomItem> JACK_O_LANTERN_MUSHROOM = ITEMS.registerItem(
            "jack_o_lantern_mushroom",
            JackOLanternMushroomItem::new,
            new Item.Properties()
    );
}
