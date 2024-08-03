package yuria.growitout.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yuria.growitout.Twerk;
import yuria.growitout.actions.Actions;
import yuria.growitout.Tags;

@Config(modid = Tags.MOD_ID)
@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class TwerkConfig {
    @SubscribeEvent
    public static void onChangeConfigEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (!event.getModID().equals(Tags.MOD_ID)) return;
        ConfigManager.sync(Tags.MOD_ID, Config.Type.INSTANCE);
        Twerk.BLACKLIST.init();
        Actions.get().setup();
    }

    @Config.Name("Client")
    public static final Client client = new Client();
    @Config.Name("Core")
    public static final Core core = new Core();
    @Config.Name("Mystical Agriculture")
    public static final MysticalAgriculture ma = new MysticalAgriculture();
    @Config.Name("AgriCraft")
    public static final AgriCraft ac = new AgriCraft();
    @Config.Name("IC2")
    public static final IC2 ic2 = new IC2();

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

    public static final class IC2 {
        @Config.Name("Enable IC2")
        @Config.Comment("Allow IC2 crops to have effects")
        public boolean enableIC2 = true;

        @Config.Name("Instant grow")
        @Config.Comment("Instant grow IC2 crops")
        public boolean InstantGrowIC2 = false;

        @Config.Name("IC2 grow method")
        @Config.Comment({
                "Grow method for IC2, Enabling Instant grow ignores this.",
                "PERFORM_TICK will perform the IC2 typical growth function with checking for humidity and more",
                "PERFORM_GROWTH_TICK will perform a custom method to PERFORM_TICK but doesn't check for humidity and more"
        })
        public IC2Methods ic2Methods = IC2Methods.PERFORM_TICK;

        public enum IC2Methods {
            PERFORM_TICK,
            PERFORM_GROWTH_TICK
        }
    }


}
