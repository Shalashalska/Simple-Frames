package simpleframes.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabSimpleFrames extends CreativeTabs{

    public CreativeTabSimpleFrames(int par1, String par2Str) {
        
        super(par1, par2Str);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex() {
        return 50;
    }
}
