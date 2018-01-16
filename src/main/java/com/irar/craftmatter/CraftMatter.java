package com.irar.craftmatter;

import java.util.ArrayList;

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
    
    private ArrayList<IMCMessage> processedMessages = new ArrayList<IMCMessage>();
    @EventHandler
    public void comms(IMCEvent event) {
    	ImmutableList<IMCMessage> messages = event.getMessages();
    	IMCMessage stack = null;
    	IMCMessage value = null;
    	boolean hasStack = false;
    	boolean hasValue = false;
    	for(IMCMessage imessage : messages) {
    		if(!processedMessages.contains(imessage) && imessage.key.equalsIgnoreCase("MATTER_VALUE_REGISTRATION")) {
	    		if(imessage.isItemStackMessage()) {
	    			if(!hasStack) {
	    				if(hasValue) {
	    					if(stack.getSender().equals(imessage.getSender())) {
	    	    				stack = imessage;
	    	    				hasStack = true;
	    					}
	    				}else {
		    				stack = imessage;
		    				hasStack = true;
	    				}
	    			}
	    		}
	    		if(imessage.isStringMessage()) {
	    			if(!hasValue) {
		    			try {
		    				Integer.parseInt(imessage.getStringValue());
		    				if(hasStack) {
		    					if(stack.getSender().equals(imessage.getSender())) {
				    				value = imessage;
				    				hasValue = true;
		    					}
		    				}else {
			    				value = imessage;
			    				hasValue = true;
		    				}
		    			}catch(NumberFormatException e) {
		    				hasValue = false; // eating the error! yum, yum!
		    			}
	    			}
	    		}
    		}
        	if(hasStack && hasValue) {
        		if(stack.getSender().equals(value.getSender())) {
	        		processedMessages.add(stack);
	        		processedMessages.add(value);
	    	    	if(!stack.getItemStackValue().isEmpty() && Integer.parseInt(value.getStringValue()) != 0) {
	    	    		Mapper.addPermanentMapping(stack.getItemStackValue(), Integer.parseInt(value.getStringValue()));
	    	    		System.out.println("Registered mapping from mod: " + stack.getSender());
	    	    	}
        			stack = null;
        			value = null;
        			hasStack = false;
        			hasValue = false;
        		}else {
        			stack = null;
        			value = null;
        			hasStack = false;
        			hasValue = false;
        		}
        	}
    	}
    }
}
