package net.sydgh.emergencytorch.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sydgh.emergencytorch.EmergencyTorch;
import net.sydgh.emergencytorch.item.custom.TempTorchBlock;

import java.util.ArrayList;

public class TickEventHandler implements ServerTickEvents.StartWorldTick{
    private static int i = 0;
    private static long tick;
    private static long diff;
    private static ArrayList<BlockPos> Pos = null;
    private static int Index;
    private static World World;
    private static int tickTar = 600;

    public void breaker(){
            World.breakBlock(Pos.get(0), false);
            if (Index == 1){
                EmergencyTorch.LOGGER.info("in if 1: "+Index);
                Pos.remove(0);
                TempTorchBlock.PlaceCount = 0;
            }
            else if(Index > 1){
                i = 0;
                Pos.remove(0);
                Index = Pos.size();
                EmergencyTorch.LOGGER.info("in if 2: "+Index);
            }
    }

    public static void getState(ArrayList<BlockPos> pos, World world){
        Pos = pos;
        World = world;
        Index = Pos.size();
        EmergencyTorch.LOGGER.info("size: "+Index);
        if (Index == 1){
        i = 0;}
    }

    @Override
    public void onStartTick(ServerWorld world) {
        tick = world.getTime();
        long initial = (tick - i);
        diff = tick - initial;
        if(Pos != null && diff == tickTar){
            breaker();
        }
        i++;
    }
}
