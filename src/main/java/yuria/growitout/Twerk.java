package yuria.growitout;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yuria.growitout.actions.Actions;
import yuria.growitout.proxies.CommonProxy;
import yuria.growitout.utils.Blacklist;
import yuria.growitout.Tags;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class Twerk {
    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);
    public static Blacklist BLACKLIST;

    @SidedProxy(clientSide = "yuria.growitout.proxies.ClientProxy", serverSide = "yuria.growitout.proxies.CommonProxy")
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
        Actions.get().setup();
        BLACKLIST.init();
    }
}
