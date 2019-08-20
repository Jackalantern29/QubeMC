package com.Jackalantern29.QubeGems.Util;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerParticles {
	static HashMap<UUID, Integer> id = new HashMap<>();
	
	public static void playParticles(Player player) {
		if(!id.containsKey(player.getUniqueId()))
			id.put(player.getUniqueId(), 0);
		int pId = id.get(player.getUniqueId());
		if(pId == 0)
			return;
		else if(pId == 1)
			playRedstoneHalo(player);
	}
	
	public static void playRedstoneHalo(Player player) {
//		if(!id.containsKey(player.getUniqueId()))
//			id.put(player.getUniqueId(), 1);
//		else
//			id.replace(player.getUniqueId(), 1);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "h set " + player.getName() + " reddust halo head");
		//player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 2.5, 0), 1);
		//player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 2.5, 0), 1, );
		//player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 2.5, 0), 1);
	}
	public static void playFlameHalo(Player player) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "h set " + player.getName() + " flame halo feet");
	}
	public static void playLavaDripHalo(Player player) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "h set " + player.getName() + " driplava halo waist");
	}
	public static void playDragonBreathCape(Player player) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "h set " + player.getName() + " dragonbreath cape");

	}
}
