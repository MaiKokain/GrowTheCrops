package yuria.twerk.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yuria.twerk.Twerk;
import yuria.yuriatwerksim.Tags;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Config(modid = Tags.MOD_ID)
@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class TwerkConfig {
    @SubscribeEvent
    public static void onChangeConfigEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (!event.getModID().equals(Tags.MOD_ID)) return;
        ConfigManager.sync(Tags.MOD_ID, Config.Type.INSTANCE);
        Twerk.BLACKLIST.init();
    }

    @Config.Name("Client")
    public static final Client client = new Client();
    @Config.Name("Core")
    public static final Core core = new Core();
    @Config.Name("Mystical Agriculture")
    public static final MysticalAgriculture ma = new MysticalAgriculture();
    @Config.Name("AgriCraft")
    public static final AgriCraft ac = new AgriCraft();

    public static final class Client
    {
        @Config.Name("Show particles")
        public boolean showParticles = true;
    }

    public static final class Core
    {
        @Config.Name("Enable twerk")
        @Config.Comment("Globally enable or disable twerk")
        public boolean enableTwerk = true;

        @Config.Name("Invert blacklist")
        @Config.Comment("Use blacklist as whitelist.")
        public boolean invertBlacklist = false;

        @Config.Name("Blacklists")
        @Config.Comment("To disallow crops from having effect. (Case Sensitive)")
        public String[] blacklists = new String[]{"minecraft:grass","minecraft:tallgrass"};

        @Config.Name("Radius")
        @Config.Comment("Range of twerk having effects. over 8 is not recommended")
        @Config.RangeInt(min = 1, max = 64)
        public int radius = 3;

        @Config.Name("Chance")
        @Config.Comment("The chance of twerk occuring.")
        @Config.RangeInt(min = 0, max = 100)
        @Config.SlidingOption
        public int chance = 25;

        @Config.Name("Instant grow")
        @Config.Comment("Instant grow crop blocks. Note: this does not affect stuff like saplings")
        public boolean InstantGrow = false;

    }

    public static final class MysticalAgriculture {
        @Config.Name("Enable Mystical Agriculture")
        @Config.Comment("Mystical Agriculture crops to have affect")
        public boolean enableMA = true;

        @Config.Name("Instant grow")
        @Config.Comment("Instant grow Mystical Agriculture crops")
        public boolean InstantGrowMA = false;
    }

    public static final class AgriCraft {
        @Config.Name("Enable AgriCraft")
        @Config.Comment("AgriCraft crops to have affect")
        public boolean enableAC = true;

        @Config.Name("Instant grow")
        @Config.Comment("Instant grow AgriCraft crops")
        public boolean InstantGrowAC = false;
    }
}
