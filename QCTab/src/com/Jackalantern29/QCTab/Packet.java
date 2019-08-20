package com.Jackalantern29.QCTab;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_10_R1.PlayerConnection;

public class Packet {
	public static void sendTabHeaderFooter(Player player, String headerJSON, String footerJSON) {
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
	public static int pingPlayer(Player who) {
        try {
            // Building the version of the server in such a form we can use it
            // in NMS code.
            String bukkitversion = Bukkit.getServer().getClass().getPackage()
                    .getName().substring(23);
            // Getting craftplayer
            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit."
                    + bukkitversion + ".entity.CraftPlayer");
            // Invoking method getHandle() for the player
            Object handle = craftPlayer.getMethod("getHandle").invoke(who);
            // Getting field "ping" that holds player's ping obviously
            Integer ping = (Integer) handle.getClass().getDeclaredField("ping")
                    .get(handle);
            // Returning the ping
            return ping.intValue();
        } catch (ClassNotFoundException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException
                | NoSuchFieldException e) {
            // Handle exceptions however you like, i chose to return value of
            // -1; since player's ping can't be -1.
            return -1;
        }
    }
}
