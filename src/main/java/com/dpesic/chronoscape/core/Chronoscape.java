package com.dpesic.chronoscape.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Chronoscape.MODID)
public class Chronoscape {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "chronoscape";
    private final IEventBus modBus;

    public Chronoscape(IEventBus modBus) {
        this.modBus = modBus;
        ModItems.ITEMS.register(modBus);
        ModBlocks.BLOCKS.register(modBus);
        modBus.addListener(Chronoscape::addCreative);
    }




    private static void addCreative(BuildCreativeModeTabContentsEvent event) {

        // Ingredients tab
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {

            event.insertAfter(
                    new ItemStack(Items.AMETHYST_SHARD),
                    new ItemStack(ModItems.IMBUED_SHARD.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );

            event.insertAfter(
                    new ItemStack(Items.NETHERITE_INGOT),
                    new ItemStack(ModItems.ENRICHED_ALLOY.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );

        }

        // Natural Blocks tab
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {

            event.insertAfter(
                    new ItemStack(Items.WARPED_FUNGUS),
                     new ItemStack(ModItems.BLEWIT.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );

            event.insertAfter(
                    new ItemStack(ModItems.BLEWIT.get()),
                    new ItemStack(ModItems.MOREL.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );

            event.insertAfter(
                    new ItemStack(ModItems.MOREL.get()),
                    new ItemStack(ModItems.JACK_O_LANTERN_MUSHROOM.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );

            event.insertAfter(
                    new ItemStack(ModItems.JACK_O_LANTERN_MUSHROOM.get()),
                    new ItemStack(ModItems.NECROSHROOM.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );

            event.insertAfter(
                    new ItemStack(ModItems.NECROSHROOM.get()),
                    new ItemStack(ModItems.FUNGAL_SUBSTRATE_BLOCK_ITEM.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.insertAfter(
                    new ItemStack(ModItems.FUNGAL_SUBSTRATE_BLOCK_ITEM.get()),
                    new ItemStack(ModItems.ROTWOOD_BLOCK_ITEM.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.insertAfter(
                    new ItemStack(ModItems.ROTWOOD_BLOCK_ITEM.get()),
                    new ItemStack(ModItems.FUNGAL_CONDUIT_BLOCK_ITEM.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );


        }



    }

}
