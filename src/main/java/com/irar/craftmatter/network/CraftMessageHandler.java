package com.irar.craftmatter.network;

import java.util.ArrayList;

import com.irar.craftmatter.proxy.CommonProxy;
import com.irar.craftmatter.tileentity.TileBase;
import com.irar.craftmatter.tileentity.TileBlueMaker;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

// The params of the IMessageHandler are <REQ, REPLY>
// This means that the first param is the packet you are receiving, and the second is the packet you are returning.
// The returned packet can be used as a "response" from a sent packet.
public class CraftMessageHandler implements IMessageHandler<CraftMessage, IMessage> {
	// Do note that the default constructor is required, but implicitly defined in this case
	
	@Override 
	public IMessage onMessage(CraftMessage message, MessageContext ctx) {
		// This is the player the packet was sent to the server from
		// The value that was sent
		ArrayList<Integer> data = message.data;
		// Execute the action on the main server thread by adding it as a scheduled task
		Minecraft.getMinecraft().addScheduledTask(() -> {
			BlockPos pos = new BlockPos(data.get(0), data.get(1), data.get(2));
			int amount = data.get(3);
			TileEntity te = Minecraft.getMinecraft().world.getTileEntity(pos);
			if(te instanceof TileBase) {
				TileBase bm = (TileBase) te;
				bm.amountMatter = amount;
				bm.writeMatterToItemStack();
			}
		});
		// No response packet
		return null;
	}
}