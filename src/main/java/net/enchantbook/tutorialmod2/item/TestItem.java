package net.enchantbook.tutorialmod2.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import org.checkerframework.checker.units.qual.Speed;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestItem extends Item {
    public TestItem(Properties pProperties) {
        super(pProperties);
    }
    public static List<SpeedableBlock> blocks = new ArrayList<SpeedableBlock>();
    public static void SpeedUpBlock(SpeedableBlock block, Level level) {
        BlockState state = level.getBlockState(block.blockPos);
        BlockEntity target = level.getBlockEntity(block.blockPos);

        for (int i = 0; i < block.tickRate; i++) {
            if (target != null) {
                BlockEntityTicker<BlockEntity> ticker = target.getBlockState().getTicker(level, (BlockEntityType<BlockEntity>) target.getType());
                if (ticker != null) {
                    ticker.tick(level,block.blockPos,state,target);
                }

            }
        }
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos blockClicked = pContext.getClickedPos();
            pContext.getPlayer().sendSystemMessage(Component.literal(blockClicked.getX() + "," + blockClicked.getY() + "," + blockClicked.getZ()));
            BlockState state = pContext.getLevel().getBlockState(blockClicked);
            BlockEntity target = pContext.getLevel().getBlockEntity(blockClicked);

            boolean exists = false;
            SpeedableBlock foundBlock = null;
            for (int i = 0; i < blocks.size(); i++) {
                if (blocks.get(i).blockPos.equals(blockClicked)) {
                    pContext.getPlayer().sendSystemMessage(Component.literal("test"));
                    exists = true;
                    foundBlock = blocks.get(i);
                }
            }
            if (foundBlock == null) {
                blocks.add(new SpeedableBlock(blockClicked, pContext.getLevel(), 1));
            } else {
                foundBlock.tickRate *= 2;
                pContext.getPlayer().sendSystemMessage(Component.literal("TickRate: " + foundBlock.tickRate));
            }

        }

        return InteractionResult.SUCCESS;
    }
}
