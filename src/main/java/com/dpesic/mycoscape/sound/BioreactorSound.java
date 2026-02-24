package com.dpesic.mycoscape.sound;

import com.dpesic.mycoscape.block.entity.AbstractMachineBlockEntity;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class BioreactorSound extends AbstractTickableSoundInstance {

    private final AbstractMachineBlockEntity machine;

    public BioreactorSound(AbstractMachineBlockEntity machine) {
        super(SoundEvents.BEACON_AMBIENT, SoundSource.BLOCKS, SoundInstance.createUnseededRandom());
        this.machine = machine;
        this.looping = true;
        this.delay = 0;
        this.volume = 1.0f;
        this.pitch = 0.8f;
        this.x = machine.getBlockPos().getX() + 0.5;
        this.y = machine.getBlockPos().getY() + 0.5;
        this.z = machine.getBlockPos().getZ() + 0.5;
    }

    @Override
    public void tick() {
        if (machine.isRemoved() || (machine.toggled == 0)) {
            this.stop();
        }
    }
}
