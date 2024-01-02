package net.sydgh.emergencytorch.item.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.block.BlockState;

public class TempTorchBlock extends Block {
    protected static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 10.0, 10.0);
    protected final ParticleEffect particle;

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    }

    public TempTorchBlock(AbstractBlock.Settings settings, ParticleEffect particle) {
        super(settings);
        this.particle = particle;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BOUNDING_SHAPE;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !this.canPlaceAt(state, world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return TorchBlock.sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    public void randomDisplayTick(BlockState state0, World world, BlockPos pos, Random random) {
        double d = (double) pos.getX() + 0.5;
        double e = (double) pos.getY() + 0.7;
        double f = (double) pos.getZ() + 0.5;
        world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
        world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        return ActionResult.SUCCESS;
    }

}