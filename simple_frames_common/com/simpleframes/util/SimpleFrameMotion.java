package com.simpleframes.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.simpleframes.block.BlockManager;
import com.simpleframes.entity.EntityMovingBlock;
import com.simpleframes.lib.BlockMeta;
import com.simpleframes.lib.Reference;

public class SimpleFrameMotion
{
    private Set<BlockData> movingBlocks = new HashSet<BlockData>();
    private List<Entity>   movingFrame  = new ArrayList<Entity>();
    private World          world;
    private int            addX;
    private int            addY;
    private int            addZ;
    private double         velX;
    private double         velY;
    private double         velZ;

    private static class BlockData
    {
        int            x;
        int            y;
        int            z;
        int            meta;
        Block          block;
        TileEntity     tileEntity;
        NBTTagCompound tileEntityNBT;
        boolean        hadTileEntity;

        BlockData(int x, int y, int z)
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        void addData(Block block, int meta, TileEntity tileEntity)
        {
            this.block = block;
            this.meta = meta;
            this.tileEntity = tileEntity;
        }

        public BlockData[] neighbors()
        {
            BlockData[] neighbors = new BlockData[] { new BlockData(x + 1, y, z), new BlockData(x - 1, y, z), new BlockData(x, y + 1, z),
                    new BlockData(x, y - 1, z), new BlockData(x, y, z + 1), new BlockData(x, y, z - 1) };

            return neighbors;
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            result = prime * result + z;
            return result;
        }

        @Override
        public boolean equals(Object obj)
        {
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

    private SimpleFrameMotion(World world, int addX, int addY, int addZ, double velX, double velY, double velZ)
    {
        this.world = world;
        this.addX = addX;
        this.addY = addY;
        this.addZ = addZ;
        this.velX = velX;
        this.velY = velY;
        this.velZ = velZ;
    }

    public static void simpleFrameMoveMain(World world, int x, int y, int z, int addX, int addY, int addZ, double velX, double velY, double velZ)
    {
        SimpleFrameMotion simpleFrameMotion = new SimpleFrameMotion(world, addX, addY, addZ, velX, velY, velZ);
        BlockData startingPoint = new BlockData(x, y, z);
        simpleFrameMotion.findBlocksToMoveFirst(startingPoint);
        if (simpleFrameMotion.getCanMoveMain())
        {
            simpleFrameMotion.simpleFrameMove(startingPoint);
            simpleFrameMotion.movingBlocks.clear();
        }
    }

    private void simpleFrameMove(BlockData p)
    {
        // Gets data about the blocks and turns them to air
        for (BlockData movingBlock : movingBlocks)
        {
            movingBlock.addData(world.getBlock(movingBlock.x, movingBlock.y, movingBlock.z),
                    world.getBlockMetadata(movingBlock.x, movingBlock.y, movingBlock.z), world.getTileEntity(movingBlock.x, movingBlock.y, movingBlock.z));
            world.removeTileEntity(movingBlock.x, movingBlock.y, movingBlock.z);
            if (movingBlock.tileEntity != null)
            {
                movingBlock.tileEntityNBT = new NBTTagCompound();
                movingBlock.tileEntity.writeToNBT(movingBlock.tileEntityNBT);
                movingBlock.tileEntity = null;
                movingBlock.hadTileEntity = true;
            }
            world.setBlockToAir(movingBlock.x, movingBlock.y, movingBlock.z);
        }
        // Puts the blocks back in the new positions
        for (BlockData movingBlock : movingBlocks)
        {
            movingFrame.add(new EntityMovingBlock(world, movingBlock.x, movingBlock.y, movingBlock.z, movingBlock.block, velX, velY, velZ, movingBlock.meta, 1));
            world.spawnEntityInWorld(movingFrame.get(movingFrame.size() - 1));
            if (movingBlock.hadTileEntity == true)
            {
                movingBlock.tileEntityNBT.setInteger("x", movingBlock.x + addX);
                movingBlock.tileEntityNBT.setInteger("y", movingBlock.y + addY);
                movingBlock.tileEntityNBT.setInteger("z", movingBlock.z + addZ);
                world.getTileEntity(movingBlock.x + addX, movingBlock.y + addY, movingBlock.z + addZ).readFromNBT(movingBlock.tileEntityNBT);
            }

        }
    }

    private boolean getCanMoveMain()
    {
        for (BlockData movingBlock : movingBlocks)
        {
            if (movingBlock.y >= Reference.CEILING && addY >= 1)
            {
                return false;
            }
            if (!world.isAirBlock(movingBlock.x + addX, movingBlock.y + addY, movingBlock.z + addZ)
                    && !movingBlocks.contains(new BlockData(movingBlock.x + addX, movingBlock.y + addY, movingBlock.z + addZ)))
            {
                return false;
            }
        }
        return true;
    }

    private void findBlocksToMove(BlockData p)
    {
        if (world.isAirBlock(p.x, p.y, p.z))
        {
            return;
        }
        movingBlocks.add(p);
        if (world.getBlock(p.x, p.y, p.z).equals(BlockManager.simpleFrameBlock) && world.getBlockMetadata(p.x, p.y, p.z) == BlockMeta.FRAME_META_ID)
        {
            for (BlockData neighbor : p.neighbors())
            {
                if (!movingBlocks.contains(neighbor))
                {
                    findBlocksToMove(neighbor);

                }
            }
        }
    }

    private void findBlocksToMoveFirst(BlockData blockData)
    {
        if (world.isAirBlock(blockData.x, blockData.y, blockData.z))
        {
            return;
        }
        movingBlocks.add(blockData);
        if (world.getBlock(blockData.x, blockData.y, blockData.z).equals(BlockManager.simpleFrameBlock))
        {
            for (BlockData neighbor : blockData.neighbors())
            {
                if (!movingBlocks.contains(neighbor))
                {
                    findBlocksToMove(neighbor);
                }
            }
        }
    }
}
