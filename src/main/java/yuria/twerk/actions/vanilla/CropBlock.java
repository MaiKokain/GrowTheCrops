package yuria.twerk.actions.vanilla;

import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.infinityraider.agricraft.api.v1.crop.IAgriCrop;
import com.infinityraider.agricraft.blocks.BlockCrop;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import yuria.twerk.actions.action;
import yuria.twerk.config.TwerkConfig;

import java.util.function.BooleanSupplier;

public class CropBlock implements action {
    @Override
    public BooleanSupplier isAvailable() {
        return TRUE;
    }

    @Override
    public boolean canApply(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        Block block = state.getBlock();
        return block instanceof BlockCrops && !(block instanceof BlockCrop || block instanceof BlockMysticalCrop);
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
