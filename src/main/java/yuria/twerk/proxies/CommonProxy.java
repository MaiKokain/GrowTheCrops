package yuria.twerk.proxies;

import net.minecraftforge.common.MinecraftForge;
import yuria.twerk.events.EventHandler;

public class CommonProxy
{
    public boolean isClient()
    {
        return false;
    }

    public void registerHandlers()
    {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
}