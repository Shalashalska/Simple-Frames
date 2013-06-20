package simpleframes;

import net.minecraft.creativetab.CreativeTabs;
import simpleframes.block.ModBlocks;
import simpleframes.configuration.ConfigurationHandler;
import simpleframes.core.proxy.CommonProxy;
import simpleframes.creativetab.CreativeTabSimpleFrames;
import simpleframes.item.ModItems;
import simpleframes.lib.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-after:Forge@[7.7.1.675,)")
public class SimpleFrames {

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    public static CreativeTabs tabsMetallum = new CreativeTabSimpleFrames(CreativeTabs.getNextID(), Reference.MOD_NAME);

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.config(event);
        ModItems.preInit();
        ModBlocks.preInit();
    }

    @Init
    public void init(FMLInitializationEvent event) {
        ModItems.init();
        ModBlocks.init();
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {

    }
}
