package simpleframes.block;

import simpleframes.SimpleFrames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSimpleFrames extends Block {

    public BlockSimpleFrames(int id, Material material, String blockName, float hardness, float resistance) {
        super(id, material);
        this.setUnlocalizedName(blockName);
        this.setCreativeTab(SimpleFrames.tabsMetallum);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }
}
