package yuria.growitout.actions.compats;

import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.infinityraider.agricraft.blocks.BlockCrop;
import com.infinityraider.agricraft.tiles.TileEntityCrop;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import yuria.growitout.actions.Action;
import yuria.growitout.config.TwerkConfig;

import java.util.function.BooleanSupplier;

public class AgricraftAction implements Action {
    @Override
    public BooleanSupplier isAvailable()
    {
        return () -> Loader.isModLoaded("agricraft") && TwerkConfig.ac.enableAC;
    }

    @Override
    public boolean canApply(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        Block block = state.getBlock();
        if (block instanceof BlockCrop && !(block instanceof BlockMysticalCrop))
        {
            TileEntityCrop tile = ((BlockCrop) block).getCropTile(world, pos).get();
            return !tile.isMature() && tile.hasSeed();
        }
        return false;
    }

    @Override
    public boolean execute(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        BlockCrop crop = (BlockCrop) state.getBlock();
        TileEntityCrop tile = crop.getCrop(world, pos).get();

        if (TwerkConfig.ac.InstantGrowAC) {
            tile.setGrowthStage(tile.getSeed().getPlant().getGrowthStages());
            return true;
        }
        tile.applyGrowthTick();
        return true;
    }
}
