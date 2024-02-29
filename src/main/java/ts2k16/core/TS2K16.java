package ts2k16.core;

import jdk.nashorn.internal.ir.annotations.Ignore;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;
import ts2k16.core.proxies.CommonProxy;
import ts2k16.core.proxies.GrowthManagerProxy;
import ts2k16.handlers.ConfigHandler;
import ts2k16.handlers.PlayerGrowthManagerDummy;

@Mod(modid = TS2K16.MODID, version = TS2K16.VERSION, name = TS2K16.NAME, guiFactory = TS2K16.MODID + ".handlers.ConfigGuiFactory")
public class TS2K16
{
    public static final String MODID = "ts2k16";
    public static final String VERSION = "1.0.0";
    public static final String BRANCH = "CI_MOD_BRANCH";
    public static final String HASH = "CI_MOD_HASH";
    public static final String NAME = "Twerk-Sim 2K16";
    public static final String PROXY = MODID + ".core.proxies";
    public static final String CHANNEL = "TS_SIM_CHAN";

    public static GrowthManagerProxy RealGrowthManager;
	
	@Instance(MODID)
	public static TS2K16 instance;
	
	@SidedProxy(clientSide = PROXY + ".ClientProxy", serverSide = PROXY + ".CommonProxy")
	public static CommonProxy proxy;
	public SimpleNetworkWrapper network;
	public static Logger logger;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
    	network = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);
    	
    	ConfigHandler.config = new Configuration(event.getSuggestedConfigurationFile(), true);
    	ConfigHandler.initConfigs();

        if (Loader.isModLoaded("mysticalagriculture")) {
            try {
                RealGrowthManager = Class.forName("ts2k16.handlers.PlayerGrowthManagerTrue").asSubclass(GrowthManagerProxy.class).newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            RealGrowthManager = new PlayerGrowthManagerDummy();
        }
    	proxy.registerHandlers();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
