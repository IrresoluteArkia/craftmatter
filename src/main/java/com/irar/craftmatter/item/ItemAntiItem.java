package com.irar.craftmatter.item;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.irar.craftmatter.Ref;
import com.irar.craftmatter.handlers.CreativeTabsHandler;
import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.ItemModelMesherForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAntiItem extends Item implements ItemMeshDefinition{
	
	public static final String STACK_KEY = Ref.MODID + "_" + "CRAFT_STACK_NBT_KEY";
	private ModelResourceLocation locb = null;
	
	public ItemAntiItem(String name){
		this.setHasSubtypes(true);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabsHandler.CM);
	}
	
	public static ItemStack getWithItemStack(ItemStack stack){
		ItemStack itemstack = new ItemStack(ItemHandler.antiItem, 1);
		itemstack.setTagCompound(new NBTTagCompound());
		itemstack.getTagCompound().setTag(STACK_KEY, stack.serializeNBT());
		return itemstack;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
		
		if(stack.getMetadata() == 0) {
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey(STACK_KEY)){
				tooltip.add("Put In The Antimatter Inverter");
			}else{
				tooltip.add("Invalid Itemstack: please get this item through proper methods!");
			}
		}else if(stack.getMetadata() == 1) {}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public static ItemStack getContainedItemStack(ItemStack itemstack) {
		ItemStack stack = ItemStack.EMPTY;
		if(itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey(STACK_KEY)){
			stack = new ItemStack((NBTTagCompound) itemstack.getTagCompound().getTag(STACK_KEY));
		}
		return stack;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(stack.getMetadata() == 0) {
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey(STACK_KEY)) {
				NBTTagCompound stackData = (NBTTagCompound) stack.getTagCompound().getTag(STACK_KEY);
				ItemStack itemstack = new ItemStack(stackData);
				return "Antimatter " + itemstack.getItem().getItemStackDisplayName(itemstack);
			}
			return "Antimatter Item";
		}else if(stack.getMetadata() == 1){
			return "Anything";
		}
		return "Antimatter Item";
	}
	
	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		if(stack.getMetadata() == 0) {
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey(STACK_KEY)) {
				NBTTagCompound stackData = (NBTTagCompound) stack.getTagCompound().getTag(STACK_KEY);
				ItemStack itemstack = new ItemStack(stackData);
				IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(itemstack);
				CommonProxy.modelRegistry.getKeys().forEach(new Consumer<ModelResourceLocation>() {
					@Override
					public void accept(ModelResourceLocation arg0) {
						if(CommonProxy.modelRegistry.getObject(arg0).equals(model)) {
							locb = arg0;
						}
					}
				});
				if(locb == null) {
					locb = new ModelResourceLocation(itemstack.getItem().getRegistryName(), "inventory");
				}
				System.out.println(locb.getResourceDomain() + ":" + locb.getResourcePath() + ":" + locb.getVariant());
				return locb;
			}else {
				return new ModelResourceLocation(this.getRegistryName(), "inventory");
			}
		}else if(stack.getMetadata() == 1){
			return new ModelResourceLocation(new ResourceLocation("craftmatter", "asterisk"), "inventory");
		}
		return new ModelResourceLocation(this.getRegistryName(), "inventory");
	}
	
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            for (int i = 0; i < 2; ++i)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

}
