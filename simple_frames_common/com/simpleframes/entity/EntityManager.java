package com.simpleframes.entity;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityManager
{
    public static void preInit()
    {
        registerEntities();
    }

    private static void registerEntities()
    {
        createEntity(EntityMovingBlock.class, "MovingBlock");
    }

    public static void createEntity(Class entityClass, String entityName)
    {
        int entityId = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, entityId);
    }
}
