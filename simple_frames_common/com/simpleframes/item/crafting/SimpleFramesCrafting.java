package com.simpleframes.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.simpleframes.lib.BlockIds;
import com.simpleframes.lib.BlockMeta;
import com.simpleframes.block.BlockManager;
import com.simpleframes.block.BlockSimpleFrames;

import cpw.mods.fml.common.registry.GameRegistry;


public class SimpleFramesCrafting {

    public static void init(){
        GameRegistry.addRecipe(new ItemStack(BlockManager.simpleFrameBlock, 4, BlockMeta.FRAME_META_ID), "sss", "sis", "sss", 's', Items.stick, 'i', Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(BlockManager.simpleFrameBlock, 1, BlockMeta.FRAME_MOTOR_META_ID), "sss", "iii", "sss", 's', Items.stick, 'i', Items.iron_ingot);
    }
}
