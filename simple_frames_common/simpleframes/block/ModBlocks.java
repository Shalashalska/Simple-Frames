package simpleframes.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import simpleframes.lib.BlockIds;
import simpleframes.lib.BlockMeta;
import simpleframes.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


public class ModBlocks {
    
    public static Block simpleFrameBlock;
    public static void preInit(){
        
        registerBlocks();

    }
    public static void init(){
        addNames();
    }
    private static void registerBlocks(){
        simpleFrameBlock = new BlockFrame(BlockIds.SIMPLE_FRAMES_META_BLOCK_ID, Material.iron, Strings.SIMPLE_FRAMES_BLOCK_NAME, 1.0f, 50.0f);
        
        GameRegistry.registerBlock(simpleFrameBlock, ItemBlockFrame.class, Strings.SIMPLE_FRAMES_BLOCK_NAME);
    }
    private static void addNames(){
        LanguageRegistry.addName(new ItemStack(simpleFrameBlock, 1, BlockMeta.FRAME_META_ID), Strings.FRAME_BLOCK_DISPLAY_NAME);
        LanguageRegistry.addName(new ItemStack(simpleFrameBlock, 1, BlockMeta.FRAME_MOTOR_META_ID), Strings.FRAME_MOTOR_DISPLAY_NAME);
    }
}
