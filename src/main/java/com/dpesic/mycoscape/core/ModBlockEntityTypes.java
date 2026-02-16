package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.block.entity.AbstractMachineBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Mycoscape.MODID);

    public static final Supplier<BlockEntityType<AbstractMachineBlockEntity>> BIOGENERATOR_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
            "biogenerator_block_entity",
            // The block entity type.
            () -> new BlockEntityType<>(
                    // The supplier to use for constructing the block entity instances.
                    AbstractMachineBlockEntity::new,
                    // An optional value that, when true, only allows players with OP permissions
                    // to load NBT data (e.g. placing a block item)
                    false,
                    // A vararg of blocks that can have this block entity.
                    // This assumes the existence of the referenced blocks as DeferredBlock<Block>s.
                    ModBlocks.BIOGENERATOR.get()
            )
    );
}