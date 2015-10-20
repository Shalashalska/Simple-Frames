package com.simpleframes.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

//Extends Entity
public class EntityMovingBlock extends Entity
{
    // Declaring a block here because EntityFallingBlock has it set to private,
    // and has no getters for it.
    private Block         block;
    private int           metadata;
    private int           distance;
    private int           ticksExistedNew;
    public int            time;
    public boolean        dropItem;
    private boolean       hurtEntities;
    private int           fallHurtMax;
    private float         fallHurtAmount;
    public NBTTagCompound nbtTag;

    // Constructor. Nothing special going on here.
    public EntityMovingBlock(World world, double x, double y, double z, Block block, double motionX, double motionY, double motionZ, int metadata, int distance)
    {
        super(world);
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.block = block;
        this.metadata = metadata;
        this.distance = distance;
        ticksExistedNew = 0;
        this.dropItem = true;
        this.fallHurtMax = 40;
        this.fallHurtAmount = 2.0F;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
        this.setPosition(x, y, z);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.setSize(1, 1);
    }

    // Overrides the default onUpdate for falling blocks so that it doesn't
    // fall.
    @Override
    public void onUpdate()
    {
        System.out.println("IsDead:" + isDead + " PosX:" + posX + " PosY:" + posY + " PosZ:" + posZ);
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        moveEntity(motionX, motionY, motionZ);
        ticksExistedNew++;
        System.out.println("TicksExisted:" + ticksExistedNew);
        if (ticksExistedNew / distance * Math.abs(motionX + motionY + motionZ) >= 1)
        {
            System.out.println("Arrived");
            arrive();
            setDead();
        }
    }

    @Override
    public void setDead()
    {
        isDead = true;
    }

    public void arrive()
    {
        worldObj.setBlock((int) Math.round(posX), (int) Math.round(posY), (int) Math.round(posZ), block, getMetadata(), 3);
    }

    @Override
    protected void entityInit()
    {
        // TODO Auto-generated method stub

    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they
     * walk on. used for spiders and wolves to prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through
     * this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return true;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setByte("Tile", (byte) Block.getIdFromBlock(this.block));
        par1NBTTagCompound.setInteger("TileID", Block.getIdFromBlock(this.block));
        par1NBTTagCompound.setByte("Data", (byte) this.getMetadata());
        par1NBTTagCompound.setByte("Time", (byte) this.time);
        par1NBTTagCompound.setBoolean("DropItem", this.dropItem);
        par1NBTTagCompound.setBoolean("HurtEntities", this.hurtEntities);
        par1NBTTagCompound.setFloat("FallHurtAmount", this.fallHurtAmount);
        par1NBTTagCompound.setInteger("FallHurtMax", this.fallHurtMax);

        if (this.nbtTag != null)
        {
            par1NBTTagCompound.setTag("TileEntityData", this.nbtTag);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        if (par1NBTTagCompound.hasKey("TileID", 99))
        {
            this.block = Block.getBlockById(par1NBTTagCompound.getInteger("TileID"));
        }
        else
        {
            this.block = Block.getBlockById(par1NBTTagCompound.getByte("Tile") & 255);
        }

        this.metadata = par1NBTTagCompound.getByte("Data") & 255;
        this.time = par1NBTTagCompound.getByte("Time") & 255;

        if (par1NBTTagCompound.hasKey("HurtEntities", 99))
        {
            this.hurtEntities = par1NBTTagCompound.getBoolean("HurtEntities");
            this.fallHurtAmount = par1NBTTagCompound.getFloat("FallHurtAmount");
            this.fallHurtMax = par1NBTTagCompound.getInteger("FallHurtMax");
        }
        else
            if (this.block == Blocks.anvil)
            {
                this.hurtEntities = true;
            }

        if (par1NBTTagCompound.hasKey("DropItem", 99))
        {
            this.dropItem = par1NBTTagCompound.getBoolean("DropItem");
        }

        if (par1NBTTagCompound.hasKey("TileEntityData", 10))
        {
            this.nbtTag = par1NBTTagCompound.getCompoundTag("TileEntityData");
        }

        if (this.block.getMaterial() == Material.air)
        {
            this.block = Blocks.sand;
        }
    }

    public void func_145806_a(boolean p_145806_1_)
    {
        this.hurtEntities = p_145806_1_;
    }

    public void addEntityCrashInfo(CrashReportCategory par1CrashReportCategory)
    {
        super.addEntityCrashInfo(par1CrashReportCategory);
        par1CrashReportCategory.addCrashSection("Immitating block ID", Integer.valueOf(Block.getIdFromBlock(this.block)));
        par1CrashReportCategory.addCrashSection("Immitating block data", Integer.valueOf(this.getMetadata()));
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 1F;
    }

    @SideOnly(Side.CLIENT)
    public World world()
    {
        return this.worldObj;
    }

    /**
     * Return whether this entity should be rendered as on fire.
     */
    @SideOnly(Side.CLIENT)
    public boolean canRenderOnFire()
    {
        return false;
    }

    public Block block()
    {
        return this.block;
    }

    public int getMetadata()
    {
        return metadata;
    }
}
