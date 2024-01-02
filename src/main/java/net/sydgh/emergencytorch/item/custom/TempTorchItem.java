package net.sydgh.emergencytorch.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.sydgh.emergencytorch.EmergencyTorch;
import net.sydgh.emergencytorch.block.ModBlocks;
import net.sydgh.emergencytorch.item.ModItems;

public class TempTorchItem extends Item {
    public TempTorchItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()){

            BlockPos positionClicked = context.getBlockPos();
            BlockState state = context.getWorld().getBlockState(positionClicked);
            PlayerEntity player = context.getPlayer();

            if (isValid(state)){

                context.getStack().decrement(1);
                player.getInventory().insertStack(ModItems.TempTorch.getDefaultStack());

            }

        }

        return ActionResult.SUCCESS;
    }

    private boolean isValid(BlockState state) {
        return state.isOf(Blocks.COBBLESTONE) || state.isOf(Blocks.ANDESITE);
    }
}
