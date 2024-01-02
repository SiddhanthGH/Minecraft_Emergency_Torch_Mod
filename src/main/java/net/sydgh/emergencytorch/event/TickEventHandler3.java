package net.sydgh.emergencytorch.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.sydgh.emergencytorch.EmergencyTorch;
import net.sydgh.emergencytorch.item.custom.TempTorchBlock;

import java.util.ArrayList;

public class TickEventHandler3 implements ServerTickEvents.EndWorldTick{
    private static int i = 0;
    private static long tick;
    private static long diff;
    private static ArrayList<BlockPos> Pos = null;
    private static int Index;
    @Override
    public void onEndTick(ServerWorld world) {
        tick = world.getTime();
        long initial = (tick - i);
        diff = tick - initial;
        if(diff >= 120 && Pos != null){
        breaker(world);
        }
        i++;
    }

    public void breaker(ServerWorld world){
            world.breakBlock(Pos.get(0), false);
            EmergencyTorch.LOGGER.info(String.valueOf(Index));
            if (Index == 0){
                EmergencyTorch.LOGGER.info("in if 1: "+Index);
                TempTorchBlock.PlaceCount = 0;
            }
            else if(Index > 0){
                i = 0;
                TempTorchBlock.Pos.remove(0);
            }
    }
    public static void getState(ArrayList<BlockPos> pos){
        Pos = pos;
        EmergencyTorch.LOGGER.info("size: "+Pos.size());
        Index = Pos.size() - 1;
        i = 0;
    }
    }
