package net.sydgh.emergencytorch.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.tick.Tick;

public class TickEventHandler implements ServerTickEvents.EndWorldTick{
    private static int X = 0;
    private static int i = 0;
    private static BlockPos pos;
    @Override
    public void onEndTick(ServerWorld world) {
        long tick = world.getTime();
        if (X == 0){
            TickEventHandler.i = 0;
        } else if (X == 1) {
            long initial = (tick - i);
            long diff = tick - initial;
            if (diff == 120){
                world.breakBlock(pos,false);
                TickEventHandler.X = 0;
            }
            i++;
        }
    }
    public static void getState(int x, BlockPos Pos){
        if (x==0){
            X = 0;
        }
        else {
            X = 1;
            pos = new BlockPos(Pos);
        }
    }
    }
