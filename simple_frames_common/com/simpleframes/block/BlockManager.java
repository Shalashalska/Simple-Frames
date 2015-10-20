package com.simpleframes.block;

import com.simpleframes.lib.BlockIds;
import com.simpleframes.lib.BlockMeta;
import com.simpleframes.lib.Strings;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


public class BlockManager {
    
    public static Block simpleFrameBlock;
    public static void preInit(){
    	initializeBlocks();
        registerBlocks();

    }
    private static void initializeBlocks(){
        simpleFrameBlock = new BlockFrame(Material.iron, 1.0f, 50.0f);
    }
    private static void registerBlocks(){ 
        GameRegistry.registerBlock(simpleFrameBlock, ItemBlockFrame.class, simpleFrameBlock.getUnlocalizedName().replace("tile.",""));
    }
}
