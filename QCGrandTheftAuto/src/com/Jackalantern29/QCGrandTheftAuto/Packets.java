package com.Jackalantern29.QCGrandTheftAuto;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;

public class Packets {
	public static void sendActionBar(Player player, String message) {
		String string = ChatColor.translateAlternateColorCodes('&', message);
		IChatBaseComponent msg = ChatSerializer.a("{\"text\": \"" + string + "\"}");
		PacketPlayOutChat bar = new PacketPlayOutChat(msg, (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
	}
}
