package yuria.growitout.actions.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import yuria.growitout.actions.Action;

import java.util.function.BooleanSupplier;

public class BoneMeal implements Action {
    @Override
    public BooleanSupplier isAvailable() {
        return TRUE;
    }

    @Override
    public boolean canApply(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        Block block = state.getBlock();

        if (block instanceof IGrowable)
        {
            IGrowable growable = (IGrowable) block;
            if (growable.canUseBonemeal(world, world.rand, pos, state)) {
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public boolean execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        ItemStack bonemeal = new ItemStack(Items.DYE, 1, 15);
        return ItemDye.applyBonemeal(bonemeal, world, pos, player, player.getActiveHand());
    }
}
