package com.irar.craftmatter;

import com.google.common.collect.ImmutableList;
import com.irar.craftmatter.crafting.Mapper;
import com.irar.craftmatter.entity.projectile.EntityGrenade;
import com.irar.craftmatter.proxy.IProxy;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;
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
    
    @EventHandler
    public void serverStart(FMLServerAboutToStartEvent event) {
		Mapper.init();
    }
    
    @EventHandler
    public void comms(IMCEvent event) {
    	ImmutableList<IMCMessage> messages = event.getMessages();
    	ItemStack stack = ItemStack.EMPTY;
    	int value = 0;
    	for(IMCMessage imessage : messages) {
    		if(imessage.isItemStackMessage()) {
    			stack = imessage.getItemStackValue();
    		}
    		if(imessage.isStringMessage()) {
    			try {
    				value = Integer.parseInt(imessage.getStringValue());
    			}catch(NumberFormatException e) {
    				; // eating the error! yum, yum!
    			}
    		}
    	}
    	if(!stack.isEmpty() && value != 0) {
    		Mapper.addPermanentMapping(stack, value);
    	}
    }
}
