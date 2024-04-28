package net.enchantbook.tutorialmod2.events;

import net.enchantbook.tutorialmod2.item.SpeedableBlock;
import net.enchantbook.tutorialmod2.item.TestItem;
import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = "tutorialmod2", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TickEventHandler {

    @SubscribeEvent
    public static void ticks(TickEvent event) {
        for (int i = 0; i < TestItem.blocks.size(); i++) {
            TestItem.SpeedUpBlock(TestItem.blocks.get(i), TestItem.blocks.get(i).level );
        }
    }
}
