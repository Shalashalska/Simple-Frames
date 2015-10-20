package com.simpleframes.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.simpleframes.lib.Strings;

public class ItemBlockFrame extends ItemBlock
{

    public ItemBlockFrame(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    public String getItemStackDisplayName(ItemStack itemStack)
    {
        int meta = itemStack.getItemDamage();
        if (meta < 0 || meta > Strings.FRAME_BLOCK_DISPLAY_NAMES.length)
        {
            return "Bad Metadata ID!";
        }
        return Strings.FRAME_BLOCK_DISPLAY_NAMES[meta];
    }

    public String getUnlocalizedName(ItemStack itemStack)
    {
        String name;
        int meta = itemStack.getItemDamage();
        if (meta < 0 || meta > Strings.FRAME_BLOCK_SUBNAMES.length)
        {
            return "badMetadataID";
        }
        return super.getUnlocalizedName() + Strings.FRAME_BLOCK_SUBNAMES[meta];
    }

    public int getMetadata(int meta)
    {
        return meta;
    }
}
