package com.simpleframes.block;

import com.simpleframes.SimpleFrames;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSimpleFrames extends Block {

    public BlockSimpleFrames(Material material, float hardness, float resistance) {
        super(material);
        this.setCreativeTab(SimpleFrames.tabsSimpleFrames);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }
}
