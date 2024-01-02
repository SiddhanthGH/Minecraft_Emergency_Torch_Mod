package net.sydgh.emergencytorch.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.sydgh.emergencytorch.EmergencyTorch;
import net.sydgh.emergencytorch.item.custom.TempTorchBlock;

import java.util.ArrayList;

public class TickEventHandler implements ServerTickEvents.StartWorldTick{
    private static int i = 0;
    private static long tick;
    private static long diff;
    private static ArrayList<BlockPos> Pos = null;
    private static int Index;

    public void breaker(ServerWorld world){
            world.breakBlock(Pos.get(0), false);
            EmergencyTorch.LOGGER.info(String.valueOf(Index));
            if (Index <= 1){
                EmergencyTorch.LOGGER.info("in if 1: "+Index);
                TempTorchBlock.PlaceCount = 0;
            }
            else if(Index > 1){
                diff = 0;
                i = 0;
                TempTorchBlock.Pos.remove(0);
                Pos.remove(0);
                Index = Pos.size();
                EmergencyTorch.LOGGER.info("in if 2: "+Index);
            }
    }

    public static void getState(ArrayList<BlockPos> pos){
        Pos = pos;
        EmergencyTorch.LOGGER.info("size: "+Pos.size());
        Index = Pos.size();
        i = 0;
    }

    @Override
    public void onStartTick(ServerWorld world) {
        tick = world.getTime();
        long initial = (tick - i);
        diff = tick - initial;
        if(Pos != null && diff == (600 * (Index/2))){
            breaker(world);
        }
        i++;
    }
}
