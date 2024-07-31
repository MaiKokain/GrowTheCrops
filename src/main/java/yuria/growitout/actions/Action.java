package yuria.growitout.actions;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.BooleanSupplier;

public interface Action {
    BooleanSupplier TRUE = () -> true;
    BooleanSupplier FALSE = () -> false;

    BooleanSupplier isAvailable();

    boolean canApply(World world, BlockPos pos, IBlockState state, EntityPlayer player);

    boolean execute(World world, BlockPos pos, IBlockState state, EntityPlayer player);
}
