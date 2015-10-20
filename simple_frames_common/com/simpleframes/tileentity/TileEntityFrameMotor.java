package com.simpleframes.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFrameMotor extends TileEntity{
    public TileEntityFrameMotor(){
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("powered", false);
    }
}
