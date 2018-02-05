package com.irar.craftmatter.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.irar.craftmatter.api.CMPlugin;
import com.irar.craftmatter.api.IModPlugin;

import net.minecraftforge.fml.common.discovery.ASMDataTable;

public class Util {

	public static List<IModPlugin> getModPlugins(ASMDataTable asmDataTable) {
		return getInstances(asmDataTable, CMPlugin.class, IModPlugin.class);
	}

	private static <T> List<T> getInstances(ASMDataTable asmDataTable, Class annotationClass, Class<T> instanceClass) {
		String annotationClassName = annotationClass.getCanonicalName();
		Set<ASMDataTable.ASMData> asmDatas = asmDataTable.getAll(annotationClassName);
		List<T> instances = new ArrayList<>();
		for (ASMDataTable.ASMData asmData : asmDatas) {
			try {
				Class<?> asmClass = Class.forName(asmData.getClassName());
				Class<? extends T> asmInstanceClass = asmClass.asSubclass(instanceClass);
				T instance = asmInstanceClass.newInstance();
				instances.add(instance);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | LinkageError e) {
				Logger.getLogger("craftmatter").log(Level.SEVERE, "Failed to load plugin!!!");
			}
		}
		return instances;
	}
}
