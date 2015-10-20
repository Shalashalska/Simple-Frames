package com.simpleframes;

import net.minecraft.creativetab.CreativeTabs;

import com.simpleframes.block.BlockManager;
import com.simpleframes.configuration.ConfigurationHandler;
import com.simpleframes.core.proxy.ClientProxy;
import com.simpleframes.core.proxy.CommonProxy;
import com.simpleframes.creativetab.CreativeTabSimpleFrames;
import com.simpleframes.entity.EntityManager;
import com.simpleframes.item.ItemManager;
import com.simpleframes.item.crafting.SimpleFramesCrafting;
import com.simpleframes.lib.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-after:Forge@[7.7.1.675, )")
public class SimpleFrames {

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    public static CreativeTabs tabsSimpleFrames = new CreativeTabSimpleFrames(CreativeTabs.getNextID(), Reference.MOD_NAME);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.config(event);
        ItemManager.preInit();
        BlockManager.preInit();
        EntityManager.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        SimpleFramesCrafting.init();
        proxy.registerRenders();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
