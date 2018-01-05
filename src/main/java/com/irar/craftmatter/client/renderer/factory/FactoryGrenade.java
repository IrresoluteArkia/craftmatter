package com.irar.craftmatter.client.renderer.factory;

import com.irar.craftmatter.entity.projectile.EntityGrenade;
import com.irar.craftmatter.client.renderer.entity.RenderGrenade;
import com.irar.craftmatter.entity.projectile.EntityGrenade;
import com.irar.craftmatter.handlers.ItemHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class FactoryGrenade implements IRenderFactory<EntityGrenade>{

	@Override
	public Render<? super EntityGrenade> createRenderFor(RenderManager manager) {
		return new RenderGrenade(manager);
	}


}
