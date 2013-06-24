package simpleframes.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import simpleframes.SimpleFrames;

public class BlockSimpleFrames extends Block {

    public BlockSimpleFrames(int id, Material material, String blockName, float hardness, float resistance) {
        super(id, material);
        this.setUnlocalizedName(blockName);
        this.setCreativeTab(SimpleFrames.tabsSimpleFrames);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }
}
