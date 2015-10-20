package com.simpleframes.core.proxy;

import com.simpleframes.client.renderer.entity.RenderMovingBlock;
import com.simpleframes.entity.EntityMovingBlock;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityMovingBlock.class, new RenderMovingBlock());
    }

    public int addArmor(String armor)
    {
        return RenderingRegistry.addNewArmourRendererPrefix(armor);
    }
}
