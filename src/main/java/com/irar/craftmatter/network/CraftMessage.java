package com.irar.craftmatter.network;

import java.util.ArrayList;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CraftMessage implements IMessage{

	public ArrayList<Integer> data = new ArrayList<Integer>();
	
	public CraftMessage(){}
	
	public CraftMessage(ArrayList<Integer> toSend) {
		System.out.println("Made it here");
		this.data = toSend;
	}

	@Override 
	public void toBytes(ByteBuf buf) {
		// Writes the int into the buf
		for(int i : data) {
			buf.writeInt(i);
		}
	}
	
	@Override 
	public void fromBytes(ByteBuf buf) {
		// Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
		while(buf.readableBytes() >= 4) {
			data.add(buf.readInt());
		}
	}
}
