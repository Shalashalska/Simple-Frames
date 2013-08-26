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

public class BlockFrame extends BlockSimpleFrames {

    public BlockFrame(int id, Material material, String blockName, float hardness, float resistance) {
        super(id, material, blockName, hardness, resistance);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float par7,
            float par8, float par9) {
        int meta = world.getBlockMetadata(x, y, z);
        switch (meta) {
            case 0:
                world.setBlockMetadataWithNotify(x, y, z, 2, 3);
                break;
            case 2:
                world.setBlockMetadataWithNotify(x, y, z, 3, 3);
                break;
            case 3:
                world.setBlockMetadataWithNotify(x, y, z, 4, 3);
                break;
            case 4:
                world.setBlockMetadataWithNotify(x, y, z, 5, 3);
                break;
            case 5:
                world.setBlockMetadataWithNotify(x, y, z, 6, 3);
                break;
            case 6:
                world.setBlockMetadataWithNotify(x, y, z, 0, 3);
                break;
        }
        return false;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, int unknown) {
        if (world.isBlockIndirectlyGettingPowered(x, y, z) && world.getBlockMetadata(x, y, z) != 1) {
            int meta = world.getBlockMetadata(x, y, z);
            int addY = 0;
            int addX = 0;
            int addZ = 0;
            switch (meta) {
                case 0: {
                    addY = -1;
                    break;
                }
                case 2: {
                    addY = 1;
                    break;
                }
                case 3: {
                    addZ = -1;
                    break;
                }
                case 4: {
                    addZ = 1;
                    break;
                }
                case 5: {
                    addX = -1;
                    break;
                }
                case 6: {
                    addX = 1;
                    break;
                }
            }
            SimpleFrameMotion.simpleFrameMoveMain(world, x, y, z, addX, addY, addZ);
        }
    }

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        icons = new Icon[4];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + (Strings.SIMPLE_FRAMES_BLOCK_NAME + i));
        }
    }

    public Icon getIcon(int face, int meta) {
        switch (meta) {
            case 0: {
                if (face == 0) {
                    return icons[1];
                } else if (face == 1) {
                    return icons[2];
                } else {
                    return icons[3];
                }
            }
            case 1:
                return icons[0];
            default:
                return icons[0];
            case 2: {
                if (face == 1) {
                    return icons[1];
                } else if (face == 0) {
                    return icons[2];
                } else {
                    return icons[3];
                }
            }
            case 3: {
                if (face == 2) {
                    return icons[1];
                } else if (face == 3) {
                    return icons[2];
                } else {
                    return icons[3];
                }
            }
            case 4: {
                if (face == 3) {
                    return icons[1];
                } else if (face == 2) {
                    return icons[2];
                } else {
                    return icons[3];
                }
            }
            case 5: {
                if (face == 4) {
                    return icons[1];
                } else if (face == 5) {
                    return icons[2];
                } else {
                    return icons[3];
                }
            }
            case 6: {
                if (face == 5) {
                    return icons[1];
                } else if (face == 4) {
                    return icons[2];
                } else {
                    return icons[3];
                }
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int id, CreativeTabs creativeTab, List itemList) {
        for (int i = 0; i < 2; i++) {
            itemList.add(new ItemStack(id, 1, i));
        }
    }

    @Override
    public int damageDropped(int metadata) {
        if (metadata == 1) {
            return 1;
        }
        return 0;
    }
}
