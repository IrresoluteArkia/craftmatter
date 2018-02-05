package com.irar.craftmatter.handlers;

import java.util.ArrayList;
import java.util.List;

import com.irar.craftmatter.api.ICustomValue;
import com.irar.craftmatter.api.ICustomValueRegistry;
import com.irar.craftmatter.api.IModPlugin;
import com.irar.craftmatter.util.Util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

public class PluginHandler {

	private static List<IModPlugin> plugins = new ArrayList<>();
	
	public static ICustomValueRegistry customValueRegistry = new ICustomValueRegistry() {

		private List<ICustomValue> values = new ArrayList<>();
		
		@Override
		public void register(ICustomValue value) {
			values.add(value);
		}

		@Override
		public boolean hasValueFor(ItemStack stack) {
			for(ICustomValue value : values) {
				if(value.hasValueFor(stack)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public int getValueFor(ItemStack stack) {
			for(ICustomValue value : values) {
				if(value.hasValueFor(stack)) {
					return value.getValueFor(stack);
				}
			}
			return 1;
		}
		
	};
	
	public static void init(ASMDataTable asmDataTable) {
		plugins = Util.getModPlugins(asmDataTable);
	}
	
	
	
}
