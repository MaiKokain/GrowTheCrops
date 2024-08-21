package yuria.growitout.actions.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import yuria.growitout.actions.Action;
import yuria.growitout.config.TwerkConfig;

import java.util.function.BooleanSupplier;

public class CropBlock implements Action {
    @Override
    public BooleanSupplier isAvailable() {
        return TRUE;
    }

    @Override
    public boolean canApply(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        Block block = state.getBlock();
        return block instanceof BlockCrops;
    }

    @Override
    public boolean execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        Block block = state.getBlock();
        BlockCrops crops = (BlockCrops) block;

        if (TwerkConfig.core.InstantGrow)
        {
            world.setBlockState(pos, crops.withAge(crops.getMaxAge()), 2);
            return true;
        }

        if (crops.canGrow(world, pos, state, false))
        {
            crops.grow(world, pos, state);
            return true;
        }
        return false;
    }

}
