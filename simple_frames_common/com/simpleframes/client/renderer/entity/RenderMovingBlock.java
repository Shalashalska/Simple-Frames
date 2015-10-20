package com.simpleframes.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.simpleframes.entity.EntityMovingBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMovingBlock extends Render
{
    private final RenderBlocks  renderBlocks = new RenderBlocks();
    private static final String __OBFID      = "CL_00000994";

    public RenderMovingBlock()
    {
        this.shadowSize = 1F;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method,
     * always casting down its argument and then handing it off to a worker
     * function which does the actual work. In all probabilty, the class Render
     * is generic (Render<T extends Entity) and this method has signature public
     * void func_76986_a(T entity, double d, double d1, double d2, float f,
     * float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityMovingBlock entity, double x, double y, double z, float p_147918_8_, float p_147918_9_)
    {
        World world = entity.world();
        Block block = entity.block();
        int i = MathHelper.floor_double(entity.posX);
        int j = MathHelper.floor_double(entity.posY);
        int k = MathHelper.floor_double(entity.posZ);

        if (block != null)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x, (float) y, (float) z);
            this.bindEntityTexture(entity);
            GL11.glDisable(GL11.GL_LIGHTING);
            Tessellator tessellator;

            if (block instanceof BlockAnvil)
            {
                this.renderBlocks.blockAccess = world;
                tessellator = Tessellator.instance;
                tessellator.startDrawingQuads();
                tessellator.setTranslation((double) ((float) (-i) - 0.5F), (double) ((float) (-j) - 0.5F), (double) ((float) (-k) - 0.5F));
                this.renderBlocks.renderBlockAnvilMetadata((BlockAnvil) block, i, j, k, entity.getMetadata());
                tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                tessellator.draw();
            }
            else
                if (block instanceof BlockDragonEgg)
                {
                    this.renderBlocks.blockAccess = world;
                    tessellator = Tessellator.instance;
                    tessellator.startDrawingQuads();
                    tessellator.setTranslation((double) ((float) (-i) - 0.5F), (double) ((float) (-j) - 0.5F), (double) ((float) (-k) - 0.5F));
                    this.renderBlocks.renderBlockDragonEgg((BlockDragonEgg) block, i, j, k);
                    tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                    tessellator.draw();
                }
                else
                {
                    this.renderBlocks.setRenderBoundsFromBlock(block);
                    this.renderBlocks.renderBlockSandFalling(block, world, i, j, k, entity.getMetadata());
                }

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called
     * unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityMovingBlock movingBlock)
    {
        return TextureMap.locationBlocksTexture;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called
     * unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityMovingBlock) entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method,
     * always casting down its argument and then handing it off to a worker
     * function which does the actual work. In all probabilty, the class Render
     * is generic (Render<T extends Entity) and this method has signature public
     * void func_76986_a(T entity, double d, double d1, double d2, float f,
     * float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRender((EntityMovingBlock) par1Entity, par2, par4, par6, par8, par9);
    }
}