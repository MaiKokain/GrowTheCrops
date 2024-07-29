package yuria.twerk;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import yuria.twerk.actions.action;
import yuria.twerk.actions.actions;
import yuria.twerk.config.TwerkConfig;

import java.util.Set;

public class TwerkAction {
    public static void perform(World world, EntityPlayer player)
    {
        if (!player.isSneaking()) return;

        if (world.rand.nextInt(100) > TwerkConfig.core.chance) return;

        grow(world, player);

    }

    public static void grow(World world, EntityPlayer player)
    {
        BlockPos pos = player.getPosition();
        Set<action> action_s = actions.get().getActions();

        for (int x = -TwerkConfig.core.radius; x <= TwerkConfig.core.radius; x++)
        {
            for (int z = -TwerkConfig.core.radius; z <= TwerkConfig.core.radius; z++)
            {
                for (int y = -TwerkConfig.core.radius; y <= TwerkConfig.core.radius; y++)
                {
                    boolean didGrow = false;
                    BlockPos offset = pos.add(x, y, z);
                    IBlockState state = world.getBlockState(offset);

                    if (state == Blocks.AIR)
                    {
                        continue;
                    }

                    if (Twerk.BLACKLIST.isBlacklisted(state) == !TwerkConfig.core.invertBlacklist)
                    {
                        continue;
                    }

                    for (action act : action_s)
                    {
                        if (!act.canApply(world, offset, state, player))
                        {
                            continue;
                        }

                        didGrow = act.execute(world, offset, state, player);
                    }

                    if (didGrow && TwerkConfig.client.showParticles)
                    {
                        world.playEvent(2005, offset, 0);
                    }
                }
            }
        }
    }

}
