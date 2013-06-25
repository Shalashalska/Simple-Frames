package simpleframes.item.crafting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import simpleframes.lib.BlockIds;
import simpleframes.lib.BlockMeta;
import cpw.mods.fml.common.registry.GameRegistry;


public class SimpleFramesCrafting {

    static ItemStack ironIngotStack = new ItemStack(Item.ingotIron);
    static ItemStack stickStack = new ItemStack(Item.stick);
    public static void init(){
        GameRegistry.addRecipe(new ItemStack(BlockIds.SIMPLE_FRAMES_META_BLOCK_ID, 4, BlockMeta.FRAME_META_ID), "sss", "sis", "sss", 's', stickStack, 'i', ironIngotStack);
        GameRegistry.addRecipe(new ItemStack(BlockIds.SIMPLE_FRAMES_META_BLOCK_ID, 1, BlockMeta.FRAME_MOTOR_META_ID), "sss", "iii", "sss", 's', stickStack, 'i', ironIngotStack);
    }
}
