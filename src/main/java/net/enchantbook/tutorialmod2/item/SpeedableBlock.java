package net.enchantbook.tutorialmod2.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class SpeedableBlock {

    public BlockPos blockPos;
    public Level level;
    public int tickRate;
    public SpeedableBlock(BlockPos blockPos, Level level, int tickRate) {
        this.blockPos = blockPos;
        this.level = level;
        this.tickRate = tickRate;
    }
}
