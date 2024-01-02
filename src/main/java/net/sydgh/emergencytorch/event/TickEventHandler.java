package net.sydgh.emergencytorch.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.sydgh.emergencytorch.EmergencyTorch;

import java.util.ArrayList;

public class TickEventHandler implements ServerTickEvents.EndWorldTick{
    private static int i = 0;
    private static long tick;
    private static long diff;
    private static BlockPos Pos = null;
    @Override
    public void onEndTick(ServerWorld world) {
        tick = world.getTime();
        long initial = (tick - i);
        diff = tick - initial;
        if(diff >= 120 && Pos != null){
        breaker(world);
        i = -1;
        }
        i++;
    }


    public void breaker(ServerWorld world){
        world.breakBlock(Pos,false);
        Pos = null;

    }
    public static void getState(BlockPos pos){
        Pos = pos;
        i = 0;
        //EmergencyTorch.LOGGER.info(String.valueOf(diff));
    }
    }
