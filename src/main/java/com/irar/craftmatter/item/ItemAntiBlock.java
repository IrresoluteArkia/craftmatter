package com.irar.craftmatter.item;

import java.util.function.Consumer;

import com.irar.craftmatter.Ref;
import com.irar.craftmatter.handlers.BlockHandler;
import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class ItemAntiBlock extends ItemBlock implements ItemMeshDefinition{

	public static final String STACK_KEY = Ref.MODID + "_" + "CRAFT_STACK_NBT_KEY";
	private ModelResourceLocation locb;

	public ItemAntiBlock(Block block) {
		super(block);
	}
	
	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
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
			return locb;
		}else {
			return new ModelResourceLocation(this.getRegistryName(), "inventory");
		}
	}
	
	public static ItemStack getWithItemStack(ItemStack stack){
		ItemStack itemstack = new ItemStack(BlockHandler.ibAntiBlock, 1);
		itemstack.setTagCompound(new NBTTagCompound());
		itemstack.getTagCompound().setTag(STACK_KEY, stack.serializeNBT());
		return itemstack;
	}

	public static ItemStack getContainedItemStack(ItemStack itemstack) {
		ItemStack stack = new ItemStack(Blocks.BONE_BLOCK, 1);
		if(itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey(STACK_KEY)){
			stack = new ItemStack((NBTTagCompound) itemstack.getTagCompound().getTag(STACK_KEY));
		}
		return stack;
	}

}
