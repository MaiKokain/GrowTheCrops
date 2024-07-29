package yuria.twerk.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import yuria.twerk.config.TwerkConfig;

import java.util.*;
import java.util.stream.Collectors;

public class Blacklist {
    public Blacklist()
    {
        trueBlacklist = new ArrayList<>(Arrays.asList(TwerkConfig.core.blacklists));
    }

    public boolean IS_INIT = false;
    public static Set<String> WILDCARD_cache = new HashSet<>();
    public static ArrayList<String> trueBlacklist = new ArrayList<>();

    public void init()
    {
        if (IS_INIT)
        {
            trueBlacklist.clear();
            WILDCARD_cache.clear();

            trueBlacklist = new ArrayList<>(Arrays.asList(TwerkConfig.core.blacklists));
            WILDCARD_cache.addAll(
                    trueBlacklist.stream().filter(e -> e.contains("*"))
                            .map(e -> e.split(":")[0])
                            .collect(Collectors.toSet())
            );
            return;
        }
        WILDCARD_cache.addAll(
                trueBlacklist.stream().filter(e -> e.contains("*"))
                        .map(e -> e.split(":")[0])
                        .collect(Collectors.toSet())
        );
        IS_INIT = true;
    }

    public boolean isBlacklisted(IBlockState blockState)
    {
        ResourceLocation resourceLocation = Objects.requireNonNull(blockState.getBlock().getRegistryName());

        if (trueBlacklist.contains(resourceLocation.toString()) || WILDCARD_cache.contains(resourceLocation.getNamespace()))
        {
            return true;
        }
        return false;
    }
}
