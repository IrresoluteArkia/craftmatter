package com.irar.craftmatter;

import com.irar.craftmatter.entity.projectile.EntityGrenade;
import com.irar.craftmatter.proxy.IProxy;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(modid = Ref.MODID, version = Ref.VERSION)

public class CraftMatter {
	@SidedProxy(clientSide=Ref.CLIENT_PROXY, serverSide=Ref.SERVER_PROXY)
	public static IProxy proxy;
	
	@Instance("craftmatter")
    public static CraftMatter instance = new CraftMatter();
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	proxy.preInit(event);
    }
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		ResourceLocation resourceLocation = new ResourceLocation(Ref.MODID, "antimatter_grenade");
		EntityRegistry.registerModEntity(resourceLocation, EntityGrenade.class, resourceLocation.toString(), 0, instance, 64, 10, true);
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	proxy.postInit(event);
    }
}
