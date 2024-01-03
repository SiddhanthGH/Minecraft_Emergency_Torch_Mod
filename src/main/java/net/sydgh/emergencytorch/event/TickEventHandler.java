package net.sydgh.emergencytorch.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sydgh.emergencytorch.EmergencyTorch;
import net.sydgh.emergencytorch.item.custom.TempTorchBlock;

import java.util.ArrayList;

public class TickEventHandler implements ServerTickEvents.StartWorldTick{
    private static long tick;
    private static long diff;
    private static ArrayList<BlockPos> Pos = null;
    private static int Index;
    private static World World;
    private static boolean timer1;

    public void breaker(){
            World.breakBlock(Pos.get(0), false);
            if (Index == 1){
                TempTorchBlock.PlaceCount = 0;
                timer1 = false;
                Pos = null;
                iStore = null;
            }
            else if(Index > 1){
                Pos.remove(0);
                iStore.remove(0);
                Index = Pos.size();
            }
    }

    private static ArrayList<Long> iStore;

    public static void getState(ArrayList<BlockPos> pos, World world){
        Pos = pos;
        World = world;
        Index = Pos.size();
        timer1 = true;
        if (Index == 1){
            iStore = new ArrayList<>();
            iStore.add(tick);
        }
        else if (Index > 1){
            iStore.add(tick);
        }
    }

    @Override
    public void onStartTick(ServerWorld world) {
        tick = world.getTime();
        if (timer1) {
            diff = tick - iStore.get(0);
            if (Pos != null && diff == 1200) {
                breaker();
            }
        }
    }
}
