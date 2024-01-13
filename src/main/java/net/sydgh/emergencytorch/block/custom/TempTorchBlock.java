package net.sydgh.emergencytorch.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.block.BlockState;
import net.sydgh.emergencytorch.event.TickEventHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class TempTorchBlock extends Block {
    public static final BooleanProperty LIT = BooleanProperty.of("lit");
    protected static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 10.0, 10.0);
    protected final ParticleEffect particle;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
        builder.add(FACING);
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
        if (state0.get(LIT)) {
            double d = (double) pos.getX() + 0.5;
            double e = (double) pos.getY() + 0.7;
            double f = (double) pos.getZ() + 0.5;
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);
        }
    }

    public static int PlaceCount = 0;
    public static ArrayList<BlockPos> Poss;
    public static ArrayList<BlockState> Statee;

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (world.isClient()) {
        }
        else{
            if (PlaceCount == 0) {
                ArrayList<BlockPos> BlockPos = new ArrayList<>();
                BlockPos.add(pos);
                ArrayList<BlockState> BlockState = new ArrayList<>();
                BlockState.add(state);
                Poss = BlockPos;
                Statee = BlockState;
                TickEventHandler.getState(BlockPos, world, BlockState);
                PlaceCount++;
            } else {
                Poss.add(pos);
                Statee.add(state);
                TickEventHandler.getState(Poss, world, Statee);
            }
        }
    }
}
