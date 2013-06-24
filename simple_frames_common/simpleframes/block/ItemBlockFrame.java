package simpleframes.block;

import simpleframes.lib.BlockMeta;
import simpleframes.lib.Strings;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockFrame extends ItemBlock{

    public ItemBlockFrame(int id) {
        super(id);
        setHasSubtypes(true);
    }
    public String getUnlocalizedName(ItemStack itemStack){
        String name;
        switch(itemStack.getItemDamage()){
            case BlockMeta.FRAME_META_ID: name = Strings.FRAME_BLOCK_NAME; break;
            case BlockMeta.FRAME_MOTOR_META_ID: name = Strings.FRAME_MOTOR_NAME; break;
            default: name = "NYI_or_Broken";
        }
        return Strings.SIMPLE_FRAMES_BLOCK_NAME + name;
    }
    
    public int getMetadata(int meta){
        return meta;
    }
}
