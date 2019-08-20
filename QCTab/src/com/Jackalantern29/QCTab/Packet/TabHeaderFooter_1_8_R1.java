package com.Jackalantern29.QCTab.Packet;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R1.PlayerConnection;

public class TabHeaderFooter_1_8_R1 implements TabHeaderFooter {
	public void sendTabHeaderFooter(Player player, String headerJSON, String footerJSON) {
	    CraftPlayer craftplayer = (CraftPlayer)player.getPlayer();
	    PlayerConnection connection = craftplayer.getHandle().playerConnection;
	    PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
	    IChatBaseComponent footer = ChatSerializer.a("{\"text\":\"" + footerJSON + "\"}");
	    IChatBaseComponent header = ChatSerializer.a("{\"text\":\"" + headerJSON + "\"}");
	    try {
	      Field headerField = packet.getClass().getDeclaredField("a");
	      headerField.setAccessible(true);
	      headerField.set(packet, header);
	      headerField.setAccessible(!headerField.isAccessible());

	      Field footerField = packet.getClass().getDeclaredField("b");
	      footerField.setAccessible(true);
	      footerField.set(packet, footer);
	      footerField.setAccessible(!footerField.isAccessible());
	    }
	    catch (Exception err) {
	      err.printStackTrace();
	    }
	    connection.sendPacket(packet);
	}
}
