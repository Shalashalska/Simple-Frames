package simpleframes.util;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import simpleframes.lib.BlockIds;
import simpleframes.lib.BlockMeta;
import simpleframes.lib.Reference;

public class SimpleFrameMotion {
    private Set<BlockData> movingBlocks = new HashSet<BlockData>();
    private World world;
    private int addX;
    private int addY;
    private int addZ;

    private static class BlockData {
        int x;
        int y;
        int z;
        int id;
        int meta;
        TileEntity tileEntity;
        NBTTagCompound tileEntityNBT;
        boolean hadTileEntity;

        BlockData(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        void addMetadata(int id, int meta, TileEntity tileEntity) {
            this.id = id;
            this.meta = meta;
            this.tileEntity = tileEntity;
        }

        public BlockData[] neighbors() {
            BlockData[] neighbors = new BlockData[] { new BlockData(x + 1, y, z), new BlockData(x - 1, y, z),
                    new BlockData(x, y + 1, z), new BlockData(x, y - 1, z), new BlockData(x, y, z + 1),
                    new BlockData(x, y, z - 1) };

            return neighbors;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            result = prime * result + z;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            BlockData other = (BlockData) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            if (z != other.z)
                return false;
            return true;
        }
    }

    private SimpleFrameMotion(World world, int addX, int addY, int addZ) {
        this.world = world;
        this.addX = addX;
        this.addY = addY;
        this.addZ = addZ;
    }

    public static void simpleFrameMoveMain(World world, int x, int y, int z, int addX, int addY, int addZ) {
        SimpleFrameMotion simpleFrameMotion = new SimpleFrameMotion(world, addX, addY, addZ);
        BlockData startingPoint = new BlockData(x, y, z);
        simpleFrameMotion.findBlocksToMove(startingPoint);
        if (simpleFrameMotion.getCanMoveMain()) {
            simpleFrameMotion.simpleFrameMove(startingPoint);
            simpleFrameMotion.movingBlocks.clear();
        }
    }

    private void simpleFrameMove(BlockData p) {
        //Gets data about the blocks and turns them to air
        for (BlockData movingBlock : movingBlocks) {
            movingBlock.addMetadata(world.getBlockId(movingBlock.x, movingBlock.y, movingBlock.z),
                    world.getBlockMetadata(movingBlock.x, movingBlock.y, movingBlock.z),
                    world.getBlockTileEntity(movingBlock.x, movingBlock.y, movingBlock.z));
            if (movingBlock.tileEntity != null) {
                movingBlock.tileEntityNBT = new NBTTagCompound();
                movingBlock.tileEntity.writeToNBT(movingBlock.tileEntityNBT);
                movingBlock.tileEntity.invalidate();
                movingBlock.hadTileEntity = true;
            }
            world.setBlockToAir(movingBlock.x, movingBlock.y, movingBlock.z);
        }
        //Puts the blocks back in the new positions
        for (BlockData movingBlock : movingBlocks) {
            world.setBlock(movingBlock.x + addX, movingBlock.y + addY, movingBlock.z + addZ, movingBlock.id, movingBlock.meta, 3);
            if (movingBlock.hadTileEntity == true) {
                movingBlock.tileEntity.yCoord = -1;
                movingBlock.tileEntityNBT.setInteger("x", movingBlock.x + addX);
                movingBlock.tileEntityNBT.setInteger("y", movingBlock.y + addY);
                movingBlock.tileEntityNBT.setInteger("z", movingBlock.z + addZ);
                world.getBlockTileEntity(movingBlock.x + addX, movingBlock.y + addY, movingBlock.z + addZ).readFromNBT(movingBlock.tileEntityNBT);
            }
        }
    }

    private boolean getCanMoveMain() {
        for (BlockData movingBlock : movingBlocks) {
            if (movingBlock.y >= Reference.CEILING && addY >= 1) {
                return false;
            }
            if (!world.isAirBlock(movingBlock.x + addX, movingBlock.y + addY, movingBlock.z + addZ) &&
                !world.getBlockMaterial(movingBlock.x + addX, movingBlock.y + addY, movingBlock.z + addZ).isReplaceable() &&
                !movingBlocks.contains(new BlockData(movingBlock.x + addX, movingBlock.y + addY, movingBlock.z + addZ))) {
                return false;
            }
        }
        return true;
    }

    private void findBlocksToMove(BlockData p) {
        if (world.isAirBlock(p.x, p.y, p.z)) {
            return;
        }
        movingBlocks.add(p);
        if (world.getBlockId(p.x, p.y, p.z) == BlockIds.SIMPLE_FRAMES_META_BLOCK_ID &&
            world.getBlockMetadata(p.x, p.y, p.z) == BlockMeta.FRAME_META_ID) {
            for (BlockData neighbor : p.neighbors()) {
                if (!movingBlocks.contains(neighbor)) {
                    findBlocksToMove(neighbor);
                }
            }
        }
    }
}
