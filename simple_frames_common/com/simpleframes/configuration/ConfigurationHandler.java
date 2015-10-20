package com.simpleframes.configuration;

import com.simpleframes.lib.Strings;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigurationHandler {

    public static int itemIdStart;
    public static int blockIdStart;
    
    public static void config(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(
                event.getSuggestedConfigurationFile());
        config.load();
        
        itemIdStart = config.get(Strings.ITEM_ID_CONFIG_CATEGORY, "CATEGORY_ITEM", 29500).getInt();
        blockIdStart = config.get(Strings.BLOCK_ID_CONFIG_CATEGORY, "CATEGORY_ITEM", 2900).getInt();
        
        config.save();
    }
}
