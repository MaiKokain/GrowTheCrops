package yuria.twerk.actions.compats;

import com.blakebr0.mysticalagriculture.blocks.crop.BlockInferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import yuria.twerk.Twerk;
import yuria.twerk.actions.vanilla.CropBlock;
import yuria.twerk.config.TwerkConfig;

import java.util.function.BooleanSupplier;

public class MysticalAction extends CropBlock {
    @Override
    public BooleanSupplier isAvailable() {
        return () -> Loader.isModLoaded("mysticalagriculture") && TwerkConfig.ma.enableMA;
    }

    @Override
    public boolean canApply(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        Block block = state.getBlock();
        return block instanceof BlockMysticalCrop;
    }

    @Override
    public boolean execute(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        Block block = state.getBlock();
        BlockCrops crops = (BlockCrops) block;

        if (!TwerkConfig.ma.InstantGrowMA) return super.execute(world, pos, state, player);

        world.setBlockState(pos, crops.withAge(crops.getMaxAge()), 2);
        return true;
    }
}
