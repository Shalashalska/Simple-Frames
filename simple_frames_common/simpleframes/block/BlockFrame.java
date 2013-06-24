package simpleframes.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import simpleframes.lib.Reference;
import simpleframes.lib.Strings;
import simpleframes.util.SimpleFrameMotion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFrame extends BlockSimpleFrames{

    public BlockFrame(int id, Material material, String blockName,
            float hardness, float resistance) {
        super(id, material, blockName, hardness, resistance);
    }
    int addX;
    int addY;
    int addZ;
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float par7, float par8, float par9){
        addX = 0;
        addY = 0;
        addZ = 0;
        switch(side){
            case 0: addY = 1; break;
            case 1: addY = -1; break;
            case 2: addZ = 1; break;
            case 3: addZ = -1; break;
            case 4: addX = 1; break;
            case 5: addX = -1; break;
        }
        SimpleFrameMotion.simpleFrameMoveMain(world, x, y, z, addX, addY, addZ);
        return false;
    }
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister){
        icons = new Icon[3];
        
        for(int i = 0; i < icons.length; i++){
            icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + (Strings.SIMPLE_FRAMES_BLOCK_NAME + i));
        }
    }
    public Icon getIcon(int face, int meta){
        switch(meta){
            case 0: {
                if(face == 1){
                    return icons[0];
                } else {
                    return icons[1];
                }
            }
            case 1: return icons[2];
            default: return icons[2];
        }
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int id, CreativeTabs creativeTab, List itemList){
        for(int i = 0; i < 2; i++){
            itemList.add(new ItemStack(id, 1, i));
        }
    }
}
