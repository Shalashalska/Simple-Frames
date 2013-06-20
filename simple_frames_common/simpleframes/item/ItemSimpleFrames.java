package simpleframes.item;

import simpleframes.SimpleFrames;
import simpleframes.lib.Reference;
import net.minecraft.item.Item;

public class ItemSimpleFrames extends Item {

    public ItemSimpleFrames(int id, String itemName) {
        super(id - Reference.SHIFTED_RANGE_ID_CORRECTION);
        this.setUnlocalizedName(itemName);
        this.setCreativeTab(SimpleFrames.tabsMetallum);
    }
}
