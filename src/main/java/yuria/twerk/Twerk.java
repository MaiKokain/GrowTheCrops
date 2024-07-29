package yuria.twerk;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yuria.twerk.actions.actions;
import yuria.twerk.config.TwerkConfig;
import yuria.twerk.proxies.CommonProxy;
import yuria.twerk.utils.Blacklist;
import yuria.yuriatwerksim.Tags;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class Twerk {
    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);
    public static Blacklist BLACKLIST;

    @SidedProxy(clientSide = "yuria.twerk.proxies.ClientProxy", serverSide = "yuria.twerk.proxies.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER.info("PREINIT");
        proxy.registerHandlers();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        BLACKLIST = new Blacklist();
        actions.get().setup();
        BLACKLIST.init();
    }
}
