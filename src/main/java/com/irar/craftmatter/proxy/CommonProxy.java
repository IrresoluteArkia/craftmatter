package com.irar.craftmatter.proxy;

import java.util.ArrayList;
import java.util.Random;

import com.irar.craftmatter.CraftMatter;
import com.irar.craftmatter.crafting.Mapper;
import com.irar.craftmatter.handlers.BlockHandler;
import com.irar.craftmatter.handlers.CraftingHandler;
import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.network.GuiHandler;
import com.irar.craftmatter.tileentity.ModTileEntities;

import net.minecraft.client.main.Main;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class CommonProxy implements IProxy{
	
//	public static SaveDataHandler saveData;
	public static IForgeRegistry<IRecipe> recipeRegistry;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
        final MainEventHandler handler = new MainEventHandler();
        MinecraftForge.EVENT_BUS.register((Object)handler);
		ItemHandler.init();
		ItemHandler.register();
		BlockHandler.init();
		BlockHandler.register();
		ModTileEntities.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(CraftMatter.instance, new GuiHandler());
		
	}

	@Override
	public void init(FMLInitializationEvent event) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		Mapper.init();
		CraftingHandler.init();
	}
	
	public static class MainEventHandler{
		@SubscribeEvent
		public void Register(RegistryEvent.Register<IRecipe> event){
			recipeRegistry = event.getRegistry();
		}

//		@SubscribeEvent
/*		public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
			if(!event.player.world.isRemote){
				saveData = SaveDataHandler.get(event.player.world);
				DimensionHandler.registerCustom();
			}
		}*/
 /*       @SubscribeEvent
        public void entTick(final LivingEvent.LivingUpdateEvent event) {
            if (event.getEntity().world.isRemote) {
                return;
            }
            if(event.getEntity() instanceof EntityPlayerMP){
            	EntityPlayerMP player = (EntityPlayerMP)event.getEntity();
            	if(player.dimension == DimensionHandler.DONR_ID){
            		if(player.world.getBlockState(player.getPosition()).getBlock() instanceof DimBlock){
            			if(player.world.getTileEntity(player.getPosition()).equals(null)){
            				player.world.setTileEntity(player.getPosition(), new TileDimMarker());
            			}
            		}
            	}
            }
        }
        @SubscribeEvent
        public void onPostServerTick(TickEvent.ServerTickEvent event){
        	
        }*/

	}

}
