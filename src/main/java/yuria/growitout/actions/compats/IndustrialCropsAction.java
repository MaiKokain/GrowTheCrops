package yuria.growitout.actions.compats;

import ic2.api.crops.CropCard;
import ic2.core.IC2;
import ic2.core.crop.TileEntityCrop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import yuria.growitout.Twerk;
import yuria.growitout.actions.Action;
import yuria.growitout.config.TwerkConfig;

import java.util.function.BooleanSupplier;

public class IndustrialCropsAction implements Action {
    @Override
    public BooleanSupplier isAvailable() {
        return () -> Loader.isModLoaded("ic2") && TwerkConfig.ic2.enableIC2;
    }

    @Override
    public boolean canApply(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (!(world.getTileEntity(pos) instanceof TileEntityCrop)) return false;

        TileEntityCrop tile = (TileEntityCrop) world.getTileEntity(pos);
        CropCard crop = tile.getCrop();

        return crop.canGrow(tile);
    }

    @Override
    public boolean execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        TileEntityCrop tile = (TileEntityCrop) world.getTileEntity(pos);
        CropCard crop = tile.getCrop();

        if (TwerkConfig.ic2.InstantGrowIC2)
        {
            tile.setCurrentSize(crop.getMaxSize());
            tile.dirty = true;
            return true;
        }

        switch (TwerkConfig.ic2.ic2Methods) {
            case PERFORM_TICK:
                tile.performTick();
                return true;
            case PERFORM_GROWTH_TICK:
                tile.performGrowthTick();
                if (tile.getGrowthPoints() >= crop.getGrowthDuration(tile)) {
                    tile.setGrowthPoints(0);
                    tile.setCurrentSize(tile.getCurrentSize()+1);
                    tile.dirty = true;
                }
                return true;
            default:
                throw new IllegalStateException("Illegal method");
        }
    }
}
