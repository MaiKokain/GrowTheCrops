package ts2k16.core.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface GrowthManagerProxy
{
    int sprintTime = 0;
    boolean wasSprinting = false;
    int crouchTime = 0;
    boolean wasCrouched = false;
    void updatePlayer(EntityPlayer player);
    void pulseGrowth(World world, BlockPos pos, EntityPlayer player);
}
